package io.github.aughtone.datetime.format.resources.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object EnDayOfWeekNames : DayOfWeekNamesResource {
    override val full by lazy { DayOfWeekNames.ENGLISH_FULL }
    override val abbreviated: DayOfWeekNames by lazy { DayOfWeekNames.ENGLISH_ABBREVIATED }
}
