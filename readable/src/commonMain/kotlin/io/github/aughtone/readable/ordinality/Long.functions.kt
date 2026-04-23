package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale

/**
 * Formats a long number into its ordinal string representation.
 *
 * @param number The number to format.
 * @param locale The locale to use for formatting rules.
 * @return The ordinal string representation of the number.
 */
fun formatOrdinal(number: Long, locale: Locale = currentNativeLocale()): String {
    return ordinalityFor(locale)(number)
}
