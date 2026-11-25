package io.github.aughtone.datetime.format.resources

// Linux has no one system setting for a 12 or 24 hour clock that every desktop honours, so there is no
// platform answer here and the locale's own convention is used.
internal actual fun isPlatform24HourSettingEnabled(): Boolean? = null
