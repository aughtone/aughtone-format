package io.github.aughtone.datetime.format

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class DurationTest {

    val testLanguageTag = "en-CA"

    @Test
    fun testFormatLongSeconds() {
        assertEquals("0 seconds", 0.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("0 seconds", 1.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("0 seconds", 499.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("1 second", 500.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("1 second", 1000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("2 seconds", 1500.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
    }
    @Test
    fun testFormatLongMinutes() {
        assertEquals("59 seconds", 59000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("60 seconds", 59500.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("1 minute", 60000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("2 minutes", 120000.milliseconds.formatRelative(DateTimeStyle.LONG,languageTag= testLanguageTag))
    }
    @Test
    fun testFormatLongHours() {
        assertEquals("59 minutes", 3540000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("1 hour", 3600000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
        assertEquals("48 hours", 172800000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
    }
}
