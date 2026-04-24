package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
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

    private fun getMonthNamesResource(locale: Locale): MonthNamesResource =
        Resources.getMonthNamesResource(locale)

    fun short(locale: Locale, timeZone: TimeZone): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.short, dayOfWeekNames, monthNames)
        }
    }

    fun medium(locale: Locale, timeZone: TimeZone): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.medium, dayOfWeekNames, monthNames)
        }
    }

    fun long(locale: Locale, timeZone: TimeZone): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.long, dayOfWeekNames, monthNames)
        }
    }

    fun full(locale: Locale, timeZone: TimeZone): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.full, dayOfWeekNames, monthNames)
        }
    }
}
