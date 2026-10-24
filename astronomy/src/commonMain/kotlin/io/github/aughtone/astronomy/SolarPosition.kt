package io.github.aughtone.astronomy

/**
 * The sun's declination and the equation of time at an instant, by the NOAA solar calculation
 * (after Meeus, *Astronomical Algorithms*), which is accurate to about a minute in the times it
 * gives at ordinary latitudes.
 *
 * @property declination The sun's declination, in degrees.
 * @property equationOfTime Apparent minus mean solar time, in minutes.
 */
internal class SolarPosition(val declination: Double, val equationOfTime: Double)

/** The sun's position at [t], in Julian centuries from J2000.0. */
internal fun solarPosition(t: Double): SolarPosition {
    val meanLongitude = normalizeDegrees(280.46646 + t * (36000.76983 + 0.0003032 * t))
    val meanAnomaly = 357.52911 + t * (35999.05029 - 0.0001537 * t)
    val eccentricity = 0.016708634 - t * (0.000042037 + 0.0000001267 * t)
    val center = sinD(meanAnomaly) * (1.914602 - t * (0.004817 + 0.000014 * t)) +
        sinD(2 * meanAnomaly) * (0.019993 - 0.000101 * t) +
        sinD(3 * meanAnomaly) * 0.000289
    val trueLongitude = meanLongitude + center
    val omega = 125.04 - 1934.136 * t
    val apparentLongitude = trueLongitude - 0.00569 - 0.00478 * sinD(omega)
    val meanObliquity = 23.0 + (26.0 + (21.448 - t * (46.815 + t * (0.00059 - t * 0.001813))) / 60.0) / 60.0
    val obliquity = meanObliquity + 0.00256 * cosD(omega)
    val declination = asinD(sinD(obliquity) * sinD(apparentLongitude))
    val y = tanD(obliquity / 2).let { it * it }
    val equationOfTime = 4.0 * (
        y * sinD(2 * meanLongitude) -
            2 * eccentricity * sinD(meanAnomaly) +
            4 * eccentricity * y * sinD(meanAnomaly) * cosD(2 * meanLongitude) -
            0.5 * y * y * sinD(4 * meanLongitude) -
            1.25 * eccentricity * eccentricity * sinD(2 * meanAnomaly)
        ).let { it * 180.0 / kotlin.math.PI }
    return SolarPosition(declination, equationOfTime)
}
