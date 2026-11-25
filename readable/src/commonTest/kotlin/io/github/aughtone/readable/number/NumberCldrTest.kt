package io.github.aughtone.readable.number

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.readable.localeOfTag
import io.github.aughtone.readable.Locales
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Number formatting in every bundled language, and every region that writes it differently,
 * checked against what Unicode CLDR writes for the same number, as a number and as a percentage.
 * The fixture is generated with the data by `tools/cldr/number-symbols.mjs`.
 */
class NumberCldrTest {

    private fun localeOf(tag: String) = localeOfTag(tag)

    @Test
    fun everyLocaleMatchesCldrForEachSample() {
        assertTrue(numberCldrFixture.size > 100)
        for ((tag, cases) in numberCldrFixture) {
            val locale = localeOf(tag)
            for (line in cases.trim().lines()) {
                val (kind, value, precision, expected) = line.split('|')
                val actual = when (kind) {
                    "d" -> value.toDouble().formatReadable(locale, precision.toInt())
                    "l" -> value.toLong().formatReadable(locale, precision.toInt())
                    "p" -> value.toDouble().formatReadablePercent(locale, precision.toInt())
                    "q" -> value.toLong().formatReadablePercent(locale, precision.toInt(), outOfHundred = true)
                    "c" -> value.toDouble().formatReadableAbbreviated(locale, precision.toInt())
                    "k" -> value.toLong().formatReadableAbbreviated(locale, precision.toInt())
                    else -> error("unknown kind $kind")
                }
                assertEquals(expected, actual, "$tag: $value at precision $precision")
            }
        }
    }

    @Test
    fun frenchInFranceAndInCanadaUseDifferentNoBreakSpaces() {
        assertEquals("1\u202F234\u202F567", 1234567.formatReadable(Locales.French))
        assertEquals("1\u00A0234\u00A0567", 1234567.formatReadable(localeOf("fr-CA")))
    }

    @Test
    fun swedishWritesTheTrueMinusSign() {
        assertEquals("\u22121\u00A0234,5", (-1234.5).formatReadable(Locales.Swedish))
        assertEquals("\u2212∞", Double.NEGATIVE_INFINITY.formatReadable(Locales.Swedish))
    }

    @Test
    fun asciiSeparatorsReplaceNoBreakSpacesAndTheMinusSign() {
        assertEquals("1 234 567,89", 1234567.891.formatReadable(Locales.French, 2, Separators.Ascii))
        assertEquals("-1 234,5", (-1234.5).formatReadable(Locales.Swedish, separators = Separators.Ascii))
        assertEquals("-1 234 567", (-1234567L).formatReadable(localeOf("fr-CA"), separators = Separators.Ascii))
        assertEquals("1,5 Mio.", 1500000.formatReadableAbbreviated(Locales.German, separators = Separators.Ascii))
        assertEquals("1.5k", 1500.formatReadableAbbreviated(Locales.English, abbreviation = Abbreviation.Si, separators = Separators.Ascii))
        assertEquals("1,234.5", 1234.5.formatReadable(Locales.English, separators = Separators.Ascii))
        assertEquals("1 000", numberFormatterFor(Locales.French, 0, Separators.Ascii)(1000.0))
    }

    @Test
    fun aLanguageCldrHasNoNumberDataForReadsAsEnglish() {
        assertEquals("-1,234.5", (-1234.5).formatReadable(Locales.Inuktitut))
    }
}
