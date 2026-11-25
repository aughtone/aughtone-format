package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.ViewableGraphic
import io.github.aughtone.viewable.ViewablePath

/** No bitmap type on macOS yet: [isImageSupported] is false, and every call here throws. */
actual class ViewableImage

actual val isImageSupported: Boolean = false

actual fun ViewablePath.toImage(
    width: Int,
    height: Int,
    fillColor: Int
): ViewableImage = imageUnsupported("macOS")

actual fun ViewableGraphic.toImage(
    width: Int,
    height: Int
): ViewableImage = imageUnsupported("macOS")

actual fun ViewableImage.toByteArray(format: ImageFormat): ByteArray = imageUnsupported("macOS")
