package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale

/**
 * Extension function to format a [Long] into its ordinal string representation.
 *
 * @param locale The locale to use for formatting rules.
 * @return The ordinal string representation of the number.
 */
fun Long.toReadableOrdinal(locale: Locale = currentNativeLocale()): String {
    return formatOrdinal(this, locale)
}
