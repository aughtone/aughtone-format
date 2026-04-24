package io.github.aughtone.readable.relative

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.format
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

fun Instant.readableRelative(
    now: Instant = Clock.System.now(),
    relativeDateStyle: RelativeStyle = RelativeStyle.Long,
    relativeTimeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String {
    val delta = this - now
    if (delta.absoluteValue > relativeThreshold) {
        return this.format(dateStyle = dateStyle, timeStyle = timeStyle, locale = locale, timeZone = timeZone)
    }

    if (delta.absoluteValue < nowThreshold) {
        return relativeTimeConfigFor(
            locale = locale,
            relativeStyle = if (relativeTimeStyle != RelativeStyle.None) relativeTimeStyle else relativeDateStyle
        ).nowString
    }

    val thisDate = this.toLocalDateTime(timeZone).date
    val nowDate = now.toLocalDateTime(timeZone).date
    val daysDelta = nowDate.daysUntil(thisDate)

    // Determine if we should use date units or time units
    val absSeconds = delta.absoluteValue.toDouble(kotlin.time.DurationUnit.SECONDS)
    val useDateUnits = when {
        relativeDateStyle == RelativeStyle.None -> false
        relativeTimeStyle == RelativeStyle.None -> true
        else -> daysDelta != 0 || absSeconds >= 86400
    }

    if (useDateUnits && daysDelta in -1..1) {
        val config = relativeTimeConfigFor(locale, relativeStyle = relativeTimeStyle)
        return when (daysDelta) {
            0 -> config.todayString
            1 -> config.tomorrowString
            -1 -> config.yesterdayString
            else -> config.nowString // Should not happen
        }
    }

    val style = if (useDateUnits) relativeDateStyle else relativeTimeStyle
    val config = relativeTimeConfigFor(locale = locale, relativeStyle = style)
    return if (useDateUnits) config.formatter(delta, true) else config.formatter(delta, false)
}

fun LocalDateTime.readableRelative(
    now: Instant = Clock.System.now(),
    relativeDateStyle: RelativeStyle = RelativeStyle.Long,
    relativeTimeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = toInstant(timeZone).readableRelative(
    now = now,
    relativeDateStyle = relativeDateStyle,
    relativeTimeStyle = relativeTimeStyle,
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    relativeThreshold = relativeThreshold,
    nowThreshold = nowThreshold,
    locale = locale,
    timeZone = timeZone
)

/**
 * Formats this [LocalDate] as a localized, human-readable relative string.
 */
fun LocalDate.readableRelative(
    now: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    relativeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.days,
    locale: Locale = Locale.current,
): String {
    val deltaDays = now.daysUntil(this)
    val absDeltaDays = deltaDays.days.absoluteValue
    if (absDeltaDays > relativeThreshold) {
        return this.format(dateStyle = dateStyle, locale = locale)
    }

    val config = relativeTimeConfigFor(locale = locale, relativeStyle = relativeStyle)
    if (absDeltaDays < nowThreshold) {
        return if (deltaDays == 0) config.todayString else config.recentlyString
    }

    return when (deltaDays) {
        0 -> config.todayString
        1 -> config.tomorrowString
        -1 -> config.yesterdayString
        else -> config.formatter(deltaDays.days, true)
    }
}

/**
 * Formats this [LocalTime] as a localized, human-readable relative string.
 */
fun LocalTime.readableRelative(
    now: LocalTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time,
    relativeStyle: RelativeStyle = RelativeStyle.Long,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.hours,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
): String {
    val deltaSeconds = this.toSecondOfDay() - now.toSecondOfDay()
    val deltaDuration = deltaSeconds.seconds
    if (deltaDuration.absoluteValue > relativeThreshold) {
        return this.format(timeStyle = timeStyle, locale = locale)
    }

    if (deltaDuration.absoluteValue < nowThreshold) {
        return relativeTimeConfigFor(
            locale = locale,
            relativeStyle = relativeStyle
        ).nowString
    }

    return relativeTimeConfigFor(
        locale = locale,
        relativeStyle = relativeStyle
    ).formatter(deltaDuration, false)
}

/**
 * Formats this [Instant] as a localized, human-readable relative string.
 *
 * Examples:
 * - `now - 8.minutes` → `"8 minutes ago"`
 * - `now + 2.hours`   → `"in 2 hours"`
 * - Within [nowThreshold] of [now] → `"just now"`
 *
 * @param locale        The locale for the output string. Defaults to the system locale.
 * @param dateStyle     The style for date-based units (Day, Week, Month, Year). Use [RelativeStyle.None] to suppress.
 * @param timeStyle     The style for time-based units (Second, Minute, Hour). Use [RelativeStyle.None] to suppress.
 * @param now           The reference instant to compare against. Defaults to [Clock.System.now()].
 * @param nowThreshold  Instants within this duration of [now] produce the "just now" string.
 * @param timeZone      The timezone used to determine "Today", "Tomorrow", and "Yesterday".
 */
@Deprecated("THis version doesnt allow the readable to fall back to using a full date/time.")
fun Instant.toReadableRelative(
    locale: Locale = Locale.current,
    dateStyle: RelativeStyle = RelativeStyle.Long,
    timeStyle: RelativeStyle = RelativeStyle.Long,
    now: Instant = Clock.System.now(),
    nowThreshold: Duration = 1.minutes,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String {
    val delta = this - now
    if (delta.absoluteValue < nowThreshold) {
        return relativeTimeConfigFor(
            locale,
            if (timeStyle != RelativeStyle.None) timeStyle else dateStyle
        ).nowString
    }

    val thisDate = this.toLocalDateTime(timeZone).date
    val nowDate = now.toLocalDateTime(timeZone).date
    val daysDelta = nowDate.daysUntil(thisDate)

    // Determine if we should use date units or time units
    val absSeconds = delta.absoluteValue.toDouble(kotlin.time.DurationUnit.SECONDS)
    val useDateUnits = when {
        dateStyle == RelativeStyle.None -> false
        timeStyle == RelativeStyle.None -> true
        else -> daysDelta != 0 || absSeconds >= 86400
    }

    if (useDateUnits && daysDelta in -1..1) {
        val config = relativeTimeConfigFor(locale, dateStyle)
        return when (daysDelta) {
            0 -> config.todayString
            1 -> config.tomorrowString
            -1 -> config.yesterdayString
            else -> config.nowString // Should not happen
        }
    }

    val style = if (useDateUnits) dateStyle else timeStyle
    val config = relativeTimeConfigFor(locale, style)
    return if (useDateUnits) config.formatter(delta, true) else config.formatter(delta, false)
}

/**
 * Formats this [LocalDateTime] as a localized, human-readable relative string.
 */
@Deprecated("This version doesnt allow the readable to fall back to using a full date/time.")
fun LocalDateTime.toReadableRelative(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    locale: Locale = Locale.current,
    dateStyle: RelativeStyle = RelativeStyle.Long,
    timeStyle: RelativeStyle = RelativeStyle.Long,
    now: Instant = Clock.System.now(),
    nowThreshold: Duration = 1.minutes,
): String = toInstant(timeZone).toReadableRelative(
    locale,
    dateStyle,
    timeStyle,
    now,
    nowThreshold,
    timeZone
)

/**
 * Formats this [LocalDate] as a localized, human-readable relative string.
 */
@Deprecated("This version doesnt allow the readable to fall back to using a full date.")
fun LocalDate.toReadableRelative(
    locale: Locale = Locale.current,
    style: RelativeStyle = RelativeStyle.Long,
    now: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
): String {
    val deltaDays = now.daysUntil(this)
    val config = relativeTimeConfigFor(locale, style)
    return when (deltaDays) {
        0 -> config.todayString
        1 -> config.tomorrowString
        -1 -> config.yesterdayString
        else -> config.formatter(deltaDays.days, true)
    }
}

/**
 * Formats this [LocalTime] as a localized, human-readable relative string.
 */
@Deprecated("THis version doesnt allow the readable to fall back to using a full time.")
fun LocalTime.toReadableRelative(
    locale: Locale = Locale.current,
    style: RelativeStyle = RelativeStyle.Long,
    now: LocalTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time,
): String {
    val deltaSeconds = this.toSecondOfDay() - now.toSecondOfDay()
    return relativeTimeConfigFor(locale, style).formatter(deltaSeconds.seconds, false)
}
