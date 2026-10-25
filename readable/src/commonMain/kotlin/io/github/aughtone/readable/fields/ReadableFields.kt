package io.github.aughtone.readable.fields

import io.github.aughtone.datetime.format.TextWidth

/**
 * Which fields of a date or a time to show, for [formatReadableFields]: a month and a day, a
 * weekday and the hour, a month and a year.
 *
 * It names fields and their widths, never their order or the text between them. The locale lays
 * them out, from Unicode CLDR's pattern for that set of fields: the month and the day are "Jun 1"
 * in American English, "1 Jun" in British English, "1. Juni" in German and "6月1日" in Japanese,
 * from the same `ReadableFields`. This is not kotlinx-datetime's `LocalDate.Format { }`, whose
 * builder fixes the order and the separators.
 *
 * It is a value: it can be kept in a constant, shared and compared. Build one with named arguments,
 * with the [ReadableFields] builder function, or take one of the ready-made sets such as
 * [MonthDay].
 *
 * ```kotlin
 * ReadableFields(month = TextWidth.Abbreviated, day = true)   // "Jun 1"
 * ReadableFields { weekday(TextWidth.Abbreviated); hour(); minute() }   // "Sat 4:05 PM"
 * ```
 *
 * @property era The era ("CE", "BCE"; "Common Era" at [TextWidth.Full]), or `null` for none. A
 *   date before year 1 shows its era whether asked or not, with its year counted from one.
 * @property year Whether to show the year.
 * @property month The month's name at this width ("Jun", "June"), or `null` for no named month. A
 *   locale that writes the month as a number in this set of fields ("6月1日") does so.
 * @property monthNumber Whether to show the month as a number ("6/1"). Not with [month].
 * @property day Whether to show the day of the month.
 * @property weekday The weekday's name at this width ("Sat", "Saturday"), or `null` for none.
 * @property hour Whether to show the hour, on the clock the call chooses.
 * @property minute Whether to show the minute.
 * @property second Whether to show the second.
 * @property timeZone The zone's name at this width ("EDT", "Eastern Daylight Time"), or `null`
 *   for none. Only an `Instant` has a zone.
 * @throws IllegalArgumentException if both [month] and [monthNumber] are given.
 */
class ReadableFields(
    val era: TextWidth? = null,
    val year: Boolean = false,
    val month: TextWidth? = null,
    val monthNumber: Boolean = false,
    val day: Boolean = false,
    val weekday: TextWidth? = null,
    val hour: Boolean = false,
    val minute: Boolean = false,
    val second: Boolean = false,
    val timeZone: TextWidth? = null,
) {
    init {
        require(month == null || !monthNumber) { "Show the month as a name or as a number, not both" }
    }

    /** Whether no field is shown. Formatting an empty set throws. */
    val isEmpty: Boolean
        get() = era == null && !year && month == null && !monthNumber && !day && weekday == null &&
            !hour && !minute && !second && timeZone == null

    /** Whether any of the date fields — era, year, month, day, weekday — is shown. */
    internal val hasDateFields: Boolean
        get() = era != null || year || month != null || monthNumber || day || weekday != null

    /** Whether any of the time fields — hour, minute, second — is shown. */
    internal val hasTimeFields: Boolean
        get() = hour || minute || second

    /** These fields as a CLDR skeleton, with the hour on a 24-hour clock when [twentyFourHour]. */
    internal fun skeleton(twentyFourHour: Boolean): String = buildString {
        era?.let { append(if (it == TextWidth.Full) "GGGG" else "G") }
        if (year) append('y')
        month?.let { append(if (it == TextWidth.Full) "MMMM" else "MMM") }
        if (monthNumber) append('M')
        weekday?.let { append(if (it == TextWidth.Full) "EEEE" else "E") }
        if (day) append('d')
        if (hour) append(if (twentyFourHour) 'H' else 'h')
        if (minute) append('m')
        if (second) append('s')
        timeZone?.let { append(if (it == TextWidth.Full) "zzzz" else "z") }
    }

    override fun equals(other: Any?): Boolean = other is ReadableFields &&
        era == other.era && year == other.year && month == other.month && monthNumber == other.monthNumber &&
        day == other.day && weekday == other.weekday && hour == other.hour && minute == other.minute &&
        second == other.second && timeZone == other.timeZone

    override fun hashCode(): Int =
        listOf(era, year, month, monthNumber, day, weekday, hour, minute, second, timeZone).hashCode()

    override fun toString(): String = "ReadableFields(${skeleton(twentyFourHour = false)})"

    companion object {
        /** An abbreviated month and the day: "Jun 1", "1 Jun", "1. Juni". */
        val MonthDay = ReadableFields(month = TextWidth.Abbreviated, day = true)

        /** The full month and the year: "June 2024", "juin 2024", "2024年6月". */
        val YearMonth = ReadableFields(year = true, month = TextWidth.Full)

        /** An abbreviated month, the day and the year: "Jun 1, 2024", "1 juin 2024". */
        val YearMonthDay = ReadableFields(year = true, month = TextWidth.Abbreviated, day = true)

        /** The abbreviated weekday, month and day: "Sat, Jun 1", "Sa., 1. Juni". */
        val WeekdayMonthDay = ReadableFields(weekday = TextWidth.Abbreviated, month = TextWidth.Abbreviated, day = true)

        /** The hour and minute: "4:05 PM", "16:05". */
        val HourMinute = ReadableFields(hour = true, minute = true)

        /** The abbreviated weekday with the hour and minute: "Sat 4:05 PM", "Sa., 16:05". */
        val WeekdayHourMinute = ReadableFields(weekday = TextWidth.Abbreviated, hour = true, minute = true)

        /**
         * The fields of a CLDR [skeleton], such as "MMMd" or "yMMMEd", with the clock its hour asks
         * for: `true` for `H` or `k`, `false` for `h` or `K`, `null` for `j` or no hour.
         *
         * The letters are those of the fields offered: `G` era (`GGGG` full), `y` year, `M` or `L`
         * month (one or two a number, `MMM` abbreviated, `MMMM` full), `d` day, `E` or `c` weekday
         * (up to three abbreviated, `EEEE` full), `h` `H` `K` `k` or `j` hour, `m` minute, `s`
         * second, `z` zone (up to three abbreviated, `zzzz` full). A field may appear once.
         *
         * @throws IllegalArgumentException if [skeleton] is empty or blank, or has a letter or a
         *   width these fields do not offer, or a field twice; the message names it.
         */
        internal fun fromSkeleton(skeleton: String): Pair<ReadableFields, Boolean?> {
            require(skeleton.isNotBlank()) { "An empty skeleton shows nothing" }
            var era: TextWidth? = null
            var year = false
            var month: TextWidth? = null
            var monthNumber = false
            var day = false
            var weekday: TextWidth? = null
            var hour = false
            var clock: Boolean? = null
            var minute = false
            var second = false
            var zone: TextWidth? = null
            val seen = mutableSetOf<String>()
            var index = 0
            while (index < skeleton.length) {
                val letter = skeleton[index]
                var end = index
                while (end < skeleton.length && skeleton[end] == letter) end++
                val count = end - index
                fun width(full: Int, maxAbbreviated: Int = 3): TextWidth = when {
                    count == full -> TextWidth.Full
                    count <= maxAbbreviated -> TextWidth.Abbreviated
                    else -> throw IllegalArgumentException("\"${letter.toString().repeat(count)}\" is not a width offered, in \"$skeleton\"")
                }
                val field = when (letter) {
                    'G' -> { era = width(4); "era" }
                    'y' -> {
                        require(count != 2) { "A two-digit year (\"yy\") is not offered, in \"$skeleton\"" }
                        year = true; "year"
                    }
                    'M', 'L' -> {
                        when (count) {
                            1, 2 -> monthNumber = true
                            3 -> month = TextWidth.Abbreviated
                            4 -> month = TextWidth.Full
                            else -> throw IllegalArgumentException("\"${letter.toString().repeat(count)}\" is not a width offered, in \"$skeleton\"")
                        }
                        "month"
                    }
                    'd' -> {
                        require(count <= 2) { "\"${"d".repeat(count)}\" is not a width offered, in \"$skeleton\"" }
                        day = true; "day"
                    }
                    'E', 'c' -> { weekday = width(4); "weekday" }
                    'h', 'K' -> { hour = true; clock = false; "hour" }
                    'H', 'k' -> { hour = true; clock = true; "hour" }
                    'j' -> { hour = true; "hour" }
                    'm' -> { minute = true; "minute" }
                    's' -> { second = true; "second" }
                    'z' -> { zone = width(4); "zone" }
                    else -> throw IllegalArgumentException("'$letter' is not a field offered, in \"$skeleton\"")
                }
                require(seen.add(field)) { "The $field appears twice, in \"$skeleton\"" }
                index = end
            }
            val fields = ReadableFields(era, year, month, monthNumber, day, weekday, hour, minute, second, zone)
            return fields to clock
        }
    }
}

