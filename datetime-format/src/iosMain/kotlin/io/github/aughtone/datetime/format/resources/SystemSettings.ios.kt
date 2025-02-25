package io.github.aughtone.datetime.format.resources

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

// See: https://stackoverflow.com/a/28183182/300236
internal actual fun isPlatform24HourSettingEnabled(): Boolean? {
    val locale = NSLocale.currentLocale()
    val dateFormat = NSDateFormatter.dateFormatFromTemplate("j", options = 0.toULong(), locale)
    return dateFormat?.contains("a")
}
