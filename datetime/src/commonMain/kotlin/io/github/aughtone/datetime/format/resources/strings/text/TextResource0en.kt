package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0en : TextResource {
    override val am: Value by lazy { Value("am") }
    override val pm: Value by lazy { Value("pm") }
    override val time_in_past: Value by lazy { Value("%1 ago") }
    override val time_in_future: Value by lazy { Value("in %1") }
    override val time_now: Value by lazy { Value("now") }
}
