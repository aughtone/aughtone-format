package io.github.aughtone.readable.ordinality

import io.github.aughtone.types.locale.Locale

private val enOrdinalFormatter: OrdinalFormatter = { n ->
    val r10 = n % 10
    val r100 = n % 100
    val suffix = when {
        r10 == 1L && r100 != 11L -> "st"
        r10 == 2L && r100 != 12L -> "nd"
        r10 == 3L && r100 != 13L -> "rd"
        else -> "th"
    }
    "$n$suffix"
}

private val frOrdinalFormatter: OrdinalFormatter = { n ->
    if (n == 1L) "${n}er" else "${n}e"
}

private val svOrdinalFormatter: OrdinalFormatter = { n ->
    val r10 = n % 10
    val r100 = n % 100
    if ((r10 == 1L || r10 == 2L) && (r100 != 11L && r100 != 12L)) "${n}:a" else "${n}:e"
}

internal val ordinalityMap: Map<String, OrdinalFormatter> = mapOf(
    "af" to suffixFormatter("e"),
    "ar" to suffixFormatter("."),
    "az" to suffixFormatter("."),
    "be" to suffixFormatter("-й"),
    "bg" to suffixFormatter("."),
    "ca" to suffixFormatter("."),
    "cs" to suffixFormatter("."),
    "da" to suffixFormatter("."),
    "de" to suffixFormatter("."),
    "el" to suffixFormatter("ος"),
    "en" to enOrdinalFormatter,
    "es" to suffixFormatter(".º"),
    "et" to suffixFormatter("."),
    "eu" to suffixFormatter("."),
    "fa" to suffixFormatter("ام"),
    "fi" to suffixFormatter("."),
    "fr" to frOrdinalFormatter,
    "gl" to suffixFormatter(".º"),
    "hi" to suffixFormatter("वां"),
    "hr" to suffixFormatter("."),
    "hu" to suffixFormatter("."),
    "id" to prefixFormatter("ke-"),
    "is" to suffixFormatter("."),
    "it" to suffixFormatter(".º"),
    "ja" to prefixFormatter("第"),
    "kk" to suffixFormatter("-ші"),
    "ko" to suffixFormatter("번째"),
    "lt" to suffixFormatter("."),
    "lv" to suffixFormatter("."),
    "mk" to suffixFormatter("."),
    "ms" to prefixFormatter("ke-"),
    "nb" to suffixFormatter("."),
    "nl" to suffixFormatter("e"),
    "nn" to suffixFormatter("."),
    "no" to suffixFormatter("."),
    "pl" to suffixFormatter("."),
    "pt" to suffixFormatter(".º"),
    "ro" to suffixFormatter("."),
    "ru" to suffixFormatter("-й"),
    "sk" to suffixFormatter("."),
    "sl" to suffixFormatter("."),
    "sq" to suffixFormatter("."),
    "sr" to suffixFormatter("."),
    "sv" to svOrdinalFormatter,
    "sw" to suffixFormatter("."),
    "th" to prefixFormatter("ที่ "),
    "tr" to suffixFormatter("."),
    "uk" to suffixFormatter("-й"),
    "uz" to suffixFormatter("."),
    "vi" to prefixFormatter("thứ "),
    "zh" to prefixFormatter("第"),
)

/**
 * Retrieves the [OrdinalFormatter] for a given [Locale].
 * Uses the language code for lookup with fallback to English.
 */
fun ordinalityFor(locale: Locale): OrdinalFormatter {
    val languageTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = languageTag
    while (currentTag.isNotEmpty()) {
        val formatter = ordinalityMap[currentTag]
        if (formatter != null) {
            return formatter
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return ordinalityMap["en"]!!
}
