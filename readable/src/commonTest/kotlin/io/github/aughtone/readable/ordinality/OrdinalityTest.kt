package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.localeFor
import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals

class OrdinalityTest {

    @Test
    fun testEnglishOrdinals() {
        val locale = localeFor("en")!!

        assertEquals("1st", 1.formatReadableOrdinal(locale))
        assertEquals("2nd", 2.formatReadableOrdinal(locale))
        assertEquals("3rd", 3.formatReadableOrdinal(locale))
        assertEquals("4th", 4.formatReadableOrdinal(locale))
        assertEquals("11th", 11.formatReadableOrdinal(locale))
        assertEquals("12th", 12.formatReadableOrdinal(locale))
        assertEquals("13th", 13.formatReadableOrdinal(locale))
        assertEquals("21st", 21.formatReadableOrdinal(locale))
        assertEquals("101st", 101.formatReadableOrdinal(locale))
    }

    @Test
    fun testFrenchOrdinals() {
        val locale = localeFor("fr")!!
        assertEquals("1er", 1.formatReadableOrdinal(locale))
        assertEquals("2e", 2.formatReadableOrdinal(locale))
        assertEquals("10e", 10.formatReadableOrdinal(locale))
    }

    @Test
    fun testSpanishOrdinals() {
        val locale = localeFor("es")!!
        assertEquals("1.º", 1.formatReadableOrdinal(locale))
        assertEquals("2.º", 2.formatReadableOrdinal(locale))
    }

    @Test
    fun testGermanOrdinals() {
        val locale = localeFor("de")!!
        assertEquals("1.", 1.formatReadableOrdinal(locale))
        assertEquals("2.", 2.formatReadableOrdinal(locale))
    }

    @Test
    fun testJapaneseOrdinals() {
        val locale = localeFor("ja")!!
        assertEquals("第1", 1.formatReadableOrdinal(locale))
        assertEquals("第2", 2.formatReadableOrdinal(locale))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        val locale = localeFor("en")!!
        assertEquals("1st", 1.toReadableOrdinal(locale))
    }

    @Test
    fun negativeNumbersTakeTheSuffixOfTheirMagnitude() {
        assertEquals("-1st", (-1).formatReadableOrdinal(Locales.English))
        assertEquals("-2nd", (-2).formatReadableOrdinal(Locales.English))
        assertEquals("-11th", (-11).formatReadableOrdinal(Locales.English))
        assertEquals("-23rd", (-23).formatReadableOrdinal(Locales.English))
    }

    @Test
    fun georgianSuffixesAreWrittenInGeorgianScript() {
        assertEquals("1-ლი", 1.formatReadableOrdinal(Locales.Georgian))
        assertEquals("21-ე", 21.formatReadableOrdinal(Locales.Georgian))
    }

    @Test
    fun unsignedValuesBeyondTheSignedRangeKeepTheirDigits() {
        assertEquals("18446744073709551615th", ULong.MAX_VALUE.formatReadableOrdinal(Locales.English))
        assertEquals("9223372036854775811th", 9223372036854775811uL.formatReadableOrdinal(Locales.English))
        assertEquals("9223372036854775821st", 9223372036854775821uL.formatReadableOrdinal(Locales.English))
        assertEquals("9223372036854775901e", 9223372036854775901uL.formatReadableOrdinal(Locales.French))
        assertEquals("21st", 21uL.formatReadableOrdinal(Locales.English))
    }
}
