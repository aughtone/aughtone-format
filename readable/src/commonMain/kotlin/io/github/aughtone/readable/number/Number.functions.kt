package io.github.aughtone.readable.number

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Formats a number into a human-readable abbreviated string (e.g., 1500 -> "1.5k").
 *
 * @param locale The locale to use for formatting (defaults to system default).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized abbreviated string.
 */
fun Double.toReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1
): String {
    val absoluteValue = abs(this)
    val formatter = numberFormatterFor(locale, precision)

    if (absoluteValue < 1000.0) {
        return formatter(this) // Use the baked formatter
    }

    // Find the largest prefix that is <= absoluteValue
    val prefix = MetricPrefix.entries
        .filter { it.exponent > 0 && it.exponent % 3 == 0 } // Standard k, M, G, T...
        .sortedByDescending { it.exponent }
        .find { 10.0.pow(it.exponent) <= absoluteValue }

    return if (prefix != null) {
        val scaledValue = this / 10.0.pow(prefix.exponent)
        // We still need a way to handle precision for abbreviated values
        // For now, we'll use the simple formatter for the scaled value
        "${formatter(scaledValue)}${prefix.symbol}"
    } else {
        formatter(this)
    }
}

