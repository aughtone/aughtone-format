package io.github.aughtone.readable.duration

import io.github.aughtone.readable.Locales
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DurationTest {

    @Test
    fun testScaling() {
        assertEquals("5 seconds", 5.seconds.formatReadable(locale = Locales.English))
        assertEquals("59 seconds", 59.seconds.formatReadable(locale = Locales.English))
        assertEquals("1 minute", 60.seconds.formatReadable(locale = Locales.English))
        assertEquals("1 hour", 60.minutes.formatReadable(locale = Locales.English))
        assertEquals("1 day", 24.hours.formatReadable(locale = Locales.English))
        assertEquals("6 days", 6.days.formatReadable(locale = Locales.English))
        assertEquals("1 week", 7.days.formatReadable(locale = Locales.English))
        assertEquals("29 days", 29.days.formatReadable(locale = Locales.English))
        assertEquals("1 month", 30.days.formatReadable(locale = Locales.English))
    }

    @Test
    fun testRounding() {
        // 8 days / 7 = 1.14 -> 1 week
        assertEquals("1 week", 8.days.formatReadable(locale = Locales.English))
        // 10 days / 7 = 1.42 -> 1 week
        assertEquals("1 week", 10.days.formatReadable(locale = Locales.English))
        // 11 days / 7 = 1.57 -> 2 weeks
        assertEquals("2 weeks", 11.days.formatReadable(locale = Locales.English))
    }

    @Test
    fun testLargeDurations() {
        // 544 hours = 22.66 days = 3.23 weeks -> 3 weeks
        assertEquals("3 weeks", 544.hours.formatReadable(locale = Locales.English))
    }

    @Test
    fun testTraditionalChineseVariant() {
        // zh-TW uses Traditional characters (小時, 週) not Simplified (小时, 周)
        assertEquals("1小時", 60.minutes.formatReadable(locale = Locales.TraditionalChinese))
        assertEquals("1週", 7.days.formatReadable(locale = Locales.TraditionalChinese))

        // zh (Simplified) uses different characters
        assertEquals("1小时", 60.minutes.formatReadable(locale = Locales.Chinese))
    }

    @Test
    fun testAllowListedLocalesReachTheirOwnUnits() {
        // Regression: these locales had full buildDurationFormatter cases but were missing from
        // isDurationTagSupported(), so they were unreachable and fell back to English "1 hour".
        assertEquals("1 ordu", 60.minutes.formatReadable(locale = Locales.Basque))
        assertEquals("1 valanda", 60.minutes.formatReadable(locale = Locales.Lithuanian))
        assertEquals("1 stunda", 60.minutes.formatReadable(locale = Locales.Latvian))
        assertEquals("1 orë", 60.minutes.formatReadable(locale = Locales.Albanian))
        assertEquals("1 saat", 60.minutes.formatReadable(locale = Locales.Azerbaijani))
        assertEquals("1 soat", 60.minutes.formatReadable(locale = Locales.Uzbek))
        assertEquals("1 сағат", 60.minutes.formatReadable(locale = Locales.Kazakh))
        assertEquals("1 ժամ", 60.minutes.formatReadable(locale = Locales.Armenian))
        assertEquals("1 საათი", 60.minutes.formatReadable(locale = Locales.Georgian))
        assertEquals("1 ᐃᑲᕐᕋᖅ", 60.minutes.formatReadable(locale = Locales.Inuktitut))
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        assertEquals("5 seconds", 5.seconds.toReadableString(Locales.English))
        assertEquals("1 week", 7.days.toReadableString(Locales.English))
    }
}
