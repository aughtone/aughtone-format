package io.github.aughtone.datetime.format.resources

import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.formats.dateformats.LocalDateFormatResourceMap
import io.github.aughtone.datetime.format.resources.formats.timeformats.LocalTimeFormatResourceMap
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours
import io.github.aughtone.datetime.format.resources.options.clock.ClockHoursResourceMap
import io.github.aughtone.datetime.format.resources.strings.dayofweeknames.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.strings.dayofweeknames.DayOfWeekNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.era.EraNamesResource
import io.github.aughtone.datetime.format.resources.strings.era.EraNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResource
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResourceMap
import io.github.aughtone.datetime.format.resources.strings.text.TextResource
import io.github.aughtone.datetime.format.resources.strings.text.TextResourceMap
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime


object ResourceGroups {
    val monthNames: Map<String, MonthNamesResource> by lazy { MonthNamesResourceMap.map }
    val dayOfWeekNames: Map<String, DayOfWeekNamesResource> by lazy { DayOfWeekNamesResourceMap.map }
    val eraNames: Map<String, EraNamesResource> by lazy { EraNamesResourceMap.map }
    val clockHours: Map<String, ClockHours> by lazy { ClockHoursResourceMap.map }
    val text: Map<String, TextResource> by lazy { TextResourceMap.map }
    val timeFormats: Map<String, StyledDateTimeFormats<LocalTime>> by lazy { LocalTimeFormatResourceMap.map }
    val dateFormats: Map<String, StyledDateTimeFormats<LocalDate>> by lazy { LocalDateFormatResourceMap.map }
}
