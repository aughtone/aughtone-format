@file:Suppress("DEPRECATION")

package io.github.aughtone.readable.number

import io.github.aughtone.readable.lookupTags
import io.github.aughtone.readable.lookupKey
import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.concurrent.Volatile

/**
 * Helper to create a [NumberFormatter] with specific separators and precision.
 */
private fun createSimpleFormatter(style: NumberStyle, precision: Int): NumberFormatter = { value ->
    formatDecimal(value, precision, style)
}

/**
 * Renders [value] as grouped decimal text, rounded half away from zero to [precision] decimal places.
 *
 * Not-a-number renders as `"NaN"` and the infinities as `"∞"` and `"-∞"`, with the style's minus
 * sign; nothing here throws.
 *
 * @param fixedFraction When `true` the fraction is padded with zeros to exactly [precision] digits;
 *   when `false` trailing zeros are trimmed and a whole number has no decimal separator.
 */
internal fun formatDecimal(
    value: Double,
    precision: Int,
    style: NumberStyle,
    fixedFraction: Boolean = false,
): String = when {
    value.isNaN() -> "NaN"
    value.isInfinite() -> if (value > 0) "∞" else "${style.minus}∞"
    else -> formatDecimalText(value.toString(), precision, style, fixedFraction)
}

/**
 * How a locale writes a number. The values for each locale are generated from Unicode CLDR into
 * [cldrNumberStyles]; see `tools/cldr/number-symbols.mjs`.
 *
 * @property decimal The decimal separator.
 * @property grouping The separator between groups of digits; empty for no grouping.
 * @property minus The minus sign: the hyphen-minus in most locales, U+2212 in Swedish, Finnish and
 *   others.
 * @property plus The plus sign.
 * @property percent The percent sign.
 * @property minimumGroupingDigits How many digits must stand before the first separator for a
 *   number to be grouped at all. With `2`, as in Spanish or Polish, "1234" stays whole and
 *   grouping starts at "12 345".
 * @property indian Whether digits are grouped the Indian way: the last three, then in twos
 *   ("12,34,567").
 */
internal class NumberStyle(
    val decimal: String,
    val grouping: String,
    val minus: String = "-",
    val plus: String = "+",
    val percent: String = "%",
    val minimumGroupingDigits: Int = 1,
    val indian: Boolean = false,
) {
    /** This style with its separators and signs written as [Separators] asks. */
    fun with(separators: Separators): NumberStyle = when (separators) {
        Separators.Typographic -> this
        Separators.Ascii -> NumberStyle(
            decimal = decimal.toAscii(),
            grouping = grouping.toAscii(),
            minus = minus.toAscii(),
            plus = plus.toAscii(),
            percent = percent.toAscii(),
            minimumGroupingDigits = minimumGroupingDigits,
            indian = indian,
        )
    }
}

/** Writes the no-break spaces, the minus sign and the Arabic percent sign as their ASCII forms. */
internal fun String.toAscii(): String = buildString(length) {
    for (c in this@toAscii) {
        append(
            when (c) {
                '\u00A0', '\u202F' -> ' '
                '\u2212' -> '-'
                '\u066A' -> '%'
                else -> c
            },
        )
    }
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
    style: NumberStyle,
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
        if (negative && !isZero) append(style.minus)
        val grouped = whole.length >= 3 + style.minimumGroupingDigits
        whole.forEachIndexed { index, digit ->
            val remaining = whole.length - index
            val boundary = when {
                !grouped || index == 0 -> false
                // Indian grouping: a separator before the last three digits, then every two.
                style.indian -> remaining == 3 || (remaining > 3 && (remaining - 3) % 2 == 0)
                else -> remaining % 3 == 0
            }
            if (boundary) append(style.grouping)
            append(digit)
        }
        if (fraction.isNotEmpty()) append(style.decimal).append(fraction)
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
    formatDecimal(abs(value), precision, plainStyle).toDouble()

/** Dot decimal and no grouping: the form that parses back to a number. */
private val plainStyle = NumberStyle(decimal = ".", grouping = "")

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

@Volatile private var numberFormatterCache = emptyMap<Triple<String, Int, Separators>, NumberFormatter>()

/**
 * Resolves how [locale] writes a number, from the data generated from Unicode CLDR. Walks the
 * locale's [lookupTags] ("fr-CA" → "fr") and ends at English, which is also how a language CLDR has
 * no number data for reads.
 */
internal fun numberStyleFor(locale: Locale, separators: Separators = Separators.Typographic): NumberStyle {
    for (currentTag in lookupTags(locale)) {
        cldrNumberStyles[currentTag]?.let { return it.with(separators) }
    }
    return englishNumberStyle.with(separators)
}

/**
 * Renders the decimal number written in [text] the way [locale] writes numbers. This is the exact
 * path for whole-number types and for money, which must not pass through a [Double].
 */
internal fun formatDecimalText(
    text: String,
    locale: Locale,
    precision: Int,
    fixedFraction: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatDecimalText(text, precision, numberStyleFor(locale, separators), fixedFraction)

/**
 * Retrieves the [NumberFormatter] for a given [Locale], building and caching it on first use.
 * The separators and minus sign are those Unicode CLDR gives the locale, looked up by its full
 * BCP 47 tag (e.g. "en-ZA", "de-CH"), then its language, then English as a final default. Spaces
 * between groups are no-break spaces unless [separators] asks for [Separators.Ascii].
 *
 * The formatter rounds half away from zero to [precision] decimal places, trims trailing zeros,
 * and groups the whole part the way the locale does — in threes for most, from five digits up in
 * the languages that leave "1234" whole, in lakhs for India — with the sign kept outside the
 * grouping. It writes every
 * digit out however large or small the value is, and it never throws: not-a-number renders as
 * `"NaN"` and the infinities as `"∞"` and `"-∞"`.
 */
fun numberFormatterFor(
    locale: Locale,
    precision: Int = 1,
    separators: Separators = Separators.Typographic,
): NumberFormatter {
    val fullTag = lookupKey(locale)
    val cacheKey = Triple(fullTag, precision, separators)
    numberFormatterCache[cacheKey]?.let { return it }

    val built = createSimpleFormatter(numberStyleFor(locale, separators), precision)

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

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun numberFormatterFor(locale: Locale, precision: Int = 1): NumberFormatter =
    numberFormatterFor(locale, precision, Separators.Typographic)
