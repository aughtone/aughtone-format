package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals

class InstantExtTest {

    val testTimeZone = TimeZone.UTC
    val dateTime = LocalDateTime.parse("2022-01-01T12:00:00")
    val instant1 = dateTime.toInstant(testTimeZone)

    @Test
    fun testShortFormatEnCa() {
        assertEquals(
            expected = "2022-01-01 12:00 p.m.",
            actual = instant1.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone
            ),
            message = "Formatting short date time",
        )
    }
}
