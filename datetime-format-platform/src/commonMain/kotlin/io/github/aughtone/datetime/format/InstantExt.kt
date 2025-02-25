package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.PlatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun Instant.systemFormat(
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    timeStyle: DateTimeStyle = DateTimeStyle.SHORT,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = PlatformDateFormatter.formatDateTime(
    localDateTime = toLocalDateTime(timeZone=timeZone),
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
) ?: toString()
