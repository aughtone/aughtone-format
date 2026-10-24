package io.github.aughtone.readable.duration

import io.github.aughtone.readable.relative.RelativeStyle
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/** Duration phrases checked against what Unicode CLDR says, through the generated fixtures. */
class DurationCldrTest {

    private fun durationOf(unit: String, count: Int): Duration = when (unit) {
        "second" -> count.seconds
        "minute" -> count.minutes
        "hour" -> count.hours
        "day" -> count.days
        "week" -> (count * 7).days
        "month" -> (count * 30).days
        "year" -> (count * 365).days
        else -> error("unknown unit $unit")
    }

    /** A locale for a CLDR identifier, as the library would be given it. */
    private fun localeOf(id: String): Locale = when (id) {
        "nb" -> Locale(languageCode = "nb", displayName = id)
        else -> Locale(languageCode = id.substringBefore('-'), regionCode = id.substringAfter('-', "").ifEmpty { null }, displayName = id)
    }

    private fun check(fixture: Map<String, String>, style: RelativeStyle) {
        assertTrue(fixture.isNotEmpty())
        for ((language, cases) in fixture) {
            val locale = localeOf(language)
            for (line in cases.trim().lines()) {
                val (unit, count, phrase) = line.split('|')
                // Zero seconds reads as "now", which the fixture does not sample.
                assertEquals(phrase, durationOf(unit, count.toInt()).formatReadable(style, locale), "$language $style: $count $unit")
            }
        }
    }

    @Test
    fun longPhrasesMatchCldr() = check(durationCldrFixture, RelativeStyle.Long)

    @Test
    fun shortPhrasesMatchCldr() = check(shortDurationCldrFixture, RelativeStyle.Short)
}
