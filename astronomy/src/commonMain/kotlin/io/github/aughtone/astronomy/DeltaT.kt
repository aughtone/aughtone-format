package io.github.aughtone.astronomy

/**
 * ΔT, Terrestrial Time minus Universal Time, in seconds, for a decimal year: the polynomial
 * expressions of Espenak and Meeus (2006). The published phase and solar-term tables are in
 * Universal Time, while the algorithms that find them run in Terrestrial Time; ΔT converts
 * between them. It is about 69 seconds in the 2020s, and its future values are predictions.
 */
internal fun deltaTSeconds(year: Double): Double {
    fun poly(t: Double, vararg c: Double): Double = c.foldRight(0.0) { coefficient, acc -> acc * t + coefficient }
    return when {
        year < -500 -> poly((year - 1820) / 100, -20.0, 0.0, 32.0)
        year < 500 -> poly(year / 100, 10583.6, -1014.41, 33.78311, -5.952053, -0.1798452, 0.022174192, 0.0090316521)
        year < 1600 -> poly((year - 1000) / 100, 1574.2, -556.01, 71.23472, 0.319781, -0.8503463, -0.005050998, 0.0083572073)
        year < 1700 -> (year - 1600).let { t -> 120 - 0.9808 * t - 0.01532 * t * t + t * t * t / 7129 }
        year < 1800 -> (year - 1700).let { t -> poly(t, 8.83, 0.1603, -0.0059285, 0.00013336) - t * t * t * t / 1_174_000 }
        year < 1860 -> poly(year - 1800, 13.72, -0.332447, 0.0068612, 0.0041116, -0.00037436, 0.0000121272, -0.0000001699, 0.000000000875)
        year < 1900 -> (year - 1860).let { t -> poly(t, 7.62, 0.5737, -0.251754, 0.01680668, -0.0004473624) + t * t * t * t * t / 233_174 }
        year < 1920 -> poly(year - 1900, -2.79, 1.494119, -0.0598939, 0.0061966, -0.000197)
        year < 1941 -> poly(year - 1920, 21.20, 0.84493, -0.076100, 0.0020936)
        year < 1961 -> (year - 1950).let { t -> 29.07 + 0.407 * t - t * t / 233 + t * t * t / 2547 }
        year < 1986 -> (year - 1975).let { t -> 45.45 + 1.067 * t - t * t / 260 - t * t * t / 718 }
        year < 2005 -> poly(year - 2000, 63.86, 0.3345, -0.060374, 0.0017275, 0.000651814, 0.00002373599)
        year < 2050 -> poly(year - 2000, 62.92, 0.32217, 0.005589)
        year < 2150 -> -20 + 32 * ((year - 1820) / 100).let { it * it } - 0.5628 * (2150 - year)
        else -> poly((year - 1820) / 100, -20.0, 0.0, 32.0)
    }
}

/** The decimal year of Julian day [jd], for [deltaTSeconds]. */
internal fun decimalYear(jd: Double): Double = 2000.0 + (jd - 2_451_545.0) / 365.25

/** The Universal Time instant of the Terrestrial Time Julian ephemeris day [jde]. */
internal fun instantOfEphemerisDay(jde: Double): kotlin.time.Instant =
    instantOfJulianDay(jde - deltaTSeconds(decimalYear(jde)) / 86_400.0)

/** The Terrestrial Time Julian ephemeris day of the Universal Time [instant]. */
internal fun ephemerisDay(instant: kotlin.time.Instant): Double =
    julianDay(instant).let { jd -> jd + deltaTSeconds(decimalYear(jd)) / 86_400.0 }

/**
 * Nutation in longitude, in degrees, at [t] Julian centuries of Terrestrial Time: Meeus's
 * four-term expression, accurate to about half an arcsecond.
 */
internal fun nutationInLongitude(t: Double): Double {
    val omega = 125.04452 - 1934.136261 * t
    val sunMean = 280.4665 + 36000.7698 * t
    val moonMean = 218.3165 + 481267.8813 * t
    return (-17.20 * sinD(omega) - 1.32 * sinD(2 * sunMean) - 0.23 * sinD(2 * moonMean) + 0.21 * sinD(2 * omega)) / 3600.0
}

/** The mean obliquity of the ecliptic, in degrees, at [t] Julian centuries. */
internal fun meanObliquity(t: Double): Double = 23.0 + (26.0 + (21.448 - t * (46.815 + t * (0.00059 - t * 0.001813))) / 60.0) / 60.0
