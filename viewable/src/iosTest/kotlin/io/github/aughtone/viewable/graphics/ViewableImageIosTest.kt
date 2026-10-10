package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewableGraphic
import io.github.aughtone.viewable.ViewableLayer
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.ViewableStyle
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ViewableImageIosTest {

    private val triangle = ViewablePath(
        listOf(PathCommand.MoveTo(0.0, 0.0), PathCommand.LineTo(100.0, 0.0), PathCommand.LineTo(100.0, 100.0), PathCommand.Close)
    )

    @Test
    fun everyFormatEncodesToNonEmptyBytes() {
        val image = triangle.toImage(64, 64)
        for (format in ImageFormat.entries) {
            assertTrue(image.toByteArray(format).isNotEmpty(), "$format produced no bytes")
        }
    }

    @Test
    fun aGraphicWithManyFilledAndStrokedLayersRenders() {
        val style = ViewableStyle(fillColor = 0x80FF0000.toInt(), strokeColor = 0xFF000000.toInt(), strokeWidth = 2.0)
        val graphic = ViewableGraphic(List(50) { ViewableLayer(triangle, style) })
        repeat(20) { assertTrue(graphic.toImage(64, 64).toByteArray(ImageFormat.PNG).isNotEmpty()) }
    }

    @Test
    fun anImageNeedsAPositiveSize() {
        assertFailsWith<IllegalArgumentException> { triangle.toImage(0, 10) }
    }
}
