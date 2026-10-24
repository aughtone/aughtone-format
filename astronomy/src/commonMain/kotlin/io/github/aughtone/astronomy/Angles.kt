package io.github.aughtone.astronomy

import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.time.Instant

/*
 * Angle and time helpers shared by the sun and moon calculations. Angles are in degrees
 * throughout, as the published algorithms give them.
 */

private const val RADIANS_PER_DEGREE = PI / 180.0

internal fun sinD(degrees: Double): Double = sin(degrees * RADIANS_PER_DEGREE)
internal fun cosD(degrees: Double): Double = cos(degrees * RADIANS_PER_DEGREE)
internal fun tanD(degrees: Double): Double = tan(degrees * RADIANS_PER_DEGREE)
internal fun asinD(value: Double): Double = asin(value) / RADIANS_PER_DEGREE
internal fun acosD(value: Double): Double = acos(value) / RADIANS_PER_DEGREE
internal fun atan2D(y: Double, x: Double): Double = atan2(y, x) / RADIANS_PER_DEGREE

/** [degrees] brought into the range 0 to 360. */
internal fun normalizeDegrees(degrees: Double): Double = ((degrees % 360.0) + 360.0) % 360.0

internal const val MILLIS_PER_DAY = 86_400_000L

/** The Julian day of [instant], counting days and fractions from noon on 1 January 4713 BC. */
internal fun julianDay(instant: Instant): Double = instant.toEpochMilliseconds() / MILLIS_PER_DAY.toDouble() + 2_440_587.5

/** The instant of Julian day [jd]. */
internal fun instantOfJulianDay(jd: Double): Instant =
    Instant.fromEpochMilliseconds(((jd - 2_440_587.5) * MILLIS_PER_DAY).toLong())

/** Julian centuries from the J2000.0 epoch to Julian day [jd]. */
internal fun julianCentury(jd: Double): Double = (jd - 2_451_545.0) / 36_525.0
