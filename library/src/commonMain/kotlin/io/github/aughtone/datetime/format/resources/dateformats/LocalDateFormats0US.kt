package io.github.aughtone.datetime.format.resources.dateformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.lookup.LocaleAwareDayOfWeekNames
import io.github.aughtone.datetime.format.lookup.LocaleAwareEraNames
import io.github.aughtone.datetime.format.lookup.LocaleAwareMonthNames
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object LocalDateFormats0US : StyledDateTimeFormats<LocalDate> {
    /** 12/31/1952 */
    override fun short(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            monthNumber(Padding.ZERO)
            char('/')
            dayOfMonth()
            char('/')
            year()
        }

    /** Jan 12, 1952 */
    override fun medium(locale: Locale,timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
            LocalDate.Format {
                monthName(LocaleAwareMonthNames.lookup(locale = locale, abbreviated = true))
                char(' ')
                dayOfMonth()
                chars(", ")
                year()
            }

    /** January 12, 1952 */
    override fun long(locale: Locale,timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            monthName(
                LocaleAwareMonthNames.lookup(
                    locale = locale, abbreviated = false
                )
            )
            char(' ')
            dayOfMonth()
            chars(", ")
            year()
        }

    /** 4:08:39 p.m. Eastern Standard Time */
    override fun full(locale: Locale,timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            dayOfWeek(
                LocaleAwareDayOfWeekNames.lookup(
                    locale = locale, abbreviated = false
                )
            )
            chars(", ")
            monthName(
                LocaleAwareMonthNames.lookup(
                    locale = locale, abbreviated = false
                )
            )
            char(' ')
            dayOfMonth()
            chars(", ")
            year()

            // XXX We can't tell here what the year is, and we can't make a formatter
            //  since the parent is a sealed class. For now we'll make an assumption
            //  that should work in most cases, but we'll need to deal with this in
            //  short order. Should probably file a RFE or Bug in kotlin-datetime:
            //  See: https://github.com/Kotlin/kotlinx-datetime
            char(' ')
            chars(LocaleAwareEraNames.lookup(locale=locale).ce)

        }

}
