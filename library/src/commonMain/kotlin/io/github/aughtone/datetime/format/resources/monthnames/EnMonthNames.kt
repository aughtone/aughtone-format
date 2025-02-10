package io.github.aughtone.datetime.format.resources.monthnames

import kotlinx.datetime.format.MonthNames

object EnMonthNames:MonthNamesResource {
    override val full by lazy { MonthNames.ENGLISH_FULL }
    override val abbreviated by lazy { MonthNames.ENGLISH_ABBREVIATED}
}
