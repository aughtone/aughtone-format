package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.quantitative.Distance
import io.github.aughtone.types.quantitative.Speed
import io.github.aughtone.types.units.MetricPrefix
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs
import kotlin.math.pow

/**
 * Formats a [Distance] into a localized metric string with automatic scaling (e.g., 1500m -> "1.5 km").
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized human-readable distance string.
 */
fun Distance.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String {
    return meters.formatReadableMetric(UnitOfMeasure.Meter, locale, precision)
}

/**
 * Formats a [Speed] into a localized metric string (e.g., 10 m/s -> "10 m/s").
 *
 * @param locale The locale defining the formatting rules (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized human-readable speed string.
 */
fun Speed.formatReadable(locale: Locale = Locale.current, precision: Int = 1): String {
    return mps.formatReadableMetric(UnitOfMeasure.MeterPerSecond, locale, precision)
}

/**
 * Generic extension to format a [Double] value with a unit and automatic metric scaling.
 *
 * Only scales standard SI-compatible units (Meter, Gram, Watt, etc.) by default.
 *
 * Example:
 * ```kotlin
 * 1500.0.toReadableMetric(UnitOfMeasure.Meter) // "1.5 km"
 * 500.0.toReadableMetric(UnitOfMeasure.Watt)   // "500 W"
 * ```
 *
 * @param unit The base unit of measure (e.g., [UnitOfMeasure.Meter]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized human-readable metric string.
 */
fun Double.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
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

    if (unit !in siBaseUnits || absoluteValue == 0.0) {
        return "${formatter(this)} ${unit.symbol}"
    }

    // Only scale if we are outside the [1, 1000) range
    if (absoluteValue >= 1.0 && absoluteValue < 1000.0) {
        return "${formatter(this)} ${unit.symbol}"
    }

    // Find the largest prefix that is <= absoluteValue
    val prefix = MetricPrefix.entries
        .filter { it.exponent % 3 == 0 } // Standard k, M, G, T, m, μ, n...
        .sortedByDescending { it.exponent }
        .find { 10.0.pow(it.exponent) <= absoluteValue }

    return if (prefix != null && prefix.exponent != 0) {
        val scaledValue = this / 10.0.pow(prefix.exponent)
        "${formatter(scaledValue)} ${prefix.symbol}${unit.symbol}"
    } else {
        "${formatter(this)} ${unit.symbol}"
    }
}

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun Double.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [Float] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Float.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun Float.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [Long] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Long.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun Long.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [Int] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Int.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun Int.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [Short] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Short.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun Short.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [Byte] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Byte.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun Byte.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [ULong] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun ULong.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun ULong.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [UInt] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UInt.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun UInt.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [UShort] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UShort.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun UShort.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)

/**
 * Formats this [UByte] value with a unit and automatic metric scaling.
 *
 * @param unit The base unit of measure.
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UByte.formatReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableMetric(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableMetric instead",
    replaceWith = ReplaceWith("formatReadableMetric(unit, locale, precision)")
)
fun UByte.toReadableMetric(
    unit: UnitOfMeasure,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableMetric(unit, locale, precision)
