package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.localeFor
import kotlin.test.Test
import kotlin.test.assertEquals

class OrdinalityTest {

    @Test
    fun testEnglishOrdinals() {
        val locale = localeFor("en")!!

        assertEquals("1st", 1.toReadableOrdinal(locale))
        assertEquals("2nd", 2.toReadableOrdinal(locale))
        assertEquals("3rd", 3.toReadableOrdinal(locale))
        assertEquals("4th", 4.toReadableOrdinal(locale))
        assertEquals("11th", 11.toReadableOrdinal(locale))
        assertEquals("12th", 12.toReadableOrdinal(locale))
        assertEquals("13th", 13.toReadableOrdinal(locale))
        assertEquals("21st", 21.toReadableOrdinal(locale))
        assertEquals("101st", 101.toReadableOrdinal(locale))
    }

    @Test
    fun testFrenchOrdinals() {
        val locale = localeFor("fr")!!
        assertEquals("1er", 1.toReadableOrdinal(locale))
        assertEquals("2e", 2.toReadableOrdinal(locale))
        assertEquals("10e", 10.toReadableOrdinal(locale))
    }

    @Test
    fun testSpanishOrdinals() {
        val locale = localeFor("es")!!
        assertEquals("1.º", 1.toReadableOrdinal(locale))
        assertEquals("2.º", 2.toReadableOrdinal(locale))
    }

    @Test
    fun testGermanOrdinals() {
        val locale = localeFor("de")!!
        assertEquals("1.", 1.toReadableOrdinal(locale))
        assertEquals("2.", 2.toReadableOrdinal(locale))
    }

    @Test
    fun testJapaneseOrdinals() {
        val locale = localeFor("ja")!!
        assertEquals("第1", 1.toReadableOrdinal(locale))
        assertEquals("第2", 2.toReadableOrdinal(locale))
    }
}
