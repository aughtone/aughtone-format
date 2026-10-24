package io.github.aughtone.astronomy

import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.minutes

/** Sun times checked against NOAA's published tables, through the generated [noaaSunFixture]. */
class SunTimesTest {

    private class Case(val place: String, val latitude: Double, val longitude: Double, val date: LocalDate,
                       val zone: TimeZone, val sunrise: LocalTime?, val sunset: LocalTime?)

    private val cases = noaaSunFixture.trim().lines().map { line ->
        val f = line.split('|')
        fun time(t: String) = if (t == "none") null else LocalTime.parse(t)
        Case(f[0], f[1].toDouble(), f[2].toDouble(), LocalDate.parse(f[3]),
            FixedOffsetTimeZone(UtcOffset(seconds = f[4].toInt() * 60)), time(f[5]), time(f[6]))
    }

    /**
     * NOAA's tables list each event under the local date it happens on, while [SunTimes] gives
     * each day the events around its solar noon, so a sunset after midnight (Reykjavik in June)
     * belongs to the previous day here and to the next row there. Each NOAA row is therefore
     * compared with every computed event that falls on that local date.
     */
    // One test per place, so no one test runs long enough to meet a browser runner's timeout.
    @Test fun torontoAgreesWithNoaa() = checkPlace("Toronto")
    @Test fun reykjavikAgreesWithNoaa() = checkPlace("Reykjavik")
    @Test fun tromsoAgreesWithNoaa() = checkPlace("Tromso")
    @Test fun quitoAgreesWithNoaa() = checkPlace("Quito")
    @Test fun singaporeAgreesWithNoaa() = checkPlace("Singapore")
    @Test fun sydneyAgreesWithNoaa() = checkPlace("Sydney")
    @Test fun capeTownAgreesWithNoaa() = checkPlace("Cape Town")
    @Test fun honoluluAgreesWithNoaa() = checkPlace("Honolulu")

    @Test
    fun everyPlaceInTheFixtureIsChecked() {
        assertEquals(setOf("Toronto", "Reykjavik", "Tromso", "Quito", "Singapore", "Sydney", "Cape Town", "Honolulu"), cases.map { it.place }.toSet())
        assertTrue(cases.size > 2500)
    }

    private fun checkPlace(place: String) {
        val mismatches = mutableListOf<String>()
        var polarEdges = 0
        run {
            val days = cases.filter { it.place == place }
            val first = days.first()
            val times = (-1..days.size).map { offset ->
                val date = first.date.plus(kotlinx.datetime.DatePeriod(days = offset))
                SunTimes.of(first.latitude, first.longitude, date, days.getOrElse(offset.coerceIn(0, days.lastIndex)) { first }.zone)
            }
            fun eventsOf(pick: (SunTimes) -> SunEvent): List<kotlin.time.Instant> = times.mapNotNull { (pick(it) as? SunEvent.At)?.instant }
            for ((what, events, expectedOf) in listOf(
                Triple("sunrise", eventsOf { it.sunrise }) { c: Case -> c.sunrise },
                Triple("sunset", eventsOf { it.sunset }) { c: Case -> c.sunset },
            )) {
                // NOAA writes whole minutes. At ordinary latitudes a time within a minute of NOAA's
                // agrees; above 60°, where the sun meets the horizon at a shallow angle, within three.
                val polar = abs(first.latitude) > 60.0
                val tolerance = if (polar) 180 else 60
                // The events by local date, once per offset the rows use, rather than once per row.
                val byDate = mutableMapOf<TimeZone, Map<LocalDate, List<kotlin.time.Instant>>>()
                for (case in days) {
                    val onThisDate = byDate.getOrPut(case.zone) { events.groupBy { it.toLocalDateTime(case.zone).date } }[case.date].orEmpty()
                    val expected = expectedOf(case)
                    if (expected != null) {
                        val want = LocalDateTime(case.date, expected).toInstant(case.zone)
                        if (events.any { abs((it - want).inWholeSeconds) <= tolerance }) continue
                        // At the edge of the midnight sun the last sunset can fall a day apart.
                        if (polar && events.any { abs((it - want).inWholeSeconds - 86_400) <= tolerance || abs((it - want).inWholeSeconds + 86_400) <= tolerance }) { polarEdges++; continue }
                        mismatches += "$place ${case.date} $what: NOAA $expected, computed ${onThisDate.map { it.toLocalDateTime(case.zone).time }}"
                        continue
                    }
                    // NOAA has none: nothing falls on this date, or only an event within a minute
                    // of midnight, which NOAA's table rounds to its "00:00" and shows as none.
                    val stray = onThisDate.filter {
                        val time = it.toLocalDateTime(case.zone).time
                        time.toSecondOfDay() > 60 && time.toSecondOfDay() < 86_400 - 60
                    }
                    if (stray.isEmpty()) continue
                    // At the edge of the polar day or night the sun only just reaches the horizon,
                    // near solar noon or solar midnight, and the first or last such day can be a
                    // day apart.
                    val noon = times[days.indexOf(case) + 1].solarNoon
                    fun nearNoonOrMidnight(t: kotlin.time.Instant) = abs((t - noon).inWholeMinutes).let { it < 40 || abs(it - 720) < 40 }
                    if (polar && stray.all(::nearNoonOrMidnight)) { polarEdges++; continue }
                    mismatches += "$place ${case.date} $what: NOAA has none, computed ${stray.map { it.toLocalDateTime(case.zone).time }}"
                }
            }
        }
        assertTrue(mismatches.isEmpty(), "$place: ${mismatches.size} mismatches:\n" + mismatches.take(40).joinToString("\n"))
        assertTrue(polarEdges <= 6, "$place: $polarEdges days at a polar edge")
    }

