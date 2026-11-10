package io.github.aughtone.readable.fields

import io.github.aughtone.datetime.format.TextWidth
import io.github.aughtone.datetime.format.resources.NumberingSystem
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
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ReadableFieldsTest {

    private fun locale(tag: String): Locale = localeFor(tag)!!
    private val date = LocalDate(2024, 6, 1)
    private val dateTime = LocalDateTime(2024, 6, 1, 16, 5, 6)

    @Test
    fun theLocaleLaysOutTheFields() {
        val monthDay = ReadableFields.MonthDay
        assertEquals("Jun 1", date.formatReadableFields(monthDay, locale("en-US")))
        assertEquals("1 Jun", date.formatReadableFields(monthDay, locale("en-GB")))
        assertEquals("1. Juni", date.formatReadableFields(monthDay, locale("de")))
        assertEquals("1 juin", date.formatReadableFields(monthDay, locale("fr")))
        assertEquals("6月1日", date.formatReadableFields(monthDay, locale("ja")))
        assertEquals("jún. 1.", date.formatReadableFields(monthDay, locale("hu")))
    }

    @Test
    fun namesTakeTheFormTheLanguageUsesInThatPhrase() {
        // Inside a date the Russian month is genitive; beside a year CLDR asks for the
        // stand-alone form, in lower case.
        assertEquals("1 июня", date.formatReadableFields("MMMMd", locale("ru")))
        // CLDR keeps "г." on the year's line with a narrow no-break space.
        assertEquals("июнь 2024\u202Fг.", date.formatReadableFields(ReadableFields.YearMonth, locale("ru")))
        assertEquals("1 de junio", date.formatReadableFields("MMMMd", locale("es")))
        assertEquals("junio de 2024", date.formatReadableFields(ReadableFields.YearMonth, locale("es")))
        assertEquals("Juni 2024", date.formatReadableFields(ReadableFields.YearMonth, locale("de")))
    }

    @Test
    fun theThreeFormsWriteTheSameText() {
        for (tag in listOf("en-US", "en-GB", "de", "ru", "ja", "ar-EG", "hu")) {
            val l = locale(tag)
            val byValue = date.formatReadableFields(ReadableFields(weekday = TextWidth.Abbreviated, month = TextWidth.Abbreviated, day = true), l)
            val byBlock = date.formatReadableFields(l) { weekday(TextWidth.Abbreviated); month(TextWidth.Abbreviated); day() }
            val bySkeleton = date.formatReadableFields("MMMEd", l)
            assertEquals(byValue, byBlock, tag)
            assertEquals(byValue, bySkeleton, tag)
        }
    }

    @Test
    fun theOrderTheFieldsAreNamedInHasNoEffect() {
        val l = locale("en-US")
        assertEquals(
            date.formatReadableFields(l) { month(TextWidth.Abbreviated); day(); year() },
            date.formatReadableFields(l) { year(); day(); month(TextWidth.Abbreviated) },
        )
        assertEquals(date.formatReadableFields("MMMdy", l), date.formatReadableFields("yMMMd", l))
        assertEquals(ReadableFields { day(); month(TextWidth.Abbreviated) }, ReadableFields.MonthDay)
    }

    @Test
    fun timesFollowTheClockAsked() {
        val time = LocalTime(16, 5, 6)
        assertEquals("4:05 PM", time.formatReadableFields(ReadableFields.HourMinute, locale("en-US"), is24HourFormat = false))
        assertEquals("16:05", time.formatReadableFields(ReadableFields.HourMinute, locale("en-US"), is24HourFormat = true))
        assertEquals("16:05:06", time.formatReadableFields("Hms", locale("de")))
        assertEquals("4:05:06 PM", time.formatReadableFields("hms", locale("en-US"), is24HourFormat = true))
        assertEquals("16:05", time.formatReadableFields("jm", locale("en-US"), is24HourFormat = true))
        assertEquals("Sat 4:05 PM", dateTime.formatReadableFields(ReadableFields.WeekdayHourMinute, locale("en-US"), is24HourFormat = false))
    }

    @Test
    fun aDateAndATimeAreJoinedAsTheLocaleJoinsThem() {
        assertEquals("June 1, 2024 at 4:05 PM", dateTime.formatReadableFields("yMMMMdhm", locale("en-US")))
        assertEquals("Jun 1, 2024, 4:05 PM", dateTime.formatReadableFields("yMMMdhm", locale("en-US")))
        assertEquals("1. Juni 2024 um 16:05", dateTime.formatReadableFields("yMMMMdHm", locale("de")))
    }

    @Test
    fun anInstantIsReadInItsZone() {
        // Named zones are checked on the JVM, where every target's tests have a zone database.
        val instant = dateTime.toInstant(TimeZone.UTC)
        assertEquals("4:05 PM", instant.formatReadableFields(ReadableFields.HourMinute, locale("en-US"), TimeZone.UTC, is24HourFormat = false))
        assertEquals("12:05 PM", instant.formatReadableFields(ReadableFields.HourMinute, locale("en-US"), TimeZone.of("UTC-4"), is24HourFormat = false))
        assertTrue(instant.formatReadableFields("hmz", locale("en-US"), TimeZone.UTC).startsWith("4:05 PM "))
    }

    @Test
    fun aFieldNoPatternCoversIsAddedAsTheLocaleAddsIt() {
        // CLDR has no pattern for a year with a day and no month; it adds the day by its name.
        assertEquals("2024 (day: 1)", date.formatReadableFields("yd", locale("en-US")))
        assertEquals("2024 (Tag: 1)", date.formatReadableFields(ReadableFields(year = true, day = true), locale("de")))
    }

    @Test
    fun aDateBeforeYearOneShowsItsEra() {
        val idesOfMarch = LocalDate(-43, 3, 15)
        assertEquals("Mar 15, 44 BCE", idesOfMarch.formatReadableFields(ReadableFields.YearMonthDay, locale("en-US")))
        assertEquals("Mar 15, 2024 CE", LocalDate(2024, 3, 15).formatReadableFields("GyMMMd", locale("en-US")))
    }

    @Test
    fun digitsFollowTheLocaleOrTheSystemAsked() {
        assertEquals("١ يونيو", date.formatReadableFields(ReadableFields.MonthDay, locale("ar-EG")))
        assertEquals("1 يونيو", date.formatReadableFields(ReadableFields.MonthDay, locale("ar-EG"), NumberingSystem.LATN))
    }

    @Test
    fun anythingThatShowsNothingIsRejected() {
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields(ReadableFields(), locale("en")) }
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields(locale("en")) { } }
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields("", locale("en")) }
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields("  ", locale("en")) }
        // Only fields a time has, asked of a date.
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields(ReadableFields.HourMinute, locale("en")) }
        assertTrue(ReadableFields().isEmpty)
    }

    @Test
    fun aFieldTheValueDoesNotHaveIsNamed() {
        val weekday = assertFailsWith<IllegalArgumentException> { LocalTime(16, 5).formatReadableFields("Ehm", locale("en")) }
        assertTrue("weekday" in weekday.message!!, weekday.message)
        val zone = assertFailsWith<IllegalArgumentException> { dateTime.formatReadableFields("hmz", locale("en")) }
        assertTrue("time zone" in zone.message!!, zone.message)
        val hour = assertFailsWith<IllegalArgumentException> { date.formatReadableFields("MMMdh", locale("en")) }
        assertTrue("hour" in hour.message!!, hour.message)
    }

    @Test
    fun aSkeletonWithAnUnofferedLetterOrWidthIsRejectedByName() {
        val quarter = assertFailsWith<IllegalArgumentException> { date.formatReadableFields("yQQQ", locale("en")) }
        assertTrue("'Q'" in quarter.message!!, quarter.message)
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields("yyMd", locale("en")) }
        assertFailsWith<IllegalArgumentException> { date.formatReadableFields("MMMMMd", locale("en")) }
        val twice = assertFailsWith<IllegalArgumentException> { date.formatReadableFields("MdM", locale("en")) }
        assertTrue("twice" in twice.message!!, twice.message)
    }

    @Test
    fun aMonthIsANameOrANumberNotBoth() {
        assertFailsWith<IllegalArgumentException> { ReadableFields(month = TextWidth.Full, monthNumber = true) }
        // The builder keeps the last one named.
        assertEquals(ReadableFields(monthNumber = true, day = true), ReadableFields { month(); monthNumber(); day() })
    }

    @Test
    fun aReadableFieldsIsAValue() {
        assertEquals(ReadableFields(month = TextWidth.Abbreviated, day = true), ReadableFields.MonthDay)
        assertEquals(ReadableFields.MonthDay.hashCode(), ReadableFields(month = TextWidth.Abbreviated, day = true).hashCode())
        assertNotEquals(ReadableFields.MonthDay, ReadableFields.YearMonthDay)
    }
}
