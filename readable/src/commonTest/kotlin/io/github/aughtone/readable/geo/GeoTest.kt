package io.github.aughtone.readable.geo

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.quantitative.CoordinateFormat
import io.github.aughtone.readable.quantitative.formatReadable
import io.github.aughtone.types.geo.GeoPoint
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

    @Test
    fun wholeMinutesAreNotShownOneSecondShort() {
        // 1.15° is exactly 1° 9' 0", but in binary it sits a hair below.
        val coords = Coordinates(1.15, 30.5)
        assertEquals("1° 9' 0\" N, 30° 30' 0\" E", coords.formatReadable(CoordinateFormat.DegreesMinutesSeconds, Locales.English))
        assertEquals("0° 6' 0\" S, 0° 18' 0\" W", Coordinates(-0.1, -0.3).formatReadable(CoordinateFormat.DegreesMinutesSeconds, Locales.English))
    }

    @Test
    fun aGeoPointFormatsTheSameAsTheEquivalentCoordinates() {
        val point = GeoPoint(latitude = 1.15, longitude = 30.5)
        assertEquals("1° 9' 0\" N, 30° 30' 0\" E", point.formatReadable(CoordinateFormat.DegreesMinutesSeconds, Locales.English))
        assertEquals("1.15° N, 30.5° E", point.formatReadable(CoordinateFormat.DecimalDegrees, Locales.English))
    }

    @Test
    fun anAzimuthThatRoundsToAFullTurnReadsAsNorthAtZero() {
        assertEquals("0° (N)", Azimuth(359.6).formatReadable(Locales.English))
        assertEquals("359.6° (N)", Azimuth(359.6).formatReadable(Locales.English, precision = 1))
        assertEquals("338° (N)", Azimuth(337.5).formatReadable(Locales.English))
        assertEquals("337° (NW)", Azimuth(337.4).formatReadable(Locales.English))
    }
}
