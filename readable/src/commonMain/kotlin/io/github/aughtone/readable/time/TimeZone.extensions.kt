package io.github.aughtone.readable.time

import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import io.github.aughtone.types.locale.Locale
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetAt
import kotlin.time.Clock

/**
 * Formats this [TimeZone] as a localized, human-readable string.
 *
 * NOTE: An Instant (or UtcOffset) is required because many timezones observe daylight
 * saving time (DST). For example, "America/New_York" formats as "EST" in winter
 * and "EDT" in summer. Passing an instant determines which variant is currently active.
 *
 * @param instant The reference instant used to determine daylight saving time status (defaults to current system time).
 * @param useFullName If true, returns the full timezone name; otherwise, returns the abbreviation.
 * @param locale The locale for formatting (defaults to [Locale.current]).
 * @return A human-readable timezone string.
 */
fun TimeZone.formatReadable(
    instant: Instant = Clock.System.now(),
    useFullName: Boolean = false,
    locale: Locale = Locale.current,
): String {
    val offset = offsetAt(instant)
    return if (useFullName) {
        TimeZoneAbbreviationLookup.getTimeZoneFullName(this, offset, locale)
    } else {
        TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, offset, locale)
    }
}

/**
 * Formats this [TimeZone] as a localized, human-readable string for a specific [UtcOffset].
 *
 * @param offset The [UtcOffset] to look up.
 * @param useFullName If true, returns the full timezone name; otherwise, returns the abbreviation.
 * @param locale The locale for formatting (defaults to [Locale.current]).
 * @return A human-readable timezone string.
 */
fun TimeZone.formatReadable(
    offset: UtcOffset,
    useFullName: Boolean = false,
    locale: Locale = Locale.current,
): String {
    return if (useFullName) {
        TimeZoneAbbreviationLookup.getTimeZoneFullName(this, offset, locale)
    } else {
        TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, offset, locale)
    }
}
