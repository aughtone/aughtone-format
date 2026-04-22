package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalTimeExtTest {
    val testTime1 = LocalTime(16,8,39)
    val testLocal = Locale("en-CA")

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
        val actual1= testTime1.format(DateTimeStyle.Long, locale = testLocal)
        assertTrue(actual1 == "4:08:39 p.m. EST" || actual1 == "4:08:39 p.m. EDT" )

        val actual2 = testTime1.format(DateTimeStyle.Long, locale = testLocal, is24HourFormat = true)
        assertTrue(actual2 == "16:08:39 EST" || actual2 == "16:08:39 EDT" )
    }

    @Test
    fun testFormatFull() {
        // the new format seems to be "America/Toronto"
        val actual1 = testTime1.format(DateTimeStyle.Full, locale = testLocal)
        assertTrue(actual1 == "4:08:39 p.m. Eastern Standard Time" || actual1 == "4:08:39 p.m. Eastern Daylight Time" )

        val actual2 = testTime1.format(DateTimeStyle.Full, locale = testLocal, is24HourFormat = true)
        assertTrue(actual2 == "16:08:39 Eastern Standard Time" || actual2 == "16:08:39 Eastern Daylight Time" )
    }
    @Test
    fun testFormatNone() {
        assertEquals("", testTime1.format(DateTimeStyle.None, locale = testLocal))
    }

}
