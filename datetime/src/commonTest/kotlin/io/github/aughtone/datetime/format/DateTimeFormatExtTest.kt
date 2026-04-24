package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
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
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
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
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-US")!!,
                timeZone = testTimeZone
            )
        )
    }

}
