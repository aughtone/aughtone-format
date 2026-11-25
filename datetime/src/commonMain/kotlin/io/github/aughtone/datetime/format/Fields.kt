package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.platform.MultiplatformPostFormatter
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.FieldFormats
import io.github.aughtone.datetime.format.resources.formats.IntervalFormats
import io.github.aughtone.toolbox.InternalAughtoneFormatApi
import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.concurrent.Volatile
import kotlin.math.abs
import kotlin.time.Instant

/*
 * Formatting a chosen set of fields, the way Unicode CLDR defines it (UTS #35, Dates, "Matching
 * Skeletons").
 *
 * A skeleton names the fields to show and their widths — "yMMMd" is a year, an abbreviated month
 * and a day — and says nothing about order or punctuation. Each locale publishes a pattern for many
 * skeletons (availableFormats); the nearest one is chosen and its field widths adjusted to those
 * asked for. A request no single pattern covers is split into its date and its time, joined by the
 * locale's date-time pattern, and a field that is still missing is added with the locale's
 * appendItems. The matching follows ICU's DateTimePatternGenerator, the reference implementation of
 * that algorithm, so the result is the pattern ICU would choose from the same data.
 */

/**
 * A field, in CLDR's field order from the era down to the zone: [order] is its place in it, which
 * decides which appendItem adds a group of fields (that of the last).
 */
private enum class FieldKind(val order: Int, val appendItem: String, val fieldName: String, val isDate: Boolean) {
    Era(0, "Era", "era", true),
    Year(1, "Year", "year", true),
    Month(3, "Month", "month", true),
    Weekday(6, "Day-Of-Week", "weekday", true),
    Day(9, "Day", "day", true),
    /** The AM/PM marker, which ICU counts as a field of every 12-hour skeleton; never asked for. */
    DayPeriod(10, "", "dayPeriod", false),
    Hour(11, "Hour", "hour", false),
    Minute(12, "Minute", "minute", false),
    Second(13, "Second", "second", false),
    Zone(15, "Timezone", "zone", false),
}

private fun kindOf(letter: Char): FieldKind? = when (letter) {
    'G' -> FieldKind.Era
    'y' -> FieldKind.Year
    'M', 'L' -> FieldKind.Month
    'E', 'c' -> FieldKind.Weekday
    'd' -> FieldKind.Day
    'h', 'H', 'K', 'k' -> FieldKind.Hour
    'm' -> FieldKind.Minute
    's' -> FieldKind.Second
    'z', 'v', 'Z' -> FieldKind.Zone
    else -> null
}

/** The letters a request may use; `Z`, CLDR's offset zone, appears only in published skeletons. */
private const val REQUEST_LETTERS = "GyMLdEchHKkmsvz"

// ICU's field types: a number for a numeric field (plus its width, so widths compare by
// difference), a negative constant for each text width, and a small offset between letters of one
// field (M and L, h and H).
private const val NUMERIC = 0x100
private const val NARROW = -0x101
private const val SHORTER = -0x102
private const val SHORT = -0x103
private const val LONG = -0x104
private const val DELTA = 0x10
private const val EXTRA_FIELD = 0x10000
private const val MISSING_FIELD = 0x1000

private fun textType(count: Int, short: Int = SHORT): Int = when {
    count >= 6 -> SHORTER
    count == 5 -> NARROW
    count == 4 -> LONG
    else -> short
}

/** ICU's type for [letter] written [count] times, by which two skeletons' fields are compared. */
private fun typeOf(letter: Char, count: Int): Int = when (letter) {
    'G' -> textType(count)
    'y', 'd', 'm', 's' -> NUMERIC + count
    'M' -> if (count <= 2) NUMERIC + count else textType(count)
    'L' -> if (count <= 2) NUMERIC + DELTA + count else textType(count) - DELTA
    'E' -> textType(count)
    'c' -> if (count <= 2) NUMERIC + 2 * DELTA + count else textType(count) - 2 * DELTA
    'h' -> NUMERIC + 10 * DELTA + count
    'H' -> NUMERIC + 11 * DELTA + count
    'K' -> NUMERIC + 12 * DELTA + count
    'k' -> NUMERIC + 13 * DELTA + count
    'a' -> SHORT
    'z' -> if (count >= 4) LONG else SHORT
    'v' -> if (count >= 4) LONG - 2 * DELTA else SHORT - 2 * DELTA
    'Z' -> when { count >= 5 -> SHORT - DELTA; count == 4 -> LONG - DELTA; else -> NARROW - DELTA }
    else -> 0
}

private fun isNumeric(letter: Char, count: Int) = typeOf(letter, count) > 0

