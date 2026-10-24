package io.github.aughtone.calendars

import io.github.aughtone.calendars.data.CHINESE_TABLE_FIRST_YEAR
import io.github.aughtone.calendars.data.CHINESE_TABLE_LAST_YEAR
import io.github.aughtone.calendars.internal.ChineseCalendar
import io.github.aughtone.calendars.internal.DangiCalendar
import io.github.aughtone.calendars.internal.LunisolarCalendar
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

/** The Chinese and Korean calendars: the table, and the computation outside it. */
class LunisolarTest {

    /** Month starts as `date|month|leap` lines, from 1901 to 2100. */
    private fun monthStarts(calendar: LunisolarCalendar, years: IntRange, computed: Boolean): Set<String> = years.flatMapTo(mutableSetOf()) { year ->
        val y = if (computed) calendar.computeYear(year) else calendar.yearOf(year)!!
        var start = y.newYear
        y.months.indices.map { i ->
            val line = "$start|${y.months[i]}|${if (i == y.leapIndex) 1 else 0}"
            start = start.plus(kotlinx.datetime.DatePeriod(days = y.lengths[i]))
            line
        }
    }

    private fun published(fixture: String, years: IntRange, column: Int = -1): Set<String> = fixture.trim().lines().mapNotNullTo(mutableSetOf()) { line ->
        val f = line.split('|')
        val year = f[0].take(4).toInt()
        if (year !in years) null else if (column < 0) line else "${f[0]}|${f[2]}|${f[3]}"
    }

    /** The months a calculation and a published list disagree on, either way. */
    private fun disagreements(ours: Set<String>, theirs: Set<String>) = (ours - theirs).map { "computed $it" } + (theirs - ours).map { "published $it" }

    @Test
    fun chineseTableMatchesThePublishedTables() {
        val years = 1902..2098 // whole years inside the table, with room for months that cross a year end
        val ours = monthStarts(ChineseCalendar, CHINESE_TABLE_FIRST_YEAR..CHINESE_TABLE_LAST_YEAR, computed = false).filter { it.take(4).toInt() in years }.toSet()
        assertEquals(published(chineseCalendarFixture, years), ours)
    }

    // The computation is checked in halves, so no one test runs long enough to meet a browser
    // runner's timeout.
    @Test fun chineseComputationMatchesThePublishedTablesUntil2000() = checkChinese(1902..2000)
    @Test fun chineseComputationMatchesThePublishedTablesFrom2001() = checkChinese(2001..2098)

    /**
     * The computation agrees with the published tables in every month but one: in April 1906 the
     * new moon falls within the calculation's accuracy of midnight.
     */
    private fun checkChinese(years: IntRange) {
        val ours = monthStarts(ChineseCalendar, (years.first - 1)..(years.last + 1), computed = true).filter { it.take(4).toInt() in years }.toSet()
        val off = disagreements(ours, published(chineseCalendarFixture, years))
        assertTrue(off.toSet() == if (1906 in years) setOf("computed 1906-04-23|4|0", "published 1906-04-24|4|0") else emptySet(), off.joinToString("\n"))
    }

    /**
     * ICU's Korean calendar starts four months on a different day from the computation. In each
     * the new moon falls within five minutes of midnight in Seoul, and the computation follows
     * the true new moon.
     */
    @Test fun dangiComputationMatchesIcuUntil2000() = checkDangi(1901..2000)
    @Test fun dangiComputationMatchesIcuFrom2001() = checkDangi(2001..2099)

    private val dangiKnownDifferences = setOf("2017-02-26", "2017-02-27", "2051-08-06", "2051-08-07", "2051-11-03", "2051-11-04", "2097-01-13", "2097-01-14")

    private fun checkDangi(years: IntRange) {
        val ours = monthStarts(DangiCalendar, (years.first - 1)..(years.last + 1), computed = true).filter { it.take(4).toInt() in years }.toSet()
        val icu = published(dangiIcuFixture, years, column = 1)
        val off = disagreements(ours, icu).filter { it.substringAfter(' ').take(10) !in dangiKnownDifferences }
        assertTrue(off.isEmpty(), "${off.size} months differ:\n" + off.joinToString("\n"))
    }

    @Test
    fun chineseNewYearAndALeapMonth() {
        val chinese = CalendarSystem.Chinese.calendar()!!
        assertEquals(CalendarDate(CalendarSystem.Chinese, 2024, 1, 1), (chinese.fromLocalDate(LocalDate(2024, 2, 10)) as CalendarResult.Resolved).date)
        // 2023 had a leap second month, beginning 22 March.
        assertEquals(2, chinese.leapMonth(2023))
        assertEquals(13, chinese.monthsInYear(2023))
        val leap = CalendarDate(CalendarSystem.Chinese, 2023, 2, 1, isLeapMonth = true)
        assertEquals(LocalDate(2023, 3, 22), chinese.toLocalDate(leap))
        assertNull(chinese.toLocalDate(CalendarDate(CalendarSystem.Chinese, 2024, 2, 1, isLeapMonth = true)))
    }

    @Test
    fun yearsOutsideTheTableAreComputedAndRoundTrip() {
        val chinese = CalendarSystem.Chinese.calendar()!!
        for (iso in listOf(LocalDate(1850, 6, 1), LocalDate(1899, 12, 31), LocalDate(2101, 3, 1), LocalDate(2150, 9, 9))) {
            val date = assertIs<CalendarResult.Resolved>(chinese.fromLocalDate(iso)).date
            assertEquals(iso, chinese.toLocalDate(date), "$iso → $date")
        }
        assertIs<CalendarResult.Unresolved>(chinese.fromLocalDate(LocalDate(1500, 1, 1)))
    }
}
