package io.github.aughtone.viewable

import io.github.aughtone.toolbox.format
import kotlin.math.max
import kotlin.math.min
import kotlin.math.ceil
import kotlin.math.floor

/**
 * A layer in a [ViewableGraphic], combining a path with a style.
 */
data class ViewableLayer(
    val path: ViewablePath,
    val style: ViewableStyle = ViewableStyle()
)

/**
 * A multi-layer vector graphic that can be rendered to various formats.
 */
data class ViewableGraphic(
    val layers: List<ViewableLayer>,
    val bounds: ViewableRect? = layers.calculateBounds()
)

private fun List<ViewableLayer>.calculateBounds(): ViewableRect? {
    val b = mapNotNull { it.path.bounds }
    if (b.isEmpty()) return null
    
    var left = Double.MAX_VALUE
    var top = Double.MAX_VALUE
    var right = -Double.MAX_VALUE
    var bottom = -Double.MAX_VALUE
    
    b.forEach {
        left = min(left, it.left)
        top = min(top, it.top)
        right = max(right, it.right)
        bottom = max(bottom, it.bottom)
    }
    
    return ViewableRect(left, top, right, bottom)
}

/**
 * Converts a [ViewableGraphic] into a full SVG document string.
 *
 * @param width The value for the SVG `width` attribute in pixels. When `null`,
 *   the attribute is omitted and the image scales to its `viewBox`.
 * @param height The value for the SVG `height` attribute in pixels. When `null`,
 *   the attribute is omitted and the image scales to its `viewBox`.
 * @param precision The number of decimal places to include in the output.
 * @return A string containing the full SVG document.
 */
fun ViewableGraphic.toSvgDocument(
    width: Int? = null,
    height: Int? = null,
    precision: Int = 3
): String {
    val viewBox = bounds?.withDrawableExtent()?.let {
        "${it.left.format(precision)} ${it.top.format(precision)} ${it.width.format(precision)} ${it.height.format(precision)}"
    } ?: "0 0 1 1"

    val widthAttr = width?.let { " width=\"$it\"" } ?: ""
    val heightAttr = height?.let { " height=\"$it\"" } ?: ""

    val sb = StringBuilder()
    sb.append("""<svg xmlns="http://www.w3.org/2000/svg"$widthAttr$heightAttr viewBox="$viewBox">""")
    
    layers.forEach { layer ->
        val d = layer.path.toSvgPathData(precision)
        val fill = if (layer.style.fillColor != 0) {
            val hex = "#" + (layer.style.fillColor and 0xFFFFFF).toString(16).padStart(6, '0')
            " fill=\"$hex\"${opacityAttribute("fill-opacity", layer.style.fillColor)}"
        } else " fill=\"none\""
        
        val stroke = if (layer.style.strokeColor != 0) {
            val hex = "#" + (layer.style.strokeColor and 0xFFFFFF).toString(16).padStart(6, '0')
            " stroke=\"$hex\"${opacityAttribute("stroke-opacity", layer.style.strokeColor)} stroke-width=\"${layer.style.strokeWidth.format(precision)}\""
        } else ""

        val fillRule = when (layer.path.winding) {
            WindingRule.NonZero -> "nonzero"
            WindingRule.EvenOdd -> "evenodd"
        }

        sb.append("""<path d="$d"$fill$stroke fill-rule="$fillRule"/>""")
    }
    
    sb.append("</svg>")
    return sb.toString()
}

/** An SVG opacity attribute for the alpha channel of an ARGB [color], or nothing when it is opaque. */
private fun opacityAttribute(name: String, color: Int): String {
    val opacity = ((color shr 24) and 0xFF) / 255.0
    return if (opacity < 1.0) " $name=\"${opacity.format(2)}\"" else ""
}

/**
 * Converts a [ViewableGraphic] into an Encapsulated PostScript (EPS) string.
 *
 * A path's y axis grows downward and PostScript's grows upward, so every y coordinate is
 * mirrored within the bounds: the drawing comes out the same way up as the SVG and raster
 * output. A layer with both a fill and a stroke gets both. EPS has no transparency, so the alpha
 * channel of a colour is ignored.
 *
 * @param precision The maximum number of decimal places to include in the output.
 * @return A string containing the full EPS document.
 */
fun ViewableGraphic.toEpsDocument(precision: Int = 3): String {
    val b = bounds?.withDrawableExtent() ?: ViewableRect(0.0, 0.0, 1.0, 1.0)
    val sb = StringBuilder()
    sb.append("%!PS-Adobe-3.0 EPSF-3.0\n")
    // The integer box has to enclose the drawing: round the low corner down and the high one up.
    sb.append("%%BoundingBox: ${floor(b.left).toLong()} ${floor(b.top).toLong()} ${ceil(b.right).toLong()} ${ceil(b.bottom).toLong()}\n")
    sb.append("%%HiResBoundingBox: ${b.left.format(precision)} ${b.top.format(precision)} ${b.right.format(precision)} ${b.bottom.format(precision)}\n")
    sb.append("%%LanguageLevel: 2\n")
    sb.append("%%EndComments\n")

    // Mirrors a y coordinate within the bounds, turning the path's y-down space into PostScript's y-up.
    fun flipY(y: Double): Double = b.top + b.bottom - y

    layers.forEach { layer ->
        sb.append("gsave\n")
        
        // Define path
        sb.append("newpath\n")
        layer.path.commands.forEach { command ->
            when (command) {
                is PathCommand.MoveTo -> sb.append("${command.x.format(precision)} ${flipY(command.y).format(precision)} moveto\n")
                is PathCommand.LineTo -> sb.append("${command.x.format(precision)} ${flipY(command.y).format(precision)} lineto\n")
                is PathCommand.Close -> sb.append("closepath\n")
            }
        }
        
        // Fill
        if (layer.style.fillColor != 0) {
            val r = ((layer.style.fillColor shr 16) and 0xFF) / 255.0
            val g = ((layer.style.fillColor shr 8) and 0xFF) / 255.0
            val bColor = (layer.style.fillColor and 0xFF) / 255.0
            // Filling consumes the current path, so it runs inside its own gsave/grestore and
            // leaves the path in place for the stroke.
            sb.append("gsave\n")
            sb.append("${r.format(precision)} ${g.format(precision)} ${bColor.format(precision)} setrgbcolor\n")
            
            if (layer.path.winding == WindingRule.EvenOdd) {
                sb.append("eofill\n")
            } else {
                sb.append("fill\n")
            }
            sb.append("grestore\n")
        }
        
        // Stroke
        if (layer.style.strokeColor != 0 && layer.style.strokeWidth > 0) {
            val r = ((layer.style.strokeColor shr 16) and 0xFF) / 255.0
            val g = ((layer.style.strokeColor shr 8) and 0xFF) / 255.0
            val bColor = (layer.style.strokeColor and 0xFF) / 255.0
            sb.append("${r.format(precision)} ${g.format(precision)} ${bColor.format(precision)} setrgbcolor\n")
            sb.append("${layer.style.strokeWidth.format(precision)} setlinewidth\n")
            sb.append("stroke\n")
        }
        
        sb.append("grestore\n")
    }
    
    sb.append("%%EOF\n")
    return sb.toString()
}
