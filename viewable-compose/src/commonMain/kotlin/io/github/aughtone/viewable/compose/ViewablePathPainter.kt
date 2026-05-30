package io.github.aughtone.viewable.compose

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import io.github.aughtone.viewable.ViewablePath

/**
 * A [Painter] that renders a [ViewablePath].
 *
 * This painter automatically scales and translates the path to fit the draw area
 * if the [ViewablePath] has bounds.
 *
 * @property viewablePath The path to render.
 * @property color The color to fill the path with.
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
                scale(scale, scale)
                translate(-bounds.left.toFloat(), -bounds.top.toFloat())
            }) {
                drawPath(path, color)
            }
        } else {
            drawPath(path, color)
        }
    }
}
