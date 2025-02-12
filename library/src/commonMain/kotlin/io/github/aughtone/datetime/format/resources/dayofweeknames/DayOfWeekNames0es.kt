package io.github.aughtone.datetime.format.resources.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0es : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "Lun.", "Mar.", "Mié.", "Jue.", "Vie.", "Sáb.", "Dom."
            )
        )
    }
}
