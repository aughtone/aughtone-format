package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalTimeExtTest {
    val testTimeZone = TimeZone.UTC
    val testTime1 = LocalTime(16,8,39)
    val testLocal = localeFor("en-CA")!!

    @Test
    fun testFormatShort() {
        assertEquals("4:08 p.m.", testTime1.format(DateTimeStyle.Short, locale = testLocal, timeZone = testTimeZone))
        assertEquals("16:08", testTime1.format(DateTimeStyle.Short, locale = testLocal, timeZone = testTimeZone, is24HourFormat = true))
    }

    @Test
    fun testFormatMedium() {
        assertEquals("4:08:39 p.m.", testTime1.format(DateTimeStyle.Medium, locale = testLocal, timeZone = testTimeZone))
        assertEquals("16:08:39", testTime1.format(DateTimeStyle.Medium, locale = testLocal, timeZone = testTimeZone, is24HourFormat = true))
    }

    // Zone names now come from each platform's CLDR (coverage over consistency),
    // so the appended zone token varies: java.time and Intl render UTC as
    // "UTC" / "Coordinated Universal Time", while Apple's Foundation renders it as
    // "GMT" / "Greenwich Mean Time". These tests assert the time portion exactly
    // and accept either UTC-family wording for the zone. The zone-name lexicon
    // itself is covered by TimeZoneDisplayNameTest / TimeZoneCoverageTest.
    private val utcAbbr = setOf("UTC", "GMT")
    private val utcFull = setOf("Coordinated Universal Time", "Greenwich Mean Time")

    @Test
    fun testFormatLong() {
        val actual1 = testTime1.format(DateTimeStyle.Long, locale = testLocal, timeZone = testTimeZone)
        assertTrue(actual1.startsWith("4:08:39 p.m. "), "unexpected time portion: $actual1")
        assertTrue(actual1.removePrefix("4:08:39 p.m. ") in utcAbbr, "unexpected zone: $actual1")

        val actual2 = testTime1.format(DateTimeStyle.Long, locale = testLocal, timeZone = testTimeZone, is24HourFormat = true)
        assertTrue(actual2.startsWith("16:08:39 "), "unexpected time portion: $actual2")
        assertTrue(actual2.removePrefix("16:08:39 ") in utcAbbr, "unexpected zone: $actual2")
    }

    @Test
    fun testFormatFull() {
        val actual1 = testTime1.format(DateTimeStyle.Full, locale = testLocal, timeZone = testTimeZone)
        assertTrue(actual1.startsWith("4:08:39 p.m. "), "unexpected time portion: $actual1")
        assertTrue(actual1.removePrefix("4:08:39 p.m. ") in utcFull, "unexpected zone: $actual1")

        val actual2 = testTime1.format(DateTimeStyle.Full, locale = testLocal, timeZone = testTimeZone, is24HourFormat = true)
        assertTrue(actual2.startsWith("16:08:39 "), "unexpected time portion: $actual2")
        assertTrue(actual2.removePrefix("16:08:39 ") in utcFull, "unexpected zone: $actual2")
    }
    @Test
    fun testFormatNone() {
        assertEquals("", testTime1.format(DateTimeStyle.None, locale = testLocal, timeZone = testTimeZone))
    }

    @Test
    fun testFormatLanguageOnlyFallback() {
        val esLocal = localeFor("es")!!
        // Spanish short format for 16:08 should use "p. m." from language-only lookup
        assertEquals("4:08 p. m.", testTime1.format(DateTimeStyle.Short, locale = esLocal, timeZone = testTimeZone, is24HourFormat = false))
    }
}
