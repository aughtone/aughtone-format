package io.github.aughtone.astronomy

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlin.math.asin
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant

/** A criterion for predicting whether the young crescent moon can be seen. */
enum class CrescentCriterion {
    /** Odeh (2006), built from 737 observations; the default. */
    Odeh,

    /** Yallop (1997), the HM Nautical Almanac Office criterion. */
    Yallop,
}

/**
 * How visible the young crescent is predicted to be, on a scale both criteria map onto.
 *
 * Yallop's six classes, A to F, map onto these in order. Odeh's four zones map onto
 * [EasilyVisible] (zone A, visible by naked eye), [OpticalAidMayHelp] (B, visible with optical
 * aid, and possibly by naked eye), [OpticalAidNeeded] (C, visible with optical aid only) and
 * [NotVisibleWithTelescope] (D). [NotVisible] is also the answer when the moon sets before the
 * sun, or the new moon has not happened yet.
 */
enum class CrescentVisibility(
    /** Whether the crescent is predicted to be seen without optical aid. */
    val isNakedEyeVisible: Boolean,
    /** Whether the crescent is predicted to be seen with optical aid, if not otherwise. */
    val isVisibleWithOpticalAid: Boolean,
) {
    /** Easily visible to the naked eye. */
    EasilyVisible(isNakedEyeVisible = true, isVisibleWithOpticalAid = true),

    /** Visible to the naked eye under perfect conditions. */
    VisibleInPerfectConditions(isNakedEyeVisible = true, isVisibleWithOpticalAid = true),

    /** Optical aid may be needed to find it; then possibly visible to the naked eye. */
    OpticalAidMayHelp(isNakedEyeVisible = false, isVisibleWithOpticalAid = true),

    /** Visible only with optical aid. */
    OpticalAidNeeded(isNakedEyeVisible = false, isVisibleWithOpticalAid = true),

    /** Not visible even with a telescope. */
    NotVisibleWithTelescope(isNakedEyeVisible = false, isVisibleWithOpticalAid = false),

    /** Not visible: below the Danjon limit, set before the sun, or before the new moon. */
    NotVisible(isNakedEyeVisible = false, isVisibleWithOpticalAid = false),
}

/**
 * A prediction of the young crescent's visibility on one evening.
 *
 * A prediction is never an observation: an Islamic month that begins when the crescent is
 * sighted begins when it is sighted, whatever this says.
 *
 * @property visibility The predicted visibility.
 * @property criterion The criterion that gave it.
 * @property value The criterion's own measure — Yallop's q, or Odeh's V — at [bestTime], or
 *   `null` when the crescent could not be above the horizon after sunset.
 * @property bestTime The best time to look: four-ninths of the way from sunset to moonset, or
 *   `null` as for [value].
 */
data class CrescentPrediction(
    val visibility: CrescentVisibility,
    val criterion: CrescentCriterion,
    val value: Double?,
    val bestTime: Instant?,
)

/** Predictions of the young crescent moon's visibility. */
object Crescent {

    /**
     * The predicted visibility of the crescent on the evening of [date] in [timeZone], seen from
     * a latitude and longitude, by [criterion]. The geometry is computed at the best time, four
     * ninths of the way from sunset to moonset (Yallop's definition, which Odeh also uses).
     *
     * @param latitude Degrees north, from −90 to 90.
     * @param longitude Degrees east, from −180 to 180.
     * @param criterion The criterion: [CrescentCriterion.Odeh] unless given.
     * @throws IllegalArgumentException if the latitude or longitude is out of range.
     */
    fun visibility(
        date: LocalDate,
        latitude: Double,
        longitude: Double,
        timeZone: TimeZone,
        criterion: CrescentCriterion = CrescentCriterion.Odeh,
    ): CrescentPrediction {
        val notVisible = CrescentPrediction(CrescentVisibility.NotVisible, criterion, null, null)
        val sunset = (SunTimes.of(latitude, longitude, date, timeZone).sunset as? SunEvent.At)?.instant ?: return notVisible
        val moonset = moonsetAfter(sunset, latitude, longitude) ?: return notVisible
        val bestTime = sunset + (moonset - sunset) * 4 / 9
        val newMoon = Moon.newMoonsBetween(bestTime - 35.days, bestTime).lastOrNull() ?: return notVisible
        if (newMoon > bestTime) return notVisible

        val geometry = Geometry(bestTime, latitude, longitude)
        val width = geometry.crescentWidthArcminutes
        val limit = 0.7319 * width * width - 0.1018 * width * width * width - 6.3226 * width
        return when (criterion) {
            CrescentCriterion.Yallop -> {
                val q = (geometry.arcvGeocentric - (11.8371 + limit)) / 10
                val visibility = when {
                    q > 0.216 -> CrescentVisibility.EasilyVisible
                    q > -0.014 -> CrescentVisibility.VisibleInPerfectConditions
                    q > -0.160 -> CrescentVisibility.OpticalAidMayHelp
                    q > -0.232 -> CrescentVisibility.OpticalAidNeeded
                    q > -0.293 -> CrescentVisibility.NotVisibleWithTelescope
                    else -> CrescentVisibility.NotVisible
                }
                CrescentPrediction(visibility, criterion, q, bestTime)
            }
            CrescentCriterion.Odeh -> {
                val v = geometry.arcvTopocentric - (7.1651 + limit)
                val visibility = when {
                    v >= 5.65 -> CrescentVisibility.EasilyVisible
                    v >= 2.0 -> CrescentVisibility.OpticalAidMayHelp
                    v >= -0.96 -> CrescentVisibility.OpticalAidNeeded
                    else -> CrescentVisibility.NotVisibleWithTelescope
                }
                CrescentPrediction(visibility, criterion, v, bestTime)
            }
        }
    }

