package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.dayofweeknames.DayOfWeekNames0en
import io.github.aughtone.datetime.format.resources.dayofweeknames.DayOfWeekNames0es
import io.github.aughtone.datetime.format.resources.dayofweeknames.DayOfWeekNames0fr
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.isoDayNumber

object LocaleAwareDayOfWeekNames: LocaleAwareLookup<DayOfWeekNames> {
    /**
     * look up the month names for the given locale.
     * Defaults to english if the locale is not found.
     *
     * falls back to numeric if there was some sort of problem
     *
     * The order of precedence is: "en_rCA", "en", ""
     */
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: DayOfWeekNames?
    ): DayOfWeekNames {
        val language = locale.language
        val region = locale.region

        println("language: $language, region: $region : ${language}_r${region}")

        return if (abbreviated) {
            (abbreviatedNames["${language}_r${region}"] ?: abbreviatedNames[language]
            ?: abbreviatedNames[""])
        } else {
            (fullNames["${language}_r${region}"] ?: fullNames[language] ?: fullNames[""])
        } ?: fallbackTo?: numericNames
    }

    // Fall back to the numeric versions is we cant produce anything else.
    private val numericNames = DayOfWeekNames(
        DayOfWeek.entries.map { it.isoDayNumber.toString() }
    )

    private val fullNames: Map<String, DayOfWeekNames> = mapOf(
        "" to DayOfWeekNames0en.full,
        "en" to DayOfWeekNames0en.full,
        "fr" to DayOfWeekNames0fr.full,
        "es" to DayOfWeekNames0es.full
    )

    private val abbreviatedNames: Map<String, DayOfWeekNames> = mapOf(
        "" to DayOfWeekNames0en.abbreviated,
        "en" to DayOfWeekNames0en.abbreviated,
        "fr" to DayOfWeekNames0fr.abbreviated,
        "es" to DayOfWeekNames0es.abbreviated
    )
}
