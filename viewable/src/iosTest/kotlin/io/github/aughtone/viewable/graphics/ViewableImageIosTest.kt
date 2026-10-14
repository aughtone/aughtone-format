package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewableGraphic
import io.github.aughtone.viewable.ViewableLayer
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.ViewableStyle
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGColorSpaceRelease
import platform.CoreGraphics.CGContextDrawImage
import platform.CoreGraphics.CGContextRelease
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSData
import platform.Foundation.create
import platform.UIKit.UIImage
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

    @Test
    fun jpegFlattensTransparencyOntoWhite() {
        // A black triangle over half of a transparent square. Flattened onto white, the JPEG
        // averages to mid-grey; flattened onto black it would be black all over.
        val brightness = averageBrightness(triangle.toImage(64, 64).toByteArray(ImageFormat.JPEG))
        assertTrue(brightness > 80.0, "JPEG average brightness was $brightness")
    }

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    private fun averageBrightness(bytes: ByteArray): Double {
        val data = bytes.usePinned { NSData.create(bytes = it.addressOf(0), length = bytes.size.toULong()) }
        val image = UIImage.imageWithData(data) ?: error("JPEG did not decode")
        val side = 32
        val pixels = UByteArray(side * side * 4)
        pixels.usePinned { pinned ->
            val colorSpace = CGColorSpaceCreateDeviceRGB()
            val context = CGBitmapContextCreate(
                pinned.addressOf(0), side.toULong(), side.toULong(), 8u, (side * 4).toULong(),
                colorSpace, CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value,
            )
            CGContextDrawImage(context, CGRectMake(0.0, 0.0, side.toDouble(), side.toDouble()), image.CGImage)
            CGContextRelease(context)
            CGColorSpaceRelease(colorSpace)
        }
        var total = 0.0
        for (index in 0 until side * side) {
            total += (pixels[index * 4].toInt() + pixels[index * 4 + 1].toInt() + pixels[index * 4 + 2].toInt()) / 3.0
        }
        return total / (side * side)
    }
}
