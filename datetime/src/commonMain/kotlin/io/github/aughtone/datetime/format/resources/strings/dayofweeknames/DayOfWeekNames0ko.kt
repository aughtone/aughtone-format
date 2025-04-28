package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0ko : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "월", "화", "수", "목", "금", "토", "일"
            )
        )
    }
}
