package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Ranges checked against CLDR, through the generated [intervalCldrFixture].
 *
 * A case is compared only where the library's two single values already read as CLDR's do: the
 * library keeps some names and widths of its own, and a range is meant to read like the library's
 * single values, not like CLDR's. Where the singles agree, the range must agree too. The
 * comparison ignores letter case, the kind of space, and leading zeros.
 */
class IntervalCldrTest {

    private class Case(val locale: String, val kind: String, val start: String, val end: String,
                       val singleStart: String, val singleEnd: String, val range: String)

    // CLDR has no Inuktitut data of its own; ICU and the CLDR JSON fill the gap from different
    // parents, so the two sources do not agree for it and it is left out.
    private val cases = intervalCldrFixture.trim().lines().map { line ->
        val f = line.split('|')
        Case(f[0], f[1], f[2], f[3], f[4], f[5], f[6])
    }.filter { it.locale != "iu" }

    /**
     * Cases where ICU, which produced the fixture, chooses a different CLDR pattern than the raw
     * CLDR interval data the library bundles. Each is checked against the raw data.
     *
     * - Thai 24-hour: the CLDR `Hm` interval is "HH:mm น. – HH:mm น."; ICU writes "HH:mm–HH:mm".
     */
    private val knownDifferences = setOf("th time24")

    /**
     * Date cases where ICU, which produced the fixture, does not write the CLDR interval pattern
     * for the style: it reads the skeleton from the pattern's letters, so a Japanese or Chinese
     * "y年M月d日" date gets the numeric interval ("2024/03/03～2024/03/05"), although CLDR files
     * the style under its text-month skeleton, whose interval is "y年M月d日～d日". The library uses
     * CLDR's skeleton; these are checked against the raw data in the curated tests instead.
     */
    private fun isKnownDateDifference(case: Case) =
        case.locale == "ja" && case.kind == "long" || case.locale == "zh" && case.kind in setOf("medium", "long")

    private fun localeOf(key: String): Locale = when {
        key == "CA" -> Locale(languageCode = "en", regionCode = "CA", displayName = key)
        key == "US" -> Locale(languageCode = "en", regionCode = "US", displayName = key)
        '-' in key -> Locale(languageCode = key.substringBefore('-'), regionCode = key.substringAfter('-'), displayName = key)
        else -> Locale(languageCode = key, displayName = key)
    }

    /** Lower case, one kind of space, and no leading zeros, so only real differences remain. */
    private fun normalised(text: String): String {
        val spaced = text.lowercase().map { if (it == ' ' || it == ' ' || it == ' ') ' ' else it }.joinToString("")
        val out = StringBuilder()
        spaced.forEachIndexed { index, char ->
            val leadingZero = char == '0' && spaced.getOrNull(index + 1)?.isDigit() == true && spaced.getOrNull(index - 1)?.isDigit() != true
            if (!leadingZero) out.append(char)
        }
        return out.toString().replace(Regex(" +"), " ").trim()
    }

    @Test
    fun timeRangesMatchCldrWhereverTheSingleTimesDo() {
        var compared = 0
        val comparedLocales = mutableSetOf<String>()
        val mismatches = mutableListOf<String>()
        for (case in cases.filter { it.kind.startsWith("time") && "${it.locale} ${it.kind}" !in knownDifferences }) {
            val locale = localeOf(case.locale)
            val twentyFourHour = case.kind == "time24"
            fun single(time: LocalTime) = time.format(DateTimeStyle.Short, locale = locale, timeZone = TimeZone.UTC,
                is24HourFormat = twentyFourHour, numberingSystem = NumberingSystem.LATN)
            val start = LocalTime.parse(case.start)
            val end = LocalTime.parse(case.end)
            if (normalised(single(start)) != normalised(case.singleStart) || normalised(single(end)) != normalised(case.singleEnd)) continue
            val range = start.formatRange(end, DateTimeStyle.Short, locale = locale, timeZone = TimeZone.UTC,
                is24HourFormat = twentyFourHour, numberingSystem = NumberingSystem.LATN)
            if (normalised(case.range) != normalised(range)) mismatches += "${case.locale} ${case.kind} ${case.start}–${case.end}: CLDR \"${case.range}\", library \"$range\""
            compared++
            comparedLocales += case.locale
        }
        assertTrue(mismatches.isEmpty(), mismatches.joinToString("\n"))
        // Guard against a fixture or a normalisation that quietly compares nothing.
        assertTrue(comparedLocales.size >= 40, "only ${comparedLocales.size} locales had comparable time ranges ($compared cases)")
        val required = setOf("de", "fr", "ja", "ko")
        assertTrue(comparedLocales.containsAll(required), "not compared: ${required - comparedLocales}")
    }

    @Test
    fun dateRangesMatchCldrWhereverTheSingleDatesDo() {
        val styles = mapOf("short" to DateTimeStyle.Short, "medium" to DateTimeStyle.Medium, "long" to DateTimeStyle.Long)
        var compared = 0
        val comparedLocales = mutableSetOf<String>()
        val mismatches = mutableListOf<String>()
        for (case in cases.filter { it.kind in styles && !isKnownDateDifference(it) }) {
            val locale = localeOf(case.locale)
            val style = styles.getValue(case.kind)
            fun single(date: LocalDate) = date.format(style, locale = locale, timeZone = TimeZone.UTC, numberingSystem = NumberingSystem.LATN)
            val start = LocalDate.parse(case.start)
            val end = LocalDate.parse(case.end)
            if (normalised(single(start)) != normalised(case.singleStart) || normalised(single(end)) != normalised(case.singleEnd)) continue
            val range = start.formatRange(end, style, locale = locale, numberingSystem = NumberingSystem.LATN)
            if (normalised(case.range) != normalised(range)) mismatches += "${case.locale} ${case.kind} ${case.start}–${case.end}: CLDR \"${case.range}\", library \"$range\""
            compared++
            comparedLocales += case.locale
        }
        assertTrue(mismatches.isEmpty(), "${mismatches.size} of $compared:\n" + mismatches.joinToString("\n"))
        assertTrue(comparedLocales.size >= 30, "only ${comparedLocales.size} locales had comparable date ranges ($compared cases)")
        val required = setOf("en-GB", "de", "ru", "hu", "es", "ko", "cs")
        assertTrue(comparedLocales.containsAll(required), "not compared: ${required - comparedLocales}")
    }
}
