package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0vi : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("SA") }
    override val pm: StringItem.Value by lazy { StringItem.Value("CH") }
    override val time_in_past: Value by lazy { Value("%1 trước") }
    override val time_in_future: Value by lazy { Value("trong %1") }
    override val time_now: Value by lazy { Value("bây giờ") }
}
