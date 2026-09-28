@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

/**
 * JS actual for [platformZoneName]. The browser's `Intl.DateTimeFormat` is a
 * complete CLDR, so formatting a real `Date` with a `timeZoneName` part yields
 * the localized specific name; `long`/`short` selects full vs. abbreviated.
 */
internal actual fun platformZoneName(
    timeZone: TimeZone,
    instant: Instant,
    abbreviated: Boolean,
    locale: Locale,
): String? {
    val name = intlZoneName(
        timeZone.id,
        instant.toEpochMilliseconds().toDouble(),
        if (abbreviated) "short" else "long",
        locale.languageTag,
    )
    return name?.ifEmpty { null }
}

// The whole body is the js() snippet, and it references only its parameters:
// Kotlin/JS re-emits the snippet and drops the `new` operator, so the two
// constructors go through Reflect.construct rather than `new`.
private fun intlZoneName(zoneId: String, epochMillis: Double, style: String, tag: String): String? =
    js(
        "(function(){try{var dtf=Reflect.construct(Intl.DateTimeFormat,[tag,{timeZone:zoneId,timeZoneName:style,hour:'numeric'}]);var d=Reflect.construct(Date,[epochMillis]);var parts=dtf.formatToParts(d);for(var i=0;i<parts.length;i++){if(parts[i].type==='timeZoneName')return parts[i].value;}return null;}catch(e){return null;}})()",
    )
