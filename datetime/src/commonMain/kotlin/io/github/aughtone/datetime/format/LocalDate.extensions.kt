@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.values.EraNames

/**
 * Formats this date at [dateStyle] in [locale].
 *
 * No style includes the era: pass [showEra] for it ("Saturday, January 12, 1952 CE"). A date
 * before year 1 always shows its era, with the year counted from one ("January 1, 501 BCE"),
 * because the year alone would read as one of the common era.
 *
 * @param dateStyle [DateTimeStyle] The style to use for the date.
 * @param locale [Locale] The locale to use for formatting. Defaults to [Locale.current].
 * @param timeZone [TimeZone] The time zone to use for formatting. Defaults to [TimeZone.currentSystemDefault].
 * @param is24HourFormat [Boolean] Whether to use 24 hour clock or not. Defaults to [is24HourFormat].
 * @param numberingSystem [NumberingSystem] Optional numbering system to use for digits.
 * @param showEra [Boolean] Whether to follow the date with its era, the locale's abbreviation
 *   ("CE", "BCE") unless [eraNames] gives other words. Defaults to `false`.
 * @param eraNames [EraNames] Optional words for the eras; giving them also shows the era.
 * @param separators [Separators] The spaces inside the value: [Separators.Ascii], the default,
 *   writes every space as an ordinary one, U+0020, which a line can break at; [Separators.Typographic]
 *   writes them as no-break spaces (U+00A0, and U+202F for a narrow one), so the value never wraps.
 * @return [String] The formatted date.
 */
fun LocalDate.format(
    dateStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
    showEra: Boolean = false,
    eraNames: EraNames? = null,
    separators: Separators = Separators.Ascii,
): String = (MultiplatformDateFormatter.formatDateTime(
    // The date itself, at midnight: no trip through the zone, which would land on the next day
    // wherever local midnight does not exist (a skipped day, or a clock change at midnight).
    localDateTime = LocalDateTime(this, LocalTime(0, 0)),
    dateStyle = dateStyle,
    timeStyle = DateTimeStyle.None,
    locale = locale,
    timeZone = timeZone,
    twentyFourHour = is24HourFormat,
    eraNames = eraNames,
    numberingSystem = numberingSystem,
    instant = null,
    showEra = showEra,
) ?: toString()).withSeparators(separators)

/** The 4.3 signature, kept so code compiled against it still links. */
@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun LocalDate.format(
    dateStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
    showEra: Boolean = false,
    eraNames: EraNames? = null,
): String = format(
    dateStyle = dateStyle,
    locale = locale,
    timeZone = timeZone,
    is24HourFormat = is24HourFormat,
    numberingSystem = numberingSystem,
    showEra = showEra,
    eraNames = eraNames,
    separators = Separators.Ascii,
)

/** The 4.1 signature, kept so code compiled against it still links. */
@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun LocalDate.format(
    dateStyle: DateTimeStyle,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    is24HourFormat: Boolean = is24HourFormat(locale = locale),
    numberingSystem: NumberingSystem? = null,
): String = format(dateStyle, locale, timeZone, is24HourFormat, numberingSystem, showEra = false)
