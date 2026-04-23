package io.github.aughtone.readable.duration

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration

/**
 * Formats a [Duration] into a localized human-readable string by scaling to the most significant unit.
 * e.g., 5.seconds -> "5 seconds", 7.days -> "1 week", 11.days -> "2 weeks".
 */
fun Duration.toReadableString(locale: Locale = currentNativeLocale()): String {
    val formatter = durationFormatterFor(locale)
    return formatter(this)
}
