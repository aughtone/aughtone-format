package io.github.aughtone.toolbox

import kotlin.test.Test
import kotlin.test.assertEquals

class NumberExtensionsTest {

    @Test
    fun roundsToAtMostTheRequestedPlacesAndTrimsTrailingZeros() {
        assertEquals("2", 2.0.format(2))
        assertEquals("2.5", 2.5.format(2))
        assertEquals("1.23", 1.2345.format(2))
        assertEquals("1.235", 1.2345.format(3))
        assertEquals("10", 9.999.format(2))
        assertEquals("3", 2.5.format(0))
        assertEquals("1235", 1234.5.format(-2))
    }

    @Test
    fun largeValuesAreWrittenOutNotInExponentForm() {
        assertEquals("10000000", 10_000_000.0.format(2))
        assertEquals("12345678.9", 12345678.9.format(1))
        assertEquals("100000000000000000", 1e17.format(2))
        assertEquals("1000000000000000000000", 1e21.format(0))
        assertEquals("10", 10.0.format(18))
    }

    @Test
    fun smallValuesAreWrittenOutNotInExponentForm() {
        assertEquals("0.0001", 0.0001.format(4))
        assertEquals("0.0005", 0.0005.format(6))
        assertEquals("0", 0.0001.format(3))
    }

    @Test
    fun negativeValuesRoundAwayFromZeroAndNeverReadAsMinusZero() {
        assertEquals("-3", (-2.5).format(0))
        assertEquals("-0.13", (-0.125).format(2))
        assertEquals("-74.006", (-74.006).format(6))
        assertEquals("0", (-0.0001).format(2))
    }

    @Test
    fun valuesThatAreNotFiniteDoNotThrow() {
        assertEquals("NaN", Double.NaN.format(2))
        assertEquals("Infinity", Double.POSITIVE_INFINITY.format(2))
        assertEquals("-Infinity", Double.NEGATIVE_INFINITY.format(0))
    }
}
