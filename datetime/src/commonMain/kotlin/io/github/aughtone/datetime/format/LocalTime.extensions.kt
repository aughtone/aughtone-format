package io.github.aughtone.datetime.format

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
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
 * @param is24HourFormat [Boolean] Whether to use 24 hour clock or not. Defaults to [is24HourFormat].
 * @param numberingSystem [NumberingSystem] Optional numbering system to use for digits.
 * @param separators [Separators] The spaces inside the value: [Separators.Ascii], the default,
 *   writes every space as an ordinary one, U+0020, which a line can break at; [Separators.Typographic]
 *   writes them as no-break spaces (U+00A0, and U+202F for a narrow one), so the value never wraps.
 * @return [String] The formatted time.
 */
@OptIn(ExperimentalTime::class)
fun LocalTime.format(
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
    separators: Separators = Separators.Ascii,
): String = (MultiplatformDateFormatter.formatDateTime(
    localDateTime = atDate(Clock.System.todayIn(timeZone = timeZone)),
    dateStyle = DateTimeStyle.None,
    timeStyle = timeStyle,
    locale = locale,
    timeZone = timeZone,
    twentyFourHour = is24HourFormat,
    numberingSystem = numberingSystem
) ?: toString()).withSeparators(separators)

/** The 4.3 signature, kept so code compiled against it still links. */
@OptIn(ExperimentalTime::class)
@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun LocalTime.format(
    timeStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = format(
    timeStyle = timeStyle,
    locale = locale,
    timeZone = timeZone,
    is24HourFormat = is24HourFormat,
    numberingSystem = numberingSystem,
    separators = Separators.Ascii,
)
