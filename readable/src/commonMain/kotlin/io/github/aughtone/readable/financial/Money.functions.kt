package io.github.aughtone.readable.financial

import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale

/**
 * Formats a [Money] object into a localized human-readable string.
 * This handles localized decimal/grouping separators, decimal precision based on the currency,
 * and locale-specific symbol placement (e.g. prefix vs suffix, spacing).
 *
 * The amount always shows the currency's full minor-unit digits — two for US dollars, none for
 * yen — so twelve dollars is `"$12.00"`, not `"$12"`. An amount finer than the minor unit is
 * rounded half to even. The digits are exact however large the amount is.
 *
 * A negative amount carries a leading minus sign: before a symbol that is attached to the number
 * (`"-$123.00"`), otherwise on the number itself (`"-123,00 €"`, `"€ -123,00"`).
 *
 * ```kotlin
 * Money(123456L, usd).formatReadable(Locales.English)  // "$1,234.56"
 * Money(1200L, usd).formatReadable(Locales.English)    // "$12.00"
 * Money(123456L, eur).formatReadable(Locales.French)   // "1 234,56 €"
 * Money(-12300L, usd).formatReadable(Locales.English)  // "-$123.00"
 * ```
 *
 * @param locale The locale to use for formatting. Defaults to [Locale.current].
 * @param showSymbol Whether to include the currency symbol in the output. Defaults to true.
 * @return A formatted monetary string.
 */
fun Money.formatReadable(
    locale: Locale = Locale.current,
    showSymbol: Boolean = true
): String {
    val formatter = moneyFormatterFor(locale, showSymbol)
    return formatter(this)
}

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
