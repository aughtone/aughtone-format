package io.github.aughtone.datetime.format.resources.values.text
import io.github.aughtone.datetime.format.resources.values.StringItem.Value
open class TextResource0tr : AbstractTextResource24HourOnly() {
    override val time_in_past: Value by lazy { Value("%1 önce") }
    override val time_in_future: Value by lazy { Value("%1 içinde") }
    override val time_now: Value by lazy { Value("şimdi") }
}
