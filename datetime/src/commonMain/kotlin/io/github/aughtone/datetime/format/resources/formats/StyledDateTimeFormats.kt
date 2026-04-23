package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.currentNativeLocale
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat

interface StyledDateTimeFormats<T> {
    fun short(
        locale: Locale = currentNativeLocale(),
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun medium(
        locale: Locale = currentNativeLocale(),
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun long(
        locale: Locale = currentNativeLocale(),
        timeZone: TimeZone? = TimeZone.Companion.currentSystemDefault(),
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>

    fun full(
        locale: Locale = currentNativeLocale(),
        timeZone: TimeZone? = null,
        twentyFourHour: Boolean = false,
    ): DateTimeFormat<T>
}
