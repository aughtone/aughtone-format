package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlin.time.Instant
import kotlinx.datetime.TimeZone


/**
 * Formats a pair of epoch milliseconds as a formatted date and time string.
 *
 * This function takes a [Pair] where each element represents epoch milliseconds (nullable Long).
 * It converts each element to an [Instant] and then formats them into a human-readable
 * date and time string based on the provided styles, locale, and time zone.
 *
 * Each side is formatted independently. A null element is not omitted — it is
 * rendered as [placeholder] (default `"?"`) — so the result always contains both
 * sides and the ` - ` separator. If both elements are null the result is `"? - ?"`
 * with the default placeholder.
 *
 * ```kotlin
 * val epochMillisPair = Pair(1678886400000L, 1678972800000L) // Example epoch milliseconds
 * val formattedDateTime = epochMillisPair.formatAsDateTime()
 * println(formattedDateTime) // Output (will vary based on locale and time zone): 3/15/23, 12:00 AM - 3/16/23, 12:00 AM
 *
 * val epochMillisPair2 = Pair(1678886400000L, null)
 * val formattedDateTime2 = epochMillisPair2.formatAsDateTime()
 * println(formattedDateTime2) // Output (will vary): 3/15/23, 12:00 AM - ?
 * ```
 *
 * @param dateStyle The desired style for formatting the date component. Defaults to [DateTimeStyle.Short].
 * @param timeStyle The desired style for formatting the time component. Defaults to [DateTimeStyle.Short].
 * @param locale The locale to use for formatting. Defaults to the current locale ([Locale.current]).
 * @param timeZone The time zone to use for formatting. Defaults to the system's default time zone ([TimeZone.currentSystemDefault()]).
 * @param placeholder The text rendered in place of a null element. Defaults to `"?"`.
 * @return A formatted string in the form `[first] - [second]`, where each side is either the
 *         formatted date and time or [placeholder] when that element is null. If both elements
 *         are null the result is `"? - ?"` with the default placeholder.
 */
fun Pair<Long?, Long?>.formatAsDateTime(
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Short,
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
 * Format this pair of instants as a date range in the form `[first] - [second]`.
 *
 * Each side is formatted independently. A null instant is not omitted — it is
 * rendered as [placeholder] (default `"?"`) — so the result always contains both
 * sides and the ` - ` separator. If both instants are null the result is `"? - ?"`
 * with the default placeholder.
 *
 * @receiver [Pair] The pair of instants to format.
 * @param dateStyle [DateTimeStyle] The date style to use. Defaults to [DateTimeStyle.Short].
 * @param timeStyle [DateTimeStyle] The time style to use. Defaults to [DateTimeStyle.Short].
 * @param locale [Locale] The locale to use. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use. Defaults to [TimeZone.currentSystemDefault].
 * @param placeholder [String] The text rendered in place of a null instant. Defaults to `"?"`.
 * @param is24HourFormat [Boolean] Whether to use the 24 hour clock. Defaults to [is24HourFormat].
 * @return [String] The formatted range `[first] - [second]`, where each side is either the
 *   formatted instant or [placeholder] when that side is null.
 */
fun Pair<Instant?, Instant?>.format(
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Short,
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
