package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

/**
 * Formats a long number into its ordinal string representation.
 *
 * @param number The number to format.
 * @param locale The locale to use for formatting rules.
 * @return The ordinal string representation of the number.
 */
fun formatOrdinal(number: Long, locale: Locale = Locale.current): String {
    return ordinalityFor(locale)(number)
}

/**
 * Formats the given [number] into its ordinal string representation (e.g., 1 becomes "1st").
 *
 * @param number The integer to format.
 * @param locale The locale determining the linguistic rules for ordinality. Defaults to [Locale.current].
 * @return The ordinal string representation of the number.
 */
fun formatOrdinal(number: Int, locale: Locale = Locale.current): String {
    return ordinalityFor(locale)(number.toLong())
}
