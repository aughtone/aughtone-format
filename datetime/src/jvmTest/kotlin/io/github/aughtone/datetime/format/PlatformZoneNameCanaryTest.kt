package io.github.aughtone.datetime.format

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Canary tests for the JVM platform CLDR (`java.time`) that the zone-name
 * migration (#16) delegates to. They lock the behaviour we rely on today so we
 * are alerted when the platform's CLDR changes — most importantly, when the
 * Inuktitut coverage hole is filled upstream and our bundled supplement can be
 * retired.
 *
 * These assert *properties* (localized vs. fallback, DST-awareness), not
 * wording that drifts between CLDR versions, except the stable English/French
 * abbreviations.
 */
class PlatformZoneNameCanaryTest {

    private val toronto = ZoneId.of("America/Toronto")
    private val winter = Instant.parse("2024-01-15T12:00:00Z") // EST / -05:00
    private val summer = Instant.parse("2024-07-15T12:00:00Z") // EDT / -04:00

    private fun specific(instant: Instant, pattern: String, locale: Locale): String =
        DateTimeFormatter.ofPattern(pattern).withLocale(locale).format(ZonedDateTime.ofInstant(instant, toronto))

    @Test
    fun englishSpecificAbbreviationsAreDstAware() {
        assertEquals("EST", specific(winter, "zzz", Locale.ENGLISH))
        assertEquals("EDT", specific(summer, "zzz", Locale.ENGLISH))
    }

    @Test
    fun frenchCanadianIsLocalizedAndDstAware() {
        val frCa = Locale.forLanguageTag("fr-CA")
        assertEquals("HNE", specific(winter, "zzz", frCa))
        assertEquals("HAE", specific(summer, "zzz", frCa))
    }

    @Test
    fun englishHasAGenericLongName() {
        // Localized generic long name exists (not a city / GMT fallback).
        val generic = toronto.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        assertEquals("Eastern Time", generic)
    }

    @Test
    fun inuktitutIsStillAHole_supplementRequired() {
        // CANARY: java.time has no Inuktitut zone data, so the short specific name
        // falls back to a GMT offset (not a localized abbreviation) and the generic
        // long name is not the English localized form. When these fail, CLDR has
        // added `iu` and the bundled `iu` supplement in #16 can be dropped for the
        // platform path. (Asserts the hole *property*, not exact strings, which
        // drift across JDK/CLDR versions.)
        val iu = Locale.forLanguageTag("iu")
        val generic = toronto.getDisplayName(TextStyle.FULL, iu)
        // The fallback (a city name or GMT offset) is all-ASCII; a real Inuktitut
        // name would be syllabics (U+1400+). This flips the day CLDR adds `iu`.
        assertTrue(generic.all { it.code < 0x80 }, "iu still falls back to ASCII (city/offset/English), got: $generic")
    }
}
