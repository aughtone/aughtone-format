package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

interface StyledDateTimeFormats<T> {
    fun short(
        locale: Locale = Locale.current,
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun medium(
        locale: Locale = Locale.current,
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun long(
        locale: Locale = Locale.current,
        timeZone: TimeZone? = TimeZone.Companion.currentSystemDefault(),
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun full(
        locale: Locale = Locale.current,
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>
}
