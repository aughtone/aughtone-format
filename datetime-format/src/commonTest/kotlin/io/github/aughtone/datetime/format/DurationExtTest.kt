package io.github.aughtone.datetime.format

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes

class DurationExtTest {

    val testLanguageTag = "en-CA"

    @Test
    fun testFormatShortMediumSeconds() {
        assertEquals("0s", 0.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("1ms", 1.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("499ms", 499.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("500ms", 500.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("1s", 1000.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("1.5s", 1500.milliseconds.formatRelative(RelativeStyle.SHORT))
    }

    @Test
    fun testFormatShortMediumMinutes() {
        assertEquals("59s", 59000.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("59.5s", 59500.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("1m", 60000.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("2m", 120000.milliseconds.formatRelative(RelativeStyle.SHORT))
    }

    @Test
    fun testFormatShortMediumHours() {
        assertEquals("59m", 3540000.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("1h", 3600000.milliseconds.formatRelative(RelativeStyle.SHORT))
        assertEquals("2d", 172800000.milliseconds.formatRelative(RelativeStyle.SHORT))
    }

    @Test
    fun testFormatLongSeconds() {
        assertEquals("0 seconds", 0.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("0 seconds", 1.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("0 seconds", 499.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("1 second", 500.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("1 second", 1000.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("2 seconds", 1500.milliseconds.formatRelative(RelativeStyle.LONG))
    }

    @Test
    fun testFormatLongMinutes() {
        assertEquals("59 seconds", 59000.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("60 seconds", 59500.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("1 minute", 60000.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("2 minutes", 120000.milliseconds.formatRelative(RelativeStyle.LONG))
    }

    @Test
    fun testFormatLongHours() {
        assertEquals("59 minutes", 3540000.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("1 hour", 3600000.milliseconds.formatRelative(RelativeStyle.LONG))
        assertEquals("2 days", 172800000.milliseconds.formatRelative(RelativeStyle.LONG))
    }

    @Test
    fun testFormatInThePast() {
        assertEquals(
            "2 days ago",
            2.days.formatRelative(style = RelativeStyle.LONG, relativeTime = RelativeTime.Past)
        )

        assertEquals(
            "5 minutes ago",
            5.minutes.formatRelative(style = RelativeStyle.LONG, relativeTime = RelativeTime.Past)
        )

        assertEquals(
            "2d ago",
            2.days.formatRelative(style = RelativeStyle.SHORT, relativeTime = RelativeTime.Past)
        )

        assertEquals(
            "5m ago",
            5.minutes.formatRelative(style = RelativeStyle.SHORT, relativeTime = RelativeTime.Past)
        )
    }

    @Test
    fun testFormatInTheFuture() {
        assertEquals(
            "in 2 days",
            2.days.formatRelative(style = RelativeStyle.LONG, relativeTime = RelativeTime.Future)
        )

        assertEquals(
            "in 5 minutes",
            5.minutes.formatRelative(style = RelativeStyle.LONG, relativeTime = RelativeTime.Future)
        )

        assertEquals(
            "in 2d",
            2.days.formatRelative(style = RelativeStyle.SHORT, relativeTime = RelativeTime.Future)
        )

        assertEquals(
            "in 5m",
            5.minutes.formatRelative(
                style = RelativeStyle.SHORT,
                relativeTime = RelativeTime.Future
            )
        )
    }
}
