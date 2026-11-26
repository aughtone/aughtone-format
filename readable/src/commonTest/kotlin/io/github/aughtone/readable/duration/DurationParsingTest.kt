package io.github.aughtone.readable.duration

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.relative.RelativeStyle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class DurationParsingTest {

    private fun failure(text: String): DurationParseOutcome.Failed =
        Duration.parseReadableWithOutcome(text) as? DurationParseOutcome.Failed
            ?: error("\"$text\" read as ${Duration.parseReadableOrNull(text)}")

    /** Every spelling in the documented table, with the length of one of that unit. */
    private val spellings: Map<Duration, List<String>> = mapOf(
        1.nanoseconds to listOf("ns", "nano", "nanos", "nanosecond", "nanoseconds"),
        1.microseconds to listOf("µs", "us", "micro", "micros", "microsecond", "microseconds"),
        1.milliseconds to listOf("ms", "milli", "millis", "millisecond", "milliseconds"),
        1.seconds to listOf("s", "sec", "secs", "second", "seconds"),
        1.minutes to listOf("m", "min", "mins", "minute", "minutes"),
        1.hours to listOf("h", "hr", "hrs", "hour", "hours"),
        1.days to listOf("d", "day", "days"),
        7.days to listOf("w", "wk", "wks", "week", "weeks"),
        30.days to listOf("mo", "mos", "month", "months"),
        365.days to listOf("y", "yr", "yrs", "year", "years"),
    )

    @Test
    fun everyUnitSpellingReadsWithAndWithoutASpaceInAnyCase() {
        for ((length, names) in spellings) for (name in names) {
            assertEquals(length * 3, Duration.parseReadable("3$name"), "3$name")
            assertEquals(length * 3, Duration.parseReadable("3 $name"), "3 $name")
            assertEquals(length * 3, Duration.parseReadable("3 ${name.uppercase()}"), "3 ${name.uppercase()}")
            assertEquals(length * 3, Duration.parseReadable("3${name.replaceFirstChar { it.uppercaseChar() }}"), name)
        }
    }

    @Test
    fun configurationStyleValuesRead() {
        assertEquals(10.seconds, Duration.parseReadable("10s"))
        assertEquals(250.milliseconds, Duration.parseReadable("250ms"))
        assertEquals(5.minutes, Duration.parseReadable("5 minutes"))
        assertEquals(2.days, Duration.parseReadable("2 days"))
        assertEquals(Duration.ZERO, Duration.parseReadable("0s"))
    }

    @Test
    fun compoundTextSumsItsPartsInAnyOrder() {
        assertEquals(90.minutes, Duration.parseReadable("1h 30m"))
        assertEquals(90.minutes, Duration.parseReadable("1h30m"))
        assertEquals(90.minutes, Duration.parseReadable("30 minutes 1 hour"))
        assertEquals(1.days + 2.hours + 3.minutes + 4.seconds + 5.milliseconds, Duration.parseReadable("1d 2h 3m 4s 5ms"))
    }

    @Test
    fun aDecimalNumberReads() {
        assertEquals(90.minutes, Duration.parseReadable("1.5h"))
        assertEquals(1500.milliseconds, Duration.parseReadable("1.5 seconds"))
        assertEquals(250.milliseconds, Duration.parseReadable("0.25s"))
        assertEquals(84.hours, Duration.parseReadable("0.5 weeks"))
    }

    @Test
    fun aLeadingSignAppliesToTheWholeValue() {
        assertEquals(-(90.minutes), Duration.parseReadable("-1h 30m"))
        assertEquals(-(5.seconds), Duration.parseReadable("- 5s"))
        assertEquals(-(5.seconds), Duration.parseReadable("\u22125s"))
        assertEquals(5.seconds, Duration.parseReadable("+5s"))
    }

    @Test
    fun spacesAroundAndNoBreakSpacesAreIgnored() {
        assertEquals(5.minutes, Duration.parseReadable("  5 minutes \n"))
        assertEquals(5.minutes, Duration.parseReadable("5\u00A0minutes"))
        assertEquals(5.minutes, Duration.parseReadable("5\u202Fminutes"))
    }

    @Test
    fun whatFormatReadableWritesInEnglishReadsBack() {
        val shown = listOf(
            5.seconds, 59.seconds, 1.minutes, 45.minutes, 1.hours, 23.hours, 1.days, 6.days, 7.days, 14.days, 21.days,
            30.days, 90.days, 365.days, 730.days,
        )
        for (duration in shown) for (style in listOf(RelativeStyle.Long, RelativeStyle.Short)) {
            val text = duration.formatReadable(style = style, locale = Locales.English)
            assertEquals(duration, Duration.parseReadable(text), "\"$text\"")
        }
        // A rounded duration reads back as what the text shows, and writes the same text again.
        for (duration in listOf(90.minutes, 11.days, 40.days, 500.days, 100.milliseconds)) {
            for (style in listOf(RelativeStyle.Long, RelativeStyle.Short)) {
                val text = duration.formatReadable(style = style, locale = Locales.English)
                assertEquals(text, Duration.parseReadable(text).formatReadable(style = style, locale = Locales.English))
            }
        }
        assertEquals(2.hours, Duration.parseReadable(90.minutes.formatReadable(locale = Locales.English)))
        assertEquals(Duration.ZERO, Duration.parseReadable("now"))
        assertEquals(Duration.ZERO, Duration.parseReadable(" Now "))
    }

    @Test
    fun eachFailureIsReportedWithItsPosition() {
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.Empty, 0), failure(""))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.Empty, 0), failure("   "))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingNumber, 0), failure("minutes"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingNumber, 1), failure("-"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingNumber, 0), failure(".5h"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingNumber, 3), failure("5m and 3s"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingNumber, 2), failure("5s-3s"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingUnit, 2), failure("10"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingUnit, 5), failure("1h 30"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingUnit, 1), failure("1,5h"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingUnit, 1), failure("1.h"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.UnknownUnit, 2), failure("5 fortnights"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.UnknownUnit, 2), failure("5 Minuten"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.RepeatedUnit, 5), failure("5m 10min"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.OutOfRange, 0), failure("99999999999999999999s"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.OutOfRange, 0), failure("9000000000000000000 years"))
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.MissingNumber, 0), failure("٥ minutes"))
    }

    @Test
    fun aLargeValueThatFitsADurationReads() {
        assertEquals(365.days * 1000, Duration.parseReadable("1000 years"))
    }

    @Test
    fun theFourEntryPointsAgree() {
        assertEquals(5.minutes, Duration.parseReadableOrNull("5 minutes"))
        assertNull(Duration.parseReadableOrNull("soon"))
        assertEquals(5.minutes, Duration.parseReadableOrElse("5 minutes") { error("not called") })
        assertEquals(30.seconds, Duration.parseReadableOrElse("soon") { 30.seconds })
        var seen: DurationParseOutcome.Failed? = null
        Duration.parseReadableOrElse("5 fortnights") { seen = it; Duration.ZERO }
        assertEquals(DurationParseOutcome.Failed(DurationParseFailure.UnknownUnit, 2), seen)
        assertEquals(DurationParseOutcome.Parsed(5.minutes), Duration.parseReadableWithOutcome("5 minutes"))
        val thrown = assertFailsWith<IllegalArgumentException> { Duration.parseReadable("5 fortnights") }
        assertTrue("5 fortnights" in thrown.message!! && "index 2" in thrown.message!!, thrown.message)
        for (text in listOf("", "10", "minutes", "5m 10min", "99999999999999999999s")) {
            assertFailsWith<IllegalArgumentException>(text) { Duration.parseReadable(text) }
        }
    }
}
