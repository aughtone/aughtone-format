package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

interface InternalDateFormatter {
    fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        languageTag: String,
        timeZone: TimeZone = TimeZone.currentSystemDefault(),
        twentyFourHour: Boolean = false,
    ): String?
}
