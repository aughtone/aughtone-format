package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.TimeZone

class DateTimeFormatExtTest {

    val testTimeZone = TimeZone.UTC

    val dateTime = LocalDateTime.parse("2022-01-01T12:00:00")

    @Test
    fun testShortFormatEnCa() {
        assertEquals(
            expected = "2022-01-01 12:00 p.m.",
            actual = dateTime.format(
                dateStyle = DateTimeStyle.SHORT,
                timeStyle = DateTimeStyle.SHORT,
                locale = Locale("en-CA"),
                timeZone = testTimeZone
            ),
            message = "Formatting short date time",
        )
    }

    @Test
    fun testShortFormatEnUs() {
        assertEquals(
            message = "Formatting short date time",
            expected = "1/1/2022 12:00 PM",
            actual = dateTime.format(
                dateStyle = DateTimeStyle.SHORT,
                timeStyle = DateTimeStyle.SHORT,
                locale = Locale("en-US"),
                timeZone = testTimeZone
            )
        )
    }

}
