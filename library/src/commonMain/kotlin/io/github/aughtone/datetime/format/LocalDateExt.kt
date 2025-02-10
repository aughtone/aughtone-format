package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.lookup.LocaleAwareDayOfWeekNames
import io.github.aughtone.datetime.format.resources.LocaleAwareLegacyFormats
import io.github.aughtone.datetime.format.lookup.LocaleAwareMonthNames
import kotlinx.datetime.LocalDate
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
fun LocalDate.formatAs(pattern: String): String =
    LocalDate.Format { byUnicodePattern(pattern) }.format(this)

fun LocalDate.formatAs(
    style: FormatStyle = FormatStyle.ISO,
    locale: Locale = Locale.current,
): String = when (style) {
    FormatStyle.SHORT -> LocalDate.Format { LocaleAwareLegacyFormats.Date.short(locale = locale) }
        .format(this)

    FormatStyle.MEDIUM -> LocalDate.Format { LocaleAwareLegacyFormats.Date.medium(locale = locale) }
        .format(this)

    FormatStyle.LONG -> LocalDate.Format { LocaleAwareLegacyFormats.Date.long(locale = locale) }
        .format(this)

    FormatStyle.FULL -> LocalDate.Format { LocaleAwareLegacyFormats.Date.full(locale = locale) }
        .format(this)

    FormatStyle.ISO -> LocalDate.Format { LocalDate.Formats.ISO }.format(this)
}


fun LocalDate.formatSimple(
    style: FormatStyle = FormatStyle.SHORT,
    locale: Locale = Locale.current,
): String = when (style) {
    FormatStyle.SHORT -> format(format = LocalDate.Format {
        year()
        char('-')
        monthNumber(Padding.ZERO)
        char('-')
        dayOfMonth()
    })

    FormatStyle.MEDIUM -> format(format = LocalDate.Format {
        monthName(LocaleAwareMonthNames.lookup(locale = locale, abbreviated = true))
        char(' ')
        dayOfMonth()
        chars(", ")
        year()
    })

    FormatStyle.LONG -> format(format = LocalDate.Format {
        monthName(
            LocaleAwareMonthNames.lookup(
                locale = locale, abbreviated = false
            )
        )
        char(' ')
        dayOfMonth()
        chars(", ")
        year()
    })

    FormatStyle.FULL -> format(format = LocalDate.Format {
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
    })// Tuesday, February 4, 2025 at 4:08:39 PM Eastern Standard Time

    FormatStyle.ISO -> format(format = LocalDate.Formats.ISO)
}
