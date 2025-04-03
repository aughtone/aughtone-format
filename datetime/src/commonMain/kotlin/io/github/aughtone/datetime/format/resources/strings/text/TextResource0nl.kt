package io.github.aughtone.datetime.format.resources.strings.text
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
open class TextResource0nl : AbstractTextResource24HourOnly() {
    override val time_in_past: Value by lazy { Value("%1 geleden") }
    override val time_in_future: Value by lazy { Value("over %1") }
    override val time_now: Value by lazy { Value("nu") }

}
