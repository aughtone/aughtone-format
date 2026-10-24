package io.github.aughtone.calendars

import io.github.aughtone.astronomy.Crescent
import io.github.aughtone.astronomy.CrescentCriterion
import io.github.aughtone.astronomy.CrescentPrediction
import io.github.aughtone.astronomy.Moon
import io.github.aughtone.calendars.internal.IslamicCivilCalendar
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

/**
 * The observed first day of one month of an observation-based Islamic calendar: the day after
 * the evening the new crescent was sighted, or after the 30th day of the month before.
 *
 * @property year The Islamic year (AH).
 * @property month The month, 1 (Muharram) to 12 (Dhu al-Hijjah).
 * @property firstDay The ISO date the month began on.
 */
data class IslamicMonthStart(val year: Int, val month: Int, val firstDay: LocalDate)

/**
 * A predicted first day of an Islamic month, from a crescent-visibility criterion. Never an
 * observation: it is a separate type from [IslamicMonthStart] so the two cannot be mixed.
 *
 * @property year The Islamic year (AH).
 * @property month The month, 1 to 12.
 * @property firstDay The ISO date the month is predicted to begin on.
 * @property sightingEvening The evening the crescent is predicted to be first seen, or `null`
 *   when it is not predicted visible in the evenings searched, and the month is taken to begin
 *   after the last of them, as a month of 30 days would.
 * @property crescent The prediction for [sightingEvening], or for the last evening searched.
 */
data class PredictedMonthStart(
    val year: Int,
    val month: Int,
    val firstDay: LocalDate,
    val sightingEvening: LocalDate?,
    val crescent: CrescentPrediction,
)

/**
 * An observation-based Islamic calendar ([CalendarSystem.Islamic] or
 * [CalendarSystem.IslamicSaudi]), resolved from the month starts a caller supplies. These months
 * begin when the crescent is sighted, so they cannot be calculated: a date is resolved only
 * within the months supplied, and is [CalendarResult.Unresolved] outside them. The last day of a
 * month is known only when the next month's start is supplied, since a month has 29 or 30 days.
 *
 * ```kotlin
 * val calendar = ObservedIslamicCalendar(CalendarSystem.IslamicSaudi, listOf(
 *     IslamicMonthStart(1445, 9, LocalDate(2024, 3, 11)),  // Ramadan
 *     IslamicMonthStart(1445, 10, LocalDate(2024, 4, 10)), // Shawwal
 * ))
 * calendar.fromLocalDate(LocalDate(2024, 3, 20)) // Resolved: 10 Ramadan 1445
 * ```
 *
 * @param system [CalendarSystem.Islamic] or [CalendarSystem.IslamicSaudi].
 * @param monthStarts The observed month starts, in any order.
 * @throws IllegalArgumentException if [system] is not an observation-based Islamic calendar, a
 *   month is supplied twice, a month number is outside 1 to 12, or two consecutive months are
 *   supplied that are not 29 or 30 days apart.
 */
