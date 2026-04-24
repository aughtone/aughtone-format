package io.github.aughtone.readable.geo

import io.github.aughtone.readable.Locales
import io.github.aughtone.types.quantitative.Altitude
import io.github.aughtone.types.quantitative.Azimuth
import io.github.aughtone.types.quantitative.Coordinates
import kotlin.test.Test
import kotlin.test.assertEquals

class GeoTest {

    @Test
    fun testAltitude() {
        val alt = Altitude(1500.0)
        assertEquals("1.5 km", alt.formatReadable(Locales.English))
    }

    @Test
    fun testAzimuth() {
        val az1 = Azimuth(90.0)
        assertEquals("90° (E)", az1.formatReadable(Locales.English))
        
        val az2 = Azimuth(225.0)
        assertEquals("225° (SW)", az2.formatReadable(Locales.English))
    }

    @Test
    fun testCoordinatesDD() {
        val coords = Coordinates(40.7128, -74.0060)
        assertEquals("40.7128° N, 74.006° W", coords.formatReadable(CoordinateFormat.DecimalDegrees, Locales.English))
    }

    @Test
    fun testCoordinatesDMS() {
        val coords = Coordinates(40.7128, -74.0060)
        // 40.7128 -> 40° 42' 46" N
        // 74.0060 -> 74° 0' 21" W
        assertEquals("40° 42' 46\" N, 74° 0' 21\" W", coords.formatReadable(CoordinateFormat.DegreesMinutesSeconds, Locales.English))
    }
}
