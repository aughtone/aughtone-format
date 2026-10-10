package io.github.aughtone.readable.number

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals

class NumbersTest {

    @Test
    fun testAbbreviation() {
        assertEquals("1k", 1000.0.formatReadableAbbreviated(Locales.English))
        assertEquals("1.5k", 1500.0.formatReadableAbbreviated(Locales.English))
        assertEquals("1M", 1000000.0.formatReadableAbbreviated(Locales.English))
        assertEquals("2.5G", 2500000000.0.formatReadableAbbreviated(Locales.English))

        assertEquals("1k", 1000L.formatReadableAbbreviated(Locales.English))
        assertEquals("1.5k", 1500.0f.formatReadableAbbreviated(Locales.English))
        assertEquals("2M", 2000000.formatReadableAbbreviated(Locales.English))
        assertEquals("1.2k", 1234.toShort().formatReadableAbbreviated(Locales.English))
        assertEquals("1k", 1000u.formatReadableAbbreviated(Locales.English))
    }

    @Test
    fun testFormatReadable() {
        assertEquals("1,234.6", 1234.56.formatReadable(Locales.English, precision = 1))
        assertEquals("1,235", 1234.56.formatReadable(Locales.English, precision = 0))
        assertEquals("1,234.56", 1234.56f.formatReadable(Locales.English, precision = 2))
        assertEquals("1,234,567", 1234567L.formatReadable(Locales.English))
        assertEquals("1,234", 1234.formatReadable(Locales.English))
        // 127 is the max value for a signed Byte
        assertEquals("127", 127.toByte().formatReadable(Locales.English))
        // 255 requires UByte as it overflows a signed Byte
        assertEquals("255", 255u.toUByte().formatReadable(Locales.English))
        assertEquals("1,000", 1000u.formatReadable(Locales.English))
    }

    @Test
    fun testLocaleFormatting() {
        // German uses comma as decimal separator
        assertEquals("1,5k", 1500.0.formatReadableAbbreviated(Locales.German))
        
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

    @Test
    fun negativeNumbersGroupDigitsWithoutTheSign() {
        assertEquals("-123", (-123).formatReadable(Locales.English))
        assertEquals("-1,234", (-1234).formatReadable(Locales.English))
        assertEquals("-123,456", (-123456).formatReadable(Locales.English))
        assertEquals("-123.5", (-123.5).formatReadable(Locales.English))
    }

    @Test
    fun largeNumbersKeepEveryDigit() {
        assertEquals("10,000,000", 10_000_000.formatReadable(Locales.English))
        assertEquals("12,345,678", 12345678.0.formatReadable(Locales.English))
        assertEquals("123,456,789,012.3", 123456789012.34.formatReadable(Locales.English))
        assertEquals("1,000,000,000,000,000,000,000", 1e21.formatReadable(Locales.English))
    }

    @Test
    fun smallFractionsAreWrittenOutNotInExponentForm() {
        assertEquals("0.0005", 0.0005.formatReadable(Locales.English, precision = 4))
        assertEquals("0.00012", 0.00012.formatReadable(Locales.English, precision = 5))
        assertEquals("0", 0.0005.formatReadable(Locales.English, precision = 1))
        assertEquals("0.001", 0.0005.formatReadable(Locales.English, precision = 3))
    }

    @Test
    fun aSmallNegativeThatRoundsToZeroHasNoSign() {
        assertEquals("0", (-0.04).formatReadable(Locales.English, precision = 1))
        assertEquals("0", (-0.0).formatReadable(Locales.English))
    }

    @Test
    fun negativeHalvesRoundAwayFromZeroLikePositiveOnes() {
        assertEquals("2.5", 2.45.formatReadable(Locales.English, precision = 1))
        assertEquals("-2.5", (-2.45).formatReadable(Locales.English, precision = 1))
        assertEquals("-3", (-2.5).formatReadable(Locales.English, precision = 0))
    }

    @Test
    fun roundingCarriesIntoANewLeadingDigit() {
        assertEquals("1,000", 999.96.formatReadable(Locales.English, precision = 1))
        assertEquals("10", 9.99.formatReadable(Locales.English, precision = 1))
    }

    @Test
    fun valuesThatAreNotFiniteDoNotThrow() {
        assertEquals("NaN", Double.NaN.formatReadable(Locales.English))
        assertEquals("∞", Double.POSITIVE_INFINITY.formatReadable(Locales.English))
        assertEquals("-∞", Double.NEGATIVE_INFINITY.formatReadable(Locales.English))
        assertEquals("NaN", Float.NaN.formatReadable(Locales.English))
    }

    @Test
    fun wholeNumberTypesAreExactAtTheirLimits() {
        assertEquals("9,223,372,036,854,775,807", Long.MAX_VALUE.formatReadable(Locales.English))
        assertEquals("-9,223,372,036,854,775,808", Long.MIN_VALUE.formatReadable(Locales.English))
        assertEquals("18,446,744,073,709,551,615", ULong.MAX_VALUE.formatReadable(Locales.English))
        assertEquals("9.007.199.254.740.993", 9_007_199_254_740_993L.formatReadable(Locales.German))
    }

    @Test
    fun abbreviationMovesUpAPrefixWhenRoundingReachesAThousand() {
        assertEquals("1M", 999_999.formatReadableAbbreviated(Locales.English))
        assertEquals("1k", 999.96.formatReadableAbbreviated(Locales.English))
        assertEquals("999.9k", 999_940.formatReadableAbbreviated(Locales.English))
        assertEquals("1G", 999_950_000.formatReadableAbbreviated(Locales.English))
    }

    @Test
    fun abbreviationHandlesNegativeAndNonFiniteValues() {
        assertEquals("-1.5k", (-1500).formatReadableAbbreviated(Locales.English))
        assertEquals("-500", (-500).formatReadableAbbreviated(Locales.English))
        assertEquals("NaN", Double.NaN.formatReadableAbbreviated(Locales.English))
        assertEquals("∞", Double.POSITIVE_INFINITY.formatReadableAbbreviated(Locales.English))
    }

    @Test
    fun languagesThatGroupWithASpaceUseACommaDecimal() {
        for (locale in listOf(
            Locales.Afrikaans, Locales.Belarusian, Locales.Bulgarian, Locales.Estonian, Locales.Hungarian,
            Locales.Georgian, Locales.Kazakh, Locales.Lithuanian, Locales.Latvian, Locales.Albanian,
            Locales.Ukrainian, Locales.Uzbek, Locales.Portuguese,
        )) {
            assertEquals("1 234 567,89", 1234567.891.formatReadable(locale, 2), "separators for ${locale.languageCode}")
        }
    }

    @Test
    fun indonesianAndVietnameseUseADotForGroupingAndACommaDecimal() {
        assertEquals("1.234.567,89", 1234567.891.formatReadable(Locales.Indonesian, 2))
        assertEquals("1.234.567,89", 1234567.891.formatReadable(Locales.Vietnamese, 2))
    }

    @Test
    fun brazilianPortugueseKeepsDotGrouping() {
        val brazilian = io.github.aughtone.types.locale.Locale(languageCode = "pt", regionCode = "BR", displayName = "Portuguese (Brazil)")
        assertEquals("1.234.567,89", 1234567.891.formatReadable(brazilian, 2))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        assertEquals("1,234.6", 1234.56.toReadable(Locales.English, precision = 1))
        assertEquals("1.5k", 1500.0.toReadableAbbreviated(Locales.English))
    }
}
