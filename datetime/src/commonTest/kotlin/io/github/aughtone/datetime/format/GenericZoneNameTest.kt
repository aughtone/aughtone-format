package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Instant

/** Generic, instant-independent zone names: "Eastern Time", the same all year. */
class GenericZoneNameTest {

    private fun zone(id: String): TimeZone? = runCatching { TimeZone.of(id) }.getOrNull()
    private val english = Locale(languageCode = "en", regionCode = "US", displayName = "en-US")
    private val french = Locale(languageCode = "fr", displayName = "fr")

    @Test
    fun torontoIsEasternTimeInEnglish() {
        val toronto = zone("America/Toronto") ?: return
        assertEquals("Eastern Time", toronto.genericDisplayName(TextWidth.Full, english))
        assertEquals("ET", toronto.genericDisplayName(TextWidth.Abbreviated, english))
    }

    @Test
    fun frenchHasItsOwnName() {
        val toronto = zone("America/Toronto") ?: return
        val name = toronto.genericDisplayName(TextWidth.Full, french)
        assertTrue(name.startsWith("heure") && "Est" in name, name)
    }

    @Test
    fun theGenericNameIsTheSameInWinterAndSummer() {
        val toronto = zone("America/Toronto") ?: return
        val winter = toronto.displayName(Instant.parse("2024-01-15T12:00:00Z"), TextWidth.Full, english)
        val summer = toronto.displayName(Instant.parse("2024-07-15T12:00:00Z"), TextWidth.Full, english)
        // The specific name changes with the season; the generic one names neither.
        assertTrue(winter != summer, "$winter / $summer")
        val generic = toronto.genericDisplayName(TextWidth.Full, english)
        assertTrue(generic != winter && generic != summer, generic)
    }

    @Test
    fun everyZoneGetsANameAndNoneThrows() {
        for (id in listOf("UTC", "Etc/GMT+5", "Europe/Berlin", "Asia/Kolkata", "Africa/Casablanca", "America/Argentina/Buenos_Aires", "Pacific/Chatham")) {
            val tz = zone(id) ?: continue
            for (width in TextWidth.entries) for (locale in listOf(english, french)) {
                assertTrue(tz.genericDisplayName(width, locale).isNotBlank(), "$id $width ${locale.displayName}")
            }
        }
    }

    @Test
    fun inuktitutGetsWhateverThePlatformGives() {
        val toronto = zone("America/Toronto") ?: return
        val inuktitut = Locale(languageCode = "iu", regionCode = "CA", displayName = "iu-CA")
        assertTrue(toronto.genericDisplayName(TextWidth.Full, inuktitut).isNotBlank())
    }
}
