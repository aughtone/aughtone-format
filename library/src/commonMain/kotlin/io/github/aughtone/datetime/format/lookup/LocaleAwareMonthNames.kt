package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.monthnames.EnMonthNames
import io.github.aughtone.datetime.format.resources.monthnames.EsMonthNames
import io.github.aughtone.datetime.format.resources.monthnames.FrMonthNames
import kotlinx.datetime.Month
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.number

object LocaleAwareMonthNames : LocaleAwareLookup<MonthNames> {
    /**
     * look up the month names for the given locale.
     * Defaults to english if the locale is not found.
     *
     * fales back to numeric if there was some sort of problem
     *
     * The order of precedence is: "en_rCA", "en", ""
     */
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: MonthNames?,
    ): MonthNames {
        val language = locale.language
        val region = locale.region

        return if (abbreviated) {
            (abbreviatedNames["${language}_r${region}"] ?: abbreviatedNames[language]
            ?: abbreviatedNames[""])
        } else {
            (fullNames["${language}_r${region}"] ?: fullNames[language] ?: fullNames[""])
        } ?: fallbackTo ?: numericNames
    }

    // Fall back to the numeric versions is we cant produce anything else.
    private val numericNames = MonthNames(
        january = Month.JANUARY.number.toString(),
        february = Month.FEBRUARY.number.toString(),
        march = Month.MARCH.number.toString(),
        april = Month.APRIL.number.toString(),
        may = Month.MAY.number.toString(),
        june = Month.JUNE.number.toString(),
        july = Month.JULY.number.toString(),
        august = Month.AUGUST.number.toString(),
        september = Month.SEPTEMBER.number.toString(),
        october = Month.OCTOBER.number.toString(),
        november = Month.NOVEMBER.number.toString(),
        december = Month.DECEMBER.number.toString()
    )

    private val fullNames: Map<String, MonthNames> = mapOf(
        "" to EnMonthNames.full,
        "en" to EnMonthNames.full,
        "fr" to FrMonthNames.full,
        "es" to EsMonthNames.full
    )

    private val abbreviatedNames: Map<String, MonthNames> = mapOf(
        "" to EnMonthNames.abbreviated,
        "en" to EnMonthNames.abbreviated,
        "fr" to FrMonthNames.abbreviated,
        "es" to EsMonthNames.abbreviated
    )
}
