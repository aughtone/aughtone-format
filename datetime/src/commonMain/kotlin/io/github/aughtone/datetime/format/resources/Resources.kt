package io.github.aughtone.datetime.format.resources

import io.github.aughtone.datetime.format.DynamicLocalDateFormats
import io.github.aughtone.datetime.format.DynamicLocalTimeFormats
import io.github.aughtone.datetime.format.resources.formats.AmPmStrings
import io.github.aughtone.datetime.format.resources.formats.DatePatterns
import io.github.aughtone.datetime.format.resources.formats.TimePatterns
import io.github.aughtone.datetime.format.resources.values.ClockHoursResource
import io.github.aughtone.datetime.format.resources.formats.localeAmPmStrings
import io.github.aughtone.datetime.format.resources.formats.localeClockHoursSource
import io.github.aughtone.datetime.format.resources.formats.localeDatePatterns
import io.github.aughtone.datetime.format.resources.formats.localeDayOfWeekNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeEraNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeMonthNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeTimePatterns
import io.github.aughtone.datetime.format.resources.values.ClockHours
import io.github.aughtone.datetime.format.resources.values.ClockType
import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.EraNames
import io.github.aughtone.datetime.format.resources.values.EraNamesResource
import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import io.github.aughtone.datetime.format.resources.values.text.TextResource
import io.github.aughtone.datetime.format.resources.values.text.TextResourceMap
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.TimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

enum class FormatStyle {
    SHORT, MEDIUM, LONG, FULL
}

object Resources {

    private val defaultLocale = Locale(languageCode = "en", regionCode = "US", displayName = "en-US")
    private const val defaultFallbackKey = "US"
    private const val defaultLanguageFallbackKey = "en"
    private const val defaultTextResourceKey = "en"
    private const val defaultEraNamesResourceKey = "en"

    private fun getLanguageRegionKey(locale: Locale): String? {
        return locale.regionCode?.let { "${locale.languageCode}-$it" }
    }

    private fun getRegionKey(locale: Locale): String? {
        return locale.regionCode
    }

    private fun getLanguageKey(locale: Locale): String? {
        return locale.languageCode.takeIf { it.isNotEmpty() }
    }

    fun getDateFormat(locale: Locale, timeZone: TimeZone, style: FormatStyle): DateTimeFormat<LocalDate> {
        return when (style) {
            FormatStyle.SHORT -> DynamicLocalDateFormats.short(locale, timeZone)
            FormatStyle.MEDIUM -> DynamicLocalDateFormats.medium(locale, timeZone)
            FormatStyle.LONG -> DynamicLocalDateFormats.long(locale, timeZone)
            FormatStyle.FULL -> DynamicLocalDateFormats.full(locale, timeZone)
        }
    }

    fun getTimeFormat(
        locale: Locale,
        timeZone: TimeZone,
        style: FormatStyle,
        twentyFourHour: Boolean? = null,
    ): DateTimeFormat<LocalTime> {
        val clockHours = getClockHours(locale)
        val use24Hour = twentyFourHour ?: clockHours.is24hour
        return when (style) {
            FormatStyle.SHORT -> DynamicLocalTimeFormats.short(locale, use24Hour)
            FormatStyle.MEDIUM -> DynamicLocalTimeFormats.medium(locale, use24Hour)
            FormatStyle.LONG -> DynamicLocalTimeFormats.long(locale, use24Hour)
            FormatStyle.FULL -> DynamicLocalTimeFormats.full(locale, use24Hour)
        }
    }

    internal fun getAmPmStrings(locale: Locale): AmPmStrings {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return localeAmPmStrings[langRegionKey]
            ?: localeAmPmStrings[regionKey]
            ?: localeAmPmStrings[languageKey]
            ?: localeAmPmStrings.getValue(defaultFallbackKey)
    }

    internal fun getTimePatterns(locale: Locale): TimePatterns {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return localeTimePatterns[langRegionKey]?.value
            ?: localeTimePatterns[regionKey]?.value
            ?: localeTimePatterns[languageKey]?.value
            ?: localeTimePatterns.getValue(defaultFallbackKey).value
    }

    internal fun getDatePatterns(locale: Locale): DatePatterns {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return localeDatePatterns[langRegionKey]
            ?: localeDatePatterns[regionKey]
            ?: localeDatePatterns[languageKey]
            ?: localeDatePatterns.getValue(defaultFallbackKey)
    }

    internal fun getDayOfWeekNamesResource(locale: Locale): DayOfWeekNamesResource {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return localeDayOfWeekNamesSource[langRegionKey]?.value
            ?: localeDayOfWeekNamesSource[regionKey]?.value
            ?: localeDayOfWeekNamesSource[languageKey]?.value
            ?: localeDayOfWeekNamesSource.getValue(defaultLanguageFallbackKey).value
    }

    internal fun getMonthNamesResource(locale: Locale): MonthNamesResource {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return localeMonthNamesSource[langRegionKey]?.value
            ?: localeMonthNamesSource[regionKey]?.value
            ?: localeMonthNamesSource[languageKey]?.value
            ?: localeMonthNamesSource.getValue(defaultLanguageFallbackKey).value
    }

    private fun getTextResource(locale: Locale): TextResource {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return TextResourceMap.map[langRegionKey]
            ?: TextResourceMap.map[regionKey]
            ?: TextResourceMap.map[languageKey]
            ?: TextResourceMap.map.getValue(defaultTextResourceKey)
    }

    fun getText(
        locale: Locale,
    ): TextResource = getTextResource(locale)

    private fun getClockHoursResource(locale: Locale): ClockHours {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        // Try specific language-region, then region-only. Default to 24-hour if neither indicates 12-hour.
        return localeClockHoursSource[langRegionKey]?.value
            ?: localeClockHoursSource[regionKey]?.value
            ?: localeClockHoursSource[languageKey]?.value
            ?: ClockHoursResource(is24hour = true, hours = ClockType.C24Hour)
    }

    fun getClockHours(locale: Locale): ClockHours = getClockHoursResource(locale)

    private fun getEraNamesResource(locale: Locale): EraNamesResource {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)
        val languageKey = getLanguageKey(locale)

        return localeEraNamesSource[langRegionKey]?.value
            ?: localeEraNamesSource[regionKey]?.value
            ?: localeEraNamesSource[languageKey]?.value
            ?: localeEraNamesSource.getValue(defaultEraNamesResourceKey).value
    }

    fun getEraNames(locale: Locale, abbreviated: Boolean): EraNames =
        with(
            getEraNamesResource(locale)
        ) {
            if (abbreviated) this.abbreviated else this.full
        }
}
