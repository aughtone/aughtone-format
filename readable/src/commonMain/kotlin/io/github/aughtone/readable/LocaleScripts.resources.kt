package io.github.aughtone.readable

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR 48.0 by tools/cldr/locale-scripts.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * The script each bundled language is written in when neither the locale nor its region names
 * another, from CLDR's likely subtags. Only languages that some region writes in another script
 * appear, because for the rest the script never decides between two tables.
 */
internal val defaultScriptByLanguage: Map<String, String> = mapOf(
    "az" to "Latn",
    "kk" to "Cyrl",
    "ms" to "Latn",
    "sr" to "Cyrl",
    "uz" to "Latn",
    "zh" to "Hans",
    "pa" to "Guru",
)

/**
 * The script a language is written in for a region, where that differs from
 * [defaultScriptByLanguage], from CLDR's likely subtags: `"zh-TW"` is `"Hant"`.
 */
internal val scriptByLanguageRegion: Map<String, String> = mapOf(
    "az-IQ" to "Arab",
    "az-IR" to "Arab",
    "az-RU" to "Cyrl",
    "kk-AF" to "Arab",
    "kk-CN" to "Arab",
    "kk-IR" to "Arab",
    "kk-MN" to "Arab",
    "ms-CC" to "Arab",
    "sr-ME" to "Latn",
    "sr-RO" to "Latn",
    "sr-TR" to "Latn",
    "uz-AF" to "Arab",
    "uz-CN" to "Cyrl",
    "zh-AU" to "Hant",
    "zh-BN" to "Hant",
    "zh-GB" to "Hant",
    "zh-GF" to "Hant",
    "zh-HK" to "Hant",
    "zh-ID" to "Hant",
    "zh-MO" to "Hant",
    "zh-PA" to "Hant",
    "zh-PF" to "Hant",
    "zh-PH" to "Hant",
    "zh-SR" to "Hant",
    "zh-TH" to "Hant",
    "zh-TW" to "Hant",
    "zh-US" to "Hant",
    "zh-VN" to "Hant",
    "pa-PK" to "Arab",
)
