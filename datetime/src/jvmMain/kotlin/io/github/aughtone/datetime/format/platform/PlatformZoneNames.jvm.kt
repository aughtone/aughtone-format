@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

/**
 * JVM actual for [platformZoneName]: `java.time` carries the full CLDR, so the
 * `zzzz` / `zzz` pattern yields the localized specific name at the instant
 * ("Eastern Standard Time" / "EST", "HNE" for fr-CA, …).
 */
internal actual fun platformZoneName(
    timeZone: TimeZone,
    instant: Instant,
    abbreviated: Boolean,
    locale: Locale,
): String? {
    val zoneId = runCatching { ZoneId.of(timeZone.id) }.getOrNull() ?: return null
    val zoned = ZonedDateTime.ofInstant(
        java.time.Instant.ofEpochMilli(instant.toEpochMilliseconds()),
        zoneId,
    )
    val pattern = if (abbreviated) "zzz" else "zzzz"
    val name = runCatching {
        DateTimeFormatter.ofPattern(pattern)
            .withLocale(java.util.Locale.forLanguageTag(locale.languageTag))
            .format(zoned)
    }.getOrNull()
    return name?.ifEmpty { null }
}

/**
 * JVM actual for [platformGenericZoneName]: `ZoneId.getDisplayName` gives the generic name, full
 * at [java.time.format.TextStyle.FULL] and abbreviated at [java.time.format.TextStyle.SHORT].
 */
internal actual fun platformGenericZoneName(
    timeZone: TimeZone,
    abbreviated: Boolean,
    locale: Locale,
): String? {
    val zoneId = runCatching { ZoneId.of(timeZone.id) }.getOrNull() ?: return null
    val style = if (abbreviated) java.time.format.TextStyle.SHORT else java.time.format.TextStyle.FULL
    val name = runCatching { zoneId.getDisplayName(style, java.util.Locale.forLanguageTag(locale.languageTag)) }.getOrNull()
    return name?.ifEmpty { null }
}
