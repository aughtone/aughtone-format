@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.getCurrent
import androidx.compose.ui.text.intl.Locale as ComposeLocale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

import io.github.aughtone.datetime.format.resources.NumberingSystem

/**
 * @param dateStyle [DateTimeStyle] The style to use for the date.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param numberingSystem [NumberingSystem] Optional numbering system to use for digits.
 * @return [String] The formatted date.
 */
fun LocalDate.format(
    dateStyle: DateTimeStyle,
    locale: Locale = Locale.getCurrent(fallbackTag = ComposeLocale.current.toLanguageTag()),
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = atStartOfDayIn(timeZone = timeZone).toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = DateTimeStyle.None,
    locale = locale,
    timeZone = timeZone,
    twentyFourHour = is24HourFormat,
    numberingSystem = numberingSystem
) ?: toString()
