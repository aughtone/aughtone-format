package io.github.aughtone.datetime.format

import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

internal fun DateTimeFormatBuilder.WithTime.appendPattern(pattern: String) {
    val cleanPattern = pattern.replace(Regex("[zZva]+"), "").trim()
    var i = 0
    while (i < cleanPattern.length) {
        val char = cleanPattern[i]
        val count = cleanPattern.substring(i).takeWhile { it == char }.count()
        when (char) {
            'h' -> amPmHour(if (count > 1) Padding.ZERO else Padding.NONE)
            'H' -> hour(if (count > 1) Padding.ZERO else Padding.NONE)
            'm' -> minute(if (count > 1) Padding.ZERO else Padding.NONE)
            's' -> second(if (count > 1) Padding.ZERO else Padding.NONE)
            'S' -> secondFraction(fixedLength = count)
            ' ' -> this.char(' ')
            ':' -> this.char(':')
            '.' -> this.char('.')
            else -> {
                // Ignore other characters
            }
        }
        i += count
    }
}
