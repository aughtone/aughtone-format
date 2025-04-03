package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
open class TextResource0ko : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("오전") }
    override val pm: StringItem.Value by lazy { StringItem.Value("오후") }
    override val time_in_past: Value by lazy { Value("%1 전") }
    override val time_in_future: Value by lazy { Value("%1 후") }
    override val time_now: Value by lazy { Value("지금") }
}
