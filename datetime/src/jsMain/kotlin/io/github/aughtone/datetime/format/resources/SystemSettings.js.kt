package io.github.aughtone.datetime.format.resources

import androidx.compose.ui.text.intl.Locale
import kotlin.js.Date

actual fun isPlatform24HourSettingEnabled(): Boolean? {
    // XXX I'm not sure this will work as expected if the date is returned in a different language
    //  that doesn't use am/pm. The goal here is to see if the suers system formats in 12h or not.
    val date = Date()
    val dateString = date.toLocaleTimeString(Locale.current.language)
    return dateString.asDynamic().match("/am|pm/i") || date.toString().asDynamic().match("/am|pm/i")
}
