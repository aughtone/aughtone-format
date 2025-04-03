package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0kk : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Қаңтар",
            february = "Ақпан",
            march = "Наурыз",
            april = "Сәуір",
            may = "Мамыр",
            june = "Маусым",
            july = "Шілде",
            august = "Тамыз",
            september = "Қыркүйек",
            october = "Қазан",
            november = "Қараша",
            december = "Желтоқсан"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Қаң",
            february = "Ақп",
            march = "Нау",
            april = "Сәу",
            may = "Мам",
            june = "Мау",
            july = "Шіл",
            august = "Там",
            september = "Қыр",
            october = "Қаз",
            november = "Қар",
            december = "Жел"
        )
    }
}
