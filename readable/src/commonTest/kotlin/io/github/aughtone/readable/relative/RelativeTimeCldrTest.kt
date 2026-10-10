package io.github.aughtone.readable.relative

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Relative phrases in the languages whose grammar needs a whole pattern for each unit, direction
 * and plural form, checked against what Unicode CLDR says for the same count. The generator's
 * few adjustments to CLDR are applied to the fixture too.
 */
class RelativeTimeCldrTest {

    private fun durationOf(unit: String, count: Int): Duration = when (unit) {
        "second" -> count.seconds
        "minute" -> count.minutes
        "hour" -> count.hours
        "day" -> count.days
        "week" -> (count * 7).days
        "month" -> (count * 30).days
        "year" -> (count * 365).days
        else -> error("unknown unit $unit")
    }

    @Test
    fun everyPatternLanguageMatchesCldrForEachUnitDirectionAndCount() {
        assertTrue(relativeTimeCldrFixture.isNotEmpty())
        for ((language, cases) in relativeTimeCldrFixture) {
            val formatter = relativeTimeConfigFor(Locale(languageCode = language, displayName = language)).formatter
            for (line in cases.trim().lines()) {
                val (unit, count, past, future) = line.split('|')
                val duration = durationOf(unit, count.toInt())
                assertEquals(past, formatter(-duration, true), "$language: $count $unit ago")
                assertEquals(future, formatter(duration, true), "$language: in $count $unit")
            }
        }
    }

    @Test
    fun theUnitTakesADifferentCaseAfterAgoThanAfterIn() {
        val czech = relativeTimeConfigFor(Locales.Czech).formatter
        assertEquals("před 5 minutami", czech((-5).minutes, true))
        assertEquals("za 5 minut", czech(5.minutes, true))
        assertEquals("před 1 hodinou", czech((-1).hours, true))
        assertEquals("za 2 hodiny", czech(2.hours, true))

        val finnish = relativeTimeConfigFor(Locales.Finnish).formatter
        assertEquals("20 minuuttia sitten", finnish((-20).minutes, true))
        assertEquals("20 minuutin päästä", finnish(20.minutes, true))
    }

    @Test
    fun aRegionVariantUsesItsLanguagesPatterns() {
        val slovak = Locale(languageCode = "sk", regionCode = "SK", displayName = "Slovak")
        assertEquals("pred 2 hodinami", relativeTimeConfigFor(slovak).formatter((-2).hours, true))
    }

    @Test
    fun arabicNamesOneAndTwoWithoutADigit() {
        val arabic = relativeTimeConfigFor(Locales.Arabic).formatter
        assertEquals("قبل ساعة واحدة", arabic((-1).hours, true))
        assertEquals("قبل ساعتين", arabic((-2).hours, true))
        assertEquals("قبل 5 ساعات", arabic((-5).hours, true))
    }

    @Test
    fun swahiliWritesTheUnitBeforeTheCount() {
        val swahili = relativeTimeConfigFor(Locales.Swahili).formatter
        assertEquals("saa 2 zilizopita", swahili((-2).hours, true))
        assertEquals("baada ya saa 2", swahili(2.hours, true))
        // CLDR capitalises this one unit in the past; the library writes it like the others.
        assertEquals("sekunde 5 zilizopita", swahili((-5).seconds, true))
        assertEquals("sekunde 1 iliyopita", swahili((-1).seconds, true))
    }

    @Test
    fun aPatternLanguageKeepsItsDayLabelsAndItsOtherStrings() {
        val czech = relativeTimeConfigFor(Locales.Czech)
        assertEquals(listOf("Dnes", "Zítra", "Včera"), listOf(czech.todayString, czech.tomorrowString, czech.yesterdayString))
        assertTrue(czech.nowString.isNotBlank())
    }

    @Test
    fun hoursKeepCountingWhenDateUnitsAreNotAllowed() {
        assertEquals("za 30 hodin", relativeTimeConfigFor(Locales.Czech).formatter(30.hours, false))
    }
}
