package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals

class MonthDisplayNameTest {

    @Test
    fun englishFull() {
        assertEquals("March", Month.MARCH.displayName(TextWidth.Full, localeFor("en")!!))
        assertEquals("December", Month.DECEMBER.displayName(TextWidth.Full, localeFor("en")!!))
    }

    @Test
    fun englishAbbreviated() {
        assertEquals("Mar", Month.MARCH.displayName(TextWidth.Abbreviated, localeFor("en")!!))
        assertEquals("Dec", Month.DECEMBER.displayName(TextWidth.Abbreviated, localeFor("en")!!))
    }

    @Test
    fun fullIsTheDefaultWidth() {
        assertEquals(
            Month.MARCH.displayName(TextWidth.Full, localeFor("en")!!),
            Month.MARCH.displayName(locale = localeFor("en")!!),
        )
    }

    @Test
    fun french() {
        // French uses the same string for full and abbreviated March.
        assertEquals("Mars", Month.MARCH.displayName(TextWidth.Full, localeFor("fr")!!))
        assertEquals("Mars", Month.MARCH.displayName(TextWidth.Abbreviated, localeFor("fr")!!))
    }

    @Test
    fun regionFallsBackToLanguage() {
        assertEquals("March", Month.MARCH.displayName(TextWidth.Full, localeFor("en-CA")!!))
    }

    @Test
    fun unsupportedLanguageFallsBackToEnglish() {
        // "ga" (Irish) has no bundled table; it must fall back to English, not throw.
        val irish = Locale(languageCode = "ga", displayName = "Irish")
        assertEquals("March", Month.MARCH.displayName(TextWidth.Full, irish))
    }
}
