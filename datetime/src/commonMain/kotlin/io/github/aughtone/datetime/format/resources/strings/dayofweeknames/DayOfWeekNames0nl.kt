package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0nl : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "maandag", "dinsdag", "woensdag", "donderdag", "vrijdag", "zaterdag", "zondag"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "ma", "di", "wo", "do", "vr", "za", "zo"
            )
        )
    }
}
