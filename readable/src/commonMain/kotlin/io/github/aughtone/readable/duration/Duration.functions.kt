package io.github.aughtone.readable.duration

import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration

import io.github.aughtone.readable.relative.RelativeStyle

/**
 * Formats a [Duration] into a localized human-readable string by scaling to the most significant unit.
 * e.g., 5.seconds -> "5 seconds", 7.days -> "1 week", 11.days -> "2 weeks".
 */
fun Duration.toReadableString(
    locale: Locale = Locale.current,
    style: RelativeStyle = RelativeStyle.Long,
): String {
    val formatter = durationFormatterFor(locale, style)
    return formatter(this, style)
}
