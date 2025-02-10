package io.github.aughtone.datetime.format.resources.datetimeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.lookup.LocaleAwareDateFormats
import io.github.aughtone.datetime.format.lookup.LocaleAwareTimeFormats
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.timeformats.ISOLocalDateFormats
import io.github.aughtone.datetime.format.resources.timeformats.ISOLocalTimeFormats
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

object CompositLocalDateTimeFormats : StyledDateTimeFormats<LocalDateTime> {
    override fun short(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format {
            date(
                format = LocaleAwareDateFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalDateFormats
                ).short(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalTimeFormats
                ).short(locale, timeZone, twentyFourHour)
            )
        }

    override fun medium(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format {
            date(
                format = LocaleAwareDateFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalDateFormats
                ).medium(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalTimeFormats
                ).medium(locale, timeZone, twentyFourHour)
            )
        }

    override fun long(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format {
            date(
                format = LocaleAwareDateFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalDateFormats
                ).long(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalTimeFormats
                ).long(locale, timeZone, twentyFourHour)
            )
        }

    override fun full(
        locale: Locale,
        timeZone: TimeZone?,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> =
        LocalDateTime.Format {
            date(
                format = LocaleAwareDateFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalDateFormats
                ).full(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = ISOLocalTimeFormats
                ).full(locale, timeZone, twentyFourHour)
            )
        }
}
