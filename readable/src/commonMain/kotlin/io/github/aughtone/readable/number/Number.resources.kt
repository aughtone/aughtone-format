package io.github.aughtone.readable.number

import io.github.aughtone.types.locale.Locale
import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Helper to create a [NumberFormatter] with specific separators and precision.
 */
private fun createSimpleFormatter(
    decimalSeparator: String,
    groupingSeparator: String,
    precision: Int
): NumberFormatter = { value ->
    val factor = 10.0.pow(precision)
    val roundedValue = (value * factor).roundToLong() / factor
    
    val parts = roundedValue.toString().split(".")
    val integerPart = parts[0]
    val fractionalPart = if (parts.size > 1) parts[1] else ""

    val groupedInteger = integerPart.reversed()
        .chunked(3)
        .joinToString(groupingSeparator)
        .reversed()

    if (precision > 0 && fractionalPart.isNotEmpty() && fractionalPart != "0") {
        val trimmedFraction = fractionalPart.take(precision).removeSuffix("0").removeSuffix("0")
        if (trimmedFraction.isNotEmpty()) {
            "$groupedInteger$decimalSeparator$trimmedFraction"
        } else {
            groupedInteger
        }
    } else {
        groupedInteger
    }
}

/**
 * Retrieves the [NumberFormatter] for a given [Locale].
 */
fun numberFormatterFor(locale: Locale, precision: Int = 1): NumberFormatter {
    val separators = when (locale.languageCode) {
        // Comma decimal, Space grouping
        "fr", "sv", "ru", "cs", "sk", "pl", "fi", "no", "nb", "nn" -> "," to " "
        
        // Comma decimal, Dot grouping
        "de", "it", "es", "pt", "nl", "tr", "el", "bg", "hu", "ro", "sl", "hr", "sq", "sr", "et", "lv", "lt", "mk", "uz", "az", "be", "kk", "uk", "is", "gl", "ca", "da", "eu" -> "," to "."
        
        // Dot decimal, Comma grouping (Default for en, zh, ja, ko, th, hi, id, ms, ar, fa, sw, vi, af)
        else -> "." to ","
    }

    return createSimpleFormatter(separators.first, separators.second, precision)
}
