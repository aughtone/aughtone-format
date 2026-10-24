package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.formats.AmPmStrings
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
    appendTimeTokens(tokenizePattern(pattern).withoutFields { it.letter in "zZva" })
}

/**
 * Appends time [tokens] as they stand, with no tidying of the literal text around them.
 *
 * A day-period field (`a`) becomes the AM/PM marker when [amPm] is given and is left out
 * otherwise; zone fields are left out. `h` and `K` are the 12-hour hour, `H` and `k` the
 * 24-hour one. Any other letter is ignored.
 */
internal fun DateTimeFormatBuilder.WithTime.appendTimeTokens(tokens: List<PatternToken>, amPm: AmPmStrings? = null) {
    for (token in tokens) {
        when (token) {
            is PatternToken.Literal -> chars(token.text)
            is PatternToken.Field -> when (token.letter) {
                'h', 'K' -> amPmHour(if (token.count > 1) Padding.ZERO else Padding.NONE)
                'H', 'k' -> hour(if (token.count > 1) Padding.ZERO else Padding.NONE)
                'm' -> minute(if (token.count > 1) Padding.ZERO else Padding.NONE)
                's' -> second(if (token.count > 1) Padding.ZERO else Padding.NONE)
                'S' -> secondFraction(fixedLength = token.count)
                'a' -> if (amPm != null) amPmMarker(am = amPm.am, pm = amPm.pm)
                else -> Unit
            }
        }
    }
}
