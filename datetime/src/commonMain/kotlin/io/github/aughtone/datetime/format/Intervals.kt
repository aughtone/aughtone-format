package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.DynamicLocalTimeFormats.to12HourPattern
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.platform.MultiplatformPostFormatter
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.IntervalFormats
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlin.time.Instant

/*
 * Interval formatting: a start and an end written as one range, the way Unicode CLDR defines it.
 *
 * A CLDR interval pattern is chosen by a skeleton (which fields the style shows) and by the
 * greatest field that differs between the two ends. It repeats a field to mark where the end
 * begins: in "MMM d – d, y" everything before the second `d` is written from the start and the
 * rest from the end, so the shared month and year appear once. The fields are then rendered with
 * the library's own builders, names and widths, so a range reads like the library's single
 * values in the same locale.
 */

/** The class a pattern letter belongs to, so that `L` and `M`, or `h` and `H`, count as one field. */
private fun fieldClass(letter: Char): Char = when (letter) {
    'L' -> 'M'
    'c', 'e' -> 'E'
    'H', 'K', 'k' -> 'h'
    else -> letter
}

/**
 * Splits a CLDR interval [pattern] at its first repeated field: the tokens written from the start
 * and the tokens written from the end. Returns `null` for a pattern that repeats no field.
 */
internal fun splitIntervalPattern(pattern: String): Pair<List<PatternToken>, List<PatternToken>>? =
    splitIntervalTokens(tokenizePattern(pattern))

/** [splitIntervalPattern] for a pattern that is already tokenized. */
internal fun splitIntervalTokens(tokens: List<PatternToken>): Pair<List<PatternToken>, List<PatternToken>>? {
    val seen = mutableSetOf<Char>()
    tokens.forEachIndexed { index, token ->
        if (token is PatternToken.Field) {
            val field = fieldClass(token.letter)
            if (field in seen) return tokens.subList(0, index) to tokens.subList(index, tokens.size)
            seen += field
        }
    }
    return null
}

/**
 * These interval tokens, with each field given the letter and width the library's [single]
 * pattern uses for it, so a range writes its fields as the library writes a single value: the
 * same year width, the library's hour field, a text month as long as the single one. A month that
 * is a number in one and a name in the other keeps the interval's form.
 */
internal fun List<PatternToken>.withWidthsOf(single: List<PatternToken>): List<PatternToken> {
    val singleFields = single.filterIsInstance<PatternToken.Field>().associateBy { fieldClass(it.letter) }
    return map { token ->
        if (token !is PatternToken.Field) return@map token
        val field = fieldClass(token.letter)
        val match = singleFields[field] ?: return@map token
        when (field) {
            'h' -> PatternToken.Field(match.letter, match.count)
            'M' -> if ((token.count >= 3) == (match.count >= 3)) PatternToken.Field('M', match.count) else token
            else -> PatternToken.Field(token.letter, match.count)
        }
    }
}

/**
 * These tokens with a narrow no-break space next to the AM/PM field turned into a plain space. CLDR
 * writes "9:00\u202FAM" in its interval patterns, while this library's single times write
 * "9:00 AM"; a range uses the library's spacing so its marker sits as a single time's does.
 */
internal fun List<PatternToken>.withPlainSpaceBeforeDayPeriod(): List<PatternToken> = mapIndexed { index, token ->
    val nextToDayPeriod = listOfNotNull(getOrNull(index - 1), getOrNull(index + 1))
        .any { it is PatternToken.Field && it.letter == 'a' }
    if (token is PatternToken.Literal && nextToDayPeriod) PatternToken.Literal(token.text.replace('\u202F', ' ')) else token
}

/** Fills a CLDR fallback pattern's `{0}` and `{1}` in one pass, so neither value is scanned again. */
internal fun fillIntervalFallback(fallback: String, start: String, end: String): String = buildString {
    var index = 0
    while (index < fallback.length) {
        when {
            fallback.startsWith("{0}", index) -> { append(start); index += 3 }
            fallback.startsWith("{1}", index) -> { append(end); index += 3 }
            else -> { append(fallback[index]); index++ }
        }
    }
}

/** The fallback pattern for [locale], or the English one when the locale has no interval data. */
internal fun intervalFallbackFor(locale: Locale): String = Resources.getIntervalFormats(locale)?.fallback ?: "{0} – {1}"

/**
 * A time range at the `Short` style; longer styles, which CLDR gives no interval patterns for,
 * join two complete times with the fallback. [single] formats one time at [style].
 */
