package io.github.aughtone.readable.fields

import io.github.aughtone.datetime.format.formatFieldsRangeSkeleton
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.is24HourFormat as platformIs24HourFormat
import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

/*
 * formatReadableFieldsRange: a range from one date or time to another, written with the fields the
 * caller chooses and laid out as the locale lays out a range of that set of fields: what both ends
 * share is written once ("Jun 1 – 3", "Jun 1 – Sep 30"), as ICU's DateIntervalFormat writes it.
 *
 * Every type has the three forms formatReadableFields has — a ReadableFields value, a builder
 * block, and a CLDR skeleton — which give the same text for the same fields.
 *
 * Spacing: the space before the range's dash never breaks, so a line cannot start with it. The
 * space after it breaks unless noBreak is true. separators decides the spaces inside each end:
 * Ascii, the default, writes them as the locale's data gives them; Typographic makes them no-break,
 * so that "Jun 1" never wraps between its month and its day.
 */

/** Writes the range by [fields]; [clock] is the clock a skeleton asked for, if any. */
private fun formatFieldsRange(
    fields: ReadableFields,
    clock: Boolean?,
    typeName: String,
    start: LocalDateTime?,
    end: LocalDateTime?,
    startDate: LocalDate?,
    endDate: LocalDate?,
    startTime: LocalTime?,
    endTime: LocalTime?,
    timeZone: TimeZone?,
    startInstant: Instant?,
    endInstant: Instant?,
    locale: Locale,
    is24HourFormat: Boolean,
    numberingSystem: NumberingSystem?,
    separators: Separators,
    noBreak: Boolean,
): String {
    requireFieldsOf(
        fields, typeName,
        hasDate = (start ?: startDate) != null, hasTime = (start ?: startTime) != null, hasZone = startInstant != null,
    )
    return formatFieldsRangeSkeleton(
        skeleton = fields.skeleton(twentyFourHour = clock ?: is24HourFormat),
        locale = locale,
        startDate = start?.date ?: startDate,
        startTime = start?.time ?: startTime,
        endDate = end?.date ?: endDate,
        endTime = end?.time ?: endTime,
        timeZone = timeZone,
        startInstant = startInstant,
        endInstant = endInstant,
        numberingSystem = numberingSystem,
        separators = separators,
        noBreak = noBreak,
    )
}

// ── LocalDate ────────────────────────────────────────────────────────────────

/**
 * Writes the range from this date to [end] with the chosen [fields], laid out as [locale] lays out
 * a range of that set of fields, with what both ends share written once.
 *
 * ```kotlin
 * val start = LocalDate(2024, 6, 1)
 * start.formatReadableFieldsRange(LocalDate(2024, 6, 3), ReadableFields.MonthDay, localeFor("en-US")!!)   // "Jun 1 – 3"
 * start.formatReadableFieldsRange(LocalDate(2024, 9, 30), ReadableFields.MonthDay, localeFor("en-US")!!)  // "Jun 1 – Sep 30"
 * start.formatReadableFieldsRange(LocalDate(2025, 6, 1), ReadableFields.MonthDay, localeFor("en-US")!!)   // "Jun 1, 2024 – Jun 1, 2025"
 * start.formatReadableFieldsRange(LocalDate(2024, 6, 3), ReadableFields.MonthDay, localeFor("de")!!)      // "1.–3. Juni"
 * ```
 *
 * The range is laid out as Unicode CLDR's interval patterns lay it out, chosen as ICU's
 * `DateIntervalFormat` chooses them. Where the ends differ in a field larger than any shown, that
 * field is added to both ends, as in the third example. Where they differ only in fields smaller
 * than any shown, the range is written as one value. Where the locale has no pattern for the set,
 * the two ends are written in full and joined by its range separator.
 *
 * The space before the range's dash never breaks. The space after it does, unless [noBreak] is
 * `true`. [separators] decides the spaces inside each end: [Separators.Ascii] writes them as the
 * locale's data gives them, and [Separators.Typographic] makes them no-break.
 *
 * @param end The last date of the range. It may be before this one; the range is written as given.
 * @param fields The fields to show; only date fields (era, year, month, day, weekday) apply.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [fields] is empty, or names a time field or the zone.
 */
