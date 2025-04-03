package io.github.aughtone.datetime.format.resources.strings.dayofweeknames
import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0id : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min"
            )
        )
    }
}
