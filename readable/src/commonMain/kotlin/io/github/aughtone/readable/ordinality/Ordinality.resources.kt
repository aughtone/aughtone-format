package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun buildOrdinalFormatter(code: String): OrdinalFormatter? = when (code) {
    "en" -> { n ->
        val r10 = n % 10; val r100 = n % 100
        val suffix = when {
            r10 == 1L && r100 != 11L -> "st"
            r10 == 2L && r100 != 12L -> "nd"
            r10 == 3L && r100 != 13L -> "rd"
            else -> "th"
        }
        "$n$suffix"
    }
    "fr" -> { n -> if (n == 1L) "${n}er" else "${n}e" }
    "sv" -> { n ->
        val r10 = n % 10; val r100 = n % 100
        if ((r10 == 1L || r10 == 2L) && (r100 != 11L && r100 != 12L)) "${n}:a" else "${n}:e"
    }
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
    "ar", "az", "bg", "ca", "cs", "da", "de", "et", "eu",
    "fi", "hr", "hu", "is", "lt", "lv", "mk", "nb", "nn",
    "no", "pl", "ro", "sk", "sl", "sq", "sr", "sw", "tr",
    "uz" -> suffixFormatter(".")
    else -> null
}

// ── Lazy cache ────────────────────────────────────────────────────────────────

private val ordinalityCache = mutableMapOf<String, OrdinalFormatter>()

/**
 * Retrieves the [OrdinalFormatter] for a given [Locale], building and caching it on first use.
 * Falls back through language subtags (e.g. "fr-CA" → "fr") before defaulting to English.
 */
fun ordinalityFor(locale: Locale): OrdinalFormatter {
    val languageTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = languageTag
    while (currentTag.isNotEmpty()) {
        val cached = ordinalityCache[currentTag]
        if (cached != null) return cached
        val built = buildOrdinalFormatter(currentTag)
        if (built != null) {
            ordinalityCache[currentTag] = built
            return built
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return ordinalityCache.getOrPut("en") { buildOrdinalFormatter("en")!! }
}
