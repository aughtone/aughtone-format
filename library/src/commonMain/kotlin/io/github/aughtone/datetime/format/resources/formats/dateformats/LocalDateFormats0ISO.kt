package io.github.aughtone.datetime.format.resources.timeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

// FIXME Check what formats NONE does have defined.
object LocalDateFormats0ISO : StyledDateTimeFormats<LocalDate> {
    /** 4:08 p.m. */
    override fun short(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format { LocalDate.Formats.ISO }

    /** 4:08:39 p.m. */
    override fun medium(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format { LocalDate.Formats.ISO }

    /** 4:08:39 p.m. EST */
    override fun long(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format { LocalDate.Formats.ISO }

    /** 4:08:39 p.m. Eastern Standard Time */
    override fun full(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDate> =
        LocalDate.Format { LocalDate.Formats.ISO }
}
