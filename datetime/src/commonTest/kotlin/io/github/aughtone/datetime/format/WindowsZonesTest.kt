package io.github.aughtone.datetime.format

import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

/** Windows time-zone names and IANA identifiers, both ways, from CLDR's mapping. */
class WindowsZonesTest {

    @Test
    fun aWindowsNameGivesTheDefaultZone() {
        assertEquals("America/New_York", TimeZone.ianaIdForWindowsName("Eastern Standard Time"))
        assertEquals("Europe/London", TimeZone.ianaIdForWindowsName("GMT Standard Time"))
        assertEquals("Asia/Kolkata", TimeZone.ianaIdForWindowsName("India Standard Time"))
    }

    @Test
    fun aTerritoryChoosesItsOwnZone() {
        assertEquals("America/Toronto", TimeZone.ianaIdForWindowsName("Eastern Standard Time", "CA"))
        assertEquals("America/Toronto", TimeZone.ianaIdForWindowsName("Eastern Standard Time", "ca"))
        assertEquals("America/Nassau", TimeZone.ianaIdForWindowsName("Eastern Standard Time", "BS"))
        // A territory CLDR does not list for the name falls back to the default.
        assertEquals("America/New_York", TimeZone.ianaIdForWindowsName("Eastern Standard Time", "FR"))
    }

    @Test
    fun anIanaIdentifierGivesItsWindowsName() {
        assertEquals("Eastern Standard Time", TimeZone.windowsNameForIanaId("America/Toronto"))
        assertEquals("Eastern Standard Time", TimeZone.windowsNameForIanaId("America/Iqaluit"))
        assertEquals("Eastern Standard Time", TimeZone.windowsNameForIanaId("America/Detroit"))
        assertEquals("W. Europe Standard Time", TimeZone.windowsNameForIanaId("Europe/Berlin"))
    }

    @Test
    fun zonesUseTheirCurrentIanaNamesAndAcceptTheOlderOnes() {
        assertEquals("Asia/Kolkata", TimeZone.ianaIdForWindowsName("India Standard Time"))
        assertEquals("Asia/Ho_Chi_Minh", TimeZone.ianaIdForWindowsName("SE Asia Standard Time", "VN"))
        assertEquals("India Standard Time", TimeZone.windowsNameForIanaId("Asia/Kolkata"))
        assertEquals("India Standard Time", TimeZone.windowsNameForIanaId("Asia/Calcutta"))
        assertEquals("Eastern Standard Time", TimeZone.windowsNameForIanaId("US/Eastern"))
    }

    @Test
    fun everyReturnedIdentifierIsAZoneTheRuntimeKnows() {
        // Skipped where the runtime has no time-zone database.
        if (runCatching { TimeZone.of("America/New_York") }.isFailure) return
        for (name in windowsZoneNames()) {
            val id = TimeZone.ianaIdForWindowsName(name)!!
            assertTrue(runCatching { TimeZone.of(id) }.isSuccess, "\"$name\" → $id is not a known zone")
        }
    }

    @Test
    fun anythingCldrDoesNotListIsNull() {
        assertNull(TimeZone.ianaIdForWindowsName("Not A Zone"))
        assertNull(TimeZone.ianaIdForWindowsName("eastern standard time"))
        assertNull(TimeZone.ianaIdForWindowsName(""))
        assertNull(TimeZone.windowsNameForIanaId("Mars/Olympus_Mons"))
        assertNull(TimeZone.windowsNameForIanaId(""))
    }

    @Test
    fun theMappingRoundTripsForEveryDefaultZone() {
        for (name in windowsZoneNames()) {
            val id = TimeZone.ianaIdForWindowsName(name)
            assertTrue(id != null, "no default zone for \"$name\"")
            assertEquals(name, TimeZone.windowsNameForIanaId(id), "\"$name\" → $id")
        }
        assertTrue(windowsZoneNames().size > 100)
    }
}
