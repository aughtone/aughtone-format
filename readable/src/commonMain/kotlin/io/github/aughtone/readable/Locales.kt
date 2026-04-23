package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale

/**
 * Common locale constants for formatting.
 */
object Locales {
    /**
     * English (United States)
     */
    val English: Locale = Locale(languageCode = "en", regionCode = "US", displayName = "English (United States)")

    /**
     * French (France)
     */
    val French: Locale = Locale(languageCode = "fr", regionCode = "FR", displayName = "French (France)")

    /**
     * German (Germany)
     */
    val German: Locale = Locale(languageCode = "de", regionCode = "DE", displayName = "German (Germany)")

    /**
     * Swedish (Sweden)
     */
    val Swedish: Locale = Locale(languageCode = "sv", regionCode = "SE", displayName = "Swedish (Sweden)")

    /**
     * Spanish (Spain)
     */
    val Spanish: Locale = Locale(languageCode = "es", regionCode = "ES", displayName = "Spanish (Spain)")

    /**
     * Japanese (Japan)
     */
    val Japanese: Locale = Locale(languageCode = "ja", regionCode = "JP", displayName = "Japanese (Japan)")

    /**
     * Russian (Russia)
     */
    val Russian: Locale = Locale(languageCode = "ru", regionCode = "RU", displayName = "Russian (Russia)")

    /**
     * South African English (en-ZA).
     * Note: "just now" in SA English means "sometime soon", not "right now".
     * This locale uses "now now" as the threshold string instead.
     */
    val SouthAfricanEnglish: Locale = Locale(languageCode = "en", regionCode = "ZA", displayName = "English (South Africa)")

    /**
     * Traditional Chinese (zh-TW).
     * Uses Traditional characters: 小時 (hour), 週 (week) vs Simplified 小时, 周.
     */
    val TraditionalChinese: Locale = Locale(languageCode = "zh", regionCode = "TW", displayName = "Chinese (Traditional, Taiwan)")

    /**
     * Simplified Chinese (zh-CN).
     */
    val Chinese: Locale = Locale(languageCode = "zh", regionCode = "CN", displayName = "Chinese (Simplified, China)")

    /**
     * Swiss French (fr-CH).
     * Uses apostrophe as grouping separator: "1'234,56" instead of "1 234,56".
     */
    val SwissFrench: Locale = Locale(languageCode = "fr", regionCode = "CH", displayName = "French (Switzerland)")

    /**
     * Swiss German (de-CH).
     * Uses apostrophe grouping and period decimal: "1'234.56" instead of "1.234,56".
     */
    val SwissGerman: Locale = Locale(languageCode = "de", regionCode = "CH", displayName = "German (Switzerland)")

    /**
     * Swiss Italian (it-CH).
     * Uses apostrophe grouping and period decimal, following Swiss convention rather than Italian.
     */
    val SwissItalian: Locale = Locale(languageCode = "it", regionCode = "CH", displayName = "Italian (Switzerland)")
}