    /** When the moon sets after [sunset], within six hours, or `null` if it has already set. */
    private fun moonsetAfter(sunset: Instant, latitude: Double, longitude: Double): Instant? {
        fun aboveHorizon(t: Instant) = Geometry(t, latitude, longitude).let { it.moonGeocentricAltitude - it.moonSetAltitude }
        var previous = aboveHorizon(sunset)
        if (previous <= 0) return null
        var t = sunset
        while (t < sunset + (6 * 60).minutes) {
            val next = t + 2.minutes
            val height = aboveHorizon(next)
            if (height <= 0) {
                // Interpolate within the two minutes.
                val fraction = previous / (previous - height)
                return t + (2.minutes * fraction)
            }
            previous = height
            t = next
        }
        return null
    }

    /** The sun's and moon's places in the sky from a place at an instant. */
    private class Geometry(instant: Instant, latitude: Double, longitude: Double) {
        val moonGeocentricAltitude: Double
        val moonSetAltitude: Double
        val arcvGeocentric: Double
        val arcvTopocentric: Double
        val crescentWidthArcminutes: Double

        init {
            val t = julianCentury(ephemerisDay(instant))
            val obliquity = meanObliquity(t) + 0.00256 * cosD(125.04 - 1934.136 * t)
            val siderealTime = normalizeDegrees(
                julianDay(instant).let { jd ->
                    val tu = julianCentury(jd)
                    280.46061837 + 360.98564736629 * (jd - 2_451_545.0) + 0.000387933 * tu * tu - tu * tu * tu / 38_710_000
                } + longitude,
            )
            fun horizontal(lambda: Double, beta: Double): Pair<Double, Double> {
                val rightAscension = atan2D(sinD(lambda) * cosD(obliquity) - tanD(beta) * sinD(obliquity), cosD(lambda))
                val declination = asinD(sinD(beta) * cosD(obliquity) + cosD(beta) * sinD(obliquity) * sinD(lambda))
                val hourAngle = siderealTime - rightAscension
                val altitude = asinD(sinD(latitude) * sinD(declination) + cosD(latitude) * cosD(declination) * cosD(hourAngle))
                val azimuth = atan2D(sinD(hourAngle), cosD(hourAngle) * sinD(latitude) - tanD(declination) * cosD(latitude))
                return altitude to azimuth
            }
            val (sunAltitude, sunAzimuth) = horizontal(apparentSolarLongitude(t), 0.0)
            val moon = moonPosition(t)
            val (moonAltitude, moonAzimuth) = horizontal(moon.longitude + nutationInLongitude(t), moon.latitude)
            val parallax = asin(6378.14 / moon.distanceKm) * 180.0 / kotlin.math.PI
            val moonTopocentricAltitude = moonAltitude - parallax * cosD(moonAltitude)
            moonGeocentricAltitude = moonAltitude
            moonSetAltitude = 0.7275 * parallax - 0.5667
            arcvGeocentric = moonAltitude - sunAltitude
            arcvTopocentric = moonTopocentricAltitude - sunAltitude
            val arcl = acosD(
                (sinD(sunAltitude) * sinD(moonTopocentricAltitude) +
                    cosD(sunAltitude) * cosD(moonTopocentricAltitude) * cosD(moonAzimuth - sunAzimuth)).coerceIn(-1.0, 1.0),
            )
            val semidiameter = 0.27245 * parallax * (1 + sinD(moonTopocentricAltitude) * sinD(parallax))
            crescentWidthArcminutes = semidiameter * 60 * (1 - cosD(arcl))
        }
    }
}
