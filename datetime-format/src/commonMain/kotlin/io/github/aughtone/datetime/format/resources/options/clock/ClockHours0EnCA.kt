package io.github.aughtone.datetime.format.resources.options.clock

object ClockHours0EnCA : ClockHours {
    override val hours: ClockType = ClockType.C12Hour
    override val is24hour: Boolean = hours == ClockType.C24Hour
}
