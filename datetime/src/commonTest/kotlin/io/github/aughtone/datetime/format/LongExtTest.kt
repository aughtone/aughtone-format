package io.github.aughtone.datetime.format

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days

class LongExtTest {

    val testTimeZone = TimeZone.UTC
    val testLong = 1641038400000L
    val testDateTime = LocalDateTime.parse("2022-01-01T12:00:00")
    val testTime = testDateTime.time
    val testDate = testDateTime.date
    val instant1 = testDateTime.toInstant(testTimeZone)

    @Test
    fun testLongToInstant() {
        assertEquals(
            expected = instant1.toEpochMilliseconds(),
            actual = testLong,
        )

        assertEquals(
            expected = instant1,
            actual = testLong.toInstant(),
        )
    }

    @Test
    fun testToLocalDateTime() {
        assertEquals(
            expected = testDateTime,
            actual = testLong.toLocalDateTime(testTimeZone),
        )
        assertEquals(
            expected = testLong,
            actual = testLong.toLocalDateTime(testTimeZone).toInstant(testTimeZone).toEpochMilliseconds(),
        )
    }

    @Test
    fun testToLocalDate() {
        assertEquals(
            expected = testDate.atTime(LocalTime(hour = 0, minute = 0, second = 0, nanosecond = 0)).toInstant(testTimeZone).toEpochMilliseconds(),
            actual = testLong.toLocalDate(testTimeZone).atStartOfDayIn(testTimeZone).toEpochMilliseconds(),
        )
    }

    @Test
    fun testToLocalTime() {
        assertEquals(
            expected = testTime.atDate(LocalDate(year = 2022, monthNumber = 1, dayOfMonth = 1)).toInstant(testTimeZone).toEpochMilliseconds(),
            actual = testLong.toLocalTime(testTimeZone).atDate(testDate).toInstant(testTimeZone).toEpochMilliseconds(),
        )
    }

    @Test
    fun testSerializer() {
        assertEquals(
            expected = "\"2022-01-01T12:00\"",
            actual = Json.encodeToString(testDateTime),
        )
        assertEquals(
            expected = "\"2022-01-01\"",
            actual = Json.encodeToString(testDate),
        )
        assertEquals(
            expected = "\"12:00\"",
            actual = Json.encodeToString(testTime),
        )
        assertEquals(
            expected = "\"2022-01-01T00:00:00Z\"",
            actual = Json.encodeToString(testLong.toLocalDate(testTimeZone).atStartOfDayIn(testTimeZone)),
        )
        assertEquals(
            expected = "\"00:00\"",
            actual = Json.encodeToString(testLong.toLocalDate(testTimeZone).atStartOfDayIn(testTimeZone).toLocalDateTime(testTimeZone).time),
        )

        assertEquals(
            expected = "{\"first\":\"12:00\",\"second\":\"12:00\"}",
            actual = Json.encodeToString(Pair(testTime,testTime)),
        )
    }
}
