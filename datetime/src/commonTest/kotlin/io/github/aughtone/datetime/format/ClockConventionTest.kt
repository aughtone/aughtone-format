package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClockConventionTest {

    private fun locale(language: String, region: String? = null) =
        Locale(languageCode = language, regionCode = region, displayName = listOfNotNull(language, region).joinToString("-"))

    private fun is24Hour(language: String, region: String? = null) = Resources.getClockHours(locale(language, region)).is24hour

    @Test
    fun aLanguageWithoutARegionUsesItsMostCommonClock() {
        for (language in listOf("en", "ar", "el", "hi", "ko", "ms", "sq")) assertFalse(is24Hour(language), "$language should be 12-hour")
        for (language in listOf("de", "fr", "es", "ru", "ja", "zh", "sv", "pt", "it", "pl")) assertTrue(is24Hour(language), "$language should be 24-hour")
    }

    @Test
    fun aRegionCanDifferFromItsLanguage() {
        assertTrue(is24Hour("en", "GB"))
        assertTrue(is24Hour("en", "IE"))
        assertTrue(is24Hour("en", "ZA"))
        assertFalse(is24Hour("en", "US"))
        assertFalse(is24Hour("en", "CA"))
        assertFalse(is24Hour("en", "AU"))
        assertFalse(is24Hour("es", "MX"))
        assertFalse(is24Hour("es", "US"))
        assertTrue(is24Hour("es", "ES"))
        assertTrue(is24Hour("fr", "CA"))
        assertTrue(is24Hour("ar", "MA"))
        assertFalse(is24Hour("ar", "EG"))
        assertFalse(is24Hour("zh", "TW"))
        assertTrue(is24Hour("zh", "CN"))
    }

    @Test
    fun aKnownLanguageIsNotOverriddenByTheRegionItIsUsedIn() {
        assertTrue(is24Hour("de", "US"))
        assertTrue(is24Hour("fr", "US"))
    }

    @Test
    fun anUnknownLanguageFollowsItsRegionAndOtherwiseIsTwentyFourHour() {
        assertFalse(is24Hour("xx", "US"))
        assertTrue(is24Hour("xx", "DE"))
        assertTrue(is24Hour("xx"))
    }

    @Test
    fun theDayPeriodComesFirstInTheLanguagesThatWriteItFirst() {
        val time = LocalTime(16, 8)
        fun short(language: String) = time.format(DateTimeStyle.Short, locale = locale(language), timeZone = TimeZone.UTC, is24HourFormat = false)

        for (language in listOf("ko", "hu", "tr")) {
            val text = short(language)
            assertTrue(text.endsWith(" 4:08"), "$language: $text")
            assertFalse(text.first().isDigit(), "$language: $text")
        }
        for (language in listOf("ja", "zh")) {
            val text = short(language)
            assertTrue(text.endsWith("4:08") && !text.contains(' '), "$language: $text")
            assertFalse(text.first().isDigit(), "$language: $text")
        }
        assertEquals("4:08 PM", time.format(DateTimeStyle.Short, locale = locale("en", "US"), timeZone = TimeZone.UTC, is24HourFormat = false))
    }
}
