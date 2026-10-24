package io.github.aughtone.calendars.internal

import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import io.github.aughtone.calendars.CalendarSystem
import kotlinx.datetime.LocalDate

/**
 * The Persian (Solar Hijri) calendar, by the 33-year arithmetic rule ICU uses: a year is a leap
 * year when the next one begins 366 days after it. The first six months have 31 days, the next
 * five 30, and Esfand 29, or 30 in a leap year.
 */
internal object PersianCalendar : ArithmeticCalendar() {
    override val system = CalendarSystem.Persian

    /** The fixed day of 1 Farvardin of [year]. */
    private fun newYear(year: Long): Long = 226895 + 365 * (year - 1) + floorDiv(8 * year + 21, 33)

    private fun isLeap(year: Long): Boolean = newYear(year + 1) - newYear(year) == 366L

    override fun fromFixed(fixed: Long): CalendarResult {
        val iso = localDateOf(fixed) ?: return CalendarResult.Unresolved("outside the range of a date")
        // Nowruz falls on 20 or 21 March, so the Persian year is the ISO year less 621 from then on.
        var year = iso.year - 621L
        if (fixed < newYear(year)) year--
        val dayOfYear = (fixed - newYear(year)).toInt()
        val month = if (dayOfYear < 186) dayOfYear / 31 + 1 else (dayOfYear - 186) / 30 + 7
        val day = if (dayOfYear < 186) dayOfYear % 31 + 1 else (dayOfYear - 186) % 30 + 1
        return resolved(year, month, day)
    }

    override fun toFixed(date: CalendarDate): Long {
        val before = if (date.month <= 7) 31 * (date.month - 1) else 186 + 30 * (date.month - 7)
        return newYear(date.year.toLong()) + before + date.day - 1
    }

    override fun monthsInYear(year: Int, era: String?): Int = 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? = when {
        isLeapMonth || month !in 1..12 -> null
        month <= 6 -> 31
        month <= 11 -> 30
        else -> if (isLeap(year.toLong())) 30 else 29
    }
}

/**
 * A calendar of twelve 30-day months and a thirteenth of five days, six in every fourth year,
 * counted from [epoch] (Reingold and Dershowitz): the Coptic and Ethiopic calendars. The Ethiopic
 * Amete Alem calendar counts the same years [yearOffset] later.
 */
internal abstract class CopticFamilyCalendar(private val epoch: Long, private val yearOffset: Long = 0) : ArithmeticCalendar() {

    private fun isLeap(year: Long): Boolean = floorMod(year, 4) == 3L

    private fun fixedOf(year: Long, month: Int, day: Int): Long =
        epoch - 1 + 365 * (year - 1) + floorDiv(year, 4) + 30L * (month - 1) + day

    override fun fromFixed(fixed: Long): CalendarResult {
        val year = floorDiv(4 * (fixed - epoch) + 1463, 1461)
        val month = (floorDiv(fixed - fixedOf(year, 1, 1), 30) + 1).toInt()
        val day = (fixed + 1 - fixedOf(year, month, 1)).toInt()
        return resolved(year + yearOffset, month, day)
    }

    override fun toFixed(date: CalendarDate): Long = fixedOf(date.year - yearOffset, date.month, date.day)

    override fun monthsInYear(year: Int, era: String?): Int = 13

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? = when {
        isLeapMonth || month !in 1..13 -> null
        month < 13 -> 30
        else -> if (isLeap(year - yearOffset)) 6 else 5
    }
}

/** The Coptic calendar, from 29 August 284 (Julian). */
internal object CopticCalendar : CopticFamilyCalendar(103605) {
    override val system = CalendarSystem.Coptic
}

/** The Ethiopic calendar of the Era of Mercy, from 29 August 8 (Julian). */
internal object EthiopicCalendar : CopticFamilyCalendar(2796) {
    override val system = CalendarSystem.Ethiopic
}

/** The Ethiopic calendar of the Era of the World: the Era of Mercy's years plus 5500. */
internal object EthiopicAmeteAlemCalendar : CopticFamilyCalendar(2796, 5500) {
    override val system = CalendarSystem.EthiopicAmeteAlem
}

/**
 * The Indian national (Saka) calendar: the year begins on 22 March, or 21 March in a Gregorian
 * leap year, and the Saka year is the Gregorian year less 78. Chaitra has 30 days (31 in a leap
 * year), the next five months 31, and the last six 30.
 */
internal object IndianCalendar : ArithmeticCalendar() {
    override val system = CalendarSystem.Indian

    private fun newYear(gregorianYear: Long): Long =
        LocalDate(gregorianYear.toInt(), 3, if (isGregorianLeap(gregorianYear)) 21 else 22).fixed()

    private fun monthLength(sakaYear: Long, month: Int): Int = when (month) {
        1 -> if (isGregorianLeap(sakaYear + 78)) 31 else 30
        in 2..6 -> 31
        else -> 30
    }

    override fun fromFixed(fixed: Long): CalendarResult {
        val iso = localDateOf(fixed) ?: return CalendarResult.Unresolved("outside the range of a date")
        var gregorianYear = iso.year.toLong()
        if (fixed < newYear(gregorianYear)) gregorianYear--
        val year = gregorianYear - 78
        var day = fixed - newYear(gregorianYear)
        for (month in 1..12) {
            val length = monthLength(year, month)
            if (day < length) return resolved(year, month, (day + 1).toInt())
            day -= length
        }
        return CalendarResult.Unresolved("no Indian month holds the date")
    }

    override fun toFixed(date: CalendarDate): Long {
        val year = date.year.toLong()
        var fixed = newYear(year + 78)
        for (month in 1 until date.month) fixed += monthLength(year, month)
        return fixed + date.day - 1
    }

    override fun toLocalDate(date: CalendarDate): LocalDate? =
        if (date.year.toLong() + 78 in -999_999L..999_999L) super.toLocalDate(date) else null

    override fun monthsInYear(year: Int, era: String?): Int = 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? =
        if (isLeapMonth || month !in 1..12) null else monthLength(year.toLong(), month)
}
