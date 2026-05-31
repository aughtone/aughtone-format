package io.github.aughtone.viewable.graphics

import io.github.aughtone.viewable.PathCommand
import io.github.aughtone.viewable.ViewablePath
import io.github.aughtone.viewable.ViewableRect
import io.github.aughtone.viewable.WindingRule
import kotlin.test.Test
import kotlin.test.assertTrue

class ViewableImageTest {

    @Test
    fun testToImageAndToByteArray() {
        if (!isImageSupported) return
        val path = ViewablePath(
            commands = listOf(
                PathCommand.MoveTo(0.0, 0.0),
                PathCommand.LineTo(100.0, 0.0),
                PathCommand.LineTo(100.0, 100.0),
                PathCommand.LineTo(0.0, 100.0),
                PathCommand.Close
            ),
            winding = WindingRule.EvenOdd,
            bounds = ViewableRect(0.0, 0.0, 100.0, 100.0)
        )

        val image = path.toImage(200, 200)
        val bytes = image.toByteArray(ImageFormat.PNG)

        assertTrue(bytes.isNotEmpty(), "Generated PNG should not be empty")
    }
}
