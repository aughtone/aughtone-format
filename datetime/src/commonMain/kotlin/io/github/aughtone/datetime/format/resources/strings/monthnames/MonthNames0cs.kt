package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0cs : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Leden",
            february = "Únor",
            march = "Březen",
            april = "Duben",
            may = "Květen",
            june = "Červen",
            july = "Červenec",
            august = "Srpen",
            september = "Září",
            october = "Říjen",
            november = "Listopad",
            december = "Prosinec"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Led",
            february = "Úno",
            march = "Bře",
            april = "Dub",
            may = "Kvě",
            june = "Čvn",
            july = "Čvc",
            august = "Srp",
            september = "Zář",
            october = "Říj",
            november = "Lis",
            december = "Pro"
        )
    }
}
