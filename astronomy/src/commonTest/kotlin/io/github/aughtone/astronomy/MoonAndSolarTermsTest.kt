package io.github.aughtone.astronomy

import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.UtcOffset
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Instant

/** New and full moons and solar terms, checked against published tables through [moonSunFixture]. */
class MoonAndSolarTermsTest {

    private class Published(val kind: String, val longitude: Int?, val instant: Instant)

    private val published = moonSunFixture.trim().lines().map { line ->
        val f = line.split('|')
        Published(f[0], f[1].toIntOrNull(), Instant.parse(f[2]))
    }

    /** Within two minutes of the published time, which is itself given to the minute. */
    private fun close(a: Instant, b: Instant) = abs((a - b).inWholeSeconds) <= 120

    @Test
    fun newAndFullMoonsAgreeWithNasaWithinTwoMinutes() {
        for (kind in listOf("new", "full")) {
            val expected = published.filter { it.kind == kind }.map { it.instant }
            val start = expected.first() - 1.days
            val end = expected.last() + 1.days
            val computed = if (kind == "new") Moon.newMoonsBetween(start, end) else Moon.fullMoonsBetween(start, end)
            assertEquals(expected.size, computed.size, "$kind moons counted")
            val off = expected.zip(computed).filterNot { (e, c) -> close(e, c) }
            assertTrue(off.isEmpty(), "$kind: ${off.size} of ${expected.size} off by more than two minutes: ${off.take(10)}")
        }
    }

    @Test
    fun solarTermsAgreeWithTheHongKongObservatoryWithinTwoMinutes() {
        val terms = published.filter { it.kind == "term" }
        val years = terms.map { it.instant.toString().take(4).toInt() }.distinct()
        assertTrue(years.size >= 8)
        val off = mutableListOf<String>()
        for (year in years) {
            val computed = Sun.solarTerms(year)
            assertEquals(24, computed.size)
            for (term in computed) {
                val match = terms.firstOrNull { it.longitude == term.longitude && abs((it.instant - term.instant).inWholeDays) < 5 }
                if (match == null || !close(match.instant, term.instant)) off += "$year ${term.longitude}°: computed ${term.instant}, published ${match?.instant}"
            }
        }
        assertTrue(off.isEmpty(), "${off.size} off:\n" + off.take(20).joinToString("\n"))
    }

    @Test
    fun theMoonsPositionMatchesMeeusWorkedExample() {
        // Meeus, Astronomical Algorithms, example 47.a: 1992 April 12 at 0h Dynamical Time.
        val dynamical = Instant.parse("1992-04-12T00:00:00Z")
        val position = moonPosition(julianCentury(julianDay(dynamical)))
        assertTrue(abs(position.longitude - 133.162655) < 0.01, "longitude ${position.longitude}")
        assertTrue(abs(position.latitude - -3.229126) < 0.01, "latitude ${position.latitude}")
        assertTrue(abs(position.distanceKm - 368_409.7) < 20, "distance ${position.distanceKm}")
    }

    @Test
    fun thePhaseFollowsTheMonth() {
        val newMoon = Instant.parse("2024-01-11T11:57:00Z")
        val fullMoon = Instant.parse("2024-01-25T17:54:00Z")
        assertTrue(Moon.phaseAt(newMoon).illuminatedFraction < 0.01)
        assertTrue(Moon.phaseAt(fullMoon).illuminatedFraction > 0.99)
        val firstQuarter = Moon.phaseAt(Instant.parse("2024-01-18T03:53:00Z"))
        assertTrue(abs(firstQuarter.illuminatedFraction - 0.5) < 0.03 && firstQuarter.isWaxing, "$firstQuarter")
        assertTrue(!Moon.phaseAt(fullMoon + 3.days).isWaxing)
    }

    @Test
    fun aCrescentIsInvisibleOnTheEveningOfTheNewMoonAndPlainTwoEveningsLater() {
        // New moon 2024-03-10 09:00 UTC, the start of Ramadan 1445. At Mecca that evening the moon
        // is about nine hours old; two evenings later it is unmistakable.
        val mecca = 21.4225 to 39.8262
        val arabia = FixedOffsetTimeZone(UtcOffset(hours = 3))
        for (criterion in CrescentCriterion.entries) {
            val first = Crescent.visibility(LocalDate(2024, 3, 10), mecca.first, mecca.second, arabia, criterion)
            assertTrue(!first.visibility.isNakedEyeVisible, "$criterion first evening: $first")
            val third = Crescent.visibility(LocalDate(2024, 3, 12), mecca.first, mecca.second, arabia, criterion)
            assertEquals(CrescentVisibility.EasilyVisible, third.visibility, "$criterion third evening: $third")
            assertEquals(criterion, third.criterion)
        }
    }

    @Test
    fun aMoonThatSetsBeforeTheSunIsNotVisible() {
        // The evening before the new moon of 2024-03-10, the moon is old and sets before the sun.
        val prediction = Crescent.visibility(LocalDate(2024, 3, 9), 21.4225, 39.8262, FixedOffsetTimeZone(UtcOffset(hours = 3)))
        assertEquals(CrescentVisibility.NotVisible, prediction.visibility)
        assertEquals(CrescentCriterion.Odeh, prediction.criterion)
    }

    @Test
    fun deltaTIsAboutAMinuteNow() {
        val seconds = deltaTSeconds(2024.5)
        assertTrue(seconds in 60.0..80.0, "$seconds")
        assertTrue(deltaTSeconds(1900.0) in -5.0..0.0)
        assertTrue((Moon.newMoonsBetween(Instant.parse("2024-01-01T00:00:00Z"), Instant.parse("2024-02-01T00:00:00Z")).single() - Instant.parse("2024-01-11T11:57:00Z")).absoluteValue < 2.hours)
    }
}
