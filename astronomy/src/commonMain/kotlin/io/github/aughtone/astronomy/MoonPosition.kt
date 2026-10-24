package io.github.aughtone.astronomy

/**
 * The moon's geocentric ecliptic position: longitude and latitude in degrees (not corrected for
 * nutation), and distance in kilometres.
 */
internal class MoonPosition(val longitude: Double, val latitude: Double, val distanceKm: Double)

/** One periodic term: multiples of D, M, M′ and F, and its coefficients. */
private class MoonTerm(val d: Int, val m: Int, val mPrime: Int, val f: Int, val a: Int, val b: Int)

private fun term(d: Int, m: Int, mPrime: Int, f: Int, a: Int, b: Int = 0) = MoonTerm(d, m, mPrime, f, a, b)

/** Meeus table 47.A: longitude (millionths of a degree) and distance (metres) terms. */
private val longitudeDistanceTerms = listOf(
    term(0, 0, 1, 0, 6288774, -20905355), term(2, 0, -1, 0, 1274027, -3699111), term(2, 0, 0, 0, 658314, -2955968),
    term(0, 0, 2, 0, 213618, -569925), term(0, 1, 0, 0, -185116, 48888), term(0, 0, 0, 2, -114332, -3149),
    term(2, 0, -2, 0, 58793, 246158), term(2, -1, -1, 0, 57066, -152138), term(2, 0, 1, 0, 53322, -170733),
    term(2, -1, 0, 0, 45758, -204586), term(0, 1, -1, 0, -40923, -129620), term(1, 0, 0, 0, -34720, 108743),
    term(0, 1, 1, 0, -30383, 104755), term(2, 0, 0, -2, 15327, 10321), term(0, 0, 1, 2, -12528, 0),
    term(0, 0, 1, -2, 10980, 79661), term(4, 0, -1, 0, 10675, -34782), term(0, 0, 3, 0, 10034, -23210),
    term(4, 0, -2, 0, 8548, -21636), term(2, 1, -1, 0, -7888, 24208), term(2, 1, 0, 0, -6766, 30824),
    term(1, 0, -1, 0, -5163, -8379), term(1, 1, 0, 0, 4987, -16675), term(2, -1, 1, 0, 4036, -12831),
    term(2, 0, 2, 0, 3994, -10445), term(4, 0, 0, 0, 3861, -11650), term(2, 0, -3, 0, 3665, 14403),
    term(0, 1, -2, 0, -2689, -7003), term(2, 0, -1, 2, -2602, 0), term(2, -1, -2, 0, 2390, 10056),
    term(1, 0, 1, 0, -2348, 6322), term(2, -2, 0, 0, 2236, -9884), term(0, 1, 2, 0, -2120, 5751),
    term(0, 2, 0, 0, -2069, 0), term(2, -2, -1, 0, 2048, -4950), term(2, 0, 1, -2, -1773, 4130),
    term(2, 0, 0, 2, -1595, 0), term(4, -1, -1, 0, 1215, -3958), term(0, 0, 2, 2, -1110, 0),
    term(3, 0, -1, 0, -892, 3258), term(2, 1, 1, 0, -810, 2616), term(4, -1, -2, 0, 759, -1897),
    term(0, 2, -1, 0, -713, -2117), term(2, 2, -1, 0, -700, 2354), term(2, 1, -2, 0, 691, 0),
    term(2, -1, 0, -2, 596, 0), term(4, 0, 1, 0, 549, -1423), term(0, 0, 4, 0, 537, -1117),
    term(4, -1, 0, 0, 520, -1571), term(1, 0, -2, 0, -487, -1739), term(2, 1, 0, -2, -399, 0),
    term(0, 0, 2, -2, -381, -4421), term(1, 1, 1, 0, 351, 0), term(3, 0, -2, 0, -340, 0),
    term(4, 0, -3, 0, 330, 0), term(2, -1, 2, 0, 327, 0), term(0, 2, 1, 0, -323, 1165),
    term(1, 1, -1, 0, 299, 0), term(2, 0, 3, 0, 294, 0), term(2, 0, -1, -2, 0, 8752),
)

