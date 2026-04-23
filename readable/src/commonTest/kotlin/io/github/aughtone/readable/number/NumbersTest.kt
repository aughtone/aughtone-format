package io.github.aughtone.readable.number

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals

class NumbersTest {

    @Test
    fun testAbbreviation() {
        assertEquals("1k", 1000.0.toReadableAbbreviated(Locales.English))
        assertEquals("1.5k", 1500.0.toReadableAbbreviated(Locales.English))
        assertEquals("1M", 1000000.0.toReadableAbbreviated(Locales.English))
        assertEquals("2.5G", 2500000000.0.toReadableAbbreviated(Locales.English))
    }

    @Test
    fun testLocaleFormatting() {
        // German uses comma as decimal separator
        assertEquals("1,5k", 1500.0.toReadableAbbreviated(Locales.German))
        
        // French uses space as grouping separator
        val frenchFormatter = numberFormatterFor(Locales.French, 0)
        assertEquals("1 000", frenchFormatter(1000.0))
    }

    @Test
    fun testPrecision() {
        val p2 = numberFormatterFor(Locales.English, 2)
        assertEquals("1.23", p2(1.2345))
        
        val p3 = numberFormatterFor(Locales.English, 3)
        assertEquals("1.235", p3(1.2345))
    }
}
