package io.github.aughtone.datetime.format.resources

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.formats.dateformats.LocalDateFormatResourceMap
import io.github.aughtone.datetime.format.resources.formats.timeformats.LocalTimeFormatResourceMap
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours
import io.github.aughtone.datetime.format.resources.options.clock.ClockHoursResourceMap
import io.github.aughtone.datetime.format.resources.strings.dayofweeknames.DayOfWeekNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.era.EraNames
import io.github.aughtone.datetime.format.resources.strings.era.EraNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.text.TextResource
import io.github.aughtone.datetime.format.resources.strings.text.TextResourceMap
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames


object Resources {

    fun getTimeFormat(locale: Locale): StyledDateTimeFormats<LocalTime> =
        ResourceGroups.timeFormats[buildResourceLocaleKey(
            ResourceGroups.timeFormats,
            locale.language,
            locale.region
        )] ?: LocalTimeFormatResourceMap.default

    fun getDateFormat(locale: Locale): StyledDateTimeFormats<LocalDate> {

        val key = buildResourceLocaleKey(
            ResourceGroups.dateFormats,
            locale.language,
            locale.region
        )
        println("Debug locale = ${locale.toLanguageTag()},  key = $key")
        return ResourceGroups.dateFormats[key] ?: LocalDateFormatResourceMap.default
    }

    fun getText(
        locale: Locale = Locale.current,
    ): TextResource = ResourceGroups.text[buildResourceLocaleKey(
        ResourceGroups.text,
        locale.language,
        locale.region
    )] ?: TextResourceMap.default

    fun getClockHours(locale: Locale): ClockHours =
        ResourceGroups.clockHours[buildResourceLocaleKey(
            ResourceGroups.clockHours,
            locale.language,
            locale.region
        )] ?: ClockHoursResourceMap.default

    fun getEraNames(locale: Locale, abbreviated: Boolean): EraNames =
        with(
            ResourceGroups.eraNames[buildResourceLocaleKey(
                ResourceGroups.eraNames,
                locale.language,
                locale.region
            )] ?: EraNamesResourceMap.default
        ) {
            if (abbreviated) this.abbreviated else this.full
        }

    fun getDayOfWeekNames(locale: Locale, abbreviated: Boolean): DayOfWeekNames =
        with(
            ResourceGroups.dayOfWeekNames[buildResourceLocaleKey(
                ResourceGroups.dayOfWeekNames,
                locale.language,
                locale.region
            )] ?: DayOfWeekNamesResourceMap.default
        ) {
            if (abbreviated) this.abbreviated else this.full
        }

    fun getMonthNames(
        locale: Locale,
        abbreviated: Boolean,
    ): MonthNames = with(
        ResourceGroups.monthNames[buildResourceLocaleKey(
            ResourceGroups.monthNames,
            locale.language,
            locale.region
        )] ?: MonthNamesResourceMap.default
    ) {
        if (abbreviated) this.abbreviated else this.full
    }

    private fun buildResourceLocaleKey(
        data: Map<String, Any>,
        language: String,
        region: String,
    ): String? {
        val localeWithRegion = language + "_" + region
        if (data.containsKey(localeWithRegion)) {
            return localeWithRegion
        }
        if (data.containsKey(language)) {
            return language
        }
        if (data.containsKey(region)) {
            return region
        }
        // when nothing found this is the default value.
        return ""
    }
}
