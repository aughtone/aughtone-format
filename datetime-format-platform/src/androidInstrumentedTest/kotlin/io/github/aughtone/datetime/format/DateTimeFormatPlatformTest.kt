package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import androidx.test.filters.SmallTest
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals
//import kotlin.test.Test

class DateTimeFormatPlatformTest {

    val testTimeZone = TimeZone.currentSystemDefault()

    val dateTime = LocalDateTime.parse("2022-01-01T12:00:00")

    @Test
    @SmallTest
    fun testShortFormatEnCa() {
        assertEquals(
            message = "Formatting short date time",
            expected = "2022-01-01 12:00 p.m.", // 
            actual = dateTime.systemFormat(
                dateStyle = DateTimeStyle.SHORT,
                timeStyle = DateTimeStyle.SHORT,
                locale = Locale("en-CA"),
                timeZone = testTimeZone
            )
        )
    }

    @Test
    @SmallTest
    fun testShortFormatEnUs() {
        assertEquals(
            message = "Formatting short date time",
            expected = "1/1/22 12:00 PM", // 
            actual = dateTime.systemFormat(
                dateStyle = DateTimeStyle.SHORT,
                timeStyle = DateTimeStyle.SHORT,
                locale = Locale("en-US"),
                timeZone = testTimeZone
            )
        )
    }

}
