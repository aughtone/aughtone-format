package io.github.aughtone.datetime.format.platform

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

interface InternalDateFormatter {
    fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        languageTag: String,
        timeZone: TimeZone = TimeZone.currentSystemDefault(),
        twentyFourHour: Boolean = is24HourFormat(Locale(languageTag)),
    ): String?
}
