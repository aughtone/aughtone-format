package io.github.aughtone.toolbox


import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

/**
 * Formats a number into a human-readable string using metric prefixes (K, M, G, T, P, E).
 *
 * This function is useful for displaying large numbers in a compact and understandable format,
 * for example, for like counts, view counts, or scores.
 *
 * - Numbers less than 1000 are displayed as is (e.g., 999).
 * - Numbers greater than or equal to 1000 are formatted with a single decimal place
 *   and a suffix (e.g., 1.2K, 5.5M).
 *
 * @receiver The Long number to be formatted.
 * @return A human-readable String representation of the number.
 */
fun Long.toHumanReadableString(): String {
    if (this < 1000) return this.toString()

    val magnitude = (log10(this.toDouble()) / 3).toInt()
    val value = this / 10.0.pow(magnitude * 3)
    val suffix = arrayOf("K", "M", "G", "T", "P", "E")[magnitude - 1]

    // Use floor to avoid rounding up to the next magnitude (e.g., 999.9K -> 1.0M)
    val formattedValue = floor(value * 10) / 10

    // Display as an integer if it's a whole number (e.g., 1.0M -> 1M)
    return if (formattedValue % 1.0 == 0.0) {
        "${formattedValue.toInt()}$suffix"
    } else {
        "$formattedValue$suffix"
    }
}
