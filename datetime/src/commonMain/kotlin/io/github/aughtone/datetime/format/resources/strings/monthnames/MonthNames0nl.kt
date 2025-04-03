package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0nl : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "januari",
            february = "februari",
            march = "maart",
            april = "april",
            may = "mei",
            june = "juni",
            july = "juli",
            august = "augustus",
            september = "september",
            october = "oktober",
            november = "november",
            december = "december"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "jan.",
            february = "feb.",
            march = "mrt.",
            april = "apr.",
            may = "mei",
            june = "jun.",
            july = "jul.",
            august = "aug.",
            september = "sep.",
            october = "okt.",
            november = "nov.",
            december = "dec."
        )
    }
}
