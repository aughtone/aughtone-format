package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0uk : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Січень",
            february = "Лютий",
            march = "Березень",
            april = "Квітень",
            may = "Травень",
            june = "Червень",
            july = "Липень",
            august = "Серпень",
            september = "Вересень",
            october = "Жовтень",
            november = "Листопад",
            december = "Грудень"
        )
    }

    override val abbreviated by lazy {
        MonthNames(
            january = "Січ",
            february = "Лют",
            march = "Бер",
            april = "Квіт",
            may = "Трав",
            june = "Черв",
            july = "Лип",
            august = "Серп",
            september = "Вер",
            october = "Жовт",
            november = "Лист",
            december = "Груд",
        )
    }
}
