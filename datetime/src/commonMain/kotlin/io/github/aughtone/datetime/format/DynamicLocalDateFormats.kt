package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.DayOfWeekNamesData
import io.github.aughtone.datetime.format.resources.formats.MonthNamesData
import io.github.aughtone.datetime.format.resources.formats.localeInDateNames
import io.github.aughtone.datetime.format.resources.formats.DatePatterns
import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames

internal object DynamicLocalDateFormats {

    private fun getPatterns(locale: Locale): DatePatterns = Resources.getDatePatterns(locale)

    /**
     * The weekday names for use inside a date: the locale's own table, with any set the language
     * writes differently inside a date (in lower case, in Spanish) swapped for that form.
     */
    internal fun getDayOfWeekNamesResource(locale: Locale): DayOfWeekNamesResource {
        val names = Resources.getDayOfWeekNamesResource(locale)
        val inDate = localeInDateNames[locale.languageCode] ?: return names
        if (inDate.daysFull == null && inDate.daysAbbreviated == null) return names
        return DayOfWeekNamesData(
            full = inDate.daysFull?.let(::DayOfWeekNames) ?: names.full,
            abbreviated = inDate.daysAbbreviated?.let(::DayOfWeekNames) ?: names.abbreviated,
        )
    }

    /**
     * The month names for use inside a date: the locale's own table, with any set the language
     * writes differently inside a date — in lower case ("5 de marzo"), or in another grammatical
     * form ("5 марта") — swapped for that form.
     */
    internal fun getMonthNamesResource(locale: Locale): MonthNamesResource {
        val names = Resources.getMonthNamesResource(locale)
        val inDate = localeInDateNames[locale.languageCode] ?: return names
        if (inDate.monthsFull == null && inDate.monthsAbbreviated == null) return names
        return MonthNamesData(
            full = inDate.monthsFull?.let(::MonthNames) ?: names.full,
            abbreviated = inDate.monthsAbbreviated?.let(::MonthNames) ?: names.abbreviated,
        )
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
