package io.github.aughtone.datetime.format

import com.ibm.icu.text.DateFormat
import com.ibm.icu.text.DateIntervalFormat
import com.ibm.icu.util.DateInterval
import com.ibm.icu.util.TimeZone as IcuTimeZone
import com.ibm.icu.util.ULocale
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.formats.localeDatePatterns
import io.github.aughtone.toolbox.InternalAughtoneFormatApi
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.util.Date
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Ranges of chosen fields checked against ICU4J's `DateIntervalFormat`, given each skeleton
 * directly, as Android's `DateIntervalFormat`, Apple's `DateIntervalFormatter` and JVM applications
 * give it. ICU4J is a test dependency only; its CLDR version matches the library's data.
 *
 * As in [IntervalCldrTest], a case is compared only where the library's two single values already
 * read as ICU's do, and the comparison ignores letter case, the kind of space and leading zeros.
 */
@OptIn(InternalAughtoneFormatApi::class)
class FieldRangeCldrTest {

    // The ready-made ReadableFields sets, other date sets, times on each clock, and dates with times.
    private val skeletons = listOf(
        "MMMd", "MMMMd", "Md", "MMMEd", "MEd", "MMMMEEEEd", "yMMM", "yMMMM", "yM", "yMMMd", "yMMMEd", "yMd", "y", "MMM", "d", "E", "Ed",
        "hm", "Hm", "h", "H",
        "MMMdhm", "MMMdHm", "yMMMdhm", "Ehm", "EHm",
    )

    // Pairs of ends, each differing first at a different field: AM/PM, the hour, the minute, the
    // day, the month, the year, and only the seconds, which no skeleton here shows.
    private val pairs = listOf(
        "2024-06-01T09:00:00" to "2024-06-01T17:00:00",
        "2024-06-01T09:00:00" to "2024-06-01T11:30:00",
        "2024-06-01T09:00:00" to "2024-06-01T09:30:00",
        "2024-06-01T09:00:00" to "2024-06-03T17:00:00",
        "2024-06-01T09:00:00" to "2024-09-30T17:00:00",
        "2024-06-01T09:00:00" to "2025-06-01T09:00:00",
        "2024-06-01T09:00:00" to "2024-06-01T09:00:05",
    ).map { (a, b) -> LocalDateTime.parse(a) to LocalDateTime.parse(b) }

    // CLDR has no Inuktitut data of its own; ICU and the bundled data fill the gap from different
    // parents, so it is left out.
    private val keys = localeDatePatterns.keys.filter { it != "iu" }

    /**
     * Cases where the library and ICU knowingly differ, by locale, skeleton and the start and end.
     * Each group is one decision; a new difference fails the test, and one listed here that stops
     * differing does too, so the list cannot go stale.
     */
    private val knownDifferences: Map<String, String> = buildMap {
        // ICU writes the weekday of a one-day range of times in its stand-alone form ("sáb",
        // "lau", "сб"); the library keeps the form its single value of the same fields uses.
        for (key in listOf("es", "nn")) for (skeleton in listOf("Ehm", "EHm")) for (end in listOf("17:00", "11:30", "09:30")) {
            put("$key $skeleton 2024-06-01T09:00–2024-06-01T$end", "stand-alone weekday")
        }
        for (end in listOf("17:00", "11:30", "09:30")) put("kk EHm 2024-06-01T09:00–2024-06-01T$end", "stand-alone weekday")
        // ICU writes the Finnish full weekday in its format form inside a range ("lauantaina");
        // the library keeps the form its single value uses ("lauantai").
        for (end in listOf("2024-06-03T17:00", "2024-09-30T17:00", "2025-06-01T09:00")) {
            put("fi MMMMEEEEd 2024-06-01T09:00–$end", "format weekday")
        }
        // Across years ICU shortens the Russian weekday in its fallback ("сб"); the library keeps
        // the requested full weekday.
        put("ru MMMMEEEEd 2024-06-01T09:00–2025-06-01T09:00", "fallback weekday width")
        // ICU widens Australian English months a year apart to full names; the library keeps the
        // requested abbreviation.
        put("en-AU MMM 2024-06-01T09:00–2025-06-01T09:00", "fallback month width")
    }

