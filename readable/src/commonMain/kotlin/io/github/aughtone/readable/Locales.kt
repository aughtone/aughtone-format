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
}
