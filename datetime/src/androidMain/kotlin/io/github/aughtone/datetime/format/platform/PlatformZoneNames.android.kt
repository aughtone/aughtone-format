@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import java.util.Date
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

/**
 * Android actual for [platformZoneName]. `java.time` needs core-library
 * desugaring below API 26 and this module targets minSdk 24, so it uses
 * `java.util.TimeZone.getDisplayName`, which Android backs with ICU CLDR and
 * localizes properly. The daylight flag is resolved from the instant so the name
 * is DST-aware, matching the specific-name contract.
 */
internal actual fun platformZoneName(
    timeZone: TimeZone,
    instant: Instant,
    abbreviated: Boolean,
    locale: Locale,
): String? {
    val jvmZone = java.util.TimeZone.getTimeZone(timeZone.id)
    // getTimeZone returns GMT for an unknown id; treat that as "no name" unless
    // the caller actually asked for GMT/UTC.
    if (jvmZone.id == "GMT" && timeZone.id != "GMT" && timeZone.id != "UTC") return null
    val daylight = jvmZone.inDaylightTime(Date(instant.toEpochMilliseconds()))
    val style = if (abbreviated) java.util.TimeZone.SHORT else java.util.TimeZone.LONG
    val name = runCatching {
        jvmZone.getDisplayName(daylight, style, java.util.Locale.forLanguageTag(locale.languageTag))
    }.getOrNull()
    return name?.ifEmpty { null }
}

/**
 * Android actual for [platformGenericZoneName]: ICU's `TimeZoneFormat` (API 24) in its generic
 * styles, which apply CLDR's own fallback where a zone has no generic name. An identifier ICU
 * does not know resolves to its unknown zone and gives `null`.
 */
internal actual fun platformGenericZoneName(
    timeZone: TimeZone,
    abbreviated: Boolean,
    locale: Locale,
): String? {
    val icuZone = android.icu.util.TimeZone.getTimeZone(timeZone.id)
    if (icuZone.id == android.icu.util.TimeZone.UNKNOWN_ZONE_ID) return null
    val style = if (abbreviated) android.icu.text.TimeZoneFormat.Style.GENERIC_SHORT else android.icu.text.TimeZoneFormat.Style.GENERIC_LONG
    val name = runCatching {
        android.icu.text.TimeZoneFormat.getInstance(android.icu.util.ULocale.forLanguageTag(locale.languageTag))
            .format(style, icuZone, System.currentTimeMillis())
    }.getOrNull()
    return name?.ifEmpty { null }
}
