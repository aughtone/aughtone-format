package io.github.aughtone.readable.number

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals

class NumbersTest {

    @Test
    fun testAbbreviation() {
        assertEquals("1k", 1000.0.toReadableAbbreviated(Locales.English))
        assertEquals("1.5k", 1500.0.toReadableAbbreviated(Locales.English))
        assertEquals("1M", 1000000.0.toReadableAbbreviated(Locales.English))
        assertEquals("2.5G", 2500000000.0.toReadableAbbreviated(Locales.English))

        assertEquals("1k", 1000L.toReadableAbbreviated(Locales.English))
        assertEquals("1.5k", 1500.0f.toReadableAbbreviated(Locales.English))
        assertEquals("2M", 2000000.toReadableAbbreviated(Locales.English))
        assertEquals("1.2k", 1234.toShort().toReadableAbbreviated(Locales.English))
        assertEquals("1k", 1000u.toReadableAbbreviated(Locales.English))
    }

    @Test
    fun testToReadable() {
        assertEquals("1,234.6", 1234.56.toReadable(Locales.English, precision = 1))
        assertEquals("1,235", 1234.56.toReadable(Locales.English, precision = 0))
        assertEquals("1,234.56", 1234.56f.toReadable(Locales.English, precision = 2))
        assertEquals("1,234,567", 1234567L.toReadable(Locales.English))
        assertEquals("1,234", 1234.toReadable(Locales.English))
        // 127 is the max value for a signed Byte
        assertEquals("127", 127.toByte().toReadable(Locales.English))
        // 255 requires UByte as it overflows a signed Byte
        assertEquals("255", 255u.toUByte().toReadable(Locales.English))
        assertEquals("1,000", 1000u.toReadable(Locales.English))
    }

    @Test
    fun testLocaleFormatting() {
        // German uses comma as decimal separator
        assertEquals("1,5k", 1500.0.toReadableAbbreviated(Locales.German))
        
        // French uses space as grouping separator
        val frenchFormatter = numberFormatterFor(Locales.French, 0)
        assertEquals("1 000", frenchFormatter(1000.0))
    }

    @Test
    fun testPrecision() {
        val p2 = numberFormatterFor(Locales.English, 2)
        assertEquals("1.23", p2(1.2345))

        val p3 = numberFormatterFor(Locales.English, 3)
        assertEquals("1.235", p3(1.2345))
    }

    @Test
    fun testRegionalSeparators() {
        // en-ZA: comma decimal, space grouping
        val zaFormatter = numberFormatterFor(Locales.SouthAfricanEnglish, 2)
        assertEquals("1 234,56", zaFormatter(1234.56))

        // fr-CH: comma decimal, apostrophe grouping
        val frChFormatter = numberFormatterFor(Locales.SwissFrench, 2)
        assertEquals("1'234,56", frChFormatter(1234.56))

        // de-CH: period decimal, apostrophe grouping
        val deChFormatter = numberFormatterFor(Locales.SwissGerman, 2)
        assertEquals("1'234.56", deChFormatter(1234.56))

        // it-CH: same as de-CH (Swiss convention)
        val itChFormatter = numberFormatterFor(Locales.SwissItalian, 2)
        assertEquals("1'234.56", itChFormatter(1234.56))

        // fr-CA: no specific entry, falls back to fr (comma decimal, space grouping)
        val frCaFormatter = numberFormatterFor(
            io.github.aughtone.types.locale.Locale(languageCode = "fr", regionCode = "CA", displayName = "French (Canada)"),
            2
        )
        assertEquals("1 234,56", frCaFormatter(1234.56))
    }
}
