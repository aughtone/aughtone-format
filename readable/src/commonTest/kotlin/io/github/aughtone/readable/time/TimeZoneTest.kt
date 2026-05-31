package io.github.aughtone.readable.time

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.locale.Locale
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeZoneTest {

    private val utcTz = TimeZone.UTC
    
    // 2024-01-15T12:00:00Z
    private val winterInstant = Instant.parse("2024-01-15T12:00:00Z")
    
    // 2024-07-15T12:00:00Z
    private val summerInstant = Instant.parse("2024-07-15T12:00:00Z")

    @Test
    fun testFormatReadable_abbreviation_instant() {
        assertEquals("UTC", utcTz.formatReadable(instant = winterInstant, locale = Locales.English))
        assertEquals("UTC", utcTz.formatReadable(instant = summerInstant, locale = Locales.English))
    }

    @Test
    fun testFormatReadable_fullName_instant() {
        assertEquals("Coordinated Universal Time", utcTz.formatReadable(instant = winterInstant, useFullName = true, locale = Locales.English))
        assertEquals("Coordinated Universal Time", utcTz.formatReadable(instant = summerInstant, useFullName = true, locale = Locales.English))
    }

    @Test
    fun testFormatReadable_abbreviation_offset() {
        assertEquals("UTC", utcTz.formatReadable(offset = UtcOffset.ZERO, locale = Locales.English))
    }

    @Test
    fun testFormatReadable_fullName_offset() {
        assertEquals("Coordinated Universal Time", utcTz.formatReadable(offset = UtcOffset.ZERO, useFullName = true, locale = Locales.English))
    }

    @Test
    fun testFormatReadable_fallback_unmappedOffset() {
        // Offset +01:00 is not mapped under UTC in timeZoneAbbreviatedMap
        val offset = UtcOffset(hours = 1)
        assertEquals("+01:00", utcTz.formatReadable(offset = offset, locale = Locales.English))
        assertEquals("+01:00", utcTz.formatReadable(offset = offset, useFullName = true, locale = Locales.English))
    }

    @Test
    fun testFormatReadable_localized_french() {
        val tz = try {
            TimeZone.of("America/New_York")
        } catch (e: Exception) {
            // Skip IANA timezone tests on environments without full timezone DB (e.g. JS/Wasm browser runner)
            return
        }

        val estOffset = UtcOffset(hours = -5)
        val edtOffset = UtcOffset(hours = -4)

        // French locale translations
        val localeFr = Locale(languageCode = "fr", displayName = "French")
        assertEquals("HNE", tz.formatReadable(offset = estOffset, locale = localeFr))
        assertEquals("HAE", tz.formatReadable(offset = edtOffset, locale = localeFr))
        assertEquals("Heure normale de l'Est nord-américain", tz.formatReadable(offset = estOffset, useFullName = true, locale = localeFr))
        assertEquals("Heure d'été de l'Est nord-américain", tz.formatReadable(offset = edtOffset, useFullName = true, locale = localeFr))
        
        // French-Canada (fr-CA) resolves directly to fr-CA (with Canadian French names)
        val localeFrCa = Locale(languageCode = "fr", regionCode = "CA", displayName = "French (Canada)")
        assertEquals("HNE", tz.formatReadable(offset = estOffset, locale = localeFrCa))
        assertEquals("Heure normale de l'Est", tz.formatReadable(offset = estOffset, useFullName = true, locale = localeFrCa))

        // French-Belgium (fr-BE) is not in the map, so it falls back to French (fr)
        val localeFrBe = Locale(languageCode = "fr", regionCode = "BE", displayName = "French (Belgium)")
        assertEquals("HNE", tz.formatReadable(offset = estOffset, locale = localeFrBe))
        assertEquals("Heure normale de l'Est nord-américain", tz.formatReadable(offset = estOffset, useFullName = true, locale = localeFrBe))
    }

    @Test
    fun testFormatReadable_localized_spanish() {
        val tz = try {
            TimeZone.of("America/New_York")
        } catch (e: Exception) {
            // Skip IANA timezone tests on environments without full timezone DB (e.g. JS/Wasm browser runner)
            return
        }

        val estOffset = UtcOffset(hours = -5)
        val edtOffset = UtcOffset(hours = -4)

        // Spanish locale translations
        val localeEs = Locale(languageCode = "es", displayName = "Spanish")
        assertEquals("EST", tz.formatReadable(offset = estOffset, locale = localeEs))
        assertEquals("EDT", tz.formatReadable(offset = edtOffset, locale = localeEs))
        assertEquals("Hora estándar oriental", tz.formatReadable(offset = estOffset, useFullName = true, locale = localeEs))
        assertEquals("Hora de verano oriental", tz.formatReadable(offset = edtOffset, useFullName = true, locale = localeEs))
    }

    @Test
    fun testFormatReadable_localized_german() {
        val tz = try {
            TimeZone.of("Europe/Berlin")
        } catch (e: Exception) {
            return
        }

        val cetOffset = UtcOffset(hours = 1)
        val cestOffset = UtcOffset(hours = 2)

        // German locale translations
        val localeDe = Locale(languageCode = "de", displayName = "German")
        assertEquals("Mitteleuropäische Normalzeit", tz.formatReadable(offset = cetOffset, useFullName = true, locale = localeDe))
        assertEquals("Mitteleuropäische Sommerzeit", tz.formatReadable(offset = cestOffset, useFullName = true, locale = localeDe))
    }
}
