package io.github.aughtone.viewable

/**
 * Defines the visual style for a [ViewablePath].
 *
 * @property fillColor The fill color as an ARGB Int. Use 0 for no fill.
 * @property strokeColor The stroke color as an ARGB Int. Use 0 for no stroke.
 * @property strokeWidth The width of the stroke in local coordinates.
 */
data class ViewableStyle(
    val fillColor: Int = 0,
    val strokeColor: Int = 0,
    val strokeWidth: Double = 0.0
)
