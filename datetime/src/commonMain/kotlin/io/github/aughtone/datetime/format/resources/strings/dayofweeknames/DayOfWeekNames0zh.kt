package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0zh : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "一", "二", "三", "四", "五", "六", "日"
            )
        )
    }
}
