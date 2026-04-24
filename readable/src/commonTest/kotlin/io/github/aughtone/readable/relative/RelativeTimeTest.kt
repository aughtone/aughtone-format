package io.github.aughtone.readable.relative

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlinx.datetime.*

class RelativeTimeTest {

    // Fixed reference point for deterministic tests
    private val now = Instant.fromEpochMilliseconds(1713888000000L) // 2024-04-23T16:00:00Z
    private val tz = TimeZone.UTC

    @Test
    fun testJustNow_withinDefaultThreshold() {
        assertEquals("just now", now.toReadableRelative(Locales.English, now = now, timeZone = tz))
        assertEquals("just now", (now - 59.seconds).toReadableRelative(Locales.English, now = now, timeZone = tz))
    }

    @Test
    fun testJustNow_exactlyAtThreshold_notNow() {
        // Exactly 1 minute should be "1 minute ago" or "in 1 minute"
        assertEquals("1 minute ago", (now - 60.seconds).toReadableRelative(Locales.English, now = now, timeZone = tz))
    }

    @Test
    fun testJustNow_customThreshold() {
        assertEquals("just now", (now - 8.seconds).toReadableRelative(Locales.English, now = now, nowThreshold = 10.seconds, timeZone = tz))
        assertEquals("8 seconds ago", (now - 8.seconds).toReadableRelative(Locales.English, now = now, nowThreshold = 5.seconds, timeZone = tz))
    }

    @Test
    fun testStyles() {
        assertEquals("5 days ago", (now - 5.days).toReadableRelative(Locales.English, dateStyle = RelativeStyle.Long, timeZone = TimeZone.UTC, now = now))
        // Short style in English suppresses the "ago" suffix
        assertEquals("5d", (now - 5.days).toReadableRelative(Locales.English, dateStyle = RelativeStyle.Short, timeZone = TimeZone.UTC, now = now))
    }

    @Test
    fun testNoneStyle_suppression() {
        // 5 days = 120 hours
        assertEquals("120 hours ago", (now - 5.days).toReadableRelative(
            Locales.English,
            dateStyle = RelativeStyle.None,
            timeStyle = RelativeStyle.Long,
            timeZone = TimeZone.UTC,
            now = now
        ))

        // 2 hours should remain 2 hours (hours is a time unit)
        assertEquals("2 hours ago", (now - 2.hours).toReadableRelative(
            Locales.English,
            timeStyle = RelativeStyle.Long,
            timeZone = TimeZone.UTC,
            now = now
        ))
    }

    @Test
    fun testDayStrings() {
        val reference = LocalDateTime(2023, 10, 27, 12, 0).toInstant(tz)
        val today = reference + 2.hours
        val tomorrow = reference + 25.hours
        val yesterday = reference - 25.hours

        // If it's the same day, Instant should return relative time (e.g. in 2 hours)
        assertEquals("in 2 hours", today.toReadableRelative(Locales.English, now = reference, timeZone = tz))
        assertEquals("Tomorrow", tomorrow.toReadableRelative(Locales.English, now = reference, timeZone = tz))
        assertEquals("Yesterday", yesterday.toReadableRelative(Locales.English, now = reference, timeZone = tz))
        
        assertEquals("dans 2 heures", today.toReadableRelative(Locales.French, now = reference, timeZone = tz))
        assertEquals("Demain", tomorrow.toReadableRelative(Locales.French, now = reference, timeZone = tz))
        assertEquals("Hier", yesterday.toReadableRelative(Locales.French, now = reference, timeZone = tz))
        
        assertEquals("oor 2 ure", today.toReadableRelative(Locales.Afrikaans, now = reference, timeZone = tz))
        assertEquals("Môre", tomorrow.toReadableRelative(Locales.Afrikaans, now = reference, timeZone = tz))
        assertEquals("Gister", yesterday.toReadableRelative(Locales.Afrikaans, now = reference, timeZone = tz))
    }

    @Test
    fun testLocalDateRelative() {
        val today = LocalDate(2023, 10, 27)
        val tomorrow = LocalDate(2023, 10, 28)
        val nextWeek = LocalDate(2023, 11, 3)
        
        assertEquals("Today", today.toReadableRelative(Locales.English, now = today))
        assertEquals("Tomorrow", tomorrow.toReadableRelative(Locales.English, now = today))
        assertEquals("in 1 week", nextWeek.toReadableRelative(Locales.English, now = today))
    }

    @Test
    fun testLocalTimeRelative() {
        val now = LocalTime(12, 0)
        val then = LocalTime(12, 5)
        
        assertEquals("in 5 minutes", then.toReadableRelative(Locales.English, now = now))
    }

    @Test
    fun testPast_years() {
        assertEquals("2 years ago", (now - (2 * 365).days).toReadableRelative(Locales.English, now = now))
    }

    @Test
    fun testFrench() {
        assertEquals("il y a 8 minutes", (now - 8.minutes).toReadableRelative(Locales.French, now = now))
        assertEquals("à l'instant", (now - 3.seconds).toReadableRelative(Locales.French, now = now))
    }

    @Test
    fun testSouthAfricanEnglish_nowNow() {
        assertEquals("now now", (now - 3.seconds).toReadableRelative(Locales.SouthAfricanEnglish, now = now))
    }
}
