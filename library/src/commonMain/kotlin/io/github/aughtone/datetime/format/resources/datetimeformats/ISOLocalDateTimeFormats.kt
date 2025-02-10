package io.github.aughtone.datetime.format.resources.datetimeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

// FIXME Check what formats ISO does have defined.
object ISOLocalDateTimeFormats : StyledDateTimeFormats<LocalDateTime> {
    /** 4:08 p.m. */
    override fun short(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format { LocalDateTime.Formats.ISO }

    /** 4:08:39 p.m. */
    override fun medium(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format { LocalDateTime.Formats.ISO }

    /** 4:08:39 p.m. EST */
    override fun long(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format { LocalDateTime.Formats.ISO }

    /** 4:08:39 p.m. Eastern Standard Time */
    override fun full(locale: Locale, timeZone: TimeZone?, twentyFourHour: Boolean): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format { LocalDateTime.Formats.ISO }
}
