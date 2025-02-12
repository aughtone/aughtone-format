package io.github.aughtone.datetime.format.resources.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0fr:MonthNamesResource {
    override val full by lazy { MonthNames(
        january = "Janvier",
        february = "Février",
        march = "Mars",
        april = "Avril",
        may = "Mai",
        june = "Juin",
        july = "Juillet",
        august = "Août",
        september = "Septembre",
        october = "October",
        november = "Octobre",
        december = "Décembre"
    )}
    override val abbreviated by lazy {
        MonthNames(
            january = "Jan.",
            february = "Fév.",
            march = "Mars",
            april = "Avril",
            may = "Mai",
            june = "Juin",
            july = "Juil.",
            august = "Août",
            september = "Sept.",
            october = "Oct.",
            november = "Nov.",
            december = "Déc."
        )
    }
}
