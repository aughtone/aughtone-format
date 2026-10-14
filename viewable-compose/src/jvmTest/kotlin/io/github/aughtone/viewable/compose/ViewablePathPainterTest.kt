package io.github.aughtone.viewable.compose

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.ViewableRect
import kotlin.test.Test
import kotlin.test.assertEquals

class ViewablePathPainterTest {

    /** Draws [path] in red into a transparent bitmap of the given size and returns its pixels. */
    private fun render(path: ViewablePath, width: Int, height: Int) = ImageBitmap(width, height).also { bitmap ->
        val painter = ViewablePathPainter(path, Color.Red)
        val size = Size(width.toFloat(), height.toFloat())
        CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, Canvas(bitmap), size) {
            with(painter) { draw(size) }
        }
    }.toPixelMap()

    private fun square(left: Double, top: Double, side: Double) = ViewablePath(
        commands = listOf(
            PathCommand.MoveTo(left, top), PathCommand.LineTo(left + side, top),
            PathCommand.LineTo(left + side, top + side), PathCommand.LineTo(left, top + side), PathCommand.Close,
        ),
        bounds = ViewableRect(left, top, left + side, top + side),
    )

    @Test
    fun aPathIsScaledToFillTheDrawArea() {
        val pixels = render(square(0.0, 0.0, 100.0), 200, 200)
        for ((x, y) in listOf(10 to 10, 190 to 10, 10 to 190, 190 to 190, 100 to 100)) {
            assertEquals(Color.Red, pixels[x, y], "pixel at $x,$y")
        }
    }

    @Test
    fun aSmallPathFarFromTheOriginStillFillsTheDrawArea() {
        // Geographic bounds are a few degrees wide and nowhere near zero.
        val pixels = render(square(-74.5, 40.0, 1.0), 120, 120)
        for ((x, y) in listOf(5 to 5, 115 to 5, 5 to 115, 115 to 115, 60 to 60)) {
            assertEquals(Color.Red, pixels[x, y], "pixel at $x,$y")
        }
    }

    @Test
    fun aPathThatIsNotSquareIsCentredWithItsShapeKept() {
        val wide = ViewablePath(
            commands = listOf(
                PathCommand.MoveTo(0.0, 0.0), PathCommand.LineTo(100.0, 0.0),
                PathCommand.LineTo(100.0, 50.0), PathCommand.LineTo(0.0, 50.0), PathCommand.Close,
            ),
            bounds = ViewableRect(0.0, 0.0, 100.0, 50.0),
        )
        val pixels = render(wide, 200, 200)
        assertEquals(Color.Red, pixels[100, 100], "centre")
        assertEquals(Color.Red, pixels[5, 100], "left edge of the band")
        assertEquals(Color.Transparent, pixels[100, 20], "above the band")
        assertEquals(Color.Transparent, pixels[100, 180], "below the band")
    }
}
