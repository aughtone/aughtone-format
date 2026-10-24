package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Locale date and time patterns carry literal text — "de", "г.", "時" — and that text has to reach
 * the output exactly, without being read as pattern letters and without leaking its quote marks.
 */
class LocalizedPatternTest {

    private val date = LocalDate(2024, 3, 15)
    private val time = LocalTime(16, 8, 39)

    private fun locale(tag: String): Locale = localeFor(tag)!!

    private val bundledLanguages = listOf(
        "af", "ar", "az", "be", "bg", "ca", "cs", "da", "de", "el", "en", "es", "et", "eu", "fa", "fi", "fr",
        "gl", "he", "hi", "hr", "hu", "hy", "id", "is", "it", "iu", "ja", "ka", "kk", "ko", "lt", "lv", "mk",
        "ms", "nb", "nl", "nn", "pl", "pt", "ro", "ru", "sk", "sl", "sq", "sr", "sv", "sw", "th", "tr", "uk",
        "uz", "vi", "zh", "pt-BR", "es-MX", "fr-CA", "en-GB", "zh-TW",
    )

    @Test
    fun everyBundledLanguageFormatsEveryDateStyleWithoutThrowingOrLeakingQuotes() {
        for (tag in bundledLanguages) {
            val locale = localeFor(tag) ?: continue
            for (style in listOf(DateTimeStyle.Short, DateTimeStyle.Medium, DateTimeStyle.Long, DateTimeStyle.Full)) {
                val text = date.format(style, locale = locale, timeZone = TimeZone.UTC)
                assertFalse(text.contains('\''), "$tag $style leaks a quote mark: $text")
                assertFalse(text.contains("  "), "$tag $style has a doubled space: $text")
                assertTrue(text.isNotBlank(), "$tag $style is blank")
            }
        }
    }

    @Test
    fun everyBundledLanguageFormatsEveryTimeStyleWithoutLeakingQuotesOrDoubledSpaces() {
        for (tag in bundledLanguages) {
            val locale = localeFor(tag) ?: continue
            for (style in listOf(DateTimeStyle.Short, DateTimeStyle.Medium, DateTimeStyle.Long, DateTimeStyle.Full)) {
                for (twentyFourHour in listOf(true, false)) {
                    val text = time.format(style, locale = locale, timeZone = TimeZone.UTC, is24HourFormat = twentyFourHour)
                    assertFalse(text.contains('\''), "$tag $style leaks a quote mark: $text")
                    assertFalse(text.contains("  "), "$tag $style has a doubled space: $text")
                    assertFalse(text.contains("()"), "$tag $style has empty parentheses: $text")
                }
            }
        }
    }

    @Test
    fun spanishAndPortugueseLongDatesKeepTheirConnectingWords() {
        assertEquals("15 de marzo de 2024", date.format(DateTimeStyle.Long, locale = locale("es"), timeZone = TimeZone.UTC))
        assertTrue(date.format(DateTimeStyle.Long, locale = locale("pt"), timeZone = TimeZone.UTC).startsWith("15 de "))
    }

    @Test
    fun russianLongDateEndsWithTheYearMarkerWithoutQuotes() {
        val text = date.format(DateTimeStyle.Long, locale = locale("ru"), timeZone = TimeZone.UTC)
        // The pattern joins the year and its marker with a narrow no-break space.
        assertTrue(text.endsWith("2024\u202Fг."), text)
    }

    @Test
    fun japaneseFullTimeKeepsItsUnitCharacters() {
        val text = time.format(DateTimeStyle.Full, locale = locale("ja"), timeZone = TimeZone.UTC, is24HourFormat = true)
        assertTrue(text.startsWith("16時08分39秒"), text)
    }

    @Test
    fun bulgarianLongTimeKeepsItsHourMarker() {
        val text = time.format(DateTimeStyle.Long, locale = locale("bg"), timeZone = TimeZone.UTC, is24HourFormat = true)
        assertTrue(text.startsWith("16:08:39 ч."), text)
    }

