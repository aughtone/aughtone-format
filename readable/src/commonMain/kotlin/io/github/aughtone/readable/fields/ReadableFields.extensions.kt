package io.github.aughtone.readable.fields

import io.github.aughtone.datetime.format.formatFieldsSkeleton
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.is24HourFormat as platformIs24HourFormat
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

/*
 * formatReadableFields: a date or a time written with the fields the caller chooses, laid out as
 * the locale lays out that set of fields.
 *
 * Every type has three forms — a ReadableFields value, a builder block, and a CLDR skeleton — which
 * give the same text for the same fields. None of them says in what order the fields go or what
 * separates them: the locale decides, from Unicode CLDR's pattern for the set. That is the
 * difference from kotlinx-datetime's `Format { }` builder, which fixes both.
 */

/** Writes the values by [fields]; [clock] is the clock a skeleton asked for, if any. */
private fun formatFields(
    fields: ReadableFields,
    clock: Boolean?,
    typeName: String,
    date: LocalDate?,
    time: LocalTime?,
    timeZone: TimeZone?,
    instant: Instant?,
    locale: Locale,
    is24HourFormat: Boolean,
    numberingSystem: NumberingSystem?,
): String {
    require(!fields.isEmpty) { "No fields to show: name at least one" }
    val absent = buildList {
        if (date == null) {
            if (fields.era != null) add("era")
            if (fields.year) add("year")
            if (fields.month != null || fields.monthNumber) add("month")
            if (fields.day) add("day")
            if (fields.weekday != null) add("weekday")
        }
        if (time == null) {
            if (fields.hour) add("hour")
            if (fields.minute) add("minute")
            if (fields.second) add("second")
        }
        if (instant == null && fields.timeZone != null) add("time zone")
    }
    require(absent.isEmpty()) { "A $typeName has no ${absent.joinToString(", ")} to show" }
    return formatFieldsSkeleton(
        skeleton = fields.skeleton(twentyFourHour = clock ?: is24HourFormat),
        locale = locale,
        date = date,
        time = time,
        timeZone = timeZone,
        instant = instant,
        numberingSystem = numberingSystem,
    )
}

// ── LocalDate ────────────────────────────────────────────────────────────────

/**
 * Writes this date with the chosen [fields], laid out as [locale] lays out that set of fields.
 *
 * ```kotlin
 * LocalDate(2024, 6, 1).formatReadableFields(ReadableFields.MonthDay, localeFor("en-US")!!)   // "Jun 1"
 * LocalDate(2024, 6, 1).formatReadableFields(ReadableFields.MonthDay, localeFor("de")!!)      // "1. Juni"
 * ```
 *
 * [fields] says which fields to show, not in what order or with what between them: the locale
 * decides, from Unicode CLDR's pattern for the set. Month and weekday names take the form the
 * language uses inside a date. A date before year 1 shows its era, with its year counted from one.
 *
 * @param fields The fields to show; only date fields — era, year, month, day, weekday — apply.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The date written with those fields.
 * @throws IllegalArgumentException if [fields] is empty, or names a time field or the zone.
 */
fun LocalDate.formatReadableFields(
    fields: ReadableFields,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
): String = formatFields(fields, null, "LocalDate", this, null, null, null, locale, false, numberingSystem)

/**
 * Writes this date with the fields the [block] names, laid out as [locale] lays out that set of
 * fields: `date.formatReadableFields(locale) { month(TextWidth.Abbreviated); day() }` is "Jun 1"
 * in American English and "1. Juni" in German.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param block Names the fields to show; only date fields apply.
 * @return The date written with those fields.
 * @throws IllegalArgumentException if the block names no field, or a time field or the zone.
 */
