package io.github.aughtone.datetime.format.resources.strings.text
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
open class TextResource0ru : AbstractTextResource24HourOnly() {
    override val time_in_past: Value by lazy { Value("%1 назад") }
    override val time_in_future: Value by lazy { Value("через %1") }
    override val time_now: Value by lazy { Value("сейчас") }
}
