package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.platform.PlatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.todayIn


fun LocalTime.formatWith(
    timeStyle: DateTimeStyle = DateTimeStyle.SHORT,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = PlatformDateFormatter.formatDateTime(
    localDateTime = atDate(Clock.System.todayIn(timeZone = timeZone)),
    dateStyle = DateTimeStyle.NONE,
    timeStyle = timeStyle,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
) ?: MultiplatformDateFormatter.formatDateTime(
    localDateTime = atDate(Clock.System.todayIn(timeZone = timeZone)),
    dateStyle = DateTimeStyle.NONE,
    timeStyle = timeStyle,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
)?: toString()
