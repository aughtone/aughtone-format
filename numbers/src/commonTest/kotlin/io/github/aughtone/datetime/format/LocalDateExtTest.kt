package io.github.aughtone.datetime.format

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateExtTest {
    val testDate1 = LocalDate(1952,1,12)
    @Test
    fun testFormatShort() {
        assertEquals("1952-01-12", testDate1.format(DateTimeStyle.Short))
    }

    @Test
    fun testFormatMedium() {
        assertEquals("Jan 12, 1952", testDate1.format(DateTimeStyle.Medium))
    }

    @Test
    fun testFormatLong() {
        assertEquals("January 12, 1952", testDate1.format(DateTimeStyle.Long))
    }

    @Test
    fun testFormatFull() {
        // XXX Review this. Maybe we don't need to spell out the CE.
        assertEquals("Saturday, January 12, 1952 Common Era", testDate1.format(DateTimeStyle.Full))
    }
    @Test
    fun testFormatNone() {
        assertEquals("", testDate1.format(DateTimeStyle.None))
    }

}
