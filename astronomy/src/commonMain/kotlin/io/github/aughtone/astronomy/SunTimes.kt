package io.github.aughtone.astronomy

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlin.time.Instant

/**
 * When the sun reaches a given altitude on a day, or that it does not.
 *
 * For sunrise and sunset, [AlwaysAbove] is polar day and [AlwaysBelow] polar night. For dawn and
 * dusk they say the sun never got that far below the horizon (a white night, with no
 * astronomical darkness) or never came that close to it.
 */
sealed interface SunEvent {
    /** The sun reaches the altitude at [instant]. */
    data class At(val instant: Instant) : SunEvent

    /** The sun stays above the altitude all day: polar day, for sunrise and sunset. */
    data object AlwaysAbove : SunEvent

    /** The sun stays below the altitude all day: polar night, for sunrise and sunset. */
    data object AlwaysBelow : SunEvent
}

/**
 * Sunrise, sunset and twilight at a place on a day.
 *
 * Each event is the instant the sun's centre reaches its altitude, rising in the morning and
 * setting in the evening: sunrise and sunset at −0.833° (the sun's upper edge on the horizon,
 * with standard refraction at sea level), civil twilight at −6°, nautical at −12° and astronomical
 * at −18°. OpenStreetMap's `dawn` and `dusk` are civil twilight. A day with no such moment says
 * so with [SunEvent.AlwaysAbove] or [SunEvent.AlwaysBelow], never a guessed time.
 *
 * The times come from the NOAA solar calculation and agree with NOAA's published tables to
 * within a minute up to 60° of latitude. Beyond that the sun crosses the horizon at a shallow
 * angle and the times can differ by a few minutes, and the first or last sunrise or sunset at the
 * edge of the polar day or night can fall a day apart. Elevation is not taken into account.
 *
 * Each day's events are the ones around its solar noon, so where the sun sets after midnight
 * (Reykjavik in June) the day's [sunset] is in the early hours of the next date.
 *
 * ```kotlin
 * val toronto = TimeZone.of("America/Toronto")
 * val times = SunTimes.of(43.6532, -79.3832, LocalDate(2024, 6, 21), toronto)
 * (times.sunrise as SunEvent.At).instant.toLocalDateTime(toronto).time // 05:36
 * ```
 *
 * @property latitude The place's latitude, in degrees north (negative for south).
 * @property longitude The place's longitude, in degrees east (negative for west).
 * @property date The day the times are for, in [timeZone].
 * @property timeZone The zone [date] is read in.
 * @property solarNoon The moment the sun crosses the meridian on [date].
 */
