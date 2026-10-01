package io.github.aughtone.readable.list

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals

class ListFunctionsTest {

    private val en = Locales.English
    private val fr = Locales.French

    @Test
    fun emptyIsBlank() {
        assertEquals("", emptyList<String>().formatReadableList(en))
    }

    @Test
    fun singleIsTheElement() {
        assertEquals("Monday", listOf("Monday").formatReadableList(en))
    }

    @Test
    fun englishTwoUsesAnd() {
        assertEquals("Monday and Friday", listOf("Monday", "Friday").formatReadableList(en))
    }

    @Test
    fun englishThreeIsOxfordComma() {
        assertEquals(
            "Monday, Wednesday, and Friday",
            listOf("Monday", "Wednesday", "Friday").formatReadableList(en),
        )
    }

    @Test
    fun englishOr() {
        assertEquals(
            "red, green, or blue",
            listOf("red", "green", "blue").formatReadableList(en, ListType.Or),
        )
        assertEquals("red or green", listOf("red", "green").formatReadableList(en, ListType.Or))
    }

    @Test
    fun englishUnitIsCommaOnly() {
        assertEquals(
            "5 h, 30 min, 10 s",
            listOf("5 h", "30 min", "10 s").formatReadableList(en, ListType.Unit),
        )
    }

    @Test
    fun frenchThreeHasNoCommaBeforeEt() {
        assertEquals(
            "lundi, mercredi et vendredi",
            listOf("lundi", "mercredi", "vendredi").formatReadableList(fr),
        )
        assertEquals("lundi et vendredi", listOf("lundi", "vendredi").formatReadableList(fr))
    }

    @Test
    fun germanAndSpanishConjunctions() {
        assertEquals(
            "a, b und c",
            listOf("a", "b", "c").formatReadableList(Locales.German),
        )
        assertEquals(
            "a, b y c",
            listOf("a", "b", "c").formatReadableList(Locales.Spanish),
        )
    }

    @Test
    fun frenchUnitUsesConjunction() {
        // CLDR gives French a real Unit pattern that uses "et" (like German), not a plain comma.
        assertEquals(
            "a, b et c",
            listOf("a", "b", "c").formatReadableList(fr, ListType.Unit),
        )
    }

    @Test
    fun unsupportedLocaleFallsBackToEnglish() {
        val irish = Locale(languageCode = "ga", displayName = "Irish")
        assertEquals(
            "Monday, Wednesday, and Friday",
            listOf("Monday", "Wednesday", "Friday").formatReadableList(irish),
        )
    }
}
