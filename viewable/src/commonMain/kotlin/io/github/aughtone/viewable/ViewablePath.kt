package io.github.aughtone.viewable

import io.github.aughtone.toolbox.format
import io.github.aughtone.types.geo.*
import kotlin.math.max
import kotlin.math.min

/**
 * Represents a platform-agnostic vector path that can be rendered by various UI engines.
 *
 * @property commands The sequence of [PathCommand]s that define the path.
 * @property winding The winding rule to use when filling the path.
 * @property bounds The bounding box of the path in its local coordinate system.
 */
data class ViewablePath(
    val commands: List<PathCommand>,
    val winding: WindingRule = WindingRule.EvenOdd,
    val bounds: ViewableRect? = null,
)

/**
 * Converts a [ViewablePath] back into an SVG path data string.
 *
 * @param precision The number of decimal places to include in the output.
 * @return A string containing SVG path instructions (M, L, Z).
 */
fun ViewablePath.toSvgPathData(precision: Int = 3): String {
    return commands.joinToString("") { command ->
        when (command) {
            is PathCommand.MoveTo -> "M${command.x.format(precision)},${command.y.format(precision)}"
            is PathCommand.LineTo -> "L${command.x.format(precision)},${command.y.format(precision)}"
            is PathCommand.Close -> "Z"
        }
    }
}

/**
 * Converts a [ViewablePath] into a full SVG document string.
 *
 * @param width The optional width of the SVG document.
 * @param height The optional height of the SVG document.
 * @param fillColor The color to fill the path with as an ARGB Int.
 * @param precision The number of decimal places to include in the output.
 * @return A string containing the full XML SVG document.
 */
fun ViewablePath.toSvgDocument(
    width: Int? = null,
    height: Int? = null,
    fillColor: Int = 0xFF000000.toInt(),
    precision: Int = 3,
): String {
    val d = toSvgPathData(precision)
    val viewBox = bounds?.let {
        "${it.left.format(precision)} ${it.top.format(precision)} ${it.width.format(precision)} ${it.height.format(precision)}"
    } ?: "0 0 1 1"

    val widthAttr = width?.let { " width=\"$it\"" } ?: ""
    val heightAttr = height?.let { " height=\"$it\"" } ?: ""

    val fillRule = when (winding) {
        WindingRule.NonZero -> "nonzero"
        WindingRule.EvenOdd -> "evenodd"
    }

    val hexColor = "#" + (fillColor and 0xFFFFFF).toString(16).padStart(6, '0')
    val opacity = ((fillColor shr 24) and 0xFF) / 255.0
    val opacityAttr = if (opacity < 1.0) " fill-opacity=\"${opacity.format(2)}\"" else ""

    return """<svg xmlns="http://www.w3.org/2000/svg"$widthAttr$heightAttr viewBox="$viewBox"><path d="$d" fill="$hexColor"$opacityAttr fill-rule="$fillRule"/></svg>"""
}