/**
 * Builds a [ReadableFields] by naming its fields, for [formatReadableFields].
 *
 * ```kotlin
 * val fields = ReadableFields { month(TextWidth.Abbreviated); day() }   // "Jun 1"
 * ```
 *
 * The order the fields are named in has no effect: the locale lays them out. Naming a field again
 * replaces its width.
 */
fun ReadableFields(block: ReadableFieldsBuilder.() -> Unit): ReadableFields = ReadableFieldsBuilder().apply(block).build()

/**
 * Names the fields of a [ReadableFields]; see the [ReadableFields] builder function. The order of
 * the calls has no effect, and naming a field again replaces its width. A name's width defaults to
 * [TextWidth.Full], as `displayName` does.
 */
class ReadableFieldsBuilder internal constructor() {
    private var era: TextWidth? = null
    private var year = false
    private var month: TextWidth? = null
    private var monthNumber = false
    private var day = false
    private var weekday: TextWidth? = null
    private var hour = false
    private var minute = false
    private var second = false
    private var timeZone: TextWidth? = null

    /** Shows the era at [width] ("Common Era", or "CE" at [TextWidth.Abbreviated]). */
    fun era(width: TextWidth = TextWidth.Full) { era = width }

    /** Shows the year. */
    fun year() { year = true }

    /** Shows the month's name at [width] ("Jun", "June"), in place of any month number. */
    fun month(width: TextWidth = TextWidth.Full) { month = width; monthNumber = false }

    /** Shows the month as a number ("6"), in place of any month name. */
    fun monthNumber() { monthNumber = true; month = null }

    /** Shows the day of the month. */
    fun day() { day = true }

    /** Shows the weekday's name at [width] ("Sat", "Saturday"). */
    fun weekday(width: TextWidth = TextWidth.Full) { weekday = width }

    /** Shows the hour, on the clock the call chooses. */
    fun hour() { hour = true }

    /** Shows the minute. */
    fun minute() { minute = true }

    /** Shows the second. */
    fun second() { second = true }

    /** Shows the zone's name at [width] ("EDT", "Eastern Daylight Time"); only an `Instant` has one. */
    fun timeZone(width: TextWidth = TextWidth.Full) { timeZone = width }

    internal fun build(): ReadableFields =
        ReadableFields(era, year, month, monthNumber, day, weekday, hour, minute, second, timeZone)
}
