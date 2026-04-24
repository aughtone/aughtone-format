package io.github.aughtone.datetime.format.native.platform

import io.github.aughtone.datetime.format.native.DateTimeStyle
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

actual object PlatformDateFormatter:InternalDateFormatter {
    actual override fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        languageTag: String,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): String? = null

}
