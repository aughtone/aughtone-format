package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0pt : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Janeiro",
            february = "Fevereiro",
            march = "Março",
            april = "Abril",
            may = "Maio",
            june = "Junho",
            july = "Julho",
            august = "Agosto",
            september = "Setembro",
            october = "Outubro",
            november = "Novembro",
            december = "Dezembro"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Jan.",
            february = "Fev.",
            march = "Mar.",
            april = "Abr.",
            may = "Mai.",
            june = "Jun.",
            july = "Jul.",
            august = "Ago.",
            september = "Set.",
            october = "Out.",
            november = "Nov.",
            december = "Dez."
        )
    }
}