    @Test
    fun twoDigitYearsShowTheLastTwoDigitsInAnyCentury() {
        assertEquals("31.12.99", LocalDate(1999, 12, 31).format(DateTimeStyle.Short, locale = locale("de"), timeZone = TimeZone.UTC))
        assertEquals("15.03.24", date.format(DateTimeStyle.Short, locale = locale("de"), timeZone = TimeZone.UTC))
        assertEquals("01.01.05", LocalDate(2105, 1, 1).format(DateTimeStyle.Short, locale = locale("de"), timeZone = TimeZone.UTC))
    }

    @Test
    fun aYearBelowOneThousandIsNotZeroPadded() {
        assertEquals("December 25, 800", LocalDate(800, 12, 25).format(DateTimeStyle.Long, locale = locale("en"), timeZone = TimeZone.UTC))
    }

    @Test
    fun yearsBeforeTheCommonEraAreCountedFromOneAndAlwaysNamed() {
        val english = locale("en")
        assertTrue(LocalDate(-500, 1, 1).format(DateTimeStyle.Full, locale = english, timeZone = TimeZone.UTC).endsWith("January 1, 501 BCE"))
        assertTrue(LocalDate(0, 6, 15).format(DateTimeStyle.Full, locale = english, timeZone = TimeZone.UTC).endsWith("June 15, 1 BCE"))
        assertEquals("June 15, 1 BCE", LocalDate(0, 6, 15).format(DateTimeStyle.Long, locale = english, timeZone = TimeZone.UTC))
        assertTrue(LocalDate(1, 6, 15).format(DateTimeStyle.Full, locale = english, timeZone = TimeZone.UTC).endsWith("June 15, 1"))
    }

    @Test
    fun theEraIsShownOnlyWhenAskedFor() {
        val english = locale("en")
        val date = LocalDate(1952, 1, 12)
        assertEquals("Saturday, January 12, 1952", date.format(DateTimeStyle.Full, locale = english, timeZone = TimeZone.UTC))
        assertEquals("Saturday, January 12, 1952 CE", date.format(DateTimeStyle.Full, locale = english, timeZone = TimeZone.UTC, showEra = true))
        assertEquals("January 12, 1952 CE", date.format(DateTimeStyle.Long, locale = english, timeZone = TimeZone.UTC, showEra = true))
        val german = date.format(DateTimeStyle.Long, locale = locale("de"), timeZone = TimeZone.UTC, showEra = true)
        assertTrue(german.startsWith("12. Januar 1952 ") && german.length > "12. Januar 1952 ".length, german)
        val custom = io.github.aughtone.datetime.format.resources.values.EraNames(bce = "BC", ce = "AD")
        assertEquals("January 12, 1952 AD", date.format(DateTimeStyle.Long, locale = english, timeZone = TimeZone.UTC, eraNames = custom))
        assertEquals("Saturday, January 12, 1952 12:00 PM", LocalDateTime(1952, 1, 12, 12, 0)
            .format(DateTimeStyle.Full, DateTimeStyle.Short, english, TimeZone.UTC, is24HourFormat = false))
        assertEquals("Saturday, January 12, 1952 CE 12:00 PM", LocalDateTime(1952, 1, 12, 12, 0)
            .format(DateTimeStyle.Full, DateTimeStyle.Short, english, TimeZone.UTC, is24HourFormat = false, showEra = true))
    }

    @Test
    fun aRegionVariantKeepsItsLanguagesPatterns() {
        assertEquals(
            date.format(DateTimeStyle.Long, locale = locale("fr"), timeZone = TimeZone.UTC),
            date.format(DateTimeStyle.Long, locale = locale("fr-CA"), timeZone = TimeZone.UTC),
        )
        val spanishUs = Locale(languageCode = "es", regionCode = "US", displayName = "Spanish (US)")
        assertEquals("15 de marzo de 2024", date.format(DateTimeStyle.Long, locale = spanishUs, timeZone = TimeZone.UTC))
    }

