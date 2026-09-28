@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

/**
 * Wasm/JS actual for [platformZoneName]. Same technique as the JS target:
 * `Intl.DateTimeFormat` with a `timeZoneName` part over a real `Date`, through
 * `Reflect.construct` because the re-emitted `js()` snippet drops `new`.
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

// The whole body is the js() snippet and references only its parameters; the two
// constructors go through Reflect.construct because Kotlin drops the re-emitted
// `new` operator.
private fun intlZoneName(zoneId: String, epochMillis: Double, style: String, tag: String): String? =
    js(
        "(function(){try{var dtf=Reflect.construct(Intl.DateTimeFormat,[tag,{timeZone:zoneId,timeZoneName:style,hour:'numeric'}]);var d=Reflect.construct(Date,[epochMillis]);var parts=dtf.formatToParts(d);for(var i=0;i<parts.length;i++){if(parts[i].type==='timeZoneName')return parts[i].value;}return null;}catch(e){return null;}})()",
    )
