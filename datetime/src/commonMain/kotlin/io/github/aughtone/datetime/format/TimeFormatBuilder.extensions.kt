package io.github.aughtone.datetime.format

import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding

/**
 * Appends the time fields and literal text of a CLDR-style [pattern].
 *
 * The zone (`z`, `Z`, `v`) and day-period (`a`) fields are left out, along with any parentheses
 * around them: the caller adds the AM/PM marker and the zone name after formatting. A letter this
 * builder has no field for is ignored.
 */
internal fun DateTimeFormatBuilder.WithTime.appendPattern(pattern: String) {
    for (token in tokenizePattern(pattern).withoutFields { it.letter in "zZva" }) {
        when (token) {
            is PatternToken.Literal -> chars(token.text)
            is PatternToken.Field -> when (token.letter) {
                'h' -> amPmHour(if (token.count > 1) Padding.ZERO else Padding.NONE)
                'H' -> hour(if (token.count > 1) Padding.ZERO else Padding.NONE)
                'm' -> minute(if (token.count > 1) Padding.ZERO else Padding.NONE)
                's' -> second(if (token.count > 1) Padding.ZERO else Padding.NONE)
                'S' -> secondFraction(fixedLength = token.count)
                else -> Unit
            }
        }
    }
}
