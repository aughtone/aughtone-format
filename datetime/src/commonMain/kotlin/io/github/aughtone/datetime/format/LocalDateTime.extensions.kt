package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.getCurrent
import androidx.compose.ui.text.intl.Locale as ComposeLocale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.values.EraNames

/**
 * @param dateStyle [DateTimeStyle] The style to use for the date.
 * @param timeStyle [DateTimeStyle] The style to use for the time.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param is24HourFormat [Boolean] Whether to use 24 hour clock or not. Defaults to [is24HourFormat].
 * @param eraNames [EraNames] Optional override for era names.
 * @param numberingSystem [NumberingSystem] Optional numbering system to use for digits.
 * @return [String] The formatted date and time.
 */
fun LocalDateTime.format(
    dateStyle: DateTimeStyle,
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.getCurrent(fallbackTag = ComposeLocale.current.toLanguageTag()),
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    eraNames: EraNames? = null,
    numberingSystem: NumberingSystem? = null,
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = this,
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    locale = locale,
    timeZone = timeZone,
    twentyFourHour = is24HourFormat,
    eraNames = eraNames,
    numberingSystem = numberingSystem
) ?: toString()
