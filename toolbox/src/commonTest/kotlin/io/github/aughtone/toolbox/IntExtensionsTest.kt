package io.github.aughtone.toolbox

import kotlin.test.Test
import kotlin.test.assertEquals

class IntExtensionsTest {

    @Test
    fun `test toHumanReadableString less than 1000`() {
        assertEquals("0", 0.toHumanReadableString())
        assertEquals("999", 999.toHumanReadableString())
    }

    @Test
    fun `test toHumanReadableString thousands`() {
        assertEquals("1K", 1000.toHumanReadableString())
        assertEquals("1.2K", 1234.toHumanReadableString())
        assertEquals("999.9K", 999999.toHumanReadableString())
    }

    @Test
    fun `test toHumanReadableString millions`() {
        assertEquals("1M", 1000000.toHumanReadableString())
        assertEquals("1.2M", 1234567.toHumanReadableString())
    }

    @Test
    fun `test formatCurrency with default symbol and scale`() {
        assertEquals("$123.45", 12345.formatCurrency())
    }

    @Test
    fun `test formatCurrency with custom symbol`() {
        assertEquals("€123.45", 12345.formatCurrency(symbol = "€"))
    }

    @Test
    fun `test formatCurrency with custom scale`() {
        assertEquals("$12.345", 12345.formatCurrency(scale = 3))
    }

    @Test
    fun `test formatCurrency with zero value`() {
        assertEquals("$0.00", 0.formatCurrency())
    }
}
