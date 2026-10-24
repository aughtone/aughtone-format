package io.github.aughtone.viewable.graphics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewableGraphic
import io.github.aughtone.viewable.ViewableLayer
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.ViewableStyle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/** Raster output through Android's own `Bitmap` encoders, run on a device or emulator. */
class ViewableImageAndroidTest {

    private val triangle = ViewablePath(
        listOf(PathCommand.MoveTo(0.0, 0.0), PathCommand.LineTo(100.0, 0.0), PathCommand.LineTo(100.0, 100.0), PathCommand.Close)
    )

    @Test
    fun everyFormatEncodesToBytesThatDecode() {
        val image = triangle.toImage(64, 64)
        for (format in ImageFormat.entries) {
            val bytes = image.toByteArray(format)
            val decoded = assertNotNull(BitmapFactory.decodeByteArray(bytes, 0, bytes.size), "$format did not decode")
            assertEquals(64 to 64, decoded.width to decoded.height, "$format size")
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

    @Test
    fun jpegFlattensTransparencyOntoWhite() {
        // A black triangle over half of a transparent square. Flattened onto white, the JPEG
        // averages to mid-grey; flattened onto black it would be black all over.
        val bytes = triangle.toImage(64, 64).toByteArray(ImageFormat.JPEG)
        val decoded = assertNotNull(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
        val brightness = averageBrightness(decoded)
        assertTrue(brightness > 80.0, "JPEG average brightness was $brightness")
        // The transparent corner is white, not black.
        val corner = decoded.getPixel(2, 61)
        assertTrue((corner and 0xFF) > 240 && (corner shr 8 and 0xFF) > 240 && (corner shr 16 and 0xFF) > 240, "corner pixel ${corner.toUInt().toString(16)}")
    }

    @Test
    fun pngKeepsTransparency() {
        val bytes = triangle.toImage(64, 64).toByteArray(ImageFormat.PNG)
        val decoded = assertNotNull(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
        assertEquals(0, decoded.getPixel(2, 61) ushr 24, "the uncovered corner should be fully transparent")
    }

    private fun averageBrightness(bitmap: Bitmap): Double {
        var total = 0.0
        for (y in 0 until bitmap.height) for (x in 0 until bitmap.width) {
            val pixel = bitmap.getPixel(x, y)
            total += ((pixel shr 16 and 0xFF) + (pixel shr 8 and 0xFF) + (pixel and 0xFF)) / 3.0
        }
        return total / (bitmap.width * bitmap.height)
    }
}
