package io.github.aughtone.datetime.format.resources

/**
 * JS actual: the hour cycle the runtime resolves for its own default locale.
 *
 * `Intl` reports the clock a time would be written on — the common one for the browser's locale,
 * or the user's own setting where the browser honours the operating system's override.
 *
 * @return `true` for a 24-hour clock, `false` for a 12-hour clock, `null` if `Intl` gave no answer.
 */
internal actual fun isPlatform24HourSettingEnabled(): Boolean? = when (intlHourCycle()) {
    "h23", "h24" -> true
    "h11", "h12" -> false
    else -> null
}

// The constructor goes through Reflect.construct because Kotlin drops the `new` operator when it
// re-emits a js() snippet.
private fun intlHourCycle(): String? =
    js("(function(){try{var f=Reflect.construct(Intl.DateTimeFormat,[undefined,{hour:'numeric'}]);var c=f.resolvedOptions().hourCycle;return c?c:null;}catch(e){return null;}})()")
