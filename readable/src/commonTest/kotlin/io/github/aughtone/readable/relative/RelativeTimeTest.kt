package io.github.aughtone.readable.relative

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class RelativeTimeTest {

    // Fixed reference point for deterministic tests
    private val now = Instant.fromEpochMilliseconds(1713888000000L) // 2024-04-23T16:00:00Z
    private val tz = TimeZone.UTC

    @Test
    fun testJustNow_withinDefaultThreshold() {
        assertEquals("just now", now.formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))
        assertEquals("just now", (now - 59.seconds).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testJustNow_exactlyAtThreshold_notNow() {
        // Exactly 1 minute should be "1 minute ago" or "in 1 minute"
        assertEquals("1 minute ago", (now - 60.seconds).formatReadableRelative(now = now, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testJustNow_customThreshold() {
        assertEquals("just now", (now - 8.seconds).formatReadableRelative(now = now, nowThreshold = 10.seconds, locale = Locales.English, timeZone = tz))
        assertEquals("8 seconds ago", (now - 8.seconds).formatReadableRelative(now = now, nowThreshold = 5.seconds, locale = Locales.English, timeZone = tz))
    }

    @Test
    fun testStyles() {
        assertEquals("5 days ago", (now - 5.days).formatReadableRelative(now = now, relativeDateStyle = RelativeStyle.Long, timeZone = TimeZone.UTC, locale = Locales.English, relativeThreshold = 10.days))
        // Short style in English suppresses the "ago" suffix
        assertEquals("5d", (now - 5.days).formatReadableRelative(now = now, relativeDateStyle = RelativeStyle.Short, timeZone = TimeZone.UTC, locale = Locales.English, relativeThreshold = 10.days))
    }

    @Test
    fun testNoneStyle_suppression() {
        // 5 days = 120 hours
        assertEquals("120 hours ago", (now - 5.days).formatReadableRelative(
            now = now,
            relativeDateStyle = RelativeStyle.None,
            relativeTimeStyle = RelativeStyle.Long,
            timeZone = TimeZone.UTC,
            locale = Locales.English,
            relativeThreshold = 10.days
        ))

        // 2 hours should remain 2 hours (hours is a time unit)
        assertEquals("2 hours ago", (now - 2.hours).formatReadableRelative(
            now = now,
            relativeTimeStyle = RelativeStyle.Long,
            timeZone = TimeZone.UTC,
            locale = Locales.English
        ))
    }

    @Test
    fun testDayStrings() {
        val reference = LocalDateTime(2023, 10, 27, 12, 0).toInstant(tz)
        val today = reference + 2.hours
        val tomorrow = reference + 25.hours
        val yesterday = reference - 25.hours

        // If it's the same day, Instant should return relative time (e.g. in 2 hours)
        assertEquals("in 2 hours", today.formatReadableRelative(now = reference, locale = Locales.English, timeZone = tz))
        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = reference, locale = Locales.English, timeZone = tz))
        assertEquals("Yesterday", yesterday.formatReadableRelative(now = reference, locale = Locales.English, timeZone = tz))
        
        assertEquals("dans 2 heures", today.formatReadableRelative(now = reference, locale = Locales.French, timeZone = tz))
        assertEquals("Demain", tomorrow.formatReadableRelative(now = reference, locale = Locales.French, timeZone = tz))
        assertEquals("Hier", yesterday.formatReadableRelative(now = reference, locale = Locales.French, timeZone = tz))
        
        assertEquals("oor 2 ure", today.formatReadableRelative(now = reference, locale = Locales.Afrikaans, timeZone = tz))
        assertEquals("Môre", tomorrow.formatReadableRelative(now = reference, locale = Locales.Afrikaans, timeZone = tz))
        assertEquals("Gister", yesterday.formatReadableRelative(now = reference, locale = Locales.Afrikaans, timeZone = tz))
    }

    @Test
    fun testLocalDateRelative() {
        val today = LocalDate(2023, 10, 27)
        val tomorrow = LocalDate(2023, 10, 28)
        val nextWeek = LocalDate(2023, 11, 3)
        
        assertEquals("Today", today.formatReadableRelative(now = today, locale = Locales.English))
        assertEquals("Tomorrow", tomorrow.formatReadableRelative(now = today, locale = Locales.English))
        assertEquals("in 1 week", nextWeek.formatReadableRelative(now = today, locale = Locales.English, relativeThreshold = 10.days))
    }

    @Test
    fun testLocalTimeRelative() {
        val now = LocalTime(12, 0)
        val then = LocalTime(12, 5)
        
        assertEquals("in 5 minutes", then.formatReadableRelative(now = now, locale = Locales.English))
    }

    @Test
    fun testPast_years() {
        assertEquals("2 years ago", (now - (2 * 365).days).formatReadableRelative(now = now, locale = Locales.English, relativeThreshold = Duration.INFINITE))
    }

    @Test
    fun testFrench() {
        assertEquals("il y a 8 minutes", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.French))
        assertEquals("à l'instant", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.French))
    }

    @Test
    fun testSouthAfricanEnglish_nowNow() {
        assertEquals("now now", (now - 3.seconds).formatReadableRelative(now = now, locale = Locales.SouthAfricanEnglish))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        // Test deprecated toReadableRelative overloads (locale-first and now-first)
        assertEquals("just now", now.toReadableRelative(Locales.English, now = now, timeZone = tz))
        assertEquals("5 days ago", (now - 5.days).toReadableRelative(now = now, relativeDateStyle = RelativeStyle.Long, timeZone = TimeZone.UTC, locale = Locales.English, relativeThreshold = 10.days))
        
        val today = LocalDate(2023, 10, 27)
        assertEquals("Today", today.toReadableRelative(Locales.English, now = today))

        val nowTime = LocalTime(12, 0)
        val thenTime = LocalTime(12, 5)
        assertEquals("in 5 minutes", thenTime.toReadableRelative(Locales.English, now = nowTime))
    }
}
