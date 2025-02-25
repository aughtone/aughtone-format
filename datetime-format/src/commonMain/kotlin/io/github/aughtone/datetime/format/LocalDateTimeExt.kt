package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

/**
 * @param dateStyle [DateTimeStyle] The style to use for the date.
 * @param timeStyle [DateTimeStyle] The style to use for the time.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param use24HourClock [Boolean] Whether to use 24 hour clock or not. Defaults to [is24HourFormat].
 * @param useNative [Boolean] Whether to use the underlying platform formatting or not. Defaults to true.
 * @return [String] The formatted date and time.
 */
fun LocalDateTime.format(
    dateStyle: DateTimeStyle,
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    use24HourClock: Boolean = is24HourFormat(locale = locale),
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = this,
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = use24HourClock
) ?: toString()
