package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.lookup.LocaleAwareDayOfWeekNames
import io.github.aughtone.datetime.format.resources.LocaleAwareLegacyFormats
import io.github.aughtone.datetime.format.lookup.LocaleAwareMonthNames
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.char

/**
 *
 * This is the format string syntax used by the Java Time's DateTimeFormatter class, Swift's and Objective-C's NSDateFormatter class, and the ICU library.
 * The syntax is specified at: [https://unicode-org.github.io/icu/userguide/format_parse/datetime/#datetime-format-syntax](https://unicode-org.github.io/icu/userguide/format_parse/datetime/#datetime-format-syntax).
 *
 * In addition to the standard syntax, this function also supports optional sections by surrounding them in `[]`. For example, `hh:mm[:ss]`.
 *
 * This uses the KMP [byUnicodePattern] function and the full documentation can be found there.
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.formatAs(pattern: String): String =
    LocalDateTime.Format { byUnicodePattern(pattern) }.format(this)

fun LocalDateTime.formatAs(
    style: FormatStyle = FormatStyle.ISO,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = when (style) {
    FormatStyle.SHORT -> LocalDateTime.Format { LocaleAwareLegacyFormats.DateTime.short(locale = locale) }
        .format(this)

    FormatStyle.MEDIUM -> LocalDateTime.Format { LocaleAwareLegacyFormats.DateTime.medium(locale = locale) }
        .format(this)

    FormatStyle.LONG -> LocalDateTime.Format { LocaleAwareLegacyFormats.DateTime.long(locale = locale) }
        .format(this)

    FormatStyle.FULL -> LocalDateTime.Format { LocaleAwareLegacyFormats.DateTime.full(locale = locale) }
        .format(this)

    FormatStyle.ISO -> LocalDateTime.Format { LocalDateTime.Formats.ISO }.format(this)
}

fun LocalDateTime.formatSimple(
    style: FormatStyle = FormatStyle.SHORT,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    locale: Locale = Locale.current,
    twentyFourHour: Boolean = false,
): String = when (style) {
    FormatStyle.SHORT -> format(format = LocalDateTime.Format {
        year()
        char('-')
        monthNumber(Padding.ZERO)
        char('-')
        dayOfMonth()
        char(' ')
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
    })

    FormatStyle.MEDIUM -> format(format = LocalDateTime.Format {
        monthName(LocaleAwareMonthNames.lookup(locale = locale, abbreviated = true))
        char(' ')
        dayOfMonth()
        chars(", ")
        year()
        char(' ')
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
    })

    FormatStyle.LONG -> format(format = LocalDateTime.Format {
        monthName(
            LocaleAwareMonthNames.lookup(
                locale = locale, abbreviated = false
            )
        )
        char(' ')
        dayOfMonth()
        chars(", ")
        year()
        char(' ')
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
        char(' ')
        chars(timeZone.id) //XXX Needs mapping to a TZ Code, Maybe optional for a local time.
    })

    FormatStyle.FULL -> format(format = LocalDateTime.Format {
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
        // FIXME We don't seem to have a way to ass teh Era, or know what the numeric years to calculate it.
        chars(" AD")
        char(' ')
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
        char(' ')
        chars(timeZone.id) //XXX Needs mapping to a TZ name, Maybe optional for a local time.
    })// Tuesday, February 4, 2025 at 4:08:39 PM Eastern Standard Time

    FormatStyle.ISO -> format(format = LocalDateTime.Formats.ISO)
}
