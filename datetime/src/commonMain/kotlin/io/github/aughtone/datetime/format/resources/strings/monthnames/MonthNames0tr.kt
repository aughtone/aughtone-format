package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0tr : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Ocak",
            february = "Şubat",
            march = "Mart",
            april = "Nisan",
            may = "Mayıs",
            june = "Haziran",
            july = "Temmuz",
            august = "Ağustos",
            september = "Eylül",
            october = "Ekim",
            november = "Kasım",
            december = "Aralık"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Oca",
            february = "Şub",
            march = "Mar",
            april = "Nis",
            may = "May",
            june = "Haz",
            july = "Tem",
            august = "Ağu",
            september = "Eyl",
            october = "Eki",
            november = "Kas",
            december = "Ara"
        )
    }
}
