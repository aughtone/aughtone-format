package io.github.aughtone.calendars

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

/** The calendar API: names, leap months, the shape of years and months, and dates that do not exist. */
class CalendarTest {

    private fun on(system: CalendarSystem, iso: String): CalendarDate =
        assertIs<CalendarResult.Resolved>(LocalDate.parse(iso).toCalendarDate(system)).date

    @Test
    fun anRscaleValueIsACldrName() {
        assertEquals(CalendarSystem.Hebrew, CalendarSystem.forCldrName("hebrew"))
        assertEquals(CalendarSystem.Hebrew, CalendarSystem.forCldrName("HEBREW"))
        assertEquals(CalendarSystem.IslamicUmmAlQura, CalendarSystem.forCldrName("islamic-umalqura"))
        assertEquals(CalendarSystem.EthiopicAmeteAlem, CalendarSystem.forCldrName("ethioaa"))
        assertNull(CalendarSystem.forCldrName("gregorian"))
        assertNull(CalendarSystem.forCldrName(""))
        assertEquals(CalendarSystem.entries.size, CalendarSystem.entries.map { it.cldrName }.toSet().size)
    }

    @Test
    fun adarIIsTheLeapMonthAfterShevat() {
        // 5784 is a Hebrew leap year: Adar I began on 10 February 2024 and Adar II on 11 March.
        assertEquals(CalendarDate(CalendarSystem.Hebrew, 5784, 5, 1, isLeapMonth = true), on(CalendarSystem.Hebrew, "2024-02-10"))
        assertEquals(CalendarDate(CalendarSystem.Hebrew, 5784, 6, 1), on(CalendarSystem.Hebrew, "2024-03-11"))
        val hebrew = CalendarSystem.Hebrew.calendar()!!
        assertEquals(13, hebrew.monthsInYear(5784))
        assertEquals(5, hebrew.leapMonth(5784))
        assertEquals(30, hebrew.daysInMonth(5784, 5, isLeapMonth = true))
        assertEquals(12, hebrew.monthsInYear(5783))
        assertNull(hebrew.leapMonth(5783))
        // 5783 has no Adar I.
        assertNull(hebrew.daysInMonth(5783, 5, isLeapMonth = true))
        assertNull(hebrew.toLocalDate(CalendarDate(CalendarSystem.Hebrew, 5783, 5, 1, isLeapMonth = true)))
        assertEquals(LocalDate(2024, 4, 23), CalendarDate(CalendarSystem.Hebrew, 5784, 7, 15).toLocalDate())
    }

    @Test
    fun theHebrewYearHasOneOfSixLengths() {
        val hebrew = CalendarSystem.Hebrew.calendar()!!
        for (year in 5600..6000) {
            val length = (1..12).sumOf { hebrew.daysInMonth(year, it)!! } + (hebrew.leapMonth(year)?.let { hebrew.daysInMonth(year, it, isLeapMonth = true)!! } ?: 0)
            assertTrue(length in setOf(353, 354, 355, 383, 384, 385), "$year has $length days")
        }
    }

    @Test
    fun copticAndEthiopicYearsHaveAThirteenthMonth() {
        val ethiopic = CalendarSystem.Ethiopic.calendar()!!
        assertEquals(13, ethiopic.monthsInYear(2016))
        assertEquals(5, ethiopic.daysInMonth(2016, 13))
        assertEquals(6, ethiopic.daysInMonth(2015, 13))
        assertNull(ethiopic.daysInMonth(2016, 14))
        assertEquals(CalendarDate(CalendarSystem.Ethiopic, 2017, 1, 1), on(CalendarSystem.Ethiopic, "2024-09-11"))
        assertEquals(CalendarDate(CalendarSystem.EthiopicAmeteAlem, 7517, 1, 1), on(CalendarSystem.EthiopicAmeteAlem, "2024-09-11"))
    }

    @Test
    fun japaneseYearsRestartWithEachEra() {
        assertEquals(CalendarDate(CalendarSystem.Japanese, 64, 1, 7, era = "showa"), on(CalendarSystem.Japanese, "1989-01-07"))
        assertEquals(CalendarDate(CalendarSystem.Japanese, 1, 1, 8, era = "heisei"), on(CalendarSystem.Japanese, "1989-01-08"))
        assertEquals(CalendarDate(CalendarSystem.Japanese, 1, 5, 1, era = "reiwa"), on(CalendarSystem.Japanese, "2019-05-01"))
        // Heisei 1 began on 8 January 1989, so its 7 January does not exist; nor does a date without an era.
        assertNull(CalendarDate(CalendarSystem.Japanese, 1, 1, 7, era = "heisei").toLocalDate())
        assertNull(CalendarDate(CalendarSystem.Japanese, 1, 1, 8).toLocalDate())
        assertNull(CalendarDate(CalendarSystem.Japanese, 1, 1, 8, era = "nonexistent").toLocalDate())
        // Heisei ended on 30 April 2019: its 31st year has no June.
        assertNull(CalendarSystem.Japanese.calendar()!!.daysInMonth(31, 6, era = "heisei"))
        assertEquals(30, CalendarSystem.Japanese.calendar()!!.daysInMonth(31, 4, era = "heisei"))
        // Before the first era there is no Japanese date.
        assertIs<CalendarResult.Unresolved>(LocalDate(600, 1, 1).toCalendarDate(CalendarSystem.Japanese))
    }

