package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Date and weekday ranges, checked against the Unicode CLDR interval formats. */
class LocalDateRangeTest {

    private fun locale(language: String, region: String? = null) =
        Locale(languageCode = language, regionCode = region, displayName = listOfNotNull(language, region).joinToString("-"))

    private val english = locale("en", "US")
    private val dash = " – "
    private val march3 = LocalDate(2024, 3, 3)
    private val march5 = LocalDate(2024, 3, 5)
    private val april2 = LocalDate(2024, 4, 2)

    private fun range(start: LocalDate, end: LocalDate, locale: Locale, style: DateTimeStyle = DateTimeStyle.Long) =
        start.formatRange(end, style, locale, numberingSystem = NumberingSystem.LATN)

    @Test
    fun englishWritesTheSharedMonthAndYearOnce() {
        assertEquals("March 3${dash}5, 2024", range(march3, march5, english))
        assertEquals("March 3${dash}April 2, 2024", range(march3, april2, english))
        assertEquals("December 30, 2024${dash}January 2, 2025", range(LocalDate(2024, 12, 30), LocalDate(2025, 1, 2), english))
        assertEquals("Mar 3${dash}5, 2024", range(march3, march5, english, DateTimeStyle.Medium))
    }

    @Test
    fun otherLanguagesPutTheSharedPartsWhereTheyBelong() {
        assertEquals("3${dash}5 March 2024", range(march3, march5, locale("en", "GB")))
        assertEquals("3.–5. März 2024", range(march3, march5, locale("de")))
        assertEquals("2024年3月3日～5日", range(march3, march5, locale("ja")))
        assertEquals("2024年3月3日${dash}5日", range(march3, march5, locale("zh")))
        assertEquals("2024年3月3日${dash}4月2日", range(march3, april2, locale("zh")))
        assertEquals("2024년 3월 3일~5일", range(march3, march5, locale("ko")))
        assertEquals("3.–5. 3. 2024", range(march3, march5, locale("cs")))
        assertEquals("3–5 марта 2024 г.", range(march3, march5, locale("ru")))
    }

    @Test
    fun spanishKeepsItsConnectingWords() {
        assertTrue(range(march3, march5, locale("es")).let { it.startsWith("3–5 de ") && it.endsWith(" de 2024") }, range(march3, march5, locale("es")))
    }

    @Test
    fun fullDatesNameBothWeekdaysAndTheEraOnce() {
        val text = range(march3, march5, english, DateTimeStyle.Full)
        assertEquals("Sunday, March 3${dash}Tuesday, March 5, 2024 Common Era", text)
    }

    @Test
    fun equalDatesFormatAsOneDate() {
        assertEquals(march3.format(DateTimeStyle.Long, locale = english, timeZone = TimeZone.UTC), range(march3, march3, english))
    }

    @Test
    fun anEndBeforeTheStartIsWrittenInTheOrderGiven() {
        assertEquals("March 5${dash}3, 2024", range(march5, march3, english))
    }

    @Test
    fun shortDatesUseTheLibrarysYearWidth() {
        // The library writes the US short date with a four-digit year; so does the range.
        assertEquals("3/3/2024${dash}3/5/2024".replace(dash, " – "), range(march3, march5, english, DateTimeStyle.Short).replace(dash, " – "))
    }

    @Test
    fun weekdayRangesJoinTheLocalesNames() {
        assertEquals("Mon${dash}Fri", DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, english))
        assertEquals("Mo${dash}Fr", DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, locale("de")))
        assertEquals("Friday${dash}Monday", DayOfWeek.FRIDAY.formatRange(DayOfWeek.MONDAY, TextWidth.Full, english))
        assertEquals("Monday", DayOfWeek.MONDAY.formatRange(DayOfWeek.MONDAY, TextWidth.Full, english))
    }

    @Test
    fun everyBundledLanguageFormatsEveryStyleAndDifference() {
        val languages = "af ar az be bg ca cs da de el en es et eu fa fi fr gl he hi hr hu hy id is it iu ja ka kk ko lt lv mk ms nb nl nn pl pt ro ru sk sl sq sr sv sw th tr uk uz vi zh".split(" ")
        val pairs = listOf(march3 to march5, march3 to april2, LocalDate(2024, 12, 30) to LocalDate(2025, 1, 2))
        for (language in languages) for (style in listOf(DateTimeStyle.Short, DateTimeStyle.Medium, DateTimeStyle.Long, DateTimeStyle.Full)) for ((start, end) in pairs) {
            val text = range(start, end, locale(language), style)
            assertTrue(text.contains('5') || text.contains('2'), "$language $style: \"$text\" lost the end")
            assertTrue(!text.contains('\'') && !text.contains("  ") && !text.contains("{"), "$language $style: \"$text\"")
            assertTrue(text != start.format(style, locale = locale(language), timeZone = TimeZone.UTC, numberingSystem = NumberingSystem.LATN), "$language $style: \"$text\" shows one date")
        }
    }
}
