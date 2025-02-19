package io.github.aughtone.datetime.format.platform

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.lookup.LocaleAwareDateFormats
import io.github.aughtone.datetime.format.lookup.LocaleAwareMonthNames
import io.github.aughtone.datetime.format.lookup.LocaleAwareTimeFormats
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.Padding
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
            format = buildLocalDateTimeFormat(
                dateStyle = dateStyle,
                timeStyle = timeStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    } else if (dateStyle != DateTimeStyle.NONE) {
        localDateTime.date.format(
            format = buildLocalDateFormat(
                dateStyle = dateStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
            )
        )
    } else if (timeStyle != DateTimeStyle.NONE) {
        localDateTime.time.format(
            format = buildLocalTimeFormat(
                timeStyle = timeStyle,
                locale = Locale(languageTag),
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    } else {
        // if both are NONE, do we return an empty string, or throw and exception?
        null
    }

    private fun buildLocalDateTimeFormat(
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
        date(
            buildLocalDateFormat(
                dateStyle = dateStyle,
                locale = locale,
                timeZone = timeZone,
            )
        )
        char(' ')
        time(
            buildLocalTimeFormat(
                timeStyle = timeStyle,
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    }

    private fun buildLocalDateFormat(
        dateStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
    ): DateTimeFormat<LocalDate> = when (dateStyle) {
        DateTimeStyle.SHORT -> LocaleAwareDateFormats.lookup(
            locale = locale,
            abbreviated = true,
            fallbackTo = null
        ).short(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.MEDIUM -> LocaleAwareDateFormats.lookup(
            locale = locale,
            abbreviated = true,
            fallbackTo = null
        ).medium(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.LONG -> LocaleAwareDateFormats.lookup(
            locale = locale,
            abbreviated = false,
            fallbackTo = null
        ).long(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.FULL -> LocaleAwareDateFormats.lookup(
            locale = locale,
            abbreviated = false,
            fallbackTo = null
        ).full(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = false,
        )

        DateTimeStyle.NONE -> LocalDate.Format { chars("") }
    }

    private fun buildLocalTimeFormat(
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalTime> = when (timeStyle) {
        DateTimeStyle.SHORT -> LocaleAwareTimeFormats.lookup(
            locale = locale,
            abbreviated = true,
            fallbackTo = null
        ).short(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = twentyFourHour
        )

        DateTimeStyle.MEDIUM -> LocaleAwareTimeFormats.lookup(
            locale = locale,
            abbreviated = true,
            fallbackTo = null
        ).medium(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = twentyFourHour
        )

        DateTimeStyle.LONG -> LocaleAwareTimeFormats.lookup(
            locale = locale,
            abbreviated = false,
            fallbackTo = null
        ).long(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = twentyFourHour
        )

        DateTimeStyle.FULL -> LocaleAwareTimeFormats.lookup(
            locale = locale,
            abbreviated = false,
            fallbackTo = null
        ).full(
            locale = locale,
            timeZone = timeZone,
            twentyFourHour = twentyFourHour
        )

        DateTimeStyle.NONE -> LocalTime.Format { chars("") }
    }
}
