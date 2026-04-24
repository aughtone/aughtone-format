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

    @Test
    fun testFormatLong() {
        val actual1= testTime1.format(DateTimeStyle.Long, locale = testLocal, timeZone = testTimeZone)
        assertEquals("4:08:39 p.m. UTC", actual1)

        val actual2 = testTime1.format(DateTimeStyle.Long, locale = testLocal, timeZone = testTimeZone, is24HourFormat = true)
        assertEquals("16:08:39 UTC", actual2)
    }

    @Test
    fun testFormatFull() {
        val actual1 = testTime1.format(DateTimeStyle.Full, locale = testLocal, timeZone = testTimeZone)
        assertEquals("4:08:39 p.m. Coordinated Universal Time", actual1)

        val actual2 = testTime1.format(DateTimeStyle.Full, locale = testLocal, timeZone = testTimeZone, is24HourFormat = true)
        assertEquals("16:08:39 Coordinated Universal Time", actual2)
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
