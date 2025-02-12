package io.github.aughtone.datetime.format.resources.monthnames

import kotlinx.datetime.Month
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.number

object MonthNames0numbers : MonthNamesResource {
    override val full by lazy {
        // XXX pad these ones
        MonthNames(
            january = Month.JANUARY.number.toString(),
            february = Month.FEBRUARY.number.toString(),
            march = Month.MARCH.number.toString(),
            april = Month.APRIL.number.toString(),
            may = Month.MAY.number.toString(),
            june = Month.JUNE.number.toString(),
            july = Month.JULY.number.toString(),
            august = Month.AUGUST.number.toString(),
            september = Month.SEPTEMBER.number.toString(),
            october = Month.OCTOBER.number.toString(),
            november = Month.NOVEMBER.number.toString(),
            december = Month.DECEMBER.number.toString()
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = Month.JANUARY.number.toString(),
            february = Month.FEBRUARY.number.toString(),
            march = Month.MARCH.number.toString(),
            april = Month.APRIL.number.toString(),
            may = Month.MAY.number.toString(),
            june = Month.JUNE.number.toString(),
            july = Month.JULY.number.toString(),
            august = Month.AUGUST.number.toString(),
            september = Month.SEPTEMBER.number.toString(),
            october = Month.OCTOBER.number.toString(),
            november = Month.NOVEMBER.number.toString(),
            december = Month.DECEMBER.number.toString()
        )
    }
}
