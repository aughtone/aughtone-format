package io.github.aughtone.datetime.format.resources

/**
 * Wasm/JS actual: the hour cycle the runtime resolves for its own default locale, read from
 * `Intl` exactly as the JS target does.
 *
 * @return `true` for a 24-hour clock, `false` for a 12-hour clock, `null` if `Intl` gave no answer.
 */
internal actual fun isPlatform24HourSettingEnabled(): Boolean? = when (intlHourCycle()) {
    "h23", "h24" -> true
    "h11", "h12" -> false
    else -> null
}

// The whole body is the js() snippet; the constructor goes through Reflect.construct because
// Kotlin drops the re-emitted `new` operator.
private fun intlHourCycle(): String? =
    js("(function(){try{var f=Reflect.construct(Intl.DateTimeFormat,[undefined,{hour:'numeric'}]);var c=f.resolvedOptions().hourCycle;return c?c:null;}catch(e){return null;}})()")
