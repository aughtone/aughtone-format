package io.github.aughtone.calendars.internal

import io.github.aughtone.calendars.Calendar
import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import kotlinx.datetime.LocalDate

/*
 * The calendars are computed on fixed day numbers ("rata die"): day 1 is 1 January of year 1 in
 * the proleptic Gregorian calendar, as in Reingold and Dershowitz's Calendrical Calculations,
 * whose algorithms most of these follow. Everything is integer arithmetic, so every platform
 * gives the same answer.
 */

/** The fixed day number of 1970-01-01, where [LocalDate.toEpochDays] counts from. */
private const val EPOCH_DAY_ZERO: Long = 719163

/** This date's fixed day number. */
internal fun LocalDate.fixed(): Long = toEpochDays() + EPOCH_DAY_ZERO

/** The ISO date of fixed day [fixed], or `null` outside the range [LocalDate] can hold. */
internal fun localDateOf(fixed: Long): LocalDate? = runCatching { LocalDate.fromEpochDays(fixed - EPOCH_DAY_ZERO) }.getOrNull()

/** Floor division, for the negative years and days these algorithms reach. */
internal fun floorDiv(a: Long, b: Long): Long = a.floorDiv(b)

/** The non-negative remainder of [a] divided by [b]. */
internal fun floorMod(a: Long, b: Long): Long = a.mod(b)

/** Whether [year] is a leap year in the proleptic Gregorian calendar. */
internal fun isGregorianLeap(year: Long): Boolean = floorMod(year, 4) == 0L && (floorMod(year, 100) != 0L || floorMod(year, 400) == 0L)

/** The number of days in [month] of [year] in the proleptic Gregorian calendar. */
internal fun gregorianDaysInMonth(year: Long, month: Int): Int = when (month) {
    2 -> if (isGregorianLeap(year)) 29 else 28
    4, 6, 9, 11 -> 30
    else -> 31
}

/**
 * A calendar computed from fixed day numbers. A subclass says how to turn a fixed day into a
 * date and back, and the shape of its years and months; this checks a date exists before
 * converting it, so a subclass may assume a valid date.
 */
internal abstract class ArithmeticCalendar : Calendar {

    /** The date in this calendar on fixed day [fixed], or the reason there is none. */
    protected abstract fun fromFixed(fixed: Long): CalendarResult

    /** The fixed day of [date], which exists in this calendar. */
    protected abstract fun toFixed(date: CalendarDate): Long

    override fun fromLocalDate(date: LocalDate): CalendarResult = fromFixed(date.fixed())

    override fun toLocalDate(date: CalendarDate): LocalDate? {
        if (date.calendar != system) return null
        val length = daysInMonth(date.year, date.month, date.isLeapMonth, date.era) ?: return null
        if (date.day !in 1..length) return null
        return localDateOf(toFixed(date))
    }

    override fun leapMonth(year: Int, era: String?): Int? = null

    /** A resolved date in this calendar, or unresolved when [year] does not fit an [Int]. */
    protected fun resolved(year: Long, month: Int, day: Int, isLeapMonth: Boolean = false, era: String? = null): CalendarResult =
        if (year in Int.MIN_VALUE..Int.MAX_VALUE) {
            CalendarResult.Resolved(CalendarDate(system, year.toInt(), month, day, isLeapMonth, era))
        } else {
            CalendarResult.Unresolved("the year is outside the range a calendar date can hold")
        }
}
