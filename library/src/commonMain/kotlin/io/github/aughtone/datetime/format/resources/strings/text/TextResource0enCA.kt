package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Plurals
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value

open class TextResource0enCA : TextResource0en() {
    override val am: Value by lazy { Value("a.m.") }
    override val pm: Value by lazy { Value("p.m.") }
}
