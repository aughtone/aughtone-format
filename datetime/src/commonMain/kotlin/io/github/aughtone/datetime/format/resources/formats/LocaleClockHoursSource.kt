package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.options.ClockHours
import io.github.aughtone.datetime.format.resources.options.ClockType

internal data class ClockHoursData(
    override val is24hour: Boolean,
    override val hours: ClockType
) : ClockHours

internal val localeClockHoursSource by lazy {
    mapOf(
        "US" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) },
        "CA" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) },
        "fr-CA" to lazy { ClockHoursData(is24hour = true, hours = ClockType.C24Hour) },
        "AU" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) },
        "NZ" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) },
        "PH" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) },
        "IN" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) },
        "GB" to lazy { ClockHoursData(is24hour = false, hours = ClockType.C12Hour) }
    )
}
