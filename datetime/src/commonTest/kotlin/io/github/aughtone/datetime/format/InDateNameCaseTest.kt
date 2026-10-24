package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

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
        assertEquals("15 мар. 2024 г.", date("ru", DateTimeStyle.Medium))
    }

    @Test
    fun grammaticalFormsInsideADateAreKept() {
        assertEquals("15 марта 2024 г.", date("ru", DateTimeStyle.Long))
        assertEquals("15. března 2024", date("cs", DateTimeStyle.Long))
    }

    @Test
    fun namesShownOnTheirOwnKeepTheirStandAloneForm() {
        assertEquals("Marzo", Month.MARCH.displayName(TextWidth.Full, locale("es")))
        assertEquals("Viernes", DayOfWeek.FRIDAY.displayName(TextWidth.Full, locale("es")))
        assertEquals("Март", Month.MARCH.displayName(TextWidth.Full, locale("ru")))
    }
}
