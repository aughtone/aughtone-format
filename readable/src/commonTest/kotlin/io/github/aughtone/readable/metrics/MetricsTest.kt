package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.quantitative.Distance
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.test.Test
import kotlin.test.assertEquals

class MetricsTest {

    @Test
    fun testDistanceScaling() {
        val d1 = Distance(1500.0)
        assertEquals("1.5 km", d1.formatReadable(Locales.English))
        
        val d2 = Distance(500.0)
        assertEquals("500 m", d2.formatReadable(Locales.English))
    }

    @Test
    fun testDataSizeScaling() {
        assertEquals("1 KiB", 1024L.toReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1 MiB", 1048576L.toReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1.5 GiB", (1024L * 1024L * 1024L * 1.5).toLong().toReadableDataSize(UnitOfMeasure.Byte, Locales.English))
    }
}
