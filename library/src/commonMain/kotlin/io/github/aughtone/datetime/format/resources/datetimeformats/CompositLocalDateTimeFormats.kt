package io.github.aughtone.datetime.format.resources.datetimeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.lookup.LocaleAwareDateFormats
import io.github.aughtone.datetime.format.lookup.LocaleAwareTimeFormats
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.timeformats.LocalDateFormats0ISO
import io.github.aughtone.datetime.format.resources.timeformats.LocalTimeFormats0ISO
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
                    fallbackTo = LocalDateFormats0ISO
                ).short(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = LocalTimeFormats0ISO
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
                    fallbackTo = LocalDateFormats0ISO
                ).medium(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = LocalTimeFormats0ISO
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
                    fallbackTo = LocalDateFormats0ISO
                ).long(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = LocalTimeFormats0ISO
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
                    fallbackTo = LocalDateFormats0ISO
                ).full(locale, timeZone, twentyFourHour)
            )
            char(' ')
            time(
                format = LocaleAwareTimeFormats.lookup(
                    locale = locale,
                    abbreviated = false,
                    fallbackTo = LocalTimeFormats0ISO
                ).full(locale, timeZone, twentyFourHour)
            )
        }
}
