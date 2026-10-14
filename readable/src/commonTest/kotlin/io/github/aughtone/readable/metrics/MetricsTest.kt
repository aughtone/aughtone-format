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
        assertEquals("1 KiB", 1024L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1 MiB", 1048576L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1.5 GiB", (1024L * 1024L * 1024L * 1.5).toLong().formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
    }

    @Test
    fun metricScalingMovesUpAPrefixWhenRoundingReachesAThousand() {
        assertEquals("1 km", Distance(999.96).formatReadable(Locales.English))
        assertEquals("999.9 m", Distance(999.94).formatReadable(Locales.English))
        assertEquals("1 m", 0.99996.formatReadableMetric(UnitOfMeasure.Meter, Locales.English))
        assertEquals("1 MW", 999_960.formatReadableMetric(UnitOfMeasure.Watt, Locales.English))
    }

    @Test
    fun metricScalingHandlesNegativeSmallAndNonFiniteValues() {
        assertEquals("-1.5 km", (-1500.0).formatReadableMetric(UnitOfMeasure.Meter, Locales.English))
        assertEquals("500 mm", 0.5.formatReadableMetric(UnitOfMeasure.Meter, Locales.English))
        assertEquals("0 m", 0.0.formatReadableMetric(UnitOfMeasure.Meter, Locales.English))
        assertEquals("12,345,678 Qm", 1.2345678e37.formatReadableMetric(UnitOfMeasure.Meter, Locales.English, 0))
        assertEquals("NaN m", Double.NaN.formatReadableMetric(UnitOfMeasure.Meter, Locales.English))
        assertEquals("∞ m", Double.POSITIVE_INFINITY.formatReadableMetric(UnitOfMeasure.Meter, Locales.English))
    }

    @Test
    fun dataSizeMovesUpAPrefixWhenRoundingReachesTheNextStep() {
        assertEquals("1 KiB", 1023.99.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1 MiB", 1_048_575L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1,023 B", 1023L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1.5 KiB", 1536L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
    }

    @Test
    fun dataSizeHandlesNegativeAndNonFiniteValues() {
        assertEquals("-1 KiB", (-1024L).formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("NaN B", Double.NaN.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("∞ B", Double.POSITIVE_INFINITY.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        assertEquals("1 KiB", 1024L.toReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1.5 km", 1500.0.toReadableMetric(UnitOfMeasure.Meter, Locales.English))
    }
}
