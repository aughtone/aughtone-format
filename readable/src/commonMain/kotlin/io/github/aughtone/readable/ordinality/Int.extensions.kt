package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

/**
 * Extension function to format an [Int] into its ordinal string representation.
 *
 * @param locale The locale to use for formatting rules.
 * @return The ordinal string representation of the number.
 */
fun Int.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatOrdinal(this, locale)
}
