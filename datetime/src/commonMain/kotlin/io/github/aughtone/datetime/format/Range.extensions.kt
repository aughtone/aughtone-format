package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.is24HourFormat
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

/**
 * Formats the range from this time to [end] in [locale], the way Unicode CLDR writes time
 * intervals: "9:00 – 11:30 AM", "9:00 AM – 5:00 PM", "09:00–17:00 Uhr".
 *
 * At [DateTimeStyle.Short] what both ends share is written once — a shared AM/PM marker appears
 * a single time — and the separator, spacing and order are the locale's own. The hours, minutes
 * and AM/PM words are the ones this library writes in a single time, so a range reads like the
 * single times beside it. CLDR has no interval patterns with seconds or zones, so the longer
 * styles write two complete times joined by the locale's interval separator.
 *
 * An end earlier than the start — a range that passes midnight — is written as the two times
 * given, with no date; pass `LocalDateTime` values to show the dates. Two times that read the same
 * at [style] are written as one. This never throws.
 *
 * ```kotlin
 * LocalTime(9, 0).formatRange(LocalTime(11, 30), locale = localeFor("en-US")!!, is24HourFormat = false) // "9:00 – 11:30 AM"
 * LocalTime(9, 0).formatRange(LocalTime(17, 0), locale = localeFor("de")!!, is24HourFormat = true)      // "09:00–17:00 Uhr"
 * ```
 *
 * @param end The end of the range.
 * @param style [DateTimeStyle] The time style. Defaults to [DateTimeStyle.Short].
 * @param locale [Locale] The locale whose interval format, words and digits to use.
 * @param timeZone [TimeZone] The zone whose name the [DateTimeStyle.Long] and [DateTimeStyle.Full]
 *   styles show.
 * @param is24HourFormat [Boolean] Whether to use the 24-hour clock.
 * @param numberingSystem [NumberingSystem] Optional numbering system for the digits.
 * @return [String] The formatted range.
 */
fun LocalTime.formatRange(
    end: LocalTime,
    style: DateTimeStyle = DateTimeStyle.Short,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = formatTimeRange(this, end, style, locale, is24HourFormat, numberingSystem) { time ->
    time.format(style, locale = locale, timeZone = timeZone, is24HourFormat = is24HourFormat, numberingSystem = numberingSystem)
}

/**
 * Formats the range from this date to [end] in [locale], the way Unicode CLDR writes date
 * intervals: "March 3 – 5, 2024", "March 3 – April 2, 2024", "3.–5. März 2024".
 *
 * What both ends share — the year, or the month and the year — is written once, and the
 * separator, spacing and order are the locale's own. The month and weekday names, and the widths
 * of the numbers, are the ones this library writes in a single date at the same [style], so a
 * range reads like the single dates beside it; at [DateTimeStyle.Full] the era follows once, as it
 * does after a single date. Equal dates are written as one date, and an end before the start is
 * written in the order given. This never throws.
 *
 * ```kotlin
 * LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 3, 5), DateTimeStyle.Long, localeFor("en-US")!!) // "March 3 – 5, 2024"
 * LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 3, 5), DateTimeStyle.Long, localeFor("de")!!)    // "3.–5. März 2024"
 * ```
 *
 * @param end The end of the range.
 * @param style [DateTimeStyle] The date style.
 * @param locale [Locale] The locale whose interval format, names and digits to use.
 * @param numberingSystem [NumberingSystem] Optional numbering system for the digits.
 * @return [String] The formatted range.
 */
fun LocalDate.formatRange(
    end: LocalDate,
    style: DateTimeStyle,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
): String = formatDateRange(this, end, style, locale, numberingSystem) { date ->
    date.format(style, locale = locale, timeZone = TimeZone.UTC, numberingSystem = numberingSystem)
}

/**
 * Formats the range from this weekday to [end] in [locale]: "Mon – Fri" in English, "Mo – Fr" in
 * German, at the given [width].
 *
 * The names are the ones [displayName] gives, joined by the locale's interval separator. A range
 * that wraps past the end of the week is written as given ("Fri – Mon"), and equal days are
 * written as one day.
 *
 * @param end The last day of the range.
 * @param width [TextWidth] Full or abbreviated names. Defaults to [TextWidth.Full].
 * @param locale [Locale] The locale whose names and separator to use.
 * @return [String] The formatted range.
 */
fun DayOfWeek.formatRange(
    end: DayOfWeek,
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String {
    val start = displayName(width, locale)
    return if (this == end) start else fillIntervalFallback(intervalFallbackFor(locale), start, end.displayName(width, locale))
}

/**
 * Formats the range from this date and time to [end] in [locale]: "March 5, 2024 9:00 – 11:30 AM"
 * for a range within one day, and "March 5, 2024 10:00 PM – March 6, 2024 2:00 AM" for one that
 * crosses midnight.
 *
 * Within one day the date is written once, followed by the time range as [LocalTime.formatRange]
 * writes it, and joined as [LocalDateTime.format] joins a date and a time. Across days each end
 * is written as [LocalDateTime.format] writes it, and the two are joined by the locale's interval
 * separator. With [DateTimeStyle.None] as the [timeStyle] the dates alone are formatted as
 * [LocalDate.formatRange] does. Equal values are written once. This never throws.
 *
 * @param end The end of the range.
 * @param dateStyle [DateTimeStyle] The date style.
 * @param timeStyle [DateTimeStyle] The time style.
 * @param locale [Locale] The locale whose interval format, words and digits to use.
 * @param timeZone [TimeZone] The zone whose name the [DateTimeStyle.Long] and [DateTimeStyle.Full]
 *   time styles show. A local date-time on a clock change is read as the earlier of its two
 *   moments; format an [Instant] range to name each end's zone exactly.
 * @param is24HourFormat [Boolean] Whether to use the 24-hour clock.
 * @param numberingSystem [NumberingSystem] Optional numbering system for the digits.
 * @return [String] The formatted range.
 */
fun LocalDateTime.formatRange(
    end: LocalDateTime,
    dateStyle: DateTimeStyle,
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = formatDateTimeRange(this, end, dateStyle, timeStyle, locale, timeZone, is24HourFormat, numberingSystem)

/**
 * Formats the range from this instant to [end] as local dates and times in [timeZone], as
 * [LocalDateTime.formatRange] does.
 *
 * Each end is shown at its own moment, so a range that crosses a daylight-saving change names
 * each end's zone at the [DateTimeStyle.Long] and [DateTimeStyle.Full] time styles: "9:00 PM EST –
 * 9:00 PM EDT" on the night the clocks go forward. Equal instants are written once. This never
 * throws.
 *
 * @param end The end of the range.
 * @param dateStyle [DateTimeStyle] The date style. Defaults to [DateTimeStyle.Short].
 * @param timeStyle [DateTimeStyle] The time style. Defaults to [DateTimeStyle.Long].
 * @param locale [Locale] The locale whose interval format, words and digits to use.
 * @param timeZone [TimeZone] The zone to show both ends in. Defaults to [TimeZone.currentSystemDefault].
 * @param is24HourFormat [Boolean] Whether to use the 24-hour clock.
 * @param numberingSystem [NumberingSystem] Optional numbering system for the digits.
 * @return [String] The formatted range.
 */
fun Instant.formatRange(
    end: Instant,
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Long,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = formatDateTimeRange(
    toLocalDateTime(timeZone), end.toLocalDateTime(timeZone), dateStyle, timeStyle, locale, timeZone, is24HourFormat, numberingSystem,
    startInstant = this, endInstant = end,
)