/** One field of a skeleton: its letter and how many times it is written. */
private data class SkeletonField(val letter: Char, val count: Int) {
    val type: Int get() = typeOf(letter, count)
}

/** A skeleton's fields by kind. */
private typealias Skeleton = Map<FieldKind, SkeletonField>

/**
 * Reads [skeleton] into its fields, or `null` when it holds a letter that is not one of the fields
 * this offers, or a field twice.
 */
private fun parseSkeleton(skeleton: String): Skeleton? {
    val fields = LinkedHashMap<FieldKind, SkeletonField>()
    var index = 0
    while (index < skeleton.length) {
        val letter = skeleton[index]
        val kind = kindOf(letter) ?: return null
        var end = index
        while (end < skeleton.length && skeleton[end] == letter) end++
        if (kind in fields) return null
        fields[kind] = SkeletonField(letter, end - index)
        index = end
    }
    return fields.withDayPeriod()
}

/** These fields with the AM/PM marker ICU adds to a skeleton whose hour is on a 12-hour clock. */
private fun LinkedHashMap<FieldKind, SkeletonField>.withDayPeriod(): LinkedHashMap<FieldKind, SkeletonField> {
    val hour = this[FieldKind.Hour]
    if (hour != null && hour.letter in "hK" && FieldKind.DayPeriod !in this) this[FieldKind.DayPeriod] = SkeletonField('a', 1)
    return this
}

/** [skeleton] read as a request, which may use only [REQUEST_LETTERS]. */
private fun parseRequest(skeleton: String): Skeleton {
    val request = if (skeleton.all { it in REQUEST_LETTERS }) parseSkeleton(skeleton) else null
    require(!request.isNullOrEmpty()) { "Not a skeleton of supported fields: \"$skeleton\"" }
    return request
}

/** How far a candidate skeleton is from the request, and which requested fields it lacks. */
private class Distance(val value: Int, val missing: Set<FieldKind>, val extra: Set<FieldKind>)

/** ICU's distance from [request], counting only the requested fields in [include], to [candidate]. */
private fun distance(request: Skeleton, include: Set<FieldKind>, candidate: Skeleton): Distance {
    var value = 0
    val missing = mutableSetOf<FieldKind>()
    val extra = mutableSetOf<FieldKind>()
    for (kind in FieldKind.entries) {
        val wanted = if (kind in include) request[kind] else null
        val offered = candidate[kind]
        when {
            wanted == null && offered == null -> Unit
            wanted == null -> { value += EXTRA_FIELD; extra += kind }
            offered == null -> { value += MISSING_FIELD; missing += kind }
            else -> value += abs(wanted.type - offered.type)
        }
    }
    return Distance(value, missing, extra)
}

/** A pattern chosen for a request, with the skeleton it was published under. */
private class Match(val pattern: String, val skeleton: Skeleton, val distance: Distance)

/** The fields of [kinds] as a bit mask in field order, by which ICU breaks ties. */
private fun mask(kinds: Set<FieldKind>): Int = kinds.sumOf { 1 shl it.order }

/**
 * The published pattern nearest to [request]'s fields in [include]. Of two at the same distance,
 * ICU keeps the one whose missing fields come later in field order — it would rather lack the day
 * than the year — and so does this; candidates are taken in the order of their skeletons.
 */
private fun bestRaw(request: Skeleton, include: Set<FieldKind>, candidates: List<Pair<Skeleton, String>>): Match? {
    var best: Match? = null
    for ((skeleton, pattern) in candidates) {
        val d = distance(request, include, skeleton)
        val better = best == null || d.value < best.distance.value ||
            (d.value == best.distance.value && mask(best.distance.missing) < mask(d.missing))
        if (better) best = Match(pattern, skeleton, d)
        if (d.value == 0) break
    }
    return best
}

/**
 * [pattern] with its fields set to the widths [request] asks for, as ICU adjusts them when asked
 * to match the hour's width, as JavaScript's `Intl` asks: a text field takes the requested width,
 * and so does a numeric date field or an hour unless the pattern was published for that width
 * already; minutes and seconds keep the pattern's width. A zone
 * takes the requested letter, so a generic zone pattern serves a specific zone; every other field
 * keeps the pattern's letter, so a stand-alone month stays stand-alone.
 */
