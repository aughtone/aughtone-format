package io.github.aughtone.readable.financial

import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale

/**
 * Formats a [Money] object into a localized human-readable string.
 * This handles localized decimal/grouping separators, decimal precision based on the currency,
 * and locale-specific symbol placement (e.g. prefix vs suffix, spacing).
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
