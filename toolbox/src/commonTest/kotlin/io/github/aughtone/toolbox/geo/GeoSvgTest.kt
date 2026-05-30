package io.github.aughtone.toolbox.geo

import io.github.aughtone.types.geo.GeoLineString
import io.github.aughtone.types.geo.GeoPoint
import kotlin.test.Test
import kotlin.test.assertEquals

class GeoSvgTest {

    @Test
    fun testPointToSvgPathData() {
        val point = GeoPoint(10.0, 20.0)
        // Equirectangular projection: x = lon, y = -lat
        val svg = point.toSvgPathData(EquirectangularProjection, precision = 1)
        assertEquals("M10,-20l0,0", svg)
    }

    @Test
    fun testLineStringToSvgPathData() {
        val line = GeoLineString(listOf(
            listOf(0.0, 0.0),
            listOf(10.0, 10.0)
        ))
        val svg = line.toSvgPathData(EquirectangularProjection, precision = 1)
        assertEquals("M0,0L10,-10", svg)
    }
}
