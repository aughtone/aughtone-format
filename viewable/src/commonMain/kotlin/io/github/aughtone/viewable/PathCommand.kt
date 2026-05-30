package io.github.aughtone.viewable

import kotlin.math.max
import kotlin.math.min

/**
 * Represents a platform-agnostic vector path command.
 */
sealed class PathCommand {
    /**
     * Moves the cursor to the specified coordinates without drawing.
     */
    data class MoveTo(val x: Double, val y: Double) : PathCommand()

    /**
     * Draws a line from the current cursor position to the specified coordinates.
     */
    data class LineTo(val x: Double, val y: Double) : PathCommand()

    /**
     * Closes the current sub-path by drawing a line back to the start.
     */
    object Close : PathCommand()
}

internal fun List<PathCommand>.calculateBounds(): ViewableRect? {
    if (isEmpty()) return null
    var minX = Double.MAX_VALUE
    var minY = Double.MAX_VALUE
    var maxX = -Double.MAX_VALUE
    var maxY = -Double.MAX_VALUE

    forEach { command ->
        when (command) {
            is PathCommand.MoveTo -> {
                minX = min(minX, command.x)
                minY = min(minY, command.y)
                maxX = max(maxX, command.x)
                maxY = max(maxY, command.y)
            }
            is PathCommand.LineTo -> {
                minX = min(minX, command.x)
                minY = min(minY, command.y)
                maxX = max(maxX, command.x)
                maxY = max(maxY, command.y)
            }
            is PathCommand.Close -> {}
        }
    }
    return if (minX == Double.MAX_VALUE) null else ViewableRect(minX, minY, maxX, maxY)
}
