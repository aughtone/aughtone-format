package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.timeformats.CALocalTimeFormats
import io.github.aughtone.datetime.format.resources.timeformats.USLocalTimeFormats
import kotlinx.datetime.LocalTime

object LocaleAwareTimeFormats : LocaleAwareLookup<StyledDateTimeFormats<LocalTime>> {
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: StyledDateTimeFormats<LocalTime>?,
    ): StyledDateTimeFormats<LocalTime> = when (locale.region) {
        "US" -> USLocalTimeFormats
        "CA" -> CALocalTimeFormats
        else -> fallbackTo?: CALocalTimeFormats
    }
}
