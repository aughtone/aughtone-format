package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.is24HourFormat
import io.github.aughtone.toolbox.Separators
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
 * A range never breaks before its dash, so no line starts with one: the space before the dash is
 * a no-break space (U+00A0, or U+202F where CLDR writes a thin space), and the one after it
 * breaks unless [noBreak] is `true`. [separators] decides the spaces inside each end.
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
 * @param separators [Separators] How the spaces inside each end are written: plain and breakable
 *   ([Separators.Ascii], the default) or no-break ([Separators.Typographic]), which keeps each end on
 *   one line.
 * @param noBreak Whether the space after the range's dash is no-break too. By default the space
 *   before the dash never breaks and the one after it can, so a range wraps only after its dash;
 *   `true` keeps it from wrapping there.
 * @return [String] The formatted range.
 */
fun LocalTime.formatRange(
    end: LocalTime,
    style: DateTimeStyle = DateTimeStyle.Short,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = finishRange(
    formatTimeRange(this, end, style, locale, is24HourFormat, numberingSystem) { time ->
        time.format(style, locale = locale, timeZone = timeZone, is24HourFormat = is24HourFormat, numberingSystem = numberingSystem)
    },
    separators, noBreak,
)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun LocalTime.formatRange(
    end: LocalTime,
    style: DateTimeStyle = DateTimeStyle.Short,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = formatRange(end, style, locale, timeZone, is24HourFormat, numberingSystem, Separators.Ascii, false)

/**
 * Formats the range from this date to [end] in [locale], the way Unicode CLDR writes date
 * intervals: "March 3 – 5, 2024", "March 3 – April 2, 2024", "3.–5. März 2024".
 *
 * What both ends share — the year, or the month and the year — is written once, and the
 * separator, spacing and order are the locale's own. The month and weekday names, and the widths
 * of the numbers, are the ones this library writes in a single date at the same [style], so a
 * range reads like the single dates beside it. A range with a date before year 1 is written as
 * two complete dates, each with its era. Equal dates are written as one date, and an end before
 * the start is written in the order given. This never throws.
 *
 * A range never breaks before its dash, so no line starts with one: the space before the dash is
 * a no-break space (U+00A0, or U+202F where CLDR writes a thin space), and the one after it
 * breaks unless [noBreak] is `true`. [separators] decides the spaces inside each end.
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
 * @param separators [Separators] How the spaces inside each end are written: plain and breakable
 *   ([Separators.Ascii], the default) or no-break ([Separators.Typographic]), which keeps each end on
 *   one line.
 * @param noBreak Whether the space after the range's dash is no-break too. By default the space
 *   before the dash never breaks and the one after it can, so a range wraps only after its dash;
 *   `true` keeps it from wrapping there.
 * @return [String] The formatted range.
 */
fun LocalDate.formatRange(
    end: LocalDate,
    style: DateTimeStyle,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = finishRange(
    formatDateRange(this, end, style, locale, numberingSystem) { date ->
        date.format(style, locale = locale, timeZone = TimeZone.UTC, numberingSystem = numberingSystem)
    },
    separators, noBreak,
)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun LocalDate.formatRange(
    end: LocalDate,
    style: DateTimeStyle,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
): String = formatRange(end, style, locale, numberingSystem, Separators.Ascii, false)

/**
 * Formats the range from this weekday to [end] in [locale]: "Mon – Fri" in English, "Mo – Fr" in
 * German, at the given [width].
 *
 * The names are the ones [displayName] gives, joined by the locale's interval separator. A range
 * that wraps past the end of the week is written as given ("Fri – Mon"), and equal days are
 * written as one day.
 *
 * A range never breaks before its dash, so no line starts with one: the space before the dash is
 * a no-break space (U+00A0, or U+202F where CLDR writes a thin space), and the one after it
 * breaks unless [noBreak] is `true`. [separators] decides the spaces inside each end.
 *
 * @param end The last day of the range.
 * @param width [TextWidth] Full or abbreviated names. Defaults to [TextWidth.Full].
 * @param locale [Locale] The locale whose names and separator to use.
 * @param separators [Separators] How the spaces inside each end are written: plain and breakable
 *   ([Separators.Ascii], the default) or no-break ([Separators.Typographic]), which keeps each end on
 *   one line.
 * @param noBreak Whether the space after the range's dash is no-break too. By default the space
 *   before the dash never breaks and the one after it can, so a range wraps only after its dash;
 *   `true` keeps it from wrapping there.
 * @return [String] The formatted range.
 */
fun DayOfWeek.formatRange(
    end: DayOfWeek,
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String {
    val start = displayName(width, locale)
    val text = if (this == end) start else fillIntervalFallback(intervalFallbackFor(locale), start, end.displayName(width, locale))
    return finishRange(text, separators, noBreak)
}

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun DayOfWeek.formatRange(
    end: DayOfWeek,
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String = formatRange(end, width, locale, Separators.Ascii, false)

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
 * A range never breaks before its dash, so no line starts with one: the space before the dash is
 * a no-break space (U+00A0, or U+202F where CLDR writes a thin space), and the one after it
 * breaks unless [noBreak] is `true`. [separators] decides the spaces inside each end.
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
 * @param separators [Separators] How the spaces inside each end are written: plain and breakable
 *   ([Separators.Ascii], the default) or no-break ([Separators.Typographic]), which keeps each end on
 *   one line.
 * @param noBreak Whether the space after the range's dash is no-break too. By default the space
 *   before the dash never breaks and the one after it can, so a range wraps only after its dash;
 *   `true` keeps it from wrapping there.
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
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = finishRange(
    formatDateTimeRange(this, end, dateStyle, timeStyle, locale, timeZone, is24HourFormat, numberingSystem),
    separators, noBreak,
)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun LocalDateTime.formatRange(
    end: LocalDateTime,
    dateStyle: DateTimeStyle,
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = formatRange(end, dateStyle, timeStyle, locale, timeZone, is24HourFormat, numberingSystem, Separators.Ascii, false)

/**
 * Formats the range from this instant to [end] as local dates and times in [timeZone], as
 * [LocalDateTime.formatRange] does.
 *
 * Each end is shown at its own moment, so a range that crosses a daylight-saving change names
 * each end's zone at the [DateTimeStyle.Long] and [DateTimeStyle.Full] time styles: "9:00 PM EST –
 * 9:00 PM EDT" on the night the clocks go forward. Equal instants are written once. This never
 * throws.
 *
 * A range never breaks before its dash, so no line starts with one: the space before the dash is
 * a no-break space (U+00A0, or U+202F where CLDR writes a thin space), and the one after it
 * breaks unless [noBreak] is `true`. [separators] decides the spaces inside each end.
 *
 * @param end The end of the range.
 * @param dateStyle [DateTimeStyle] The date style. Defaults to [DateTimeStyle.Short].
 * @param timeStyle [DateTimeStyle] The time style. Defaults to [DateTimeStyle.Long].
 * @param locale [Locale] The locale whose interval format, words and digits to use.
 * @param timeZone [TimeZone] The zone to show both ends in. Defaults to [TimeZone.currentSystemDefault].
 * @param is24HourFormat [Boolean] Whether to use the 24-hour clock.
 * @param numberingSystem [NumberingSystem] Optional numbering system for the digits.
 * @param separators [Separators] How the spaces inside each end are written: plain and breakable
 *   ([Separators.Ascii], the default) or no-break ([Separators.Typographic]), which keeps each end on
 *   one line.
 * @param noBreak Whether the space after the range's dash is no-break too. By default the space
 *   before the dash never breaks and the one after it can, so a range wraps only after its dash;
 *   `true` keeps it from wrapping there.
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
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = finishRange(
    formatDateTimeRange(
        toLocalDateTime(timeZone), end.toLocalDateTime(timeZone), dateStyle, timeStyle, locale, timeZone, is24HourFormat, numberingSystem,
        startInstant = this, endInstant = end,
    ),
    separators, noBreak,
)

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun Instant.formatRange(
    end: Instant,
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Long,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = formatRange(end, dateStyle, timeStyle, locale, timeZone, is24HourFormat, numberingSystem, Separators.Ascii, false)

/**
 * Joins two ends the library cannot type, text the caller has already written, the way [locale]
 * joins the ends of any range: CLDR's interval fallback pattern, "{0} – {1}" in English and
 * "{0}～{1}" in Japanese, or English's where the locale has none.
 *
 * Use it for ends of two different kinds, such as a time and a word: `formatRange("8:00",
 * "Sunset", locale)`. For two values of one kind, use that kind's `formatRange`, or
 * `formatReadableFieldsRange` for a chosen set of fields, which write what the ends share once.
 * [start] and [end] are written as given: they are not parsed, compared or merged, and their own
 * spaces are left as they are. The space before the dash never breaks; see [noBreak]. This never
 * throws.
 *
 * ```kotlin
 * formatRange("1. Juni", "30. Sept.", localeFor("de")!!)  // "1. Juni – 30. Sept."
 * formatRange("8:00", "Sunset", localeFor("ja")!!)        // "8:00～Sunset"
 * ```
 *
 * @param start The start of the range, as written.
 * @param end The end of the range, as written.
 * @param locale [Locale] The locale whose interval separator to use.
 * @param noBreak Whether the space after the dash is no-break too. By default the space before
 *   the dash never breaks and the one after it can, so the range wraps only after its dash.
 * @return [String] The two ends joined.
 */
fun formatRange(
    start: String,
    end: String,
    locale: Locale = Locale.current,
    noBreak: Boolean = false,
): String {
    val joined = fillIntervalFallback(intervalFallbackFor(locale), start, end)
    // The ends are the caller's text, so only the separator is laid out.
    val open = joined.indexOf(RANGE_SEPARATOR_START)
    val close = joined.indexOf(RANGE_SEPARATOR_END)
    if (open < 0 || close < open) return joined
    val separator = finishRange(joined.substring(open, close + 1), Separators.Typographic, noBreak)
    return joined.substring(0, open) + separator + joined.substring(close + 1)
}
