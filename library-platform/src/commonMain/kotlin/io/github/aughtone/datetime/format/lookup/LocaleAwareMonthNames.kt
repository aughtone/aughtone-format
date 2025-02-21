package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNames0en
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNames0es
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNames0fr
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNames0numbers
import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNames0uk
import kotlinx.datetime.format.MonthNames

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

        // Fall back to the numeric versions is we cant produce anything else.
        return if (abbreviated) {
            (abbreviatedNames("${language}_${region}") ?: abbreviatedNames(language)
            ?: abbreviatedNames("")) ?: fallbackTo ?: MonthNames0numbers.abbreviated
        } else {
            (fullNames("${language}_${region}") ?: fullNames(language) ?: fullNames(""))
        } ?: fallbackTo ?: MonthNames0numbers.full
    }

    private fun fullNames(key: String) = when (key) {
        "en" -> MonthNames0en.full
        "fr" -> MonthNames0fr.full
        "es" -> MonthNames0es.full
        "uk" -> MonthNames0uk.full
        else -> null
    }

    private fun abbreviatedNames(key: String) = when (key) {
        "en" -> MonthNames0en.abbreviated
        "fr" -> MonthNames0fr.abbreviated
        "es" -> MonthNames0es.abbreviated
        "uk" -> MonthNames0uk.abbreviated
        else -> null
    }
}
