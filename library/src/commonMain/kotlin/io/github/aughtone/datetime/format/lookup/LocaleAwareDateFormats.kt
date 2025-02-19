package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.dateformats.LocalDateFormats0CA
import io.github.aughtone.datetime.format.resources.dateformats.LocalDateFormats0US
import kotlinx.datetime.LocalDate

object LocaleAwareDateFormats: LocaleAwareLookup<StyledDateTimeFormats<LocalDate>> {
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: StyledDateTimeFormats<LocalDate>?,
    ): StyledDateTimeFormats<LocalDate> =when (locale.region) {
        "US" -> LocalDateFormats0US
        "CA" -> LocalDateFormats0CA
        else -> fallbackTo?: LocalDateFormats0CA
    }


}
