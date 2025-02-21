package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalTimeExtTest {
    val testTime1 = LocalTime(16,8,39)
    val testLocal = Locale("en-CA")
    @Test
    fun testFormatShort() {
        assertEquals("4:08 p.m.", testTime1.format(DateTimeStyle.SHORT, locale = testLocal))
        assertEquals("16:08", testTime1.format(DateTimeStyle.SHORT, locale = testLocal, use24HourClock = true))
    }

    @Test
    fun testFormatMedium() {
        // XXX adds the second? Maybe medium means something else?
        assertEquals("4:08:39 p.m.", testTime1.format(DateTimeStyle.MEDIUM, locale = testLocal))
        assertEquals("16:08:39", testTime1.format(DateTimeStyle.MEDIUM, locale = testLocal, use24HourClock = true))
    }

    @Test
    fun testFormatLong() {
        // XXX Not sure I like how the time zone is coming out.
        assertEquals("4:08:39 p.m. EST", testTime1.format(DateTimeStyle.LONG, locale = testLocal))
        assertEquals("16:08:39 EST", testTime1.format(DateTimeStyle.LONG, locale = testLocal, use24HourClock = true))
    }

    @Test
    fun testFormatFull() {
        // XXX Same as long? needs work!
        assertEquals("4:08:39 p.m. Eastern Standard Time", testTime1.format(DateTimeStyle.FULL, locale = testLocal))
        assertEquals("16:08:39 p.m. Eastern Standard Time", testTime1.format(DateTimeStyle.FULL, locale = testLocal, use24HourClock = true))
    }
    @Test
    fun testFormatNone() {
        assertEquals("", testTime1.format(DateTimeStyle.NONE, locale = testLocal))
    }

}
