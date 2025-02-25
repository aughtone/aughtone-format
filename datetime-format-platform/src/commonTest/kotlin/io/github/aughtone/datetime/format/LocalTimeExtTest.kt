package io.github.aughtone.datetime.format

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class LocalTimeExtTest {
    val testDate1 = LocalDate(2004,1,1)

    // XXX We can't actually unit test this unless we take out the native formatting.
    //  Put these back in when the split happens.

//    @Test
//    fun testFormatShort() {
//        assertEquals("", testDate1.formatWith(DateTimeStyle.SHORT))
//    }
//
//    @Test
//    fun testFormatMedium() {
//        assertEquals("", testDate1.formatWith(DateTimeStyle.MEDIUM))
//    }
//
//    @Test
//    fun testFormatFull() {
//        assertEquals("", testDate1.formatWith(DateTimeStyle.FULL))
//    }
//    @Test
//    fun testFormatNone() {
//        assertEquals("", testDate1.formatWith(DateTimeStyle.NONE))
//    }

}
