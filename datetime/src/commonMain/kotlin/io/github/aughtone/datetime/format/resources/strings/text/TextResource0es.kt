package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0es : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("a. m.") }
    override val pm: StringItem.Value by lazy { StringItem.Value("p. m.") }
    override val time_in_past: Value by lazy { Value("hace %1") }
    override val time_in_future: Value by lazy { Value("en %1") }
    override val time_now: Value by lazy { Value("ahora") }
}
