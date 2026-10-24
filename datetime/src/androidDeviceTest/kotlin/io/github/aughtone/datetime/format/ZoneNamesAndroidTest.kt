package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Instant

/** Zone names through Android's own ICU, run on a device or emulator. */
class ZoneNamesAndroidTest {

    private val english = Locale(languageCode = "en", regionCode = "US", displayName = "en-US")
    private val french = Locale(languageCode = "fr", displayName = "fr")
    private val toronto = TimeZone.of("America/Toronto")

    @Test
    fun genericNamesComeFromTheDevice() {
        assertEquals("Eastern Time", toronto.genericDisplayName(TextWidth.Full, english))
        assertEquals("ET", toronto.genericDisplayName(TextWidth.Abbreviated, english))
        val name = toronto.genericDisplayName(TextWidth.Full, french)
        assertTrue(name.startsWith("heure") && "Est" in name, name)
    }

    @Test
    fun specificNamesFollowTheSeason() {
        assertEquals("Eastern Standard Time", toronto.displayName(Instant.parse("2024-01-15T12:00:00Z"), TextWidth.Full, english))
        assertEquals("Eastern Daylight Time", toronto.displayName(Instant.parse("2024-07-15T12:00:00Z"), TextWidth.Full, english))
    }

    @Test
    fun windowsZonesNeedNoPlatform() {
        assertEquals("America/Toronto", TimeZone.ianaIdForWindowsName("Eastern Standard Time", "CA"))
        assertEquals("Eastern Standard Time", TimeZone.windowsNameForIanaId("America/Toronto"))
    }
}
