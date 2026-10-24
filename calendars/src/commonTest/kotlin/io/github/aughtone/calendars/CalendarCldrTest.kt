package io.github.aughtone.calendars

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Every calendar against ICU, through the generated [calendarCldrFixture]: each case is checked
 * from the ISO date to the calendar date, and back. One test per calendar, so none runs long
 * enough to meet a browser runner's timeout.
 */
class CalendarCldrTest {

    private fun check(system: CalendarSystem) {
        val calendar = system.calendar() ?: error("no calendar for $system")
        val cases = calendarCldrFixture.getValue(system.cldrName).trim().lines()
        assertTrue(cases.size > 1000, "${system.cldrName}: only ${cases.size} cases")
        val mismatches = mutableListOf<String>()
        for (line in cases) {
            val fields = line.split('|')
            val (iso, year, month, leap, day) = fields
            val expected = CalendarDate(system, year.toInt(), month.toInt(), day.toInt(), leap == "1", fields[5].ifEmpty { null })
            val date = LocalDate.parse(iso)
            val actual = (calendar.fromLocalDate(date) as? CalendarResult.Resolved)?.date
            if (actual != expected) mismatches += "$iso: ICU $expected, library $actual"
            val back = calendar.toLocalDate(expected)
            if (back != date) mismatches += "$expected: ICU $iso, library $back"
        }
        assertTrue(mismatches.isEmpty(), "${system.cldrName}: ${mismatches.size} of ${cases.size}:\n" + mismatches.take(20).joinToString("\n"))
    }

    @Test fun buddhist() = check(CalendarSystem.Buddhist)
    @Test fun japanese() = check(CalendarSystem.Japanese)
    @Test fun roc() = check(CalendarSystem.Roc)
    @Test fun hebrew() = check(CalendarSystem.Hebrew)
    @Test fun islamicCivil() = check(CalendarSystem.IslamicCivil)
    @Test fun islamicTabular() = check(CalendarSystem.IslamicTabular)
    @Test fun islamicUmmAlQura() = check(CalendarSystem.IslamicUmmAlQura)
    @Test fun persian() = check(CalendarSystem.Persian)
    @Test fun coptic() = check(CalendarSystem.Coptic)
    @Test fun ethiopic() = check(CalendarSystem.Ethiopic)
    @Test fun ethiopicAmeteAlem() = check(CalendarSystem.EthiopicAmeteAlem)
    @Test fun indian() = check(CalendarSystem.Indian)

    /**
     * Every computed calendar is checked against ICU here, except the Chinese and Korean ones:
     * ICU starts some of their months on the wrong day (ADR-0007), so they are checked against
     * the Hong Kong Observatory's published tables and the true new moons in `LunisolarTest`.
     */
    @Test
    fun theFixtureCoversEveryComputedCalendar() {
        val computed = CalendarSystem.entries.filter { it.calendar() != null }.map { it.cldrName }.toSet()
        assertEquals(computed - setOf("chinese", "dangi"), calendarCldrFixture.keys)
    }
}
