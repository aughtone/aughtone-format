@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Cross-platform coverage canaries for the zone-name migration (#16). Unlike the
 * per-platform `PlatformZoneName*CanaryTest`s, which lock a single platform's raw
 * CLDR behaviour, these exercise the *resolver* ([TimeZone.displayName]) on every
 * target the test runs on, so a regression shows up wherever it lands.
 *
 * They guard the two failure modes that delegation introduces: losing Inuktitut
 * (which no platform ships) to a silent English fallback, and returning a blank
 * string when a platform hands back nothing.
 */
class TimeZoneCoverageTest {

    private val winter = Instant.parse("2024-01-15T12:00:00Z")
    private val summer = Instant.parse("2024-07-15T12:00:00Z")

    // Named zones need the IANA tz database, which Kotlin/JS and wasmJs only have
    // when the app bundles it; TimeZone.of throws there, so a null means "skip".
    private fun zone(id: String): TimeZone? = runCatching { TimeZone.of(id) }.getOrNull()

    // A representative spread across offsets and DST regimes.
    private val sampleZoneIds = listOf(
        "America/Toronto", "America/Los_Angeles", "Europe/Paris", "Europe/London",
        "Asia/Tokyo", "Asia/Kolkata", "Australia/Sydney", "Pacific/Auckland", "UTC",
    )

    private val iu = Locale(languageCode = "iu", displayName = "Inuktitut")

    @Test
    fun inuktitutFullNameStaysSyllabicsNeverEnglish() {
        // The bundled supplement is consulted before the platform, so Inuktitut
        // full names are Canadian syllabics (U+1400+), never a silent fall-through
        // to the platform's English. If this fails, the supplement stopped winning.
        for (id in sampleZoneIds) {
            val tz = zone(id) ?: continue
            for (instant in listOf(winter, summer)) {
                val name = tz.displayName(instant, TextWidth.Full, iu)
                assertTrue(
                    name.any { it.code in 0x1400..0x167F },
                    "iu full name for $id should be Inuktitut syllabics, got: $name",
                )
                assertTrue(
                    name.none { it.code in 'a'.code..'z'.code || it.code in 'A'.code..'Z'.code },
                    "iu full name for $id leaked ASCII (English fallback?): $name",
                )
            }
        }
    }

    @Test
    fun everyResolvedNameIsNonBlank() {
        // The silent-null trap: a platform delegate that returns null/empty must be
        // backstopped, never surfaced as a blank name. Scan zones x locales and
        // assert every resolved name is non-blank, in both widths.
        val locales = listOfNotNull(
            localeFor("en"), localeFor("fr"), localeFor("de"), localeFor("ja"), iu,
        )
        for (id in sampleZoneIds) {
            val tz = zone(id) ?: continue
            for (locale in locales) {
                for (width in TextWidth.entries) {
                    val name = tz.displayName(winter, width, locale)
                    assertTrue(
                        name.isNotBlank(),
                        "blank name for $id / ${locale.languageTag} / $width",
                    )
                }
            }
        }
    }
}
