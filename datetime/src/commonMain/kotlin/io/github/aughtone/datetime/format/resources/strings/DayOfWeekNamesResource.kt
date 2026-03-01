package io.github.aughtone.datetime.format.resources.strings

import io.github.aughtone.datetime.format.resources.NamesResource
import kotlinx.datetime.format.DayOfWeekNames

interface DayOfWeekNamesResource : NamesResource<DayOfWeekNames> {
    override val full: DayOfWeekNames
    override val abbreviated: DayOfWeekNames
}
