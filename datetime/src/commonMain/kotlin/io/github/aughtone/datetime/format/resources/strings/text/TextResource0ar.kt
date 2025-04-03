package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0ar : TextResource0en() {
    override val am: Value by lazy { Value("ص") }
    override val pm: Value by lazy { Value("م") }
    override val time_in_past: Value by lazy { Value("%1 مضت") }
    override val time_in_future: Value by lazy { Value("خلال %1") }
    override val time_now: Value by lazy { Value("الآن") }
}
