package io.github.aughtone.datetime.format.resources.strings.monthnames

import io.github.aughtone.datetime.format.resources.NamesResource
import kotlinx.datetime.format.MonthNames

interface MonthNamesResource:NamesResource<MonthNames> {
    override val full: MonthNames
    override val abbreviated: MonthNames
}
