package io.github.aughtone.toolbox

import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Formats a [Double] to a string, rounding to at most [precision] decimal places.
 *
 * Trailing zeros are not padded and a trailing `.0` is removed, so the result is
 * the shortest representation of the rounded value: `2.0.format(2)` returns `"2"`
 * and `2.5.format(2)` returns `"2.5"`. A [precision] of `0` or less rounds to a
 * whole number.
 *
 * @param precision The maximum number of decimal places to keep. Values of `0` or
 *   less round to a whole number.
 * @return A string representation of the rounded number, with any trailing `.0` removed.
 */
fun Double.format(precision: Int): String {
    if (precision <= 0) return roundToLong().toString()
    val factor = 10.0.pow(precision)
    val rounded = (this * factor).roundToLong() / factor
    return rounded.toString().removeSuffix(".0")
}
