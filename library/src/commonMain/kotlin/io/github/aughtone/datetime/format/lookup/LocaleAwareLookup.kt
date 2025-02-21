package io.github.aughtone.datetime.format.lookup

import androidx.compose.ui.text.intl.Locale

@Deprecated("Use Resources.get*** instead")
interface LocaleAwareLookup<T> {
    fun lookup(locale: Locale, abbreviated: Boolean = false, fallbackTo: T? = null ): T
}
