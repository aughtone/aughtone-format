package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.readable.number.prefixRungFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.quantitative.Distance
import io.github.aughtone.types.quantitative.Speed
import io.github.aughtone.types.units.MetricPrefix
import io.github.aughtone.types.units.UnitOfMeasure
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
 * Only scales standard SI-compatible units (Meter, Gram, Watt, etc.) by default. The prefix is
 * chosen so the number shown is from 1 up to, but not including, 1,000 — a value that rounds up to
 * 1,000 moves to the next prefix. Values below 1 scale down (m, μ, n, …). Zero, not-a-number and
 * the infinities are shown with the bare unit.
 *
 * Example:
 * ```kotlin
 * 1500.0.formatReadableMetric(UnitOfMeasure.Meter)  // "1.5 km"
 * 500.0.formatReadableMetric(UnitOfMeasure.Watt)    // "500 W"
 * 999.96.formatReadableMetric(UnitOfMeasure.Meter)  // "1 km", not "1,000 m"
 * 0.5.formatReadableMetric(UnitOfMeasure.Meter)     // "500 mm"
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
    val formatter = numberFormatterFor(locale, precision)
    val (symbol, divisor) = (if (unit in siBaseUnits) prefixRungFor(this, precision, metricLadder) else null)
        ?: return "${formatter(this)} ${unit.symbol}"
    return "${formatter(this / divisor)} $symbol${unit.symbol}"
}

/** The units that take an SI prefix (a basic set for now); every other unit is shown unscaled. */
private val siBaseUnits = listOf(
    UnitOfMeasure.Meter,
    UnitOfMeasure.Gram,
    UnitOfMeasure.Watt,
    UnitOfMeasure.Hertz,
    UnitOfMeasure.Ampere,
    UnitOfMeasure.Volt,
    UnitOfMeasure.Joule
)

/** The SI prefixes in steps of a thousand (q … m, none, k … Q), smallest first. */
private val metricLadder: List<Pair<String, Double>> =
    (MetricPrefix.entries.filter { it.exponent % 3 == 0 }.map { it.symbol to 10.0.pow(it.exponent) } + ("" to 1.0))
        .sortedBy { it.second }

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
