package io.github.aughtone.datetime.format.resources.strings.dayofweeknames
import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0de : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"
            )
        )
    }
}
