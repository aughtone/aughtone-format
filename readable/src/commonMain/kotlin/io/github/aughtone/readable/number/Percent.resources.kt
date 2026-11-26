package io.github.aughtone.readable.number

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.readable.lookupTags
import io.github.aughtone.types.locale.Locale

/**
 * Where a locale puts a percentage's percent sign and minus sign, from Unicode CLDR's percent
 * pattern. The values for each locale are generated into [cldrPercentPatterns]; see
 * `tools/cldr/number-symbols.mjs`.
 *
 * Each template is the percentage's layout, with `%` for the locale's percent sign, `#` for the
 * digits and `-` for the minus sign; any other character is written as it is, which in CLDR's
 * data is only ever a no-break space. English is `"#%"`, French `"# %"`, Turkish `"%#"`.
 *
 * @property positive The layout of a positive percentage or zero.
 * @property negative The layout of a negative percentage.
 * @property indian Whether a percentage is grouped the Indian way, where that differs from the
 *   locale's numbers (Malayalam, Marathi and Telugu group numbers in lakhs and percentages in
 *   threes); `null` to use the locale's number style.
 */
internal class PercentPattern(
    val positive: String,
    val negative: String,
    val indian: Boolean? = null,
)

/**
 * Writes a percentage in this pattern: [digits] is the number without its sign, [minus] the
 * locale's minus sign, [sign] its percent sign.
 */
internal fun PercentPattern.fill(digits: String, negative: Boolean, minus: String, sign: String): String =
    buildString {
        for (c in if (negative) this@fill.negative else positive) {
            when (c) {
                '#' -> append(digits)
                '-' -> append(minus)
                '%' -> append(sign)
                else -> append(c)
            }
        }
    }

/**
 * Resolves where [locale] puts the percent sign. Walks the locale's [lookupTags] ("de-CH" → "de")
 * and ends at English.
 */
internal fun percentPatternFor(locale: Locale): PercentPattern {
    for (currentTag in lookupTags(locale)) {
        cldrPercentPatterns[currentTag]?.let { return it }
    }
    return englishPercentPattern
}

/** How [numberStyle]'s locale writes the digits of a percentage in [pattern]. */
private fun percentStyleFor(numberStyle: NumberStyle, pattern: PercentPattern): NumberStyle =
    if (pattern.indian == null) {
        numberStyle
    } else {
        NumberStyle(
            decimal = numberStyle.decimal,
            grouping = numberStyle.grouping,
            minus = numberStyle.minus,
            plus = numberStyle.plus,
            percent = numberStyle.percent,
            minimumGroupingDigits = numberStyle.minimumGroupingDigits,
            indian = pattern.indian,
        )
    }

/**
 * Multiplies the decimal number written in this text by a hundred by raising its exponent, so no
 * digit passes through arithmetic: `"0.07"` is exactly seven percent, which `0.07 * 100` is not.
 */
private fun String.timesHundred(): String = timesTenTo(2)

/**
 * Renders [value] as a percentage. See [formatPercentText]. Not-a-number is written as `"NaN"` and
 * an infinity as `"∞"`, laid out as any other percentage; nothing here throws.
 */
internal fun formatPercent(
    value: Double,
    locale: Locale,
    precision: Int,
    outOfHundred: Boolean,
    separators: Separators,
): String =
    if (value.isFinite()) {
        formatPercentText(value.toString(), locale, precision, outOfHundred, separators)
    } else {
        writePercent(locale, separators) { style -> formatDecimal(value, precision, style) }
    }

/**
 * Renders the decimal number written in [text] as a percentage in [locale].
 *
 * @param text The output of a number's `toString()`, as [formatDecimalText] takes it.
 * @param precision The most decimal places the percentage shows.
 * @param outOfHundred `true` where the number is out of a hundred, `false` where it is a fraction
 *   to multiply by a hundred.
 */
internal fun formatPercentText(
    text: String,
    locale: Locale,
    precision: Int,
    outOfHundred: Boolean,
    separators: Separators,
): String = writePercent(locale, separators) { style ->
    formatDecimalText(if (outOfHundred) text else text.timesHundred(), precision, style)
}

/** Lays out what [number] writes in the locale's percent pattern. */
private fun writePercent(locale: Locale, separators: Separators, number: (NumberStyle) -> String): String {
    val pattern = percentPatternFor(locale)
    val style = percentStyleFor(numberStyleFor(locale), pattern)
    val written = number(style)
    // The minus sign is there only when the rounded number is below zero, so one that rounds to
    // zero takes the positive layout.
    val negative = written.startsWith(style.minus)
    val text = pattern.fill(written.removePrefix(style.minus), negative, style.minus, style.percent)
    return if (separators == Separators.Ascii) text.toAscii() else text
}
