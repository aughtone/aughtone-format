package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0it : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "Lun", "Mar", "Mer", "Gio", "Ven", "Sab", "Dom"
            )
        )
    }
}
