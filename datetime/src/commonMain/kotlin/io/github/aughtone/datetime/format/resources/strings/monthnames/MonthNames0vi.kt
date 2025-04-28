package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0vi : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "Tháng Một",
            february = "Tháng Hai",
            march = "Tháng Ba",
            april = "Tháng Tư",
            may = "Tháng Năm",
            june = "Tháng Sáu",
            july = "Tháng Bảy",
            august = "Tháng Tám",
            september = "Tháng Chín",
            october = "Tháng Mười",
            november = "Tháng Mười Một",
            december = "Tháng Mười Hai"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "Thg 1",
            february = "Thg 2",
            march = "Thg 3",
            april = "Thg 4",
            may = "Thg 5",
            june = "Thg 6",
            july = "Thg 7",
            august = "Thg 8",
            september = "Thg 9",
            october = "Thg 10",
            november = "Thg 11",
            december = "Thg 12"
        )
    }
}
