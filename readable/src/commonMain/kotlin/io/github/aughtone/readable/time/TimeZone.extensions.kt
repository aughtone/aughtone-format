package io.github.aughtone.readable.time

import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import io.github.aughtone.types.locale.Locale
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlin.time.Clock

/**
 * Formats this [TimeZone] as a localized, human-readable string.
 *
 * NOTE: An Instant is required because many timezones observe daylight saving
 * time (DST). For example, "America/New_York" formats as "EST" in winter and
 * "EDT" in summer. Passing an instant determines which variant is active.
 *
 * The name comes from the platform's own CLDR (`java.time`, ICU, `NSTimeZone`,
 * `Intl`), with a bundled supplement for languages no platform ships (notably
 * Inuktitut) and an English/offset backstop. Its exact wording therefore follows
 * the reader's OS and runtime rather than a bundled table.
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
): String =
    if (useFullName) {
        TimeZoneAbbreviationLookup.getTimeZoneFullName(this, instant, locale)
    } else {
        TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, instant, locale)
    }

/**
 * Formats this [TimeZone] as a localized, human-readable string for a specific [UtcOffset].
 *
 * Unlike the [Instant]-based overload, a bare offset carries no moment, so this
 * form **cannot** consult the platform CLDR (which names a zone at an instant).
 * It resolves the bundled supplement (e.g. Inuktitut) and then the English name,
 * falling back to the offset string. Prefer the [Instant] overload whenever an
 * instant is available, so the reader gets a name in their own language.
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
): String =
    if (useFullName) {
        TimeZoneAbbreviationLookup.getTimeZoneFullName(this, offset, locale)
    } else {
        TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, offset, locale)
    }
