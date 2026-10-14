package io.github.aughtone.viewable.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import io.github.aughtone.viewable.*
import java.io.ByteArrayOutputStream

actual typealias ViewableImage = Bitmap

actual val isImageSupported: Boolean = true

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
    require(width > 0 && height > 0) { "An image needs a positive size, but was asked for ${width}x$height." }
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val fillPaint = Paint().apply { style = Paint.Style.FILL; isAntiAlias = true }
    val strokePaint = Paint().apply { style = Paint.Style.STROKE; isAntiAlias = true }

    val bounds = bounds
    if (bounds != null) {
        val (scale, dx, dy) = bounds.fitInto(width.toDouble(), height.toDouble())
        canvas.translate(dx.toFloat(), dy.toFloat())
        canvas.scale(scale.toFloat(), scale.toFloat())
        canvas.translate(-bounds.left.toFloat(), -bounds.top.toFloat())
    }

    layers.forEach { layer ->
        val androidPath = Path().apply {
            fillType = when (layer.path.winding) {
                WindingRule.NonZero -> Path.FillType.WINDING
                WindingRule.EvenOdd -> Path.FillType.EVEN_ODD
            }
        }
        layer.path.commands.forEach { command ->
            when (command) {
                is PathCommand.MoveTo -> androidPath.moveTo(command.x.toFloat(), command.y.toFloat())
                is PathCommand.LineTo -> androidPath.lineTo(command.x.toFloat(), command.y.toFloat())
                is PathCommand.Close -> androidPath.close()
            }
        }
        if (layer.style.fillColor != 0) {
            fillPaint.color = layer.style.fillColor
            canvas.drawPath(androidPath, fillPaint)
        }
        if (layer.style.strokeColor != 0 && layer.style.strokeWidth > 0) {
            strokePaint.color = layer.style.strokeColor
            strokePaint.strokeWidth = layer.style.strokeWidth.toFloat()
            canvas.drawPath(androidPath, strokePaint)
        }
    }
    return bitmap
}

actual fun ViewableImage.toByteArray(format: ImageFormat): ByteArray {
    val output = ByteArrayOutputStream()
    val compressFormat = when (format) {
        ImageFormat.PNG -> Bitmap.CompressFormat.PNG
        ImageFormat.JPEG -> Bitmap.CompressFormat.JPEG
    }
    // JPEG has no alpha channel and the encoder would turn transparent pixels black, so the
    // image is first flattened onto white — the same result as on the JVM and on iOS.
    val source = if (format == ImageFormat.JPEG) flattenedOntoWhite() else this
    val wrote = source.compress(compressFormat, 100, output)
    check(wrote) { "The bitmap could not be encoded as ${format.name}." }
    return output.toByteArray()
}

/** Returns an opaque copy of this bitmap with its transparent areas painted white. */
private fun Bitmap.flattenedOntoWhite(): Bitmap {
    val opaque = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    Canvas(opaque).apply {
        drawColor(android.graphics.Color.WHITE)
        drawBitmap(this@flattenedOntoWhite, 0f, 0f, null)
    }
    return opaque
}

/**
 * Renders a [ViewableGraphic] to a PDF byte array.
 *
 * @param width The width of the PDF page in points (1/72 inch).
 * @param height The height of the PDF page in points.
 * @return A [ByteArray] containing the PDF document.
 */
fun ViewableGraphic.toPdf(width: Int, height: Int): ByteArray {
    val document = android.graphics.pdf.PdfDocument()
    val pageInfo = android.graphics.pdf.PdfDocument.PageInfo.Builder(width, height, 1).create()
    val page = document.startPage(pageInfo)
    val canvas = page.canvas

    val fillPaint = Paint().apply { style = Paint.Style.FILL; isAntiAlias = true }
    val strokePaint = Paint().apply { style = Paint.Style.STROKE; isAntiAlias = true }

    val bounds = bounds
    if (bounds != null) {
        val (scale, dx, dy) = bounds.fitInto(width.toDouble(), height.toDouble())
        canvas.translate(dx.toFloat(), dy.toFloat())
        canvas.scale(scale.toFloat(), scale.toFloat())
        canvas.translate(-bounds.left.toFloat(), -bounds.top.toFloat())
    }

    layers.forEach { layer ->
        val androidPath = android.graphics.Path().apply {
            fillType = when (layer.path.winding) {
                WindingRule.NonZero -> android.graphics.Path.FillType.WINDING
                WindingRule.EvenOdd -> android.graphics.Path.FillType.EVEN_ODD
            }
        }
        layer.path.commands.forEach { command ->
            when (command) {
                is PathCommand.MoveTo -> androidPath.moveTo(command.x.toFloat(), command.y.toFloat())
                is PathCommand.LineTo -> androidPath.lineTo(command.x.toFloat(), command.y.toFloat())
                is PathCommand.Close -> androidPath.close()
            }
        }
        if (layer.style.fillColor != 0) {
            fillPaint.color = layer.style.fillColor
            canvas.drawPath(androidPath, fillPaint)
        }
        if (layer.style.strokeColor != 0 && layer.style.strokeWidth > 0) {
            strokePaint.color = layer.style.strokeColor
            strokePaint.strokeWidth = layer.style.strokeWidth.toFloat()
            canvas.drawPath(androidPath, strokePaint)
        }
    }

    document.finishPage(page)
    val output = ByteArrayOutputStream()
    document.writeTo(output)
    document.close()
    return output.toByteArray()
}
