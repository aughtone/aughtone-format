package io.github.aughtone.readable.geo

import io.github.aughtone.readable.metrics.toReadableMetric
import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale
import io.github.aughtone.types.quantitative.Altitude
import io.github.aughtone.types.quantitative.Azimuth
import io.github.aughtone.types.quantitative.Coordinates
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs

/**
 * Formats an [Altitude] into a localized metric string with automatic scaling.
 */
fun Altitude.formatReadable(locale: Locale = currentNativeLocale(), precision: Int = 1): String {
    return meters.toReadableMetric(UnitOfMeasure.Meter, locale, precision)
}

/**
 * Formats an [Azimuth] into a localized string with degrees and cardinal direction.
 * e.g., "90° (E)"
 */
fun Azimuth.formatReadable(locale: Locale = currentNativeLocale(), precision: Int = 0): String {
    val formatter = numberFormatterFor(locale, precision)
    val cardinal = getCardinalDirection(degrees, locale)
    return "${formatter(degrees)}° ($cardinal)"
}

/**
 * Formats [Coordinates] into a localized human-readable string.
 */
fun Coordinates.formatReadable(
    format: CoordinateFormat = CoordinateFormat.DecimalDegrees,
    locale: Locale = currentNativeLocale()
): String {
    return when (format) {
        CoordinateFormat.DecimalDegrees -> toDecimalDegrees(locale)
        CoordinateFormat.DegreesMinutesSeconds -> toDMS(locale)
    }
}

private fun Coordinates.toDecimalDegrees(locale: Locale): String {
    val formatter = numberFormatterFor(locale, 4)
    val directions = GeoResources.cardinalDirectionsFor(locale)
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
    
    val directions = GeoResources.cardinalDirectionsFor(locale)
    val direction = if (isLatitude) {
        if (value >= 0) directions[0] else directions[4] // N or S
    } else {
        if (value >= 0) directions[2] else directions[6] // E or W
    }
    
    val formatter = numberFormatterFor(locale, 0)
    return "${formatter(degrees.toDouble())}° ${formatter(minutes.toDouble())}' ${formatter(seconds.toDouble())}\" $direction"
}

private fun getCardinalDirection(degrees: Double, locale: Locale): String {
    val directions = GeoResources.cardinalDirectionsFor(locale)
    val index = ((degrees + 22.5) / 45.0).toInt() % 8
    return directions[index]
}