fun LocalDate.formatReadableFields(
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFields(ReadableFields(block), locale, numberingSystem)

/**
 * Writes this date with the fields of a CLDR [skeleton], such as "MMMd" or "yMMMEd", laid out as
 * [locale] lays out that set of fields: "MMMd" is "Jun 1" in American English and "1. Juni" in
 * German.
 *
 * A skeleton names fields and their widths, not their order; the letters offered are those of
 * [ReadableFields]'s fields, and only the date fields apply here: `G` era, `y` year, `M` month
 * (one or two letters a number, `MMM` abbreviated, `MMMM` full), `d` day, `E` weekday (`EEEE`
 * full).
 *
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The date written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, has a letter or width not
 *   offered, or names a time field or the zone; the message names it.
 */
fun LocalDate.formatReadableFields(
    skeleton: String,
    locale: Locale = Locale.current,
    numberingSystem: NumberingSystem? = null,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFields(fields, clock, "LocalDate", this, null, null, null, locale, false, numberingSystem)
}

// ── LocalTime ────────────────────────────────────────────────────────────────

/**
 * Writes this time with the chosen [fields], laid out as [locale] lays out that set of fields:
 * [ReadableFields.HourMinute] is "4:05 PM" in American English and "16:05" in German.
 *
 * [fields] says which fields to show, not in what order or with what between them: the locale
 * decides, from Unicode CLDR's pattern for the set.
 *
 * @param fields The fields to show; only the hour, minute and second apply.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The time written with those fields.
 * @throws IllegalArgumentException if [fields] is empty, or names a date field or the zone.
 */
fun LocalTime.formatReadableFields(
    fields: ReadableFields,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
): String = formatFields(fields, null, "LocalTime", null, this, null, null, locale, is24HourFormat, numberingSystem)

/**
 * Writes this time with the fields the [block] names, laid out as [locale] lays out that set of
 * fields: `time.formatReadableFields(locale) { hour(); minute() }` is "4:05 PM" in American
 * English.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param block Names the fields to show; only the hour, minute and second apply.
 * @return The time written with those fields.
 * @throws IllegalArgumentException if the block names no field, or a date field or the zone.
 */
fun LocalTime.formatReadableFields(
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFields(ReadableFields(block), locale, is24HourFormat, numberingSystem)

/**
 * Writes this time with the fields of a CLDR [skeleton], such as "hm" or "Hms", laid out as
 * [locale] lays out that set of fields.
 *
 * A skeleton names fields and their widths, not their order. Only the time fields apply here: an
 * hour (`h` or `K` on a 12-hour clock, `H` or `k` on a 24-hour one, `j` on the clock
 * [is24HourFormat] chooses), `m` minute and `s` second.
 *
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat The clock for a `j` hour (defaults to the platform's setting for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The time written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, has a letter or width not
 *   offered, or names a date field or the zone; the message names it.
 */
fun LocalTime.formatReadableFields(
    skeleton: String,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFields(fields, clock, "LocalTime", null, this, null, null, locale, is24HourFormat, numberingSystem)
}

// ── LocalDateTime ────────────────────────────────────────────────────────────

/**
 * Writes this date-time with the chosen [fields], laid out as [locale] lays out that set of
 * fields: a weekday with the hour and minute is "Sat 4:05 PM" in American English.
 *
 * [fields] says which fields to show, not in what order or with what between them: the locale
 * decides, from Unicode CLDR's pattern for the set, or joins its patterns for the date and the
 * time as it joins a date and a time ("Jun 1, 4:05 PM", "June 1 at 4:05 PM").
 *
 * @param fields The fields to show; every field but the zone applies.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The date-time written with those fields.
 * @throws IllegalArgumentException if [fields] is empty, or names the zone, which a local
 *   date-time does not have; format an [Instant] to show it.
 */
fun LocalDateTime.formatReadableFields(
    fields: ReadableFields,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
): String = formatFields(fields, null, "LocalDateTime", date, time, null, null, locale, is24HourFormat, numberingSystem)

/**
 * Writes this date-time with the fields the [block] names, laid out as [locale] lays out that set
 * of fields: `dateTime.formatReadableFields(locale) { weekday(TextWidth.Abbreviated); hour();
 * minute() }` is "Sat 4:05 PM" in American English.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param block Names the fields to show; every field but the zone applies.
 * @return The date-time written with those fields.
 * @throws IllegalArgumentException if the block names no field, or the zone.
 */
fun LocalDateTime.formatReadableFields(
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFields(ReadableFields(block), locale, is24HourFormat, numberingSystem)

/**
 * Writes this date-time with the fields of a CLDR [skeleton], such as "MMMEdhm", laid out as
 * [locale] lays out that set of fields.
 *
 * A skeleton names fields and their widths, not their order; the letters offered are those of
 * [ReadableFields]'s fields, all but the zone apply here, and the hour may be `h` or `K` on a
 * 12-hour clock, `H` or `k` on a 24-hour one, or `j` on the clock [is24HourFormat] chooses.
 *
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param is24HourFormat The clock for a `j` hour (defaults to the platform's setting for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The date-time written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, has a letter or width not
 *   offered, or names the zone; the message names it.
 */
fun LocalDateTime.formatReadableFields(
    skeleton: String,
    locale: Locale = Locale.current,
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    return formatFields(fields, clock, "LocalDateTime", date, time, null, null, locale, is24HourFormat, numberingSystem)
}

// ── Instant ──────────────────────────────────────────────────────────────────

/**
 * Writes this instant, as it reads in [timeZone], with the chosen [fields], laid out as [locale]
 * lays out that set of fields: the hour, minute and zone are "4:05 PM EDT" in American English.
 *
 * [fields] says which fields to show, not in what order or with what between them: the locale
 * decides, from Unicode CLDR's pattern for the set. The zone's name is the one in force at this
 * instant ("EDT" in July, "EST" in January).
 *
 * @param fields The fields to show; every field applies.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param timeZone The zone the instant is read in (defaults to the system's).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The instant written with those fields.
 * @throws IllegalArgumentException if [fields] is empty.
 */
fun Instant.formatReadableFields(
    fields: ReadableFields,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
): String {
    val local = toLocalDateTime(timeZone)
    return formatFields(fields, null, "Instant", local.date, local.time, timeZone, this, locale, is24HourFormat, numberingSystem)
}

/**
 * Writes this instant, as it reads in [timeZone], with the fields the [block] names, laid out as
 * [locale] lays out that set of fields: `instant.formatReadableFields(locale) { hour(); minute();
 * timeZone(TextWidth.Abbreviated) }` is "4:05 PM EDT" in American English.
 *
 * The order the block names the fields in has no effect; see [ReadableFields].
 *
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param timeZone The zone the instant is read in (defaults to the system's).
 * @param is24HourFormat Whether the hour is on a 24-hour clock (defaults to the platform's setting
 *   for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @param block Names the fields to show; every field applies.
 * @return The instant written with those fields.
 * @throws IllegalArgumentException if the block names no field.
 */
fun Instant.formatReadableFields(
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
    block: ReadableFieldsBuilder.() -> Unit,
): String = formatReadableFields(ReadableFields(block), locale, timeZone, is24HourFormat, numberingSystem)

/**
 * Writes this instant, as it reads in [timeZone], with the fields of a CLDR [skeleton], such as
 * "hmz" or "yMMMdHm", laid out as [locale] lays out that set of fields.
 *
 * A skeleton names fields and their widths, not their order; the letters offered are those of
 * [ReadableFields]'s fields, and the hour may be `h` or `K` on a 12-hour clock, `H` or `k` on a
 * 24-hour one, or `j` on the clock [is24HourFormat] chooses.
 *
 * @param skeleton The fields to show, as a CLDR skeleton.
 * @param locale The locale whose layout, names and digits to use (defaults to [Locale.current]).
 * @param timeZone The zone the instant is read in (defaults to the system's).
 * @param is24HourFormat The clock for a `j` hour (defaults to the platform's setting for [locale]).
 * @param numberingSystem The digits to write, or `null` for the locale's default.
 * @return The instant written with those fields.
 * @throws IllegalArgumentException if [skeleton] is empty or blank, or has a letter or width not
 *   offered; the message names it.
 */
fun Instant.formatReadableFields(
    skeleton: String,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = platformIs24HourFormat(locale),
    numberingSystem: NumberingSystem? = null,
): String {
    val (fields, clock) = ReadableFields.fromSkeleton(skeleton)
    val local = toLocalDateTime(timeZone)
    return formatFields(fields, clock, "Instant", local.date, local.time, timeZone, this, locale, is24HourFormat, numberingSystem)
}
