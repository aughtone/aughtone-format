@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

/**
 * The platform CLDR's localized name for [timeZone] **at [instant]**, or `null`
 * when the platform has no localized name for that zone in [locale].
 *
 * This is the delegation point for the zone-name migration (#16): rather than
 * bundle a large all-locale translation table, each target reads the CLDR its
 * own runtime already carries — `java.time` on JVM, `java.util.TimeZone`
 * (ICU-backed) on Android, `NSTimeZone` on Apple, and `Intl.DateTimeFormat` on
 * JS/Wasm. The name is *specific* (daylight- vs. standard-time aware), which is
 * why it needs the instant.
 *
 * A `null` return means genuine absence — the platform has no name for this
 * zone/locale (an unrecognized zone, or a language the platform's CLDR omits) —
 * **not** a swallowed error and not an offset placeholder dressed up as a name.
 * Callers treat `null` as "fall back" (to the bundled supplement or an English
 * name), so an actual must never return a value it did not really localize.
 *
 * @param timeZone the zone to name.
 * @param instant the moment that decides standard vs. daylight time.
 * @param abbreviated `true` for the short form ("EST"), `false` for the full
 *   name ("Eastern Standard Time").
 * @param locale the language to localize into.
 * @return the platform's localized name, or `null` when the platform has none.
 */
internal expect fun platformZoneName(
    timeZone: TimeZone,
    instant: Instant,
    abbreviated: Boolean,
    locale: Locale,
): String?

/**
 * The platform CLDR's localized **generic** name for [timeZone] — the one that names the zone
 * all year rather than its standard or daylight time ("Eastern Time" / "ET") — or `null` when
 * the platform gives none. Each platform applies CLDR's own fallback when a zone has no generic
 * name in [locale], usually a location form ("Germany Time", "heure : Toronto") or, for a zone
 * with no location, an offset ("GMT-05:00"); that is returned as given.
 *
 * @param timeZone the zone to name.
 * @param abbreviated `true` for the short form ("ET"), `false` for the full name ("Eastern Time").
 * @param locale the language to localize into.
 * @return the platform's generic name, or `null` when the platform has none.
 */
internal expect fun platformGenericZoneName(
    timeZone: TimeZone,
    abbreviated: Boolean,
    locale: Locale,
): String?
