package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale
import kotlin.math.absoluteValue

/**
 * Standard plural categories based on Unicode CLDR.
 */
enum class PluralCategory {
    /** CLDR `zero` category. */
    Zero,

    /** CLDR `one` category. */
    One,

    /** CLDR `two` category. */
    Two,

    /** CLDR `few` category. */
    Few,

    /** CLDR `many` category. */
    Many,

    /** CLDR `other` category — the required catch-all every locale defines. */
    Other
}

/**
 * Returns the cardinal [PluralCategory] for the whole number [n] in [locale].
 *
 * These are the Unicode CLDR plural rules as they apply to whole numbers, for the bundled
 * languages. A negative [n] takes the category of its magnitude. A language outside the bundled
 * set falls back to the English rule (one for 1, other for everything else).
 *
 * ```kotlin
 * pluralCategoryFor(Locales.English, 1)  // One
 * pluralCategoryFor(Locales.Czech, 3)    // Few
 * pluralCategoryFor(Locales.Latvian, 10) // Zero
 * ```
 */
fun pluralCategoryFor(locale: Locale, n: Long): PluralCategory {
    val lang = locale.languageCode
    // Remainders of a negative number are negative; taking them of the magnitude keeps -21 with 21.
    val n10 = (n % 10).absoluteValue
    val n100 = (n % 100).absoluteValue
    val isOne = n == 1L || n == -1L
    val isTwo = n == 2L || n == -2L
    return when (lang) {
        // ── East Asian and others with no plural inflection ───────────────────
        "ja", "zh", "ko", "th", "vi" -> PluralCategory.Other

        // ── Germanic / Romance / Most Others (1 vs Other) ─────────────────────
        "en", "de", "nl", "af", "da", "nb", "nn", "no", "sv",
        "es", "it", "pt", "ca", "gl", "fi", "et", "hu", "el", "eu",
        "tr", "az", "uz", "kk", "ka", "id", "ms", "sw", "sq", "bg" -> {
            if (isOne) PluralCategory.One else PluralCategory.Other
        }

        // ── French, Hindi, Persian, Armenian (0 and 1 are One) ────────────────
        "fr", "hi", "fa", "hy" -> {
            if (n == 0L || isOne) PluralCategory.One else PluralCategory.Other
        }

        // ── Icelandic, Macedonian (singular whenever the number ends in 1) ────
        "is", "mk" -> {
            if (n10 == 1L && n100 != 11L) PluralCategory.One else PluralCategory.Other
        }

        // ── Romanian ──────────────────────────────────────────────────────────
        "ro" -> when {
            isOne -> PluralCategory.One
            n == 0L || n100 in 1..19 -> PluralCategory.Few
            else -> PluralCategory.Other
        }

        // ── Slavic (Russian, Ukrainian, Belarusian) ───────────────────────────
        "ru", "uk", "be" -> when {
            n10 == 1L && n100 != 11L -> PluralCategory.One
            n10 in 2..4 && n100 !in 12..14 -> PluralCategory.Few
            else -> PluralCategory.Many
        }

        // ── Polish ────────────────────────────────────────────────────────────
        "pl" -> when {
            isOne -> PluralCategory.One
            n10 in 2..4 && n100 !in 12..14 -> PluralCategory.Few
            else -> PluralCategory.Many
        }

        // ── Czech, Slovak ─────────────────────────────────────────────────────
        "cs", "sk" -> when {
            isOne -> PluralCategory.One
            n in 2..4 || n in -4..-2 -> PluralCategory.Few
            else -> PluralCategory.Other
        }

        // ── Croatian, Serbian, Bosnian ────────────────────────────────────────
        "hr", "sr", "bs" -> when {
            n10 == 1L && n100 != 11L -> PluralCategory.One
            n10 in 2..4 && n100 !in 12..14 -> PluralCategory.Few
            else -> PluralCategory.Other
        }

        // ── Slovenian (has a dual; cycles every hundred) ──────────────────────
        "sl" -> when (n100) {
            1L -> PluralCategory.One
            2L -> PluralCategory.Two
            3L, 4L -> PluralCategory.Few
            else -> PluralCategory.Other
        }

        // ── Lithuanian ────────────────────────────────────────────────────────
        "lt" -> when {
            n10 == 1L && n100 !in 11..19 -> PluralCategory.One
            n10 in 2..9 && n100 !in 11..19 -> PluralCategory.Few
            else -> PluralCategory.Other
        }

        // ── Latvian (zero form for tens and teens) ────────────────────────────
        "lv" -> when {
            n10 == 0L || n100 in 11..19 -> PluralCategory.Zero
            n10 == 1L && n100 != 11L -> PluralCategory.One
            else -> PluralCategory.Other
        }

        // ── Arabic ────────────────────────────────────────────────────────────
        "ar" -> when {
            n == 0L -> PluralCategory.Zero
            isOne -> PluralCategory.One
            isTwo -> PluralCategory.Two
            n100 in 3..10 -> PluralCategory.Few
            n100 in 11..99 -> PluralCategory.Many
            else -> PluralCategory.Other
        }

        // ── Hebrew, Inuktitut (singular, dual, plural) ────────────────────────
        "he", "iu" -> when {
            isOne -> PluralCategory.One
            isTwo -> PluralCategory.Two
            else -> PluralCategory.Other
        }

        else -> if (isOne) PluralCategory.One else PluralCategory.Other
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
            // Of the magnitude, so that -21 reads "-21st" like 21.
            val n10 = (n % 10).absoluteValue
            val n100 = (n % 100).absoluteValue
            when {
                n10 == 1L && n100 != 11L -> PluralCategory.One
                n10 == 2L && n100 != 12L -> PluralCategory.Two
                n10 == 3L && n100 != 13L -> PluralCategory.Few
                else -> PluralCategory.Other
            }
        }

        // ── French ────────────────────────────────────────────────────────────
        "fr" -> if (n == 1L || n == -1L) PluralCategory.One else PluralCategory.Other

        // ── Swedish / Danish ──────────────────────────────────────────────────
        "sv", "da" -> {
            val n10 = (n % 10).absoluteValue
            val n100 = (n % 100).absoluteValue
            if ((n10 == 1L || n10 == 2L) && n100 != 11L && n100 != 12L) PluralCategory.One else PluralCategory.Other
        }

        // ── Armenian / Georgian ───────────────────────────────────────────────
        "hy", "ka" -> if (n == 1L || n == -1L) PluralCategory.One else PluralCategory.Other

        // ── Others ────────────────────────────────────────────────────────────
        // Most other languages in the 55 set use a single ordinal form or a dot.
        else -> PluralCategory.Other
    }
}
