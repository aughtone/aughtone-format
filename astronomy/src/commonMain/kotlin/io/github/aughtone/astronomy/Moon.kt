package io.github.aughtone.astronomy

import kotlin.math.floor
import kotlin.time.Instant

/**
 * The moon's illuminated fraction at an instant.
 *
 * @property illuminatedFraction The fraction of the disc lit by the sun, from 0 (new moon) to 1
 *   (full moon).
 * @property isWaxing Whether the lit part is growing: from new moon to full moon.
 */
data class MoonPhase(val illuminatedFraction: Double, val isWaxing: Boolean)

/**
 * New moons, full moons and the moon's phase, by the algorithms of Meeus's *Astronomical
 * Algorithms* (chapters 47 to 49). New and full moons agree with NASA's published tables to
 * within two minutes for 2001 to 2030; the instants are Universal Time, converted from the
 * algorithms' Terrestrial Time with ΔT, whose future values are predictions.
 */
object Moon {

    /** Every new moon from [start] to [end], in order. */
    fun newMoonsBetween(start: Instant, end: Instant): List<Instant> = phasesBetween(start, end, full = false)

    /** Every full moon from [start] to [end], in order. */
    fun fullMoonsBetween(start: Instant, end: Instant): List<Instant> = phasesBetween(start, end, full = true)

    /** The moon's phase at [instant]. */
    fun phaseAt(instant: Instant): MoonPhase {
        val t = julianCentury(ephemerisDay(instant))
        val moon = moonPosition(t)
        val sunLongitude = apparentSolarLongitude(t)
        // Elongation of the moon from the sun along the ecliptic, 0° at new moon and 180° at full.
        val elongation = normalizeDegrees(moon.longitude + nutationInLongitude(t) - sunLongitude)
        val cosElongation = cosD(moon.latitude) * cosD(elongation)
        // The phase angle, seen from the moon, between the sun and the earth (Meeus 48.2–48.3).
        val psi = acosD(cosElongation)
        val sunDistanceKm = 149_597_870.7 * solarDistanceAu(t)
        val phaseAngle = atan2D(sunDistanceKm * sinD(psi), moon.distanceKm - sunDistanceKm * cosElongation)
        return MoonPhase(illuminatedFraction = (1 + cosD(phaseAngle)) / 2, isWaxing = elongation < 180.0)
    }

    /** The new (or full) moons from [start] to [end], by lunation number from J2000.0. */
    private fun phasesBetween(start: Instant, end: Instant, full: Boolean): List<Instant> {
        if (end < start) return emptyList()
        val offset = if (full) 0.5 else 0.0
        var k = floor((decimalYear(julianDay(start)) - 2000.0) * 12.3685) - 1 + offset
        val phases = mutableListOf<Instant>()
        while (true) {
            val instant = instantOfEphemerisDay(phaseEphemerisDay(k, full))
            if (instant > end) return phases
            if (instant >= start) phases += instant
            k += 1.0
        }
    }

    /** The Julian ephemeris day of lunation [k]'s new moon (whole k) or full moon (k + 0.5), by Meeus chapter 49. */
    private fun phaseEphemerisDay(k: Double, full: Boolean): Double {
        val t = k / 1236.85
        val t2 = t * t
        val t3 = t2 * t
        val t4 = t3 * t
        val mean = 2451550.09766 + 29.530588861 * k + 0.00015437 * t2 - 0.000000150 * t3 + 0.00000000073 * t4
        val e = 1 - 0.002516 * t - 0.0000074 * t2
        val m = 2.5534 + 29.10535670 * k - 0.0000014 * t2 - 0.00000011 * t3
        val mp = 201.5643 + 385.81693528 * k + 0.0107582 * t2 + 0.00001238 * t3 - 0.000000058 * t4
        val f = 160.7108 + 390.67050284 * k - 0.0016118 * t2 - 0.00000227 * t3 + 0.000000011 * t4
        val omega = 124.7746 - 1.56375588 * k + 0.0020672 * t2 + 0.00000215 * t3
        val c = if (full) {
            -0.40614 * sinD(mp) + 0.17302 * e * sinD(m) + 0.01614 * sinD(2 * mp) + 0.01043 * sinD(2 * f) +
                0.00734 * e * sinD(mp - m) - 0.00515 * e * sinD(mp + m) + 0.00209 * e * e * sinD(2 * m)
        } else {
            -0.40720 * sinD(mp) + 0.17241 * e * sinD(m) + 0.01608 * sinD(2 * mp) + 0.01039 * sinD(2 * f) +
                0.00739 * e * sinD(mp - m) - 0.00514 * e * sinD(mp + m) + 0.00208 * e * e * sinD(2 * m)
        } + (-0.00111 * sinD(mp - 2 * f) - 0.00057 * sinD(mp + 2 * f) + 0.00056 * e * sinD(2 * mp + m) -
            0.00042 * sinD(3 * mp) + 0.00042 * e * sinD(m + 2 * f) + 0.00038 * e * sinD(m - 2 * f) -
            0.00024 * e * sinD(2 * mp - m) - 0.00017 * sinD(omega) - 0.00007 * sinD(mp + 2 * m) +
            0.00004 * sinD(2 * mp - 2 * f) + 0.00004 * sinD(3 * m) + 0.00003 * sinD(mp + m - 2 * f) +
            0.00003 * sinD(2 * mp + 2 * f) - 0.00003 * sinD(mp + m + 2 * f) + 0.00003 * sinD(mp - m + 2 * f) -
            0.00002 * sinD(mp - m - 2 * f) - 0.00002 * sinD(3 * mp + m) + 0.00002 * sinD(4 * mp))
        val planetary = 0.000325 * sinD(299.77 + 0.107408 * k - 0.009173 * t2) + 0.000165 * sinD(251.88 + 0.016321 * k) +
            0.000164 * sinD(251.83 + 26.651886 * k) + 0.000126 * sinD(349.42 + 36.412478 * k) +
            0.000110 * sinD(84.66 + 18.206239 * k) + 0.000062 * sinD(141.74 + 53.303771 * k) +
            0.000060 * sinD(207.14 + 2.453732 * k) + 0.000056 * sinD(154.84 + 7.306860 * k) +
            0.000047 * sinD(34.52 + 27.261239 * k) + 0.000042 * sinD(207.19 + 0.121824 * k) +
            0.000040 * sinD(291.34 + 1.844379 * k) + 0.000037 * sinD(161.72 + 24.198154 * k) +
            0.000035 * sinD(239.56 + 25.513099 * k) + 0.000023 * sinD(331.55 + 3.592518 * k)
        return mean + c + planetary
    }
}