fun LocalDate.formatReadableFieldsRange(
    end: LocalDate,
    fields: ReadableFields,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = formatFieldsRange(
    fields, null, "LocalDate", null, null, this, end, null, null, null, null, null,
    locale, false, numberingSystem, separators, noBreak,
)

/**
 * Writes the range from this date to [end] with the fields the [block] names, laid out as [locale]
 * lays out a range of that set of fields: `start.formatReadableFieldsRange(end, locale) {
 * month(TextWidth.Abbreviated); day() }` is "Jun 1 – 3" in American English for June 1 to 3.
 *
 * The order the block names the fields in has no effect; see [ReadableFields]. The layout and the
 * spacing are as the [ReadableFields] form describes.
 *
 * @param end The last date of the range. It may be before this one; the range is written as given.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @param block Names the fields to show; only date fields apply.
 * @return The range written with those fields.
 * @throws IllegalArgumentException if the block names no field, or a time field or the zone.
 */
fun LocalDate.formatReadableFieldsRange(
    end: LocalDate,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFieldsRange(end, ReadableFields(block), locale, numberingSystem, separators, noBreak)

/**
 * Writes the range from this date to [end] with the fields of a CLDR [skeleton], such as "MMMd" or
 * "yMMMEd", laid out as [locale] lays out a range of that set of fields: "MMMd" is "Jun 1 – 3" in
 * American English and "1.–3. Juni" in German for June 1 to 3.
 *
 * The skeleton's letters are those [LocalDate.formatReadableFields] takes. The layout and the
 * spacing are as the [ReadableFields] form describes.
 *
 * @param end The last date of the range. It may be before this one; the range is written as given.
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, has a letter or width not
 *   offered, or names a time field or the zone; the message names it.
 */
fun LocalDate.formatReadableFieldsRange(
    end: LocalDate,
    skeleton: String,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFieldsRange(
        fields, clock, "LocalDate", null, null, this, end, null, null, null, null, null,
        locale, false, numberingSystem, separators, noBreak,
    )
}

// ── LocalTime ────────────────────────────────────────────────────────────────

/**
 * Writes the range from this time to [end] with the chosen [fields], laid out as [locale] lays out
 * a range of that set of fields: [ReadableFields.HourMinute] from 9:00 to 11:30 is
 * "9:00 – 11:30 AM" in American English and "09:00–11:30 Uhr" in German.
 *
 * Both ends are times of one day. For a range that crosses midnight, format a [LocalDateTime]
 * range, which shows the dates. The layout and the spacing are as [LocalDate]'s
 * [ReadableFields] form describes.
 *
 * @param end The last time of the range. It may be before this one; the range is written as given.
 * @param fields The fields to show; only the hour, minute and second apply.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [fields] is empty, or names a date field or the zone.
 */
fun LocalTime.formatReadableFieldsRange(
    end: LocalTime,
    fields: ReadableFields,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = formatFieldsRange(
    fields, null, "LocalTime", null, null, null, null, this, end, null, null, null,
    locale, is24HourFormat, numberingSystem, separators, noBreak,
)

/**
 * Writes the range from this time to [end] with the fields the [block] names, laid out as [locale]
 * lays out a range of that set of fields: `start.formatReadableFieldsRange(end, locale) { hour();
 * minute() }` is "9:00 – 11:30 AM" in American English for 9:00 to 11:30.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param end The last time of the range. It may be before this one; the range is written as given.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @param block Names the fields to show; only the hour, minute and second apply.
 * @return The range written with those fields.
 * @throws IllegalArgumentException if the block names no field, or a date field or the zone.
 */
fun LocalTime.formatReadableFieldsRange(
    end: LocalTime,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFieldsRange(end, ReadableFields(block), locale, is24HourFormat, numberingSystem, separators, noBreak)

/**
 * Writes the range from this time to [end] with the fields of a CLDR [skeleton], such as "hm" or
 * "Hms", laid out as [locale] lays out a range of that set of fields.
 *
 * The skeleton's letters are those [LocalTime.formatReadableFields] takes, `j` an hour on the clock
 * [is24HourFormat] chooses.
 *
 * @param end The last time of the range. It may be before this one; the range is written as given.
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat The clock for a `j` hour (defaults to the platform's setting for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, has a letter or width not
 *   offered, or names a date field or the zone; the message names it.
 */
fun LocalTime.formatReadableFieldsRange(
    end: LocalTime,
    skeleton: String,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFieldsRange(
        fields, clock, "LocalTime", null, null, null, null, this, end, null, null, null,
        locale, is24HourFormat, numberingSystem, separators, noBreak,
    )
}

// ── LocalDateTime ────────────────────────────────────────────────────────────

/**
 * Writes the range from this date-time to [end] with the chosen [fields], laid out as [locale]
 * lays out a range of that set of fields.
 *
 * ```kotlin
 * val start = LocalDateTime(2024, 6, 1, 9, 0)
 * val fields = ReadableFields { month(TextWidth.Abbreviated); day(); hour(); minute() }
 * start.formatReadableFieldsRange(LocalDateTime(2024, 6, 1, 17, 0), fields, localeFor("en-US")!!, is24HourFormat = false)
 * // "Jun 1, 9:00 AM – 5:00 PM"
 * start.formatReadableFieldsRange(LocalDateTime(2024, 6, 3, 17, 0), fields, localeFor("en-US")!!, is24HourFormat = false)
 * // "Jun 1, 9:00 AM – Jun 3, 5:00 PM"
 * ```
 *
 * On one day the date is written once, then the range of times. On different days each end is
 * written in full. Where the fields show only the time and the ends fall on different days, each
 * end shows its date as well, so the range does not read as one day's. The rest of the layout and
 * the spacing are as [LocalDate]'s [ReadableFields] form describes.
 *
 * @param end The last date-time of the range. It may be before this one; the range is written as
 *   given.
 * @param fields The fields to show; every field but the zone applies.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [fields] is empty, or names the zone, which a local
 *   date-time does not have; format an [Instant] range to show it.
 */
fun LocalDateTime.formatReadableFieldsRange(
    end: LocalDateTime,
    fields: ReadableFields,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = formatFieldsRange(
    fields, null, "LocalDateTime", this, end, null, null, null, null, null, null, null,
    locale, is24HourFormat, numberingSystem, separators, noBreak,
)

/**
 * Writes the range from this date-time to [end] with the fields the [block] names, laid out as
 * [locale] lays out a range of that set of fields: `start.formatReadableFieldsRange(end, locale) {
 * month(TextWidth.Abbreviated); day(); hour(); minute() }` is "Jun 1, 9:00 AM – 5:00 PM" in
 * American English for 9:00 to 17:00 on June 1.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param end The last date-time of the range. It may be before this one; the range is written as
 *   given.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @param block Names the fields to show; every field but the zone applies.
 * @return The range written with those fields.
 * @throws IllegalArgumentException if the block names no field, or the zone.
 */
fun LocalDateTime.formatReadableFieldsRange(
    end: LocalDateTime,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFieldsRange(end, ReadableFields(block), locale, is24HourFormat, numberingSystem, separators, noBreak)

/**
 * Writes the range from this date-time to [end] with the fields of a CLDR [skeleton], such as
 * "MMMdhm", laid out as [locale] lays out a range of that set of fields.
 *
 * The skeleton's letters are those [LocalDateTime.formatReadableFields] takes.
 *
 * @param end The last date-time of the range. It may be before this one; the range is written as
 *   given.
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat The clock for a `j` hour (defaults to the platform's setting for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, has a letter or width not
 *   offered, or names the zone; the message names it.
 */
fun LocalDateTime.formatReadableFieldsRange(
    end: LocalDateTime,
    skeleton: String,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFieldsRange(
        fields, clock, "LocalDateTime", this, end, null, null, null, null, null, null, null,
        locale, is24HourFormat, numberingSystem, separators, noBreak,
    )
}

// ── Instant ──────────────────────────────────────────────────────────────────

/**
 * Writes the range from this instant to [end], as both read in [timeZone], with the chosen
 * [fields], laid out as [locale] lays out a range of that set of fields: the hour, minute and zone
 * from 9:00 to 17:00 on one day are "9:00 AM – 5:00 PM EDT" in American English.
 *
 * Each end's zone name is the one in force at that instant. The layout and the spacing are as
 * [LocalDateTime]'s [ReadableFields] form describes.
 *
 * @param end The last instant of the range. It may be before this one; the range is written as
 *   given.
 * @param fields The fields to show; every field applies.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param timeZone The zone both instants are read in (defaults to the system's).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [fields] is empty.
 */
fun Instant.formatReadableFieldsRange(
    end: Instant,
    fields: ReadableFields,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String = formatFieldsRange(
    fields, null, "Instant", toLocalDateTime(timeZone), end.toLocalDateTime(timeZone), null, null, null, null,
    timeZone, this, end, locale, is24HourFormat, numberingSystem, separators, noBreak,
)

/**
 * Writes the range from this instant to [end], as both read in [timeZone], with the fields the
 * [block] names, laid out as [locale] lays out a range of that set of fields.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param end The last instant of the range. It may be before this one; the range is written as
 *   given.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param timeZone The zone both instants are read in (defaults to the system's).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @param block Names the fields to show; every field applies.
 * @return The range written with those fields.
 * @throws IllegalArgumentException if the block names no field.
 */
fun Instant.formatReadableFieldsRange(
    end: Instant,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFieldsRange(end, ReadableFields(block), locale, timeZone, is24HourFormat, numberingSystem, separators, noBreak)

/**
 * Writes the range from this instant to [end], as both read in [timeZone], with the fields of a
 * CLDR [skeleton], such as "hmz" or "yMMMdHm", laid out as [locale] lays out a range of that set of
 * fields.
 *
 * The skeleton's letters are those [Instant.formatReadableFields] takes.
 *
 * @param end The last instant of the range. It may be before this one; the range is written as
 *   given.
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param timeZone The zone both instants are read in (defaults to the system's).
 * @param is24HourFormat The clock for a `j` hour (defaults to the platform's setting for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param separators The spaces inside each end (defaults to [Separators.Ascii]).
 * @param noBreak Whether the space after the range's dash is a no-break space too (defaults to
 *   `false`).
 * @return The range written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, or has a letter or width not
 *   offered; the message names it.
 */
fun Instant.formatReadableFieldsRange(
    end: Instant,
    skeleton: String,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
    noBreak: Boolean = false,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFieldsRange(
        fields, clock, "Instant", toLocalDateTime(timeZone), end.toLocalDateTime(timeZone), null, null, null, null,
        timeZone, this, end, locale, is24HourFormat, numberingSystem, separators, noBreak,
    )
}
