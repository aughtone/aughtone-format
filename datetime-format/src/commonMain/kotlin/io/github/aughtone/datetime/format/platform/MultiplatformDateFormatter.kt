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
    ): String? = if (dateStyle != DateTimeStyle.NONE && timeStyle != DateTimeStyle.NONE) {
        localDateTime.format(
            format = getLocalDateTimeFormat(
                dateStyle = dateStyle,
                timeStyle = timeStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    } else if (dateStyle != DateTimeStyle.NONE) {
        localDateTime.date.format(
            format = getLocalDateFormat(
                dateStyle = dateStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
            )
        )
    } else if (timeStyle != DateTimeStyle.NONE) {
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
        DateTimeStyle.SHORT -> Resources.getDateFormat(locale).short(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.MEDIUM -> Resources.getDateFormat(locale).medium(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.LONG -> Resources.getDateFormat(locale).long(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.FULL -> Resources.getDateFormat(locale).full(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.NONE -> LocalDate.Format { chars("") }
    }

    fun getLocalTimeFormat(
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalTime> = when (timeStyle) {
        DateTimeStyle.SHORT -> Resources.getTimeFormat(locale).short(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = twentyFourHour,
        )

        DateTimeStyle.MEDIUM ->
            Resources.getTimeFormat(locale).medium(
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour,
            )

        DateTimeStyle.LONG ->
            Resources.getTimeFormat(locale).long(
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour,
            )

        DateTimeStyle.FULL ->
            Resources.getTimeFormat(locale).full(
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour,
            )

        DateTimeStyle.NONE -> LocalTime.Format { chars("") }
    }
}
