package io.github.aughtone.readable.fields

import io.github.aughtone.datetime.format.TextWidth
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals

/** Zone names by region, which need the zone database the JVM has; the browser tests have none. */
class ReadableFieldsZoneTest {

    private val toronto = TimeZone.of("America/Toronto")
    private val english = localeFor("en-US")!!

    @Test
    fun anInstantNamesItsZoneAtThatMoment() {
        val summer = LocalDateTime(2024, 6, 1, 16, 5).toInstant(toronto)
        val winter = LocalDateTime(2024, 1, 15, 16, 5).toInstant(toronto)
        assertEquals("4:05 PM EDT", summer.formatReadableFields("hmz", english, toronto))
        assertEquals("4:05 PM EST", winter.formatReadableFields("hmz", english, toronto))
        assertEquals("4:05 PM Eastern Daylight Time", summer.formatReadableFields("hmzzzz", english, toronto))
        assertEquals(
            "Jun 1, 4:05 PM EDT",
            summer.formatReadableFields(english, toronto, is24HourFormat = false) {
                month(TextWidth.Abbreviated); day(); hour(); minute(); timeZone(TextWidth.Abbreviated)
            },
        )
    }
}
