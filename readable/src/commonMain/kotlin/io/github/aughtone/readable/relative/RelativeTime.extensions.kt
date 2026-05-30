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

/**
 * Formats this [Instant] as a localized, human-readable relative string with automatic fallback.
 *
 * For example: "5 minutes ago", "Yesterday", or a full date if the difference exceeds [relativeThreshold].
 *
 * Examples:
 * ```kotlin
 * val now = Clock.System.now()
 * (now - 5.minutes).toReadableRelative() // "5 minutes ago"
 * (now + 1.days).toReadableRelative()    // "Tomorrow"
 * (now - 10.days).toReadableRelative()   // "May 3, 2026" (fallback to absolute)
 * ```
 *
 * @param now The reference instant to compare against (defaults to current system time).
 * @param relativeDateStyle The style for date-based units (defaults to [RelativeStyle.Long]).
 * @param relativeTimeStyle The style for time-based units (defaults to [RelativeStyle.Long]).
 * @param dateStyle The fallback style for the date if [relativeThreshold] is exceeded.
 * @param timeStyle The fallback style for the time if [relativeThreshold] is exceeded.
 * @param relativeThreshold The duration beyond which to use absolute formatting (defaults to 3 days).
 * @param nowThreshold Values within this duration produce the "just now" string (defaults to 1 minute).
 * @param locale The locale for localization rules (defaults to [Locale.current]).
 * @param timeZone The timezone used for day boundary calculations (defaults to system default).
 * @return A localized relative or absolute time string.
 */
