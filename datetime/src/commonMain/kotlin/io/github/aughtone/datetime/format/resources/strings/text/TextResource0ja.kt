package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
open class TextResource0ja : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("午前") }
    override val pm: StringItem.Value by lazy { StringItem.Value("午後") }
    override val time_in_past: Value by lazy { Value("%1前") }
    override val time_in_future: Value by lazy { Value("%1後") }
    override val time_now: Value by lazy { Value("今") }
}
