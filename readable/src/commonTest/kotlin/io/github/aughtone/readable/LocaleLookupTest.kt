package io.github.aughtone.readable

import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.relative.relativeTimeConfigFor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.time.Duration.Companion.hours

/** A locale's data is chosen by its script before its region. */
class LocaleLookupTest {

    private fun tags(tag: String) = lookupTags(localeOfTag(tag))

    private fun hours(tag: String) = 5.hours.formatReadable(locale = localeOfTag(tag)).replace(" ", "")

    private fun hoursAgo(tag: String) =
        relativeTimeConfigFor(localeOfTag(tag)).formatter((-5).hours, true).replace(" ", "")

    @Test
    fun theScriptDecidesBeforeTheRegion() {
        assertEquals(listOf("zh-Hant-US", "zh-US", "zh-Hant", "zh"), tags("zh-Hant-US"))
        assertEquals(listOf("zh-Hans-HK", "zh-Hans", "zh"), tags("zh-Hans-HK"))
        assertEquals(listOf("zh-Hant", "zh"), tags("zh-Hant"))
    }

    @Test
    fun aRegionImpliesItsScript() {
        assertEquals(listOf("zh-Hant-TW", "zh-TW", "zh-Hant", "zh"), tags("zh-TW"))
        assertEquals(listOf("zh-Hant-MO", "zh-MO", "zh-Hant", "zh"), tags("zh-MO"))
        assertEquals(listOf("zh-Hans-SG", "zh-SG", "zh-Hans", "zh"), tags("zh-SG"))
        assertEquals(listOf("zh-Hans", "zh"), tags("zh"))
        assertEquals(listOf("sr-Latn-ME", "sr-ME", "sr-Latn", "sr"), tags("sr-ME"))
        assertEquals(listOf("sr-Latn-RS", "sr-Latn", "sr"), tags("sr-Latn-RS"))
    }

    @Test
    fun aLanguageWithOneScriptKeepsItsChain() {
        assertEquals(listOf("de-CH", "de"), tags("de-CH"))
        assertEquals(listOf("en"), tags("en"))
    }

    @Test
    fun traditionalChineseReadsTraditionalData() {
        for (tag in listOf("zh-Hant", "zh-Hant-US", "zh-Hant-MO", "zh-MO", "zh-TW", "zh-US")) {
            assertEquals("5小時", hours(tag), tag)
            assertEquals(hoursAgo("zh-Hant"), hoursAgo(tag), tag)
        }
        assertNotEquals(hoursAgo("zh"), hoursAgo("zh-Hant"))
    }

    @Test
    fun simplifiedChineseReadsSimplifiedData() {
        for (tag in listOf("zh", "zh-Hans", "zh-Hans-HK", "zh-SG", "zh-CN")) {
            assertEquals("5小时", hours(tag), tag)
            assertEquals(hoursAgo("zh"), hoursAgo(tag), tag)
        }
    }

    @Test
    fun localesThatDifferOnlyInScriptAreCachedApart() {
        assertEquals("5小时", hours("zh"))
        assertEquals("5小時", hours("zh-Hant"))
        assertEquals("5小时", hours("zh"))
        assertNotEquals(lookupKey(localeOfTag("zh-Hans")), lookupKey(localeOfTag("zh-Hant")))
        assertNotEquals(lookupKey(localeOfTag("zh-Hans-HK")), lookupKey(localeOfTag("zh-HK")))
        assertEquals(lookupKey(localeOfTag("zh-TW")), lookupKey(localeOfTag("zh-Hant-TW")))
    }
}
