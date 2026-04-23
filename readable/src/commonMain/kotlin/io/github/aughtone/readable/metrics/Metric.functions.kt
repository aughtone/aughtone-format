package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale
import io.github.aughtone.types.quantitative.Distance
import io.github.aughtone.types.quantitative.Speed
import io.github.aughtone.types.units.MetricPrefix
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs
import kotlin.math.pow

/**
 * Formats a [Distance] into a localized metric string with automatic scaling (e.g., 1500m -> "1.5km").
 */
fun Distance.formatReadable(locale: Locale = currentNativeLocale(), precision: Int = 1): String {
    return meters.toReadableMetric(UnitOfMeasure.Meter, locale, precision)
}

/**
 * Formats a [Speed] into a localized metric string.
 */
fun Speed.formatReadable(locale: Locale = currentNativeLocale(), precision: Int = 1): String {
    return mps.toReadableMetric(UnitOfMeasure.MeterPerSecond, locale, precision)
}

/**
 * Generic extension to format a numeric value with a unit and automatic metric scaling.
 */
fun Double.toReadableMetric(
    baseUnit: UnitOfMeasure,
    locale: Locale = currentNativeLocale(),
    precision: Int = 1
): String {
    val absoluteValue = abs(this)
    val formatter = numberFormatterFor(locale, precision)

    // Only scale SI-compatible units (basic set for now)
    val siBaseUnits = listOf(
        UnitOfMeasure.Meter,
        UnitOfMeasure.Gram,
        UnitOfMeasure.Watt,
        UnitOfMeasure.Hertz,
        UnitOfMeasure.Ampere,
        UnitOfMeasure.Volt,
        UnitOfMeasure.Joule
    )

    if (baseUnit !in siBaseUnits || absoluteValue == 0.0) {
        return "${formatter(this)} ${baseUnit.symbol}"
    }

    // Only scale if we are outside the [1, 1000) range
    if (absoluteValue >= 1.0 && absoluteValue < 1000.0) {
        return "${formatter(this)} ${baseUnit.symbol}"
    }

    // Find the largest prefix that is <= absoluteValue
    val prefix = MetricPrefix.entries
        .filter { it.exponent % 3 == 0 } // Standard k, M, G, T, m, μ, n...
        .sortedByDescending { it.exponent }
        .find { 10.0.pow(it.exponent) <= absoluteValue }

    return if (prefix != null && prefix.exponent != 0) {
        val scaledValue = this / 10.0.pow(prefix.exponent)
        "${formatter(scaledValue)} ${prefix.symbol}${baseUnit.symbol}"
    } else {
        "${formatter(this)} ${baseUnit.symbol}"
    }
}
