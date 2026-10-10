@file:Suppress("DEPRECATION")

package io.github.aughtone.readable.number

import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.concurrent.Volatile

/**
 * Helper to create a [NumberFormatter] with specific separators and precision.
 */
private fun createSimpleFormatter(
    decimalSeparator: String,
    groupingSeparator: String,
    precision: Int
): NumberFormatter = { value ->
    formatDecimal(value, precision, decimalSeparator, groupingSeparator)
}

/**
 * Renders [value] as grouped decimal text, rounded half away from zero to [precision] decimal places.
 *
 * Not-a-number renders as `"NaN"` and the infinities as `"∞"` and `"-∞"`; nothing here throws.
 *
 * @param fixedFraction When `true` the fraction is padded with zeros to exactly [precision] digits;
 *   when `false` trailing zeros are trimmed and a whole number has no decimal separator.
 */
internal fun formatDecimal(
    value: Double,
    precision: Int,
    decimalSeparator: String,
    groupingSeparator: String,
    fixedFraction: Boolean = false,
): String = when {
    value.isNaN() -> "NaN"
    value.isInfinite() -> if (value > 0) "∞" else "-∞"
    else -> formatDecimalText(value.toString(), precision, decimalSeparator, groupingSeparator, fixedFraction)
}

/**
 * Renders the decimal number written in [text] as grouped decimal text. See [formatDecimal].
 *
 * [text] is the output of a number's `toString()`: an optional sign, digits, an optional fraction
 * and an optional exponent (`"-123"`, `"1234.5"`, `"1.0E7"`, `"5e-7"`, `"1e+21"`). Working from the
 * digits rather than from arithmetic is what keeps a whole number exact however large it is, and
 * makes the result independent of where each platform switches to exponent notation.
 */
