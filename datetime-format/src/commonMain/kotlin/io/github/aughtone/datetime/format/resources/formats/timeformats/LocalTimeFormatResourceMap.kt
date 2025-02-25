package io.github.aughtone.datetime.format.resources.formats.timeformats

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.formats.dateformats.LocalDateFormats0CA
import io.github.aughtone.datetime.format.resources.formats.dateformats.LocalDateFormats0US
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0Default
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0EnCA
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0FrCA
import io.github.aughtone.datetime.format.resources.timeformats.LocalDateFormats0ISO
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormatBuilder


object LocalTimeFormatResourceMap {

    val default: StyledDateTimeFormats<LocalTime> by lazy { LocalTimeFormats0ISO }
    val CA: StyledDateTimeFormats<LocalTime> by lazy { LocalTimeFormats0CA }
    val US: StyledDateTimeFormats<LocalTime> by lazy { LocalTimeFormats0US }

    val map: Map<String, StyledDateTimeFormats<LocalTime>> = mapOf(
        "" to default,
        "CA" to CA,
        "US" to US,
    )
}

internal fun DateTimeFormatBuilder.WithTime.getLocaleMarker(locale: Locale) {
    amPmMarker(
        am = Resources.getText(locale = locale).am.text,
        pm = Resources.getText(locale = locale).pm.text
    )
}
