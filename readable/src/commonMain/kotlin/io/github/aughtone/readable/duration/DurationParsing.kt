package io.github.aughtone.readable.duration

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * The result of reading a duration from text with [parseReadableWithOutcome]: the duration, or
 * why and where the text could not be read.
 */
sealed interface DurationParseOutcome {
    /** The text reads as [duration]. */
    data class Parsed(val duration: Duration) : DurationParseOutcome

    /**
     * The text is not a duration.
     *
     * @property reason What is wrong with the text.
     * @property position The index in the text given, counted from 0, that [reason] applies to: the
     *   unit that is not known or is repeated, the place a number or a unit was expected, or the
     *   start of a number that is too large. It is the length of the text when the text ended too
     *   soon, and 0 for empty text.
     * @property message [reason] and [position] in an English sentence, for a log or an exception;
     *   it is not meant for display.
     */
    data class Failed(val reason: DurationParseFailure, val position: Int) : DurationParseOutcome {
        val message: String
            get() = when (reason) {
                DurationParseFailure.Empty -> "the text is empty"
                DurationParseFailure.MissingNumber -> "a number was expected at index $position"
                DurationParseFailure.MissingUnit -> "a unit was expected at index $position"
                DurationParseFailure.UnknownUnit -> "the unit at index $position is not known"
                DurationParseFailure.RepeatedUnit -> "the unit at index $position is given twice"
                DurationParseFailure.OutOfRange -> "the value at index $position is too large for a Duration"
            }
    }
}

/** Why text could not be read as a duration. */
enum class DurationParseFailure {
    /** The text is empty, or only spaces. */
    Empty,

    /** There is no number where one was expected: `"minutes"`, `"-"`, `".5h"`, `"5m and 3s"`. */
    MissingNumber,

    /** A number is not followed by a unit: `"10"`, `"1h 30"`. Nothing says what a bare number counts. */
    MissingUnit,

    /** The word after a number is not a unit this reads: `"5 fortnights"`, or a unit in another language. */
    UnknownUnit,

    /** The same unit is given twice, in any spelling: `"5m 10min"`. */
    RepeatedUnit,

    /** The value is too large for a [Duration] to hold. */
    OutOfRange,
}

/**
 * Reads a duration from text such as `"10s"`, `"250ms"`, `"5 minutes"` or `"1h 30m"`: a value from
 * a configuration file, a command-line option or a settings field.
 *
 * The text is one or more parts, each a number followed by a unit, with optional spaces between
 * the number and its unit and between parts. A number is ASCII digits with an optional decimal
 * fraction (`"1.5h"`). A leading `-` or `+` applies to the whole value, and the minus sign U+2212
 * reads as `-`. Spaces around the text are ignored, and a no-break space counts as a space. Each
 * unit may appear once, in any order.
 * `"now"`, which [formatReadable] writes for a duration under half a second, reads as zero.
 *
 * The units, matched without regard to case:
 *
 * | Unit | Accepted |
 * |---|---|
 * | nanosecond | `ns`, `nano`, `nanos`, `nanosecond`, `nanoseconds` |
 * | microsecond | `µs`, `us`, `micro`, `micros`, `microsecond`, `microseconds` |
 * | millisecond | `ms`, `milli`, `millis`, `millisecond`, `milliseconds` |
 * | second | `s`, `sec`, `secs`, `second`, `seconds` |
 * | minute | `m`, `min`, `mins`, `minute`, `minutes` |
 * | hour | `h`, `hr`, `hrs`, `hour`, `hours` |
 * | day | `d`, `day`, `days` |
 * | week | `w`, `wk`, `wks`, `week`, `weeks` |
 * | month | `mo`, `mos`, `month`, `months` |
 * | year | `y`, `yr`, `yrs`, `year`, `years` |
 *
 * A day is 24 hours and a week 7 days. A month is 30 days and a year 365 days, the lengths
 * [formatReadable] scales by, so the English text it writes, in either style, reads back as the
 * duration it shows. That is not always the duration it was given: [formatReadable] rounds, so
 * 90 minutes is written `"2 hours"` and reads back as two hours.
 *
 * It is not locale-aware. The words are English: text [formatReadable] writes in another
 * language does not read, and neither does a number in other digits or with a decimal comma.
 *
 * ```kotlin
 * Duration.parseReadable("10s")          // 10 seconds
 * Duration.parseReadable("5 Minutes")    // 5 minutes
 * Duration.parseReadable("1h 30m")       // 90 minutes
 * Duration.parseReadable("1.5h")         // 90 minutes
 * Duration.parseReadable("-250ms")       // minus 250 milliseconds
 * Duration.parseReadable("2 weeks")      // 14 days
 * Duration.parseReadable("10")           // throws: nothing says what 10 counts
 * ```
 *
 * @param text The text to read.
 * @return The duration the text gives.
 * @throws IllegalArgumentException if [text] is not a duration as described here. The message
 *   names what is wrong and where. Use [parseReadableOrNull], [parseReadableOrElse] or
 *   [parseReadableWithOutcome] where bad text is expected and should not throw.
 */
