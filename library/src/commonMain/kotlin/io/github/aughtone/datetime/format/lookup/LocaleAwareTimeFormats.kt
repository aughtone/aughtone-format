package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.formats.timeformats.LocalTimeFormats0CA
import io.github.aughtone.datetime.format.resources.formats.timeformats.LocalTimeFormats0US
import kotlinx.datetime.LocalTime

object LocaleAwareTimeFormats : LocaleAwareLookup<StyledDateTimeFormats<LocalTime>> {
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: StyledDateTimeFormats<LocalTime>?,
    ): StyledDateTimeFormats<LocalTime> = when (locale.region) {
        "US" -> LocalTimeFormats0US
        "CA" -> LocalTimeFormats0CA
        else -> fallbackTo?: LocalTimeFormats0CA
    }
}
