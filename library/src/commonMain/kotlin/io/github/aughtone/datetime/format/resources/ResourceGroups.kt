package io.github.aughtone.datetime.format.resources

import io.github.aughtone.datetime.format.resources.options.is24hour.Is24Hour
import io.github.aughtone.datetime.format.resources.options.is24hour.Is24HourResourceMap
import io.github.aughtone.datetime.format.resources.strings.dayofweeknames.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.strings.dayofweeknames.DayOfWeekNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.era.EraNamesResource
import io.github.aughtone.datetime.format.resources.strings.era.EraNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResource
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.text.TextResource
import io.github.aughtone.datetime.format.resources.strings.text.TextResourceMap


object ResourceGroups {
    val monthNames: Map<String, MonthNamesResource> by lazy { MonthNamesResourceMap.map }
    val dayOfWeekNames: Map<String, DayOfWeekNamesResource> by lazy { DayOfWeekNamesResourceMap.map }
    val eraNames: Map<String, EraNamesResource> by lazy { EraNamesResourceMap.map }
    val is24Hour: Map<String, Is24Hour> by lazy { Is24HourResourceMap.map }
    val text: Map<String, TextResource> by lazy { TextResourceMap.map }
}
