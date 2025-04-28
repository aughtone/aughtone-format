package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0fi : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Tammikuu",
            february = "Helmikuu",
            march = "Maaliskuu",
            april = "Huhtikuu",
            may = "Toukokuu",
            june = "Kesäkuu",
            july = "Heinäkuu",
            august = "Elokuu",
            september = "Syyskuu",
            october = "Lokakuu",
            november = "Marraskuu",
            december = "Joulukuu"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Tammi",
            february = "Helmi",
            march = "Maalis",
            april = "Huhti",
            may = "Touko",
            june = "Kesä",
            july = "Heinä",
            august = "Elo",
            september = "Syys",
            october = "Loka",
            november = "Marras",
            december = "Joulu"
        )
    }
}
