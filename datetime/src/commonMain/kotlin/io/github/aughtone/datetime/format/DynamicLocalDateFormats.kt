package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.MonthNamesData
import io.github.aughtone.datetime.format.resources.formats.localeFormatMonthNames
import io.github.aughtone.datetime.format.resources.formats.DatePatterns
import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

internal object DynamicLocalDateFormats {

    private fun getPatterns(locale: Locale): DatePatterns = Resources.getDatePatterns(locale)

    private fun getDayOfWeekNamesResource(locale: Locale): DayOfWeekNamesResource =
        Resources.getDayOfWeekNamesResource(locale)

    /**
     * The month names for use inside a date: the locale's own table, with the full names swapped
     * for the in-date form where the language has one that differs.
     */
    private fun getMonthNamesResource(locale: Locale): MonthNamesResource {
        val names = Resources.getMonthNamesResource(locale)
        val inDate = localeFormatMonthNames[locale.languageCode] ?: return names
        return MonthNamesData(full = inDate, abbreviated = names.abbreviated)
    }

    fun short(locale: Locale, timeZone: TimeZone, twoDigitYearBase: Int = 2000): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.short, dayOfWeekNames, monthNames, twoDigitYearBase)
        }
    }

    fun medium(locale: Locale, timeZone: TimeZone, twoDigitYearBase: Int = 2000): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.medium, dayOfWeekNames, monthNames, twoDigitYearBase)
        }
    }

    fun long(locale: Locale, timeZone: TimeZone, twoDigitYearBase: Int = 2000): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.long, dayOfWeekNames, monthNames, twoDigitYearBase)
        }
    }

    fun full(locale: Locale, timeZone: TimeZone, twoDigitYearBase: Int = 2000): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.full, dayOfWeekNames, monthNames, twoDigitYearBase)
        }
    }
}
