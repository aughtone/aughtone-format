package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale

/**
 * Standard plural categories based on Unicode CLDR.
 */
enum class PluralCategory {
    Zero,
    One,
    Two,
    Few,
    Many,
    Other
}

/**
 * Returns the [PluralCategory] for a given number [n] and [locale].
 * Implements simplified CLDR rules for the 55 core languages.
 */
fun pluralCategoryFor(locale: Locale, n: Long): PluralCategory {
    val lang = locale.languageCode
    return when (lang) {
        // ── East Asian (No plural inflection) ─────────────────────────────────
        "ja", "zh", "ko", "th", "vi" -> PluralCategory.Other

        // ── Germanic / Romance / Most Others (1 vs Other) ─────────────────────
        "en", "de", "nl", "af", "da", "nb", "nn", "no", "sv", "is",
        "es", "it", "pt", "ca", "gl", "ro", "fi", "et", "hu", "el", "eu",
        "tr", "az", "uz", "kk", "hy", "ka", "hi", "id", "ms", "sw", "sq" -> {
            if (n == 1L) PluralCategory.One else PluralCategory.Other
        }

        // ── French (0 and 1 are One) ──────────────────────────────────────────
        "fr" -> {
            if (n == 0L || n == 1L) PluralCategory.One else PluralCategory.Other
        }

        // ── Slavic (Russian, Ukrainian, Belarusian) ───────────────────────────
        "ru", "uk", "be" -> {
            val n10 = n % 10
            val n100 = n % 100
            when {
                n10 == 1L && n100 != 11L -> PluralCategory.One
                n10 in 2..4 && n100 !in 12..14 -> PluralCategory.Few
                else -> PluralCategory.Many
            }
        }

        // ── Polish ────────────────────────────────────────────────────────────
        "pl" -> {
            val n10 = n % 10
            val n100 = n % 100
            when {
                n == 1L -> PluralCategory.One
                n10 in 2..4 && n100 !in 12..14 -> PluralCategory.Few
                else -> PluralCategory.Many
            }
        }

        // ── Arabic ────────────────────────────────────────────────────────────
        "ar" -> {
            val n100 = n % 100
            when {
                n == 0L -> PluralCategory.Zero
                n == 1L -> PluralCategory.One
                n == 2L -> PluralCategory.Two
                n100 in 3..10 -> PluralCategory.Few
                n100 in 11..99 -> PluralCategory.Many
                else -> PluralCategory.Other
            }
        }

        // ── Hebrew ────────────────────────────────────────────────────────────
        "he" -> {
            when {
                n == 1L -> PluralCategory.One
                n == 2L -> PluralCategory.Two
                n > 10 && n % 10 == 0L -> PluralCategory.Many // Complex, simplified here
                else -> PluralCategory.Other
            }
        }

        // ── Inuktitut ─────────────────────────────────────────────────────────
        "iu" -> {
            when (n) {
                1L -> PluralCategory.One
                2L -> PluralCategory.Two
                else -> PluralCategory.Other
            }
        }

        else -> if (n == 1L) PluralCategory.One else PluralCategory.Other
    }
}

/**
 * Returns the [PluralCategory] for an ordinal number [n] and [locale].
 * Implements simplified CLDR ordinal rules for the 55 core languages.
 */
fun ordinalCategoryFor(locale: Locale, n: Long): PluralCategory {
    val lang = locale.languageCode
    return when (lang) {
        // ── English ───────────────────────────────────────────────────────────
        "en" -> {
            val n10 = n % 10
            val n100 = n % 100
            when {
                n10 == 1L && n100 != 11L -> PluralCategory.One
                n10 == 2L && n100 != 12L -> PluralCategory.Two
                n10 == 3L && n100 != 13L -> PluralCategory.Few
                else -> PluralCategory.Other
            }
        }

        // ── French ────────────────────────────────────────────────────────────
        "fr" -> if (n == 1L) PluralCategory.One else PluralCategory.Other

        // ── Swedish / Danish ──────────────────────────────────────────────────
        "sv", "da" -> {
            val n10 = n % 10
            val n100 = n % 100
            if ((n10 == 1L || n10 == 2L) && n100 != 11L && n100 != 12L) PluralCategory.One else PluralCategory.Other
        }

        // ── Armenian / Georgian ───────────────────────────────────────────────
        "hy", "ka" -> if (n == 1L) PluralCategory.One else PluralCategory.Other

        // ── Others ────────────────────────────────────────────────────────────
        // Most other languages in the 55 set use a single ordinal form or a dot.
        else -> PluralCategory.Other
    }
}
