package io.github.aughtone.datetime.format.resources

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.resources.options.is24hour.Is24Hour
import io.github.aughtone.datetime.format.resources.options.is24hour.Is24HourResourceMap
import io.github.aughtone.datetime.format.resources.strings.dayofweeknames.DayOfWeekNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.era.EraNames
import io.github.aughtone.datetime.format.resources.strings.era.EraNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.text.TextResource
import io.github.aughtone.datetime.format.resources.strings.text.TextResourceMap
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlin.toString





object Resources {

    fun getText(
        locale: Locale = Locale.current,
    ): TextResource = ResourceGroups.text[buildResourceLocaleKey(
        ResourceGroups.text,
        locale.language,
        locale.region
    )] ?: TextResourceMap.default

    fun get24Hour(locale: Locale): Is24Hour =
        ResourceGroups.is24Hour[buildResourceLocaleKey(
            ResourceGroups.is24Hour,
            locale.language,
            locale.region
        )] ?: Is24HourResourceMap.default

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
        // when nothing found this is the default value.
        return ""
    }
}
