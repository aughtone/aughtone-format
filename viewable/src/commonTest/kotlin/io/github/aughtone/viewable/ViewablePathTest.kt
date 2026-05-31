package io.github.aughtone.viewable

import io.github.aughtone.toolbox.geo.EquirectangularProjection
import io.github.aughtone.types.geo.GeoLineString
import io.github.aughtone.viewable.geojson.toViewablePath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ViewablePathTest {

    @Test
    fun testGeoJsonToViewablePath() {
        val line = GeoLineString(listOf(
            listOf(0.0, 0.0),
            listOf(10.0, 10.0)
        ))
        val viewablePath = line.toViewablePath(EquirectangularProjection)
        
        assertEquals(2, viewablePath.commands.size)
        assertEquals(PathCommand.MoveTo(0.0, 0.0), viewablePath.commands[0])
        assertEquals(PathCommand.LineTo(10.0, -10.0), viewablePath.commands[1])
        
        val bounds = viewablePath.bounds
        assertNotNull(bounds)
        assertEquals(0.0, bounds.left)
        assertEquals(-10.0, bounds.top) // y increases downwards, so -10 is top
        assertEquals(10.0, bounds.right)
        assertEquals(0.0, bounds.bottom)
    }

    @Test
    fun testViewablePathToSvgPathData() {
        val path = ViewablePath(listOf(
            PathCommand.MoveTo(0.0, 0.0),
            PathCommand.LineTo(10.0, 10.0),
            PathCommand.Close
        ))
        assertEquals("M0,0L10,10Z", path.toSvgPathData(precision = 0))
    }

    @Test
    fun testViewablePathToSvgDocument() {
        val path = ViewablePath(
            commands = listOf(
                PathCommand.MoveTo(0.0, 0.0),
                PathCommand.LineTo(100.0, 100.0),
                PathCommand.Close
            ),
            bounds = ViewableRect(0.0, 0.0, 100.0, 100.0)
        )
        val svg = path.toSvgDocument(width = 100, height = 100, fillColor = 0xFFFF0000.toInt())
        assertTrue(svg.contains("viewBox=\"0 0 100 100\""))
        assertTrue(svg.contains("fill=\"#ff0000\""))
        assertTrue(svg.contains("<svg xmlns=\"http://www.w3.org/2000/svg\""))
    }
}
