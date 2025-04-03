package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
open class TextResource0id : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("pagi") }
    override val pm: StringItem.Value by lazy { StringItem.Value("sore") }
    override val time_in_past: Value by lazy { Value("%1 yang lalu") }
    override val time_in_future: Value by lazy { Value("dalam %1") }
    override val time_now: Value by lazy { Value("sekarang") }
}