    @Test
    fun rocYearsBeforeMinguoOneAreZeroAndNegative() {
        assertEquals(CalendarDate(CalendarSystem.Roc, 1, 1, 1), on(CalendarSystem.Roc, "1912-01-01"))
        assertEquals(CalendarDate(CalendarSystem.Roc, 0, 12, 31), on(CalendarSystem.Roc, "1911-12-31"))
        assertEquals(CalendarDate(CalendarSystem.Roc, -11, 1, 1), on(CalendarSystem.Roc, "1900-01-01"))
        assertEquals(CalendarDate(CalendarSystem.Buddhist, 2567, 3, 20), on(CalendarSystem.Buddhist, "2024-03-20"))
    }

    @Test
    fun ummAlQuraUsesItsTableAndTheCivilCalendarOutsideIt() {
        assertEquals(CalendarDate(CalendarSystem.IslamicUmmAlQura, 1300, 1, 1), on(CalendarSystem.IslamicUmmAlQura, "1882-11-12"))
        // AH 1300 Muharram has 30 days in the table, where the civil calendar would end the year a day sooner.
        assertEquals(CalendarDate(CalendarSystem.IslamicUmmAlQura, 1301, 1, 1), on(CalendarSystem.IslamicUmmAlQura, "1883-11-01"))
        assertEquals(CalendarDate(CalendarSystem.IslamicCivil, 1300, 12, 30), on(CalendarSystem.IslamicCivil, "1883-11-01"))
        // Outside the table, the civil calendar.
        val outside = on(CalendarSystem.IslamicUmmAlQura, "1800-01-01")
        assertEquals(on(CalendarSystem.IslamicCivil, "1800-01-01").copy(calendar = CalendarSystem.IslamicUmmAlQura), outside)
        assertEquals(LocalDate(1800, 1, 1), outside.toLocalDate())
    }

    @Test
    fun aDayPastTheEndOfItsMonthDoesNotExist() {
        assertNull(CalendarDate(CalendarSystem.Persian, 1402, 12, 30).toLocalDate()) // 1402 is not a leap year
        assertEquals(LocalDate(2025, 3, 20), CalendarDate(CalendarSystem.Persian, 1403, 12, 30).toLocalDate())
        assertNull(CalendarDate(CalendarSystem.IslamicCivil, 1445, 2, 30).toLocalDate())
        assertNull(CalendarDate(CalendarSystem.Indian, 1946, 0, 1).toLocalDate())
        assertNull(CalendarDate(CalendarSystem.Coptic, 1741, 1, 0).toLocalDate())
        assertNull(CalendarDate(CalendarSystem.Buddhist, 2567, 2, 30).toLocalDate())
        assertNull(CalendarDate(CalendarSystem.Persian, 1403, 1, 1, isLeapMonth = true).toLocalDate())
        // A date given to the wrong calendar is not converted.
        assertNull(CalendarSystem.Persian.calendar()!!.toLocalDate(CalendarDate(CalendarSystem.Coptic, 1741, 1, 1)))
    }

    /**
     * Every week from 1900 to 2100 converts to [system] and back to the same day. One test per
     * calendar, so none runs long enough to meet a browser runner's timeout.
     */
    private fun roundTrips(system: CalendarSystem) {
        val calendar = system.calendar()!!
        var date = LocalDate(1900, 1, 1)
        val end = LocalDate(2100, 12, 31)
        while (date <= end) {
            val inCalendar = assertIs<CalendarResult.Resolved>(calendar.fromLocalDate(date), "$system $date").date
            assertEquals(date, calendar.toLocalDate(inCalendar), "$system $inCalendar")
            date = date.plus(DatePeriod(days = 7))
        }
    }

    @Test fun buddhistRoundTrips() = roundTrips(CalendarSystem.Buddhist)
    @Test fun japaneseRoundTrips() = roundTrips(CalendarSystem.Japanese)
    @Test fun rocRoundTrips() = roundTrips(CalendarSystem.Roc)
    @Test fun hebrewRoundTrips() = roundTrips(CalendarSystem.Hebrew)
    @Test fun islamicCivilRoundTrips() = roundTrips(CalendarSystem.IslamicCivil)
    @Test fun islamicTabularRoundTrips() = roundTrips(CalendarSystem.IslamicTabular)
    @Test fun ummAlQuraRoundTrips() = roundTrips(CalendarSystem.IslamicUmmAlQura)
    @Test fun persianRoundTrips() = roundTrips(CalendarSystem.Persian)
    @Test fun copticRoundTrips() = roundTrips(CalendarSystem.Coptic)
    @Test fun ethiopicRoundTrips() = roundTrips(CalendarSystem.Ethiopic)
    @Test fun ethiopicAmeteAlemRoundTrips() = roundTrips(CalendarSystem.EthiopicAmeteAlem)
    @Test fun indianRoundTrips() = roundTrips(CalendarSystem.Indian)

    @Test
    fun observationBasedCalendarsAreNotComputed() {
        for (system in listOf(CalendarSystem.Islamic, CalendarSystem.IslamicSaudi)) {
            assertNull(system.calendar())
            assertIs<CalendarResult.Unresolved>(LocalDate(2024, 1, 1).toCalendarDate(system))
            assertNull(CalendarDate(system, 1445, 1, 1).toLocalDate())
        }
    }

    @Test
    fun extremeYearsDoNotThrow() {
        for (system in CalendarSystem.entries) {
            val calendar = system.calendar() ?: continue
            for (year in listOf(Int.MIN_VALUE, -1_000_000, 0, 1_000_000, Int.MAX_VALUE)) {
                calendar.toLocalDate(CalendarDate(system, year, 1, 1, era = "reiwa"))
                calendar.monthsInYear(year, "reiwa")
                calendar.daysInMonth(year, 1, era = "reiwa")
            }
        }
    }
}
