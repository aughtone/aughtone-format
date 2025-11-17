package io.github.aughtone.toolbox

import kotlin.test.Test
import kotlin.test.assertEquals

class LongExtensionsTest {

    @Test
    fun `test numbers less than 1000`() {
        assertEquals("0", 0L.toHumanReadableString())
        assertEquals("37", 37L.toHumanReadableString())
        assertEquals("999", 999L.toHumanReadableString())
    }

    @Test
    fun `test thousands`() {
        assertEquals("1K", 1000L.toHumanReadableString())
        assertEquals("1.2K", 1234L.toHumanReadableString())
        assertEquals("999.9K", 999999L.toHumanReadableString())
    }

    @Test
    fun `test millions`() {
        assertEquals("1M", 1000000L.toHumanReadableString())
        assertEquals("1.2M", 1234567L.toHumanReadableString())
    }

    @Test
    fun `test billions`() {
        assertEquals("1G", 1000000000L.toHumanReadableString())
    }

    @Test
    fun `test trillions`() {
        assertEquals("1T", 1000000000000L.toHumanReadableString())
    }

    @Test
    fun `test quadrillions`() {
        assertEquals("1P", 1000000000000000L.toHumanReadableString())
    }

    @Test
    fun `test quintillions`() {
        assertEquals("1E", 1000000000000000000L.toHumanReadableString())
    }
}