fun Instant.formatReadableRelative(
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

@Deprecated(
    message = "Use formatReadableRelative instead",
    replaceWith = ReplaceWith("formatReadableRelative(now, relativeDateStyle, relativeTimeStyle, dateStyle, timeStyle, relativeThreshold, nowThreshold, locale, timeZone)")
)
fun Instant.toReadableRelative(
    now: Instant = Clock.System.now(),
    relativeDateStyle: RelativeStyle = RelativeStyle.Long,
    relativeTimeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = formatReadableRelative(
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
 * Formats this [LocalDateTime] as a localized, human-readable relative string.
 *
 * This is a convenience wrapper around [Instant.formatReadableRelative].
 *
 * Examples:
 * ```kotlin
 * val now = Clock.System.now()
 * localDateTime.formatReadableRelative(now = now) // "2 hours ago"
 * ```
 *
 * @param now The reference instant to compare against (defaults to current system time).
 * @param relativeDateStyle The style for date-based units (defaults to [RelativeStyle.Long]).
 * @param relativeTimeStyle The style for time-based units (defaults to [RelativeStyle.Long]).
 * @param dateStyle The fallback style for the date if [relativeThreshold] is exceeded.
 * @param timeStyle The fallback style for the time if [relativeThreshold] is exceeded.
 * @param relativeThreshold The duration beyond which to use absolute formatting (defaults to 3 days).
 * @param nowThreshold Values within this duration produce the "just now" string (defaults to 1 minute).
 * @param locale The locale for localization rules (defaults to [Locale.current]).
 * @param timeZone The timezone used to convert this [LocalDateTime] to an [Instant].
 * @return A localized relative or absolute time string.
 */
fun LocalDateTime.formatReadableRelative(
    now: Instant = Clock.System.now(),
    relativeDateStyle: RelativeStyle = RelativeStyle.Long,
    relativeTimeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = toInstant(timeZone).formatReadableRelative(
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

@Deprecated(
    message = "Use formatReadableRelative instead",
    replaceWith = ReplaceWith("formatReadableRelative(now, relativeDateStyle, relativeTimeStyle, dateStyle, timeStyle, relativeThreshold, nowThreshold, locale, timeZone)")
)
fun LocalDateTime.toReadableRelative(
    now: Instant = Clock.System.now(),
    relativeDateStyle: RelativeStyle = RelativeStyle.Long,
    relativeTimeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = formatReadableRelative(
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
 *
 * Examples:
 * ```kotlin
 * val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
 * (today + 1.days).formatReadableRelative() // "Tomorrow"
 * (today - 5.days).formatReadableRelative() // "May 8, 2026" (fallback to absolute)
 * ```
 *
 * @param now The reference date to compare against (defaults to today in system default timezone).
 * @param relativeStyle The style for the relative output (defaults to [RelativeStyle.Long]).
 * @param dateStyle The fallback style for the date if [relativeThreshold] is exceeded.
 * @param relativeThreshold The duration beyond which to use absolute formatting (defaults to 3 days).
 * @param nowThreshold Days within this duration produce the "Today" or "Recently" string (defaults to 1 day).
 * @param locale The locale for localization rules (defaults to [Locale.current]).
 * @return A localized relative or absolute date string.
 */
fun LocalDate.formatReadableRelative(
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

@Deprecated(
    message = "Use formatReadableRelative instead",
    replaceWith = ReplaceWith("formatReadableRelative(now, relativeStyle, dateStyle, relativeThreshold, nowThreshold, locale)")
)
fun LocalDate.toReadableRelative(
    now: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    relativeStyle: RelativeStyle = RelativeStyle.Long,
    dateStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.days,
    nowThreshold: Duration = 1.days,
    locale: Locale = Locale.current,
): String = formatReadableRelative(
    now = now,
    relativeStyle = relativeStyle,
    dateStyle = dateStyle,
    relativeThreshold = relativeThreshold,
    nowThreshold = nowThreshold,
    locale = locale
)

/**
 * Formats this [LocalTime] as a localized, human-readable relative string.
 *
 * Examples:
 * ```kotlin
 * val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
 * (now - 15.minutes).formatReadableRelative() // "15 minutes ago"
 * (now + 5.hours).formatReadableRelative()    // "5:30 PM" (fallback to absolute)
 * ```
 *
 * @param now The reference time to compare against (defaults to current system time).
 * @param relativeStyle The style for the relative output (defaults to [RelativeStyle.Long]).
 * @param timeStyle The fallback style for the time if [relativeThreshold] is exceeded.
 * @param relativeThreshold The duration beyond which to use absolute formatting (defaults to 3 hours).
 * @param nowThreshold Times within this duration produce the "just now" string (defaults to 1 minute).
 * @param locale The locale for localization rules (defaults to [Locale.current]).
 * @return A localized relative or absolute time string.
 */
fun LocalTime.formatReadableRelative(
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

@Deprecated(
    message = "Use formatReadableRelative instead",
    replaceWith = ReplaceWith("formatReadableRelative(now, relativeStyle, timeStyle, relativeThreshold, nowThreshold, locale)")
)
fun LocalTime.toReadableRelative(
    now: LocalTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time,
    relativeStyle: RelativeStyle = RelativeStyle.Long,
    timeStyle: DateTimeStyle = DateTimeStyle.Medium,
    relativeThreshold: Duration = 3.hours,
    nowThreshold: Duration = 1.minutes,
    locale: Locale = Locale.current,
): String = formatReadableRelative(
    now = now,
    relativeStyle = relativeStyle,
    timeStyle = timeStyle,
    relativeThreshold = relativeThreshold,
    nowThreshold = nowThreshold,
    locale = locale
)

@Deprecated("This version doesn't allow the readable to fall back to using a full date/time. Use formatReadableRelative instead.")
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

@Suppress("DEPRECATION")
@Deprecated("This version doesn't allow the readable to fall back to using a full date/time. Use formatReadableRelative instead.")
fun LocalDateTime.toReadableRelative(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    locale: Locale = Locale.current,
    dateStyle: RelativeStyle = RelativeStyle.Long,
    timeStyle: RelativeStyle = RelativeStyle.Long,
    now: Instant = Clock.System.now(),
    nowThreshold: Duration = 1.minutes,
): String = toInstant(timeZone).toReadableRelative(
    locale = locale,
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    now = now,
    nowThreshold = nowThreshold,
    timeZone = timeZone
)

@Deprecated("This version doesn't allow the readable to fall back to using a full date. Use formatReadableRelative instead.")
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

@Deprecated("This version doesn't allow the readable to fall back to using a full time. Use formatReadableRelative instead.")
fun LocalTime.toReadableRelative(
    locale: Locale = Locale.current,
    style: RelativeStyle = RelativeStyle.Long,
    now: LocalTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time,
): String {
    val deltaSeconds = this.toSecondOfDay() - now.toSecondOfDay()
    return relativeTimeConfigFor(locale, style).formatter(deltaSeconds.seconds, false)
}
