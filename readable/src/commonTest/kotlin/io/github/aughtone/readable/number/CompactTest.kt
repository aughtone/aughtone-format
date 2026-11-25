package io.github.aughtone.readable.number

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.localeOfTag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/** Compact numbers follow the locale; ICU agreement for every locale is in [NumberCldrTest]. */
class CompactTest {

    @Test
    fun englishUsesCldrsLetters() {
        assertEquals("1.5K", 1500.formatReadableAbbreviated(Locales.English))
        assertEquals("2.5M", 2_500_000.formatReadableAbbreviated(Locales.English))
        assertEquals("1.2B", 1_200_000_000L.formatReadableAbbreviated(Locales.English))
        assertEquals("1T", 1e12.formatReadableAbbreviated(Locales.English))
        assertEquals("999", 999.formatReadableAbbreviated(Locales.English))
    }

    @Test
    fun aLocaleThatWritesAMagnitudeWholeWritesItWhole() {
        assertEquals("1500", 1500.formatReadableAbbreviated(Locales.German))
        assertEquals("15.000", 15_000.formatReadableAbbreviated(Locales.German))
        assertEquals("1234", 1234.formatReadableAbbreviated(Locales.Japanese))
    }

    @Test
    fun theWordIsSpacedWithANoBreakSpace() {
        assertEquals("1,5 Mio.", 1_500_000.formatReadableAbbreviated(Locales.German))
        assertEquals("1,5 mil", 1500.formatReadableAbbreviated(Locales.Spanish))
        assertEquals("1,5 k", 1500.formatReadableAbbreviated(Locales.French))
        for (locale in listOf(Locales.German, Locales.Spanish, Locales.French, Locales.Russian, Locales.Hindi, Locales.Arabic)) {
            val text = 25_000_000.formatReadableAbbreviated(locale)
            assertFalse(' ' in text, "${locale.languageTag}: \"$text\"")
        }
    }

    @Test
    fun asciiSeparatorsUseAnOrdinarySpace() {
        assertEquals("1,5 Mio.", 1_500_000.formatReadableAbbreviated(Locales.German, separators = Separators.Ascii))
        assertEquals("-2,5 Mio.", (-2_500_000).formatReadableAbbreviated(Locales.German, separators = Separators.Ascii))
    }

    @Test
    fun eastAsianLocalesCountInTenThousands() {
        assertEquals("1.5万", 15_000.formatReadableAbbreviated(Locales.Japanese))
        assertEquals("150万", 1_500_000.formatReadableAbbreviated(Locales.Japanese))
        assertEquals("12億", 1_200_000_000.formatReadableAbbreviated(Locales.Japanese))
        assertEquals("150万", 1_500_000.formatReadableAbbreviated(Locales.Chinese))
        assertEquals("12亿", 1_200_000_000.formatReadableAbbreviated(Locales.Chinese))
    }

    @Test
    fun hindiCountsInLakhsAndCrores() {
        assertEquals("15 लाख", 1_500_000.formatReadableAbbreviated(Locales.Hindi))
        assertEquals("2.5 क॰", 25_000_000.formatReadableAbbreviated(Locales.Hindi))
        assertEquals("15L", 1_500_000.formatReadableAbbreviated(localeOfTag("en-IN")))
    }

    @Test
    fun arabicThousandsTakeTheirPluralForm() {
        assertEquals("1 ألف", 1000.formatReadableAbbreviated(Locales.Arabic, 0))
        assertEquals("3 آلاف", 3000.formatReadableAbbreviated(Locales.Arabic, 0))
        assertEquals("11 ألف", 11_000.formatReadableAbbreviated(Locales.Arabic, 0))
        assertEquals("3.5 ألف", 3500.formatReadableAbbreviated(Locales.Arabic))
    }

    @Test
    fun roundingCarriesToTheNextRung() {
        assertEquals("1M", 999_999.formatReadableAbbreviated(Locales.English))
        assertEquals("1K", 999.96.formatReadableAbbreviated(Locales.English))
        assertEquals("999.9K", 999_940.formatReadableAbbreviated(Locales.English))
        assertEquals("1B", 999_950_000.formatReadableAbbreviated(Locales.English))
        assertEquals("1 Mrd.", 999_999_999.formatReadableAbbreviated(Locales.German))
        assertEquals("100万", 999_999.formatReadableAbbreviated(Locales.Japanese))
    }

    @Test
    fun aLocaleWithNoRungToCarryToWritesTheNumberWhole() {
        assertEquals("999.999", 999_999.formatReadableAbbreviated(Locales.German))
    }

    @Test
    fun beyondTheLastPatternTheLargestWordIsKept() {
        assertEquals("1000T", 1e15.formatReadableAbbreviated(Locales.English))
        assertTrue(Long.MAX_VALUE.formatReadableAbbreviated(Locales.English).endsWith("T"))
    }

    @Test
    fun wholeNumberTypesAreExact() {
        assertEquals("9.223.372 Bio.", Long.MAX_VALUE.formatReadableAbbreviated(Locales.German, 0))
        assertEquals("18.446.744 Bio.", ULong.MAX_VALUE.formatReadableAbbreviated(Locales.German, 0))
    }

    @Test
    fun siIsKeptAsAnOption() {
        assertEquals("1.5k", 1500.formatReadableAbbreviated(Locales.English, abbreviation = Abbreviation.Si))
        assertEquals("1,5k", 1500.formatReadableAbbreviated(Locales.German, abbreviation = Abbreviation.Si))
        assertEquals("1.2G", 1_200_000_000.formatReadableAbbreviated(Locales.English, abbreviation = Abbreviation.Si))
    }

    @Test
    fun negativesAndSpecialValues() {
        assertEquals("-1.5K", (-1500).formatReadableAbbreviated(Locales.English))
        assertEquals("NaN", Double.NaN.formatReadableAbbreviated(Locales.English))
        assertEquals("∞", Double.POSITIVE_INFINITY.formatReadableAbbreviated(Locales.English))
        assertEquals("0", 0.0.formatReadableAbbreviated(Locales.English))
    }
}
