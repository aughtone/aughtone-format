package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.DayOfWeek
import kotlin.test.Test
import kotlin.test.assertEquals

class DayOfWeekDisplayNameTest {

    @Test
    fun englishFull() {
        assertEquals("Monday", DayOfWeek.MONDAY.displayName(TextWidth.Full, localeFor("en")!!))
        assertEquals("Sunday", DayOfWeek.SUNDAY.displayName(TextWidth.Full, localeFor("en")!!))
    }

    @Test
    fun englishAbbreviated() {
        assertEquals("Mon", DayOfWeek.MONDAY.displayName(TextWidth.Abbreviated, localeFor("en")!!))
        assertEquals("Sun", DayOfWeek.SUNDAY.displayName(TextWidth.Abbreviated, localeFor("en")!!))
    }

    @Test
    fun fullIsTheDefaultWidth() {
        assertEquals(
            DayOfWeek.MONDAY.displayName(TextWidth.Full, localeFor("en")!!),
            DayOfWeek.MONDAY.displayName(locale = localeFor("en")!!),
        )
    }

    @Test
    fun french() {
        assertEquals("lundi", DayOfWeek.MONDAY.displayName(TextWidth.Full, localeFor("fr")!!))
        assertEquals("lun.", DayOfWeek.MONDAY.displayName(TextWidth.Abbreviated, localeFor("fr")!!))
    }

    @Test
    fun german() {
        assertEquals("Montag", DayOfWeek.MONDAY.displayName(TextWidth.Full, localeFor("de")!!))
        assertEquals("Mo", DayOfWeek.MONDAY.displayName(TextWidth.Abbreviated, localeFor("de")!!))
    }

    @Test
    fun regionFallsBackToLanguage() {
        assertEquals(
            "lundi",
            DayOfWeek.MONDAY.displayName(TextWidth.Full, localeFor("fr-CA")!!),
        )
    }

    @Test
    fun unsupportedLanguageFallsBackToEnglish() {
        // "ga" (Irish) has no bundled table; it must fall back to English, not throw.
        val irish = Locale(languageCode = "ga", displayName = "Irish")
        assertEquals("Monday", DayOfWeek.MONDAY.displayName(TextWidth.Full, irish))
    }
}