private fun adjust(pattern: String, published: Skeleton, request: Skeleton): String = buildString {
    for (token in tokenizePattern(pattern)) {
        when (token) {
            is PatternToken.Literal -> append(quoted(token.text))
            is PatternToken.Field -> {
                val kind = kindOf(token.letter)
                val wanted = kind?.let { request[it] }
                if (kind == null || wanted == null) {
                    // A field the request does not name (the AM/PM marker) stays as it is.
                    append(token.letter.toString().repeat(token.count))
                    continue
                }
                val letter = if (kind == FieldKind.Zone) wanted.letter else token.letter
                val count = when (kind) {
                    FieldKind.Minute, FieldKind.Second -> token.count
                    else -> {
                        val publishedField = published[kind]
                        val patternNumeric = isNumeric(token.letter, token.count)
                        val publishedNumeric = publishedField?.let { isNumeric(it.letter, it.count) } ?: patternNumeric
                        val keep = publishedField != null &&
                            (publishedField.count == wanted.count || patternNumeric != publishedNumeric)
                        when {
                            keep -> token.count
                            // A numeric field stays a number and a text field stays text.
                            patternNumeric != isNumeric(wanted.letter, wanted.count) -> token.count
                            else -> wanted.count
                        }
                    }
                }
                append(letter.toString().repeat(count))
            }
        }
    }
}

/** [text] as a pattern literal: letters and apostrophes quoted so they are not read as fields. */
private fun quoted(text: String): String {
    if (text.none { it in 'a'..'z' || it in 'A'..'Z' || it == '\'' }) return text
    return "'" + text.replace("'", "''") + "'"
}

/** Fills `{0}`, `{1}` and `{2}` of a CLDR pattern in one pass. */
private fun fill(pattern: String, vararg values: String): String = buildString {
    var index = 0
    while (index < pattern.length) {
        val slot = if (pattern[index] == '{' && index + 2 < pattern.length && pattern[index + 2] == '}') pattern[index + 1].digitToIntOrNull() else null
        if (slot != null && slot < values.size) {
            append(values[slot])
            index += 3
        } else {
            append(pattern[index])
            index++
        }
    }
}

/**
 * The pattern for [request]'s fields in [include]: the nearest published one, adjusted, with any
 * field it still lacks added through the locale's appendItems.
 */
private fun bestAppending(request: Skeleton, include: Set<FieldKind>, formats: FieldFormats, candidates: List<Pair<Skeleton, String>>): String {
    val first = bestRaw(request, include, candidates) ?: return ""
    var result = adjust(first.pattern, first.skeleton, request)
    var missing = first.distance.missing
    while (missing.isNotEmpty()) {
        val next = bestRaw(request, missing, candidates) ?: break
        val found = missing - next.distance.missing
        if (found.isEmpty()) break
        val piece = adjust(next.pattern, next.skeleton, request)
        val top = found.maxBy { it.order }
        val item = formats.appendItemsByField[top.appendItem]
        if (!item.isNullOrEmpty()) {
            val name = formats.fieldNamesByField[top.fieldName] ?: top.fieldName
            result = fill(item, result, piece, "'" + name.replace("'", "''") + "'")
        }
        missing = next.distance.missing
    }
    return result
}

/**
 * The date-time join for a request's date fields, as UTS #35 chooses it: full for a wide month
 * with a weekday, long for a wide month, medium for an abbreviated month, short otherwise.
 */
private fun dateTimeJoin(request: Skeleton, formats: FieldFormats): String {
    val month = request[FieldKind.Month]
    val wideMonth = month != null && month.count == 4
    val index = when {
        wideMonth && FieldKind.Weekday in request -> 0
        wideMonth -> 1
        month != null && month.count == 3 -> 2
        else -> 3
    }
    return formats.dateTime.getOrNull(index) ?: "{1} {0}"
}

/** [skeleton] written out in field order, the key ICU files a pattern under. */
private fun keyOf(skeleton: Skeleton): String =
    skeleton.entries.filter { it.key != FieldKind.DayPeriod }.sortedBy { it.key.order }
        .joinToString("") { (_, field) -> field.letter.toString().repeat(field.count) }

/** The skeleton [pattern]'s fields spell, without its AM/PM marker, or `null` if it has a field this does not offer. */
private fun skeletonOfPattern(pattern: String): Skeleton? {
    val fields = LinkedHashMap<FieldKind, SkeletonField>()
    for (token in tokenizePattern(pattern)) {
        if (token !is PatternToken.Field || token.letter in "abB") continue
        val kind = kindOf(token.letter) ?: return null
        fields.getOrPut(kind) { SkeletonField(token.letter, token.count) }
    }
    return fields.withDayPeriod()
}

/** ICU's single-field patterns, which it adds for any field the locale gives no pattern of its own. */
private const val CANONICAL_ITEMS = "GyMEdHmsv"

