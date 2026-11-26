package io.github.aughtone.readable.number

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import kotlin.math.pow

/**
 * Formats this [Double] into a localized string representation.
 *
 * Uses the specified [locale] to determine the decimal and group separators and the minus sign,
 * as Unicode CLDR writes them. Where a locale groups digits with a space it is a no-break space —
 * U+202F in French, U+00A0 in Canadian French, Swedish or Polish — so a number never breaks
 * across a line; pass [Separators.Ascii] for ordinary spaces and an ASCII minus. The value is
 * rounded half away from zero to [precision] decimal places and trailing zeros are trimmed, so
 * [precision] is a maximum, not a fixed width. Every digit is written out — there is no exponent
 * notation, however large or small the value.
 *
 * This never throws. Not-a-number renders as `"NaN"`, and the infinities as `"∞"` and `"-∞"`,
 * with the locale's minus sign.
 *
 * Example:
 * ```kotlin
 * 1234.56.formatReadable(Locales.English, precision = 1) // "1,234.6"
 * 1234.56.formatReadable(Locales.German, precision = 1)  // "1.234,6"
 * 1234.56.formatReadable(Locales.French, precision = 1)  // "1 234,6" (U+202F between groups)
 * (-1234.0).formatReadable(Locales.English)              // "-1,234"
 * (-1234.0).formatReadable(Locales.Swedish)              // "−1 234" (U+2212, then U+00A0)
 * (-1234.0).formatReadable(Locales.Swedish, separators = Separators.Ascii) // "-1 234"
 * 12345678.0.formatReadable(Locales.English)             // "12,345,678"
 * ```
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places to include (default is 1).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun Double.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 1,
    separators: Separators = Separators.Typographic,
): String =
    numberFormatterFor(locale, precision, separators)(this)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Double.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Double.toReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadable(locale, precision)

/**
 * Formats this [Float] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun Float.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 1,
    separators: Separators = Separators.Typographic,
): String =
    toDouble().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Float.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Float.toReadable(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadable(locale, precision)

/**
 * Formats this [Long] into a localized string representation.
 *
 * The digits are exact across the whole [Long] range; the value is never routed through a [Double].
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0). A whole number has no
 *   fraction, so this only matters when negative, which rounds to tens, hundreds, and so on.
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun Long.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    formatDecimalText(toString(), locale, precision, separators = separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Long.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Long.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Int] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun Int.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    toLong().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Int.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Int.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Short] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun Short.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    toLong().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Short.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Short.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Byte] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun Byte.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    toLong().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Byte.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun Byte.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [ULong] into a localized string representation.
 *
 * The digits are exact across the whole [ULong] range; the value is never routed through a [Double].
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun ULong.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    formatDecimalText(toString(), locale, precision, separators = separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun ULong.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun ULong.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [UInt] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun UInt.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    toLong().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun UInt.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun UInt.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [UShort] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun UShort.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    toLong().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun UShort.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun UShort.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [UByte] into a localized string representation.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 0).
 * @param separators Whether the separators and signs are the typographic ones Unicode CLDR uses
 *   (the default), or plain ASCII. See [Separators].
 */
fun UByte.formatReadable(
    locale: Locale = Locale.current,
    precision: Int = 0,
    separators: Separators = Separators.Typographic,
): String =
    toLong().formatReadable(locale, precision, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun UByte.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision, Separators.Typographic)

@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(locale, precision)")
)
fun UByte.toReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatReadable(locale, precision)

/**
 * Formats this [Double] as a short count the way [locale] abbreviates one, or with SI prefixes.
 *
 * With [Abbreviation.Compact], the default, the abbreviation is the locale's own, from Unicode
 * CLDR's short compact-number patterns: `"1.5K"` in English, `"1,5 Mio."` in German, `"150万"` in
 * Japanese and Chinese, which count in ten-thousands, and `"15 लाख"` in Hindi, which counts in
 * lakhs. A locale that does not abbreviate a magnitude writes the number whole: German thousands
 * stay `"1500"`, and Japanese and Chinese ones `"1234"`. The space
 * between the number and its word is a no-break space (U+00A0), so they never wrap apart.
 *
 * With [Abbreviation.Si] the abbreviation is an SI prefix (k, M, G, T, … up to Q, 10^30), the
 * same in every language, as it was before 4.4.0: use it for a quantity rather than a count.
 *
 * Either way, a value that rounds up to the next rung is shown on it, so 999,999 reads `"1M"` and
 * never `"1,000K"`, while a locale with no rung to carry to writes it whole (`"999.999"` in German).
 * Values below the first rung, not-a-number and the infinities are written as [formatReadable]
 * writes them.
 *
 * Examples:
 * ```kotlin
 * 1500.0.formatReadableAbbreviated(Locales.English)                            // "1.5K"
 * 999999.0.formatReadableAbbreviated(Locales.English)                          // "1M", not "1,000K"
 * 1200000000.0.formatReadableAbbreviated(Locales.English)                      // "1.2B"
 * 1500000.0.formatReadableAbbreviated(Locales.German)                          // "1,5 Mio." (U+00A0)
 * 1500.0.formatReadableAbbreviated(Locales.German)                             // "1500"
 * 1500000.0.formatReadableAbbreviated(Locales.Japanese)                        // "150万"
 * 1500.0.formatReadableAbbreviated(Locales.English, abbreviation = Abbreviation.Si) // "1.5k"
 * 500.0.formatReadableAbbreviated(Locales.English)                             // "500"
 * ```
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun Double.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String = when {
    !isFinite() -> formatReadable(locale, precision, separators)
    abbreviation == Abbreviation.Si -> siAbbreviated(this, locale, precision, separators)
    else -> formatCompactText(toString(), locale, precision, separators)
}

/** [value] with the SI prefix of its magnitude, as `formatReadableAbbreviated` wrote it before 4.4.0. */
private fun siAbbreviated(value: Double, locale: Locale, precision: Int, separators: Separators): String {
    val (symbol, divisor) = prefixRungFor(value, precision, abbreviationLadder)
        ?: return value.formatReadable(locale, precision, separators)
    return "${(value / divisor).formatReadable(locale, precision, separators)}$symbol"
}

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Double.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

