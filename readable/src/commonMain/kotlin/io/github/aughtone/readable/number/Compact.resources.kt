package io.github.aughtone.readable.number

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.readable.PluralCategory
import io.github.aughtone.readable.lookupTags
import io.github.aughtone.readable.pluralCategoryFor
import io.github.aughtone.types.locale.Locale
import kotlin.concurrent.Volatile

/**
 * One rung of a locale's short compact-number patterns: the power of ten its shown number is
 * divided by, and the text the number sits in, with `#` for the number and `-` where the minus
 * sign goes when it is negative, which is before a leading word in some locales ("-elfu #") and
 * after it in others ("elfu -#").
 *
 * @property exponent The shown number is the value divided by 10^exponent: `6` for "1.5M".
 * @property pattern The template for a shown number of the plural category "other", and for any
 *   shown number with a fraction.
 * @property plurals The template for each plural category that is written differently
 *   ("آلاف" after three to ten in Arabic).
 */
internal class CompactRung(
    val exponent: Int,
    val pattern: String,
    val plurals: Map<PluralCategory, String> = emptyMap(),
)

/** How many digits must stand before a compact number's first group separator, as in ICU. */
private const val COMPACT_MINIMUM_GROUPING = 2

/** The first and last powers of ten the patterns cover. Above the last, its rung is used. */
private const val FIRST_COMPACT_MAGNITUDE = 3
private const val LAST_COMPACT_MAGNITUDE = 19

/** Unpacks one tag's patterns from [cldrCompactPatterns]: one rung per power of ten, or `null`. */
private fun unpackCompact(packed: String): List<CompactRung?> = packed.split(';').map { entry ->
    if (entry.isEmpty()) return@map null
    val fields = entry.split('|')
    CompactRung(
        exponent = fields[0].toInt(),
        pattern = fields[1],
        plurals = fields.drop(2).associate { plural ->
            val (category, template) = plural.split('=', limit = 2)
            PluralCategory.entries.first { it.name.equals(category, ignoreCase = true) } to template
        },
    )
}

@Volatile private var compactCache = emptyMap<String, List<CompactRung?>>()

/**
 * The compact-number rungs [locale] writes with, from 10^3 to 10^19: its own from
 * [cldrCompactPatterns] at the first of its [lookupTags] that has them, otherwise English's.
 */
internal fun compactRungsFor(locale: Locale): List<CompactRung?> {
    val tag = lookupTags(locale).firstOrNull { it in cldrCompactPatterns }
    val key = tag ?: "en"
    compactCache[key]?.let { return it }
    val rungs = unpackCompact(tag?.let { cldrCompactPatterns.getValue(it) } ?: englishCompactPatterns)
    val old = compactCache
    compactCache = if (old.size >= 150) mapOf(key to rungs) else old + (key to rungs)
    return rungs
}

/**
 * The power of ten of the leading digit of the decimal number written in [text], a number's
 * `toString()` (`"1234.5"` is 3, `"1.0E7"` is 7, `"0.05"` is -2), or `null` for zero.
 */
internal fun magnitudeOf(text: String): Int? {
    val unsigned = text.trimStart('-', '+')
    val exponentAt = unsigned.indexOfFirst { it == 'e' || it == 'E' }
    val mantissa = if (exponentAt < 0) unsigned else unsigned.substring(0, exponentAt)
    val exponent = if (exponentAt < 0) 0 else unsigned.substring(exponentAt + 1).removePrefix("+").toInt()
    val whole = mantissa.substringBefore('.').trimStart('0')
    if (whole.isNotEmpty()) return whole.length - 1 + exponent
    val fraction = mantissa.substringAfter('.', "")
    val first = fraction.indexOfFirst { it != '0' }
    return if (first < 0) null else -(first + 1) + exponent
}

/** The decimal number written in [this] multiplied by 10^[power], exactly, by moving its exponent. */
internal fun String.timesTenTo(power: Int): String {
    val exponentAt = indexOfFirst { it == 'e' || it == 'E' }
    if (exponentAt < 0) return "${this}e$power"
    val exponent = substring(exponentAt + 1).removePrefix("+").toInt()
    return "${substring(0, exponentAt)}e${exponent + power}"
}

/** The rung for a value whose leading digit is at 10^[magnitude], or `null` if it is written whole. */
private fun List<CompactRung?>.at(magnitude: Int?): CompactRung? =
    if (magnitude == null || magnitude < FIRST_COMPACT_MAGNITUDE) null
    else this[minOf(magnitude, LAST_COMPACT_MAGNITUDE) - FIRST_COMPACT_MAGNITUDE]

/**
 * Writes the decimal number in [text] the way [locale] abbreviates a count, from its short
 * compact-number patterns, rounded half away from zero to [precision] decimal places of the shown
 * number, as ICU does. A value whose rounding carries it to the next power of ten is shown on that
 * rung ("1M" for 999,999 in English), and a locale that writes the magnitude whole gets the number
 * whole ("999.999" in German). A number is grouped only from five digits ("1500", "15.000"), as ICU
 * groups compact numbers. The space in a pattern is U+00A0 unless [separators] asks for ASCII.
 */
internal fun formatCompactText(text: String, locale: Locale, precision: Int, separators: Separators): String {
    val rungs = compactRungsFor(locale)
    val magnitude = magnitudeOf(text)
    var rung = rungs.at(magnitude)
    var shown = formatDecimalText(rung?.let { text.timesTenTo(-it.exponent) } ?: text, precision, plainStyle)
    val roundedMagnitude = magnitudeOf(shown)?.plus(rung?.exponent ?: 0)
    if (magnitude != null && roundedMagnitude != null && roundedMagnitude > magnitude && magnitude < LAST_COMPACT_MAGNITUDE) {
        rung = rungs.at(roundedMagnitude)
        shown = formatDecimalText(rung?.let { text.timesTenTo(-it.exponent) } ?: text, precision, plainStyle)
    }
    // ICU groups a compact number only from two digits before the first separator, so "1500"
    // stays whole there even in a locale that writes it "1.500".
    val style = numberStyleFor(locale, separators).groupingFrom(COMPACT_MINIMUM_GROUPING)
    if (rung == null) return formatDecimalText(text, precision, style)
    val category = if ('.' in shown) PluralCategory.Other
    else shown.toLongOrNull()?.let { pluralCategoryFor(locale, it) } ?: PluralCategory.Other
    val template = rung.plurals[category] ?: rung.pattern
    val number = formatDecimalText(text.timesTenTo(-rung.exponent), precision, style)
    // The minus sign goes where the pattern puts it, only when the rounded number is below zero.
    val negative = number.startsWith(style.minus)
    val written = template
        .replace("-", if (negative) style.minus else "")
        .replace("#", number.removePrefix(style.minus))
    return if (separators == Separators.Ascii) written.toAscii() else written
}