    @Test
    fun theArcticReportsPolarDayAndPolarNight() {
        val tromso = 69.6492 to 18.9553
        val oslo = FixedOffsetTimeZone(UtcOffset(hours = 2))
        val midsummer = SunTimes.of(tromso.first, tromso.second, LocalDate(2024, 6, 21), oslo)
        assertIs<SunEvent.AlwaysAbove>(midsummer.sunrise)
        assertIs<SunEvent.AlwaysAbove>(midsummer.sunset)
        assertTrue(midsummer.isPolarDay && !midsummer.isPolarNight)
        val midwinter = SunTimes.of(tromso.first, tromso.second, LocalDate(2024, 12, 21), FixedOffsetTimeZone(UtcOffset(hours = 1)))
        assertIs<SunEvent.AlwaysBelow>(midwinter.sunrise)
        assertTrue(midwinter.isPolarNight && !midwinter.isPolarDay)
        // In the polar night the sun still comes within 6° of the horizon, so there is civil twilight.
        assertIs<SunEvent.At>(midwinter.civilDawn)
    }

    @Test
    fun twilightComesInOrder() {
        val toronto = SunTimes.of(43.6532, -79.3832, LocalDate(2024, 3, 20), FixedOffsetTimeZone(UtcOffset(hours = -4)))
        val order = listOf(toronto.astronomicalDawn, toronto.nauticalDawn, toronto.civilDawn, toronto.sunrise,
            toronto.sunset, toronto.civilDusk, toronto.nauticalDusk, toronto.astronomicalDusk)
            .map { (it as SunEvent.At).instant }
        assertEquals(order.sorted(), order)
        // Civil twilight at Toronto in March lasts about half an hour.
        val civil = (toronto.sunrise as SunEvent.At).instant - (toronto.civilDawn as SunEvent.At).instant
        assertTrue(civil in 20.minutes..40.minutes, "$civil")
        assertTrue(toronto.solarNoon > (toronto.sunrise as SunEvent.At).instant && toronto.solarNoon < (toronto.sunset as SunEvent.At).instant)
    }

    @Test
    fun aWhiteNightHasNoAstronomicalDarkness() {
        // Reykjavik at midsummer: the sun sets, but never sinks 18° below the horizon.
        val reykjavik = SunTimes.of(64.1466, -21.9426, LocalDate(2024, 6, 21), FixedOffsetTimeZone(UtcOffset.ZERO))
        assertIs<SunEvent.At>(reykjavik.sunset)
        assertIs<SunEvent.AlwaysAbove>(reykjavik.astronomicalDusk)
    }

    @Test
    fun theUtcDayFormMatchesTheLocalFormInUtc() {
        val date = LocalDate(2024, 5, 1)
        assertEquals(SunTimes.of(51.4769, 0.0, date, TimeZone.UTC).sunrise, SunTimes.ofUtcDay(51.4769, 0.0, date).sunrise)
    }

    @Test
    fun eventsFallOnTheRequestedLocalDay() {
        // Auckland is far east of Greenwich; its sunrise is the previous day in UTC.
        val auckland = FixedOffsetTimeZone(UtcOffset(hours = 12))
        val date = LocalDate(2024, 7, 1)
        val times = SunTimes.of(-36.8485, 174.7633, date, auckland)
        for (event in listOf(times.sunrise, times.sunset)) assertEquals(date, (event as SunEvent.At).instant.toLocalDateTime(auckland).date)
    }

    @Test
    fun aPositionOffTheGlobeIsRejected() {
        assertFailsWith<IllegalArgumentException> { SunTimes.ofUtcDay(91.0, 0.0, LocalDate(2024, 1, 1)) }
        assertFailsWith<IllegalArgumentException> { SunTimes.ofUtcDay(0.0, 181.0, LocalDate(2024, 1, 1)) }
        assertFailsWith<IllegalArgumentException> { SunTimes.ofUtcDay(Double.NaN, 0.0, LocalDate(2024, 1, 1)) }
    }
}