internal fun formatDecimalText(
    text: String,
    precision: Int,
    decimalSeparator: String,
    groupingSeparator: String,
    fixedFraction: Boolean = false,
): String {
    val negative = text.startsWith('-')
    val unsigned = text.trimStart('-', '+')
    val exponentAt = unsigned.indexOfFirst { it == 'e' || it == 'E' }
    val mantissa = if (exponentAt < 0) unsigned else unsigned.substring(0, exponentAt)
    val exponent = if (exponentAt < 0) 0 else unsigned.substring(exponentAt + 1).removePrefix("+").toInt()
    val pointAt = mantissa.indexOf('.')
    val mantissaWhole = if (pointAt < 0) mantissa else mantissa.substring(0, pointAt)
    val mantissaFraction = if (pointAt < 0) "" else mantissa.substring(pointAt + 1)

    // Undo the exponent: every significant digit, and how many of them sit before the decimal point.
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

    // Round at the requested place. A negative precision rounds to tens, hundreds, and so on.
    val allDigits = plainWhole + plainFraction
    val cut = plainWhole.length + precision
    val kept = when {
        cut < 0 -> ""
        cut >= allDigits.length -> allDigits
        allDigits[cut] >= '5' -> incrementDigits(allDigits.substring(0, cut))
        else -> allDigits.substring(0, cut)
    }
    val fractionLength = minOf(precision, plainFraction.length).coerceAtLeast(0)
    val roundedWhole = if (precision < 0) kept + "0".repeat(-precision) else kept.dropLast(fractionLength)
    val roundedFraction = kept.takeLast(fractionLength)

    val whole = roundedWhole.trimStart('0').ifEmpty { "0" }
    val fraction = if (fixedFraction) roundedFraction.padEnd(precision, '0') else roundedFraction.trimEnd('0')

    val isZero = whole == "0" && fraction.all { it == '0' }
    return buildString {
        // Zero carries no sign, so a small negative that rounds away does not read as "-0".
        if (negative && !isZero) append('-')
        whole.forEachIndexed { index, digit ->
            if (index > 0 && (whole.length - index) % 3 == 0) append(groupingSeparator)
            append(digit)
        }
        if (fraction.isNotEmpty()) append(decimalSeparator).append(fraction)
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

/** The magnitude [value] shows once it has been rounded to [precision] decimal places. */
private fun roundedMagnitude(value: Double, precision: Int): Double =
    formatDecimal(abs(value), precision, ".", "").toDouble()

/**
 * Picks which rung of a unit-prefix ladder to display [value] on.
 *
 * [ladder] lists each rung as `symbol to divisor`, in ascending order of divisor. The rung chosen
 * is the largest whose divisor does not exceed the magnitude — then the one above it if rounding
 * to [precision] would carry the scaled value up to that next rung, so 999,960 reads "1M" rather
 * than "1,000k".
 *
 * @return the rung to scale by, or `null` when the value is not finite or is smaller than every
 *   rung (zero included), in which case the caller shows it unscaled.
 */
internal fun prefixRungFor(
    value: Double,
    precision: Int,
    ladder: List<Pair<String, Double>>,
): Pair<String, Double>? {
    val magnitude = abs(value)
    if (!magnitude.isFinite()) return null
    val index = ladder.indexOfLast { it.second <= magnitude }
    if (index < 0) return null
    if (index < ladder.lastIndex) {
        val step = ladder[index + 1].second / ladder[index].second
        if (roundedMagnitude(value / ladder[index].second, precision) >= step) return ladder[index + 1]
    }
    return ladder[index]
}

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun separatorsFor(tag: String): Pair<String, String>? = when (tag) {
    // ── Region-specific overrides (checked before language fallback) ──────────
    // South Africa: comma decimal, space grouping (ISO 31-0) e.g. "1 234,56"
    "en-ZA"          -> "," to " "
    // Switzerland: all variants use apostrophe grouping
    "fr-CH"          -> "," to "'"   // Swiss French:  "1'234,56"
    "de-CH", "it-CH" -> "." to "'"   // Swiss German/Italian: "1'234.56"

    // Portugal groups with a space; Brazil, the "pt" default below, groups with a dot.
    "pt-PT"          -> "," to " "

    // ── Language-level rules ──────────────────────────────────────────────────
    // Comma decimal, Space grouping
    "fr", "sv", "ru", "cs", "sk", "pl", "fi", "no", "nb", "nn", "hy",
    "af", "be", "bg", "et", "hu", "ka", "kk", "lt", "lv", "sq", "uk", "uz" -> "," to " "
    // Comma decimal, Dot grouping
    "de", "it", "es", "pt", "nl", "tr", "el", "ro",
    "sl", "hr", "sr", "mk", "az",
    "is", "gl", "ca", "da", "eu", "id", "vi" -> "," to "."
    // Dot decimal, Comma grouping (en, zh, ja, ko, th, hi, ms, ar, fa, sw, …)
    else -> null  // null = use dot/comma default at call site
}

@Volatile private var numberFormatterCache = emptyMap<Pair<String, Int>, NumberFormatter>()

/**
 * Resolves the decimal and grouping separators for [locale], as a `decimal to grouping` pair.
 * Walks the BCP 47 subtag chain ("en-ZA" → "en") and ends at dot-decimal / comma-grouping.
 */
internal fun numberSeparatorsFor(locale: Locale): Pair<String, String> {
    var currentTag: String =
        if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    while (currentTag.isNotEmpty()) {
        separatorsFor(currentTag)?.let { return it }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return "." to ","
}

/**
 * Renders the decimal number written in [text] with the separators of [locale]. This is the exact
 * path for whole-number types and for money, which must not pass through a [Double].
 */
internal fun formatDecimalText(
    text: String,
    locale: Locale,
    precision: Int,
    fixedFraction: Boolean = false,
): String {
    val (decimalSeparator, groupingSeparator) = numberSeparatorsFor(locale)
    return formatDecimalText(text, precision, decimalSeparator, groupingSeparator, fixedFraction)
}

/**
 * Retrieves the [NumberFormatter] for a given [Locale], building and caching it on first use.
 * Supports full BCP 47 subtag lookup (e.g. "en-ZA", "de-CH") before falling back to
 * the base language code, then to dot-decimal / comma-grouping as a final default.
 *
 * The formatter rounds half away from zero to [precision] decimal places, trims trailing zeros,
 * and groups the whole part in threes with the sign kept outside the grouping. It writes every
 * digit out however large or small the value is, and it never throws: not-a-number renders as
 * `"NaN"` and the infinities as `"∞"` and `"-∞"`.
 */
fun numberFormatterFor(locale: Locale, precision: Int = 1): NumberFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    val cacheKey = fullTag to precision
    numberFormatterCache[cacheKey]?.let { return it }

    val (dec, grp) = numberSeparatorsFor(locale)
    val built = createSimpleFormatter(dec, grp, precision)

    val oldCache = numberFormatterCache
    if (!oldCache.containsKey(cacheKey)) {
        val newCache = if (oldCache.size >= 150) {
            mapOf(cacheKey to built)
        } else {
            oldCache + (cacheKey to built)
        }
        numberFormatterCache = newCache
    }
    return built
}
