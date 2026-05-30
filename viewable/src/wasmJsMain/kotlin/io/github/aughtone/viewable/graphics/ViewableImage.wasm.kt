package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.ViewableGraphic
import io.github.aughtone.viewable.ViewablePath

actual class ViewableImage

actual fun ViewablePath.toImage(
    width: Int,
    height: Int,
    fillColor: Int
): ViewableImage {
    error("Bitmap generation is not supported on WASM yet.")
}

actual fun ViewableGraphic.toImage(
    width: Int,
    height: Int
): ViewableImage {
    error("Bitmap generation is not supported on WASM yet.")
}

actual fun ViewableImage.toByteArray(format: ImageFormat): ByteArray {
    error("Bitmap generation is not supported on WASM yet.")
}
