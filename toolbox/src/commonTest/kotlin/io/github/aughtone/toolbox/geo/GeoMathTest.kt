package io.github.aughtone.toolbox.geo

import io.github.aughtone.types.geo.GeoPoint
import io.github.aughtone.types.geo.GeoPolygon
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class GeoMathTest {

    @Test
    fun testCalculateBoundingBoxPoint() {
        val point = GeoPoint(10.0, 20.0)
        val bbox = point.calculateBoundingBox()
        assertNotNull(bbox)
        assertEquals(10.0, bbox.west)
        assertEquals(20.0, bbox.south)
        assertEquals(10.0, bbox.east)
        assertEquals(20.0, bbox.north)
    }

    @Test
    fun testCalculateBoundingBoxPolygon() {
        val polygon = GeoPolygon(listOf(
            listOf(
                listOf(0.0, 0.0),
                listOf(10.0, 0.0),
                listOf(10.0, 10.0),
                listOf(0.0, 10.0),
                listOf(0.0, 0.0)
            )
        ))
        val bbox = polygon.calculateBoundingBox()
        assertNotNull(bbox)
        assertEquals(0.0, bbox.west)
        assertEquals(0.0, bbox.south)
        assertEquals(10.0, bbox.east)
        assertEquals(10.0, bbox.north)
    }
}
