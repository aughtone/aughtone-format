package io.github.aughtone.readable.financial

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.number.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MoneyTest {

    private val usd = Currency(
        code = "USD",
        number = 840,
        digits = 2,
        name = "US Dollar",
        symbol = "$",
    )

    private val eur = Currency(
        code = "EUR",
        number = 978,
        digits = 2,
        name = "Euro",
        symbol = "€",
    )

    private val jpy = Currency(
        code = "JPY",
        number = 392,
        digits = 0,
        name = "Japanese Yen",
        symbol = "¥",
    )

    @Test
    fun testEnglishFormatting() {
        val money = Money(123456L, usd) // $1,234.56

        // English prefers Prefix, No Space
        assertEquals("$1,234.56", money.formatReadable(Locales.English))
        
        // Without symbol
        assertEquals("1,234.56", money.formatReadable(Locales.English, showSymbol = false))
    }

    @Test
    fun testFrenchFormatting() {
        val money = Money(123456L, eur) // 1 234,56 €

        // French prefers Suffix, Space
        assertEquals("1 234,56 €", money.formatReadable(Locales.French))
    }

    @Test
    fun testGermanFormatting() {
        val money = Money(123456L, eur) // 1.234,56 €

        // German prefers Suffix, Space
        assertEquals("1.234,56 €", money.formatReadable(Locales.German))
    }

    @Test
    fun testJapaneseZeroDecimalFormatting() {
        val money = Money(1234L, jpy) // ¥1,234 (digits = 0)

        // Japanese prefers Prefix, No Space
        assertEquals("¥1,234", money.formatReadable(Locales.Japanese))
    }



    @Test
    fun testPersianSuffixPlacement() {
        // fa previously had no rule and fell to the prefix default; it should now place the
        // currency symbol as a suffix with a space.
        val result = Money(123456L, usd).formatReadable(Locales.Persian)
        assertTrue(result.endsWith(" \$"), "expected suffix symbol with space, got: $result")
    }

    @Test
    fun amountsKeepTheCurrencysMinorUnitDigits() {
        assertEquals("$12.50", Money(1250L, usd).formatReadable(Locales.English))
        assertEquals("$12.00", Money(1200L, usd).formatReadable(Locales.English))
        assertEquals("$0.05", Money(5L, usd).formatReadable(Locales.English))
        assertEquals("12,00 €", Money(1200L, eur).formatReadable(Locales.French))
        assertEquals("12.00", Money(1200L, usd).formatReadable(Locales.English, showSymbol = false))
    }

    @Test
    fun largeAmountsKeepEveryDigit() {
        assertEquals("$10,000,000.00", Money(1_000_000_000L, usd).formatReadable(Locales.English))
        assertEquals(
            "$123,456,789,012,345,678,901,234.56",
            Money(BigDecimal("123456789012345678901234.56"), usd).formatReadable(Locales.English)
        )
        assertEquals("¥10,000,000", Money(10_000_000L, jpy).formatReadable(Locales.Japanese))
    }

    @Test
    fun negativeAmountsPutTheSignBeforeAPrefixedSymbol() {
        assertEquals("-$123.00", Money(-12300L, usd).formatReadable(Locales.English))
        assertEquals("-$1,234.56", Money(-123456L, usd).formatReadable(Locales.English))
        assertEquals("-¥1,234", Money(-1234L, jpy).formatReadable(Locales.Japanese))
        assertEquals("-123.00", Money(-12300L, usd).formatReadable(Locales.English, showSymbol = false))
    }

    @Test
    fun negativeAmountsKeepTheSignOnTheNumberWhenTheSymbolIsSpacedOrTrailing() {
        assertEquals("-1 234,56 €", Money(-123456L, eur).formatReadable(Locales.French))
        assertEquals("€ -1.234,56", Money(-123456L, eur).formatReadable(Locales.Dutch))
    }

    @Test
    fun amountsFinerThanTheMinorUnitRoundHalfToEven() {
        assertEquals("$0.12", Money(BigDecimal("0.125"), usd).formatReadable(Locales.English))
        assertEquals("$0.14", Money(BigDecimal("0.135"), usd).formatReadable(Locales.English))
    }

    @Test
    fun symbolPlacementFollowsTheLanguage() {
        val money = Money(123456L, usd)
        assertEquals("$1 234,56", money.formatReadable(Locales.Afrikaans))
        assertEquals("1,234.56 $", money.formatReadable(Locales.Hebrew))
        assertEquals("$ 1,234.56", money.formatReadable(Locales.Swahili))
        assertEquals("$ 1'234.56", money.formatReadable(Locales.SwissGerman))
        assertEquals("$ 1'234.56", money.formatReadable(Locales.SwissItalian))
        assertEquals("1.234,56 $", money.formatReadable(Locales.German))
        assertEquals("1.234,56 $", money.formatReadable(Locales.Italian))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        val money = Money(123456L, usd)
        assertEquals("$1,234.56", money.toReadableString(Locales.English))
    }
}
