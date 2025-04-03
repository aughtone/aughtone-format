package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0de : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Januar",
            february = "Februar",
            march = "März",
            april = "April",
            may = "Mai",
            june = "Juni",
            july = "Juli",
            august = "August",
            september = "September",
            october = "Oktober",
            november = "November",
            december = "Dezember"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Jan.",
            february = "Feb.",
            march = "Mär.",
            april = "Apr.",
            may = "Mai",
            june = "Juni",
            july = "Juli",
            august = "Aug.",
            september = "Sept.",
            october = "Okt.",
            november = "Nov.",
            december = "Dez."
        )
    }
}
