package io.github.aughtone.readable.geo

import io.github.aughtone.readable.metrics.toReadableMetric
import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.quantitative.Altitude
import io.github.aughtone.types.quantitative.Azimuth
import io.github.aughtone.types.quantitative.Coordinates
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs

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
    return meters.toReadableMetric(UnitOfMeasure.Meter, locale, precision)
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
fun Azimuth.formatReadable(locale: Locale = Locale.current, precision: Int = 0): String {
    val formatter = numberFormatterFor(locale, precision)
    val cardinal = getCardinalDirection(degrees, locale)
    return "${formatter(degrees)}° ($cardinal)"
}

/**
 * Formats these [Coordinates] into a localized human-readable string.
 *
 * Supports two main formats:
 * - [CoordinateFormat.DecimalDegrees]: e.g., `"40.7128° N, 74.0060° W"`
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
        CoordinateFormat.DecimalDegrees -> toDecimalDegrees(locale)
        CoordinateFormat.DegreesMinutesSeconds -> toDMS(locale)
    }
}

private fun Coordinates.toDecimalDegrees(locale: Locale): String {
    val formatter = numberFormatterFor(locale, 4)
    val directions = cardinalDirectionsFor(locale)
    val latDir = if (latitude >= 0) directions[0] else directions[4] // N or S
    val lonDir = if (longitude >= 0) directions[2] else directions[6] // E or W
    
    return "${formatter(abs(latitude))}° $latDir, ${formatter(abs(longitude))}° $lonDir"
}

private fun Coordinates.toDMS(locale: Locale): String {
    val latStr = formatDMS(latitude, true, locale)
    val lonStr = formatDMS(longitude, false, locale)
    return "$latStr, $lonStr"
}

private fun formatDMS(value: Double, isLatitude: Boolean, locale: Locale): String {
    val absolute = abs(value)
    val degrees = absolute.toInt()
    val minutesDouble = (absolute - degrees) * 60.0
    val minutes = minutesDouble.toInt()
    val seconds = ((minutesDouble - minutes) * 60.0).toInt()
    
    val directions = cardinalDirectionsFor(locale)
    val direction = if (isLatitude) {
        if (value >= 0) directions[0] else directions[4] // N or S
    } else {
        if (value >= 0) directions[2] else directions[6] // E or W
    }
    
    val formatter = numberFormatterFor(locale, 0)
    return "${formatter(degrees.toDouble())}° ${formatter(minutes.toDouble())}' ${formatter(seconds.toDouble())}\" $direction"
}

private fun getCardinalDirection(degrees: Double, locale: Locale): String {
    val directions = cardinalDirectionsFor(locale)
    val index = ((degrees + 22.5) / 45.0).toInt() % 8
    return directions[index]
}
