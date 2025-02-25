package io.github.aughtone.datetime.format.resources.options.clock

object ClockHours0FrCA : ClockHours {
    override val hours: ClockType = ClockType.C24Hour
    override val is24hour: Boolean = hours == ClockType.C24Hour
}
