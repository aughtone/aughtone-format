package io.github.aughtone.readable.number

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import kotlin.math.abs
import kotlin.math.pow

/**
 * Formats this [Double] into a localized string representation.
 *
 * Uses the specified [locale] to determine decimal and grouping separators.
 *
 * Example:
 * ```kotlin
 * 1234.56.toReadable(Locale("en"), precision = 1) // "1,234.6"
 * 1234.56.toReadable(Locale("de"), precision = 1) // "1.234,6"
 * ```
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Double.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    numberFormatterFor(locale, precision)(this)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Double.toReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadable(locale, precision)

/**
 * Formats this [Float] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Float.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Float.toReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadable(locale, precision)

/**
 * Formats this [Long] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Long.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toDouble().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Long.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Int] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Int.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Int.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Short] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Short.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Short.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Byte] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Byte.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Byte.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [ULong] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun ULong.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toDouble().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun ULong.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [UInt] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun UInt.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun UInt.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [UShort] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun UShort.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun UShort.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [UByte] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun UByte.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().formatReadable(locale, precision)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun UByte.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Double] into a localized human-readable abbreviated string.
 *
 * This function uses standard SI metric prefixes (k, M, G, T, etc.) for values 1000 or greater.
 * Small values (under 1000) are formatted using [formatReadable].
 *
 * Examples:
 * ```kotlin
 * 1500.0.formatReadableAbbreviated(Locale("en")) // "1.5k"
 * 1000000.0.formatReadableAbbreviated(Locale("en")) // "1M"
 * 500.0.formatReadableAbbreviated(Locale("en")) // "500"
 * ```
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 * @return A localized abbreviated string.
 */
fun Double.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1
): String {
    val absoluteValue = abs(this)

    if (absoluteValue < 1000.0) {
        return formatReadable(locale, precision)
    }

    // Find the largest prefix that is <= absoluteValue
    val prefix = MetricPrefix.entries
        .filter { it.exponent > 0 && it.exponent % 3 == 0 } // Standard k, M, G, T...
        .sortedByDescending { it.exponent }
        .find { 10.0.pow(it.exponent) <= absoluteValue }

    return if (prefix != null) {
        val scaledValue = this / 10.0.pow(prefix.exponent)
        "${scaledValue.formatReadable(locale, precision)}${prefix.symbol}"
    } else {
        formatReadable(locale, precision)
    }
}

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Double.toReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Float] into a localized abbreviated string (e.g., 1500.0f -> "1.5k").
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Float.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Float.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Long] into a localized abbreviated string (e.g., 1000000L -> "1M").
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Long.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Long.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Int] into a localized abbreviated string (e.g., 2500 -> "2.5k").
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Int.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Int.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Short] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Short.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Short.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Byte] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Byte.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Byte.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [ULong] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun ULong.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun ULong.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [UInt] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun UInt.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun UInt.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [UShort] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun UShort.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun UShort.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [UByte] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun UByte.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().formatReadableAbbreviated(locale, precision)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun UByte.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)
