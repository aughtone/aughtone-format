package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0de : AbstractTextResource24HourOnly() {
    override val time_in_past: Value by lazy { Value("vor %1") }
    override val time_in_future: Value by lazy { Value("in %1") }
    override val time_now: Value by lazy { Value("jetzt") }
}
