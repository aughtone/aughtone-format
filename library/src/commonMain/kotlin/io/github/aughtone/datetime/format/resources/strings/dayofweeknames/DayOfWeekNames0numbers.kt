package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.isoDayNumber

object DayOfWeekNames0numbers : DayOfWeekNamesResource {
    //    DayOfWeek {
//        MONDAY,
//        TUESDAY,
//        WEDNESDAY,
//        THURSDAY,
//        FRIDAY,
//        SATURDAY,
//        SUNDAY;
//    }
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                DayOfWeek.MONDAY.isoDayNumber.toString(),
                DayOfWeek.TUESDAY.isoDayNumber.toString(),
                DayOfWeek.WEDNESDAY.isoDayNumber.toString(),
                DayOfWeek.THURSDAY.isoDayNumber.toString(),
                DayOfWeek.FRIDAY.isoDayNumber.toString(),
                DayOfWeek.SATURDAY.isoDayNumber.toString(),
                DayOfWeek.SUNDAY.isoDayNumber.toString(),
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                DayOfWeek.MONDAY.isoDayNumber.toString(),
                DayOfWeek.TUESDAY.isoDayNumber.toString(),
                DayOfWeek.WEDNESDAY.isoDayNumber.toString(),
                DayOfWeek.THURSDAY.isoDayNumber.toString(),
                DayOfWeek.FRIDAY.isoDayNumber.toString(),
                DayOfWeek.SATURDAY.isoDayNumber.toString(),
                DayOfWeek.SUNDAY.isoDayNumber.toString(),
            )
        )
    }
}
