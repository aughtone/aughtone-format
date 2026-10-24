package io.github.aughtone.calendars.internal

import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import io.github.aughtone.calendars.CalendarSystem

/**
 * The Hebrew calendar, by the molad and the postponement rules (Reingold and Dershowitz).
 *
 * The year begins with Tishri (month 1). A leap year, seven in every nineteen, inserts Adar I
 * after Shevat (5) as the leap month `5L`; Adar (6) is then called Adar II. Heshvan and Kislev
 * vary so that the year's length keeps the new year off the forbidden weekdays.
 */
internal object HebrewCalendar : ArithmeticCalendar() {
    override val system = CalendarSystem.Hebrew

    /** The fixed day of 1 Tishri AM 1 (7 October 3761 BCE, Julian), less one. */
    private const val EPOCH: Long = -1373427

    private fun isLeap(year: Long): Boolean = floorMod(7 * year + 1, 19) < 7

    /** Days from the epoch to the molad-determined start of [year], before the year-length delay. */
    private fun elapsedDays(year: Long): Long {
        val monthsElapsed = floorDiv(235 * year - 234, 19)
        val partsElapsed = 12084 + 13753 * monthsElapsed
        val days = 29 * monthsElapsed + floorDiv(partsElapsed, 25920)
        return if (floorMod(3 * (days + 1), 7) < 3) days + 1 else days
    }

    /** The extra delay that keeps a year from being too long or too short. */
    private fun yearLengthDelay(year: Long): Long {
        val previous = elapsedDays(year - 1)
        val present = elapsedDays(year)
        val next = elapsedDays(year + 1)
        return when {
            next - present == 356L -> 2
            present - previous == 382L -> 1
            else -> 0
        }
    }

    /** The fixed day of 1 Tishri of [year]. */
    private fun newYear(year: Long): Long = EPOCH + elapsedDays(year) + yearLengthDelay(year)

    private fun yearLength(year: Long): Long = newYear(year + 1) - newYear(year)

    /** The year's months in order, each as month number, leap flag and length. */
    private fun months(year: Long): List<Triple<Int, Boolean, Int>> {
        val length = yearLength(year)
        val heshvan = if (floorMod(length, 10) == 5L) 30 else 29
        val kislev = if (floorMod(length, 10) == 3L) 29 else 30
        return buildList {
            add(Triple(1, false, 30))
            add(Triple(2, false, heshvan))
            add(Triple(3, false, kislev))
            add(Triple(4, false, 29))
            add(Triple(5, false, 30))
            if (isLeap(year)) add(Triple(5, true, 30))
            add(Triple(6, false, 29))
            add(Triple(7, false, 30))
            add(Triple(8, false, 29))
            add(Triple(9, false, 30))
            add(Triple(10, false, 29))
            add(Triple(11, false, 30))
            add(Triple(12, false, 29))
        }
    }

    override fun fromFixed(fixed: Long): CalendarResult {
        // The Hebrew year starts in September or October; start from the estimate and correct it.
        var year = floorDiv(fixed - EPOCH, 366) + 1
        while (newYear(year + 1) <= fixed) year++
        while (newYear(year) > fixed) year--
        var day = fixed - newYear(year)
        for ((month, leap, length) in months(year)) {
            if (day < length) return resolved(year, month, (day + 1).toInt(), leap)
            day -= length
        }
        return CalendarResult.Unresolved("no Hebrew month holds the date")
    }

    override fun toFixed(date: CalendarDate): Long {
        val year = date.year.toLong()
        var fixed = newYear(year)
        for ((month, leap, length) in months(year)) {
            if (month == date.month && leap == date.isLeapMonth) return fixed + date.day - 1
            fixed += length
        }
        error("unreachable: the month was checked to exist")
    }

    override fun monthsInYear(year: Int, era: String?): Int = if (isLeap(year.toLong())) 13 else 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? =
        months(year.toLong()).firstOrNull { it.first == month && it.second == isLeapMonth }?.third

    override fun leapMonth(year: Int, era: String?): Int? = if (isLeap(year.toLong())) 5 else null
}
