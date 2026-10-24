package io.github.aughtone.calendars

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Every day from 1900 to 2100 converts to every computed calendar and back. Run on the JVM only:
 * the common tests check a sample on every platform, and this is too long for a browser runner.
 */
class EveryDayRoundTripTest {

    @Test
    fun everyDayRoundTripsInEveryComputedCalendar() {
        val failures = mutableListOf<String>()
        for (system in CalendarSystem.entries) {
            val calendar = system.calendar() ?: continue
            var date = LocalDate(1900, 1, 1)
            var previous: CalendarDate? = null
            while (date <= LocalDate(2100, 12, 31)) {
                val converted = (calendar.fromLocalDate(date) as? CalendarResult.Resolved)?.date
                if (converted == null || calendar.toLocalDate(converted) != date) failures += "${system.cldrName} $date → $converted"
                // Consecutive days are consecutive in the calendar: the next day, or day 1 of a new month.
                if (converted != null && previous != null && converted.day != previous.day + 1 && converted.day != 1) failures += "${system.cldrName} $date: $previous then $converted"
                previous = converted
                date = date.plus(DatePeriod(days = 1))
                if (failures.size > 20) break
            }
        }
        assertTrue(failures.isEmpty(), failures.joinToString("\n"))
    }
}
