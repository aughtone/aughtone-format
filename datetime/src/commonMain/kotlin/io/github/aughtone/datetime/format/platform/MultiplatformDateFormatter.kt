package io.github.aughtone.datetime.format.platform

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.Resources
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

object MultiplatformDateFormatter : InternalDateFormatter {
    override fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        languageTag: String,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): String? = if (dateStyle != DateTimeStyle.None && timeStyle != DateTimeStyle.None) {
        localDateTime.format(
            format = getLocalDateTimeFormat(
                dateStyle = dateStyle,
                timeStyle = timeStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    } else if (dateStyle != DateTimeStyle.None) {
        localDateTime.date.format(
            format = getLocalDateFormat(
                dateStyle = dateStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
            )
        )
    } else if (timeStyle != DateTimeStyle.None) {
        localDateTime.time.format(
            format = getLocalTimeFormat(
                timeStyle = timeStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    } else {
        // if both are NONE, then an empty string results
        ""
    }

    fun getLocalDateTimeFormat(
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
        date(
            getLocalDateFormat(
                dateStyle = dateStyle,
                locale = locale,
                timeZone = timeZone,
            )
        )
        char(' ')
        time(
            getLocalTimeFormat(
                timeStyle = timeStyle,
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    }

    fun getLocalDateFormat(
        dateStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
    ): DateTimeFormat<LocalDate> = when (dateStyle) {
        DateTimeStyle.Short -> Resources.getDateFormat(locale).short(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.Medium -> Resources.getDateFormat(locale).medium(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.Long -> Resources.getDateFormat(locale).long(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.Full -> Resources.getDateFormat(locale).full(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.None -> LocalDate.Format { chars("") }
    }

    fun getLocalTimeFormat(
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalTime> = when (timeStyle) {
        DateTimeStyle.Short -> Resources.getTimeFormat(locale).short(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = twentyFourHour,
        )

        DateTimeStyle.Medium ->
            Resources.getTimeFormat(locale).medium(
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour,
            )

        DateTimeStyle.Long ->
            Resources.getTimeFormat(locale).long(
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour,
            )

        DateTimeStyle.Full ->
            Resources.getTimeFormat(locale).full(
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour,
            )

        DateTimeStyle.None -> LocalTime.Format { chars("") }
    }
}
