package io.github.aughtone.readable.financial

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import kotlin.test.Test
import kotlin.test.assertEquals

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
        assertEquals("$1,234.56", money.toReadableString(Locales.English))
        
        // Without symbol
        assertEquals("1,234.56", money.toReadableString(Locales.English, showSymbol = false))
    }

    @Test
    fun testFrenchFormatting() {
        val money = Money(123456L, eur) // 1 234,56 €

        // French prefers Suffix, Space
        assertEquals("1 234,56 €", money.toReadableString(Locales.French))
    }

    @Test
    fun testGermanFormatting() {
        val money = Money(123456L, eur) // 1.234,56 €

        // German prefers Suffix, Space
        assertEquals("1.234,56 €", money.toReadableString(Locales.German))
    }

    @Test
    fun testJapaneseZeroDecimalFormatting() {
        val money = Money(1234L, jpy) // ¥1,234 (digits = 0)

        // Japanese prefers Prefix, No Space
        assertEquals("¥1,234", money.toReadableString(Locales.Japanese))
    }

    @Test
    fun testNullCurrencyFallback() {
        val money = Money(123456L, null)

        // Without a currency, it defaults to 2 digits and ignores symbol placement
        assertEquals("1,234.56", money.toReadableString(Locales.English))
        assertEquals("1 234,56", money.toReadableString(Locales.French))
    }
}
