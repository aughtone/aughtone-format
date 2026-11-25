package io.github.aughtone.readable.number

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.readable.Locales
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * What [formatReadablePercent] promises beyond matching CLDR, which [NumberCldrTest] checks for
 * every bundled language.
 */
class PercentTest {

    private fun language(code: String) = Locale(languageCode = code, regionCode = null, displayName = code)

    @Test
    fun theCldrFixtureChecksTheLayoutsThatDiffer() {
        for (tag in listOf("en", "fr", "de", "sv", "tr", "pl")) {
            val cases = numberCldrFixture.getValue(tag).trim().lines()
            assertTrue(cases.any { it.startsWith("p|0.25|") }, "$tag has no positive percentage")
            assertTrue(cases.any { it.startsWith("p|-0.25|") }, "$tag has no negative percentage")
        }
    }

    @Test
    fun theSignGoesWhereTheLanguagePutsIt() {
        assertEquals("25%", 0.25.formatReadablePercent(Locales.English))
        assertEquals("25 %", 0.25.formatReadablePercent(Locales.French))
        assertEquals("25 %", 0.25.formatReadablePercent(Locales.German))
        assertEquals("−25 %", (-0.25).formatReadablePercent(Locales.Swedish))
        assertEquals("%25", 0.25.formatReadablePercent(Locales.Turkish))
        assertEquals("-%25", (-0.25).formatReadablePercent(Locales.Turkish))
    }

    @Test
    fun aNumberIsAFractionUnlessItIsOutOfHundred() {
        assertEquals("56%", 0.56.formatReadablePercent(Locales.English))
        assertEquals("0.56%", 0.56.formatReadablePercent(Locales.English, precision = 2, outOfHundred = true))
        assertEquals("1%", 0.56.formatReadablePercent(Locales.English, outOfHundred = true))
        assertEquals("56%", 56.formatReadablePercent(Locales.English, outOfHundred = true))
        assertEquals("5,600%", 56.formatReadablePercent(Locales.English))
        assertEquals("100%", 1.formatReadablePercent(Locales.English))
    }

    @Test
    fun precisionIsTheMostPlacesThePercentageShows() {
        assertEquals("26%", 0.256.formatReadablePercent(Locales.English))
        assertEquals("12.3%", 0.1234.formatReadablePercent(Locales.English, precision = 1))
        assertEquals("12.346%", 0.123456.formatReadablePercent(Locales.English, precision = 3))
        assertEquals("25%", 0.25.formatReadablePercent(Locales.English, precision = 2))
        assertEquals("130%", 1.26.formatReadablePercent(Locales.English, precision = -1))
    }

    @Test
    fun multiplyingByAHundredLosesNoDigit() {
        assertEquals("7%", 0.07.formatReadablePercent(Locales.English, precision = 15))
        assertEquals("0.00001%", 1e-7.formatReadablePercent(Locales.English, precision = 5))
        assertEquals("100,000,000,000,000,000,000,000%", 1e21.formatReadablePercent(Locales.English))
        assertEquals("922,337,203,685,477,580,700%", Long.MAX_VALUE.formatReadablePercent(Locales.English))
        assertEquals("1,844,674,407,370,955,161,500%", ULong.MAX_VALUE.formatReadablePercent(Locales.English))
    }

    @Test
    fun everyNumberTypeFollowsTheSameRule() {
        val en = Locales.English
        assertEquals("25%", 0.25f.formatReadablePercent(en))
        assertEquals("200%", 2L.formatReadablePercent(en))
        assertEquals("200%", 2.toShort().formatReadablePercent(en))
        assertEquals("-200%", (-2).toByte().formatReadablePercent(en))
        assertEquals("200%", 2uL.formatReadablePercent(en))
        assertEquals("200%", 2u.formatReadablePercent(en))
        assertEquals("200%", 2u.toUShort().formatReadablePercent(en))
        assertEquals("25%", 25u.toUByte().formatReadablePercent(en, outOfHundred = true))
    }

    @Test
    fun aValueThatRoundsToZeroHasNoSign() {
        assertEquals("0%", (-0.001).formatReadablePercent(Locales.English))
        assertEquals("%0", (-0.0).formatReadablePercent(Locales.Turkish))
    }

    @Test
    fun notANumberAndTheInfinitiesTakeTheSameLayout() {
        assertEquals("NaN%", Double.NaN.formatReadablePercent(Locales.English))
        assertEquals("NaN %", Float.NaN.formatReadablePercent(Locales.French))
        assertEquals("%∞", Double.POSITIVE_INFINITY.formatReadablePercent(Locales.Turkish))
        assertEquals("−∞ %", Double.NEGATIVE_INFINITY.formatReadablePercent(Locales.Swedish, outOfHundred = true))
    }

    @Test
    fun percentagesGroupInThreesWhereNumbersGroupInLakhs() {
        for (code in listOf("ml", "mr", "te")) {
            assertEquals("1,234,567%", 1234567.formatReadablePercent(language(code), outOfHundred = true), code)
        }
    }

    @Test
    fun asciiSeparatorsApply() {
        assertEquals("-1 234,5 %", (-12.345).formatReadablePercent(Locales.Swedish, 1, separators = Separators.Ascii))
        assertEquals("1 234 %", 1234.formatReadablePercent(Locales.French, outOfHundred = true, separators = Separators.Ascii))
        assertEquals("-% 25", (-0.25).formatReadablePercent(language("eu"), separators = Separators.Ascii))
    }
}
