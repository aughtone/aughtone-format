package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.ViewableGraphic
import io.github.aughtone.viewable.ViewablePath
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Represents a platform-specific bitmap or image.
 */
expect class ViewableImage

/**
 * Indicates if the current platform supports bitmap/image rendering.
 */
expect val isImageSupported: Boolean

/**
 * Renders a [ViewablePath] to a [ViewableImage].
 */
expect fun ViewablePath.toImage(
    width: Int,
    height: Int,
    fillColor: Int = 0xFF000000.toInt()
): ViewableImage

/**
 * Renders a [ViewableGraphic] to a [ViewableImage].
 */
expect fun ViewableGraphic.toImage(
    width: Int,
    height: Int
): ViewableImage

/**
 * Extension to export a [ViewableImage] as a byte array (e.g., PNG or WebP).
 */
expect fun ViewableImage.toByteArray(format: ImageFormat = ImageFormat.PNG): ByteArray

/**
 * Converts a [ViewableImage] to a Base64-encoded Data URI string.
 */
@OptIn(ExperimentalEncodingApi::class)
fun ViewableImage.toDataUri(format: ImageFormat = ImageFormat.PNG): String {
    val mimeType = when (format) {
        ImageFormat.PNG -> "image/png"
        ImageFormat.WEBP -> "image/webp"
        ImageFormat.JPEG -> "image/jpeg"
    }
    val base64 = Base64.encode(toByteArray(format))
    return "data:$mimeType;base64,$base64"
}

/**
 * Supported image formats for export.
 */
enum class ImageFormat {
    PNG,
    WEBP,
    JPEG
}
