package io.github.aughtone.readable.number

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale

/**
 * Formats this [Double] as a percentage in [locale].
 *
 * **The number is a fraction unless [outOfHundred] is `true`**: `0.25` is 25 percent and `1.5` is
 * 150 percent. With `outOfHundred = true` the number is already out of a hundred and is written as
 * it stands: `25.0` is 25 percent and `0.56` is a little over half of one percent. The rule is the
 * same for every number type, so `25.formatReadablePercent()` is 2,500 percent.
 *
 * Where the percent sign goes, the space beside it, the grouping and the signs are the ones
 * Unicode CLDR gives the locale: `"25%"` in English, `"25 %"` in French and German, `"%25"` in
 * Turkish. That space is a no-break space (U+00A0), so a percentage never breaks before its sign;
 * pass [Separators.Ascii] for an ordinary space and an ASCII minus.
 *
 * [precision] is the most decimal places the percentage shows, counted after any multiplying by a
 * hundred. It is `0` by default, as in CLDR, so `0.256` is `"26%"`, and a value under half a
 * percent is `"0%"` until a higher precision is given. The value is rounded half away from zero
 * and trailing zeros are trimmed, so [precision] is a maximum, not a fixed width. A negative
 * precision rounds to tens, hundreds, and so on.
 *
 * This never throws. Not-a-number and the infinities take the same layout: `"NaN%"`, `"-∞%"`.
 *
 * Example:
 * ```kotlin
 * 0.25.formatReadablePercent(Locales.English)                      // "25%"
 * 0.25.formatReadablePercent(Locales.French)                       // "25 %" (U+00A0)
 * 0.25.formatReadablePercent(Locales.Turkish)                      // "%25"
 * 0.1234.formatReadablePercent(Locales.English, precision = 1)     // "12.3%"
 * 0.56.formatReadablePercent(Locales.English)                      // "56%"
 * 0.56.formatReadablePercent(Locales.English, precision = 2, outOfHundred = true) // "0.56%"
 * ```
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0).
 * @param outOfHundred Whether the number is already out of a hundred (`25.0` is 25 percent).
 *   `false` by default: the number is a fraction (`0.25` is 25 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun Double.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercent(this, locale, precision, outOfHundred, separators)

/**
 * Formats this [Float] as a percentage in [locale]: a fraction (`0.25f` is 25 percent) unless
 * [outOfHundred] is `true`. See [Double.formatReadablePercent].
 *
 * The value is widened to a [Double] first, as [Float.formatReadable] does, so it rounds by the
 * value the [Float] holds: one written `0.145f` can be just under 14.5 percent.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0).
 * @param outOfHundred Whether the number is already out of a hundred (`25f` is 25 percent).
 *   `false` by default: the number is a fraction (`0.25f` is 25 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun Float.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercent(toDouble(), locale, precision, outOfHundred, separators)

/**
 * Formats this [Long] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [Long] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25L` is 25 percent).
 *   `false` by default: the number is a fraction (`25L` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun Long.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [Int] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: an
 * [Int] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25` is 25 percent).
 *   `false` by default: the number is a fraction (`25` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun Int.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [Short] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [Short] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25` is 25 percent).
 *   `false` by default: the number is a fraction (`25` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun Short.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [Byte] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [Byte] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25` is 25 percent).
 *   `false` by default: the number is a fraction (`25` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun Byte.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [ULong] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [ULong] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25uL` is 25 percent).
 *   `false` by default: the number is a fraction (`25uL` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun ULong.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [UInt] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [UInt] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25u` is 25 percent).
 *   `false` by default: the number is a fraction (`25u` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun UInt.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [UShort] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [UShort] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25u` is 25 percent).
 *   `false` by default: the number is a fraction (`25u` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun UShort.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)

/**
 * Formats this [UByte] as a percentage in [locale], keeping every digit. See
 * [Double.formatReadablePercent].
 *
 * **As with every other type, the number is a fraction unless [outOfHundred] is `true`**: a
 * [UByte] of 1 is 100 percent and one of 25 is 2,500 percent. For a count that is already out of
 * a hundred, pass `outOfHundred = true`.
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The maximum number of decimal places in the percentage (default is 0); a
 *   negative one rounds to tens, hundreds, and so on.
 * @param outOfHundred Whether the number is already out of a hundred (`25u` is 25 percent).
 *   `false` by default: the number is a fraction (`25u` is 2,500 percent).
 * @param separators Whether the spaces and signs are the typographic ones Unicode CLDR uses (the
 *   default), or plain ASCII. See [Separators].
 * @return The percentage, with the locale's percent sign.
 */
fun UByte.formatReadablePercent(
    locale: Locale = Locale.current,
    precision: Int = 0,
    outOfHundred: Boolean = false,
    separators: Separators = Separators.Typographic,
): String = formatPercentText(toString(), locale, precision, outOfHundred, separators)
