package io.github.aughtone.datetime.format.resources.formats

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

interface StyledDateTimeFormats<T> {
    fun short(
        locale: Locale = Locale.Companion.current,
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun medium(
        locale: Locale = Locale.Companion.current,
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun long(
        locale: Locale = Locale.Companion.current,
        timeZone: TimeZone? = TimeZone.Companion.currentSystemDefault(),
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun full(
        locale: Locale = Locale.Companion.current,
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>
}
