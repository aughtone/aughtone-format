package io.github.aughtone.readable.number

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import kotlin.math.abs
import kotlin.math.pow

// ── Standard Localized Formatting (toReadable) ───────────────────────────────

/**
 * Formats this [Double] into a localized string representation.
 *
 * Uses the specified [locale] to determine decimal and grouping separators.
 * For example, in `en-US`, `1234.56` becomes `"1,234.6"` (with precision 1).
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Double.toReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    numberFormatterFor(locale, precision)(this)

/**
 * Formats this [Float] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Float.toReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadable(locale, precision)

/**
 * Formats this [Long] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Long.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toDouble().toReadable(locale, precision)

/**
 * Formats this [Int] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Int.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().toReadable(locale, precision)

/**
 * Formats this [Short] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Short.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().toReadable(locale, precision)

/**
 * Formats this [Byte] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun Byte.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().toReadable(locale, precision)

/**
 * Formats this [ULong] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun ULong.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toDouble().toReadable(locale, precision)

/**
 * Formats this [UInt] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun UInt.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().toReadable(locale, precision)

/**
 * Formats this [UShort] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun UShort.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().toReadable(locale, precision)

/**
 * Formats this [UByte] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun UByte.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    toLong().toReadable(locale, precision)

// ── Abbreviated Formatting (toReadableAbbreviated) ────────────────────────────

/**
 * Formats this [Double] into a human-readable abbreviated string (e.g., 1500 -> "1.5k").
 *
 * This function uses standard SI metric prefixes (k, M, G, T, etc.) for values 1000 or greater.
 * Small values (under 1000) are formatted using [toReadable].
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 * @return A localized abbreviated string.
 */
fun Double.toReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1
): String {
    val absoluteValue = abs(this)

    if (absoluteValue < 1000.0) {
        return toReadable(locale, precision)
    }

    // Find the largest prefix that is <= absoluteValue
    val prefix = MetricPrefix.entries
        .filter { it.exponent > 0 && it.exponent % 3 == 0 } // Standard k, M, G, T...
        .sortedByDescending { it.exponent }
        .find { 10.0.pow(it.exponent) <= absoluteValue }

    return if (prefix != null) {
        val scaledValue = this / 10.0.pow(prefix.exponent)
        "${scaledValue.toReadable(locale, precision)}${prefix.symbol}"
    } else {
        toReadable(locale, precision)
    }
}

/**
 * Formats this [Float] into a localized abbreviated string (e.g., 1500.0f -> "1.5k").
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Float.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [Long] into a localized abbreviated string (e.g., 1000000L -> "1M").
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Long.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [Int] into a localized abbreviated string (e.g., 2500 -> "2.5k").
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Int.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [Short] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Short.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [Byte] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun Byte.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [ULong] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun ULong.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [UInt] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun UInt.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [UShort] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun UShort.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)

/**
 * Formats this [UByte] into a localized abbreviated string.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places for the scaled value (default is 1).
 */
fun UByte.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    toDouble().toReadableAbbreviated(locale, precision)
