package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

/**
 * Formats a long number into its localized ordinal string representation.
 *
 * Example:
 * ```kotlin
 * formatOrdinal(1L, Locale("en")) // "1st"
 * formatOrdinal(2L, Locale("fr")) // "2e"
 * ```
 *
 * @param number The number to format.
 * @param locale The locale to use for formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of the number.
 */
fun formatOrdinal(number: Long, locale: Locale = Locale.current): String {
    return ordinalityFor(locale)(number)
}

/**
 * Formats the given [number] into its localized ordinal string representation.
 *
 * Example:
 * ```kotlin
 * formatOrdinal(1, Locale("en")) // "1st"
 * formatOrdinal(3, Locale("en")) // "3rd"
 * ```
 *
 * @param number The integer to format.
 * @param locale The locale determining the linguistic rules for ordinality (defaults to [Locale.current]).
 * @return The ordinal string representation of the number.
 */
fun formatOrdinal(number: Int, locale: Locale = Locale.current): String {
    return ordinalityFor(locale)(number.toLong())
}