fun Duration.Companion.parseReadable(text: String): Duration =
    when (val outcome = parseReadableWithOutcome(text)) {
        is DurationParseOutcome.Parsed -> outcome.duration
        is DurationParseOutcome.Failed -> throw IllegalArgumentException("Cannot read \"$text\" as a duration: ${outcome.message}")
    }

/**
 * Reads a duration from text as [parseReadable] does, and returns `null` where that throws.
 *
 * ```kotlin
 * Duration.parseReadableOrNull("5 minutes")   // 5 minutes
 * Duration.parseReadableOrNull("soon")        // null
 * ```
 *
 * @param text The text to read.
 * @return The duration the text gives, or `null` if it is not a duration [parseReadable] reads.
 */
fun Duration.Companion.parseReadableOrNull(text: String): Duration? =
    (parseReadableWithOutcome(text) as? DurationParseOutcome.Parsed)?.duration

/**
 * Reads a duration from text as [parseReadable] does, and returns what [fallback] gives where
 * that throws.
 *
 * ```kotlin
 * val timeout = Duration.parseReadableOrElse(setting) { 30.seconds }
 * val strict = Duration.parseReadableOrElse(setting) { failure -> error("timeout: ${failure.message}") }
 * ```
 *
 * @param text The text to read.
 * @param fallback Called with the failure when [text] is not a duration [parseReadable] reads;
 *   its result is returned. It is not called when the text reads.
 * @return The duration the text gives, or the result of [fallback].
 */
inline fun Duration.Companion.parseReadableOrElse(
    text: String,
    fallback: (failure: DurationParseOutcome.Failed) -> Duration,
): Duration = when (val outcome = parseReadableWithOutcome(text)) {
    is DurationParseOutcome.Parsed -> outcome.duration
    is DurationParseOutcome.Failed -> fallback(outcome)
}

/**
 * Reads a duration from text as [parseReadable] does, and returns the result as a value: the
 * duration, or the reason the text does not read and where reading stopped. It never throws.
 *
 * ```kotlin
 * when (val outcome = Duration.parseReadableWithOutcome(text)) {
 *     is DurationParseOutcome.Parsed -> use(outcome.duration)
 *     is DurationParseOutcome.Failed -> report(outcome.reason, outcome.position)
 * }
 * Duration.parseReadableWithOutcome("5 fortnights")   // Failed(UnknownUnit, position = 2)
 * ```
 *
 * @param text The text to read.
 * @return [DurationParseOutcome.Parsed] with the duration, or [DurationParseOutcome.Failed] with
 *   a [DurationParseFailure] and the index in [text] it applies to.
 */
