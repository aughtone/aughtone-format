package io.github.aughtone.calendars.internal

import io.github.aughtone.astronomy.Moon
import io.github.aughtone.astronomy.Sun
import io.github.aughtone.calendars.Calendar
import io.github.aughtone.calendars.CalendarDate
import io.github.aughtone.calendars.CalendarResult
import io.github.aughtone.calendars.CalendarSystem
import io.github.aughtone.calendars.data.CHINESE_TABLE_FIRST_YEAR
import io.github.aughtone.calendars.data.CHINESE_TABLE_LAST_YEAR
import io.github.aughtone.calendars.data.chineseYears
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.concurrent.Volatile
import kotlin.time.Duration.Companion.days
import kotlin.time.Instant

/**
 * One year of a Chinese-style lunisolar calendar: when it begins, and its months in order, each
 * with its number, whether it is the leap month, and its length.
 */
internal class LunisolarYear(
    val year: Int,
    val newYear: LocalDate,
    val months: List<Int>,
    val leapIndex: Int,
    val lengths: IntArray,
) {
    /** The month the leap month follows, or `null`. */
    val leapMonth: Int? get() = if (leapIndex < 0) null else months[leapIndex]

    /** The position of [month] (its leap month when [isLeap]) in [months], or -1. */
    fun indexOf(month: Int, isLeap: Boolean): Int =
        if (isLeap) leapIndex.takeIf { it >= 0 && months[it] == month } ?: -1
        else months.indices.firstOrNull { months[it] == month && it != leapIndex } ?: -1
}

/**
 * A Chinese-style lunisolar calendar: months begin on the day of the new moon where the calendar
 * is reckoned, and a year with thirteen months takes as its leap month the first with no major
 * solar term. Years in [tableYears] come from [table]; the others are computed from the new moons
 * and solar terms in `:astronomy`, for the years [COMPUTED_FIRST] to [COMPUTED_LAST]. Years are
 * the ISO year in which the year begins.
 *
 * Neither calendar uses ICU's data, which the platforms' own calendars are built on: ICU starts a
 * month on the wrong day when a new moon falls within minutes of midnight (see ADR-0007).
 */
internal abstract class LunisolarCalendar(private val table: IntArray?, private val tableYears: IntRange) : Calendar {

    /** The UTC offset the calendar is reckoned at in [year]. */
    protected abstract fun offset(year: Int): UtcOffset

    @Volatile private var computed = emptyMap<Int, LunisolarYear>()

    /** The year beginning in ISO year [year], or `null` outside the supported range. */
    internal fun yearOf(year: Int): LunisolarYear? = when (year) {
        in tableYears -> tableYear(year)
        in COMPUTED_FIRST..COMPUTED_LAST -> computed[year] ?: computeYear(year).also { computed = (if (computed.size > 64) emptyMap() else computed) + (year to it) }
        else -> null
    }

    /** The table's year beginning in ISO year [year]. */
    internal fun tableYear(year: Int): LunisolarYear {
        val word = checkNotNull(table)[year - tableYears.first]
        val leap = (word shr 13) and 0xF
        val months = (1..12).flatMap { if (it == leap) listOf(it, it) else listOf(it) }
        val lengths = IntArray(months.size) { if ((word shr it) and 1 == 1) 30 else 29 }
        val newYear = LocalDate(year, 1, 1).plus(DatePeriod(days = (word shr 17) and 0x3F))
        return LunisolarYear(year, newYear, months, if (leap == 0) -1 else leap, lengths)
    }

    /** The year beginning in ISO year [year], computed from new moons and solar terms. */
    internal fun computeYear(year: Int): LunisolarYear {
        // Months 1 to 10 come from the solar year running from the December solstice of the year
        // before; months 11 and 12 (and a leap month among them) from the next solar year.
        val first = suiMonths(year - 1)
        val second = suiMonths(year)
        val startOfYear = first.indexOfFirst { it.month == 1 && !it.isLeap }
        val endOfYear = second.indexOfFirst { it.month == 1 && !it.isLeap }
        val nextNewYear = second[endOfYear].start
        val months = first.drop(startOfYear) + second.take(endOfYear)
        val starts = months.map { it.start } + nextNewYear
        return LunisolarYear(
            year = year,
            newYear = months.first().start,
            months = months.map { it.month },
            leapIndex = months.indexOfFirst { it.isLeap },
            lengths = IntArray(months.size) { (starts[it + 1].toEpochDays() - starts[it].toEpochDays()).toInt() },
        )
    }

    private class Month(val start: LocalDate, val month: Int, val isLeap: Boolean)

