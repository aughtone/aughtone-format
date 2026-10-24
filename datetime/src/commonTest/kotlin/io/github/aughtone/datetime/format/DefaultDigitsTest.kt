package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** The digits a date is written in when no numbering system is given: CLDR's default for the locale. */
class DefaultDigitsTest {

    private fun locale(language: String, region: String? = null) =
        Locale(languageCode = language, regionCode = region, displayName = listOfNotNull(language, region).joinToString("-"))

    private val date = LocalDate(2024, 3, 15)
    private fun short(locale: Locale, numberingSystem: NumberingSystem? = null) =
        date.format(DateTimeStyle.Short, locale = locale, timeZone = TimeZone.UTC, numberingSystem = numberingSystem)

    private fun digitsOf(text: String) = text.filter { it.isDigit() }.toSet()

    @Test
    fun hindiThaiAndUrduDefaultToLatinDigits() {
        for (locale in listOf(locale("hi"), locale("th"), locale("ur"), locale("ur", "PK"))) {
            assertTrue(digitsOf(short(locale)).all { it in '0'..'9' }, "${locale.displayName}: ${short(locale)}")
        }
    }

    @Test
    fun arabicDigitsDependOnTheRegion() {
        assertTrue(digitsOf(short(locale("ar"))).all { it in '0'..'9' }, short(locale("ar")))
        assertTrue(digitsOf(short(locale("ar", "MA"))).all { it in '0'..'9' }, short(locale("ar", "MA")))
        assertTrue(digitsOf(short(locale("ar", "EG"))).all { it in '٠'..'٩' }, short(locale("ar", "EG")))
        assertTrue(digitsOf(short(locale("ar", "SA"))).all { it in '٠'..'٩' }, short(locale("ar", "SA")))
    }

    @Test
    fun persianBengaliAndIndianUrduKeepNativeDigits() {
        assertTrue(digitsOf(short(locale("fa"))).all { it in '۰'..'۹' }, short(locale("fa")))
        assertTrue(digitsOf(short(locale("ur", "IN"))).all { it in '۰'..'۹' }, short(locale("ur", "IN")))
        assertTrue(digitsOf(short(locale("bn"))).all { it in '০'..'৯' }, short(locale("bn")))
    }

    @Test
    fun anExplicitNumberingSystemStillWins() {
        assertTrue(digitsOf(short(locale("hi"), NumberingSystem.DEVA)).all { it in '०'..'९' })
        assertTrue(digitsOf(short(locale("th"), NumberingSystem.THAI)).all { it in '๐'..'๙' })
        assertTrue(digitsOf(short(locale("ar", "EG"), NumberingSystem.LATN)).all { it in '0'..'9' })
    }
}
