package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding

/**
 * Appends the date fields and literal text of a CLDR-style [pattern].
 *
 * The era field `G` is left out here; the era is added after formatting, when the date is known.
 *
 * @param twoDigitYearBase First year of the century that `yy` abbreviates. kotlinx-datetime prints
 *   a two-digit year only inside one hundred-year window and writes any other year in full with a
 *   sign, so the caller passes the century of the date being formatted.
 * @throws IllegalArgumentException if [pattern] uses a letter this builder has no field for. The
 *   bundled patterns are covered by tests, so this guards a mistake in the tables.
 */
internal fun DateTimeFormatBuilder.WithDate.appendPattern(
    pattern: String,
    dayOfWeekNames: DayOfWeekNamesResource,
    monthNames: MonthNamesResource,
    twoDigitYearBase: Int = 2000,
) {
    appendDateTokens(tokenizePattern(pattern).withoutFields { it.letter == 'G' }, dayOfWeekNames, monthNames, twoDigitYearBase)
}

/**
 * Appends date [tokens] as they stand, with no tidying of the literal text around them. The
 * fields are those [appendPattern] accepts.
 */
internal fun DateTimeFormatBuilder.WithDate.appendDateTokens(
    tokens: List<PatternToken>,
    dayOfWeekNames: DayOfWeekNamesResource,
    monthNames: MonthNamesResource,
    twoDigitYearBase: Int = 2000,
) {
    for (token in tokens) {
        when (token) {
            is PatternToken.Literal -> chars(token.text)
            is PatternToken.Field -> when (token.letter) {
                'y' -> when {
                    token.count == 2 -> yearTwoDigits(twoDigitYearBase)
                    token.count >= 4 -> year(Padding.ZERO)
                    // A bare "y" is the year as it is: 800, not 0800.
                    else -> year(Padding.NONE)
                }
                // "L" is the stand-alone month and "c" the stand-alone weekday.
                'M', 'L' -> when (token.count) {
                    1 -> monthNumber(Padding.NONE)
                    2 -> monthNumber(Padding.ZERO)
                    3 -> monthName(monthNames.abbreviated)
                    else -> monthName(monthNames.full)
                }
                'd' -> when (token.count) {
                    1 -> day(Padding.NONE)
                    else -> day(Padding.ZERO)
                }
                'E', 'c' -> when (token.count) {
                    1, 2, 3 -> dayOfWeek(dayOfWeekNames.abbreviated)
                    else -> dayOfWeek(dayOfWeekNames.full)
                }
                else -> throw IllegalArgumentException("Unrecognized pattern character: ${token.letter}")
            }
        }
    }
}
