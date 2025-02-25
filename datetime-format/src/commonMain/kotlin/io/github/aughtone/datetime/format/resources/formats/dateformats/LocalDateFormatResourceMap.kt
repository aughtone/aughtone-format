package io.github.aughtone.datetime.format.resources.formats.dateformats

import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0Default
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0EnCA
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0FrCA
import io.github.aughtone.datetime.format.resources.timeformats.LocalDateFormats0ISO
import kotlinx.datetime.LocalDate


object LocalDateFormatResourceMap {

    val default: StyledDateTimeFormats<LocalDate> by lazy { LocalDateFormats0ISO }
    val CA: StyledDateTimeFormats<LocalDate> by lazy { LocalDateFormats0CA }
    val US: StyledDateTimeFormats<LocalDate> by lazy { LocalDateFormats0US }

    val map: Map<String, StyledDateTimeFormats<LocalDate>> = mapOf(
        "" to default,
        "CA" to CA,
        "US" to US,
    )
}
