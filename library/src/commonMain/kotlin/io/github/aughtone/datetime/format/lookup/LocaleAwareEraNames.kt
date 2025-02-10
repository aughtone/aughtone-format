package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.era.EnEraNames
import io.github.aughtone.datetime.format.resources.era.EraNames

object LocaleAwareEraNames : LocaleAwareLookup<EraNames> {
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
        fallbackTo: EraNames?,
    ): EraNames =
        when (locale.language) {
            else -> {
                val names by lazy {
                    EnEraNames
                }
                names
            }
        }
}




//val answer: Int by lazy {
//    println("Computing the answer to the Ultimate Question of Life, the Universe, and Everything")
//    42
//}
