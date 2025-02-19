package io.github.aughtone.datetime.format.resources.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0es : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Enero",
            february = "Febrero",
            march = "Marzo",
            april = "Abril",
            may = "Mayo",
            june = "Junio",
            july = "Julio",
            august = "Agosto",
            september = "Septiembre",
            october = "Octubre",
            november = "Noviembre",
            december = "Diciembre"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Ene.",
            february = "Feb.",
            march = "Mar.",
            april = "Abr.",
            may = "May.",
            june = "Jun.",
            july = "Jul.",
            august = "Ago.",
            september = "Sept.",
            october = "Oct.",
            november = "Nov.",
            december = "Dic."
        )
    }
}
