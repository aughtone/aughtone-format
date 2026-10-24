package io.github.aughtone.readable.duration

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.relative.RelativeStyle
import kotlin.test.Test
import kotlin.test.assertTrue
import io.github.aughtone.types.locale.Locale
import kotlin.time.Duration.Companion.milliseconds
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
        assertEquals("1 小時", 60.minutes.formatReadable(locale = Locales.TraditionalChinese))
        assertEquals("1 週", 7.days.formatReadable(locale = Locales.TraditionalChinese))

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

    @Test
    fun aValueThatRoundsUpToAWholeLargerUnitIsShownInThatUnit() {
        assertEquals("1 minute", 59.6.seconds.formatReadable(locale = Locales.English))
        assertEquals("59 seconds", 59.4.seconds.formatReadable(locale = Locales.English))
        assertEquals("1 hour", 59.6.minutes.formatReadable(locale = Locales.English))
        assertEquals("1 day", 23.6.hours.formatReadable(locale = Locales.English))
        assertEquals("1 week", 6.6.days.formatReadable(locale = Locales.English))
        assertEquals("1 month", 29.6.days.formatReadable(locale = Locales.English))
    }

    @Test
    fun elevenAndAHalfMonthsIsAYearNotTwelveMonths() {
        assertEquals("11 months", 340.days.formatReadable(locale = Locales.English))
        assertEquals("1 year", 345.days.formatReadable(locale = Locales.English))
        assertEquals("1 year", 364.days.formatReadable(locale = Locales.English))
        assertEquals("1 year", 365.days.formatReadable(locale = Locales.English))
    }

    @Test
    fun aNegativeDurationReadsAsItsMagnitude() {
        assertEquals("5 minutes", (-5).minutes.formatReadable(locale = Locales.English))
    }

    @Test
    fun shortStyleAbbreviatesTheUnitInEveryLanguage() {
        assertEquals("5m", 5.minutes.formatReadable(RelativeStyle.Short, Locales.English))
        assertEquals("5 Min.", 5.minutes.formatReadable(RelativeStyle.Short, Locales.German))
        assertEquals("5\u00A0min", 5.minutes.formatReadable(RelativeStyle.Short, Locales.French))
        assertEquals("5 分", 5.minutes.formatReadable(RelativeStyle.Short, Locales.Japanese))
    }

    @Test
    fun underHalfASecondReadsAsNow() {
        assertEquals("now", 200.milliseconds.formatReadable(locale = Locales.English))
        assertEquals("now", 200.milliseconds.formatReadable(RelativeStyle.Short, Locales.English))
        assertEquals("now", 0.milliseconds.formatReadable(locale = Locale(languageCode = "en", regionCode = "ZA", displayName = "en-ZA")))
        assertEquals("jetzt", 0.milliseconds.formatReadable(locale = Locales.German))
        assertEquals("maintenant", (-300).milliseconds.formatReadable(locale = Locales.French))
        assertEquals("1 second", 500.milliseconds.formatReadable(locale = Locales.English))
    }

    @Test
    fun chineseScriptTagsUseTheirScriptsWords() {
        val simplified = Locale(languageCode = "zh", regionCode = "Hans", displayName = "zh-Hans")
        val traditional = Locale(languageCode = "zh", regionCode = "Hant", displayName = "zh-Hant")
        assertEquals("5小时", 5.hours.formatReadable(locale = simplified).replace(" ", ""))
        assertEquals("5小時", 5.hours.formatReadable(locale = traditional).replace(" ", ""))
    }

    @Test
    fun japaneseTraditionalChineseAndThaiSpaceTheUnit() {
        assertEquals("5 分", 5.minutes.formatReadable(locale = Locales.Japanese))
        assertEquals("5 小時", 5.hours.formatReadable(locale = Locale(languageCode = "zh", regionCode = "TW", displayName = "zh-TW")))
        assertEquals("5 ชั่วโมง", 5.hours.formatReadable(locale = Locale(languageCode = "th", displayName = "th")))
        assertEquals("5小时", 5.hours.formatReadable(locale = Locales.Chinese))
    }

    @Test
    fun arabicNamesOneAndTwoWithoutADigit() {
        val arabic = Locales.Arabic
        assertEquals("دقيقة", 1.minutes.formatReadable(locale = arabic))
        assertEquals("دقيقتان", 2.minutes.formatReadable(locale = arabic))
        assertEquals("5 دقائق", 5.minutes.formatReadable(locale = arabic))
    }

    @Test
    fun chineseCountsMonthsAndMinutesAsSpans() {
        // A bare 月 after a count names a month of the year: "2月" is February.
        assertEquals("2个月", 60.days.formatReadable(locale = Locales.Chinese))
        assertEquals("5分钟", 5.minutes.formatReadable(locale = Locales.Chinese))
        val traditional = Locale(languageCode = "zh", regionCode = "TW", displayName = "zh-TW")
        assertEquals("2 個月", 60.days.formatReadable(locale = traditional))
    }

    @Test
    fun macedonianUsesTheCountFormAfterANumber() {
        assertEquals("2 часа", 2.hours.formatReadable(locale = Locale(languageCode = "mk", displayName = "mk")))
    }

    @Test
    fun southAsianLanguagesUseTheirOwnWords() {
        for (language in listOf("bn", "gu", "kn", "ml", "mr", "pa", "ta", "te", "ur")) {
            val text = 5.minutes.formatReadable(locale = Locale(languageCode = language, displayName = language))
            assertTrue(text.none { it in 'a'..'z' || it in 'A'..'Z' }, "$language: \"$text\"")
        }
    }

    @Test
    fun slavicAndBalticLanguagesInflectTheUnitByCount() {
        assertEquals(listOf("1 den", "2 dny", "5 dní"), listOf(1, 2, 5).map { it.days.formatReadable(locale = Locales.Czech) })
        assertEquals(listOf("1 deň", "3 dni", "5 dní"), listOf(1, 3, 5).map { it.days.formatReadable(locale = Locales.Slovak) })
        assertEquals(listOf("1 dan", "2 dana", "5 dana"), listOf(1, 2, 5).map { it.days.formatReadable(locale = Locales.Croatian) })
        assertEquals(listOf("1 сат", "3 сата", "5 сати", "21 сат"), listOf(1, 3, 5, 21).map { it.hours.formatReadable(locale = Locales.Serbian) })
        assertEquals(listOf("1 diena", "2 dienos", "5 dienos"), listOf(1, 2, 5).map { it.days.formatReadable(locale = Locales.Lithuanian) })
        assertEquals(listOf("10 valandų", "21 valanda"), listOf(10, 21).map { it.hours.formatReadable(locale = Locales.Lithuanian) })
        assertEquals("2 týdny", 14.days.formatReadable(locale = Locales.Czech))
    }

    @Test
    fun slovenianUsesItsDualFewAndGeneralPluralForms() {
        assertEquals(
            listOf("1 ura", "2 uri", "3 ure", "5 ur", "21 ur"),
            listOf(1, 2, 3, 5, 21).map { it.hours.formatReadable(locale = Locales.Slovenian) }
        )
        assertEquals(listOf("1 sekunda", "2 sekundi", "4 sekunde", "5 sekund"), listOf(1, 2, 4, 5).map { it.seconds.formatReadable(locale = Locales.Slovenian) })
        assertEquals(listOf("1 dan", "2 dneva", "3 dni"), listOf(1, 2, 3).map { it.days.formatReadable(locale = Locales.Slovenian) })
    }

    @Test
    fun latvianUsesItsZeroFormForTensAndTeens() {
        assertEquals(
            listOf("1 minūte", "2 minūtes", "10 minūšu", "11 minūšu", "21 minūte", "22 minūtes"),
            listOf(1, 2, 10, 11, 21, 22).map { it.minutes.formatReadable(locale = Locales.Latvian) }
        )
    }

    @Test
    fun romanianAddsDeFromTwenty() {
        assertEquals(
            listOf("1 minut", "2 minute", "19 minute", "20 de minute"),
            listOf(1, 2, 19, 20).map { it.minutes.formatReadable(locale = Locales.Romanian) }
        )
    }

    @Test
    fun icelandicAndMacedonianUseTheSingularAfterTwentyOne() {
        assertEquals("21 mínúta", 21.minutes.formatReadable(locale = Locales.Icelandic))
        assertEquals("21 минута", 21.minutes.formatReadable(locale = Locales.Macedonian))
    }

    @Test
    fun everyBundledLanguageHasItsOwnUnitWords() {
        assertEquals(listOf("1 minuut", "2 minuten", "2 uur"), listOf(1.minutes, 2.minutes, 2.hours).map { it.formatReadable(locale = Locales.Dutch) })
        assertEquals(listOf("1 minuut", "2 minute", "2 dae"), listOf(1.minutes, 2.minutes, 2.days).map { it.formatReadable(locale = Locales.Afrikaans) })
        assertEquals(listOf("1 menit", "2 hari"), listOf(1.minutes, 2.days).map { it.formatReadable(locale = Locales.Indonesian) })
        assertEquals(listOf("1 minit", "2 hari"), listOf(1.minutes, 2.days).map { it.formatReadable(locale = Locales.Malay) })
        assertEquals(listOf("dakika 1", "siku 2", "miezi 2"), listOf(1.minutes, 2.days, 60.days).map { it.formatReadable(locale = Locales.Swahili) })
        assertEquals(listOf("1 dəqiqə", "2 həftə"), listOf(1.minutes, 14.days).map { it.formatReadable(locale = Locales.Azerbaijani) })
        assertEquals("2 anos", 730.days.formatReadable(locale = Locales.Galician))
        assertEquals("2 vjet", 730.days.formatReadable(locale = Locales.Albanian))
    }

    @Test
    fun hebrewUsesThePluralForTwo() {
        assertEquals(listOf("1 דקה", "2 דקות", "3 דקות"), listOf(1, 2, 3).map { it.minutes.formatReadable(locale = Locales.Hebrew) })
    }

    @Suppress("DEPRECATION")
    @Test
    fun testDeprecatedCompatibility() {
        assertEquals("5 seconds", 5.seconds.toReadableString(Locales.English))
        assertEquals("1 week", 7.days.toReadableString(Locales.English))
    }
}