/**
 * The patterns the matching chooses from, in the order ICU tries them: the locale's standard
 * date and time patterns, its availableFormats in the generated order — the locale's own, then
 * each parent's — which replace a standard pattern filed under the same skeleton, and the
 * single-field items, taken a bucket at a time by the first letter of
 * their skeleton, capitals first, and in that order within a bucket.
 */
private fun candidatesOf(formats: FieldFormats): List<Pair<Skeleton, String>> {
    class Candidate(val key: String, val skeleton: Skeleton, var pattern: String)
    val byKey = LinkedHashMap<String, Candidate>()
    fun add(skeleton: Skeleton, pattern: String, replace: Boolean) {
        if (skeleton.isEmpty()) return
        val key = keyOf(skeleton)
        val existing = byKey[key]
        if (existing == null) byKey[key] = Candidate(key, skeleton, pattern) else if (replace) existing.pattern = pattern
    }
    for (pattern in formats.standard) skeletonOfPattern(pattern)?.let { add(it, pattern, replace = false) }
    for ((key, pattern) in formats.patternsBySkeleton) parseSkeleton(key)?.let { add(it, pattern, replace = true) }
    for (letter in CANONICAL_ITEMS) add(parseSkeleton(letter.toString())!!, letter.toString(), replace = false)
    // A stable sort, so each bucket keeps the order its patterns were added in.
    return byKey.values.sortedBy { it.key.first() }.map { it.skeleton to it.pattern }
}

/** The pattern [formats] gives for [request], as ICU's DateTimePatternGenerator would choose it. */
private fun bestPattern(request: Skeleton, formats: FieldFormats): String {
    val candidates = candidatesOf(formats)
    val all = request.keys
    val whole = bestRaw(request, all, candidates)
    if (whole != null && whole.distance.missing.isEmpty() && whole.distance.extra.isEmpty()) {
        return adjust(whole.pattern, whole.skeleton, request)
    }
    val date = all.filter { it.isDate }.toSet()
    val time = all.filterNot { it.isDate }.toSet()
    val datePattern = if (date.isEmpty()) "" else bestAppending(request, date, formats, candidates)
    val timePattern = if (time.isEmpty()) "" else bestAppending(request, time, formats, candidates)
    return when {
        datePattern.isEmpty() -> timePattern
        timePattern.isEmpty() -> datePattern
        else -> fill(dateTimeJoin(request, formats), timePattern, datePattern)
    }
}

/** Patterns already chosen, by locale tag and skeleton. Thread-safe by replacement. */
private object FieldPatternCache {
    @Volatile var patterns = emptyMap<String, String>()
}

/**
 * The CLDR pattern [locale] uses for the fields of [skeleton] ("yMMMd", "Ehm"), as ICU's
 * DateTimePatternGenerator chooses it from the same data.
 *
 * @throws IllegalArgumentException if [skeleton] is empty or holds a letter other than the fields
 *   this offers (`G y M L d E c h H K k m s z v`), or a field twice.
 */
internal fun fieldPattern(skeleton: String, locale: Locale): String {
    val key = "${locale.languageTag}|$skeleton"
    FieldPatternCache.patterns[key]?.let { return it }
    val request = parseRequest(skeleton)
    val pattern = bestPattern(request, Resources.getFieldFormats(locale))
    val cache = FieldPatternCache.patterns
    FieldPatternCache.patterns = if (cache.size >= 500) mapOf(key to pattern) else cache + (key to pattern)
    return pattern
}

/**
 * Writes [date], [time] and the zone at [instant] in [timeZone] by [skeleton]'s fields, laid out
 * as [locale] writes that set of fields.
 *
 * This is the engine behind `formatReadableFields` in `aughtone-format`'s readable module; it is
 * public only so that module can reach it. The caller passes a skeleton of the fields it offers —
 * `G` era, `y` year, `M` month (one or two letters a number, three abbreviated, four full), `d`
 * day, `E` weekday (three letters abbreviated, four full), `h` or `H` hour on a 12- or 24-hour
 * clock, `m` minute, `s` second, `z` zone (three letters abbreviated, four full) — and the values
 * those fields need. A date before year 1 also shows its era, with the year counted from one.
 *
 * Month and weekday names take the form the language uses inside a date, or the stand-alone form
 * where CLDR's pattern asks for it, in lower case where CLDR writes it so ("июнь 2024 г."); digits
 * follow [numberingSystem], or the locale's default.
 *
 * @throws IllegalArgumentException if [skeleton] is not a skeleton of those fields, or names a
 *   field whose value is not given: a date field without [date], a time field without [time], or a
 *   zone without [timeZone] and [instant].
 */