    /** The months of the solar year from the December solstice of [isoYear] to the next one. */
    private fun suiMonths(isoYear: Int): List<Month> {
        val solstice = localDate(Sun.solarTerms(isoYear).single { it.longitude == 270 }.instant, isoYear)
        val nextSolstice = localDate(Sun.solarTerms(isoYear + 1).single { it.longitude == 270 }.instant, isoYear + 1)
        val starts = newMoonStarts(solstice.plus(DatePeriod(days = -30)), nextSolstice.plus(DatePeriod(days = 1)), isoYear)
        val first = starts.last { it <= solstice }
        val last = starts.last { it <= nextSolstice }
        val sui = starts.filter { it >= first && it < last } + last
        val majorTerms = (isoYear..isoYear + 1).flatMap { y -> Sun.solarTerms(y).filter { it.longitude % 30 == 0 }.map { localDate(it.instant, y) } }
        val months = mutableListOf<Month>()
        val leapSui = sui.size - 1 == 13
        var leapTaken = false
        var number = 11
        for (i in 0 until sui.size - 1) {
            val hasMajorTerm = majorTerms.any { it >= sui[i] && it < sui[i + 1] }
            if (leapSui && !leapTaken && i > 0 && !hasMajorTerm) {
                months += Month(sui[i], months.last().month, isLeap = true)
                leapTaken = true
                continue
            }
            months += Month(sui[i], number, isLeap = false)
            number = number % 12 + 1
        }
        return months
    }

    /** The local dates of the new moons from [from] to [to]. */
    private fun newMoonStarts(from: LocalDate, to: LocalDate, isoYear: Int): List<LocalDate> {
        val start = from.atStartOfDayIn(TimeZone.UTC) - 1.days
        val end = to.atStartOfDayIn(TimeZone.UTC) + 1.days
        return Moon.newMoonsBetween(start, end).map { localDate(it, isoYear) }.filter { it >= from && it <= to }
    }

    /** The date of [instant] where the calendar is reckoned in [year]. */
    private fun localDate(instant: Instant, year: Int): LocalDate = instant.toLocalDateTime(FixedOffsetTimeZone(offset(year))).date

    override fun fromLocalDate(date: LocalDate): CalendarResult {
        val candidate = yearOf(date.year) ?: return outOfRange()
        val year = if (date < candidate.newYear) yearOf(date.year - 1) ?: return outOfRange() else candidate
        var day = (date.toEpochDays() - year.newYear.toEpochDays()).toInt()
        for (i in year.months.indices) {
            if (day < year.lengths[i]) return CalendarResult.Resolved(CalendarDate(system, year.year, year.months[i], day + 1, i == year.leapIndex))
            day -= year.lengths[i]
        }
        return outOfRange()
    }

    override fun toLocalDate(date: CalendarDate): LocalDate? {
        if (date.calendar != system) return null
        val year = yearOf(date.year) ?: return null
        val index = year.indexOf(date.month, date.isLeapMonth)
        if (index < 0 || date.day !in 1..year.lengths[index]) return null
        val days = (0 until index).sumOf { year.lengths[it] } + date.day - 1
        return year.newYear.plus(DatePeriod(days = days))
    }

    override fun monthsInYear(year: Int, era: String?): Int = yearOf(year)?.months?.size ?: 12

    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? {
        val y = yearOf(year) ?: return null
        val index = y.indexOf(month, isLeapMonth)
        return if (index < 0) null else y.lengths[index]
    }

    override fun leapMonth(year: Int, era: String?): Int? = yearOf(year)?.leapMonth

    private fun outOfRange() = CalendarResult.Unresolved(
        "the ${system.cldrName} calendar is computed for the years $COMPUTED_FIRST to $COMPUTED_LAST",
    )

    internal companion object {
        /** 1645, when the present rules (true new moons and true solar terms) were adopted. */
        const val COMPUTED_FIRST = 1645
        const val COMPUTED_LAST = 2999
    }
}

/**
 * The Chinese calendar. From 1901 to 2099 its months come from a table generated from the Hong
 * Kong Observatory's published Gregorian–Lunar conversion tables; outside it they are computed,
 * reckoned at Beijing's local mean time (116°25′ E, UTC+7:45:40) before 1929 and at UTC+8 since,
 * as those tables reckon them. The computation agrees with the published tables in every month
 * from 1901 to 2100 but one, April 1906, where the new moon falls within the calculation's
 * accuracy of midnight.
 */
internal object ChineseCalendar : LunisolarCalendar(chineseYears, CHINESE_TABLE_FIRST_YEAR..CHINESE_TABLE_LAST_YEAR) {
    override val system = CalendarSystem.Chinese
    override fun offset(year: Int): UtcOffset = if (year < 1929) UtcOffset(hours = 7, minutes = 45, seconds = 40) else UtcOffset(hours = 8)
}

/**
 * The Korean (Dangi) calendar, computed in every year: no published Korean table is available to
 * generate one from. It is reckoned at Seoul, at UTC+8 before 1912 and UTC+9 since, so its months
 * differ from the Chinese calendar's in a few years.
 */
internal object DangiCalendar : LunisolarCalendar(null, IntRange.EMPTY) {
    override val system = CalendarSystem.Dangi
    override fun offset(year: Int): UtcOffset = if (year < 1912) UtcOffset(hours = 8) else UtcOffset(hours = 9)
}
