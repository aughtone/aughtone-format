package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.FormatStyle
import io.github.aughtone.datetime.format.resources.Resources
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

object MultiplatformDateFormatter : InternalDateFormatter {
    override fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): String? {
        val formattedDate = if (dateStyle != DateTimeStyle.None) {
            localDateTime.date.format(
                format = getLocalDateFormat(
                    dateStyle = dateStyle,
                    locale = locale,
                    timeZone = timeZone,
                )
            ).let {
                MultiplatformPostFormatter.postFormatDate(
                    dateStyle = dateStyle,
                    locale = locale,
                    date = localDateTime.date,
                    formattedDate = it
                )
            }
        } else null

        val formattedTime = if (timeStyle != DateTimeStyle.None) {
            localDateTime.time.format(
                format = getLocalTimeFormat(
                    timeStyle = timeStyle,
                    locale = locale,
                    timeZone = timeZone,
                    twentyFourHour = twentyFourHour
                )
            ).let {
                MultiplatformPostFormatter.postFormatTime(
                    timeStyle = timeStyle,
                    timeZone = timeZone,
                    instant = localDateTime.toInstant(timeZone),
                    formatedTime = it
                )
            }
        } else null

        return when {
            formattedDate != null && formattedTime != null -> "$formattedDate $formattedTime"
            formattedDate != null -> formattedDate
            formattedTime != null -> formattedTime
            else -> ""
        }
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
        DateTimeStyle.Short -> Resources.getDateFormat(
            locale = locale,
            timeZone = timeZone,
            style = FormatStyle.SHORT
        )

        DateTimeStyle.Medium -> Resources.getDateFormat(
            locale = locale,
            timeZone = timeZone,
            style = FormatStyle.MEDIUM
        )

        DateTimeStyle.Long -> Resources.getDateFormat(
            locale = locale,
            timeZone = timeZone,
            style = FormatStyle.LONG
        )

        DateTimeStyle.Full -> Resources.getDateFormat(
            locale = locale,
            timeZone = timeZone,
            style = FormatStyle.FULL
        )

        DateTimeStyle.None -> LocalDate.Format { chars("") }
    }

    fun getLocalTimeFormat(
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalTime> = when (timeStyle) {
        DateTimeStyle.Short -> Resources.getTimeFormat(
            locale = locale,
            timeZone = timeZone,
            style = FormatStyle.SHORT,
            twentyFourHour = twentyFourHour
        )

        DateTimeStyle.Medium ->
            Resources.getTimeFormat(
                locale = locale,
                timeZone = timeZone,
                style = FormatStyle.MEDIUM,
                twentyFourHour = twentyFourHour
            )

        DateTimeStyle.Long ->
            Resources.getTimeFormat(
                locale = locale,
                timeZone = timeZone,
                style = FormatStyle.LONG,
                twentyFourHour = twentyFourHour
            )

        DateTimeStyle.Full ->
            Resources.getTimeFormat(
                locale = locale,
                timeZone = timeZone,
                style = FormatStyle.FULL,
                twentyFourHour = twentyFourHour
            )

        DateTimeStyle.None -> LocalTime.Format { chars("") }
    }
}
