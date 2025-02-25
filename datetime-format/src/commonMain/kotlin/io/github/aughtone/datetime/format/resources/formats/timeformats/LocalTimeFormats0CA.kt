package io.github.aughtone.datetime.format.resources.formats.timeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object LocalTimeFormats0CA : StyledDateTimeFormats<LocalTime> {
    /** 4:08 p.m. */
    override fun short(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.NONE)
            char(':')
            minute(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                getLocaleMarker(locale) // else amPmMarker(am = "am", pm = "pm")
            }
        }
    /** 04:08:39 p.m. */
    override fun medium(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.NONE)
            char(':')
            minute(Padding.ZERO)
            char(':')
            second(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                getLocaleMarker(locale)
            }
        }

    /** 4:08:39 p.m. EST */
    override fun long(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.NONE)
            char(':')
            minute(Padding.ZERO)
            char(':')
            second(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                getLocaleMarker(locale)
            }
            // we need the instant to figure out the time zone name instead of the id, to this
            // happens in the post formatter instead.
//            timeZone?.let {
//                char(' ')
//                //XXX Needs mapping to a TZ Code, Maybe optional for a local time.
//                // The problem is that you need to time for format the zone its in,
//                // and we don't have access here. WE may need to append this after the
//                // rest is format.
//                chars(it.toString())
//            }

        }

    /** 4:08:39 p.m. Eastern Standard Time */
    override fun full(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.NONE)
            char(':')
            minute(Padding.ZERO)
            char(':')
            second(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                getLocaleMarker(locale)
            }
            // we need the instant to figure out the time zone name instead of the id, to this
            // happens in the post formatter instead.
//            timeZone?.let {
//                char(' ')
//                chars(it.id) //XXX Needs mapping to a TZ name, Maybe optional for a local time.
//            }
        }
}
