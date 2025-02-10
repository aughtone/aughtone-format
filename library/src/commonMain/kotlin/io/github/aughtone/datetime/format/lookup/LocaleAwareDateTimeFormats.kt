package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.StyledDateTimeFormats
import io.github.aughtone.datetime.format.resources.datetimeformats.CompositLocalDateTimeFormats
import io.github.aughtone.datetime.format.resources.datetimeformats.ISOLocalDateTimeFormats
import kotlinx.datetime.LocalDateTime

object LocaleAwareDateTimeFormats: LocaleAwareLookup<StyledDateTimeFormats<LocalDateTime>> {
    override fun lookup(
        locale: Locale,
        abbreviated: Boolean,
        fallbackTo: StyledDateTimeFormats<LocalDateTime>?,
    ): StyledDateTimeFormats<LocalDateTime> =when (locale.region) {
        "US" -> CompositLocalDateTimeFormats
        "CA" -> CompositLocalDateTimeFormats
        else -> fallbackTo?: ISOLocalDateTimeFormats
    }


}
