package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0id : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Januari",
            february = "Februari",
            march = "Maret",
            april = "April",
            may = "Mei",
            june = "Juni",
            july = "Juli",
            august = "Agustus",
            september = "September",
            october = "Oktober",
            november = "November",
            december = "Desember"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Jan",
            february = "Feb",
            march = "Mar",
            april = "Apr",
            may = "Mei",
            june = "Jun",
            july = "Jul",
            august = "Agu",
            september = "Sep",
            october = "Okt",
            november = "Nov",
            december = "Des"
        )
    }
}
