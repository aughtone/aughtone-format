package io.github.aughtone.datetime.format.resources

import io.github.aughtone.datetime.format.resources.values.text.TextResourceMap
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ResourceLookupTest {

    @Test
    fun testAmPmLookupFallback() {
        // en-ZA should fall back to en if en-ZA is not defined
        val localeZA = Locale(languageCode = "en", regionCode = "ZA", displayName = "English (South Africa)")
        val amPmZA = Resources.getAmPmStrings(localeZA)
        
        val localeEN = Locale(languageCode = "en", displayName = "English")
        val amPmEN = Resources.getAmPmStrings(localeEN)
        
        assertEquals(amPmEN.am, amPmZA.am)
    }

    @Test
    fun testCaching() {
        val locale = Locale(languageCode = "en", regionCode = "US", displayName = "English (US)")
        val first = Resources.getAmPmStrings(locale)
        val second = Resources.getAmPmStrings(locale)
        
        // They should be the exact same instance if cached
        // Note: This depends on whether the map returns the same instance or a new one each time.
        // Since we are caching the resolved value in Resources.kt, it should be the same.
        assertNotNull(first)
        assertEquals(first, second)
    }

    @Test
    fun testRegionOnlyLookup() {
        // If we have a locale with only a region, it should try to find it
        val locale = Locale(languageCode = "", regionCode = "US", displayName = "US Only")
        val amPm = Resources.getAmPmStrings(locale)
        assertNotNull(amPm)
    }

    @Test
    fun testAllSupportedLanguages() {
        // Verify that we can get resources for all languages defined in the map
        TextResourceMap.map.keys.forEach { tag ->
            if (tag.isEmpty()) return@forEach
            val language = tag.substringBefore('-')
            val region = if (tag.contains('-')) tag.substringAfter('-') else null
            val locale = Locale(languageCode = language, regionCode = region, displayName = "Test $tag")
            val amPm = Resources.getAmPmStrings(locale)
            val text = Resources.getText(locale)
            assertNotNull(amPm, "Failed to load AM/PM strings for $tag")
            assertNotNull(text, "Failed to load TextResource for $tag")
        }
    }
}