@InternalAughtoneFormatApi
fun formatFieldsSkeleton(
    skeleton: String,
    locale: Locale,
    date: LocalDate? = null,
    time: LocalTime? = null,
    timeZone: TimeZone? = null,
    instant: Instant? = null,
    numberingSystem: NumberingSystem? = null,
): String {
    val request = parseRequest(skeleton)
    for (kind in request.keys) {
        when {
            kind.isDate && date == null -> throw IllegalArgumentException("The ${kind.fieldName} needs a date")
            kind == FieldKind.Zone && (timeZone == null || instant == null) -> throw IllegalArgumentException("The zone needs a time zone and an instant")
            !kind.isDate && kind != FieldKind.Zone && time == null -> throw IllegalArgumentException("The ${kind.fieldName} needs a time")
        }
    }
    // A year before year 1 reads as one of the common era without its era, so the era is shown.
    val withEra = if (date != null && date.year <= 0 && FieldKind.Year in request && FieldKind.Era !in request) "G$skeleton" else skeleton
    val pattern = fieldPattern(withEra, locale)
    val text = renderFieldPattern(pattern, locale, date, time, timeZone, instant)
    return MultiplatformPostFormatter.applyNumberingSystem(text, locale, numberingSystem)
}

/** Writes the fields of a CLDR [pattern] from these values, in Latin digits. */
internal fun renderFieldPattern(
    pattern: String,
    locale: Locale,
    date: LocalDate?,
    time: LocalTime?,
    timeZone: TimeZone?,
    instant: Instant?,
): String {
    val tokens = tokenizePattern(pattern)
    val hasEra = tokens.any { it is PatternToken.Field && it.letter == 'G' }
    return buildString {
        tokens.forEachIndexed { index, token ->
            when (token) {
                // CLDR puts a narrow no-break space before the AM/PM marker; the library's times
                // write a plain space there, and these read as its times do.
                is PatternToken.Literal -> append(
                    if (tokens.getOrNull(index + 1).isDayPeriod() || tokens.getOrNull(index - 1).isDayPeriod()) token.text.replace(' ', ' ') else token.text,
                )
                is PatternToken.Field -> append(renderField(token, locale, date, time, timeZone, instant, hasEra))
            }
        }
    }
}

/**
 * This name with its first letter in lower case. The library's stand-alone names are capitalised
 * for headings ("Июнь"); inside a phrase CLDR writes them in lower case where the language does.
 */
private fun String.lowerFirst(): String = replaceFirstChar { it.lowercaseChar() }

private fun PatternToken?.isDayPeriod() = this is PatternToken.Field && letter in "abB"

private fun renderField(
    field: PatternToken.Field,
    locale: Locale,
    date: LocalDate?,
    time: LocalTime?,
    timeZone: TimeZone?,
    instant: Instant?,
    hasEra: Boolean,
): String {
    val count = field.count
    fun number(value: Int, width: Int) = value.toString().padStart(width, '0')
    return when (field.letter) {
        'G' -> {
            val eras = Resources.getEraNames(locale, abbreviated = count != 4)
            if (date!!.year <= 0) eras.bce else eras.ce
        }
        'y' -> {
            val year = date!!.year
            val shown = if (hasEra && year <= 0) 1 - year else year
            when {
                count == 2 -> number(((shown % 100) + 100) % 100, 2)
                else -> number(shown, count)
            }
        }
        'M', 'L' -> {
            val month = date!!.month
            when {
                count <= 2 -> number(month.number, count)
                field.letter == 'L' -> {
                    val name = Resources.getMonthNamesResource(locale).let { if (count == 4) it.full else it.abbreviated }.names[month.number - 1]
                    if (Resources.getFieldFormats(locale).standAloneMonthsLowerCase) name.lowerFirst() else name
                }
                else -> DynamicLocalDateFormats.getMonthNamesResource(locale).let { if (count == 4) it.full else it.abbreviated }.names[month.number - 1]
            }
        }
        'd' -> number(date!!.day, count)
        'E', 'c' -> {
            val names = if (field.letter == 'c') Resources.getDayOfWeekNamesResource(locale) else DynamicLocalDateFormats.getDayOfWeekNamesResource(locale)
            // The names run Monday to Sunday, as ISO numbers the days.
            val name = (if (count == 4) names.full else names.abbreviated).names[date!!.dayOfWeek.isoDayNumber - 1]
            if (field.letter == 'c' && Resources.getFieldFormats(locale).standAloneDaysLowerCase) name.lowerFirst() else name
        }
        'h' -> number(time!!.hour.let { if (it % 12 == 0) 12 else it % 12 }, count)
        'K' -> number(time!!.hour % 12, count)
        'H' -> number(time!!.hour, count)
        'k' -> number(time!!.hour.let { if (it == 0) 24 else it }, count)
        'm' -> number(time!!.minute, count)
        's' -> number(time!!.second, count)
        'a', 'b', 'B' -> {
            val amPm = Resources.getAmPmStrings(locale)
            if (time!!.hour < 12) amPm.am else amPm.pm
        }
        'z' -> timeZone!!.displayName(instant!!, if (count >= 4) TextWidth.Full else TextWidth.Abbreviated, locale)
        'v' -> timeZone!!.genericDisplayName(if (count >= 4) TextWidth.Full else TextWidth.Abbreviated, locale)
        else -> ""
    }
}

