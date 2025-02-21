package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.platform.PlatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

/**
 * @param dateStyle [DateTimeStyle] The style to use for the date.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param useNative [Boolean] Whether to use the underlying platform formatting or not. Defaults to true.
 * @return [String] The formatted date.
 */
fun LocalDate.formatWith(
    dateStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    useNative: Boolean = true,
): String = if (useNative) {
    PlatformDateFormatter.formatDateTime(
        localDateTime = atStartOfDayIn(timeZone = timeZone).toLocalDateTime(timeZone = timeZone),
        dateStyle = dateStyle,
        timeStyle = DateTimeStyle.NONE,
        locale.toLanguageTag(),
        timeZone = timeZone,
        twentyFourHour = is24HourFormat(locale = locale)
    )
} else {
    null
} ?: MultiplatformDateFormatter.formatDateTime(
    localDateTime = atStartOfDayIn(timeZone = timeZone).toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = DateTimeStyle.NONE,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
) ?: toString()
