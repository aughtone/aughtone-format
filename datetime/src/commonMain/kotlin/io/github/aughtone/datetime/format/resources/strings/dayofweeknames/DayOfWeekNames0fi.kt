package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0fi : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "maanantai", "tiistai", "keskiviikko", "torstai", "perjantai", "lauantai", "sunnuntai"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "ma", "ti", "ke", "to", "pe", "la", "su"
            )
        )
    }
}
