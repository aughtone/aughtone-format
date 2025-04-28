package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0pl : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota","niedziela"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "pon", "wt", "śr", "czw", "pt", "sob", "nd"
            )
        )
    }
}
