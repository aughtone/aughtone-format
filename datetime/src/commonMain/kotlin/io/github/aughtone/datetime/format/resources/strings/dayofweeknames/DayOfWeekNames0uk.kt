package io.github.aughtone.datetime.format.resources.strings.dayofweeknames
import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0uk : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "понеділок", "вівторок", "середа", "четвер", "п’ятниця", "субота","неділя"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "пн", "вт", "ср", "чт", "пт", "сб", "нд"
            )
        )
    }
}
