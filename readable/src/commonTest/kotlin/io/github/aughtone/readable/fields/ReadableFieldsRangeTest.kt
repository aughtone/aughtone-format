package io.github.aughtone.readable.fields

import io.github.aughtone.datetime.format.TextWidth
import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ReadableFieldsRangeTest {

    private fun locale(tag: String): Locale = localeFor(tag)!!
    private val us get() = locale("en-US")

    /** English's range dash: a no-break space before it, a breakable thin space after. */
    private val dash = " – "
    private val start = LocalDate(2024, 6, 1)

    @Test
    fun whatBothEndsShareIsWrittenOnce() {
        val monthDay = ReadableFields.MonthDay
        assertEquals("Jun 1${dash}3", start.formatReadableFieldsRange(LocalDate(2024, 6, 3), monthDay, us))
        assertEquals("Jun 1${dash}Sep 30", start.formatReadableFieldsRange(LocalDate(2024, 9, 30), monthDay, us))
        assertEquals("1. Juni\u202F–\u200930. Sept.", start.formatReadableFieldsRange(LocalDate(2024, 9, 30), "MMMd", locale("de")))
        assertEquals("1.–3. Juni", start.formatReadableFieldsRange(LocalDate(2024, 6, 3), monthDay, locale("de")))
    }

    @Test
    fun aLargerFieldThatDiffersIsAddedToBothEnds() {
        assertEquals("Jun 1, 2024${dash}Jun 1, 2025", start.formatReadableFieldsRange(LocalDate(2025, 6, 1), ReadableFields.MonthDay, us))
        assertEquals("Jun 2024${dash}Jun 2025", start.formatReadableFieldsRange(LocalDate(2025, 6, 1), "MMM", us))
        assertEquals("2024${dash}2025", start.formatReadableFieldsRange(LocalDate(2025, 6, 1), "y", us))
    }

    @Test
    fun equalEndsAreWrittenOnce() {
        assertEquals("Jun 1", start.formatReadableFieldsRange(start, ReadableFields.MonthDay, us))
        assertEquals("9:00 AM", LocalTime(9, 0).formatReadableFieldsRange(LocalTime(9, 0), "hm", us))
    }

    @Test
    fun aDifferenceOnlyInFieldsNotShownIsOneValue() {
        assertEquals("Jun", start.formatReadableFieldsRange(LocalDate(2024, 6, 20), "MMM", us))
    }

    @Test
    fun theThreeFormsWriteTheSameText() {
        val end = LocalDate(2024, 9, 30)
        for (tag in listOf("en-US", "de", "ja", "ru")) {
            val l = locale(tag)
            val byValue = start.formatReadableFieldsRange(end, ReadableFields(month = TextWidth.Abbreviated, day = true, weekday = TextWidth.Abbreviated), l)
            val byBlock = start.formatReadableFieldsRange(end, l) { weekday(TextWidth.Abbreviated); month(TextWidth.Abbreviated); day() }
            val bySkeleton = start.formatReadableFieldsRange(end, "MMMEd", l)
            assertEquals(byValue, byBlock, tag)
            assertEquals(byValue, bySkeleton, tag)
        }
    }

    @Test
    fun timesFollowTheClockAsked() {
        val nine = LocalTime(9, 0)
        val half = LocalTime(11, 30)
        assertEquals("9:00${dash}11:30 AM", nine.formatReadableFieldsRange(half, ReadableFields.HourMinute, us, is24HourFormat = false))
        assertEquals("09:00${dash}11:30", nine.formatReadableFieldsRange(half, ReadableFields.HourMinute, us, is24HourFormat = true))
        assertEquals("09:00–11:30 Uhr", nine.formatReadableFieldsRange(half, "Hm", locale("de")))
        assertEquals(
            nine.formatReadableFieldsRange(half, us, is24HourFormat = false) { hour(); minute() },
            nine.formatReadableFieldsRange(half, "jm", us, is24HourFormat = false),
        )
    }

    @Test
    fun dateTimesOnOneDayWriteTheDateOnce() {
        val fields = ReadableFields { month(TextWidth.Abbreviated); day(); hour(); minute() }
        val from = LocalDateTime(2024, 6, 1, 9, 0)
        assertEquals(
            "Jun 1, 9:00 AM${dash}5:00 PM",
            from.formatReadableFieldsRange(LocalDateTime(2024, 6, 1, 17, 0), fields, us, is24HourFormat = false),
        )
        assertEquals(
            "Jun 1, 9:00 AM${dash}Jun 3, 5:00 PM",
            from.formatReadableFieldsRange(LocalDateTime(2024, 6, 3, 17, 0), fields, us, is24HourFormat = false),
        )
    }

    @Test
    fun timesOnDifferentDaysShowTheirDates() {
        val from = LocalDateTime(2024, 6, 1, 22, 0)
        val range = from.formatReadableFieldsRange(LocalDateTime(2024, 6, 2, 2, 0), ReadableFields.HourMinute, us, is24HourFormat = true)
        assertEquals("6/1/2024, 22:00${dash}6/2/2024, 02:00", range)
    }

    @Test
    fun instantsAreReadInTheZone() {
        // A fixed offset, which every platform's kotlinx-datetime knows without a zone database.
        val zone = TimeZone.of("UTC-4")
        val from = LocalDateTime(2024, 6, 1, 9, 0).toInstant(zone)
        val to = LocalDateTime(2024, 6, 1, 17, 0).toInstant(zone)
        assertEquals(
            "9:00 AM${dash}5:00 PM",
            from.formatReadableFieldsRange(to, ReadableFields.HourMinute, us, zone, is24HourFormat = false),
        )
        assertTrue(
            from.formatReadableFieldsRange(to, us, zone, is24HourFormat = false) { hour(); minute(); timeZone(TextWidth.Abbreviated) }
                .startsWith("9:00 AM${dash}5:00 PM "),
        )
        assertEquals(
            from.formatReadableFieldsRange(to, "hm", us, zone),
            from.formatReadableFieldsRange(to, us, zone, is24HourFormat = false) { hour(); minute() },
        )
    }

    @Test
    fun separatorsDecideTheSpacesInsideEachEnd() {
        // The time keeps a plain space before AM, as the single value does; Typographic makes it no-break.
        val nine = LocalTime(9, 0)
        assertEquals("9:00${dash}11:30\u00A0AM", nine.formatReadableFieldsRange(LocalTime(11, 30), "hm", us, separators = Separators.Typographic))
        val end = LocalDate(2024, 9, 30)
        assertEquals("Jun 1${dash}Sep 30", start.formatReadableFieldsRange(end, ReadableFields.MonthDay, us, separators = Separators.Typographic))
        assertEquals("Jun 1 – Sep 30", start.formatReadableFieldsRange(end, ReadableFields.MonthDay, us, noBreak = true))
    }

    @Test
    fun fieldsTheValueDoesNotHaveAreRefused() {
        assertFailsWith<IllegalArgumentException> { start.formatReadableFieldsRange(start, ReadableFields.HourMinute, us) }
        assertFailsWith<IllegalArgumentException> { LocalTime(9, 0).formatReadableFieldsRange(LocalTime(10, 0), ReadableFields.MonthDay, us) }
        assertFailsWith<IllegalArgumentException> { start.formatReadableFieldsRange(start, us) { } }
        assertFailsWith<IllegalArgumentException> { start.formatReadableFieldsRange(start, "Mq", us) }
    }
}
