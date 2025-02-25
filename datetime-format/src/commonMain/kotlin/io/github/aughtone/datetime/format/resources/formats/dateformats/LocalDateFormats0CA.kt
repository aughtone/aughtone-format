package io.github.aughtone.datetime.format.resources.formats.dateformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object LocalDateFormats0CA : StyledDateTimeFormats<LocalDate> {
    /** 1952-12-01 */
    override fun short(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            year()
            char('-')
            monthNumber(Padding.ZERO)
            char('-')
            dayOfMonth()
        }

    /** Jan 12, 1952 */
    override fun medium(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            monthName(Resources.getMonthNames(locale = locale, abbreviated = true))
            char(' ')
            dayOfMonth()
            chars(", ")
            year()
        }

    /** January 12, 1952 */
    override fun long(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            monthName(Resources.getMonthNames(locale = locale, abbreviated = false))
            char(' ')
            dayOfMonth()
            chars(", ")
            year()
        }

    /** Tuesday, January 12, 1952 AD */
    override fun full(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDate> =
        LocalDate.Format {
            dayOfWeek(Resources.getDayOfWeekNames(locale = locale, abbreviated = false))
            chars(", ")
            monthName(Resources.getMonthNames(locale = locale, abbreviated = false))
            char(' ')
            dayOfMonth()
            chars(", ")
            year()

            // XXX We can't tell here what the year is, and we can't make a formatter
            //  since the parent is a sealed class. For now we'll make an assumption
            //  that should work in most cases, but we'll need to deal with this in
            //  short order. Should probably file a RFE or Bug in kotlin-datetime:
            //  See: https://github.com/Kotlin/kotlinx-datetime
                // FIXME we can use the same post processing we use for time zones
            char(' ')
            chars(Resources.getEraNames(locale = locale, abbreviated = false).ce)
        }
}
