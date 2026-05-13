package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs
import kotlin.math.pow

/**
 * Formats this [Double] value into a human-readable data size string using binary scaling (IEC).
 *
 * Binary prefixes (KiB, MiB, GiB, etc.) use base-1024 scaling.
 * For example, `1024.0` becomes `"1.0 KiB"`.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized human-readable data size string.
 */
fun Double.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String {
    val absoluteValue = abs(this)
    val formatter = numberFormatterFor(locale, precision)

    if (absoluteValue < 1024.0) {
        return "${formatter(this)} ${unit.symbol}"
    }

    // Binary prefixes follow the same order as SI but with 2^10 steps
    // Kilo (3) -> 2^10, Mega (6) -> 2^20, Giga (9) -> 2^30...
    val prefixes = listOf(
        MetricPrefix.Quetta to 10,
        MetricPrefix.Ronna to 9,
        MetricPrefix.Yotta to 8,
        MetricPrefix.Zetta to 7,
        MetricPrefix.Exa to 6,
        MetricPrefix.Peta to 5,
        MetricPrefix.Tera to 4,
        MetricPrefix.Giga to 3,
        MetricPrefix.Mega to 2,
        MetricPrefix.Kilo to 1
    )

    val (prefix, factor) = prefixes.find { 1024.0.pow(it.second) <= absoluteValue }
        ?: return "${formatter(this)} ${unit.symbol}"

    val scaledValue = this / 1024.0.pow(factor)

    // For binary scaling, we use the IEC symbol (Ki, Mi, Gi...)
    val binarySymbol = if (prefix == MetricPrefix.Kilo) "Ki" else "${prefix.symbol}i"

    return "${formatter(scaledValue)} $binarySymbol${unit.symbol}"
}

/**
 * Formats this [Float] value into a human-readable data size string (e.g., 1024.0f -> "1.0 KiB").
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Float.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [Long] value into a human-readable data size string (e.g., 1048576L -> "1.0 MiB").
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Long.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [Int] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Int.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [Short] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Short.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [Byte] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Byte.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [ULong] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun ULong.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [UInt] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UInt.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [UShort] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UShort.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)

/**
 * Formats this [UByte] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UByte.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().toReadableDataSize(unit, locale, precision)
