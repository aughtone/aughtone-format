package io.github.aughtone.toolbox

import io.github.aughtone.types.financial.Currency
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow


/**
 * Formats a number into a human-readable string using metric suffixes (K, M, G, T, P, E).
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
fun Int.toHumanReadableString(): String {
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

/**
 * Formats this Int as a currency string, taking into account the currency's specific formatting rules.
 *
 * This function assumes the Int represents the currency amount in its smallest unit (e.g., cents for USD).
 * The number of decimal places is determined by the `digits` property of the `Currency` object, but can be overridden.
 *
 * The format of the output string can be customized with a pattern. The pattern should contain
 * placeholders for the value and the symbol, which will be replaced accordingly.
 *
 * @param currency The `Currency` object containing details about the currency.
 * @param pattern A string that defines the format. `{value}` will be replaced by the formatted numeric value,
 *                and `{symbol}` will be replaced by the currency symbol. Defaults to `"{symbol}{value}"`.
 * @param scale The number of decimal places to display. Defaults to the `digits` property of the `currency`.
 * @return A String representing the Int formatted as currency.
 *
 * @sample io.github.aughtone.toolbox.IntExtensionsTest.formatCurrency
 */
@ExperimentalMultiplatform()
fun Int.formatCurrency(
    currency: Currency,
    pattern: String = "{symbol}{value}",
    scale: Int? = null
): String {
    val finalScale = scale ?: currency.digits
    val amount = this / 10.0.pow(currency.digits)
    val formattedValue = "%.${finalScale}f".format(amount)
    return pattern.replace("{symbol}", currency.symbol).replace("{value}", formattedValue)
}
