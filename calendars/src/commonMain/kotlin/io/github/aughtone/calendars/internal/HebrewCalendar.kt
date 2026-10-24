package io.github.aughtone.calendars.internal

import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import io.github.aughtone.calendars.CalendarSystem
import kotlin.concurrent.Volatile

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

    /** The fixed days of 1 Tishri of [year] ([start]) and of the year after ([next]). */
    private class YearBounds(val year: Long, val start: Long, val next: Long)

    /**
     * The last year's bounds. Converting a date asks for its year's bounds several times, and a run
     * of nearby dates asks for the same year again, so one entry saves most of the molad arithmetic.
     * Thread-safe by replacement: the value is immutable.
     */
    @Volatile private var lastBounds: YearBounds? = null

    private fun bounds(year: Long): YearBounds {
        lastBounds?.let { if (it.year == year) return it }
        return YearBounds(year, newYear(year), newYear(year + 1)).also { lastBounds = it }
    }

    private fun yearLength(year: Long): Long = bounds(year).let { it.next - it.start }

    /** The year's months in order, each as month number, leap flag and length; [length] is the year's. */
    private fun months(year: Long, length: Long = yearLength(year)): List<Triple<Int, Boolean, Int>> {
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
        // The Hebrew year starts in September or October; estimate it from the mean year of
        // 35975351/98496 days, which is at most a year out, and correct it.
        var year = bounds(floorDiv(98496 * (fixed - EPOCH), 35975351) + 1)
        while (year.next <= fixed) year = bounds(year.year + 1)
        while (year.start > fixed) year = bounds(year.year - 1)
        var day = fixed - year.start
        for ((month, leap, length) in months(year.year, year.next - year.start)) {
            if (day < length) return resolved(year.year, month, (day + 1).toInt(), leap)
            day -= length
        }
        return CalendarResult.Unresolved("no Hebrew month holds the date")
    }

    override fun toFixed(date: CalendarDate): Long {
        val year = bounds(date.year.toLong())
        var fixed = year.start
        for ((month, leap, length) in months(year.year, year.next - year.start)) {
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
