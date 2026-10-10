package io.github.aughtone.readable

import io.github.aughtone.readable.PluralCategory.Few
import io.github.aughtone.readable.PluralCategory.Many
import io.github.aughtone.readable.PluralCategory.One
import io.github.aughtone.readable.PluralCategory.Other
import io.github.aughtone.readable.PluralCategory.Two
import io.github.aughtone.readable.PluralCategory.Zero
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Cardinal plural categories, checked against the Unicode CLDR plural rules for whole numbers.
 */
class PluralCategoryTest {

    private fun categories(locale: Locale, vararg numbers: Long): List<PluralCategory> =
        numbers.map { pluralCategoryFor(locale, it) }

    @Test
    fun czechAndSlovakUseAFewFormForTwoToFour() {
        for (locale in listOf(Locales.Czech, Locales.Slovak)) {
            assertEquals(listOf(Other, One, Few, Few, Few, Other, Other, Other), categories(locale, 0, 1, 2, 3, 4, 5, 12, 22))
        }
    }

    @Test
    fun croatianAndSerbianFollowTheLastDigitExceptInTheTeens() {
        for (locale in listOf(Locales.Croatian, Locales.Serbian)) {
            assertEquals(
                listOf(Other, One, Few, Few, Other, Other, Other, One, Few, Other, One, Other),
                categories(locale, 0, 1, 2, 4, 5, 11, 12, 21, 22, 25, 101, 111)
            )
        }
    }

    @Test
    fun slovenianHasADualAndCyclesEveryHundred() {
        assertEquals(
            listOf(Other, One, Two, Few, Few, Other, Other, One, Two, Few, Other),
            categories(Locales.Slovenian, 0, 1, 2, 3, 4, 5, 11, 101, 102, 103, 105)
        )
    }

    @Test
    fun lithuanianSeparatesTheTeensAndTheTens() {
        assertEquals(
            listOf(Other, One, Few, Few, Other, Other, Other, Other, One, Few, Other),
            categories(Locales.Lithuanian, 0, 1, 2, 9, 10, 11, 15, 20, 21, 22, 30)
        )
    }

    @Test
    fun latvianHasAZeroFormForTensAndTeens() {
        assertEquals(
            listOf(Zero, One, Other, Zero, Zero, Zero, Zero, One, Other, One, Zero),
            categories(Locales.Latvian, 0, 1, 2, 10, 11, 15, 20, 21, 22, 101, 111)
        )
    }

    @Test
    fun icelandicAndMacedonianAreSingularWheneverTheNumberEndsInOne() {
        for (locale in listOf(Locales.Icelandic, Locales.Macedonian)) {
            assertEquals(listOf(Other, One, Other, Other, One, One, Other), categories(locale, 0, 1, 2, 11, 21, 101, 111))
        }
    }

    @Test
    fun romanianHasAFewFormBelowTwenty() {
        assertEquals(
            listOf(Few, One, Few, Few, Other, Other, Few, Few, Other),
            categories(Locales.Romanian, 0, 1, 2, 19, 20, 100, 101, 119, 120)
        )
    }

    @Test
    fun hindiPersianAndArmenianTreatZeroAsSingular() {
        for (locale in listOf(Locales.Hindi, Locales.Persian, Locales.Armenian)) {
            assertEquals(listOf(One, One, Other), categories(locale, 0, 1, 2))
        }
    }

    @Test
    fun hebrewHasOnlySingularDualAndPlural() {
        assertEquals(listOf(Other, One, Two, Other, Other, Other), categories(Locales.Hebrew, 0, 1, 2, 3, 20, 100))
    }

    @Test
    fun rulesThatWereAlreadyRightStayRight() {
        assertEquals(listOf(Many, One, Few, Many, Many, One, Few), categories(Locales.Russian, 0, 1, 2, 5, 11, 21, 22))
        assertEquals(listOf(Many, One, Few, Many, Many, Many, Few), categories(Locales.Polish, 0, 1, 2, 5, 12, 21, 22))
        assertEquals(listOf(Zero, One, Two, Few, Many, Other), categories(Locales.Arabic, 0, 1, 2, 3, 11, 100))
        assertEquals(listOf(One, One, Other), categories(Locales.French, 0, 1, 2))
        assertEquals(listOf(Other, One, Other), categories(Locales.English, 0, 1, 2))
        assertEquals(listOf(Other, Other), categories(Locales.Japanese, 1, 2))
    }

    @Test
    fun aNegativeCountTakesTheCategoryOfItsMagnitude() {
        assertEquals(One, pluralCategoryFor(Locales.English, -1))
        assertEquals(Few, pluralCategoryFor(Locales.Russian, -22))
        assertEquals(Few, pluralCategoryFor(Locales.Czech, -3))
    }
}
