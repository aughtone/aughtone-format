package io.github.aughtone.datetime.format

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class DurationTest {

    val testLanguageTag = "en-CA"

    @Test
    fun testFormatShortMediumSeconds() {
        assertEquals("0s", 0.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1ms", 1.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("499ms", 499.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("500ms", 500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1s", 1000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1.5s", 1500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
    }

    @Test
    fun testFormatShortMediumMinutes() {
        assertEquals("59s", 59000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("59.5s", 59500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1m", 60000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("2m", 120000.milliseconds.formatRelative(DateTimeStyle.SHORT,languageTag= testLanguageTag, roundToNearestUnit=false))
    }

    @Test
    fun testFormatShortMediumHours() {
        assertEquals("59m", 3540000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1h", 3600000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("2d", 172800000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
    }
//
//    @Test
//    fun testFormatMediumSeconds() {
//        assertEquals("0 secs", 0.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("0 secs", 1.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("0 secs", 499.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("1 sec", 500.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("1 sec", 1000.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("2 secs", 1500.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//    }
//    @Test
//    fun testFormatMediumMinutes() {
//        assertEquals("59 secs", 59000.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("60 secs", 59500.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("1 min", 60000.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("2 mins", 120000.milliseconds.formatRelative(DateTimeStyle.MEDIUM,languageTag= testLanguageTag))
//    }
//    @Test
//    fun testFormatMediumHours() {
//        assertEquals("59 mins", 3540000.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("1 hr", 3600000.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//        assertEquals("2 days", 172800000.milliseconds.formatRelative(DateTimeStyle.MEDIUM, languageTag=testLanguageTag))
//    }

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
        assertEquals("2 days", 172800000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
    }
}