// ── Ranges of chosen fields ──────────────────────────────────────────────────
//
// A range of a chosen set of fields follows ICU's DateIntervalFormat, the reference
// implementation of CLDR's interval formats (UTS #35, Dates, "Interval Formats"): the greatest
// field that differs between the two ends picks the interval pattern of the nearest published
// skeleton, which writes what both ends share once. A difference only in fields smaller than any
// shown is not shown, and the ends are written as one. A difference in a field larger than any
// shown adds that field to both ends ("Jun 1, 2026 – Jun 1, 2027" for a month and a day,
// "Jun 2026 – Jun 2027" for a month). Where
// no pattern fits, two complete values are joined with the locale's fallback.

/**
 * The calendar fields a range compares, in ICU's order from the largest. [size] orders them: a
 * larger field has a larger size.
 */
private enum class RangeField(val kind: FieldKind, val size: Int) {
    Era(FieldKind.Era, 8), Year(FieldKind.Year, 7), Month(FieldKind.Month, 6), Day(FieldKind.Day, 5),
    DayPeriod(FieldKind.DayPeriod, 4), Hour(FieldKind.Hour, 3), Minute(FieldKind.Minute, 2), Second(FieldKind.Second, 1),
}

/** The range field a skeleton field counts as; a weekday counts as its day, as in ICU. */
private fun rangeFieldOf(kind: FieldKind): RangeField? = when (kind) {
    FieldKind.Weekday -> RangeField.Day
    FieldKind.Zone -> null
    else -> RangeField.entries.first { it.kind == kind }
}

/** One end of a range: the values its fields are written from. */
private class RangeEnd(val date: LocalDate?, val time: LocalTime?, val instant: Instant?)

/** The largest field that differs between [start] and [end], or `null` when they agree to the second. */
private fun largestDifference(start: RangeEnd, end: RangeEnd): RangeField? {
    val a = start.date
    val b = end.date
    if (a != null && b != null) {
        if ((a.year <= 0) != (b.year <= 0)) return RangeField.Era
        if (a.year != b.year) return RangeField.Year
        if (a.month != b.month) return RangeField.Month
        if (a.day != b.day) return RangeField.Day
    }
    val s = start.time
    val e = end.time
    if (s != null && e != null) {
        if ((s.hour < 12) != (e.hour < 12)) return RangeField.DayPeriod
        if (s.hour != e.hour) return RangeField.Hour
        if (s.minute != e.minute) return RangeField.Minute
        if (s.second != e.second) return RangeField.Second
    }
    return null
}

/** The letters of a skeleton and how many times each is written, as ICU compares interval skeletons. */
private fun letterWidths(skeleton: String): Map<Char, Int> = buildMap {
    for (letter in skeleton) {
        // ICU reads a specific zone as a generic one, and the other hour letters as h and H.
        val read = when (letter) { 'z' -> 'v'; 'k' -> 'H'; 'K' -> 'h'; 'L' -> 'M'; 'c' -> 'E'; else -> letter }
        this[read] = (this[read] ?: 0) + 1
    }
}

/**
 * The interval skeleton nearest to [skeleton] among [published], as ICU's
 * `DateIntervalInfo::getBestSkeleton` chooses it, and whether it has the same fields: `null` when
 * the nearest has fields of its own or lacks one of [skeleton]'s, which ICU does not use.
 */
private fun bestIntervalSkeleton(skeleton: String, published: Collection<String>): String? {
    val wanted = letterWidths(skeleton)
    var best: String? = null
    var bestDistance = Int.MAX_VALUE
    var bestSameFields = false
    for (candidate in published) {
        val offered = letterWidths(candidate)
        var distance = 0
        var sameFields = true
        for (letter in wanted.keys + offered.keys) {
            val w = wanted[letter] ?: 0
            val o = offered[letter] ?: 0
            when {
                w == o -> Unit
                w == 0 || o == 0 -> { distance += 0x1000; sameFields = false }
                letter == 'M' && (w <= 2) != (o <= 2) -> distance += 0x100
                else -> distance += abs(w - o)
            }
        }
        if (distance < bestDistance) {
            best = candidate
            bestDistance = distance
            bestSameFields = sameFields
            if (distance == 0) break
        }
    }
    return best?.takeIf { bestSameFields }
}

