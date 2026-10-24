package io.github.aughtone.calendars

import io.github.aughtone.calendars.internal.BuddhistCalendar
import io.github.aughtone.calendars.internal.ChineseCalendar
import io.github.aughtone.calendars.internal.CopticCalendar
import io.github.aughtone.calendars.internal.DangiCalendar
import io.github.aughtone.calendars.internal.EthiopicAmeteAlemCalendar
import io.github.aughtone.calendars.internal.EthiopicCalendar
import io.github.aughtone.calendars.internal.HebrewCalendar
import io.github.aughtone.calendars.internal.IndianCalendar
import io.github.aughtone.calendars.internal.IslamicCivilCalendar
import io.github.aughtone.calendars.internal.IslamicTabularCalendar
import io.github.aughtone.calendars.internal.JapaneseCalendar
import io.github.aughtone.calendars.internal.PersianCalendar
import io.github.aughtone.calendars.internal.RocCalendar
import io.github.aughtone.calendars.internal.UmmAlQuraCalendar
import kotlinx.datetime.LocalDate

/**
 * The arithmetic of one calendar system: conversion to and from ISO dates, and the shape of its
 * years and months. Get one with [CalendarSystem.calendar].
 *
 * Every calendar gives the same answer on every platform: it is computed in common code, never
 * read from the platform. Nothing here throws; a date or month that does not exist is `null`.
 */
interface Calendar {
    /** The system this calendar computes. */
    val system: CalendarSystem

    /**
     * The date in this calendar that falls on the ISO [date]. An arithmetic calendar always
     * gives [CalendarResult.Resolved]; [CalendarResult.Unresolved] is for a date the calendar
     * cannot place, such as a Japanese date before the first era.
     */
    fun fromLocalDate(date: LocalDate): CalendarResult

    /**
     * The ISO date [date] falls on, or `null` when [date] does not exist in this calendar (a day
     * past the end of its month, a leap month in a year without one, a Japanese year outside its
     * era) or is in another calendar.
     */
    fun toLocalDate(date: CalendarDate): LocalDate?

    /**
     * The number of months in [year], counting a leap month: 13 in a Hebrew leap year and in
     * every Coptic and Ethiopic year, 12 otherwise. [era] is needed only by a calendar with eras.
     */
    fun monthsInYear(year: Int, era: String? = null): Int

    /**
     * The number of days in [month] of [year] (the leap month that follows it, when
     * [isLeapMonth] is set), or `null` when that month does not exist.
     */
    fun daysInMonth(year: Int, month: Int, isLeapMonth: Boolean = false, era: String? = null): Int?

    /**
     * The month after which [year]'s leap month falls, or `null` when the year has none: 5 in a
     * Hebrew leap year, whose Adar I follows Shevat.
     */
    fun leapMonth(year: Int, era: String? = null): Int?
}

/**
 * The result of placing an ISO date in a calendar: the date, or the reason it has none.
 */
sealed interface CalendarResult {
    /** The ISO date falls on [date]. */
    data class Resolved(val date: CalendarDate) : CalendarResult

    /**
     * The calendar cannot place the ISO date. [reason] says why, in English, for a log or a
     * developer; it is not meant for display.
     */
    data class Unresolved(val reason: String) : CalendarResult
}

/**
 * The arithmetic for this system, or `null` for the observation-based Islamic calendars
 * ([CalendarSystem.Islamic] and [CalendarSystem.IslamicSaudi]), which cannot be computed: build
 * an [ObservedIslamicCalendar] from the month starts observed, or predict one with
 * [ObservedIslamicCalendar.predictMonthStart].
 */
fun CalendarSystem.calendar(): Calendar? = when (this) {
    CalendarSystem.Buddhist -> BuddhistCalendar
    CalendarSystem.Japanese -> JapaneseCalendar
    CalendarSystem.Roc -> RocCalendar
    CalendarSystem.Hebrew -> HebrewCalendar
    CalendarSystem.IslamicCivil -> IslamicCivilCalendar
    CalendarSystem.IslamicTabular -> IslamicTabularCalendar
    CalendarSystem.IslamicUmmAlQura -> UmmAlQuraCalendar
    CalendarSystem.Persian -> PersianCalendar
    CalendarSystem.Coptic -> CopticCalendar
    CalendarSystem.Ethiopic -> EthiopicCalendar
    CalendarSystem.EthiopicAmeteAlem -> EthiopicAmeteAlemCalendar
    CalendarSystem.Indian -> IndianCalendar
    CalendarSystem.Chinese -> ChineseCalendar
    CalendarSystem.Dangi -> DangiCalendar
    CalendarSystem.Islamic, CalendarSystem.IslamicSaudi -> null
}

/**
 * This ISO date in [system]: "15 Nisan 5787" from 2027-04-22 in the Hebrew calendar. Gives
 * [CalendarResult.Unresolved] when [system] is not computed by this release, as well as for a
 * date its calendar cannot place.
 */
fun LocalDate.toCalendarDate(system: CalendarSystem): CalendarResult =
    system.calendar()?.fromLocalDate(this) ?: CalendarResult.Unresolved("the ${system.cldrName} calendar is not available")
