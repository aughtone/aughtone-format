package io.github.aughtone.datetime.format

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class StringExtTest {
    @Test
    fun testFormatOneParam() {
        assertEquals("hello world", "hello %1".format("world"))
    }

    @Test
    fun testFormatTwoParams() {
        assertEquals("hello world car", "hello %1 %2".format("world", "car"))
    }

    @Test
    fun testFormatTwoParamsTwice() {
        assertEquals("hello world car world", "hello %1 %2 %1".format("world", "car"))
    }

    @Test
    fun testFormatMissingPlaceholderIsIgnored() {
        assertEquals("hello world", "hello %1".format("world", "car"))
    }

    @Test
    fun testFormatExtraPlaceholderNotFormatted() {
        assertEquals("hello world car %3", "hello %1 %2 %3".format("world", "car"))
    }
}