    @Test
    fun englishOutsideNorthAmericaPutsTheDayFirst() {
        val fifthOfMarch = LocalDate(2024, 3, 5)
        fun formats(tag: String): List<String> {
            val english = Locale(languageCode = "en", regionCode = tag.substringAfter('-'), displayName = tag)
            return listOf(DateTimeStyle.Short, DateTimeStyle.Medium, DateTimeStyle.Long)
                .map { fifthOfMarch.format(it, locale = english, timeZone = TimeZone.UTC) }
        }

        assertEquals(listOf("05/03/2024", "5 Mar 2024", "5 March 2024"), formats("en-GB"))
        assertEquals(listOf("5/3/24", "5 Mar 2024", "5 March 2024"), formats("en-AU"))
        assertEquals(listOf("05/03/2024", "5 Mar 2024", "5 March 2024"), formats("en-NZ"))
        assertEquals(listOf("05/03/2024", "5 Mar 2024", "5 March 2024"), formats("en-IE"))
        assertEquals(listOf("05/03/24", "5 Mar 2024", "5 March 2024"), formats("en-IN"))
        assertEquals(listOf("2024/03/05", "05 Mar 2024", "05 March 2024"), formats("en-ZA"))
        // North America is unchanged.
        assertEquals(listOf("3/5/2024", "Mar 5, 2024", "March 5, 2024"), formats("en-US"))
        assertEquals(listOf("2024-03-05", "Mar 5, 2024", "March 5, 2024"), formats("en-CA"))
    }

    @Test
    fun frenchCanadaKeepsItsIsoShortDate() {
        assertEquals("2024-03-05", LocalDate(2024, 3, 5).format(DateTimeStyle.Short, locale = locale("fr-CA"), timeZone = TimeZone.UTC))
    }

    @Test
    fun monthsInsideADateTakeTheFormTheLanguageUsesAfterADayNumber() {
        val fifthOfMarch = LocalDate(2024, 3, 5)
        fun long(tag: String) = fifthOfMarch.format(DateTimeStyle.Long, locale = locale(tag), timeZone = TimeZone.UTC)
        assertEquals("5 марта 2024\u202Fг.", long("ru"))
        assertEquals("5 березня 2024\u202Fр.", long("uk"))
        assertEquals("5 marca 2024", long("pl"))
        assertEquals("5. března 2024", long("cs"))
        assertEquals("5. maaliskuuta 2024", long("fi"))
    }

    @Test
    fun aMonthNamedOnItsOwnKeepsItsDictionaryForm() {
        assertEquals("Март", kotlinx.datetime.Month.MARCH.displayName(TextWidth.Full, locale("ru")))
        assertEquals("marzec", kotlinx.datetime.Month.MARCH.displayName(TextWidth.Full, locale("pl")))
    }

    @Test
    fun aCombinedFormatWithOnlyOneHalfHasNoStraySpace() {
        val english = Locale(languageCode = "en", regionCode = "US", displayName = "English (US)")
        val moment = kotlinx.datetime.LocalDateTime(2024, 3, 5, 16, 8)
        fun format(dateStyle: DateTimeStyle, timeStyle: DateTimeStyle) =
            io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
                .getLocalDateTimeFormat(dateStyle, timeStyle, english, TimeZone.UTC, twentyFourHour = false)
                .format(moment)
        assertEquals("4:08 PM", format(DateTimeStyle.None, DateTimeStyle.Short))
        assertEquals("3/5/2024", format(DateTimeStyle.Short, DateTimeStyle.None))
        assertEquals("3/5/2024 4:08 PM", format(DateTimeStyle.Short, DateTimeStyle.Short))
    }
}