internal fun formatTimeRange(
    start: LocalTime,
    end: LocalTime,
    style: DateTimeStyle,
    locale: Locale,
    twentyFourHour: Boolean,
    numberingSystem: NumberingSystem?,
    single: (LocalTime) -> String,
): String {
    if (style == DateTimeStyle.None) return ""
    if (style != DateTimeStyle.Short) return fillIntervalFallback(intervalFallbackFor(locale), single(start), single(end))

    val greatest = when {
        twentyFourHour && start.hour != end.hour -> 'H'
        !twentyFourHour && (start.hour < 12) != (end.hour < 12) -> 'a'
        !twentyFourHour && start.hour % 12 != end.hour % 12 -> 'h'
        start.minute != end.minute -> 'm'
        // Seconds are not shown at the Short style, so the two read as one time.
        else -> return single(start)
    }
    val skeleton = if (twentyFourHour) "Hm" else "hm"
    val pattern = Resources.getIntervalFormats(locale)?.patterns?.get(skeleton)?.get(greatest)
    val halves = pattern?.let(::splitIntervalPattern)
        ?: return fillIntervalFallback(intervalFallbackFor(locale), single(start), single(end))

    val shortPattern = Resources.getTimePatterns(locale).short
    val singleTokens = tokenizePattern(if (twentyFourHour) shortPattern else shortPattern.to12HourPattern())
    val amPm = Resources.getAmPmStrings(locale)
    val startText = LocalTime.Format { appendTimeTokens(halves.first.withWidthsOf(singleTokens).withPlainSpaceBeforeDayPeriod(), amPm) }.format(start)
    val endText = LocalTime.Format { appendTimeTokens(halves.second.withWidthsOf(singleTokens).withPlainSpaceBeforeDayPeriod(), amPm) }.format(end)
    return MultiplatformPostFormatter.applyNumberingSystem(startText + endText, locale, numberingSystem)
}

/**
 * The interval pattern for a date style whose single pattern is [single], where [greatest] is the
 * greatest field that differs, or `null` when the locale has none that fits.
 *
 * Neither source of the skeleton is right everywhere. CLDR's own skeleton for the style
 * ([IntervalFormats.dateStyles]) is the only way to know that Japanese "y年M月d日" belongs with
 * the text-month intervals, which write "y年M月d日～d日"; but for some locales it names a numeric
 * skeleton beside a text-month pattern. Reading the skeleton from the letters is right for those,
 * and for a library pattern that is not CLDR's. So CLDR's skeleton is tried first when the
 * library's pattern is CLDR's, then the one read from the letters, and a numeric-month skeleton is
 * never used for a date that names its month. Within a text-month skeleton the interval's own
 * month form is kept: Czech and Korean ranges write the month as a number where CLDR says so.
 */
private fun dateIntervalPattern(single: List<PatternToken>, formats: IntervalFormats, style: DateTimeStyle, greatest: Char): String? {
    val cldrStyle = formats.dateStyles[style.name.lowercase()]
    val cldrSkeleton = cldrStyle?.takeIf { (_, pattern) -> withoutEra(tokenizePattern(pattern)) == single }?.first
    val namesMonth = single.any { it is PatternToken.Field && fieldClass(it.letter) == 'M' && it.count >= 3 }
    return (listOfNotNull(cldrSkeleton) + skeletonFromLetters(single))
        .flatMap(::skeletonCandidates)
        .distinct()
        .filter { skeleton -> !namesMonth || skeleton.count { it == 'M' } >= 3 }
        .firstNotNullOfOrNull { formats.patterns[it]?.get(greatest) }
}

private fun withoutEra(tokens: List<PatternToken>) = tokens.withoutFields { it.letter == 'G' }

/** The skeleton [single] spells: a year, the month at its width, the weekday if any, and the day. */
private fun skeletonFromLetters(single: List<PatternToken>): String {
    val fields = single.filterIsInstance<PatternToken.Field>().associate { fieldClass(it.letter) to it.count }
    val month = fields['M'] ?: 1
    val weekday = fields['E']
    return "y" + (if (month >= 3) "M".repeat(month) else "M") + (weekday?.let { "E".repeat(it) } ?: "") + "d"
}

/**
 * The interval skeletons to try for [skeleton], nearest first. CLDR does not publish every
 * skeleton for every locale: a four-letter month falls back to three letters, a full weekday to
 * the short one, and widths of year, numeric month and day do not matter — the library's own
 * widths are applied afterwards.
 */
private fun skeletonCandidates(skeleton: String): List<String> {
    val month = skeleton.count { it == 'M' || it == 'L' }
    val weekday = skeleton.count { it == 'E' }
    val months = when {
        month >= 4 -> listOf("MMMM", "MMM")
        month == 3 -> listOf("MMM", "MMMM")
        else -> listOf("M")
    }
    val weekdays = when {
        weekday == 0 -> listOf("")
        weekday >= 4 -> listOf("EEEE", "E")
        else -> listOf("E", "EEEE")
    }
    return months.flatMap { m -> weekdays.map { e -> "y${m}${e}d" } }
}

