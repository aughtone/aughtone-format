package io.github.aughtone.readable.metrics

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.units.MetricPrefix
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.math.abs
import kotlin.math.pow

/**
 * Formats a raw byte count into a human-readable data size string using binary scaling (IEC).
 * e.g., 1024 -> "1.0 KiB", 1048576 -> "1.0 MiB".
 */
fun Long.toReadableDataSize(
    unit: UnitOfMeasure = UnitOfMeasure.Byte,
    locale: Locale = Locale.current,
    precision: Int = 1
): String {
    val absoluteValue = abs(this).toDouble()
    val formatter = numberFormatterFor(locale, precision)

    if (absoluteValue < 1024.0) {
        return "${formatter(this.toDouble())} ${unit.symbol}"
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
        ?: return "${formatter(this.toDouble())} ${unit.symbol}"

    val scaledValue = this / 1024.0.pow(factor)
    
    // For binary scaling, we use the IEC symbol (Ki, Mi, Gi...)
    val binarySymbol = if (prefix == MetricPrefix.Kilo) "Ki" else "${prefix.symbol}i"
    
    return "${formatter(scaledValue)} $binarySymbol${unit.symbol}"
}
