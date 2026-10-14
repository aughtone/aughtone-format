package io.github.aughtone.toolbox

/**
 * Formats a [Double] to a string, rounding to at most [precision] decimal places.
 *
 * Trailing zeros are not padded and a trailing `.0` is removed, so the result is
 * the shortest representation of the rounded value: `2.0.format(2)` returns `"2"`
 * and `2.5.format(2)` returns `"2.5"`. A [precision] of `0` or less rounds to a
 * whole number. Halves round away from zero, so `2.5.format(0)` is `"3"` and
 * `(-2.5).format(0)` is `"-3"`.
 *
 * Every digit is written out: there is no exponent notation however large or small
 * the value, on any platform, so `10000000.0.format(2)` is `"10000000"` and
 * `0.0001.format(4)` is `"0.0001"`. A value that rounds to zero has no sign.
 *
 * This never throws. A value that is not a number, or is infinite, comes back as its
 * own `toString()` — `"NaN"`, `"Infinity"` or `"-Infinity"`.
 *
 * @param precision The maximum number of decimal places to keep. Values of `0` or
 *   less round to a whole number.
 * @return A string representation of the rounded number, with any trailing `.0` removed.
 */
fun Double.format(precision: Int): String =
    if (isNaN() || isInfinite()) toString() else roundDecimalText(toString(), precision.coerceAtLeast(0))

/**
 * Rounds the decimal number written in [text] — a `Double.toString()`, which may carry an exponent
 * (`"1.0E7"`, `"5e-7"`, `"1e+21"`) — to [places] decimal places and writes it out in plain digits.
 *
 * Working on the digits, not on `value * 10^places`, is what keeps large values from saturating
 * and makes the result the same wherever a platform switches to exponent notation.
 */
private fun roundDecimalText(text: String, places: Int): String {
    val negative = text.startsWith('-')
    val unsigned = text.trimStart('-', '+')
    val exponentAt = unsigned.indexOfFirst { it == 'e' || it == 'E' }
    val mantissa = if (exponentAt < 0) unsigned else unsigned.substring(0, exponentAt)
    val exponent = if (exponentAt < 0) 0 else unsigned.substring(exponentAt + 1).removePrefix("+").toInt()
    val pointAt = mantissa.indexOf('.')
    val mantissaWhole = if (pointAt < 0) mantissa else mantissa.substring(0, pointAt)
    val mantissaFraction = if (pointAt < 0) "" else mantissa.substring(pointAt + 1)

    // Undo the exponent: every significant digit, and how many of them sit before the point.
    val significant = mantissaWhole + mantissaFraction
    val pointPosition = mantissaWhole.length + exponent
    val plainWhole: String
    val plainFraction: String
    when {
        pointPosition <= 0 -> {
            plainWhole = ""
            plainFraction = "0".repeat(-pointPosition) + significant
        }
        pointPosition >= significant.length -> {
            plainWhole = significant + "0".repeat(pointPosition - significant.length)
            plainFraction = ""
        }
        else -> {
            plainWhole = significant.substring(0, pointPosition)
            plainFraction = significant.substring(pointPosition)
        }
    }

    val allDigits = plainWhole + plainFraction
    val cut = plainWhole.length + places
    val kept = when {
        cut >= allDigits.length -> allDigits
        allDigits[cut] >= '5' -> incrementDigits(allDigits.substring(0, cut))
        else -> allDigits.substring(0, cut)
    }
    val fractionLength = minOf(places, plainFraction.length)
    val whole = kept.dropLast(fractionLength).trimStart('0').ifEmpty { "0" }
    val fraction = kept.takeLast(fractionLength).trimEnd('0')

    val isZero = whole == "0" && fraction.isEmpty()
    return buildString {
        if (negative && !isZero) append('-')
        append(whole)
        if (fraction.isNotEmpty()) append('.').append(fraction)
    }
}

/** Adds one to a string of decimal digits, carrying into a new leading digit when needed. */
private fun incrementDigits(digits: String): String {
    val result = digits.toCharArray()
    for (index in result.indices.reversed()) {
        if (result[index] != '9') {
            result[index] = result[index] + 1
            return result.concatToString()
        }
        result[index] = '0'
    }
    return "1" + result.concatToString()
}
