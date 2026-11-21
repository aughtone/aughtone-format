package io.github.aughtone.readable.financial

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.number.Separators
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.number.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Where an amount's symbol and sign go, and the spaces around them, in every bundled language and
 * every region that does it differently, checked against what Unicode CLDR writes. The fixture
 * is generated with the data by `tools/cldr/number-symbols.mjs`. Each case formats with the symbol
 * ICU showed, so only placement, spacing and the number are compared.
 */
class MoneyCldrTest {

    private fun localeOf(tag: String) = Locale(
        languageCode = tag.substringBefore('-'),
        regionCode = tag.substringAfter('-', "").ifEmpty { null },
        displayName = tag,
    )

    private fun currency(code: String, digits: Int, symbol: String) =
        Currency(code = code, number = 0, digits = digits, name = code, symbol = symbol)

    @Test
    fun everyLocaleMatchesCldrForEachSample() {
        assertTrue(moneyCldrFixture.size > 100)
        for ((tag, cases) in moneyCldrFixture) {
            val locale = localeOf(tag)
            for (line in cases.trim().lines()) {
                val (code, digits, amount, symbol, expected) = line.split('|')
                val money = Money(BigDecimal(amount), currency(code, digits.toInt(), symbol))
                assertEquals(expected, money.formatReadable(locale), "$tag: $amount $code")
            }
        }
    }

    @Test
    fun placementFollowsTheLocaleNotJustTheLanguage() {
        val eur = currency("EUR", 2, "€")
        val chf = currency("CHF", 2, "CHF")
        assertEquals("€\u00A0-1.234,50", Money(BigDecimal("-1234.5"), eur).formatReadable(Locales.Dutch))
        assertEquals("CHF-1'234.50", Money(BigDecimal("-1234.5"), chf).formatReadable(Locales.SwissGerman))
    }

    @Test
    fun aSymbolEndingInALetterIsSpacedFromTheDigits() {
        assertEquals("CHF\u00A05.00", Money(BigDecimal("5"), currency("CHF", 2, "CHF")).formatReadable(Locales.English))
        assertEquals("CA$5.00", Money(BigDecimal("5"), currency("CAD", 2, "CA$")).formatReadable(Locales.English))
        assertEquals("-R\u00A01\u00A0234,50", Money(BigDecimal("-1234.5"), currency("ZAR", 2, "R")).formatReadable(Locales.SouthAfricanEnglish))
    }

    @Test
    fun anAmountThatRoundsToZeroTakesThePositiveLayout() {
        assertEquals("€\u00A00,00", Money(BigDecimal("-0.001"), currency("EUR", 2, "€")).formatReadable(Locales.Dutch))
    }

    @Test
    fun asciiSeparatorsReplaceEveryNoBreakSpaceAndTheMinusSign() {
        val eur = currency("EUR", 2, "€")
        assertEquals("1 234,50 €", Money(BigDecimal("1234.5"), eur).formatReadable(Locales.French, separators = Separators.Ascii))
        assertEquals("-1 234,50 kr", Money(BigDecimal("-1234.5"), currency("SEK", 2, "kr")).formatReadable(Locales.Swedish, separators = Separators.Ascii))
        assertEquals("€ -1.234,50", moneyFormatterFor(Locales.Dutch, separators = Separators.Ascii)(Money(BigDecimal("-1234.5"), eur)))
    }
}
