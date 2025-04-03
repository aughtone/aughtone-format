package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0ja : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日", "日曜日"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "月", "火", "水", "木", "金", "土", "日"
            )
        )
    }
}
