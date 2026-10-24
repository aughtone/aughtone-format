package io.github.aughtone.calendars.internal

import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import io.github.aughtone.calendars.CalendarSystem
import io.github.aughtone.calendars.data.UMM_AL_QURA_FIRST_YEAR
import io.github.aughtone.calendars.data.UMM_AL_QURA_LAST_YEAR
import io.github.aughtone.calendars.data.ummAlQuraMonthLengths
import io.github.aughtone.calendars.data.ummAlQuraStart
import kotlinx.datetime.LocalDate

/**
 * A tabular Islamic calendar from [epoch]: months alternate 30 and 29 days, and in eleven years
 * of every thirty the twelfth month has 30 (Reingold and Dershowitz).
 */
internal abstract class TabularIslamicCalendar(private val epoch: Long) : ArithmeticCalendar() {

    private fun isLeap(year: Long): Boolean = floorMod(14 + 11 * year, 30) < 11

    internal fun fixedOf(year: Long, month: Int, day: Int): Long =
        epoch - 1 + (year - 1) * 354 + floorDiv(3 + 11 * year, 30) + 29L * (month - 1) + floorDiv(month.toLong(), 2) + day

    override fun fromFixed(fixed: Long): CalendarResult {
        val year = floorDiv(30 * (fixed - epoch) + 10646, 10631)
        val priorDays = fixed - fixedOf(year, 1, 1)
        val month = floorDiv(11 * priorDays + 330, 325).toInt()
        val day = (fixed - fixedOf(year, month, 1) + 1).toInt()
        return resolved(year, month, day)
    }

    override fun toFixed(date: CalendarDate): Long = fixedOf(date.year.toLong(), date.month, date.day)

    override fun monthsInYear(year: Int, era: String?): Int = 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? = when {
        isLeapMonth || month !in 1..12 -> null
        month == 12 -> if (isLeap(year.toLong())) 30 else 29
        month % 2 == 1 -> 30
        else -> 29
    }
}

/** The tabular Islamic calendar from the Friday epoch, 16 July 622 (Julian). */
internal object IslamicCivilCalendar : TabularIslamicCalendar(227015) {
    override val system = CalendarSystem.IslamicCivil

    /** The civil date on fixed day [fixed], for the Umm al-Qura calendar outside its table. */
    internal fun civilFromFixed(fixed: Long): CalendarResult = fromFixed(fixed)
}

/** The tabular Islamic calendar from the Thursday epoch, 15 July 622 (Julian). */
internal object IslamicTabularCalendar : TabularIslamicCalendar(227014) {
    override val system = CalendarSystem.IslamicTabular
}

/**
 * The Umm al-Qura calendar, from its published table for AH 1300 to 1600 (1882 to 2174), and the
 * civil tabular calendar outside it, as ICU computes it. The two agree at both ends of the table.
 */
internal object UmmAlQuraCalendar : ArithmeticCalendar() {
    override val system = CalendarSystem.IslamicUmmAlQura

    private fun inTable(year: Long): Boolean = year in UMM_AL_QURA_FIRST_YEAR..UMM_AL_QURA_LAST_YEAR

    private fun monthLength(year: Long, month: Int): Int =
        if ((ummAlQuraMonthLengths[(year - UMM_AL_QURA_FIRST_YEAR).toInt()] shr (month - 1)) and 1 == 1) 30 else 29

    /** The fixed day each table year begins, and one past the end of the last. */
    private val yearStarts: LongArray by lazy {
        val starts = LongArray(UMM_AL_QURA_LAST_YEAR - UMM_AL_QURA_FIRST_YEAR + 2)
        starts[0] = LocalDate(ummAlQuraStart[0], ummAlQuraStart[1], ummAlQuraStart[2]).fixed()
        for (index in 1 until starts.size) {
            val year = (UMM_AL_QURA_FIRST_YEAR + index - 1).toLong()
            starts[index] = starts[index - 1] + (1..12).sumOf { monthLength(year, it) }
        }
        starts
    }

    override fun fromFixed(fixed: Long): CalendarResult {
        val starts = yearStarts
        if (fixed < starts.first() || fixed >= starts.last()) {
            val civil = IslamicCivilCalendar.civilFromFixed(fixed)
            return if (civil is CalendarResult.Resolved) CalendarResult.Resolved(civil.date.copy(calendar = system)) else civil
        }
        var index = starts.indexOfLast { it <= fixed }
        if (index == starts.lastIndex) index--
        val year = (UMM_AL_QURA_FIRST_YEAR + index).toLong()
        var day = fixed - starts[index]
        for (month in 1..12) {
            val length = monthLength(year, month)
            if (day < length) return resolved(year, month, (day + 1).toInt())
            day -= length
        }
        return CalendarResult.Unresolved("no Umm al-Qura month holds the date")
    }

    override fun toFixed(date: CalendarDate): Long {
        val year = date.year.toLong()
        if (!inTable(year)) return IslamicCivilCalendar.fixedOf(year, date.month, date.day)
        var fixed = yearStarts[(year - UMM_AL_QURA_FIRST_YEAR).toInt()]
        for (month in 1 until date.month) fixed += monthLength(year, month)
        return fixed + date.day - 1
    }

    override fun monthsInYear(year: Int, era: String?): Int = 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? = when {
        isLeapMonth || month !in 1..12 -> null
        inTable(year.toLong()) -> monthLength(year.toLong(), month)
        else -> IslamicCivilCalendar.daysInMonth(year, month)
    }
}
