package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale

/**
 * The tags [locale]'s bundled data is looked up under, most specific first, ending at its bare
 * language. English, which every lookup ends at, is not included.
 *
 * The script decides before the region, so that a locale never reads data in a script it did not
 * ask for. A locale's script is its own [Locale.scriptCode], otherwise the one its region implies
 * ([scriptByLanguageRegion]: Chinese in Taiwan, Macau or the United States is Traditional),
 * otherwise its language's default ([defaultScriptByLanguage]). The chain is then
 * language-script-region, language-region where that region's data is written in the same
 * script, language-script, and the language:
 *
 * - `zh-Hant-US` → `zh-Hant-US`, `zh-US`, `zh-Hant`, `zh`
 * - `zh-Hans-HK` → `zh-Hans-HK`, `zh-Hans`, `zh`, because Hong Kong's data is Traditional
 * - `zh-TW`, with no script → `zh-Hant-TW`, `zh-TW`, `zh-Hant`, `zh`
 * - `de-CH` → `de-CH`, `de`, since German is written in one script
 *
 * A script with no data of its own, such as Latin Serbian, ends at its language's data, in the
 * language's default script.
 */
internal fun lookupTags(locale: Locale): List<String> {
    val language = locale.languageCode
    val region = locale.regionCode
    val languageRegion = region?.let { "$language-$it" }
    val regionScript = languageRegion?.let { scriptByLanguageRegion[it] } ?: defaultScriptByLanguage[language]
    val script = locale.scriptCode ?: regionScript
    return buildList {
        if (script != null && region != null) add("$language-$script-$region")
        if (languageRegion != null && (regionScript == null || regionScript == script)) add(languageRegion)
        if (script != null) add("$language-$script")
        add(language)
    }
}

/**
 * The key a formatter built for [locale] is cached under: its most specific lookup tag, so that
 * locales differing only in script never share a formatter, and those that read the same data
 * (`zh-TW` and `zh-Hant-TW`) do.
 */
internal fun lookupKey(locale: Locale): String = lookupTags(locale).first()
