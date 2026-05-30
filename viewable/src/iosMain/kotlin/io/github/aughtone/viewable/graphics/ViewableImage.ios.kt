package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.*
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreGraphics.*
import platform.UIKit.*
import platform.posix.memcpy

actual typealias ViewableImage = UIImage

actual fun ViewablePath.toImage(
    width: Int,
    height: Int,
    fillColor: Int
): ViewableImage {
    return ViewableGraphic(listOf(ViewableLayer(this, ViewableStyle(fillColor = fillColor))))
        .toImage(width, height)
}

actual fun ViewableGraphic.toImage(
    width: Int,
    height: Int
): ViewableImage {
    val size = CGSizeMake(width.toDouble(), height.toDouble())
    UIGraphicsBeginImageContextWithOptions(size, false, 1.0)
    val context = UIGraphicsGetCurrentContext()

    val bounds = bounds
    if (bounds != null) {
        val scaleX = width / bounds.width
        val scaleY = height / bounds.height
        val scale = minOf(scaleX, scaleY)
        val dx = (width - bounds.width * scale) / 2.0
        val dy = (height - bounds.height * scale) / 2.0
        CGContextTranslateCTM(context, dx, dy)
        CGContextScaleCTM(context, scale, scale)
        CGContextTranslateCTM(context, -bounds.left, -bounds.top)
    }

    layers.forEach { layer ->
        val cgPath = CGPathCreateMutable()
        layer.path.commands.forEach { command ->
            when (command) {
                is PathCommand.MoveTo -> CGPathMoveToPoint(cgPath, null, command.x, command.y)
                is PathCommand.LineTo -> CGPathAddLineToPoint(cgPath, null, command.x, command.y)
                is PathCommand.Close -> CGPathCloseSubpath(cgPath)
            }
        }

        if (layer.style.fillColor != 0) {
            val r = ((layer.style.fillColor shr 16) and 0xFF) / 255.0
            val g = ((layer.style.fillColor shr 8) and 0xFF) / 255.0
            val b = (layer.style.fillColor and 0xFF) / 255.0
            val a = ((layer.style.fillColor shr 24) and 0xFF) / 255.0
            CGContextSetRGBFillColor(context, r, g, b, a)
            CGContextAddPath(context, cgPath)
            if (layer.path.winding == WindingRule.EvenOdd) {
                CGContextEOFillPath(context)
            } else {
                CGContextFillPath(context)
            }
        }

        if (layer.style.strokeColor != 0 && layer.style.strokeWidth > 0) {
            val r = ((layer.style.strokeColor shr 16) and 0xFF) / 255.0
            val g = ((layer.style.strokeColor shr 8) and 0xFF) / 255.0
            val b = (layer.style.strokeColor and 0xFF) / 255.0
            val a = ((layer.style.strokeColor shr 24) and 0xFF) / 255.0
            CGContextSetRGBStrokeColor(context, r, g, b, a)
            CGContextSetLineWidth(context, layer.style.strokeWidth)
            CGContextAddPath(context, cgPath)
            CGContextStrokePath(context)
        }
    }
    
    val image = UIGraphicsGetImageFromCurrentImageContext()
    UIGraphicsEndImageContext()
    return image!!
}

@OptIn(ExperimentalForeignApi::class)
actual fun ViewableImage.toByteArray(format: ImageFormat): ByteArray {
    val data = when (format) {
        ImageFormat.PNG -> UIImagePNGRepresentation(this)
        ImageFormat.JPEG -> UIImageJPEGRepresentation(this, 1.0)
        ImageFormat.WEBP -> UIImagePNGRepresentation(this)
    }
    
    return data?.let {
        val bytes = it.bytes
        val length = it.length.toInt()
        ByteArray(length).apply {
            usePinned { pinned ->
                memcpy(pinned.addressOf(0), bytes, it.length)
            }
        }
    } ?: ByteArray(0)
}
