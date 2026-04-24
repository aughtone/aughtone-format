package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem
import io.github.aughtone.datetime.format.resources.values.StringItem.Value
open class TextResource0ja : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("午前") }
    override val pm: StringItem.Value by lazy { StringItem.Value("午後") }
}
