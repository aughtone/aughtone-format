package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.values.EraNames

/**
 * Formats this instant as a date and time string for the given [timeZone].
 *
 * The instant is converted to the local date and time in [timeZone] and rendered
 * according to [dateStyle] and [timeStyle]. A [DateTimeStyle.Long] time appends a
 * time-zone abbreviation and a [DateTimeStyle.Full] time appends the full zone
 * name, while a [DateTimeStyle.Full] date appends the era word (see [eraNames]).
 *
 * @param dateStyle [DateTimeStyle] The style to use for the date. Defaults to [DateTimeStyle.Short].
 * @param timeStyle [DateTimeStyle] The style to use for the time. Defaults to [DateTimeStyle.Long].
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param is24HourFormat [Boolean] Whether to use 24 hour clock or not. Defaults to [is24HourFormat].
 * @param eraNames [EraNames] Optional override for era names.
 * @param numberingSystem [NumberingSystem] Optional numbering system to use for digits.
 * @return [String] The formatted date and time.
 */
fun Instant.format(
    dateStyle: DateTimeStyle = DateTimeStyle.Short,
    timeStyle: DateTimeStyle = DateTimeStyle.Long,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    eraNames: EraNames? = null,
    numberingSystem: NumberingSystem? = null,
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    locale = locale,
    timeZone = timeZone,
    twentyFourHour = is24HourFormat,
    eraNames = eraNames,
    numberingSystem = numberingSystem
) ?: toString()
