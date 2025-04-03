package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0uz : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Yanvar",
            february = "Fevral",
            march = "Mart",
            april = "Aprel",
            may = "May",
            june = "Iyun",
            july = "Iyul",
            august = "Avgust",
            september = "Sentyabr",
            october = "Oktyabr",
            november = "Noyabr",
            december = "Dekabr"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Yan",
            february = "Fev",
            march = "Mar",
            april = "Apr",
            may = "May",
            june = "Iyn",
            july = "Iyl",
            august = "Avg",
            september = "Sen",
            october = "Okt",
            november = "Noy",
            december = "Dek"
        )
    }
}
