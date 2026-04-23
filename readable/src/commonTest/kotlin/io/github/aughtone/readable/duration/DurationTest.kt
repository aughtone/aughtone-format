package io.github.aughtone.readable.duration

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DurationTest {

    @Test
    fun testScaling() {
        assertEquals("5 seconds", 5.seconds.toReadableString(Locales.English))
        assertEquals("59 seconds", 59.seconds.toReadableString(Locales.English))
        assertEquals("1 minute", 60.seconds.toReadableString(Locales.English))
        assertEquals("1 hour", 60.minutes.toReadableString(Locales.English))
        assertEquals("1 day", 24.hours.toReadableString(Locales.English))
        assertEquals("6 days", 6.days.toReadableString(Locales.English))
        assertEquals("1 week", 7.days.toReadableString(Locales.English))
        assertEquals("29 days", 29.days.toReadableString(Locales.English))
        assertEquals("1 month", 30.days.toReadableString(Locales.English))
    }

    @Test
    fun testRounding() {
        // 8 days / 7 = 1.14 -> 1 week
        assertEquals("1 week", 8.days.toReadableString(Locales.English))
        // 10 days / 7 = 1.42 -> 1 week
        assertEquals("1 week", 10.days.toReadableString(Locales.English))
        // 11 days / 7 = 1.57 -> 2 weeks
        assertEquals("2 weeks", 11.days.toReadableString(Locales.English))
    }

    @Test
    fun testLargeDurations() {
        // 544 hours = 22.66 days = 3.23 weeks -> 3 weeks
        assertEquals("3 weeks", 544.hours.toReadableString(Locales.English))
    }
}
