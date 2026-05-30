package io.github.aughtone.readable.duration

import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration

import io.github.aughtone.readable.relative.RelativeStyle


/**
 * Formats a [Duration] into a localized human-readable string by scaling to the most significant unit.
 *
 * Examples:
 * ```kotlin
 * 5.seconds.formatReadable() // "5 seconds"
 * 7.days.formatReadable()    // "1 week"
 * 11.days.formatReadable(style = RelativeStyle.Short) // "2w"
 * ```
 *
 * @param style The verbosity style of the output (defaults to [RelativeStyle.Long]).
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @return A localized human-readable duration string.
 */
fun Duration.formatReadable(
    style: RelativeStyle = RelativeStyle.Long,
    locale: Locale = Locale.current,
): String {
    val formatter = durationFormatterFor(locale, style)
    return formatter(this, style)
}

/**
 * Formats a [Duration] into a localized human-readable string by scaling to the most significant unit.
 *
 * Examples:
 * ```kotlin
 * 5.seconds.toReadableString() // "5 seconds"
 * 7.days.toReadableString()    // "1 week"
 * 11.days.toReadableString(style = RelativeStyle.Short) // "2w"
 * ```
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param style The verbosity style of the output (defaults to [RelativeStyle.Long]).
 * @return A localized human-readable duration string.
 */
@Deprecated(
    message = "Use formatReadable instead",
    replaceWith = ReplaceWith("formatReadable(style, locale)")
)
fun Duration.toReadableString(
    locale: Locale = Locale.current,
    style: RelativeStyle = RelativeStyle.Long,
): String = formatReadable(style = style, locale = locale)