/** No prefix, then the standard SI thousands (k, M, G, T, …), smallest first. */
private val abbreviationLadder: List<Pair<String, Double>> =
    listOf("" to 1.0) + MetricPrefix.entries
        .filter { it.exponent > 0 && it.exponent % 3 == 0 }
        .sortedBy { it.exponent }
        .map { it.symbol to 10.0.pow(it.exponent) }

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Double.toReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Float] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `1500.0f.formatReadableAbbreviated(Locales.English)` is
 * `"1.5K"`.
 *
 * Widened to a [Double] first, as [Float.formatReadable] is.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun Float.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Float.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Float.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Long] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `1500000L.formatReadableAbbreviated(Locales.English)` is
 * `"1.5M"`.
 *
 * Written from its exact digits, never through a [Double], so the largest values round correctly.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun Long.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Long.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Long.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Int] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `2500.formatReadableAbbreviated(Locales.English)` is
 * `"2.5K"`.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun Int.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Int.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Int.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Short] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `1500.toShort().formatReadableAbbreviated(Locales.English)` is
 * `"1.5K"`.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun Short.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Short.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Short.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [Byte] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `100.toByte().formatReadableAbbreviated(Locales.English)` is
 * `"100"`.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun Byte.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Byte.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun Byte.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [ULong] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `1500000uL.formatReadableAbbreviated(Locales.English)` is
 * `"1.5M"`.
 *
 * Written from its exact digits, never through a [Double].
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun ULong.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun ULong.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun ULong.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [UInt] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `2500u.formatReadableAbbreviated(Locales.English)` is
 * `"2.5K"`.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun UInt.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun UInt.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun UInt.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [UShort] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `1500.toUShort().formatReadableAbbreviated(Locales.English)` is
 * `"1.5K"`.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun UShort.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun UShort.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun UShort.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)

/**
 * Formats this [UByte] as a short count the way [locale] abbreviates one, or with SI prefixes, as
 * [Double.formatReadableAbbreviated] does: `100.toUByte().formatReadableAbbreviated(Locales.English)` is
 * `"100"`.
 *
 * @param locale The locale to use for numeric formatting (defaults to [Locale.current]).
 * @param precision The most decimal places the shown number keeps (default 1); trailing zeros are
 *   trimmed.
 * @param abbreviation [Abbreviation.Compact], the locale's own short form (the default), or
 *   [Abbreviation.Si] for SI prefixes in every language.
 * @param separators Whether the separators, signs and the space before the locale's word are the
 *   typographic ones Unicode CLDR uses (the default, no-break), or plain ASCII. See [Separators].
 * @return A localized abbreviated string.
 */
fun UByte.formatReadableAbbreviated(
    locale: Locale = Locale.current,
    precision: Int = 1,
    abbreviation: Abbreviation = Abbreviation.Compact,
    separators: Separators = Separators.Typographic,
): String =
    when (abbreviation) {
        Abbreviation.Compact -> formatCompactText(toString(), locale, precision, separators)
        Abbreviation.Si -> toDouble().formatReadableAbbreviated(locale, precision, abbreviation, separators)
    }

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun UByte.formatReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision, Abbreviation.Compact, Separators.Typographic)

@Deprecated(
    message = "Use formatReadableAbbreviated instead",
    replaceWith = ReplaceWith("formatReadableAbbreviated(locale, precision)")
)
fun UByte.toReadableAbbreviated(locale: Locale = Locale.current, precision: Int = 1): String =
    formatReadableAbbreviated(locale, precision)
