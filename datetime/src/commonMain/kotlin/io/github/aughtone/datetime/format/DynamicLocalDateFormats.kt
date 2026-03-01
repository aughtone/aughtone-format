package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.formats.DatePatterns
import io.github.aughtone.datetime.format.resources.formats.localeDatePatterns
import io.github.aughtone.datetime.format.resources.formats.localeDayOfWeekNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeMonthNamesSource
import io.github.aughtone.datetime.format.resources.values.dayofweeknames.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.monthnames.MonthNamesResource
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat

internal object DynamicLocalDateFormats {

    private val defaultLocale = Locale(languageCode = "en", regionCode = "US", displayName = "en-US")
    private const val defaultPatternKey = "US"
    private const val defaultDayOfWeekKey = "en"
    private const val defaultMonthNamesKey = "en"

    private fun getLanguageRegionKey(locale: Locale): String? {
        return locale.regionCode?.let { "${locale.languageCode}-$it" }
    }

    private fun getRegionKey(locale: Locale): String? {
        return locale.regionCode
    }

    private fun getLanguageKey(locale: Locale): String? {
        return locale.languageCode.takeIf { it.isNotEmpty() }
    }

    private fun getPatterns(locale: Locale): DatePatterns {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)

        return localeDatePatterns[langRegionKey]?.value
            ?: localeDatePatterns[regionKey]?.value
            ?: localeDatePatterns.getValue(defaultPatternKey).value
    }

    private fun getDayOfWeekNamesResource(locale: Locale): DayOfWeekNamesResource {
        val languageKey = getLanguageKey(locale)
        return localeDayOfWeekNamesSource[languageKey]?.value ?: localeDayOfWeekNamesSource.getValue(defaultDayOfWeekKey).value
    }

    private fun getMonthNamesResource(locale: Locale): MonthNamesResource {
        val languageKey = getLanguageKey(locale)
        return localeMonthNamesSource[languageKey]?.value ?: localeMonthNamesSource.getValue(defaultMonthNamesKey).value
    }

    fun short(locale: Locale): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.short, dayOfWeekNames, monthNames)
        }
    }

    fun medium(locale: Locale): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.medium, dayOfWeekNames, monthNames)
        }
    }

    fun long(locale: Locale): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.long, dayOfWeekNames, monthNames)
        }
    }

    fun full(locale: Locale): DateTimeFormat<LocalDate> {
        val patterns = getPatterns(locale)
        val dayOfWeekNames = getDayOfWeekNamesResource(locale)
        val monthNames = getMonthNamesResource(locale)
        return LocalDate.Format {
            appendPattern(patterns.full, dayOfWeekNames, monthNames)
        }
    }
}
