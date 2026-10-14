package io.github.aughtone.viewable

/**
 * A platform-agnostic rectangle.
 */
data class ViewableRect(
    val left: Double,
    val top: Double,
    val right: Double,
    val bottom: Double
) {
    val width: Double get() = right - left
    val height: Double get() = bottom - top
}

/**
 * This rectangle with any zero dimension opened up so there is room to draw in: a flat line gets
 * a square box of its own length centred on it, and a single point gets a 1×1 box around it.
 * A rectangle that already has width and height is returned as it is.
 */
internal fun ViewableRect.withDrawableExtent(): ViewableRect {
    if (width > 0.0 && height > 0.0) return this
    val side = maxOf(width, height).takeIf { it > 0.0 } ?: 1.0
    val newWidth = if (width > 0.0) width else side
    val newHeight = if (height > 0.0) height else side
    val centerX = (left + right) / 2.0
    val centerY = (top + bottom) / 2.0
    return ViewableRect(centerX - newWidth / 2.0, centerY - newHeight / 2.0, centerX + newWidth / 2.0, centerY + newHeight / 2.0)
}

/** A uniform [scale] and the offsets that centre the scaled content. */
internal data class FitTransform(val scale: Double, val dx: Double, val dy: Double)

/**
 * The transform that fits this rectangle into a [width] by [height] area, keeping its shape and
 * centring it. A dimension of zero does not limit the scale, so a flat line is scaled along its
 * length; a rectangle with no extent at all is drawn at its own scale.
 */
internal fun ViewableRect.fitInto(width: Double, height: Double): FitTransform {
    val scaleX = if (this.width > 0.0) width / this.width else null
    val scaleY = if (this.height > 0.0) height / this.height else null
    val scale = listOfNotNull(scaleX, scaleY).minOrNull() ?: 1.0
    return FitTransform(scale, (width - this.width * scale) / 2.0, (height - this.height * scale) / 2.0)
}
