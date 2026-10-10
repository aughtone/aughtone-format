package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Instant

class ZoneResolutionTest {

    private val english = Locale(languageCode = "en", regionCode = "US", displayName = "English (US)")

    // Named zones need a time-zone database, which js and wasm test runtimes may not have.
    private fun zone(id: String): TimeZone? = runCatching { TimeZone.of(id) }.getOrNull()

    @Test
    fun theRepeatedHourAfterClocksGoBackIsNamedForItsOwnInstant() {
        val toronto = zone("America/Toronto") ?: return
        // 06:30Z is 01:30 for the second time that night: clocks went back at 06:00Z.
        val afterTheChange = Instant.parse("2024-11-03T06:30:00Z")
        val beforeTheChange = Instant.parse("2024-11-03T05:30:00Z")
        fun format(instant: Instant) = instant.format(
            dateStyle = DateTimeStyle.None, timeStyle = DateTimeStyle.Long,
            locale = english, timeZone = toronto, is24HourFormat = false,
        )
        assertEquals("1:30:00 AM EST", format(afterTheChange))
        assertEquals("1:30:00 AM EDT", format(beforeTheChange))
    }

    @Test
    fun aZoneAtZeroOffsetResolvesItsOwnAbbreviation() {
        val london = zone("Europe/London") ?: return
        assertEquals("GMT", TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(london, UtcOffset.ZERO, english))
        assertTrue(TimeZoneAbbreviationLookup.getTimeZoneFullName(london, UtcOffset.ZERO, english) != "Z")
    }

    @Test
    fun aDateIsFormattedAsItselfEvenWhereThatDayWasSkipped() {
        // Samoa skipped 30 December 2011 when it crossed the date line.
        val apia = zone("Pacific/Apia") ?: return
        assertEquals("12/30/2011", LocalDate(2011, 12, 30).format(DateTimeStyle.Short, locale = english, timeZone = apia))
    }
}
