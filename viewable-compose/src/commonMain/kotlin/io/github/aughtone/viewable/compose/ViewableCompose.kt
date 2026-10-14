package io.github.aughtone.viewable.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.unit.dp
import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.ViewableRect
import io.github.aughtone.viewable.WindingRule

/**
 * Converts a [ViewablePath] into a Compose [Path].
 *
 * @param path An optional existing [Path] to append to. Its fill type is
 * overwritten with the fill type derived from this path's winding rule before
 * the commands are appended.
 * @return A Compose [Path] representing the viewable path.
 */
fun ViewablePath.toComposePath(path: Path = Path()): Path {
    path.fillType = when (winding) {
        WindingRule.NonZero -> PathFillType.NonZero
        WindingRule.EvenOdd -> PathFillType.EvenOdd
    }

    commands.forEach { command ->
        when (command) {
            is PathCommand.MoveTo -> path.moveTo(command.x.toFloat(), command.y.toFloat())
            is PathCommand.LineTo -> path.lineTo(command.x.toFloat(), command.y.toFloat())
            is PathCommand.Close -> path.close()
        }
    }
    return path
}

/**
 * Converts a [ViewablePath] into an [ImageVector].
 *
 * This function uses the path's bounds to set the viewport. If the path has no bounds
 * (e.g. it is empty), a default 1x1 viewport is used. A shape with no area — a flat line or a
 * single point — gets a square viewport around it, so the vector is never zero-sized.
 *
 * @param name The name of the [ImageVector].
 * @param fillColor The color to fill the path with.
 * @return An [ImageVector] representing the viewable path.
 */
fun ViewablePath.toImageVector(
    name: String = "ViewablePath",
    fillColor: Color = Color.Black
): ImageVector {
    val bounds = (bounds ?: ViewableRect(0.0, 0.0, 1.0, 1.0)).withDrawableExtent()

    val nodes = commands.map { command ->
        when (command) {
            is PathCommand.MoveTo -> PathNode.MoveTo(
                (command.x - bounds.left).toFloat(),
                (command.y - bounds.top).toFloat()
            )
            is PathCommand.LineTo -> PathNode.LineTo(
                (command.x - bounds.left).toFloat(),
                (command.y - bounds.top).toFloat()
            )
            is PathCommand.Close -> PathNode.Close
        }
    }

    return ImageVector.Builder(
        name = name,
        defaultWidth = bounds.width.toFloat().dp,
        defaultHeight = bounds.height.toFloat().dp,
        viewportWidth = bounds.width.toFloat(),
        viewportHeight = bounds.height.toFloat()
    ).addPath(
        pathData = nodes,
        fill = SolidColor(fillColor),
        pathFillType = when (winding) {
            WindingRule.NonZero -> PathFillType.NonZero
            WindingRule.EvenOdd -> PathFillType.EvenOdd
        }
    ).build()
}

/**
 * This rectangle with any zero dimension opened up so there is room to draw in: a flat line gets
 * a square of its own length centred on it, and a single point a 1×1 square around it. The same
 * rule the `:viewable` renderers apply to their SVG viewBox.
 */
private fun ViewableRect.withDrawableExtent(): ViewableRect {
    if (width > 0.0 && height > 0.0) return this
    val side = maxOf(width, height).takeIf { it > 0.0 } ?: 1.0
    val newWidth = if (width > 0.0) width else side
    val newHeight = if (height > 0.0) height else side
    val centerX = (left + right) / 2.0
    val centerY = (top + bottom) / 2.0
    return ViewableRect(centerX - newWidth / 2.0, centerY - newHeight / 2.0, centerX + newWidth / 2.0, centerY + newHeight / 2.0)
}
