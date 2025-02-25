package io.github.aughtone.datetime.format

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class DurationTest {

    val testLanguageTag = "en-CA"

    @Test
    fun testFormatShortSeconds() {
        assertEquals("0s", 0.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1ms", 1.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("499ms", 499.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("500ms", 500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1s", 1000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1.5s", 1500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
    }

    @Test
    fun testFormatShortMinutes() {
        assertEquals("59s", 59000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("59.5s", 59500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1m", 60000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("2m", 120000.milliseconds.formatRelative(DateTimeStyle.SHORT,languageTag= testLanguageTag, roundToNearestUnit=false))
    }
    
    @Test
    fun testFormatShortHours() {
        assertEquals("59m", 3540000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("1h", 3600000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
        assertEquals("2d", 172800000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag, roundToNearestUnit=false))
    }

//    @Test
//    fun testFormatShortSecondsRounded() {
//        assertEquals("0s", 0.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("0s", 1.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("0s", 499.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("1s", 500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("1s", 1000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("2s", 1500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//    }
//    @Test
//    fun testFormatShortMinutesRounded() {
//        assertEquals("59s", 59000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("60s", 59500.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("1m", 60000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("2m", 120000.milliseconds.formatRelative(DateTimeStyle.SHORT,languageTag= testLanguageTag))
//    }
//    @Test
//    fun testFormatShortHoursRounded() {
//        assertEquals("59m", 3540000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("1h", 3600000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//        assertEquals("48h", 172800000.milliseconds.formatRelative(DateTimeStyle.SHORT, languageTag=testLanguageTag))
//    }

//    @Test
//    fun testFormatLongSeconds() {
//        assertEquals("0 seconds", 0.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("0 seconds", 1.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("0 seconds", 499.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("1 second", 500.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("1 second", 1000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("2 seconds", 1500.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//    }
//    @Test
//    fun testFormatLongMinutes() {
//        assertEquals("59 seconds", 59000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("60 seconds", 59500.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("1 minute", 60000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("2 minutes", 120000.milliseconds.formatRelative(DateTimeStyle.LONG,languageTag= testLanguageTag))
//    }
//    @Test
//    fun testFormatLongHours() {
//        assertEquals("59 minutes", 3540000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("1 hour", 3600000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//        assertEquals("48 hours", 172800000.milliseconds.formatRelative(DateTimeStyle.LONG, languageTag=testLanguageTag))
//    }
}
