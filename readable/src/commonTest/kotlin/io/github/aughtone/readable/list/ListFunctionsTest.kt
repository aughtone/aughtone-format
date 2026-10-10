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

    @Test
    fun elementsContainingPlaceholderTextAreEmittedUnchanged() {
        assertEquals("a{1} and b", listOf("a{1}", "b").formatReadableList(Locales.English))
        assertEquals("{0}, {1}, and {0}", listOf("{0}", "{1}", "{0}").formatReadableList(Locales.English))
        assertEquals("x or {1}", listOf("x", "{1}").formatReadableList(Locales.English, ListType.Or))
    }

    @Test
    fun englishOutsideNorthAmericaHasNoCommaBeforeTheConjunction() {
        val items = listOf("A", "B", "C")
        for (region in listOf("GB", "AU", "NZ", "IE", "IN", "ZA", "CA")) {
            val english = Locale(languageCode = "en", regionCode = region, displayName = "en-$region")
            assertEquals("A, B and C", items.formatReadableList(english), "en-$region")
            assertEquals("A, B or C", items.formatReadableList(english, ListType.Or), "en-$region")
        }
        assertEquals("A, B, and C", items.formatReadableList(Locales.English))
        assertEquals("A, B, and C", items.formatReadableList(Locale(languageCode = "en", displayName = "en")))
    }

    @Test
    fun hebrewJoinsItsConjunctionToAForeignWordWithAHyphen() {
        assertEquals("A ו-B", listOf("A", "B").formatReadableList(Locales.Hebrew))
        assertEquals("A, B ו-C", listOf("A", "B", "C").formatReadableList(Locales.Hebrew))
        assertEquals("אבא ואמא", listOf("אבא", "אמא").formatReadableList(Locales.Hebrew))
    }
}
