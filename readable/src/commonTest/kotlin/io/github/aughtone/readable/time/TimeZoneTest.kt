package io.github.aughtone.readable.time

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.locale.Locale
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TimeZoneTest {

    private val utcTz = TimeZone.UTC

    // 2024-01-15T12:00:00Z
    private val winterInstant = Instant.parse("2024-01-15T12:00:00Z")

    // 2024-07-15T12:00:00Z
    private val summerInstant = Instant.parse("2024-07-15T12:00:00Z")

    // Instant-based names come from each platform's CLDR (coverage over
    // consistency), so UTC's specific name varies: "UTC" / "Coordinated Universal
    // Time" on java.time and Intl, "GMT" / "Greenwich Mean Time" on Apple.
    private val utcAbbr = setOf("UTC", "GMT")
    private val utcFull = setOf("Coordinated Universal Time", "Greenwich Mean Time")

    // Named zones need the IANA tz database, unavailable on some JS/Wasm runners.
    private fun zone(id: String): TimeZone? = runCatching { TimeZone.of(id) }.getOrNull()

    @Test
    fun testFormatReadable_abbreviation_instant() {
        assertTrue(utcTz.formatReadable(instant = winterInstant, locale = Locales.English) in utcAbbr)
        assertTrue(utcTz.formatReadable(instant = summerInstant, locale = Locales.English) in utcAbbr)
    }

    @Test
    fun testFormatReadable_fullName_instant() {
        assertTrue(utcTz.formatReadable(instant = winterInstant, useFullName = true, locale = Locales.English) in utcFull)
        assertTrue(utcTz.formatReadable(instant = summerInstant, useFullName = true, locale = Locales.English) in utcFull)
    }

    // The offset-only overload does not delegate to the platform (an offset has no
    // instant), so it uses the bundled English scaffolding and is consistent
    // across every platform.
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
    fun testFormatReadable_inuktitut_offset_fromSupplement() {
        // Inuktitut is not shipped by any platform CLDR, so it comes from the
        // bundled supplement on every platform — even the offset-only overload,
        // and even though no instant is involved.
        val iu = Locale(languageCode = "iu", displayName = "Inuktitut")
        val name = utcTz.formatReadable(offset = UtcOffset.ZERO, useFullName = true, locale = iu)
        assertTrue(name.any { it.code in 0x1400..0x167F }, "expected Inuktitut syllabics, got: $name")
    }

    @Test
    fun testFormatReadable_localized_french_instant() {
        // The instant overload delegates to the platform CLDR, which localizes:
        // the French name is non-blank and differs from the English one. Exact
        // wording follows the platform, so this asserts the property, not a string.
        val tz = zone("America/New_York") ?: return
        val fr = Locale(languageCode = "fr", displayName = "French")
        val frFull = tz.formatReadable(instant = winterInstant, useFullName = true, locale = fr)
        val enFull = tz.formatReadable(instant = winterInstant, useFullName = true, locale = Locales.English)
        assertTrue(frFull.isNotBlank(), "expected a French zone name")
        assertNotEquals(enFull, frFull, "French full name should differ from English")
    }
}
