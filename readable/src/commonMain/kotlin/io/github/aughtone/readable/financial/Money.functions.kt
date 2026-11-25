package io.github.aughtone.readable.financial

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale

/**
 * Formats a [Money] object into a localized human-readable string.
 * This handles localized decimal/grouping separators, decimal precision based on the currency,
 * and locale-specific symbol placement (e.g. prefix vs suffix, spacing). The separators, the minus
 * sign, and where the symbol and sign go are the ones Unicode CLDR gives the locale. Every space
 * inside an amount is a no-break space — between digit groups, and between the number and a
 * spaced symbol — so an amount never breaks across a line; pass [Separators.Ascii] for ordinary
 * spaces. A symbol that ends in a letter is spaced from the digits even where the locale writes
 * `"$"` against them (`"CHF 12.00"` in English), as CLDR does.
 *
 * The symbol is CLDR's standard symbol for the currency in [locale], which marks a dollar that is
 * not the locale's own: `"CA$5.00"` in US English, `"US$5.00"` in Canadian English, `"5,00 $ US"` in
 * Canadian French. Pass [CurrencySymbol.Narrow] for the bare `"$"` where the context already says
 * which currency it is, or [CurrencySymbol.Code] for `"CAD 5.00"`. A currency CLDR has no symbol
 * for is written with its own `Currency.symbol`; the `symbol` of a currency CLDR does name is not
 * used.
 *
 * The amount always shows the currency's full minor-unit digits — two for US dollars, none for
 * yen — so twelve dollars is `"$12.00"`, not `"$12"`. An amount finer than the minor unit is
 * rounded half to even. The digits are exact however large the amount is.
 *
 * A negative amount takes the locale's minus sign where CLDR puts it: before the symbol in English
 * (`"-$123.00"`), on the number after a symbol in Dutch (`"€ -123,00"`) and Swiss German
 * (`"CHF-123.00"`), and before the number when the symbol follows (`"-123,00 €"`).
 *
 * ```kotlin
 * Money(123456L, usd).formatReadable(Locales.English)  // "$1,234.56"
 * Money(1200L, usd).formatReadable(Locales.English)    // "$12.00"
 * Money(123456L, eur).formatReadable(Locales.French)   // "1 234,56 €" (U+202F, then U+00A0)
 * Money(-12300L, usd).formatReadable(Locales.English)  // "-$123.00"
 * Money(-12300L, eur).formatReadable(Locales.Dutch)    // "€ -123,00"
 * Money(500L, cad).formatReadable(Locales.English)     // "CA$5.00"
 * Money(500L, cad).formatReadable(Locales.English, symbol = CurrencySymbol.Narrow)  // "$5.00"
 * ```
 *
 * @param locale The locale to use for formatting. Defaults to [Locale.current].
 * @param showSymbol Whether to include the currency symbol in the output. Defaults to true.
 * @param symbol Which symbol to write: CLDR's standard symbol (the default), its narrow symbol, or
 *   the ISO code. See [CurrencySymbol]. Ignored when [showSymbol] is false.
 * @param separators Whether spaces and signs are the typographic ones CLDR uses (the default), or
 *   plain ASCII. See [Separators].
 * @return A formatted monetary string.
 */
fun Money.formatReadable(
    locale: Locale = Locale.current,
    showSymbol: Boolean = true,
    symbol: CurrencySymbol = CurrencySymbol.Standard,
    separators: Separators = Separators.Typographic,
): String {
    val formatter = moneyFormatterFor(locale, showSymbol, symbol, separators)
    return formatter(this)
}

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Money.formatReadable(locale: Locale = Locale.current, showSymbol: Boolean = true): String =
    formatReadable(locale, showSymbol, CurrencySymbol.Standard, Separators.Typographic)

/**
 * Formats a [Money] object into a localized human-readable string.
 *
 * @param locale The locale to use for formatting. Defaults to [Locale.current].
 * @param showSymbol Whether to include the currency symbol in the output. Defaults to true.
 * @return A formatted monetary string.
 */
@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, showSymbol)")
)
fun Money.toReadableString(
    locale: Locale = Locale.current,
    showSymbol: Boolean = true
): String = formatReadable(locale, showSymbol)
