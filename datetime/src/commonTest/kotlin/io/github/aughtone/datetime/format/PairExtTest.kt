package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlin.time.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days

class PairExtTest {

    val testTimeZone = TimeZone.UTC
    val dateTime = LocalDateTime.parse("2022-01-01T12:00:00")
    val instant1 = dateTime.toInstant(testTimeZone)
    val instant2 = instant1.plus(5.days)


    @Test
    fun testShortFormatDateTimeRangeEnCa() {
        val testPair1: Pair<Instant?, Instant?> = Pair(instant1, instant2)
        val testPair2: Pair<Long?, Long?> = Pair(instant1.toEpochMilliseconds(), instant2.toEpochMilliseconds())
        assertEquals(
            expected = "2022-01-01 12:00 p.m. - 2022-01-06 12:00 p.m.",
            actual = testPair1.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone
            ),
        )

        assertEquals(
            expected = "2022-01-01 12:00 p.m. - 2022-01-06 12:00 p.m.",
            actual = testPair2.formatAsDateTime(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone
            ),
        )
    }

    @Test
    fun testShortFormatPartialRangeOnlyEnCa() {
        val testPair3: Pair<Instant?, Instant?> = Pair(instant1, null)
        val testPair4: Pair<Instant?, Instant?> = Pair(null, instant2)
        assertEquals(
            expected = "2022-01-01 12:00 p.m. - ?",
            actual = testPair3.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone
            ),
        )

        assertEquals(
            expected = "? - 2022-01-06 12:00 p.m.",
            actual = testPair4.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone
            ),
        )
    }

    @Test
    fun testShortFormatEmptyRangeEnCa() {
        val testPair0: Pair<Instant?, Instant?> = Pair(null, null)
        assertEquals(
            expected = "? - ?",
            actual = testPair0.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone
            ),
        )
    }

    @Test
    fun testShortFormatChangedPlaceholderEnCa() {
        val testPair3: Pair<Instant?, Instant?> = Pair(instant1, null)
        val testPair4: Pair<Instant?, Instant?> = Pair(null, instant2)
        assertEquals(
            expected = "2022-01-01 12:00 p.m. - #",
            actual = testPair3.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone,
                placeholder = "#"
            ),
        )

        assertEquals(
            expected = "<missing> - 2022-01-06 12:00 p.m.",
            actual = testPair4.format(
                dateStyle = DateTimeStyle.Short,
                timeStyle = DateTimeStyle.Short,
                locale = localeFor("en-CA")!!,
                timeZone = testTimeZone,
                placeholder = "<missing>"
            ),
        )
    }


}
