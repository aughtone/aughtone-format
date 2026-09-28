@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import io.github.aughtone.types.locale.Locale
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt

/**
 * The localized display name of this time zone **at [instant]**, in the given
 * [width].
 *
 * This is the *specific* name — the one that distinguishes standard time from
 * daylight time — which is why it needs an instant: `America/Toronto` is
 * "Eastern Standard Time" / "EST" in winter and "Eastern Daylight Time" / "EDT"
 * in summer, and only the instant says which. It reads the same bundled zone
 * tables the `.format` path uses for its Long/Full styles, localized for the
 * ~50 languages those tables carry.
 *
 * It is **not** the generic, instant-independent name ("Eastern Time"): that is
 * a separate CLDR metazone dataset this module does not yet bundle.
 *
 * Coverage is a curated set of IANA zones. A zone that is not in the tables, or
 * a locale with no bundled name, falls back to the zone's **UTC offset string**
 * (for example `"-05:00"`) rather than throwing or returning null — so a result
 * that looks like an offset means "no bundled name for this zone".
 *
 * ```kotlin
 * val toronto = TimeZone.of("America/Toronto")
 * val winter = Instant.parse("2024-01-15T12:00:00Z")
 * toronto.displayName(winter, locale = localeFor("en")!!)                    // "Eastern Standard Time"
 * toronto.displayName(winter, TextWidth.Abbreviated, localeFor("en")!!)       // "EST"
 * ```
 *
 * @param instant the moment at which to resolve standard vs. daylight time.
 *   Defaults to now ([Clock.System]); pin it for reproducible output.
 * @param width [TextWidth.Full] for the full name (default) or
 *   [TextWidth.Abbreviated] for the abbreviation.
 * @param locale the locale whose names to use. Defaults to [Locale.current].
 * @return the zone's localized name at [instant] in [width], or its UTC offset
 *   string when no bundled name applies.
 * @see TextWidth
 */
fun TimeZone.displayName(
    instant: Instant = Clock.System.now(),
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String {
    val offset = offsetAt(instant)
    return when (width) {
        TextWidth.Full -> TimeZoneAbbreviationLookup.getTimeZoneFullName(this, offset, locale)
        TextWidth.Abbreviated -> TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, offset, locale)
    }
}