class ObservedIslamicCalendar(
    override val system: CalendarSystem,
    monthStarts: List<IslamicMonthStart>,
) : Calendar {

    private val starts: List<IslamicMonthStart> = monthStarts.sortedBy { it.firstDay }
    private val byMonth: Map<Pair<Int, Int>, IslamicMonthStart> = starts.associateBy { it.year to it.month }

    init {
        require(system == CalendarSystem.Islamic || system == CalendarSystem.IslamicSaudi) {
            "Only the observation-based Islamic calendars take observed month starts, not ${system.cldrName}."
        }
        require(byMonth.size == starts.size) { "A month was supplied more than once." }
        require(starts.all { it.month in 1..12 }) { "A month number was outside 1 to 12." }
        for (start in starts) {
            val next = byMonth[next(start.year, start.month)] ?: continue
            val days = next.firstDay.toEpochDays() - start.firstDay.toEpochDays()
            require(days == 29L || days == 30L) { "${start.year}/${start.month} began ${start.firstDay} and the next month $days days later." }
        }
    }

    private fun next(year: Int, month: Int) = if (month == 12) year + 1 to 1 else year to month + 1

    /** The length of the month that begins with [start], when the next month's start is known. */
    private fun length(start: IslamicMonthStart): Int? =
        byMonth[next(start.year, start.month)]?.let { (it.firstDay.toEpochDays() - start.firstDay.toEpochDays()).toInt() }

    override fun fromLocalDate(date: LocalDate): CalendarResult {
        val start = starts.lastOrNull { it.firstDay <= date }
            ?: return CalendarResult.Unresolved("$date is before the first observed month")
        val day = (date.toEpochDays() - start.firstDay.toEpochDays()).toInt() + 1
        val length = length(start)
        return when {
            length != null && day <= length -> CalendarResult.Resolved(CalendarDate(system, start.year, start.month, day))
            length == null && day <= 29 -> CalendarResult.Resolved(CalendarDate(system, start.year, start.month, day))
            else -> CalendarResult.Unresolved("$date is after the observed months, or in a month whose length is not known")
        }
    }

    override fun toLocalDate(date: CalendarDate): LocalDate? {
        if (date.calendar != system || date.isLeapMonth) return null
        val start = byMonth[date.year to date.month] ?: return null
        val length = length(start) ?: 29
        return if (date.day in 1..length) start.firstDay.plus(DatePeriod(days = date.day - 1)) else null
    }

    override fun monthsInYear(year: Int, era: String?): Int = 12

    /** The observed length of the month, or `null` when its start or the next month's is not supplied. */
    override fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean, era: String?): Int? =
        if (isLeapMonth) null else byMonth[year to month]?.let(::length)

    override fun leapMonth(year: Int, era: String?): Int? = null

    companion object {
        /**
         * A prediction of the first day of Islamic month [month] of [year], as seen from a
         * latitude and longitude in [timeZone]: the day after the first evening, from the new
         * moon's, on which the crescent is predicted visible by [criterion] — to the naked eye,
         * or with optical aid too when [acceptOpticalAid]. When it is predicted visible on none
         * of the three evenings searched, the month is taken to begin after the third.
         *
         * This is a prediction, returned as a [PredictedMonthStart] and never as an
         * [IslamicMonthStart]. The month an authority announces is the month that begins.
         *
         * @throws IllegalArgumentException if [month] is outside 1 to 12, or the latitude or
         *   longitude is out of range.
         */
        fun predictMonthStart(
            year: Int,
            month: Int,
            latitude: Double,
            longitude: Double,
            timeZone: TimeZone,
            criterion: CrescentCriterion = CrescentCriterion.Odeh,
            acceptOpticalAid: Boolean = false,
        ): PredictedMonthStart {
            require(month in 1..12) { "Month must be from 1 to 12, but was $month." }
            // The tabular calendar puts the month within a day or two of its true start; the new
            // moon before it is the one this month follows.
            val approximate = IslamicCivilCalendar.toLocalDate(CalendarDate(CalendarSystem.IslamicCivil, year, month, 1))
                ?: throw IllegalArgumentException("Year $year is outside the range this can predict.")
            val around = approximate.atStartOfDayIn(TimeZone.UTC)
            val newMoon = Moon.newMoonsBetween(around - 5.days, around + 3.days).lastOrNull()
                ?: Moon.newMoonsBetween(around - 20.days, around + 10.days).last()
            val firstEvening = newMoon.toLocalDateTime(timeZone).date
            var last: CrescentPrediction? = null
            for (offset in 0..2) {
                val evening = firstEvening.plus(DatePeriod(days = offset))
                val prediction = Crescent.visibility(evening, latitude, longitude, timeZone, criterion)
                last = prediction
                val visible = prediction.visibility.isNakedEyeVisible || (acceptOpticalAid && prediction.visibility.isVisibleWithOpticalAid)
                if (visible) return PredictedMonthStart(year, month, evening.plus(DatePeriod(days = 1)), evening, prediction)
            }
            return PredictedMonthStart(year, month, firstEvening.plus(DatePeriod(days = 3)), null, last!!)
        }
    }
}