/** Meeus table 47.B: latitude terms, in millionths of a degree. */
private val latitudeTerms = listOf(
    term(0, 0, 0, 1, 5128122), term(0, 0, 1, 1, 280602), term(0, 0, 1, -1, 277693), term(2, 0, 0, -1, 173237),
    term(2, 0, -1, 1, 55413), term(2, 0, -1, -1, 46271), term(2, 0, 0, 1, 32573), term(0, 0, 2, 1, 17198),
    term(2, 0, 1, -1, 9266), term(0, 0, 2, -1, 8822), term(2, -1, 0, -1, 8216), term(2, 0, -2, -1, 4324),
    term(2, 0, 1, 1, 4200), term(2, 1, 0, -1, -3359), term(2, -1, -1, 1, 2463), term(2, -1, 0, 1, 2211),
    term(2, -1, -1, -1, 2065), term(0, 1, -1, -1, -1870), term(4, 0, -1, -1, 1828), term(0, 1, 0, 1, -1794),
    term(0, 0, 0, 3, -1749), term(0, 1, -1, 1, -1565), term(1, 0, 0, 1, -1491), term(0, 1, 1, 1, -1475),
    term(0, 1, 1, -1, -1410), term(0, 1, 0, -1, -1344), term(1, 0, 0, -1, -1335), term(0, 0, 3, 1, 1107),
    term(4, 0, 0, -1, 1021), term(4, 0, -1, 1, 833), term(0, 0, 1, -3, 777), term(4, 0, -2, 1, 671),
    term(2, 0, 0, -3, 607), term(2, 0, 2, -1, 596), term(2, -1, 1, -1, 491), term(2, 0, -2, 1, -451),
    term(0, 0, 3, -1, 439), term(2, 0, 2, 1, 422), term(2, 0, -3, -1, 421), term(2, 1, -1, 1, -366),
    term(2, 1, 0, 1, -351), term(4, 0, 0, 1, 331), term(2, -1, 1, 1, 315), term(2, -2, 0, -1, 302),
    term(0, 0, 1, 3, -283), term(2, 1, 1, -1, -229), term(1, 1, 0, -1, 223), term(1, 1, 0, 1, 223),
    term(0, 1, -2, -1, -220), term(2, 1, -1, -1, -220), term(1, 0, 1, 1, -185), term(2, -1, -2, -1, 181),
    term(0, 1, 2, 1, -177), term(4, 0, -2, -1, 176), term(4, -1, -1, -1, 166), term(1, 0, 1, -1, -164),
    term(4, 0, 1, -1, 132), term(1, 0, -1, -1, -119), term(4, -1, 0, -1, 115), term(2, -2, 0, 1, 107),
)

/**
 * The moon's position at [t] Julian centuries of Terrestrial Time from J2000.0, by Meeus's
 * *Astronomical Algorithms*, chapter 47: accurate to about 10″ in longitude and 4″ in latitude.
 */
internal fun moonPosition(t: Double): MoonPosition {
    val t2 = t * t
    val t3 = t2 * t
    val t4 = t3 * t
    val meanLongitude = 218.3164477 + 481267.88123421 * t - 0.0015786 * t2 + t3 / 538841 - t4 / 65194000
    val d = 297.8501921 + 445267.1114034 * t - 0.0018819 * t2 + t3 / 545868 - t4 / 113065000
    val m = 357.5291092 + 35999.0502909 * t - 0.0001536 * t2 + t3 / 24490000
    val mPrime = 134.9633964 + 477198.8675055 * t + 0.0087414 * t2 + t3 / 69699 - t4 / 14712000
    val f = 93.2720950 + 483202.0175233 * t - 0.0036539 * t2 - t3 / 3526000 + t4 / 863310000
    val a1 = 119.75 + 131.849 * t
    val a2 = 53.09 + 479264.290 * t
    val a3 = 313.45 + 481266.484 * t
    val e = 1 - 0.002516 * t - 0.0000074 * t2
    fun eccentricity(term: MoonTerm) = when (kotlin.math.abs(term.m)) { 1 -> e; 2 -> e * e; else -> 1.0 }
    fun argument(term: MoonTerm) = term.d * d + term.m * m + term.mPrime * mPrime + term.f * f

    var sumL = 0.0
    var sumR = 0.0
    for (term in longitudeDistanceTerms) {
        val factor = eccentricity(term)
        val argument = argument(term)
        sumL += term.a * factor * sinD(argument)
        sumR += term.b * factor * cosD(argument)
    }
    var sumB = 0.0
    for (term in latitudeTerms) sumB += term.a * eccentricity(term) * sinD(argument(term))
    sumL += 3958 * sinD(a1) + 1962 * sinD(meanLongitude - f) + 318 * sinD(a2)
    sumB += -2235 * sinD(meanLongitude) + 382 * sinD(a3) + 175 * sinD(a1 - f) + 175 * sinD(a1 + f) +
        127 * sinD(meanLongitude - mPrime) - 115 * sinD(meanLongitude + mPrime)
    return MoonPosition(
        longitude = normalizeDegrees(meanLongitude + sumL / 1_000_000),
        latitude = sumB / 1_000_000,
        distanceKm = 385000.56 + sumR / 1000,
    )
}
