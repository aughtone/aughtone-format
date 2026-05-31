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
 * @param path An optional existing [Path] to append to.
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
 * (e.g. it is empty), a default 1x1 viewport is used.
 *
 * @param name The name of the [ImageVector].
 * @param fillColor The color to fill the path with.
 * @return An [ImageVector] representing the viewable path.
 */
fun ViewablePath.toImageVector(
    name: String = "ViewablePath",
    fillColor: Color = Color.Black
): ImageVector {
    val bounds = bounds ?: ViewableRect(0.0, 0.0, 1.0, 1.0)

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
