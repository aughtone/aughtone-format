package io.github.aughtone.datetime.format.resources.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object FrDayOfWeekNames : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "Lun.", "Mar.", "Mer.", "Jeu.", "Ven.", "Sam.", "Dim."
            )
        )
    }
}
