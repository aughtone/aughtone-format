package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.getCurrent
import androidx.compose.ui.text.intl.Locale as ComposeLocale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.platform.MultiplatformPostFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlin.time.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate

import kotlinx.datetime.toInstant
import kotlinx.datetime.todayIn
import kotlin.time.ExperimentalTime

import io.github.aughtone.datetime.format.resources.NumberingSystem

/**
 * @param timeStyle [DateTimeStyle] The style to use for the time.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param numberingSystem [NumberingSystem] Optional numbering system to use for digits.
 * @return [String] The formatted time.
 */
@OptIn(ExperimentalTime::class)
fun LocalTime.format(
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.getCurrent(fallbackTag = ComposeLocale.current.toLanguageTag()),
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = atDate(Clock.System.todayIn(timeZone = timeZone)),
    dateStyle = DateTimeStyle.None,
    timeStyle = timeStyle,
    locale = locale,
    timeZone = timeZone,
    twentyFourHour = is24HourFormat,
    numberingSystem = numberingSystem
) ?: toString()
