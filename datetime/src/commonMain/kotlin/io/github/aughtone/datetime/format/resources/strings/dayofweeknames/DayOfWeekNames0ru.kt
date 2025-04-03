package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0ru : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "понедельник", "вторник", "среда", "четверг", "пятница", "суббота", "воскресенье"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "пн", "вт", "ср", "чт", "пт", "сб", "вс"
            )
        )
    }
}