/** [tokens] written back as a pattern, with literals quoted. */
private fun patternOf(tokens: List<PatternToken>): String = tokens.joinToString("") { token ->
    when (token) {
        is PatternToken.Literal -> quoted(token.text)
        is PatternToken.Field -> token.letter.toString().repeat(token.count)
    }
}

/** [request] with [kinds] added in their narrowest numeric form, as ICU extends a skeleton. */
private fun Skeleton.with(vararg kinds: FieldKind): Skeleton {
    val fields = LinkedHashMap(this)
    for (kind in kinds) if (kind !in fields) fields[kind] = SkeletonField(LETTER_OF.getValue(kind), 1)
    return fields
}

private val LETTER_OF = mapOf(FieldKind.Era to 'G', FieldKind.Year to 'y', FieldKind.Month to 'M', FieldKind.Day to 'd')

/** [skeleton] as the interval data's key: in field order, the AM/PM marker left out. */
private fun intervalKeyOf(skeleton: Skeleton): String = keyOf(skeleton)

/**
 * The interval pattern for [request]'s fields when [field] is the largest that differs, adjusted to
 * the requested widths, or `null` when the locale publishes none that fits. A larger date field
 * the request lacks is added, as ICU adds it: a year that differs on a month and a day finds the
 * year's pattern of the month, day and year skeleton, and on a month alone that of the month and
 * year skeleton. A month is added only between a day and a year, so a range of years stays one.
 */
private fun intervalPattern(request: Skeleton, field: RangeField, formats: IntervalFormats): String? {
    val published = formats.patterns
    var best = bestIntervalSkeleton(intervalKeyOf(request), published.keys) ?: return null
    var extended = request
    val showsDay = FieldKind.Day in request || FieldKind.Weekday in request
    fun extend(kind: FieldKind, letter: Char) {
        if (kind in extended || published[best]?.get(letter) != null) return
        val key = letter + best
        val found = if (published[key]?.get(letter) != null) key else bestIntervalSkeleton(intervalKeyOf(extended.with(kind)), published.keys)
        if (found != null && published[found]?.get(letter) != null) {
            best = found
            extended = extended.with(kind)
        }
    }
    when (field) {
        RangeField.Month -> extend(FieldKind.Month, 'M')
        // A month is added between a day and a year, never to a year on its own.
        RangeField.Year -> { if (showsDay) extend(FieldKind.Month, 'M'); extend(FieldKind.Year, 'y') }
        RangeField.Era -> { if (showsDay) extend(FieldKind.Month, 'M'); extend(FieldKind.Year, 'y'); extend(FieldKind.Era, 'G') }
        else -> Unit
    }
    val byField = published[best] ?: return null
    val hourLetter = request[FieldKind.Hour]?.letter?.let { if (it in "hK") 'h' else 'H' } ?: 'h'
    val pattern = when (field) {
        RangeField.Era -> byField['G']
        RangeField.Year -> byField['y']
        RangeField.Month -> byField['M']
        RangeField.Day -> byField['d']
        // A 24-hour skeleton has no AM/PM pattern; a change of AM/PM is a change of hour there.
        RangeField.DayPeriod -> byField['a'] ?: byField[hourLetter]
        RangeField.Hour -> byField[hourLetter]
        RangeField.Minute -> byField['m']
        RangeField.Second -> null
    } ?: return null
    val publishedSkeleton = parseSkeleton(best) ?: return null
    return adjust(pattern, publishedSkeleton, extended)
}

/**
 * Writes the range from [start] to [end] by [request]'s fields in [locale], with the range's own
 * separator marked for [finishRange].
 */
