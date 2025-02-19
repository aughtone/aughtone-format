package io.github.aughtone.datetime.format.resources.monthnames

import kotlinx.datetime.format.MonthNames

interface MonthNamesResource {
    val full: MonthNames
    val abbreviated: MonthNames
}
