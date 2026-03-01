package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem
import io.github.aughtone.datetime.format.resources.values.StringItem.Value
open class TextResource0ko : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("오전") }
    override val pm: StringItem.Value by lazy { StringItem.Value("오후") }
    override val time_in_past: Value by lazy { Value("%1 전") }
    override val time_in_future: Value by lazy { Value("%1 후") }
    override val time_now: Value by lazy { Value("지금") }
}
