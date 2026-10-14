package io.github.aughtone.viewable.compose

import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ViewableComposeTest {

    @Test
    fun aFlatLineGetsAViewportWithRoomToDrawIn() {
        val line = ViewablePath(listOf(PathCommand.MoveTo(0.0, 5.0), PathCommand.LineTo(10.0, 5.0)))
        val vector = line.toImageVector()
        assertEquals(10f, vector.viewportWidth)
        assertEquals(10f, vector.viewportHeight)
    }

    @Test
    fun aSinglePointGetsAOneByOneViewport() {
        val vector = ViewablePath(listOf(PathCommand.MoveTo(3.0, 4.0))).toImageVector()
        assertTrue(vector.viewportWidth > 0f && vector.viewportHeight > 0f)
    }

    @Test
    fun aShapeWithAreaKeepsItsBoundsAsTheViewport() {
        val square = ViewablePath(listOf(PathCommand.MoveTo(0.0, 0.0), PathCommand.LineTo(20.0, 0.0), PathCommand.LineTo(20.0, 8.0), PathCommand.Close))
        val vector = square.toImageVector()
        assertEquals(20f, vector.viewportWidth)
        assertEquals(8f, vector.viewportHeight)
    }
}
