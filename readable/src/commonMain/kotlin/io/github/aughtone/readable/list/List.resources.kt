package io.github.aughtone.readable.list

import io.github.aughtone.types.locale.Locale

/**
 * The four CLDR list-pattern templates for one locale and [ListType]. Each is a
 * template with `{0}` and `{1}` placeholders.
 *
 * - [two] joins exactly two elements.
 * - [start] joins the first element to the running tail.
 * - [middle] joins each interior element.
 * - [end] joins the final element.
 */
internal data class ListPatterns(
    val two: String,
    val start: String,
    val middle: String,
    val end: String,
)

// Comma glue shared by every seeded locale for the start/middle joins and for
// the whole of a Unit list.
private const val COMMA = "{0}, {1}"

// Seeded, hand-verified wide patterns. English carries all three types and is
// the fallback for every unlisted locale; the others carry the conjunction
// types only (their Unit list falls back to English, which is a plain comma
// join). Additional locales, and the short/narrow widths, are to be filled in
// from CLDR — until then everything else resolves to English. Base conjunction
// forms only: the euphonic Spanish y→e / o→u changes are not applied, matching
// the CLDR root.
private val listPatterns: Map<String, Map<ListType, ListPatterns>> = mapOf(
    "en" to mapOf(
        ListType.And to ListPatterns(two = "{0} and {1}", start = COMMA, middle = COMMA, end = "{0}, and {1}"),
        ListType.Or to ListPatterns(two = "{0} or {1}", start = COMMA, middle = COMMA, end = "{0}, or {1}"),
        ListType.Unit to ListPatterns(two = COMMA, start = COMMA, middle = COMMA, end = COMMA),
    ),
    "fr" to mapOf(
        ListType.And to ListPatterns(two = "{0} et {1}", start = COMMA, middle = COMMA, end = "{0} et {1}"),
        ListType.Or to ListPatterns(two = "{0} ou {1}", start = COMMA, middle = COMMA, end = "{0} ou {1}"),
    ),
    "de" to mapOf(
        ListType.And to ListPatterns(two = "{0} und {1}", start = COMMA, middle = COMMA, end = "{0} und {1}"),
        ListType.Or to ListPatterns(two = "{0} oder {1}", start = COMMA, middle = COMMA, end = "{0} oder {1}"),
    ),
    "es" to mapOf(
        ListType.And to ListPatterns(two = "{0} y {1}", start = COMMA, middle = COMMA, end = "{0} y {1}"),
        ListType.Or to ListPatterns(two = "{0} o {1}", start = COMMA, middle = COMMA, end = "{0} o {1}"),
    ),
)

/**
 * The [ListPatterns] for [locale] and [type], resolved with the same BCP-47
 * fallback the rest of the module uses: a region variant falls back to its
 * language, and anything without a bundled entry for the requested [type] falls
 * back to English. English carries every [ListType], so this always resolves.
 */
internal fun listPatternsFor(locale: Locale, type: ListType): ListPatterns {
    var tag = if (locale.regionCode != null) {
        "${locale.languageCode}-${locale.regionCode}"
    } else {
        locale.languageCode
    }
    while (tag.isNotEmpty()) {
        listPatterns[tag]?.get(type)?.let { return it }
        tag = tag.substringBeforeLast('-', "")
    }
    return listPatterns.getValue("en").getValue(type)
}
