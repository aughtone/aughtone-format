@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toNSTimeZone
import platform.Foundation.NSDate
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZoneNameStyle
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.localizedName

/**
 * Apple actual for [platformZoneName]. `NSTimeZone.localizedName` reads
 * Foundation's CLDR; the daylight flag at the instant selects the standard vs.
 * daylight style so the name is specific, and the short styles give the
 * abbreviation.
 */
internal actual fun platformZoneName(
    timeZone: TimeZone,
    instant: Instant,
    abbreviated: Boolean,
    locale: Locale,
): String? {
    val nsZone = timeZone.toNSTimeZone()
    val date = NSDate.dateWithTimeIntervalSince1970(instant.toEpochMilliseconds() / 1000.0)
    val daylight = nsZone.isDaylightSavingTimeForDate(date)
    val style = when {
        abbreviated && daylight -> NSTimeZoneNameStyle.NSTimeZoneNameStyleShortDaylightSaving
        abbreviated -> NSTimeZoneNameStyle.NSTimeZoneNameStyleShortStandard
        daylight -> NSTimeZoneNameStyle.NSTimeZoneNameStyleDaylightSaving
        else -> NSTimeZoneNameStyle.NSTimeZoneNameStyleStandard
    }
    val name = nsZone.localizedName(style, NSLocale(localeIdentifier = locale.languageTag))
    return name?.ifEmpty { null }
}
