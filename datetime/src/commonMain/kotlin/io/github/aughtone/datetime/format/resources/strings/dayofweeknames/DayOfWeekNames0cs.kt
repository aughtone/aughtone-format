package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0cs : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota", "Neděle"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "Po", "Út", "St", "Čt", "Pá", "So", "Ne"
            )
        )
    }
}
