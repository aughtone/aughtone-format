package io.github.aughtone.calendars

import io.github.aughtone.astronomy.CrescentCriterion
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.UtcOffset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

/** Observation-based Islamic months: resolved from supplied starts, and predicted separately. */
class ObservedIslamicTest {

    // Sha'ban, Ramadan and Shawwal 1445 as observed in Saudi Arabia.
    private val starts = listOf(
        IslamicMonthStart(1445, 8, LocalDate(2024, 2, 11)),
        IslamicMonthStart(1445, 9, LocalDate(2024, 3, 11)),
        IslamicMonthStart(1445, 10, LocalDate(2024, 4, 10)),
    )
    private val calendar = ObservedIslamicCalendar(CalendarSystem.IslamicSaudi, starts)

    @Test
    fun datesWithinTheSuppliedMonthsResolve() {
        assertEquals(CalendarDate(CalendarSystem.IslamicSaudi, 1445, 9, 10), (calendar.fromLocalDate(LocalDate(2024, 3, 20)) as CalendarResult.Resolved).date)
        assertEquals(CalendarDate(CalendarSystem.IslamicSaudi, 1445, 9, 30), (calendar.fromLocalDate(LocalDate(2024, 4, 9)) as CalendarResult.Resolved).date)
        assertEquals(LocalDate(2024, 3, 11), calendar.toLocalDate(CalendarDate(CalendarSystem.IslamicSaudi, 1445, 9, 1)))
        assertEquals(29, calendar.daysInMonth(1445, 8))
        assertEquals(30, calendar.daysInMonth(1445, 9))
    }

    @Test
    fun datesOutsideThemAreUnresolvedNotGuessed() {
        assertIs<CalendarResult.Unresolved>(calendar.fromLocalDate(LocalDate(2024, 1, 1)))
        // The last month's length is unknown: its first 29 days resolve, the 30th does not.
        assertIs<CalendarResult.Resolved>(calendar.fromLocalDate(LocalDate(2024, 5, 8)))
        assertIs<CalendarResult.Unresolved>(calendar.fromLocalDate(LocalDate(2024, 5, 9)))
        assertNull(calendar.daysInMonth(1445, 10))
        assertNull(calendar.toLocalDate(CalendarDate(CalendarSystem.IslamicSaudi, 1446, 1, 1)))
        assertNull(calendar.toLocalDate(CalendarDate(CalendarSystem.IslamicSaudi, 1445, 8, 30)))
    }

    @Test
    fun inconsistentStartsAreRejected() {
        assertFailsWith<IllegalArgumentException> { ObservedIslamicCalendar(CalendarSystem.Hebrew, starts) }
        assertFailsWith<IllegalArgumentException> {
            ObservedIslamicCalendar(CalendarSystem.Islamic, listOf(IslamicMonthStart(1445, 8, LocalDate(2024, 2, 11)), IslamicMonthStart(1445, 9, LocalDate(2024, 3, 20))))
        }
        assertFailsWith<IllegalArgumentException> { ObservedIslamicCalendar(CalendarSystem.Islamic, starts + starts.first()) }
    }

    @Test
    fun aPredictionIsItsOwnTypeAndFollowsTheCrescent() {
        val mecca = 21.4225 to 39.8262
        val arabia = FixedOffsetTimeZone(UtcOffset(hours = 3))
        // Ramadan 1445: new moon 10 March 2024 09:00 UTC. The crescent is not predicted visible to
        // the naked eye at Mecca that evening, and is the next, so Ramadan is predicted for 12 March.
        val nakedEye = ObservedIslamicCalendar.predictMonthStart(1445, 9, mecca.first, mecca.second, arabia)
        assertIs<PredictedMonthStart>(nakedEye)
        assertEquals(LocalDate(2024, 3, 11), nakedEye.sightingEvening)
        assertEquals(LocalDate(2024, 3, 12), nakedEye.firstDay)
        assertEquals(CrescentCriterion.Odeh, nakedEye.crescent.criterion)
        val yallop = ObservedIslamicCalendar.predictMonthStart(1445, 9, mecca.first, mecca.second, arabia, CrescentCriterion.Yallop)
        assertEquals(CrescentCriterion.Yallop, yallop.crescent.criterion)
        assertTrue(yallop.firstDay in LocalDate(2024, 3, 11)..LocalDate(2024, 3, 12))
        assertFailsWith<IllegalArgumentException> { ObservedIslamicCalendar.predictMonthStart(1445, 13, mecca.first, mecca.second, arabia) }
    }
}
