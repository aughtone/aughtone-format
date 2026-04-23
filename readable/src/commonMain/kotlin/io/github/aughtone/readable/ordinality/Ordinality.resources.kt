package io.github.aughtone.readable.ordinality

import io.github.aughtone.readable.PluralCategory
import io.github.aughtone.readable.ordinalCategoryFor
import io.github.aughtone.types.locale.Locale

private fun buildFormatter(
    locale: Locale,
    forms: Map<PluralCategory, String>,
    isPrefix: Boolean = false
): OrdinalFormatter = { n ->
    val category = ordinalCategoryFor(locale, n)
    val affix = forms[category] ?: forms[PluralCategory.Other] ?: ""
    if (isPrefix) "$affix$n" else "$n$affix"
}

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun buildOrdinalFormatter(tag: String, locale: Locale): OrdinalFormatter? {
    return when (tag) {
        "en" -> buildFormatter(locale, mapOf(
            PluralCategory.One to "st",
            PluralCategory.Two to "nd",
            PluralCategory.Few to "rd",
            PluralCategory.Other to "th"
        ))
        "fr" -> buildFormatter(locale, mapOf(
            PluralCategory.One to "er",
            PluralCategory.Other to "e"
        ))
        "hy" -> buildFormatter(locale, mapOf(
            PluralCategory.One to "-ին",
            PluralCategory.Other to "-րդ"
        ))
        "ka" -> buildFormatter(locale, mapOf(
            PluralCategory.One to "-լի",
            PluralCategory.Other to "-ե"
        ))
        "sv", "da" -> buildFormatter(locale, mapOf(
            PluralCategory.One to ":a",
            PluralCategory.Other to ":e"
        ))
        "af", "nl"       -> suffixFormatter("e")
        "be", "ru", "uk" -> suffixFormatter("-й")
        "el"             -> suffixFormatter("ος")
        "fa"             -> suffixFormatter("ام")
        "hi"             -> suffixFormatter("वां")
        "kk"             -> suffixFormatter("-ші")
        "ko"             -> suffixFormatter("번째")
        "th"             -> prefixFormatter("ที่ ")
        "vi"             -> prefixFormatter("thứ ")
        "id", "ms"       -> prefixFormatter("ke-")
        "ja", "zh"       -> prefixFormatter("第")
        "es", "gl", "it", "pt" -> suffixFormatter(".º")
        
        // All remaining locales use "." suffix
        "ar", "az", "bg", "ca", "cs", "de", "et", "eu",
        "fi", "hr", "hu", "is", "iu", "lt", "lv", "mk", "nb", "nn",
        "no", "pl", "ro", "sk", "sl", "sq", "sr", "sw", "tr",
        "uz", "he" -> suffixFormatter(".")
        
        else -> null
    }
}

// ── Lazy cache ────────────────────────────────────────────────────────────────

private val ordinalityCache = mutableMapOf<String, OrdinalFormatter>()

/**
 * Retrieves the [OrdinalFormatter] for a given [Locale], building and caching it on first use.
 * Falls back through language subtags (e.g. "fr-CA" → "fr") before defaulting to English.
 */
fun ordinalityFor(locale: Locale): OrdinalFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = fullTag
    while (currentTag.isNotEmpty()) {
        val cached = ordinalityCache[currentTag]
        if (cached != null) return cached
        val built = buildOrdinalFormatter(currentTag, locale)
        if (built != null) {
            ordinalityCache[currentTag] = built
            return built
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return ordinalityCache.getOrPut("en") { buildOrdinalFormatter("en", Locale("en"))!! }
}
