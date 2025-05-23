package io.github.aughtone.datetime.format.resources.formats.timeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

// FIXME Check what formats NONE does have defined.
object LocalTimeFormats0ISO : StyledDateTimeFormats<LocalTime> {
    /** 4:08 p.m. */
    override fun short(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format { LocalTime.Formats.ISO }

    /** 4:08:39 p.m. */
    override fun medium(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format { LocalTime.Formats.ISO }

    /** 4:08:39 p.m. EST */
    override fun long(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format { LocalTime.Formats.ISO }

    /** 4:08:39 p.m. Eastern Standard Time */
    override fun full(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalTime> =
        LocalTime.Format { LocalTime.Formats.ISO }
}
