package io.github.aughtone.datetime.format.resources.timeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object CALocalTimeFormats : StyledDateTimeFormats<LocalTime> {
    /** 4:08 p.m. */
    override fun short(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.ZERO)
            char(':')
            minute(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                amPmMarker(
                    am = "a.m.",
                    pm = "p.m."
                ) // else amPmMarker(am = "am", pm = "pm")
            }
        }

    /** 4:08:39 p.m. */
    override fun medium(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.ZERO)
            char(':')
            minute(Padding.ZERO)
            char(':')
            second(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                amPmMarker(
                    am = "a.m.",
                    pm = "p.m."
                ) // else amPmMarker(am = "am", pm = "pm")
            }
        }

    /** 4:08:39 p.m. EST */
    override fun long(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.ZERO)
            char(':')
            minute(Padding.ZERO)
            char(':')
            second(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                amPmMarker(
                    am = "a.m.",
                    pm = "p.m."
                ) // else amPmMarker(am = "am", pm = "pm")
            }
            timeZone?.let {
                char(' ')
                chars(it.id) //XXX Needs mapping to a TZ Code, Maybe optional for a local time.
            }

        }

    /** 4:08:39 p.m. Eastern Standard Time */
    override fun full(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format {
            if (twentyFourHour) hour(Padding.ZERO) else amPmHour(Padding.ZERO)
            char(':')
            minute(Padding.ZERO)
            char(':')
            second(Padding.ZERO)
            if (!twentyFourHour) {
                char(' ')
                amPmMarker(
                    am = "a.m.",
                    pm = "p.m."
                ) // else amPmMarker(am = "am", pm = "pm")
            }
            timeZone?.let {
                char(' ')
                chars(it.id) //XXX Needs mapping to a TZ name, Maybe optional for a local time.
            }
        }
}
