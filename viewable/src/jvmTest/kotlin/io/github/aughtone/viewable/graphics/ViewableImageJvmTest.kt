package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * `ImageIO.write` returns false (leaving the stream empty) when no writer handles a
 * format; `toByteArray` must never hand that back as a silently empty result.
 */
class ViewableImageJvmTest {

    private fun sampleImage(): ViewableImage =
        ViewablePath(
            listOf(
                PathCommand.MoveTo(0.0, 0.0),
                PathCommand.LineTo(10.0, 10.0),
                PathCommand.Close,
            ),
        ).toImage(width = 20, height = 20)

    @Test
    fun everyFormatEncodesToNonEmptyBytes() {
        for (format in ImageFormat.entries) {
            assertTrue(sampleImage().toByteArray(format).isNotEmpty(), "$format encoded to empty bytes")
        }
    }
}