fun Duration.Companion.parseReadableWithOutcome(text: String): DurationParseOutcome {
    var index = 0
    fun skipSpaces() {
        while (index < text.length && text[index].isWhitespace()) index++
    }
    fun failed(reason: DurationParseFailure, at: Int) = DurationParseOutcome.Failed(reason, at)

    skipSpaces()
    if (index == text.length) return failed(DurationParseFailure.Empty, 0)
    if (text.trim().equals("now", ignoreCase = true)) return DurationParseOutcome.Parsed(Duration.ZERO)

    var negative = false
    if (text[index] == '-' || text[index] == '+' || text[index] == '\u2212') {
        negative = text[index] != '+'
        index++
        skipSpaces()
    }

    var total = Duration.ZERO
    val seen = mutableSetOf<ParsedUnit>()
    do {
        val numberStart = index
        while (index < text.length && text[index] in '0'..'9') index++
        if (index == numberStart) return failed(DurationParseFailure.MissingNumber, numberStart)
        val whole = text.substring(numberStart, index)
        var fraction = 0.0
        if (index + 1 < text.length && text[index] == '.' && text[index + 1] in '0'..'9') {
            val fractionStart = index
            index++
            while (index < text.length && text[index] in '0'..'9') index++
            fraction = ("0" + text.substring(fractionStart, index)).toDouble()
        }
        skipSpaces()

        val unitStart = index
        while (index < text.length && text[index].isLetter()) index++
        if (index == unitStart) return failed(DurationParseFailure.MissingUnit, unitStart)
        val unit = durationUnits[text.substring(unitStart, index).lowercase()]
            ?: return failed(DurationParseFailure.UnknownUnit, unitStart)
        if (!seen.add(unit)) return failed(DurationParseFailure.RepeatedUnit, unitStart)

        // The whole part is converted exactly, and Duration saturates to an infinite value when
        // it does not fit; only the fraction goes through floating point.
        val count = whole.toLongOrNull() ?: return failed(DurationParseFailure.OutOfRange, numberStart)
        total += count.toDuration(unit.unit) * unit.count + 1.toDuration(unit.unit) * (unit.count * fraction)
        if (total.isInfinite()) return failed(DurationParseFailure.OutOfRange, numberStart)
        skipSpaces()
    } while (index < text.length)

    return DurationParseOutcome.Parsed(if (negative) -total else total)
}

/** A unit of text: [count] of [unit], so that a week is 7 days and stays exact. */
private data class ParsedUnit(val unit: DurationUnit, val count: Int = 1)

/** Every spelling of every unit [parseReadableWithOutcome] reads, in lower case. */
private val durationUnits: Map<String, ParsedUnit> = buildMap {
    fun unit(length: ParsedUnit, vararg spellings: String) = spellings.forEach { put(it, length) }
    unit(ParsedUnit(DurationUnit.NANOSECONDS), "ns", "nano", "nanos", "nanosecond", "nanoseconds")
    // The micro sign (U+00B5) and the Greek letter mu (U+03BC), which an upper-case "µs" lowers to.
    unit(ParsedUnit(DurationUnit.MICROSECONDS), "\u00B5s", "\u03BCs", "us", "micro", "micros", "microsecond", "microseconds")
    unit(ParsedUnit(DurationUnit.MILLISECONDS), "ms", "milli", "millis", "millisecond", "milliseconds")
    unit(ParsedUnit(DurationUnit.SECONDS), "s", "sec", "secs", "second", "seconds")
    unit(ParsedUnit(DurationUnit.MINUTES), "m", "min", "mins", "minute", "minutes")
    unit(ParsedUnit(DurationUnit.HOURS), "h", "hr", "hrs", "hour", "hours")
    unit(ParsedUnit(DurationUnit.DAYS), "d", "day", "days")
    unit(ParsedUnit(DurationUnit.DAYS, 7), "w", "wk", "wks", "week", "weeks")
    // The lengths formatReadable counts a month and a year as.
    unit(ParsedUnit(DurationUnit.DAYS, 30), "mo", "mos", "month", "months")
    unit(ParsedUnit(DurationUnit.DAYS, 365), "y", "yr", "yrs", "year", "years")
}
