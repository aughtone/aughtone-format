package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0pl : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "styczeń",
            february = "luty",
            march = "marzec",
            april = "kwiecień",
            may = "maj",
            june = "czerwiec",
            july = "lipiec",
            august = "sierpień",
            september = "wrzesień",
            october = "październik",
            november = "listopad",
            december = "grudzień"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "sty",
            february = "lut",
            march = "mar",
            april = "kwi",
            may = "maj",
            june = "cze",
            july = "lip",
            august = "sie",
            september = "wrz",
            october = "paź",
            november = "lis",
            december = "gru"
        )
    }
}