/**
 * A date range at [style]. [single] formats one date at that style, and is used for equal dates
 * and wherever no interval pattern applies.
 */
internal fun formatDateRange(
    start: LocalDate,
    end: LocalDate,
    style: DateTimeStyle,
    locale: Locale,
    numberingSystem: NumberingSystem?,
    single: (LocalDate) -> String,
): String {
    if (style == DateTimeStyle.None) return ""
    val greatest = when {
        start.year != end.year -> 'y'
        start.month != end.month -> 'M'
        start.day != end.day -> 'd'
        else -> return single(start)
    }
    val fallback = { fillIntervalFallback(intervalFallbackFor(locale), single(start), single(end)) }
    // A date before year 1 carries its era, with its year counted from one; such a range is
    // written as two complete dates, each with its own era.
    if (start.year <= 0 || end.year <= 0) return fallback()

    val patterns = Resources.getDatePatterns(locale)
    val singlePattern = when (style) {
        DateTimeStyle.Short -> patterns.short
        DateTimeStyle.Medium -> patterns.medium
        DateTimeStyle.Long -> patterns.long
        else -> patterns.full
    }
    val singleTokens = withoutEra(tokenizePattern(singlePattern))
    val formats = Resources.getIntervalFormats(locale) ?: return fallback()
    // The era is dropped from the pattern (Thai writes one in its full interval) and added once by
    // the post formatter, as it is after a single date.
    val halves = dateIntervalPattern(singleTokens, formats, style, greatest)
        ?.let { splitIntervalTokens(withoutEra(tokenizePattern(it))) }
        ?: return fallback()

    val dayNames = DynamicLocalDateFormats.getDayOfWeekNamesResource(locale)
    val monthNames = DynamicLocalDateFormats.getMonthNamesResource(locale)
    fun render(tokens: List<PatternToken>, date: LocalDate): String {
        val base = if (date.year in 0..9999) date.year - date.year % 100 else 2000
        return LocalDate.Format { appendDateTokens(tokens.withWidthsOf(singleTokens), dayNames, monthNames, base) }.format(date)
    }
    val text = render(halves.first, start) + render(halves.second, end)
    // The post formatter writes the digits in the locale's system.
    return MultiplatformPostFormatter.postFormatDate(
        dateStyle = style, locale = locale, date = start, formattedDate = text, numberingSystem = numberingSystem,
    ) ?: text
}

/**
 * A date-time range. On one day the date is written once and followed by the time range, joined
 * as a single date-time joins its date and time; across days both ends are written in full and
 * joined by the fallback. [startInstant] and [endInstant], when known, are the exact moments
 * shown, so that each end names the zone abbreviation in force at that moment.
 */
internal fun formatDateTimeRange(
    start: LocalDateTime,
    end: LocalDateTime,
    dateStyle: DateTimeStyle,
    timeStyle: DateTimeStyle,
    locale: Locale,
    timeZone: TimeZone,
    twentyFourHour: Boolean,
    numberingSystem: NumberingSystem?,
    startInstant: Instant? = null,
    endInstant: Instant? = null,
): String {
    fun single(value: LocalDateTime, instant: Instant?, date: DateTimeStyle = dateStyle, time: DateTimeStyle = timeStyle) =
        MultiplatformDateFormatter.formatDateTime(
            value, date, time, locale, timeZone, twentyFourHour, eraNames = null, numberingSystem = numberingSystem, instant = instant,
        ) ?: value.toString()

    val sameMoment = if (startInstant != null && endInstant != null) startInstant == endInstant else start == end
    if (sameMoment) return single(start, startInstant)
    if (timeStyle == DateTimeStyle.None) {
        return formatDateRange(start.date, end.date, dateStyle, locale, numberingSystem) { date ->
            single(if (date == start.date) start else end, null)
        }
    }
    if (start.date != end.date) {
        return fillIntervalFallback(intervalFallbackFor(locale), single(start, startInstant), single(end, endInstant))
    }
    // One day: the time range, each end formatted at its own moment for its zone name.
    val times = formatTimeRange(start.time, end.time, timeStyle, locale, twentyFourHour, numberingSystem) { time ->
        if (time == start.time) single(start, startInstant, date = DateTimeStyle.None) else single(end, endInstant, date = DateTimeStyle.None)
    }
    if (dateStyle == DateTimeStyle.None) return times
    return "${single(start, startInstant, time = DateTimeStyle.None)} $times"
}
