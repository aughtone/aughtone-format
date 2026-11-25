package io.github.aughtone.readable.financial

import io.github.aughtone.readable.localeOfTag
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
 * is generated with the data by `tools/cldr/number-symbols.mjs`. Each case is checked with the
 * currency's standard symbol, its narrow symbol and its code, so the symbol the library picks is
 * compared as well.
 */
class MoneyCldrTest {

    private fun localeOf(tag: String) = localeOfTag(tag)

    private fun currency(code: String, digits: Int, symbol: String) =
        Currency(code = code, number = 0, digits = digits, name = code, symbol = symbol)

    @Test
    fun everyLocaleMatchesCldrForEachSample() {
        assertTrue(moneyCldrFixture.size > 100)
        for ((tag, cases) in moneyCldrFixture) {
            val locale = localeOf(tag)
            for (line in cases.trim().lines()) {
                val (code, digits, amount, standard, narrow) = line.split('|')
                val byCode = line.substringAfterLast('|')
                // A symbol of the currency's own, which CLDR's must replace.
                val money = Money(BigDecimal(amount), currency(code, digits.toInt(), "?"))
                assertEquals(standard, money.formatReadable(locale), "$tag: $amount $code")
                assertEquals(narrow, money.formatReadable(locale, symbol = CurrencySymbol.Narrow), "$tag: $amount $code narrow")
                assertEquals(byCode, money.formatReadable(locale, symbol = CurrencySymbol.Code), "$tag: $amount $code by code")
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
    fun theStandardSymbolTellsADollarFromTheLocalesOwn() {
        val cad = currency("CAD", 2, "$")
        val usd = currency("USD", 2, "$")
        val five = BigDecimal("5")
        assertEquals("CA$5.00", Money(five, cad).formatReadable(Locales.English))
        assertEquals("$5.00", Money(five, usd).formatReadable(Locales.English))
        assertEquals("$5.00", Money(five, cad).formatReadable(localeOf("en-CA")))
        assertEquals("US$5.00", Money(five, usd).formatReadable(localeOf("en-CA")))
        assertEquals("5,00\u00A0$\u00A0US", Money(five, usd).formatReadable(localeOf("fr-CA")))
        assertEquals("5,00\u00A0\$CA", Money(five, cad).formatReadable(Locales.French))
    }

    @Test
    fun theNarrowSymbolIsTheBareSign() {
        val five = BigDecimal("5")
        assertEquals("$5.00", Money(five, currency("CAD", 2, "CA$")).formatReadable(Locales.English, symbol = CurrencySymbol.Narrow))
        assertEquals("5,00\u00A0$", Money(five, currency("USD", 2, "$")).formatReadable(localeOf("fr-CA"), symbol = CurrencySymbol.Narrow))
        assertEquals("$5.00", moneyFormatterFor(localeOf("en-CA"), symbol = CurrencySymbol.Narrow)(Money(five, currency("USD", 2, "$"))))
    }

    @Test
    fun theCodeIsPlacedAndSpacedAsASymbol() {
        val five = BigDecimal("5")
        assertEquals("CAD\u00A05.00", Money(five, currency("CAD", 2, "$")).formatReadable(Locales.English, symbol = CurrencySymbol.Code))
        assertEquals("5,00\u00A0CAD", Money(five, currency("CAD", 2, "$")).formatReadable(Locales.French, symbol = CurrencySymbol.Code))
    }

    @Test
    fun aCurrencyCldrHasNoSymbolForShowsItsOwn() {
        val five = BigDecimal("5")
        // A fund code ICU does not know, and a code made up for the test.
        assertEquals("BOV\u00A05.00", Money(five, currency("BOV", 2, "BOV")).formatReadable(Locales.English))
        assertEquals("¤5.00", Money(five, currency("QQQ", 2, "¤")).formatReadable(Locales.English))
        assertEquals("¤5.00", Money(five, currency("QQQ", 2, "¤")).formatReadable(Locales.English, symbol = CurrencySymbol.Narrow))
        assertEquals("5,00\u00A0¤", Money(five, currency("QQQ", 2, "¤")).formatReadable(localeOf("fr-CA")))
        assertEquals("QQQ\u00A05.00", Money(five, currency("QQQ", 2, "¤")).formatReadable(Locales.English, symbol = CurrencySymbol.Code))
    }

    @Test
    fun theSymbolIsLeftOutWhenNotShown() {
        val five = BigDecimal("5")
        assertEquals("5.00", Money(five, currency("CAD", 2, "$")).formatReadable(Locales.English, showSymbol = false, symbol = CurrencySymbol.Code))
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
