package io.github.aughtone.readable.relative

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale
import kotlinx.datetime.*
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

/**
 * Formats this [Instant] as a localized, human-readable relative time string.
 *
 * Examples:
 * - `Clock.System.now() - 134.minutes` → `"2 hours ago"`
 * - `Clock.System.now() + 8.minutes`   → `"in 8 minutes"`
 * - Within [nowThreshold] of [now]      → `"just now"` (locale-equivalent)
 *
 * @param locale        The locale for the output string. Defaults to the system locale.
 * @param now           The reference instant to compare against. Defaults to [Clock.System.now()].
 * @param nowThreshold  Instants within this duration of [now] produce the "just now" string.
 */
@OptIn(ExperimentalTime::class)
fun Instant.toReadableRelativeTime(
    locale: Locale = currentNativeLocale(),
    now: Instant = Clock.System.now(),
    nowThreshold: Duration = 5.seconds,
): String {
    val delta = this - now   // negative = past, positive = future
    val config = relativeTimeConfigFor(locale)
    return if (delta.absoluteValue < nowThreshold) {
        config.nowString
    } else {
        config.formatter(delta)
    }
}

/**
 * Formats this [LocalDateTime] as a localized, human-readable relative time string.
 * Converts to [Instant] using the provided [timeZone] before formatting.
 */
@OptIn(ExperimentalTime::class)
fun LocalDateTime.toReadableRelativeTime(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    locale: Locale = currentNativeLocale(),
    now: Instant = Clock.System.now(),
    nowThreshold: Duration = 5.seconds,
): String = toInstant(timeZone).toReadableRelativeTime(locale, now, nowThreshold)

/**
 * Formats this [LocalDate] as a localized, human-readable relative time string.
 * Uses the start of the day in the provided [timeZone] as the reference point.
 */
@OptIn(ExperimentalTime::class)
fun LocalDate.toReadableRelativeTime(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    locale: Locale = currentNativeLocale(),
    now: Instant = Clock.System.now(),
    nowThreshold: Duration = 5.seconds,
): String = atStartOfDayIn(timeZone).toReadableRelativeTime(locale, now, nowThreshold)
