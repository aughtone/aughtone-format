@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.localeFor
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TimeZoneDisplayNameTest {

    private val en = localeFor("en")!!
    private val winter = Instant.parse("2024-01-15T12:00:00Z") // EST, -05:00
    private val summer = Instant.parse("2024-07-15T12:00:00Z") // EDT, -04:00

    // Named zones need the IANA tz database, which Kotlin/JS and wasmJs only
    // have when the app bundles it (e.g. @js-joda/timezone). The test module
    // does not, so TimeZone.of throws there; a null zone means "skip on this
    // target". The library function itself is platform-independent.
    private fun zone(id: String): TimeZone? = runCatching { TimeZone.of(id) }.getOrNull()

    @Test
    fun fullNameStandardTime() {
        val toronto = zone("America/Toronto") ?: return
        assertEquals("Eastern Standard Time", toronto.displayName(winter, TextWidth.Full, en))
    }

    @Test
    fun fullNameDaylightTime() {
        val toronto = zone("America/Toronto") ?: return
        assertEquals("Eastern Daylight Time", toronto.displayName(summer, TextWidth.Full, en))
    }

    @Test
    fun abbreviationStandardVsDaylight() {
        val toronto = zone("America/Toronto") ?: return
        assertEquals("EST", toronto.displayName(winter, TextWidth.Abbreviated, en))
        assertEquals("EDT", toronto.displayName(summer, TextWidth.Abbreviated, en))
    }

    @Test
    fun fullIsTheDefaultWidth() {
        val toronto = zone("America/Toronto") ?: return
        assertEquals(
            toronto.displayName(winter, TextWidth.Full, en),
            toronto.displayName(winter, locale = en),
        )
    }

    @Test
    fun uncuratedZoneGetsPlatformName() {
        // A real IANA zone outside the small English scaffolding now gets its name
        // from the platform CLDR (coverage over consistency), so it is a real
        // localized string, not the bare UTC offset the old bundled table fell
        // back to.
        val chagos = zone("Indian/Chagos") ?: return // +06:00, no DST, not in the scaffolding
        val name = chagos.displayName(winter, TextWidth.Full, en)
        val bareOffset = chagos.offsetAt(winter).toString() // "+06:00"
        assertTrue(name.isNotBlank(), "expected a platform name, got blank")
        assertNotEquals(bareOffset, name, "expected a platform name, not the bare offset")
    }
}
