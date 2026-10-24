package io.github.aughtone.calendars

import kotlinx.datetime.LocalDate

/**
 * A date in a [calendar] other than the ISO Gregorian one: "15 Nisan 5787", "1 Ramadan 1448".
 *
 * Months are numbered as RFC 7529 and Unicode CLDR number them: from 1 to 12, and to 13 in the
 * Coptic and Ethiopic calendars, whose thirteenth month holds the five or six days left over. A
 * **leap month** carries the number of the month it follows, with [isLeapMonth] set: RFC 7529's
 * `5L`. So the Hebrew Adar I, which a leap year inserts after Shevat (5), is month 5 with
 * [isLeapMonth] set, and Adar, or Adar II in a leap year, is month 6.
 *
 * [year] is the calendar's own year on one continuous axis: the year before year 1 is year 0,
 * then -1. In the Republic of China calendar, 1911 (the year before Minguo 1) is year 0; in the
 * Ethiopic calendar a year before the Era of Mercy is 0 or negative, where the Amete Alem
 * calendar ([CalendarSystem.EthiopicAmeteAlem]) counts the same years from the creation era.
 *
 * [era] is set only in a calendar whose years restart with each era, which is the Japanese
 * calendar: its CLDR era code ("reiwa", "heisei", "showa", "taisho", "meiji"), with [year]
 * counted from 1 within that era. Calendars without eras ignore it.
 *
 * A [CalendarDate] can describe a date that does not exist (a 30th day in a 29-day month);
 * [Calendar.toLocalDate] reports such a date as `null`.
 *
 * @property calendar The calendar this date is in.
 * @property year The year, as described above.
 * @property month The month, from 1.
 * @property day The day of the month, from 1.
 * @property isLeapMonth Whether this is the leap month that follows [month].
 * @property era The era code, in a calendar with eras, or `null`.
 */
data class CalendarDate(
    val calendar: CalendarSystem,
    val year: Int,
    val month: Int,
    val day: Int,
    val isLeapMonth: Boolean = false,
    val era: String? = null,
)

/**
 * This date's ISO equivalent, or `null` when it does not exist in its calendar, or the calendar
 * cannot be computed (an observation-based calendar without the month starts it needs).
 * Shorthand for `calendar.calendar()?.toLocalDate(this)`.
 */
fun CalendarDate.toLocalDate(): LocalDate? = calendar.calendar()?.toLocalDate(this)
