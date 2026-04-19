package io.github.aughtone.datetime.format

import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

internal fun DateTimeFormatBuilder.WithTime.appendPattern(pattern: String) {
    var i = 0
    while (i < pattern.length) {
        val char = pattern[i]
        val count = pattern.substring(i).takeWhile { it == char }.count()
        when (char) {
            'h' -> amPmHour(if (count > 1) Padding.ZERO else Padding.NONE)
            'H' -> hour(if (count > 1) Padding.ZERO else Padding.NONE)
            'm' -> minute(if (count > 1) Padding.ZERO else Padding.NONE)
            's' -> second(if (count > 1) Padding.ZERO else Padding.NONE)
            'S' -> secondFraction(fixedLength = count)
            ' ' -> this.char(' ')
            ':' -> this.char(':')
            'a' -> {
                // 'a' is handled by DynamicLocalTimeFormats, not here. We simply ignore it.
            }
            'z' -> {
                // Timezone 'z' and 'zzzz' are part of CLDR time patterns,
                // but kotlinx-datetime's LocalTime.Format cannot directly format timezones.
                // We will ignore it here as LocalTime does not contain timezone information.
            }

            else -> {
                // For now, ignore timezone 'z' and other characters
            }
        }
        i += count
    }
}
