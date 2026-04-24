package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem.Value

open class TextResource0is : TextResource {
    override val am: Value by lazy { Value("f.h.") }
    override val pm: Value by lazy { Value("e.h.") }
}
