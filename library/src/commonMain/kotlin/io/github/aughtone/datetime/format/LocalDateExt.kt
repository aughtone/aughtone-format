package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.PlatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

fun LocalDate.formatAs(
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = PlatformDateFormatter.formatDateTime(
    localDateTime = atStartOfDayIn(timeZone = timeZone).toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = DateTimeStyle.NONE,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
) ?: toString()
