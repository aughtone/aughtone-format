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
 * Indicates if the current platform supports bitmap/image rendering: true on the JVM, Android and
 * iOS, false on JS, Wasm, macOS and Linux, where the `toImage` and `toByteArray` calls throw.
 */
expect val isImageSupported: Boolean

/**
 * Renders a [ViewablePath] to a [ViewableImage].
 *
 * @param width The width of the rendered image in pixels.
 * @param height The height of the rendered image in pixels.
 * @param fillColor The ARGB fill color applied to the path. Defaults to opaque black.
 * @return The rendered [ViewableImage].
 * @throws IllegalArgumentException if the width or the height is not positive.
 * @throws IllegalStateException on JS, Wasm, macOS and Linux, where bitmap generation is
 *   unsupported. Guard with [isImageSupported] before calling on those targets.
 */
expect fun ViewablePath.toImage(
    width: Int,
    height: Int,
    fillColor: Int = 0xFF000000.toInt()
): ViewableImage

/**
 * Renders a [ViewableGraphic] to a [ViewableImage].
 *
 * @param width The width of the rendered image in pixels.
 * @param height The height of the rendered image in pixels.
 * @return The rendered [ViewableImage].
 * @throws IllegalArgumentException if the width or the height is not positive.
 * @throws IllegalStateException on JS, Wasm, macOS and Linux, where bitmap generation is
 *   unsupported. Guard with [isImageSupported] before calling on those targets.
 */
expect fun ViewableGraphic.toImage(
    width: Int,
    height: Int
): ViewableImage

/**
 * Extension to export a [ViewableImage] as a byte array (e.g., PNG or JPEG).
 *
 * @param format The [ImageFormat] to encode. Defaults to [ImageFormat.PNG].
 * @return The encoded image bytes. Never empty.
 * @throws IllegalStateException on JS, Wasm, macOS and Linux, where bitmap generation is
 *   unsupported — guard with [isImageSupported] before calling on those targets —
 *   and on any platform if the image cannot be encoded.
 */
expect fun ViewableImage.toByteArray(format: ImageFormat = ImageFormat.PNG): ByteArray

/**
 * Converts a [ViewableImage] to a Base64-encoded Data URI string.
 *
 * The URI's MIME type is derived directly from [format], and matches the encoded
 * payload (see [ImageFormat] for where each format is available).
 *
 * @param format The [ImageFormat] to encode. Defaults to [ImageFormat.PNG].
 * @return A `data:` URI containing the Base64-encoded image.
 * @throws IllegalStateException on JS and Wasm, where this transitively calls
 *   [toByteArray] and bitmap generation is unsupported. Guard with
 *   [isImageSupported] before calling on those targets.
 */
@OptIn(ExperimentalEncodingApi::class)
fun ViewableImage.toDataUri(format: ImageFormat = ImageFormat.PNG): String {
    val mimeType = when (format) {
        ImageFormat.PNG -> "image/png"
        ImageFormat.JPEG -> "image/jpeg"
    }
    val base64 = Base64.encode(toByteArray(format))
    return "data:$mimeType;base64,$base64"
}

/**
 * Supported image formats for export.
 *
 * Both are encoded on every platform that supports bitmap generation (JVM,
 * Android, iOS); JS and Wasm do not support bitmap generation at all. [JPEG]
 * carries no transparency, so transparent areas are flattened onto opaque
 * white — use [PNG] to keep them.
 */
enum class ImageFormat {
    PNG,
    JPEG
}

/**
 * Thrown by the image calls on a platform with no bitmap type ([isImageSupported] is false), naming
 * the [platform] and pointing at the SVG output, which every platform has.
 */
internal fun imageUnsupported(platform: String): Nothing =
    error("Bitmap generation is not supported on $platform yet. Use SVG strings instead.")
