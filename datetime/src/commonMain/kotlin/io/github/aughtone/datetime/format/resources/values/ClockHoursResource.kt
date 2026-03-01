package io.github.aughtone.datetime.format.resources.values

internal data class ClockHoursResource(
    override val is24hour: Boolean,
    override val hours: ClockType
) : ClockHours