    private fun tagOf(key: String): String = when (key) {
        "CA" -> "en-CA"
        "US" -> "en-US"
        else -> key
    }

    private fun localeOf(key: String): Locale = when {
        key == "CA" -> Locale(languageCode = "en", regionCode = "CA", displayName = key)
        key == "US" -> Locale(languageCode = "en", regionCode = "US", displayName = key)
        '-' in key -> Locale(languageCode = key.substringBefore('-'), regionCode = key.substringAfter('-'), displayName = key)
        else -> Locale(languageCode = key, displayName = key)
    }

    private fun normalised(text: String): String {
        val spaced = text.lowercase()
            .filter { it != '‎' && it != '‏' && it != '؜' }
            .map { if (it == ' ' || it == ' ' || it == ' ') ' ' else it }
            .joinToString("")
        val out = StringBuilder()
        spaced.forEachIndexed { index, char ->
            val leadingZero = char == '0' && spaced.getOrNull(index + 1)?.isDigit() == true && spaced.getOrNull(index - 1)?.isDigit() != true
            if (!leadingZero) out.append(char)
        }
        return out.toString().replace(Regex(" +"), " ").trim()
    }

    private fun usesDate(skeleton: String) = skeleton.any { it in "yMdE" }
    private fun usesTime(skeleton: String) = skeleton.any { it in "hHm" }

    @Test
    fun rangesOfChosenFieldsMatchIcuWhereverTheSingleValuesDo() {
        val utc = IcuTimeZone.getTimeZone("UTC")
        var cases = 0
        var compared = 0
        val mismatches = mutableListOf<String>()
        for (key in keys) {
            // The Gregorian calendar, which Persian and Thai do not default to, and Latin digits.
            val icuLocale = ULocale("${tagOf(key)}@calendar=gregorian;numbers=latn")
            val locale = localeOf(key)
            for (skeleton in skeletons) {
                val icuSingle = DateFormat.getInstanceForSkeleton(skeleton, icuLocale).apply { timeZone = utc }
                val icuRange = DateIntervalFormat.getInstance(skeleton, icuLocale).apply { timeZone = utc }
                val date = usesDate(skeleton)
                val time = usesTime(skeleton)
                for ((start, end) in pairs) {
                    cases++
                    val s = start.toInstant(TimeZone.UTC).toEpochMilliseconds()
                    val e = end.toInstant(TimeZone.UTC).toEpochMilliseconds()
                    fun single(at: LocalDateTime) = formatFieldsSkeleton(
                        skeleton, locale, date = if (date) at.date else null, time = if (time) at.time else null,
                        numberingSystem = NumberingSystem.LATN,
                    )
                    if (normalised(single(start)) != normalised(icuSingle.format(Date(s))) ||
                        normalised(single(end)) != normalised(icuSingle.format(Date(e)))
                    ) continue
                    compared++
                    val expected = icuRange.format(DateInterval(s, e))
                    val range = formatFieldsRangeSkeleton(
                        skeleton, locale,
                        // Both ends' dates are given, as a LocalDateTime range gives them, so a
                        // range of times across days shows its dates as ICU's does.
                        startDate = start.date, startTime = if (time) start.time else null,
                        endDate = end.date, endTime = if (time) end.time else null,
                        numberingSystem = NumberingSystem.LATN,
                    )
                    val id = "$key $skeleton $start–$end"
                    val differs = normalised(expected) != normalised(range)
                    if (differs != (id in knownDifferences)) {
                        mismatches += if (differs) "$id: ICU \"$expected\", library \"$range\"" else "$id: listed as differing, now matches"
                    }
                }
            }
        }
        assertTrue(compared > cases / 2, "only $compared of $cases cases compared")
        assertTrue(mismatches.isEmpty(), "${mismatches.size} of $compared differ:\n" + mismatches.take(80).joinToString("\n"))
    }
}
