package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.*
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.geom.Path2D
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

actual typealias ViewableImage = BufferedImage

actual val isImageSupported: Boolean = true

/**
 * Draws a [ViewablePath] to a JVM [Graphics2D] context.
 * Useful for PDF, EPS, or Printer support.
 */
fun ViewablePath.drawTo(g2d: Graphics2D, style: ViewableStyle = ViewableStyle(fillColor = 0xFF000000.toInt())) {
    val path = Path2D.Double().apply {
        windingRule = when (winding) {
            WindingRule.NonZero -> Path2D.WIND_NON_ZERO
            WindingRule.EvenOdd -> Path2D.WIND_EVEN_ODD
        }
    }
    commands.forEach { command ->
        when (command) {
            is PathCommand.MoveTo -> path.moveTo(command.x, command.y)
            is PathCommand.LineTo -> path.lineTo(command.x, command.y)
            is PathCommand.Close -> path.closePath()
        }
    }
    if (style.fillColor != 0) {
        g2d.color = Color(style.fillColor, true)
        g2d.fill(path)
    }
    if (style.strokeColor != 0 && style.strokeWidth > 0) {
        g2d.color = Color(style.strokeColor, true)
        g2d.stroke = BasicStroke(style.strokeWidth.toFloat())
        g2d.draw(path)
    }
}

/**
 * Draws a [ViewableGraphic] to a JVM [Graphics2D] context.
 */
fun ViewableGraphic.drawTo(g2d: Graphics2D, width: Int, height: Int) {
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    val bounds = bounds
    if (bounds != null) {
        val scaleX = width / bounds.width
        val scaleY = height / bounds.height
        val scale = minOf(scaleX, scaleY)
        val dx = (width - bounds.width * scale) / 2.0
        val dy = (height - bounds.height * scale) / 2.0
        g2d.translate(dx, dy)
        g2d.scale(scale, scale)
        g2d.translate(-bounds.left, -bounds.top)
    }
    layers.forEach { it.path.drawTo(g2d, it.style) }
}

actual fun ViewablePath.toImage(
    width: Int,
    height: Int,
    fillColor: Int
): ViewableImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2d = image.createGraphics()
    val graphic = ViewableGraphic(listOf(ViewableLayer(this, ViewableStyle(fillColor = fillColor))))
    graphic.drawTo(g2d, width, height)
    g2d.dispose()
    return image
}

actual fun ViewableGraphic.toImage(
    width: Int,
    height: Int
): ViewableImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2d = image.createGraphics()
    drawTo(g2d, width, height)
    g2d.dispose()
    return image
}

actual fun ViewableImage.toByteArray(format: ImageFormat): ByteArray {
    val output = ByteArrayOutputStream()
    val formatName = when (format) {
        ImageFormat.PNG -> "png"
        ImageFormat.WEBP -> "webp"
        ImageFormat.JPEG -> "jpg"
    }
    ImageIO.write(this, formatName, output)
    return output.toByteArray()
}
