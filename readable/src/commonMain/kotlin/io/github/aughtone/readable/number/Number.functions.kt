package io.github.aughtone.readable.number

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import kotlin.math.pow

/**
 * Formats this [Double] into a localized string representation.
 *
 * Uses the specified [locale] to determine decimal and grouping separators. The value is rounded
 * half away from zero to [precision] decimal places and trailing zeros are trimmed, so [precision]
 * is a maximum, not a fixed width. Every digit is written out — there is no exponent notation,
 * however large or small the value.
 *
 * This never throws. Not-a-number renders as `"NaN"`, and the infinities as `"∞"` and `"-∞"`.
 *
 * Example:
 * ```kotlin
 * 1234.56.formatReadable(Locales.English, precision = 1) // "1,234.6"
 * 1234.56.formatReadable(Locales.German, precision = 1)  // "1.234,6"
 * (-1234.0).formatReadable(Locales.English)              // "-1,234"
 * 12345678.0.formatReadable(Locales.English)             // "12,345,678"
 * ```
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places to include (default is 1).
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
 * The digits are exact across the whole [Long] range; the value is never routed through a [Double].
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0). A whole number has no
 *   fraction, so this only matters when negative, which rounds to tens, hundreds, and so on.
 */
fun Long.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatDecimalText(toString(), locale, precision)

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
 * The digits are exact across the whole [ULong] range; the value is never routed through a [Double].
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 */
fun ULong.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatDecimalText(toString(), locale, precision)

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
 * Small values (under 1000) are formatted using [formatReadable]. A value that rounds up to the
 * next thousand moves to the next prefix, so the number shown is always below 1,000 until the
 * largest prefix (Q, 10^30) is reached. Not-a-number and the infinities carry no prefix.
 *
 * Examples:
 * ```kotlin
 * 1500.0.formatReadableAbbreviated(Locales.English)    // "1.5k"
 * 1000000.0.formatReadableAbbreviated(Locales.English) // "1M"
 * 999999.0.formatReadableAbbreviated(Locales.English)  // "1M", not "1,000k"
 * 500.0.formatReadableAbbreviated(Locales.English)     // "500"
 * (-1500.0).formatReadableAbbreviated(Locales.English) // "-1.5k"
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
    val (symbol, divisor) = prefixRungFor(this, precision, abbreviationLadder)
        ?: return formatReadable(locale, precision)
    return "${(this / divisor).formatReadable(locale, precision)}$symbol"
}

/** No prefix, then the standard SI thousands (k, M, G, T, …), smallest first. */
private val abbreviationLadder: List<Pair<String, Double>> =
    listOf("" to 1.0) + MetricPrefix.entries
        .filter { it.exponent > 0 && it.exponent % 3 == 0 }
        .sortedBy { it.exponent }
        .map { it.symbol to 10.0.pow(it.exponent) }

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
