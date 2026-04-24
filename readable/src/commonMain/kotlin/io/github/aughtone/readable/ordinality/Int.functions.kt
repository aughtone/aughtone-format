package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

/**
 * Formats a number into its ordinal string representation (e.g., 1 to "1st").
 *
 * @param number The number to format.
 * @param locale The locale to use for formatting rules.
 * @return The ordinal string representation of the number.
 */
fun formatOrdinal(number: Int, locale: Locale = Locale.current): String {
    return ordinalityFor(locale)(number.toLong())
}
