package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Date-time and instant ranges. */
class LocalDateTimeRangeTest {

    private fun locale(language: String, region: String? = null) =
        Locale(languageCode = language, regionCode = region, displayName = listOfNotNull(language, region).joinToString("-"))

    private val english = locale("en", "US")
    private val dash = " – "
    private val march5 = LocalDate(2024, 3, 5)

    private fun at(date: LocalDate, hour: Int, minute: Int = 0) = LocalDateTime(date.year, date.month, date.day, hour, minute)

    private fun range(start: LocalDateTime, end: LocalDateTime, locale: Locale = english, date: DateTimeStyle = DateTimeStyle.Long,
                      time: DateTimeStyle = DateTimeStyle.Short, twentyFourHour: Boolean = false) =
        start.formatRange(end, date, time, locale, TimeZone.UTC, twentyFourHour, NumberingSystem.LATN)

    private fun single(value: LocalDateTime, locale: Locale = english, date: DateTimeStyle = DateTimeStyle.Long,
                       time: DateTimeStyle = DateTimeStyle.Short, twentyFourHour: Boolean = false) =
        value.format(date, time, locale, TimeZone.UTC, twentyFourHour, numberingSystem = NumberingSystem.LATN)

    @Test
    fun oneDayWritesTheDateOnceAndThenTheTimeRange() {
        assertEquals("March 5, 2024 9:00 AM${dash}5:00 PM", range(at(march5, 9), at(march5, 17)))
        assertEquals("March 5, 2024 9:00${dash}11:30 AM", range(at(march5, 9), at(march5, 11, 30)))
        assertEquals("5. März 2024 09:00–17:00 Uhr", range(at(march5, 9), at(march5, 17), locale("de"), twentyFourHour = true))
    }

    @Test
    fun theTimeRangeIsTheOneLocalTimeWrites() {
        val start = at(march5, 9)
        val end = at(march5, 17)
        val date = single(start, time = DateTimeStyle.None)
        val times = start.time.formatRange(end.time, DateTimeStyle.Short, english, TimeZone.UTC, false, NumberingSystem.LATN)
        assertEquals("$date $times", range(start, end))
    }

    @Test
    fun differentDaysWriteEachEndInFull() {
        val start = at(march5, 22)
        val end = at(LocalDate(2024, 3, 6), 2)
        assertEquals("${single(start)}${dash}${single(end)}", range(start, end))
        assertEquals("March 5, 2024 10:00 PM${dash}March 6, 2024 2:00 AM", range(start, end))
    }

    @Test
    fun equalValuesFormatAsOneDateTime() {
        assertEquals(single(at(march5, 9)), range(at(march5, 9), at(march5, 9)))
    }

    @Test
    fun noTimeStyleFormatsTheDatesAsADateRange() {
        val text = range(at(march5, 9), at(LocalDate(2024, 3, 7), 17), time = DateTimeStyle.None)
        assertEquals("March 5${dash}7, 2024", text)
    }

    @Test
    fun noDateStyleFormatsTheTimesAlone() {
        assertEquals("9:00 AM${dash}5:00 PM", range(at(march5, 9), at(march5, 17), date = DateTimeStyle.None))
    }

    @Test
    fun instantsAreShownInTheGivenZone() {
        val start = at(march5, 9).toInstant(TimeZone.UTC)
        val end = at(march5, 17).toInstant(TimeZone.UTC)
        val text = start.formatRange(end, DateTimeStyle.Long, DateTimeStyle.Short, english, TimeZone.UTC, false, NumberingSystem.LATN)
        assertEquals("March 5, 2024 9:00 AM${dash}5:00 PM", text)
        assertEquals(start.format(DateTimeStyle.Long, DateTimeStyle.Short, english, TimeZone.UTC, false, numberingSystem = NumberingSystem.LATN),
            start.formatRange(start, DateTimeStyle.Long, DateTimeStyle.Short, english, TimeZone.UTC, false, NumberingSystem.LATN))
    }

    @Test
    fun eachEndNamesItsOwnZoneAcrossADaylightSavingChange() {
        // Skipped where the runtime has no time-zone database.
        val newYork = runCatching { TimeZone.of("America/New_York") }.getOrNull() ?: return
        // Clocks go forward at 02:00 on 10 March 2024.
        val day = LocalDate(2024, 3, 10)
        val start = at(day, 1).toInstant(newYork)
        val end = at(day, 4).toInstant(newYork)
        val text = start.formatRange(end, DateTimeStyle.Long, DateTimeStyle.Long, english, newYork, false, NumberingSystem.LATN)
        val startTime = start.format(DateTimeStyle.None, DateTimeStyle.Long, english, newYork, false, numberingSystem = NumberingSystem.LATN)
        val endTime = end.format(DateTimeStyle.None, DateTimeStyle.Long, english, newYork, false, numberingSystem = NumberingSystem.LATN)
        assertTrue(startTime != endTime && startTime.endsWith("EST") && endTime.endsWith("EDT"), "$startTime / $endTime")
        assertEquals("March 10, 2024 $startTime${dash}$endTime", text)
    }

    @Test
    fun everyBundledLanguageFormatsSameDayAndCrossDayRanges() {
        val languages = "af ar az be bg ca cs da de el en es et eu fa fi fr gl he hi hr hu hy id is it iu ja ka kk ko lt lv mk ms nb nl nn pl pt ro ru sk sl sq sr sv sw th tr uk uz vi zh".split(" ")
        for (language in languages) for (clock in listOf(false, true)) {
            val sameDay = range(at(march5, 9), at(march5, 17), locale(language), twentyFourHour = clock)
            val crossDay = range(at(march5, 22), at(LocalDate(2024, 3, 6), 2), locale(language), twentyFourHour = clock)
            for (text in listOf(sameDay, crossDay)) {
                assertTrue(!text.contains('\'') && !text.contains("  ") && !text.contains("{"), "$language: \"$text\"")
            }
            assertTrue(sameDay.length < crossDay.length, "$language: same day \"$sameDay\" should write the date once, cross day \"$crossDay\"")
        }
    }
}
