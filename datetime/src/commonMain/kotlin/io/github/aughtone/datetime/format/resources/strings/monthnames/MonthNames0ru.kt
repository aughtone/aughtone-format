package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0ru : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Январь",
            february = "Февраль",
            march = "Март",
            april = "Апрель",
            may = "Май",
            june = "Июнь",
            july = "Июль",
            august = "Август",
            september = "Сентябрь",
            october = "Октябрь",
            november = "Ноябрь",
            december = "Декабрь"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Янв",
            february = "Фев",
            march = "Мар",
            april = "Апр",
            may = "Май",
            june = "Июн",
            july = "Июл",
            august = "Авг",
            september = "Сен",
            october = "Окт",
            november = "Ноя",
            december = "Дек"
        )
    }
}
