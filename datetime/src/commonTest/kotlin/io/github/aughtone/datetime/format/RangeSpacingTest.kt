package io.github.aughtone.datetime.format

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/** A range never breaks before its dash; `separators` and `noBreak` decide the rest. */
class RangeSpacingTest {

    private fun locale(tag: String): Locale = localeFor(tag)!!
    private val june1 = LocalDate(2026, 6, 1)
    private val sep30 = LocalDate(2026, 9, 30)

    @Test
    fun theSpaceBeforeTheDashNeverBreaks() {
        // CLDR's thin spaces; the shared year is written once, after the end.
        assertEquals("Jun 1 – Sep 30, 2026", june1.formatRange(sep30, DateTimeStyle.Medium, locale("en-US")))
        assertEquals("Mon – Fri", DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, locale("en-US")))
        // Russian spaces its dash with ordinary spaces; the one before becomes U+00A0.
        val russian = june1.formatRange(sep30, DateTimeStyle.Long, locale("ru"))
        val dash = russian.indexOf('–')
        assertEquals(" – ", russian.substring(dash - 1, dash + 2), russian)
    }

    @Test
    fun aLocaleWithNoSpaceAroundItsSeparatorIsUnchanged() {
        for (tag in listOf("sv", "ja")) {
            val range = june1.formatRange(sep30, DateTimeStyle.Medium, locale(tag))
            assertFalse(' ' in range || ' ' in range, "$tag: $range")
        }
    }

    @Test
    fun noBreakKeepsTheSpaceAfterTheDashFromBreakingToo() {
        assertEquals("Mon – Fri", DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, locale("en-US"), noBreak = true))
        assertEquals("09:00–17:00 Uhr", LocalTime(9, 0).formatRange(LocalTime(17, 0), locale = locale("de"), is24HourFormat = true, noBreak = true))
    }

    @Test
    fun typographicSeparatorsKeepEachEndWhole() {
        val range = june1.formatRange(sep30, DateTimeStyle.Medium, locale("en-US"), separators = Separators.Typographic)
        assertEquals("Jun 1 – Sep 30, 2026", range)
        // The one place the range can break is after its dash.
        assertEquals(" ", range.filter { it == ' ' || it == ' ' })
    }

    @Test
    fun asciiSeparatorsWritePlainSpacesInsideTheEnds() {
        val end = june1.formatRange(sep30, DateTimeStyle.Long, locale("ru")).substringAfter('–')
        assertFalse(' ' in end || ' ' in end, end)
    }

    @Test
    fun theStringJoinUsesTheLocalesSeparator() {
        assertEquals("1. Juni – 30. Sept.", formatRange("1. Juni", "30. Sept.", locale("de")))
        assertEquals("8:00～Sunset", formatRange("8:00", "Sunset", locale("ja")))
        assertEquals("a – b", formatRange("a", "b", locale("en"), noBreak = true))
    }

    @Test
    fun theStringJoinLeavesItsEndsAsGiven() {
        assertEquals("Jun 1 – Sep 30", formatRange("Jun 1", "Sep 30", locale("en")))
    }

    @Test
    fun theStringJoinMatchesATypedRangeThatFallsBack() {
        val next = LocalDate(2027, 9, 30)
        val typed = june1.formatRange(next, DateTimeStyle.Full, locale("en-US"))
        val joined = formatRange(june1.format(DateTimeStyle.Full, locale = locale("en-US")), next.format(DateTimeStyle.Full, locale = locale("en-US")), locale("en-US"))
        assertEquals(typed, joined)
    }
}
