package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.readable.number.prefixRungFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.pow

/**
 * Formats this [Double] value into a localized human-readable data size string using binary scaling (IEC).
 *
 * For example: 1024 -> "1 KiB", 1048576 -> "1 MiB", 1536 -> "1.5 KiB". Trailing zeros are
 * trimmed, so a whole number of units never shows a decimal point. A value that rounds up to 1,024
 * of one unit moves to the next, so 1,048,575 bytes is "1 MiB" rather than "1,024 KiB".
 *
 * Examples:
 * ```kotlin
 * 1024.0.formatReadableDataSize() // "1 KiB"
 * 1048576.0.formatReadableDataSize(UnitOfMeasure.Byte) // "1 MiB"
 * ```
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 * @return A localized human-readable data size string.
 */
fun Double.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String {
    val formatter = numberFormatterFor(locale, precision)
    val (symbol, divisor) = prefixRungFor(this, precision, binaryLadder)
        ?: return "${formatter(this)} ${unit.symbol}"
    return "${formatter(this / divisor)} $symbol${unit.symbol}"
}

/** No prefix, then the IEC binary prefixes in steps of 1024 (Ki, Mi, Gi, … Qi), smallest first. */
private val binaryLadder: List<Pair<String, Double>> =
    listOf("" to 1.0) + listOf(
        MetricPrefix.Kilo, MetricPrefix.Mega, MetricPrefix.Giga, MetricPrefix.Tera, MetricPrefix.Peta,
        MetricPrefix.Exa, MetricPrefix.Zetta, MetricPrefix.Yotta, MetricPrefix.Ronna, MetricPrefix.Quetta,
    ).mapIndexed { index, prefix ->
        // IEC writes kibi with a capital K, unlike the SI kilo.
        val symbol = if (prefix == MetricPrefix.Kilo) "Ki" else "${prefix.symbol}i"
        symbol to 1024.0.pow(index + 1)
    }

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun Double.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [Float] value into a human-readable data size string (e.g., 1024.0f -> "1 KiB").
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Float.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun Float.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [Long] value into a human-readable data size string (e.g., 1048576L -> "1 MiB").
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Long.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun Long.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [Int] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Int.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun Int.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [Short] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Short.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun Short.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [Byte] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun Byte.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun Byte.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [ULong] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun ULong.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun ULong.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [UInt] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UInt.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun UInt.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [UShort] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UShort.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun UShort.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)

/**
 * Formats this [UByte] value into a human-readable data size string.
 *
 * @param unit The unit of measure (defaults to [UnitOfMeasure.Byte]).
 * @param locale The locale for numeric formatting (defaults to [Locale.current]).
 * @param precision The number of decimal places to include (default is 1).
 */
fun UByte.formatReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = toDouble().formatReadableDataSize(unit, locale, precision)

@Deprecated(
    message = "Use formatReadableDataSize instead",
    replaceWith = ReplaceWith("formatReadableDataSize(unit, locale, precision)")
)
fun UByte.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String = formatReadableDataSize(unit, locale, precision)
