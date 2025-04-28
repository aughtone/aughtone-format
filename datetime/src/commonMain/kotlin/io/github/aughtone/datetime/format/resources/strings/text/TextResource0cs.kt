package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

class TextResource0cs : AbstractTextResource24HourOnly() {
    override val time_in_past: Value by lazy { Value("před %1") }
    override val time_in_future: Value by lazy { Value("za %1") }
    override val time_now: Value by lazy { Value("nyní") }
}