private fun formatFieldsRange(
    request: Skeleton,
    locale: Locale,
    start: RangeEnd,
    end: RangeEnd,
    timeZone: TimeZone?,
): String {
    val formats = Resources.getFieldFormats(locale)
    fun render(pattern: String, at: RangeEnd) = renderFieldPattern(pattern, locale, at.date, at.time, timeZone, at.instant)
    fun single(skeleton: Skeleton, at: RangeEnd) = render(bestPattern(skeleton, formats), at)
    fun fallback(skeleton: Skeleton) =
        fillIntervalFallback(intervalFallbackFor(locale), single(skeleton, start), single(skeleton, end))
    fun interval(pattern: String): String? {
        val halves = splitIntervalTokens(tokenizePattern(pattern)) ?: return null
        return render(patternOf(halves.first.withMarkedSeparator()), start) + render(patternOf(halves.second), end)
    }

    val field = largestDifference(start, end) ?: return single(request, start)
    val shown = request.keys.mapNotNull(::rangeFieldOf)
    // A difference only in fields smaller than any shown does not show.
    if (shown.isEmpty() || field.size < shown.minOf { it.size }) return single(request, start)
    // A date before year 1 is written as two complete dates, each with its era.
    if ((start.date?.year ?: 1) <= 0 || (end.date?.year ?: 1) <= 0) return fallback(request.with(FieldKind.Era))

    val intervals = Resources.getIntervalFormats(locale) ?: return fallback(request)
    val dateKinds = request.keys.filter { it.isDate }
    val timeKinds = request.keys.filterNot { it.isDate }
    val dateField = field.kind.isDate
    return when {
        timeKinds.isEmpty() || dateKinds.isEmpty() && !dateField -> {
            intervalPattern(request, field, intervals)?.let(::interval) ?: fallback(request)
        }
        dateKinds.isEmpty() -> {
            // Times on different days: each end in full, with a short date, as ICU writes them.
            fallback(request.with(FieldKind.Year, FieldKind.Month, FieldKind.Day))
        }
        dateField -> {
            // Different days: each end in full, with every date field down to the one that differs.
            val added = buildList {
                add(FieldKind.Day)
                if (field.size >= RangeField.Month.size) add(FieldKind.Month)
                if (field.size >= RangeField.Year.size) add(FieldKind.Year)
            }
            fallback(request.with(*added.toTypedArray()))
        }
        else -> {
            // One day: the date once, then the range of times, joined by the locale's medium
            // date-time pattern, as ICU joins them in a range.
            val dateRequest = request.filterKeys { it.isDate }
            val timeRequest = request.filterKeys { !it.isDate }
            val datePattern = bestPattern(dateRequest, formats)
            val join = intervals.dateTimeJoin
            val timeInterval = intervalPattern(timeRequest, field, intervals)
            if (timeInterval != null) {
                interval(fill(join, timeInterval, datePattern)) ?: fallback(request)
            } else {
                val times = fillIntervalFallback(intervalFallbackFor(locale), single(timeRequest, start), single(timeRequest, end))
                render(fill(join, TIME_RANGE_SLOT.toString(), datePattern), start).replace(TIME_RANGE_SLOT.toString(), times)
            }
        }
    }
}

/** Stands in a date-time pattern for a range of times rendered on its own. */
private const val TIME_RANGE_SLOT = ''

/**
 * Writes the range from one date and time to another by [skeleton]'s fields, laid out as [locale]
 * writes a range of that set of fields: what both ends share is written once.
 *
 * This is the engine behind `formatReadableFieldsRange` in `aughtone-format`'s readable module; it
 * is public only so that module can reach it. The skeleton and the values are as
 * [formatFieldsSkeleton] takes them, one set for each end. [separators] decides the spaces inside
 * each end and [noBreak] whether the space after the range's dash can break; the space before it
 * never does.
 *
 * @throws IllegalArgumentException as [formatFieldsSkeleton] does, for either end.
 */
@InternalAughtoneFormatApi
fun formatFieldsRangeSkeleton(
    skeleton: String,
    locale: Locale,
    startDate: LocalDate? = null,
    startTime: LocalTime? = null,
    endDate: LocalDate? = null,
    endTime: LocalTime? = null,
    timeZone: TimeZone? = null,
    startInstant: Instant? = null,
    endInstant: Instant? = null,
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String {
    val request = parseRequest(skeleton)
    for (kind in request.keys) {
        when {
            kind.isDate && (startDate == null || endDate == null) -> throw IllegalArgumentException("The ${kind.fieldName} needs a date")
            kind == FieldKind.Zone && (timeZone == null || startInstant == null || endInstant == null) ->
                throw IllegalArgumentException("The zone needs a time zone and an instant")
            !kind.isDate && kind != FieldKind.Zone && (startTime == null || endTime == null) -> throw IllegalArgumentException("The ${kind.fieldName} needs a time")
        }
    }
    val text = formatFieldsRange(
        request, locale, RangeEnd(startDate, startTime, startInstant), RangeEnd(endDate, endTime, endInstant), timeZone,
    )
    return finishRange(MultiplatformPostFormatter.applyNumberingSystem(text, locale, numberingSystem), separators, noBreak)
}
