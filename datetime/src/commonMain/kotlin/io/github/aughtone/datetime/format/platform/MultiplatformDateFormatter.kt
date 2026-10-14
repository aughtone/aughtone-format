package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.FormatStyle
import io.github.aughtone.datetime.format.resources.Resources
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.time.Instant
import kotlinx.datetime.toInstant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.values.EraNames

object MultiplatformDateFormatter : InternalDateFormatter {
    override fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): String? = formatDateTime(
        localDateTime = localDateTime,
        dateStyle = dateStyle,
        timeStyle = timeStyle,
        locale = locale,
        timeZone = timeZone,
        twentyFourHour = twentyFourHour,
        eraNames = null,
        numberingSystem = null
    )

    fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
        eraNames: EraNames? = null,
        numberingSystem: NumberingSystem? = null,
    ): String? = formatDateTime(
        localDateTime, dateStyle, timeStyle, locale, timeZone, twentyFourHour, eraNames, numberingSystem,
        instant = null,
    )

    /**
     * As [formatDateTime], for a caller that knows the exact [instant] being shown.
     *
     * A local date-time alone cannot say which side of a clock change it is on: 01:30 happens
     * twice on the night clocks go back. When [instant] is given, the zone name is resolved for
     * it, so the second 01:30 reads "EST" and not "EDT". When it is `null` the local date-time is
     * resolved in [timeZone], which picks the earlier of the two.
     */
    internal fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
        eraNames: EraNames?,
        numberingSystem: NumberingSystem?,
        instant: Instant?,
    ): String? {
        val formattedDate = if (dateStyle != DateTimeStyle.None) {
            localDateTime.date.format(
                format = getLocalDateFormat(
                    dateStyle = dateStyle,
                    locale = locale,
                    timeZone = timeZone,
                    twoDigitYearBase = twoDigitYearBaseFor(localDateTime.date.year),
                )
            ).let {
                MultiplatformPostFormatter.postFormatDate(
                    dateStyle = dateStyle,
                    locale = locale,
                    date = localDateTime.date,
                    formattedDate = it,
                    eraNames = eraNames,
                    numberingSystem = numberingSystem
                )
            }
        } else null

        val formattedTime = if (timeStyle != DateTimeStyle.None) {
            localDateTime.time.format(
                format = getLocalTimeFormat(
                    timeStyle = timeStyle,
                    locale = locale,
                    timeZone = timeZone,
                    twentyFourHour = twentyFourHour
                )
            ).let {
                MultiplatformPostFormatter.postFormatTime(
                    timeStyle = timeStyle,
                    timeZone = timeZone,
                    instant = instant ?: localDateTime.toInstant(timeZone),
                    formattedTime = it,
                    locale = locale,
                    numberingSystem = numberingSystem
                )
            }
        } else null

        return when {
            formattedDate != null && formattedTime != null -> "$formattedDate $formattedTime"
            formattedDate != null -> formattedDate
            formattedTime != null -> formattedTime
            else -> ""
        }
    }

    fun getLocalDateTimeFormat(
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
        date(
            getLocalDateFormat(
                dateStyle = dateStyle,
                locale = locale,
                timeZone = timeZone,
            )
        )
        // The separator belongs between a date and a time, not beside an empty half.
        if (dateStyle != DateTimeStyle.None && timeStyle != DateTimeStyle.None) char(' ')
        time(
            getLocalTimeFormat(
                timeStyle = timeStyle,
                locale = locale,
                timeZone = timeZone,
                twentyFourHour = twentyFourHour
            )
        )
    }

    fun getLocalDateFormat(
        dateStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
    ): DateTimeFormat<LocalDate> = getLocalDateFormat(dateStyle, locale, timeZone, twoDigitYearBase = 2000)

    /**
     * As [getLocalDateFormat], for a date in the century that starts at [twoDigitYearBase].
     */
    internal fun getLocalDateFormat(
        dateStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twoDigitYearBase: Int,
    ): DateTimeFormat<LocalDate> {
        val style = when (dateStyle) {
            DateTimeStyle.Short -> FormatStyle.SHORT
            DateTimeStyle.Medium -> FormatStyle.MEDIUM
            DateTimeStyle.Long -> FormatStyle.LONG
            DateTimeStyle.Full -> FormatStyle.FULL
            DateTimeStyle.None -> return LocalDate.Format { chars("") }
        }
        return Resources.getDateFormat(locale = locale, timeZone = timeZone, style = style, twoDigitYearBase = twoDigitYearBase)
    }

    /**
     * The first year of the century [year] falls in, so that a two-digit year shows the last two
     * digits of any year from 0 to 9999. Outside that range the default window is kept.
     */
    private fun twoDigitYearBaseFor(year: Int): Int = if (year in 0..9999) year - year % 100 else 2000

    fun getLocalTimeFormat(
        timeStyle: DateTimeStyle,
        locale: Locale,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): DateTimeFormat<LocalTime> = when (timeStyle) {
        DateTimeStyle.Short -> Resources.getTimeFormat(
            locale = locale,
            timeZone = timeZone,
            style = FormatStyle.SHORT,
            twentyFourHour = twentyFourHour
        )

        DateTimeStyle.Medium ->
            Resources.getTimeFormat(
                locale = locale,
                timeZone = timeZone,
                style = FormatStyle.MEDIUM,
                twentyFourHour = twentyFourHour
            )

        DateTimeStyle.Long ->
            Resources.getTimeFormat(
                locale = locale,
                timeZone = timeZone,
                style = FormatStyle.LONG,
                twentyFourHour = twentyFourHour
            )

        DateTimeStyle.Full ->
            Resources.getTimeFormat(
                locale = locale,
                timeZone = timeZone,
                style = FormatStyle.FULL,
                twentyFourHour = twentyFourHour
            )

        DateTimeStyle.None -> LocalTime.Format { chars("") }
    }
}
