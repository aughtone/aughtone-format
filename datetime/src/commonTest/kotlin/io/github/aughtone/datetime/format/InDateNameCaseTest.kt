package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.formats.localeDayOfWeekNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeMonthNamesSource
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Month and weekday names inside a date are written as the language writes them there, which for
 * many languages is lower case; the same names shown on their own keep their stand-alone form.
 */
class InDateNameCaseTest {

    private fun locale(language: String) = Locale(languageCode = language, displayName = language)
    private val friday = LocalDate(2024, 3, 15)

    private fun date(language: String, style: DateTimeStyle) =
        friday.format(style, locale = locale(language), timeZone = TimeZone.UTC, numberingSystem = NumberingSystem.LATN)

    @Test
    fun romanceLanguagesWriteMonthsAndWeekdaysInLowerCaseInsideADate() {
        assertEquals("15 de marzo de 2024", date("es", DateTimeStyle.Long))
        assertEquals("viernes, 15 de marzo de 2024", date("es", DateTimeStyle.Full))
        assertEquals("15 mars 2024", date("fr", DateTimeStyle.Long))
        assertEquals("15 marzo 2024", date("it", DateTimeStyle.Long))
        assertEquals("15 de março de 2024", date("pt", DateTimeStyle.Long))
    }

    @Test
    fun abbreviatedNamesInsideADateFollowTheLanguageToo() {
        assertEquals("15 mar 2024", date("es", DateTimeStyle.Medium))
        assertEquals("15 мар. 2024 г.", date("ru", DateTimeStyle.Medium))
    }

    @Test
    fun grammaticalFormsInsideADateAreKept() {
        assertEquals("15 марта 2024 г.", date("ru", DateTimeStyle.Long))
        assertEquals("15. března 2024", date("cs", DateTimeStyle.Long))
    }

    @Test
    fun namesShownOnTheirOwnKeepTheirStandAloneForm() {
        assertEquals("Marzo", Month.MARCH.displayName(TextWidth.Full, locale("es")))
        assertEquals("Viernes", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("es")))
        assertEquals("Март", Month.MARCH.displayName(TextWidth.Full, locale("ru")))
    }

    @Test
    fun weekdaysOnTheirOwnAreCapitalisedWhereMonthsAre() {
        assertEquals("Vendredi", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("fr")))
        assertEquals("Ven.", DayOfWeek.FRIDAY.displayName(TextWidth.Abbreviated, locale("fr")))
        assertEquals("Sexta-feira", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("pt")))
        assertEquals("Sex.", DayOfWeek.FRIDAY.displayName(TextWidth.Abbreviated, locale("pt")))
        assertEquals("Perjantai", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("fi")))
        assertEquals("Pe", DayOfWeek.FRIDAY.displayName(TextWidth.Abbreviated, locale("fi")))
        assertEquals("Пятница", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("ru")))
        assertEquals("Пт", DayOfWeek.FRIDAY.displayName(TextWidth.Abbreviated, locale("ru")))
        assertEquals("Пʼятниця", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("uk")))
        assertEquals("Пт", DayOfWeek.FRIDAY.displayName(TextWidth.Abbreviated, locale("uk")))
    }

    @Test
    fun weekdaysInsideADateKeepTheFormTheLanguageWritesThere() {
        assertEquals("vendredi 15 mars 2024", date("fr", DateTimeStyle.Full))
        assertEquals("sexta-feira, 15 de março de 2024", date("pt", DateTimeStyle.Full))
        // A Finnish full date takes the weekday's stand-alone form; "perjantaina" is for other dates.
        assertEquals("perjantai 15. maaliskuuta 2024", date("fi", DateTimeStyle.Full))
        assertEquals("пятница, 15 марта 2024 г.", date("ru", DateTimeStyle.Full))
        assertEquals("пʼятниця, 15 березня 2024 р.", date("uk", DateTimeStyle.Full))
    }

    /**
     * A heading or a picker shows a month name beside a weekday name, so the two must agree in
     * case in every bundled language: both capitalised or both lower case.
     */
    @Test
    fun fullMonthAndWeekdayNamesOnTheirOwnAgreeInCaseInEveryLanguage() {
        // null for a name whose first character has no case, as in Arabic or Chinese.
        fun startsUpper(name: String): Boolean? {
            val first = name.first()
            return if (first.uppercaseChar() == first.lowercaseChar()) null else first.isUpperCase()
        }
        val disagreeing = localeMonthNamesSource.keys.intersect(localeDayOfWeekNamesSource.keys).filter { language ->
            val months = localeMonthNamesSource.getValue(language).value.full.names.mapNotNull(::startsUpper).toSet()
            val days = localeDayOfWeekNamesSource.getValue(language).value.full.names.mapNotNull(::startsUpper).toSet()
            months.isNotEmpty() && days.isNotEmpty() && months != days
        }
        assertTrue(disagreeing.isEmpty(), "month and weekday names differ in case in: $disagreeing")
    }
}