class SunTimes private constructor(
    val latitude: Double,
    val longitude: Double,
    val date: LocalDate,
    val timeZone: TimeZone,
    val solarNoon: Instant,
    /** Sunrise: the sun's upper edge appears. */
    val sunrise: SunEvent,
    /** Sunset: the sun's upper edge disappears. */
    val sunset: SunEvent,
    /** Civil dawn: the sun is 6° below the horizon, rising. */
    val civilDawn: SunEvent,
    /** Civil dusk: the sun is 6° below the horizon, setting. */
    val civilDusk: SunEvent,
    /** Nautical dawn: the sun is 12° below the horizon, rising. */
    val nauticalDawn: SunEvent,
    /** Nautical dusk: the sun is 12° below the horizon, setting. */
    val nauticalDusk: SunEvent,
    /** Astronomical dawn: the sun is 18° below the horizon, rising. */
    val astronomicalDawn: SunEvent,
    /** Astronomical dusk: the sun is 18° below the horizon, setting. */
    val astronomicalDusk: SunEvent,
) {
    /** Whether the sun stays above the horizon all day. */
    val isPolarDay: Boolean get() = sunrise is SunEvent.AlwaysAbove

    /** Whether the sun stays below the horizon all day. */
    val isPolarNight: Boolean get() = sunrise is SunEvent.AlwaysBelow

    override fun toString(): String = "SunTimes($date at $latitude, $longitude: sunrise=$sunrise, sunset=$sunset)"

    companion object {
        /**
         * The sun times at a place for [date] as it is in [timeZone] — the calendar day the
         * caller means there, which is what an opening-hours rule such as `sunrise-sunset`
         * means. The events are the ones around that day's solar noon.
         *
         * @param latitude Degrees north, from −90 to 90.
         * @param longitude Degrees east, from −180 to 180.
         * @param date The day, in [timeZone].
         * @param timeZone The zone the place keeps.
         * @throws IllegalArgumentException if [latitude] or [longitude] is out of range or not a
         *   number.
         */
        fun of(latitude: Double, longitude: Double, date: LocalDate, timeZone: TimeZone): SunTimes {
            require(latitude in -90.0..90.0) { "Latitude must be from -90 to 90, but was $latitude." }
            require(longitude in -180.0..180.0) { "Longitude must be from -180 to 180, but was $longitude." }
            val calculation = Calculation(latitude, longitude, date.atTime(LocalTime(12, 0)).toInstant(timeZone))
            return SunTimes(
                latitude, longitude, date, timeZone, calculation.noon,
                sunrise = calculation.event(SUNRISE_ALTITUDE, rising = true),
                sunset = calculation.event(SUNRISE_ALTITUDE, rising = false),
                civilDawn = calculation.event(-6.0, rising = true),
                civilDusk = calculation.event(-6.0, rising = false),
                nauticalDawn = calculation.event(-12.0, rising = true),
                nauticalDusk = calculation.event(-12.0, rising = false),
                astronomicalDawn = calculation.event(-18.0, rising = true),
                astronomicalDusk = calculation.event(-18.0, rising = false),
            )
        }

        /**
         * The sun times at a place for [date] as a UTC day: the same as [of] in [TimeZone.UTC].
         * Simpler to reason about, but for a place far from Greenwich an event can belong to the
         * neighbouring local day; use [of] for a place's own day.
         *
         * @throws IllegalArgumentException if [latitude] or [longitude] is out of range or not a
         *   number.
         */
        fun ofUtcDay(latitude: Double, longitude: Double, date: LocalDate): SunTimes = of(latitude, longitude, date, TimeZone.UTC)

        /** The sun's centre when its upper edge is on the horizon: semidiameter plus standard refraction. */
        private const val SUNRISE_ALTITUDE = -0.833
    }

    /** One day's calculation, anchored on the solar noon nearest to [localNoon]. */
    private class Calculation(val latitude: Double, val longitude: Double, localNoon: Instant) {
        /** UTC midnight of the solar day, in milliseconds since the epoch. */
        private val dayStart: Long
        val noon: Instant

        init {
            val utcMidnight = localNoon.toEpochMilliseconds().floorDiv(MILLIS_PER_DAY) * MILLIS_PER_DAY
            // The solar noon of the UTC day holding local noon can be up to a day away from it at
            // longitudes far from the zone's meridian; take the day whose noon is nearest.
            var start = utcMidnight
            var guess = noonOf(start, localNoon)
            val gap = guess.toEpochMilliseconds() - localNoon.toEpochMilliseconds()
            if (gap > MILLIS_PER_DAY / 2) start -= MILLIS_PER_DAY else if (gap < -MILLIS_PER_DAY / 2) start += MILLIS_PER_DAY
            guess = noonOf(start, guess)
            dayStart = start
            noon = noonOf(start, guess)
        }

        /** Solar noon of the UTC day starting at [start], with the equation of time at [at]. */
        private fun noonOf(start: Long, at: Instant): Instant {
            val eot = solarPosition(julianCentury(julianDay(at))).equationOfTime
            return Instant.fromEpochMilliseconds(start + ((720.0 - 4.0 * longitude - eot) * 60_000).toLong())
        }

        /** When the sun's centre reaches [altitude], rising or setting, refined at its own moment. */
        fun event(altitude: Double, rising: Boolean): SunEvent {
            var t = noon
            repeat(4) {
                val position = solarPosition(julianCentury(julianDay(t)))
                val cosHourAngle = (sinD(altitude) - sinD(latitude) * sinD(position.declination)) /
                    (cosD(latitude) * cosD(position.declination))
                if (cosHourAngle > 1.0) return SunEvent.AlwaysBelow
                if (cosHourAngle < -1.0) return SunEvent.AlwaysAbove
                val hourAngleMinutes = 4.0 * acosD(cosHourAngle)
                val noonMinutes = 720.0 - 4.0 * longitude - position.equationOfTime
                val minutes = if (rising) noonMinutes - hourAngleMinutes else noonMinutes + hourAngleMinutes
                t = Instant.fromEpochMilliseconds(dayStart + (minutes * 60_000).toLong())
            }
            return SunEvent.At(t)
        }
    }
}
