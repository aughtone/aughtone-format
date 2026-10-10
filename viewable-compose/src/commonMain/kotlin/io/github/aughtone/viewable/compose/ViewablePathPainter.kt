package io.github.aughtone.viewable.compose

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import io.github.aughtone.viewable.ViewablePath

/**
 * A [Painter] that renders a [ViewablePath].
 *
 * When the [ViewablePath] has bounds, the painter scales the path uniformly to fit the draw
 * area and centres it, keeping its shape. A path without bounds is drawn at its own coordinates,
 * unscaled.
 *
 * @param viewablePath The path to render.
 * @param color The color to fill the path with.
 */
class ViewablePathPainter(
    private val viewablePath: ViewablePath,
    private val color: Color = Color.Black
) : Painter() {
    private val path = viewablePath.toComposePath()

    override val intrinsicSize: Size = viewablePath.bounds?.let {
        Size(it.width.toFloat(), it.height.toFloat())
    } ?: Size.Unspecified

    override fun DrawScope.onDraw() {
        val bounds = viewablePath.bounds
        if (bounds != null && size.width > 0 && size.height > 0) {
            val scaleX = size.width / bounds.width.toFloat()
            val scaleY = size.height / bounds.height.toFloat()
            val scale = minOf(scaleX, scaleY)

            // Center the path in the draw area
            val dx = (size.width - bounds.width.toFloat() * scale) / 2f
            val dy = (size.height - bounds.height.toFloat() * scale) / 2f

            withTransform({
                translate(dx, dy)
                // Scale from the origin. The default pivot is the centre of the draw area, which
                // would move the path away from where the two translations put it.
                scale(scale, scale, pivot = Offset.Zero)
                translate(-bounds.left.toFloat(), -bounds.top.toFloat())
            }) {
                drawPath(path, color)
            }
        } else {
            drawPath(path, color)
        }
    }
}
