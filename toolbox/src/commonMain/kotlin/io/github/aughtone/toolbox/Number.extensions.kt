package io.github.aughtone.toolbox

import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Formats a [Double] to a string with a fixed number of decimal places.
 *
 * @param precision The number of decimal places to include.
 * @return A string representation of the number.
 */
fun Double.format(precision: Int): String {
    if (precision <= 0) return roundToLong().toString()
    val factor = 10.0.pow(precision)
    val rounded = (this * factor).roundToLong() / factor
    return rounded.toString().removeSuffix(".0")
}
