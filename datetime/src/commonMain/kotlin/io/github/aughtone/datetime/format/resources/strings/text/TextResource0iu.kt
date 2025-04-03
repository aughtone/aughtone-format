package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem
import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
open class TextResource0iu : TextResource0en() {
    override val am: StringItem.Value by lazy { StringItem.Value("ᐅᓪᓛᒃᑯᑦ") }
    override val pm: StringItem.Value by lazy { StringItem.Value("ᐅᓐᓄᒃᑯᑦ") }
    override val time_in_past: Value by lazy { Value("%1 ᓯᕗᓂᐊᓂ") }
    override val time_in_future: Value by lazy { Value("%1 ᑭᖑᓂᐊᓂ") }
    override val time_now: Value by lazy { Value("ᒫᓐᓇ") }
}
