package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.values.ClockType
import io.github.aughtone.datetime.format.resources.values.ClockHoursResource

internal val localeClockHoursSource by lazy {
    mapOf(
        "US" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "CA" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "fr-CA" to lazy { ClockHoursResource(is24hour = true, hours = ClockType.C24Hour) },
        "AU" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "NZ" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "PH" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "IN" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "GB" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) }
    )
}
