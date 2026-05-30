package io.github.aughtone.readable.time

import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.relative.RelativeStyle
import io.github.aughtone.types.locale.Locale
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Instant

/**
 * Converts this [Long] (representing epoch milliseconds) into an [Instant].
 *
 * Example:
 * ```kotlin
 * 1672531200000L.toInstant() // 2023-01-01T00:00:00Z
 * ```
 */
fun Long.toInstant(): Instant = Instant.fromEpochMilliseconds(this)

/**
 * Converts this [Long] (representing milliseconds) into a [Duration].
 *
 * Example:
 * ```kotlin
 * 5000L.toDuration() // 5 seconds
 * ```
 */
fun Long.toDuration(): Duration = milliseconds

/**
 * Converts this [Long] (representing milliseconds) into a localized, human-readable duration string.
 *
 * Examples:
 * ```kotlin
 * 5000L.formatReadableDuration() // "5 seconds"
 * 600000L.formatReadableDuration(style = RelativeStyle.Short) // "10m"
 * ```
 *
 * @param style The verbosity style of the output (defaults to [RelativeStyle.Long]).
 * @param locale The locale for localization rules (defaults to [Locale.current]).
 * @return A localized human-readable duration string.
 */
fun Long.formatReadableDuration(
    style: RelativeStyle = RelativeStyle.Long,
    locale: Locale = Locale.current,
): String = milliseconds.formatReadable(style = style, locale = locale)

@Deprecated(
    message = "Use formatReadableDuration instead",
    replaceWith = ReplaceWith("formatReadableDuration(style, locale)")
)
fun Long.toReadableDuration(
    style: RelativeStyle = RelativeStyle.Long,
    locale: Locale = Locale.current,
): String = formatReadableDuration(style = style, locale = locale)
