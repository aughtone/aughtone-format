package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem.Value

open class TextResource0bg : TextResource {
    override val am: Value by lazy { Value("пр.об.") }
    override val pm: Value by lazy { Value("сл.об.") }
}
