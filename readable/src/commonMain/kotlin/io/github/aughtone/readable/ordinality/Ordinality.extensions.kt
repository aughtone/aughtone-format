package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

/**
 * Converts this [Long] into its localized human-readable ordinal string representation.
 *
 * For example, in English, `1L` becomes `"1st"`, `2L` becomes `"2nd"`, and `11L` becomes `"11th"`.
 *
 * @param locale The locale defining the formatting rules to apply (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun Long.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return formatOrdinal(this, locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun Long.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}

/**
 * Extension function to format an [Int] into its ordinal string representation.
 *
 * @param locale The locale to use for formatting rules.
 * @return The ordinal string representation of the number.
 */
fun Int.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return formatOrdinal(this, locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun Int.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}


/**
 * Converts this [Short] into its localized human-readable ordinal string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun Short.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return toLong().formatReadableOrdinal(locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun Short.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}

/**
 * Converts this [Byte] into its localized human-readable ordinal string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun Byte.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return toLong().formatReadableOrdinal(locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun Byte.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}

/**
 * Converts this [ULong] into its localized human-readable ordinal string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun ULong.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return toLong().formatReadableOrdinal(locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun ULong.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}

/**
 * Converts this [UInt] into its localized human-readable ordinal string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun UInt.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return toLong().formatReadableOrdinal(locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun UInt.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}

/**
 * Converts this [UShort] into its localized human-readable ordinal string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun UShort.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return toLong().formatReadableOrdinal(locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun UShort.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}

/**
 * Converts this [UByte] into its localized human-readable ordinal string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return The ordinal string representation of this number.
 */
fun UByte.formatReadableOrdinal(locale: Locale = Locale.current): String {
    return toLong().formatReadableOrdinal(locale)
}

@Deprecated(
    message = "Use formatReadableOrdinal instead",
    replaceWith = ReplaceWith("formatReadableOrdinal(locale)")
)
fun UByte.toReadableOrdinal(locale: Locale = Locale.current): String {
    return formatReadableOrdinal(locale)
}
