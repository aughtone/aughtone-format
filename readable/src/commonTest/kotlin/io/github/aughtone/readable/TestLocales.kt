package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale

/**
 * A locale for a BCP 47 tag such as `"de-CH"`, `"zh-Hant"` or `"zh-Hant-TW"`, built from its own
 * subtags as a caller could build it: a four-letter subtag is the script, a two-letter or
 * three-digit one the region.
 */
internal fun localeOfTag(tag: String): Locale {
    val parts = tag.split('-')
    return Locale(
        languageCode = parts[0],
        scriptCode = parts.drop(1).firstOrNull { it.length == 4 },
        regionCode = parts.drop(1).firstOrNull { it.length == 2 || (it.length == 3 && it.all(Char::isDigit)) },
        displayName = tag,
    )
}
