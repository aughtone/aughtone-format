package io.github.aughtone.readable.relative

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class RelativeTimeTest {

    // Fixed reference point for deterministic tests
    private val now = Clock.System.now()

    @Test
    fun testJustNow_withinDefaultThreshold() {
        assertEquals("just now", (now - 3.seconds).toReadableRelativeTime(Locales.English, now))
        assertEquals("just now", (now + 3.seconds).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testJustNow_exactlyAtThreshold_notNow() {
        // 5s is NOT within the threshold (< 5s), so it should format as seconds
        assertEquals("5 seconds ago", (now - 5.seconds).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testJustNow_customThreshold() {
        assertEquals("just now", (now - 8.seconds).toReadableRelativeTime(Locales.English, now, 10.seconds))
    }

    @Test
    fun testPast_minutes() {
        assertEquals("8 minutes ago", (now - 8.minutes).toReadableRelativeTime(Locales.English, now))
        assertEquals("2 hours ago", (now - 134.minutes).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testFuture_minutes() {
        assertEquals("in 8 minutes", (now + 8.minutes).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testPast_days() {
        assertEquals("1 day ago", (now - 1.days).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testFuture_weeks() {
        assertEquals("in 1 week", (now + 7.days).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testPast_years() {
        assertEquals("2 years ago", (now - (2 * 365).days).toReadableRelativeTime(Locales.English, now))
    }

    @Test
    fun testFrench() {
        assertEquals("il y a 8 minutes", (now - 8.minutes).toReadableRelativeTime(Locales.French, now))
        assertEquals("dans 8 minutes", (now + 8.minutes).toReadableRelativeTime(Locales.French, now))
        assertEquals("à l'instant", (now - 3.seconds).toReadableRelativeTime(Locales.French, now))
    }

    @Test
    fun testGerman() {
        assertEquals("vor 8 Minuten", (now - 8.minutes).toReadableRelativeTime(Locales.German, now))
        assertEquals("in 8 Minuten", (now + 8.minutes).toReadableRelativeTime(Locales.German, now))
        assertEquals("gerade eben", (now - 3.seconds).toReadableRelativeTime(Locales.German, now))
    }

    @Test
    fun testSpanish() {
        assertEquals("hace 8 minutos", (now - 8.minutes).toReadableRelativeTime(Locales.Spanish, now))
        assertEquals("en 8 minutos", (now + 8.minutes).toReadableRelativeTime(Locales.Spanish, now))
    }

    @Test
    fun testJapanese() {
        assertEquals("8分前", (now - 8.minutes).toReadableRelativeTime(Locales.Japanese, now))
        assertEquals("8分後", (now + 8.minutes).toReadableRelativeTime(Locales.Japanese, now))
        assertEquals("たった今", (now - 3.seconds).toReadableRelativeTime(Locales.Japanese, now))
    }

    @Test
    fun testRussian() {
        // Russian plural for 8 (minutes) is "минут" (genitive plural, 5-20 range)
        assertEquals("8 минут назад", (now - 8.minutes).toReadableRelativeTime(Locales.Russian, now))
        assertEquals("через 8 минут", (now + 8.minutes).toReadableRelativeTime(Locales.Russian, now))
    }

    @Test
    fun testSouthAfricanEnglish_nowNow() {
        // "just now" means "later" in SA English — threshold phrase must be "now now"
        assertEquals("now now", (now - 3.seconds).toReadableRelativeTime(Locales.SouthAfricanEnglish, now))
        assertEquals("now now", (now + 3.seconds).toReadableRelativeTime(Locales.SouthAfricanEnglish, now))
        // Regular phrasing inherits from "en"
        assertEquals("8 minutes ago", (now - 8.minutes).toReadableRelativeTime(Locales.SouthAfricanEnglish, now))
    }

    @Test
    fun testBcp47Fallback() {
        // fr-CA has no specific config, should fall back to "fr"
        val frCA = io.github.aughtone.types.locale.Locale(
            languageCode = "fr", regionCode = "CA", displayName = "French (Canada)"
        )
        assertEquals("il y a 8 minutes", (now - 8.minutes).toReadableRelativeTime(frCA, now))
    }
}
