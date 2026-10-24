package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Time ranges, checked against the Unicode CLDR interval formats for the same locale. */
class LocalTimeRangeTest {

    private fun locale(language: String, region: String? = null) =
        Locale(languageCode = language, regionCode = region, displayName = listOfNotNull(language, region).joinToString("-"))

    private val english = locale("en", "US")

    /** CLDR's English interval separator: an en dash between thin spaces. */
    private val dash = "\u2009–\u2009"
    private val nine = LocalTime(9, 0)
    private val elevenThirty = LocalTime(11, 30)
    private val five = LocalTime(17, 0)

    private fun range(start: LocalTime, end: LocalTime, locale: Locale, twentyFourHour: Boolean, style: DateTimeStyle = DateTimeStyle.Short) =
        start.formatRange(end, style, locale = locale, timeZone = TimeZone.UTC, is24HourFormat = twentyFourHour)

    @Test
    fun englishWritesTheDayPeriodOnceWhenBothEndsShareIt() {
        assertEquals("9:00${dash}11:30 AM", range(nine, elevenThirty, english, twentyFourHour = false))
        assertEquals("9:00${dash}9:30 AM", range(nine, LocalTime(9, 30), english, twentyFourHour = false))
    }

    @Test
    fun englishWritesBothDayPeriodsWhenTheyDiffer() {
        assertEquals("9:00 AM${dash}5:00 PM", range(nine, five, english, twentyFourHour = false))
    }

    @Test
    fun twentyFourHourRangesFollowEachLanguagesSeparator() {
        assertEquals("09:00–17:00", range(nine, five, locale("en", "GB"), twentyFourHour = true))
        assertEquals("09:00${dash}17:00", range(nine, five, english, twentyFourHour = true))
        assertEquals("09:00–17:00 Uhr", range(nine, five, locale("de"), twentyFourHour = true))
        assertEquals("09:00${dash}17:00", range(nine, five, locale("fr"), twentyFourHour = true))
        assertEquals("09:00 ~ 17:00", range(nine, five, locale("ko"), twentyFourHour = true))
    }

    @Test
    fun theDayPeriodComesFirstWhereTheLanguagePutsItFirst() {
        // Korean and Japanese CLDR patterns put the marker before the hour; the library's own
        // marker words are used, as in its single times.
        assertEquals("오전 9:00~11:30", range(nine, elevenThirty, locale("ko"), twentyFourHour = false))
        assertEquals("午前9時00分～11時30分", range(nine, elevenThirty, locale("ja"), twentyFourHour = false))
        assertEquals("9時00分～17時00分", range(nine, five, locale("ja"), twentyFourHour = true))
    }

    @Test
    fun aRangeThatPassesMidnightIsTwoPlainTimes() {
        assertEquals("22:00–02:00 Uhr", range(LocalTime(22, 0), LocalTime(2, 0), locale("de"), twentyFourHour = true))
        assertEquals("10:00 PM${dash}2:00 AM", range(LocalTime(22, 0), LocalTime(2, 0), english, twentyFourHour = false))
    }

    @Test
    fun equalTimesFormatAsOneTime() {
        assertEquals(nine.format(DateTimeStyle.Short, locale = english, timeZone = TimeZone.UTC, is24HourFormat = false), range(nine, nine, english, twentyFourHour = false))
        // Seconds are not shown at the Short style, so they do not make two times different.
        assertEquals("09:00", range(nine, LocalTime(9, 0, 30), locale("en", "GB"), twentyFourHour = true))
    }

    @Test
    fun longerStylesJoinTwoCompleteTimes() {
        val start = nine.format(DateTimeStyle.Medium, locale = english, timeZone = TimeZone.UTC, is24HourFormat = false)
        val end = five.format(DateTimeStyle.Medium, locale = english, timeZone = TimeZone.UTC, is24HourFormat = false)
        assertEquals("$start$dash$end", range(nine, five, english, twentyFourHour = false, style = DateTimeStyle.Medium))
        assertEquals("9:00:00 AM${dash}5:00:00 PM", range(nine, five, english, twentyFourHour = false, style = DateTimeStyle.Medium))
    }

    @Test
    fun digitsFollowTheLocalesNumberingSystem() {
        val arabic = locale("ar", "EG")
        assertEquals("09:00–17:00", nine.formatRange(five, DateTimeStyle.Short, locale = arabic, timeZone = TimeZone.UTC, is24HourFormat = true, numberingSystem = NumberingSystem.LATN))
        assertTrue(range(nine, five, arabic, twentyFourHour = true).none { it in '0'..'9' })
    }

    @Test
    fun everyBundledLanguageFormatsEveryRangeKind() {
        val languages = "af ar az be bg ca cs da de el en es et eu fa fi fr gl he hi hr hu hy id is it iu ja ka kk ko lt lv mk ms nb nl nn pl pt ro ru sk sl sq sr sv sw th tr uk uz vi zh".split(" ")
        val pairs = listOf(nine to LocalTime(9, 30), nine to elevenThirty, nine to five, LocalTime(22, 0) to LocalTime(2, 0))
        for (language in languages) for (twentyFourHour in listOf(true, false)) for ((start, end) in pairs) {
            val text = range(start, end, locale(language), twentyFourHour)
            val single = start.format(DateTimeStyle.Short, locale = locale(language), timeZone = TimeZone.UTC, is24HourFormat = twentyFourHour)
            assertTrue(text.length > single.length, "$language: \"$text\" is no longer than one time")
            assertTrue(!text.contains('\'') && !text.contains("  "), "$language: \"$text\"")
            assertTrue(text.none { it in 'a'..'z' && language in setOf("ja", "zh", "ko", "ar", "fa", "he", "th", "ru", "uk", "bg", "el", "hy", "ka") }, "$language: \"$text\" carries a pattern letter")
        }
    }
}
