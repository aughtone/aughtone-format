package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

actual object PlatformDateFormatter:InternalDateFormatter {
    actual override fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        languageTag: String,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): String? = if (dateStyle != DateTimeStyle.NONE && timeStyle != DateTimeStyle.NONE) {
        localDateTime.toJavaLocalDateTime().format(
            DateTimeFormatter.ofLocalizedDateTime(
                getJavaStyle(style = dateStyle),
                getJavaStyle(style = timeStyle)
            ).withZone(timeZone.toJavaZoneId()).withLocale(Locale.forLanguageTag(languageTag))
        )
    } else if (dateStyle != DateTimeStyle.NONE) {
        localDateTime.toJavaLocalDateTime().format(
            DateTimeFormatter.ofLocalizedDate(
                getJavaStyle(style = dateStyle),
            )
        )
    } else if (timeStyle != DateTimeStyle.NONE) {
        localDateTime.toJavaLocalDateTime().format(
            DateTimeFormatter.ofLocalizedTime(
                getJavaStyle(style = dateStyle),
            )
        )
    } else {
        // if both are NONE, do we return an empty string, or throw and exception?
        null
    }

    private fun getJavaStyle(style: DateTimeStyle): FormatStyle? = when (style) {
        DateTimeStyle.SHORT -> FormatStyle.SHORT
        DateTimeStyle.MEDIUM -> FormatStyle.MEDIUM
        DateTimeStyle.LONG -> FormatStyle.LONG
        DateTimeStyle.FULL -> FormatStyle.FULL
        DateTimeStyle.NONE -> null
    }

}
