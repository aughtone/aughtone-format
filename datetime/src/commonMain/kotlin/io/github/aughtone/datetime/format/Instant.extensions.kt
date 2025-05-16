package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit
import kotlin.time.toDuration


fun Instant.format(
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Long,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale)
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    languageTag = locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat
) ?: toString()

/**
 * Formats the [Instant] relative to another [Instant].
 *
 * For dates within the [until] duration, a relative format is used (e.g., "2 days ago").
 * For dates outside this range, a standard date/time format is applied.
 *
 * The selection of formatting in the past of future is based on the [relativeTo] parameter.
 *
 * @param until The duration within which relative formatting is used.  Defaults to 5 days.
 * @param dateStyle The date style for standard date/time formatting. Defaults to [DateTimeStyle.Short].
 * @param timeStyle The time style for standard date/time formatting. Defaults to [DateTimeStyle.Short].
 * @param relativeStyle The style for relative time formatting. Defaults to [RelativeStyle.Long].
 * @param locale The locale for formatting. Defaults to the current locale.
 * @param timeZone The time zone for formatting. Defaults to the system's default time zone.
 * @param relativeTo The [Instant] relative to which the formatting is performed. Defaults to the current time.
 * @return The formatted string representing the [Instant].
 */
fun Instant.formatRelative(
    until: Duration = 5.days,
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Short,
    relativeStyle: RelativeStyle = RelativeStyle.Long,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    relativeTo: Instant = Clock.System.now()
) = formatRelative(
    until = until,
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    relativeStyle = relativeStyle,
    relativeTime = if (relativeTo < this) RelativeTime.Future else RelativeTime.Past,
    locale = locale,
    timeZone = timeZone,
    relativeTo = relativeTo
)

/**
 * Formats the [Instant] relative to another [Instant].
 *
 * For dates within the [until] duration, a relative format is used (e.g., "2 days ago").
 * For dates outside this range, a standard date/time format is applied.
 *
 * @param until The duration within which relative formatting is used.  Defaults to 5 days.
 * @param dateStyle The date style for standard date/time formatting. Defaults to [DateTimeStyle.Short].
 * @param timeStyle The time style for standard date/time formatting. Defaults to [DateTimeStyle.Short].
 * @param relativeStyle The style for relative time formatting. Defaults to [RelativeStyle.Long].
 * @param relativeTime Whether to use past, present or future tense.  Defaults to [RelativeTime.Present].
 * @param locale The locale for formatting. Defaults to the current locale.
 * @param timeZone The time zone for formatting. Defaults to the system's default time zone.
 * @param relativeTo The [Instant] relative to which the formatting is performed. Defaults to the current time.
 * @return The formatted string representing the [Instant].
 */
fun Instant.formatRelative(
    until: Duration = 5.days,
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Short,
    relativeStyle: RelativeStyle = RelativeStyle.Long,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    relativeTo: Instant = Clock.System.now()
): String {
    require(
        when (relativeTime) {
            RelativeTime.Past -> relativeTo > this
            RelativeTime.Future -> relativeTo < this
            else -> true
        }
    ) {
        "For a relativeTime of $relativeTime the ${
            when (relativeTime) {
                RelativeTime.Past -> "relativeTo must be after this"
                RelativeTime.Future -> "relativeTo must be before this"
                else -> ""
            }
        }."
    }
    val untilFuture = plus(until)
    return if (relativeTo < untilFuture) {
        val ago = relativeTo.minus(this)

        ago.formatRelative(
            style = relativeStyle,
            relativeTime = relativeTime,
        )
    } else {
        format(
            dateStyle = dateStyle,
            timeStyle = timeStyle,
            locale = locale,
            timeZone = timeZone
        )
    }
}

/**
 * Formats the remaining time between a given timestamp and an [Instant], relative to a provided duration.
 *
 * This function calculates the difference between the [Instant]'s epoch milliseconds and a [timestamp],
 * subtracting this difference from a given [duration]. The result is coerced to ensure it falls within the range
 * of 0 to the total milliseconds of the [duration]. Finally, it converts this remaining time back into a
 * [Duration] and formats it as a string in the specified [unit] with no decimal places.
 *
 * @param duration The total duration representing the initial time frame.
 * @param timestamp The reference timestamp in milliseconds against which the [Instant] is compared.
 * @param unit The unit in which the remaining time should be expressed (default: [DurationUnit.HOURS]).
 * @return A string representing the remaining time in the specified unit, rounded down to the nearest whole number.
 */
fun Instant.formatRelativeRemainingIn(
    duration: Duration,
    timestamp: Long,
    unit: DurationUnit = DurationUnit.HOURS
): String {
    return (duration.inWholeMilliseconds - (toEpochMilliseconds() - timestamp)).coerceIn(
        minimumValue = 0L,
        maximumValue = duration.inWholeMilliseconds
    ).toDuration(DurationUnit.MILLISECONDS).toString(unit = unit, decimals = 0)
}
