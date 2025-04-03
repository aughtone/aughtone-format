package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalTimeExtTest {
    val testTime1 = LocalTime(16,8,39)
    val testLocal = Locale("en-CA")

//    @Test
//    fun testTimeZoneIds() {
//        TimeZone.availableZoneIds.forEach {
//            println(it)
//        }
//        fail("stop the test")
//    }

    @Test
    fun testFormatShort() {
        assertEquals("4:08 p.m.", testTime1.format(DateTimeStyle.Short, locale = testLocal))
        assertEquals("16:08", testTime1.format(DateTimeStyle.Short, locale = testLocal, is24HourFormat = true))
    }

    @Test
    fun testFormatMedium() {
        assertEquals("4:08:39 p.m.", testTime1.format(DateTimeStyle.Medium, locale = testLocal))
        assertEquals("16:08:39", testTime1.format(DateTimeStyle.Medium, locale = testLocal, is24HourFormat = true))
    }

    @Test
    fun testFormatLong() {
        assertEquals("4:08:39 p.m. EST", testTime1.format(DateTimeStyle.Long, locale = testLocal))
        assertEquals("16:08:39 EST", testTime1.format(DateTimeStyle.Long, locale = testLocal, is24HourFormat = true))
    }

    @Test
    fun testFormatFull() {
        // the new format seems to be "America/Toronto"
        assertEquals("4:08:39 p.m. Eastern Standard Time", testTime1.format(DateTimeStyle.Full, locale = testLocal))
        assertEquals("16:08:39 Eastern Standard Time", testTime1.format(DateTimeStyle.Full, locale = testLocal, is24HourFormat = true))
    }
    @Test
    fun testFormatNone() {
        assertEquals("", testTime1.format(DateTimeStyle.None, locale = testLocal))
    }

}
