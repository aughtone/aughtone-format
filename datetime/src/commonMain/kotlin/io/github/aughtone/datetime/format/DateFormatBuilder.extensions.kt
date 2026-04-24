package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

internal fun DateTimeFormatBuilder.WithDate.appendPattern(
    pattern: String,
    dayOfWeekNames: DayOfWeekNamesResource,
    monthNames: MonthNamesResource
) {
    var i = 0
    while (i < pattern.length) {
        val char = pattern[i]
        val count = pattern.substring(i).takeWhile { it == char }.count()
        when (char) {
            'y' -> when (count) {
                2 -> yearTwoDigits(2000)
                else -> year()
            }
            'M' -> when (count) {
                1 -> monthNumber(Padding.NONE)
                2 -> monthNumber(Padding.ZERO)
                3 -> monthName(monthNames.abbreviated)
                else -> monthName(monthNames.full)
            }
            'd' -> when (count) {
                1 -> day(Padding.NONE)
                else -> day(Padding.ZERO)
            }
            'E' -> when (count) {
                1, 2, 3 -> dayOfWeek(dayOfWeekNames.abbreviated)
                else -> dayOfWeek(dayOfWeekNames.full)
            }
            'G' -> {
                // kotlinx-datetime does not have a built-in era formatter.
                // This is a placeholder.
            }
            ',' -> this.char(',')
            '/' -> this.char('/')
            ' ' -> this.char(' ')
            else -> {
                if ((char in 'a'..'z') || (char in 'A'..'Z')) {
                    throw IllegalArgumentException("Unrecognized pattern character: $char in $pattern")
                }
                this.char(char)
            }
        }
        i += count
    }
}
