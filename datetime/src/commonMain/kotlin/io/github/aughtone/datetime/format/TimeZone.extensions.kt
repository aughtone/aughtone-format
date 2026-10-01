@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

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
 * It is **not** the generic, instant-independent name ("Eastern Time").
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
 */
fun TimeZone.displayName(
    instant: Instant = Clock.System.now(),
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String = when (width) {
    TextWidth.Full -> TimeZoneAbbreviationLookup.getTimeZoneFullName(this, instant, locale)
    TextWidth.Abbreviated -> TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(this, instant, locale)
}
