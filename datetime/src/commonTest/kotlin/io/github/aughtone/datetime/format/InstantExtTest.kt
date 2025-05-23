package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit

class InstantExtTest {

    val testTimeZone = TimeZone.UTC
    val dateTime = LocalDateTime.parse("2022-01-01T12:00:00")
    val instant1 = dateTime.toInstant(testTimeZone)
    val instant2 = instant1.plus(5.days)

    @Test
    fun testShortFormatEnCa() {
        assertEquals(
            expected = "2022-01-01 12:00 p.m.",
            actual = instant1.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = Locale("en-CA"),
                timeZone = testTimeZone
            ),
            message = "Formatting short date time",
        )
    }

    @Ignore // test wont run outside of android, we are going to remove the offending library.
    @Test
    fun testRelativeInstant() {
        assertEquals(
            "5 days",
            instant1.formatRelative(
                until = 6.days,
                relativeTo = instant2,
                timeStyle = DateTimeStyle.Long
            )
        )
        assertEquals(
            "2022-01-01 7:00:00 a.m.",
            instant1.formatRelative(
                until = 5.days,
                relativeTo = instant2,
                timeStyle = DateTimeStyle.Long
            )
        )
        assertEquals(
            "2022-01-01 7:00:00 a.m.",
            instant1.formatRelative(
                until = 4.days,
                relativeTo = instant2,
                timeStyle = DateTimeStyle.Long
            )
        )
        assertEquals(
            "7:00:00 a.m.",
            instant1.formatRelative(
                until = 4.days,
                relativeTo = instant2,
                timeStyle = DateTimeStyle.Long,
                dateStyle = DateTimeStyle.None
            )
        )
        assertEquals(
            "5d",
            instant1.formatRelative(
                until = 6.days,
                relativeTo = instant2,
                relativeStyle = RelativeStyle.Short
            )
        )

    }

    @Test
    fun testRelativeInstantOutOfRange() {
        try {
            instant1.formatRelative(relativeTo = instant2, relativeTime = RelativeTime.Future)
            fail("Should have thrown an exception")
        } catch (e: IllegalArgumentException) {
            assertEquals(
                "For a relativeTime of Future the relativeTo must be before this.",
                e.message
            )
        }

        try {
            instant2.formatRelative(relativeTo = instant1, relativeTime = RelativeTime.Past)
            fail("Should have thrown an exception")
        } catch (e: IllegalArgumentException) {
            assertEquals("For a relativeTime of Past the relativeTo must be after this.", e.message)
        }
    }

    @Ignore // test wont run outside of android, we are going to remove the offending library.
    @Test
    fun testRelativeInstantAutoSelect() {

        assertEquals(
            "2022-01-01 7:00 a.m.",
            instant1.formatRelative(until = 6.days, relativeTo = instant2)
        )
        assertEquals("5d", instant2.formatRelative(until = 6.days, relativeTo = instant1))

    }


    @Test
    fun `test formatRelativeRemainingIn`() {
        assertEquals("10d",instant1.formatRelativeRemainingIn(duration = 10.days, timestamp = instant2.toEpochMilliseconds(), unit = DurationUnit.DAYS))
        assertEquals("120h",instant2.formatRelativeRemainingIn(duration = 10.days, timestamp = instant1.toEpochMilliseconds()))
    }
}
