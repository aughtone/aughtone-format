package io.github.aughtone.readable.quantitative

import io.github.aughtone.readable.metrics.formatReadableMetric
import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.quantitative.Altitude
import io.github.aughtone.types.quantitative.Azimuth
import io.github.aughtone.types.quantitative.Coordinates
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs
import kotlin.math.floor

/**
 * Formats this [Altitude] into a localized human-readable metric string.
 *
 * Automatically scales the value to the most appropriate SI prefix (e.g., "1500 m" becomes "1.5 km").
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized human-readable altitude string.
 */
fun Altitude.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String {
    return meters.formatReadableMetric(UnitOfMeasure.Meter, locale, precision)
}

/**
 * Formats this [Azimuth] into a localized string with degrees and cardinal direction.
 *
 * For example, an azimuth of `90.0` degrees might be formatted as `"90° (E)"`.
 * The cardinal direction is automatically localized based on the provided [locale].
 *
 * @param locale The locale defining the formatting rules and cardinal names (defaults to [Locale.current]).
 * @param precision The number of decimal places for the degree value (default is 0).
 * @return A localized human-readable azimuth string.
 */
fun Azimuth.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String =
    formatAzimuth(degrees, locale, precision)

/** Shared by the `quantitative` and `geo` azimuth formatters. */
internal fun formatAzimuth(degrees: Double, locale: Locale, precision: Int): String {
    val formatter = numberFormatterFor(locale, precision)
    val cardinal = cardinalDirectionsFor(locale)[((degrees + 22.5) / 45.0).toInt() % 8]
    // A bearing just short of a full turn rounds to 360, which is north again: show it as 0.
    val shown = formatter(degrees).let { if (it == formatter(360.0)) formatter(0.0) else it }
    return "$shown° ($cardinal)"
}

/**
 * Formats these [Coordinates] into a localized human-readable string.
 *
 * Supports two main formats:
 * - [CoordinateFormat.DecimalDegrees]: e.g., `"40.7128° N, 74.006° W"`
 * - [CoordinateFormat.DegreesMinutesSeconds]: e.g., `"40° 42' 46\" N, 74° 0' 21\" W"`
 *
 * @param format The desired coordinate format (defaults to [CoordinateFormat.DecimalDegrees]).
 * @param locale The locale defining the formatting rules and direction indicators (defaults to [Locale.current]).
 * @return A localized human-readable coordinates string.
 */
fun Coordinates.formatReadable(
    format: CoordinateFormat = CoordinateFormat.DecimalDegrees,
    locale: Locale = Locale.current
): String {
    return when (format) {
        CoordinateFormat.DecimalDegrees -> formatDecimalDegrees(locale)
        CoordinateFormat.DegreesMinutesSeconds -> formatDegreesMinutesSeconds(locale)
    }
}

/**
 * Formats these [Coordinates] into a localized string using the decimal degrees format.
 *
 * For example, a coordinate pair might be formatted as `"40.7128° N, 74.006° W"`.
 * The latitude and longitude are formatted to 4 decimal places, and the cardinal
 * directions are localized according to the provided [locale].
 *
 * @param locale The locale defining the formatting rules and direction indicators.
 * @return A localized string representation in decimal degrees format.
 */
fun Coordinates.formatDecimalDegrees(locale: Locale): String = formatDecimalDegrees(latitude, longitude, locale)

/** Shared by the [Coordinates] and `GeoPoint` decimal-degree formatters. */
internal fun formatDecimalDegrees(latitude: Double, longitude: Double, locale: Locale): String {
    val formatter = numberFormatterFor(locale, 4)
    val directions = cardinalDirectionsFor(locale)
    val latDir = if (latitude >= 0) directions[0] else directions[4] // N or S
    val lonDir = if (longitude >= 0) directions[2] else directions[6] // E or W

    return "${formatter(abs(latitude))}° $latDir, ${formatter(abs(longitude))}° $lonDir"
}


/**
 * Converts these [Coordinates] into a localized Degrees, Minutes, Seconds (DMS) string.
 *
 * Each component (latitude and longitude) is formatted with its corresponding
 * cardinal direction suffix based on the provided [locale]. Seconds are whole and truncated, not
 * rounded, so 21.6 seconds reads as 21.
 *
 * @param locale The locale defining the formatting rules and direction indicators.
 * @return A localized string representation in DMS format (e.g., "40° 42' 46\" N, 74° 0' 21\" W").
 */
fun Coordinates.formatDegreesMinutesSeconds(locale: Locale): String =
    formatDegreesMinutesSeconds(latitude, longitude, locale)

/** Shared by the [Coordinates] and `GeoPoint` degrees-minutes-seconds formatters. */
internal fun formatDegreesMinutesSeconds(latitude: Double, longitude: Double, locale: Locale): String =
    "${formatDMS(latitude, true, locale)}, ${formatDMS(longitude, false, locale)}"

private fun formatDMS(value: Double, isLatitude: Boolean, locale: Locale): String {
    // Whole seconds, truncated. The small nudge absorbs binary error, so a value that is exactly
    // 1° 9' 0" is not shown as 1° 8' 59" for sitting a hair below it.
    val totalSeconds = floor(abs(value) * 3600.0 + 1e-6).toLong()
    val degrees = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    val directions = cardinalDirectionsFor(locale)
    val direction = if (isLatitude) {
        if (value >= 0) directions[0] else directions[4] // N or S
    } else {
        if (value >= 0) directions[2] else directions[6] // E or W
    }

    return "$degrees° $minutes' $seconds\" $direction"
}
