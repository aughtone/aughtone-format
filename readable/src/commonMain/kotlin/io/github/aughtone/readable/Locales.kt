package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale

/**
 * Common locale constants for formatting.
 */
object Locales {
    // ── Germanic ──────────────────────────────────────────────────────────────
    val Afrikaans: Locale = Locale(languageCode = "af", regionCode = "ZA", displayName = "Afrikaans")
    val Dutch: Locale = Locale(languageCode = "nl", regionCode = "NL", displayName = "Dutch")
    val English: Locale = Locale(languageCode = "en", regionCode = "US", displayName = "English (United States)")
    val SouthAfricanEnglish: Locale = Locale(languageCode = "en", regionCode = "ZA", displayName = "English (South Africa)")
    val German: Locale = Locale(languageCode = "de", regionCode = "DE", displayName = "German")
    val SwissGerman: Locale = Locale(languageCode = "de", regionCode = "CH", displayName = "German (Switzerland)")
    val Danish: Locale = Locale(languageCode = "da", regionCode = "DK", displayName = "Danish")
    val NorwegianBokmal: Locale = Locale(languageCode = "nb", regionCode = "NO", displayName = "Norwegian Bokmål")
    val NorwegianNynorsk: Locale = Locale(languageCode = "nn", regionCode = "NO", displayName = "Norwegian Nynorsk")
    val Swedish: Locale = Locale(languageCode = "sv", regionCode = "SE", displayName = "Swedish")
    val Icelandic: Locale = Locale(languageCode = "is", regionCode = "IS", displayName = "Icelandic")

    // ── Romance ───────────────────────────────────────────────────────────────
    val French: Locale = Locale(languageCode = "fr", regionCode = "FR", displayName = "French")
    val SwissFrench: Locale = Locale(languageCode = "fr", regionCode = "CH", displayName = "French (Switzerland)")
    val Spanish: Locale = Locale(languageCode = "es", regionCode = "ES", displayName = "Spanish")
    val Italian: Locale = Locale(languageCode = "it", regionCode = "IT", displayName = "Italian")
    val SwissItalian: Locale = Locale(languageCode = "it", regionCode = "CH", displayName = "Italian (Switzerland)")
    val Portuguese: Locale = Locale(languageCode = "pt", regionCode = "PT", displayName = "Portuguese")
    val Catalan: Locale = Locale(languageCode = "ca", regionCode = "ES", displayName = "Catalan")
    val Galician: Locale = Locale(languageCode = "gl", regionCode = "ES", displayName = "Galician")
    val Romanian: Locale = Locale(languageCode = "ro", regionCode = "RO", displayName = "Romanian")

    // ── Slavic & Baltic ───────────────────────────────────────────────────────
    val Russian: Locale = Locale(languageCode = "ru", regionCode = "RU", displayName = "Russian")
    val Ukrainian: Locale = Locale(languageCode = "uk", regionCode = "UA", displayName = "Ukrainian")
    val Belarusian: Locale = Locale(languageCode = "be", regionCode = "BY", displayName = "Belarusian")
    val Polish: Locale = Locale(languageCode = "pl", regionCode = "PL", displayName = "Polish")
    val Czech: Locale = Locale(languageCode = "cs", regionCode = "CZ", displayName = "Czech")
    val Slovak: Locale = Locale(languageCode = "sk", regionCode = "SK", displayName = "Slovak")
    val Bulgarian: Locale = Locale(languageCode = "bg", regionCode = "BG", displayName = "Bulgarian")
    val Croatian: Locale = Locale(languageCode = "hr", regionCode = "HR", displayName = "Croatian")
    val Serbian: Locale = Locale(languageCode = "sr", regionCode = "RS", displayName = "Serbian")
    val Macedonian: Locale = Locale(languageCode = "mk", regionCode = "MK", displayName = "Macedonian")
    val Slovenian: Locale = Locale(languageCode = "sl", regionCode = "SI", displayName = "Slovenian")
    val Lithuanian: Locale = Locale(languageCode = "lt", regionCode = "LT", displayName = "Lithuanian")
    val Latvian: Locale = Locale(languageCode = "lv", regionCode = "LV", displayName = "Latvian")

    // ── East Asian & SE Asian ──────────────────────────────────────────────────
    val Japanese: Locale = Locale(languageCode = "ja", regionCode = "JP", displayName = "Japanese")
    val Chinese: Locale = Locale(languageCode = "zh", regionCode = "CN", displayName = "Chinese (Simplified)")
    val TraditionalChinese: Locale = Locale(languageCode = "zh", regionCode = "TW", displayName = "Chinese (Traditional)")
    val Korean: Locale = Locale(languageCode = "ko", regionCode = "KR", displayName = "Korean")
    val Thai: Locale = Locale(languageCode = "th", regionCode = "TH", displayName = "Thai")
    val Vietnamese: Locale = Locale(languageCode = "vi", regionCode = "VN", displayName = "Vietnamese")
    val Indonesian: Locale = Locale(languageCode = "id", regionCode = "ID", displayName = "Indonesian")
    val Malay: Locale = Locale(languageCode = "ms", regionCode = "MY", displayName = "Malay")

    // ── South & Central Asian ──────────────────────────────────────────────────
    val Hindi: Locale = Locale(languageCode = "hi", regionCode = "IN", displayName = "Hindi")
    val Armenian: Locale = Locale(languageCode = "hy", regionCode = "AM", displayName = "Armenian")
    val Georgian: Locale = Locale(languageCode = "ka", regionCode = "GE", displayName = "Georgian")
    val Kazakh: Locale = Locale(languageCode = "kk", regionCode = "KZ", displayName = "Kazakh")
    val Uzbek: Locale = Locale(languageCode = "uz", regionCode = "UZ", displayName = "Uzbek")

    // ── Middle Eastern & African ──────────────────────────────────────────────
    val Arabic: Locale = Locale(languageCode = "ar", regionCode = "EG", displayName = "Arabic")
    val Hebrew: Locale = Locale(languageCode = "he", regionCode = "IL", displayName = "Hebrew")
    val Persian: Locale = Locale(languageCode = "fa", regionCode = "IR", displayName = "Persian")
    val Turkish: Locale = Locale(languageCode = "tr", regionCode = "TR", displayName = "Turkish")
    val Swahili: Locale = Locale(languageCode = "sw", regionCode = "KE", displayName = "Swahili")

    // ── Finno-Ugric & Other ───────────────────────────────────────────────────
    val Finnish: Locale = Locale(languageCode = "fi", regionCode = "FI", displayName = "Finnish")
    val Estonian: Locale = Locale(languageCode = "et", regionCode = "EE", displayName = "Estonian")
    val Hungarian: Locale = Locale(languageCode = "hu", regionCode = "HU", displayName = "Hungarian")
    val Greek: Locale = Locale(languageCode = "el", regionCode = "GR", displayName = "Greek")
    val Basque: Locale = Locale(languageCode = "eu", regionCode = "ES", displayName = "Basque")
    val Albanian: Locale = Locale(languageCode = "sq", regionCode = "AL", displayName = "Albanian")
    val Inuktitut: Locale = Locale(languageCode = "iu", regionCode = "CA", displayName = "Inuktitut")
}
