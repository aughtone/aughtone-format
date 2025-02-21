package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.options.clock.Is24Hour0enCA
import io.github.aughtone.datetime.format.resources.options.clock.Is24Hour0frCA

object LocaleAwareIs24Hour : LocaleAwareLookup<Boolean> {
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: Boolean?,
    ): Boolean =
        checkFullLocale(locale) ?: checkRegion(locale) ?: checkLanguage(locale) ?: fallbackTo
        ?: false

    private fun checkRegion(locale: Locale): Boolean? =
        when ("${locale.language}_${locale.region}") {
            "CA" -> Is24Hour0enCA.is24hour
            else -> null
        }

    private fun checkLanguage(locale: Locale): Boolean? = null

    private fun checkFullLocale(locale: Locale): Boolean? =
        when ("${locale.language}_${locale.region}") {
            "fr_CA" -> Is24Hour0frCA.is24hour
            else -> null
        }

}


//val answer: Int by lazy {
//    println("Computing the answer to the Ultimate Question of Life, the Universe, and Everything")
//    42
//}
