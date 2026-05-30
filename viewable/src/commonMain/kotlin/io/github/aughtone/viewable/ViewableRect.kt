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
