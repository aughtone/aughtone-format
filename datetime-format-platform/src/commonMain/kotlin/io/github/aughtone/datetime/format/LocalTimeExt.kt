package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.PlatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.todayIn

/**
 * @param timeStyle [DateTimeStyle] The style to use for the time.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param use24HourClock [Boolean] Whether to use 24 hour clock or not. Defaults to [is24HourFormat].
 * @param useNative [Boolean] Whether to use the underlying platform formatting or not. Defaults to true.
 * @return [String] The formatted time.
 */
fun LocalTime.systemFormat(
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    use24HourClock: Boolean = is24HourFormat(locale = locale),
): String = PlatformDateFormatter.formatDateTime(
    localDateTime = atDate(Clock.System.todayIn(timeZone = timeZone)),
    dateStyle = DateTimeStyle.NONE,
    timeStyle = timeStyle,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = use24HourClock
) ?: toString()
