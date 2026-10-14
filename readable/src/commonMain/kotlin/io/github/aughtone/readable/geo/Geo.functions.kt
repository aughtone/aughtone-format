package io.github.aughtone.readable.geo

import io.github.aughtone.readable.metrics.formatReadableMetric
import io.github.aughtone.readable.quantitative.CoordinateFormat
import io.github.aughtone.readable.quantitative.formatAzimuth
import io.github.aughtone.types.geo.GeoPoint
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.quantitative.Altitude
import io.github.aughtone.types.quantitative.Azimuth
import io.github.aughtone.types.units.UnitOfMeasure

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

/**
 * Formats this [GeoPoint] into a localized human-readable string.
 *
 * Supports two main formats:
 * - [CoordinateFormat.DecimalDegrees]: e.g., `"40.7128° N, 74.006° W"`
 * - [CoordinateFormat.DegreesMinutesSeconds]: e.g., `"40° 42' 46\" N, 74° 0' 21\" W"`
 *
 * @param format The desired coordinate format (defaults to [CoordinateFormat.DecimalDegrees]).
 * @param locale The locale defining the formatting rules and direction indicators (defaults to [Locale.current]).
 * @return A localized human-readable coordinates string.
 */
fun GeoPoint.formatReadable(
    format: CoordinateFormat = CoordinateFormat.DecimalDegrees,
    locale: Locale = Locale.current
): String {
    return when (format) {
        CoordinateFormat.DecimalDegrees -> formatDecimalDegrees(locale)
        CoordinateFormat.DegreesMinutesSeconds -> formatDegreesMinutesSeconds(locale)
    }
}

/**
 * Formats this [GeoPoint] into a localized string using the decimal degrees format.
 *
 * For example, a coordinate pair might be formatted as `"40.7128° N, 74.006° W"`.
 * The latitude and longitude are formatted to 4 decimal places, and the cardinal
 * directions are localized according to the provided [locale].
 *
 * @param locale The locale defining the formatting rules and direction indicators.
 * @return A localized string representation in decimal degrees format.
 */
fun GeoPoint.formatDecimalDegrees(locale: Locale): String =
    io.github.aughtone.readable.quantitative.formatDecimalDegrees(latitude, longitude, locale)


/**
 * Converts this [GeoPoint] into a localized Degrees, Minutes, Seconds (DMS) string.
 *
 * Each component (latitude and longitude) is formatted with its corresponding
 * cardinal direction suffix based on the provided [locale]. Seconds are whole and truncated, not
 * rounded, so 21.6 seconds reads as 21.
 *
 * @param locale The locale defining the formatting rules and direction indicators.
 * @return A localized string representation in DMS format (e.g., "40° 42' 46\" N, 74° 0' 21\" W").
 */
fun GeoPoint.formatDegreesMinutesSeconds(locale: Locale): String =
    io.github.aughtone.readable.quantitative.formatDegreesMinutesSeconds(latitude, longitude, locale)
