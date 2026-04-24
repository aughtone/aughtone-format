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

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun separatorsFor(tag: String): Pair<String, String>? = when (tag) {
    // ── Region-specific overrides (checked before language fallback) ──────────
    // South Africa: comma decimal, space grouping (ISO 31-0) e.g. "1 234,56"
    "en-ZA"          -> "," to " "
    // Switzerland: all variants use apostrophe grouping
    "fr-CH"          -> "," to "'"   // Swiss French:  "1'234,56"
    "de-CH", "it-CH" -> "." to "'"   // Swiss German/Italian: "1'234.56"

    // ── Language-level rules ──────────────────────────────────────────────────
    // Comma decimal, Space grouping
    "fr", "sv", "ru", "cs", "sk", "pl", "fi", "no", "nb", "nn", "hy" -> "," to " "
    // Comma decimal, Dot grouping
    "de", "it", "es", "pt", "nl", "tr", "el", "bg", "hu", "ro",
    "sl", "hr", "sq", "sr", "et", "lv", "lt", "mk", "uz", "az",
    "be", "kk", "uk", "is", "gl", "ca", "da", "eu", "ka" -> "," to "."
    // Dot decimal, Comma grouping (en, zh, ja, ko, th, hi, id, ms, ar, fa, sw, vi, af, …)
    else -> null  // null = use dot/comma default at call site
}

private val numberFormatterCache = mutableMapOf<Pair<String, Int>, NumberFormatter>()

/**
 * Retrieves the [NumberFormatter] for a given [Locale], building and caching it on first use.
 * Supports full BCP 47 subtag lookup (e.g. "en-ZA", "de-CH") before falling back to
 * the base language code, then to dot-decimal / comma-grouping as a final default.
 */
fun numberFormatterFor(locale: Locale, precision: Int = 1): NumberFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    val cacheKey = fullTag to precision
    return numberFormatterCache.getOrPut(cacheKey) {
        // Walk the subtag chain: "en-ZA" → "en" → default
        var currentTag: String = fullTag
        var separators: Pair<String, String>? = null
        while (currentTag.isNotEmpty()) {
            separators = separatorsFor(currentTag)
            if (separators != null) break
            currentTag = currentTag.substringBeforeLast('-', "")
        }
        val (dec, grp) = separators ?: ("." to ",")
        createSimpleFormatter(dec, grp, precision)
    }
}
