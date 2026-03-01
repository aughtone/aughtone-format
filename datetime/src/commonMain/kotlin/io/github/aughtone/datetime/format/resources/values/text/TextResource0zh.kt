package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem
import io.github.aughtone.datetime.format.resources.values.StringItem.Value

open class TextResource0zh : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("上午") }
    override val pm: StringItem.Value by lazy { StringItem.Value("下午") }
    override val time_in_past: Value by lazy { Value("%1前") }
    override val time_in_future: Value by lazy { Value("%1后") }
    override val time_now: Value by lazy { Value("现在") }
}
