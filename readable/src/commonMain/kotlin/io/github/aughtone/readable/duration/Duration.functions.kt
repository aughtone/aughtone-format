package io.github.aughtone.readable.duration

import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration

import io.github.aughtone.readable.relative.RelativeStyle


/**
 * Formats a [Duration] into a localized human-readable string by scaling to the most significant unit.
 *
 * The value is rounded to a whole number of that unit, and a value that rounds up to a whole
 * larger unit is shown as that unit: 59.6 seconds is "1 minute", not "60 seconds". The sign is
 * dropped, so a negative duration reads as its magnitude. Anything under half a second, which
 * would round to zero seconds, reads as the language's word for "now" ("now", "jetzt",
 * "maintenant"). The unit word takes the plural form the language requires for the count.
 *
 * Examples:
 * ```kotlin
 * 5.seconds.formatReadable() // "5 seconds"
 * 7.days.formatReadable()    // "1 week"
 * 11.days.formatReadable(style = RelativeStyle.Short) // "2w"
 * 5.minutes.formatReadable(style = RelativeStyle.Short, locale = Locales.German) // "5 Min."
 * 5.days.formatReadable(locale = Locales.Czech)       // "5 dní"
 * 200.milliseconds.formatReadable()                   // "now"
 * ```
 *
 * @param style The verbosity style of the output (defaults to [RelativeStyle.Long]).
 *   [RelativeStyle.Short] abbreviates the unit: English writes "5m", other languages CLDR's short
 *   form ("5 Min.", "5 min"). Inuktitut has no short form and writes its full word.
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
