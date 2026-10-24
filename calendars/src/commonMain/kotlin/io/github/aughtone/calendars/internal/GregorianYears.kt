package io.github.aughtone.calendars.internal

import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import io.github.aughtone.calendars.CalendarSystem
import io.github.aughtone.calendars.data.japaneseEras
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

/**
 * A calendar whose months and days are the Gregorian's, with its years counted [offset] years
 * after the ISO year.
 */
internal abstract class OffsetGregorianCalendar(private val offset: Long) : ArithmeticCalendar() {
    override fun fromFixed(fixed: Long): CalendarResult {
        val date = localDateOf(fixed) ?: return CalendarResult.Unresolved("outside the range of a date")
        return resolved(date.year + offset, date.month.number, date.day)
    }

    override fun toFixed(date: CalendarDate): Long = LocalDate((date.year - offset).toInt(), date.month, date.day).fixed()

    override fun toLocalDate(date: CalendarDate): LocalDate? {
        val isoYear = date.year - offset
        if (isoYear !in -999_999L..999_999L) return null
        return super.toLocalDate(date)
    }

    override fun monthsInYear(year: Int, era: String?): Int = 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? =
        if (month in 1..12 && !isLeapMonth) gregorianDaysInMonth(year - offset, month) else null
}

/** The Thai solar calendar: the Buddhist Era is the ISO year plus 543. */
internal object BuddhistCalendar : OffsetGregorianCalendar(543) {
    override val system = CalendarSystem.Buddhist
}

/** The Republic of China calendar: Minguo 1 is 1912, and 1911 is year 0. */
internal object RocCalendar : OffsetGregorianCalendar(-1911) {
    override val system = CalendarSystem.Roc
}

/**
 * The Japanese calendar: Gregorian months and days, with years counted from 1 within each era.
 *
 * Era start dates are CLDR's. Those before 1873, when Japan adopted the Gregorian calendar, are
 * written as a year with the month and day of the lunisolar calendar then in use (one begins on
 * "1624-2-30"), so they are not dates in any solar calendar. As ICU does, a date is placed in an
 * era by comparing its year, month and day with the era's as written, here against the
 * proleptic Gregorian date. Before 1873 that is a convention, not the dates of the time.
 */
internal object JapaneseCalendar : ArithmeticCalendar() {
    override val system = CalendarSystem.Japanese

    /** An era: its [code], the year it begins, and its start as one comparable number. */
    private class Era(val code: String, val startYear: Int, val start: Long)

    /** A year, month and day as one number that orders as the dates do, valid or not. */
    private fun key(year: Long, month: Int, day: Int): Long = year * 10_000 + month * 100 + day

    private val eras: List<Era> by lazy {
        japaneseEras.map { Era(it.code, it.startYear, key(it.startYear.toLong(), it.startMonth, it.startDay)) }
    }

    private fun eraIndex(code: String?): Int = if (code == null) -1 else eras.indexOfFirst { it.code == code }

    /** The era holding the date [key], or -1 before the first. */
    private fun eraOf(key: Long): Int = eras.indexOfLast { it.start <= key }

    override fun fromFixed(fixed: Long): CalendarResult {
        val date = localDateOf(fixed) ?: return CalendarResult.Unresolved("outside the range of a date")
        val index = eraOf(key(date.year.toLong(), date.month.number, date.day))
        if (index < 0) return CalendarResult.Unresolved("the date is before the first Japanese era")
        val era = eras[index]
        return resolved((date.year - era.startYear + 1).toLong(), date.month.number, date.day, era = era.code)
    }

    override fun toFixed(date: CalendarDate): Long {
        val era = eras[eraIndex(date.era)]
        return LocalDate(era.startYear + date.year - 1, date.month, date.day).fixed()
    }

    override fun toLocalDate(date: CalendarDate): LocalDate? {
        val index = eraIndex(date.era)
        if (date.calendar != system || index < 0 || date.year < 1 || date.year > 999_999) return null
        val iso = super.toLocalDate(date) ?: return null
        // The date must fall within its era: Heisei 1 began on 8 January 1989.
        return if (eraOf(key(iso.year.toLong(), iso.month.number, iso.day)) == index) iso else null
    }

    override fun monthsInYear(year: Int, era: String?): Int = 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? {
        val index = eraIndex(era)
        if (index < 0 || month !in 1..12 || isLeapMonth || year < 1 || year > 999_999) return null
        val isoYear = (eras[index].startYear + year - 1).toLong()
        val length = gregorianDaysInMonth(isoYear, month)
        // A month exists in an era when any of its days does.
        val next = eras.getOrNull(index + 1)?.start ?: Long.MAX_VALUE
        return if (key(isoYear, month, length) >= eras[index].start && key(isoYear, month, 1) < next) length else null
    }
}
