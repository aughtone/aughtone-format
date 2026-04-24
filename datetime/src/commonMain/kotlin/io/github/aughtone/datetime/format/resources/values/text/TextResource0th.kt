package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem.Value

open class TextResource0th : TextResource {
    override val am: Value by lazy { Value("ก่อนเที่ยง") }
    override val pm: Value by lazy { Value("หลังเที่ยง") }
}
