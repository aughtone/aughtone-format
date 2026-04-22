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

class SerializerTest {

    val testTimeZone = TimeZone.UTC
    val testLong = 1641038400000L
    val testDateTime = LocalDateTime.parse("2022-01-01T12:00:00")
    val testTime = testDateTime.time
    val testDate = testDateTime.date
    val instant1 = testDateTime.toInstant(testTimeZone)


    @Test
    fun testSerializer() {
        assertEquals(
            expected = "\"2022-01-01T12:00:00Z\"",
            actual = Json.encodeToString(instant1),
        )
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
