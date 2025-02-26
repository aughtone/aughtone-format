package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone


/**
 * Formats a pair of epoch milliseconds as a formatted date and time string.
 *
 * This function takes a [Pair] where each element represents epoch milliseconds (nullable Long).
 * It converts each element to an [Instant] and then formats them into a human-readable
 * date and time string based on the provided styles, locale, and time zone.
 *
 * If either the first or second element of the Pair is null, it is treated as absent when formatting.
 *
 * @param dateStyle The desired style for formatting the date component. Defaults to [DateTimeStyle.SHORT].
 * @param timeStyle The desired style for formatting the time component. Defaults to [DateTimeStyle.SHORT].
 * @param locale The locale to use for formatting. Defaults to the current locale ([Locale.current]).
 * @param timeZone The time zone to use for formatting. Defaults to the system's default time zone ([TimeZone.currentSystemDefault()]).
 * @return A formatted string representing the date and time of the pair of milliseconds or an empty string if both are null.
 *         If only one is null it will return the formatted value of the non null one.
 *         If both non null, it will return a formatted representation of the first and second with a dash between them.
 * @sample
 *     val epochMillisPair = Pair(1678886400000L, 1678972800000L) // Example epoch milliseconds
 *     val formattedDateTime = epochMillisPair.formatAsDateTime()
 *     println(formattedDateTime) // Output (will vary based on locale and time zone): 3/15/23, 12:00 AM - 3/16/23, 12:00 AM
 *
 *     val epochMillisPair2 = Pair(1678886400000L, null)
 *     val formattedDateTime2 = epochMillisPair2.formatAsDateTime()
 */
fun Pair<Long?, Long?>.formatAsDateTime(
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    timeStyle: DateTimeStyle = DateTimeStyle.SHORT,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    placeholder: String = "?",
): String = Pair(
    first = first?.let { Instant.fromEpochMilliseconds(it) },
    second = second?.let { Instant.fromEpochMilliseconds(it) }
).format(
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    locale = locale,
    timeZone = timeZone,
    placeholder = placeholder,
)

/**
 * Format this pair of instants as a date range.
 * If one of the instants is null, it will be ignored.
 * If both instants are null, it will return an empty string.
 * If both instants are non-null, it will return a string in the format: `[start] - [end]`
 * where [start] and [end] are the formatted instants.
 *
 * @receiver [Pair] The pair of instants to format. If one of the instants is null, it will be ignored.
 * @param dateStyle [DateTimeStyle] The date style to use. Defaults to [DateTimeStyle.SHORT].
 * @param timeStyle [DateTimeStyle] The time style to use. Defaults to [DateTimeStyle.SHORT].
 * @param locale [Locale] The locale to use. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use. Defaults to [TimeZone.currentSystemDefault].
 */
fun Pair<Instant?, Instant?>.format(
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    timeStyle: DateTimeStyle = DateTimeStyle.SHORT,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    placeholder: String = "?",
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
): String = "${
    first?.format(
        dateStyle = dateStyle,
        timeStyle = timeStyle,
        locale = locale,
        timeZone = timeZone,
        is24HourFormat = is24HourFormat,
    ) ?: placeholder
} - ${
    second?.format(
        dateStyle = dateStyle,
        timeStyle = timeStyle,
        locale = locale,
        timeZone = timeZone,
        is24HourFormat = is24HourFormat,
    ) ?: placeholder
}"
