package io.github.aughtone.toolbox.geo

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GeoProjectionsTest {

    @Test
    fun webMercatorIsFiniteAndSymmetricAtThePoles() {
        val (_, south) = WebMercatorProjection.project(0.0, -90.0)
        val (_, north) = WebMercatorProjection.project(0.0, 90.0)
        assertTrue(south.isFinite(), "south pole projects to $south")
        assertTrue(north.isFinite(), "north pole projects to $north")
        assertEquals(south, -north, 1e-9)
        // Beyond the square-map limit of about 85.05 degrees the latitude is held at the limit.
        assertEquals(180.0, south, 1e-6)
    }

    @Test
    fun webMercatorIsUnchangedInsideItsLimit() {
        val (x, y) = WebMercatorProjection.project(-74.006, 40.7128)
        assertEquals(-74.006, x)
        assertTrue(abs(y - -44.6469) < 0.001, "y was $y")
        assertEquals(0.0, WebMercatorProjection.project(10.0, 0.0).second, 1e-9)
    }
}
