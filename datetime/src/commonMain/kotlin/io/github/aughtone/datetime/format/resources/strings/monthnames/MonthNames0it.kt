package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0it : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Gennaio",
            february = "Febbraio",
            march = "Marzo",
            april = "Aprile",
            may = "Maggio",
            june = "Giugno",
            july = "Luglio",
            august = "Agosto",
            september = "Settembre",
            october = "Ottobre",
            november = "Novembre",
            december = "Dicembre"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Gen.",
            february = "Feb.",
            march = "Mar.",
            april = "Apr.",
            may = "Mag.",
            june = "Giu.",
            july = "Lug.",
            august = "Ago.",
            september = "Set.",
            october = "Ott.",
            november = "Nov.",
            december = "Dic."
        )
    }
}
