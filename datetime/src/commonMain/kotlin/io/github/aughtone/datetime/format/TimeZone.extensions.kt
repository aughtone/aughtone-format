@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.platform.platformGenericZoneName
import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import io.github.aughtone.types.locale.Locale
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

/**
 * The localized display name of this time zone **at [instant]**, in the given
 * [width].
 *
 * This is the *specific* name — the one that distinguishes standard time from
 * daylight time — which is why it needs an instant: `America/Toronto` is
 * "Eastern Standard Time" / "EST" in winter and "Eastern Daylight Time" / "EDT"
 * in summer, and only the instant says which.
 *
 * The name comes from the **platform's own CLDR** — `java.time` on JVM,
 * ICU-backed `java.util.TimeZone` on Android, `NSTimeZone` on Apple, and `Intl`
 * on JS/Wasm — so its exact wording follows the reader's OS and runtime rather
 * than a bundled table (the library favours *coverage over consistency* for
 * large translation sets). A small bundled supplement covers languages no
 * platform ships (notably Inuktitut), so those never fall back to English.
 *
 * It is **not** the generic, instant-independent name ("Eastern Time"); for that, which suits a
 * weekly schedule or opening hours that span a daylight-saving change, use [genericDisplayName].
 *
 * When no platform or supplement name applies — an unrecognized zone, or a
 * language the platform's CLDR omits — the result falls back to the English
 * name and finally to the zone's **UTC offset string** (for example `"-05:00"`)
 * rather than throwing or returning null.
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
 * @return the zone's localized name at [instant] in [width], or an English name
 *   / UTC offset string when no localized name applies.
 * @see TextWidth
 * @see genericDisplayName
 */
fun TimeZone.displayName(
    instant: Instant = Clock.System.now(),
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String = when (width) {
    TextWidth.Full -> TimeZoneAbbreviationLookup.getTimeZoneFullName(this, instant, locale)
    TextWidth.Abbreviated -> TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, instant, locale)
}

/**
 * The localized **generic** name of this time zone, which names the zone all year rather than
 * its standard or daylight time: `America/Toronto` is "Eastern Time" / "ET", in January and in
 * July alike.
 *
 * Use it where no single instant applies — a weekly schedule, or a place's opening hours, both
 * of which span a daylight-saving change and would be half wrong with [displayName]'s "Eastern
 * Standard Time".
 *
 * The name comes from the platform's own CLDR, as [displayName]'s does, so its wording follows
 * the reader's operating system and runtime. Where a zone has no generic name in [locale], the
 * platform applies CLDR's fallback: a location form ("Germany Time", "heure : Toronto"), or for
 * a zone with no location an offset ("GMT-05:00"). Where the platform gives nothing at all, the
 * result is the city part of the zone's identifier ("Toronto"), then the identifier itself.
 * Inuktitut has no bundled generic names, and gets what the platform gives. This never throws.
 *
 * ```kotlin
 * val toronto = TimeZone.of("America/Toronto")
 * toronto.genericDisplayName(locale = localeFor("en")!!)                       // "Eastern Time"
 * toronto.genericDisplayName(TextWidth.Abbreviated, localeFor("en")!!)         // "ET"
 * ```
 *
 * @param width [TextWidth.Full] for the full name (default) or [TextWidth.Abbreviated] for the
 *   abbreviation.
 * @param locale the locale whose names to use. Defaults to [Locale.current].
 * @return the zone's generic name in [width].
 * @see displayName for the specific name at an instant.
 */
fun TimeZone.genericDisplayName(
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String = platformGenericZoneName(this, width == TextWidth.Abbreviated, locale)
    ?: id.substringAfterLast('/').replace('_', ' ').ifEmpty { id }
