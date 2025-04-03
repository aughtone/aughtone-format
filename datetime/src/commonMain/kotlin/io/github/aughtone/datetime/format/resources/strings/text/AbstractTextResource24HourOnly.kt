package io.github.aughtone.datetime.format.resources.strings.text

import io.github.aughtone.datetime.format.resources.strings.StringItem.Value
/**
 * A text resource that only supports 24-hour time.
 *
 * This class extends [TextResource0en] and provides empty strings for the AM and PM values,
 * indicating that only 24-hour time is supported.
 */
abstract class AbstractTextResource24HourOnly : TextResource {
    override val am: Value by lazy { Value("") }
    override val pm: Value by lazy { Value("") }
}
