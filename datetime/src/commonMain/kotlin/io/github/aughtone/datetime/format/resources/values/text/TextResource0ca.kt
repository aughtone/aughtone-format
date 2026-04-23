package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem.Value

open class TextResource0ca : TextResource {
    override val am: Value by lazy { Value("a. m.") }
    override val pm: Value by lazy { Value("p. m.") }
}
