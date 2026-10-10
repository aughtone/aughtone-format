package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * `ImageIO.write` returns false (leaving the stream empty) when no writer handles a
 * format; `toByteArray` must never hand that back as a silently empty result.
 */
class ViewableImageJvmTest {

    private fun sampleImage(): ViewableImage =
        ViewablePath(
            listOf(
                PathCommand.MoveTo(0.0, 0.0),
                PathCommand.LineTo(10.0, 10.0),
                PathCommand.Close,
            ),
        ).toImage(width = 20, height = 20)

    @Test
    fun everyFormatEncodesToNonEmptyBytes() {
        for (format in ImageFormat.entries) {
            assertTrue(sampleImage().toByteArray(format).isNotEmpty(), "$format encoded to empty bytes")
        }
    }

    @Test
    fun drawingAGraphicLeavesTheCallersGraphicsContextAsItFoundIt() {
        val path = ViewablePath(
            commands = listOf(
                PathCommand.MoveTo(10.0, 10.0), PathCommand.LineTo(30.0, 10.0),
                PathCommand.LineTo(30.0, 30.0), PathCommand.Close,
            ),
            bounds = io.github.aughtone.viewable.ViewableRect(10.0, 10.0, 30.0, 30.0),
        )
        val graphic = io.github.aughtone.viewable.ViewableGraphic(
            listOf(io.github.aughtone.viewable.ViewableLayer(path, io.github.aughtone.viewable.ViewableStyle(fillColor = 0xFFFF0000.toInt())))
        )
        val canvas = java.awt.image.BufferedImage(100, 100, java.awt.image.BufferedImage.TYPE_INT_ARGB)
        val g2d = canvas.createGraphics()
        val before = g2d.transform
        graphic.drawTo(g2d, 100, 100)
        kotlin.test.assertEquals(before, g2d.transform, "drawTo left its transform on the caller's Graphics2D")
        // A second drawing on the same context therefore lands in the same place.
        graphic.drawTo(g2d, 100, 100)
        g2d.dispose()
        kotlin.test.assertEquals(0xFFFF0000.toInt(), canvas.getRGB(90, 10), "upper-right corner is filled")
    }
}
