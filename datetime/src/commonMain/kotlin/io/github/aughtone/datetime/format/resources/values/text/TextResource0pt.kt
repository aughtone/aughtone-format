package io.github.aughtone.datetime.format.resources.values.text

import io.github.aughtone.datetime.format.resources.values.StringItem.Value

open class TextResource0pt : TextResource0enCA() {
    //    override val am: StringItem.Value by lazy { StringItem.Value("a.m.") }
//    override val pm: StringItem.Value by lazy { StringItem.Value("p.m.") }
    override val time_in_past: Value by lazy { Value("há %1") }
    override val time_in_future: Value by lazy { Value("em %1") }
    override val time_now: Value by lazy { Value("agora") }
}
