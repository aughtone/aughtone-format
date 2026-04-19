package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.values.ClockType
import io.github.aughtone.datetime.format.resources.values.ClockHoursResource

/**
 * A lazy mapping of locale identifiers to their preferred clock hours representation.
 * A missing value should default to 24 hour clock.
 *
 * This source specifies whether a given locale uses a 12-hour or 24-hour clock
 * format by default. The keys are ISO 3166-1 alpha-2 country codes (e.g., "US", "GB")
 * or specific IETF BCP 47 language tags (e.g., "fr-CA", "cy-GB") for locales where
 * conventions differ based on language within a country.
 *
 * Based on authoritative locale data from sources such as the Unicode CLDR.
 */
internal val localeClockHoursSource by lazy {
    mapOf(
        "AU" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "BD" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "CA" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "fr-CA" to lazy { ClockHoursResource(is24hour = true, hours = ClockType.C24Hour) },
        "GB" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "cy-GB" to lazy { ClockHoursResource(is24hour = true, hours = ClockType.C24Hour) },
        "IE" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "ga-IE" to lazy { ClockHoursResource(is24hour = true, hours = ClockType.C24Hour) },
        "IN" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "MX" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "NZ" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "PH" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "PK" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "US" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "ZA" to lazy { ClockHoursResource(is24hour = false, hours = ClockType.C12Hour) },
        "af-ZA" to lazy { ClockHoursResource(is24hour = true, hours = ClockType.C24Hour) }
    )
}
