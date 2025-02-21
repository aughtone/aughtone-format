package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0EnCA
import io.github.aughtone.datetime.format.resources.options.clock.ClockHours0FrCA

@Deprecated("Use Resources.get24Hour(locale).is24hour instead")
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
            "CA" -> ClockHours0EnCA.is24hour
            else -> null
        }

    private fun checkLanguage(locale: Locale): Boolean? = null

    private fun checkFullLocale(locale: Locale): Boolean? =
        when ("${locale.language}_${locale.region}") {
            "fr_CA" -> ClockHours0FrCA.is24hour
            else -> null
        }

}


//val answer: Int by lazy {
//    println("Computing the answer to the Ultimate Question of Life, the Universe, and Everything")
//    42
//}
