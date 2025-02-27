package io.github.aughtone.datetime.format.resources

import androidx.compose.ui.text.intl.Locale
import kotlin.js.Date

actual fun isPlatform24HourSettingEnabled(): Boolean? {
    val date = Date()
    val dateString = date.toLocaleTimeString(Locale.current.language)
    return dateString.asDynamic().match("/am|pm/i") || date.toString().asDynamic().match("/am|pm/i")
}
