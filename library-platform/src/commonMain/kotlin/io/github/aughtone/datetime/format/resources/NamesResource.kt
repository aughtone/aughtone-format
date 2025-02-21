package io.github.aughtone.datetime.format.resources

import kotlinx.datetime.format.DayOfWeekNames

interface NamesResource<t_Data> {
    val full: t_Data
    val abbreviated: t_Data
}
