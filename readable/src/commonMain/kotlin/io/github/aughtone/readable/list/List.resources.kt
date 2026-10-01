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

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR (cldr-json, cldr-misc-full) — do not edit by hand;
// regenerate from the source. Wide forms only (no short/narrow yet). Locales not
// listed fall back to English via listPatternsFor. Canonical sets are shared
// across locales that use identical patterns, so the table stays small.
// ─────────────────────────────────────────────────────────────────────────────

// Canonical CLDR wide list patterns, deduplicated: many locales share a set.
private val P00 = ListPatterns(two = "{0} en {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} en {1}")
private val P01 = ListPatterns(two = "{0} of {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} of {1}")
private val P02 = ListPatterns(two = "{0}, {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}")
private val P03 = ListPatterns(two = "{0} و{1}", start = "{0} و{1}", middle = "{0} و{1}", end = "{0} و{1}")
private val P04 = ListPatterns(two = "{0} أو {1}", start = "{0} أو {1}", middle = "{0} أو {1}", end = "{0} أو {1}")
private val P05 = ListPatterns(two = "{0} و{1}", start = "{0}، و{1}", middle = "{0}، و{1}", end = "{0}، و{1}")
private val P06 = ListPatterns(two = "{0} və {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} və {1}")
private val P07 = ListPatterns(two = "{0} yaxud {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, yaxud {1}")
private val P08 = ListPatterns(two = "{0} і {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} і {1}")
private val P09 = ListPatterns(two = "{0} ці {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ці {1}")
private val P10 = ListPatterns(two = "{0} {1}", start = "{0} {1}", middle = "{0} {1}", end = "{0} {1}")
private val P11 = ListPatterns(two = "{0} и {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} и {1}")
private val P12 = ListPatterns(two = "{0} или {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} или {1}")
private val P13 = ListPatterns(two = "{0} i {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} i {1}")
private val P14 = ListPatterns(two = "{0} o {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} o {1}")
private val P15 = ListPatterns(two = "{0} a {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} a {1}")
private val P16 = ListPatterns(two = "{0} nebo {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} nebo {1}")
private val P17 = ListPatterns(two = "{0} og {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} og {1}")
private val P18 = ListPatterns(two = "{0} eller {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} eller {1}")
private val P19 = ListPatterns(two = "{0} und {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} und {1}")
private val P20 = ListPatterns(two = "{0} oder {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} oder {1}")
private val P21 = ListPatterns(two = "{0}, {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} und {1}")
private val P22 = ListPatterns(two = "{0} και {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} και {1}")
private val P23 = ListPatterns(two = "{0} ή {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ή {1}")
private val P24 = ListPatterns(two = "{0} and {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, and {1}")
private val P25 = ListPatterns(two = "{0} or {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, or {1}")
private val P26 = ListPatterns(two = "{0} y {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} y {1}")
private val P27 = ListPatterns(two = "{0} ja {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ja {1}")
private val P28 = ListPatterns(two = "{0} või {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} või {1}")
private val P29 = ListPatterns(two = "{0} eta {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} eta {1}")
private val P30 = ListPatterns(two = "{0} edo {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} edo {1}")
private val P31 = ListPatterns(two = "{0} و {1}", start = "{0}،‏ {1}", middle = "{0}،‏ {1}", end = "{0}، و {1}")
private val P32 = ListPatterns(two = "{0} یا {1}", start = "{0}،‏ {1}", middle = "{0}،‏ {1}", end = "{0}، یا {1}")
private val P33 = ListPatterns(two = "{0} tai {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} tai {1}")
private val P34 = ListPatterns(two = "{0} et {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} et {1}")
private val P35 = ListPatterns(two = "{0} ou {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ou {1}")
private val P36 = ListPatterns(two = "{0} e {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} e {1}")
private val P37 = ListPatterns(two = "{0} ו{1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ו{1}")
private val P38 = ListPatterns(two = "{0} או {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} או {1}")
private val P39 = ListPatterns(two = "{0}, {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ו-{1}")
private val P40 = ListPatterns(two = "{0} और {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, और {1}")
private val P41 = ListPatterns(two = "{0} या {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} या {1}")
private val P42 = ListPatterns(two = "{0} ili {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ili {1}")
private val P43 = ListPatterns(two = "{0} és {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} és {1}")
private val P44 = ListPatterns(two = "{0} vagy {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} vagy {1}")
private val P45 = ListPatterns(two = "{0} և {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} և {1}")
private val P46 = ListPatterns(two = "{0} կամ {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} կամ {1}")
private val P47 = ListPatterns(two = "{0} dan {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, dan {1}")
private val P48 = ListPatterns(two = "{0} atau {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, atau {1}")
private val P49 = ListPatterns(two = "{0} eða {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} eða {1}")
private val P50 = ListPatterns(two = "{0}、{1}", start = "{0}、{1}", middle = "{0}、{1}", end = "{0}、{1}")
private val P51 = ListPatterns(two = "{0}または{1}", start = "{0}、{1}", middle = "{0}、{1}", end = "{0}、または{1}")
private val P52 = ListPatterns(two = "{0} და {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} და {1}")
private val P53 = ListPatterns(two = "{0} ან {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ან {1}")
private val P54 = ListPatterns(two = "{0} және {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}")
private val P55 = ListPatterns(two = "{0} не {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, не болмаса {1}")
private val P56 = ListPatterns(two = "{0} 및 {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} 및 {1}")
private val P57 = ListPatterns(two = "{0} 또는 {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} 또는 {1}")
private val P58 = ListPatterns(two = "{0} ir {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ir {1}")
private val P59 = ListPatterns(two = "{0} ar {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ar {1}")
private val P60 = ListPatterns(two = "{0} ir {1}", start = "{0} {1}", middle = "{0} {1}", end = "{0} ir {1}")
private val P61 = ListPatterns(two = "{0} un {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} un {1}")
private val P62 = ListPatterns(two = "{0} vai {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} vai {1}")
private val P63 = ListPatterns(two = "{0} dan {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} dan {1}")
private val P64 = ListPatterns(two = "{0} lub {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} lub {1}")
private val P65 = ListPatterns(two = "{0} și {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} și {1}")
private val P66 = ListPatterns(two = "{0} sau {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} sau {1}")
private val P67 = ListPatterns(two = "{0} și {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0}, {1}")
private val P68 = ListPatterns(two = "{0} a {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} a {1}")
private val P69 = ListPatterns(two = "{0} alebo {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} alebo {1}")
private val P70 = ListPatterns(two = "{0} in {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} in {1}")
private val P71 = ListPatterns(two = "{0} ali {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ali {1}")
private val P72 = ListPatterns(two = "{0} dhe {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} dhe {1}")
private val P73 = ListPatterns(two = "{0} ose {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ose {1}")
private val P74 = ListPatterns(two = "{0} och {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} och {1}")
private val P75 = ListPatterns(two = "{0} na {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} na {1}")
private val P76 = ListPatterns(two = "{0} au {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} au {1}")
private val P77 = ListPatterns(two = "{0}และ{1}", start = "{0} {1}", middle = "{0} {1}", end = "{0} และ{1}")
private val P78 = ListPatterns(two = "{0} หรือ {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} หรือ {1}")
private val P79 = ListPatterns(two = "{0} และ {1}", start = "{0} {1}", middle = "{0} {1}", end = "{0} และ {1}")
private val P80 = ListPatterns(two = "{0} ve {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} ve {1}")
private val P81 = ListPatterns(two = "{0} veya {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} veya {1}")
private val P82 = ListPatterns(two = "{0} або {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} або {1}")
private val P83 = ListPatterns(two = "{0} va {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} va {1}")
private val P84 = ListPatterns(two = "{0} yoki {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} yoki {1}")
private val P85 = ListPatterns(two = "{0} và {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} và {1}")
private val P86 = ListPatterns(two = "{0} hoặc {1}", start = "{0}, {1}", middle = "{0}, {1}", end = "{0} hoặc {1}")
private val P87 = ListPatterns(two = "{0}和{1}", start = "{0}、{1}", middle = "{0}、{1}", end = "{0}和{1}")
private val P88 = ListPatterns(two = "{0}或{1}", start = "{0}、{1}", middle = "{0}、{1}", end = "{0}或{1}")
private val P89 = ListPatterns(two = "{0}{1}", start = "{0}{1}", middle = "{0}{1}", end = "{0}{1}")

private val listPatterns: Map<String, Map<ListType, ListPatterns>> = mapOf(
    "af" to mapOf(ListType.And to P00, ListType.Or to P01, ListType.Unit to P02),
    "ar" to mapOf(ListType.And to P03, ListType.Or to P04, ListType.Unit to P05),
    "az" to mapOf(ListType.And to P06, ListType.Or to P07, ListType.Unit to P02),
    "be" to mapOf(ListType.And to P08, ListType.Or to P09, ListType.Unit to P10),
    "bg" to mapOf(ListType.And to P11, ListType.Or to P12, ListType.Unit to P11),
    "ca" to mapOf(ListType.And to P13, ListType.Or to P14, ListType.Unit to P13),
    "cs" to mapOf(ListType.And to P15, ListType.Or to P16, ListType.Unit to P15),
    "da" to mapOf(ListType.And to P17, ListType.Or to P18, ListType.Unit to P17),
    "de" to mapOf(ListType.And to P19, ListType.Or to P20, ListType.Unit to P21),
    "el" to mapOf(ListType.And to P22, ListType.Or to P23, ListType.Unit to P02),
    "en" to mapOf(ListType.And to P24, ListType.Or to P25, ListType.Unit to P02),
    "es" to mapOf(ListType.And to P26, ListType.Or to P14, ListType.Unit to P26),
    "et" to mapOf(ListType.And to P27, ListType.Or to P28, ListType.Unit to P02),
    "eu" to mapOf(ListType.And to P29, ListType.Or to P30, ListType.Unit to P29),
    "fa" to mapOf(ListType.And to P31, ListType.Or to P32, ListType.Unit to P31),
    "fi" to mapOf(ListType.And to P27, ListType.Or to P33, ListType.Unit to P27),
    "fr" to mapOf(ListType.And to P34, ListType.Or to P35, ListType.Unit to P34),
    "gl" to mapOf(ListType.And to P36, ListType.Or to P35, ListType.Unit to P36),
    "he" to mapOf(ListType.And to P37, ListType.Or to P38, ListType.Unit to P39),
    "hi" to mapOf(ListType.And to P40, ListType.Or to P41, ListType.Unit to P40),
    "hr" to mapOf(ListType.And to P13, ListType.Or to P42, ListType.Unit to P13),
    "hu" to mapOf(ListType.And to P43, ListType.Or to P44, ListType.Unit to P43),
    "hy" to mapOf(ListType.And to P45, ListType.Or to P46, ListType.Unit to P45),
    "id" to mapOf(ListType.And to P47, ListType.Or to P48, ListType.Unit to P02),
    "is" to mapOf(ListType.And to P17, ListType.Or to P49, ListType.Unit to P17),
    "it" to mapOf(ListType.And to P36, ListType.Or to P14, ListType.Unit to P36),
    "iu" to mapOf(ListType.And to P02, ListType.Or to P25, ListType.Unit to P02),
    "ja" to mapOf(ListType.And to P50, ListType.Or to P51, ListType.Unit to P10),
    "ka" to mapOf(ListType.And to P52, ListType.Or to P53, ListType.Unit to P02),
    "kk" to mapOf(ListType.And to P54, ListType.Or to P55, ListType.Unit to P10),
    "ko" to mapOf(ListType.And to P56, ListType.Or to P57, ListType.Unit to P10),
    "lt" to mapOf(ListType.And to P58, ListType.Or to P59, ListType.Unit to P60),
    "lv" to mapOf(ListType.And to P61, ListType.Or to P62, ListType.Unit to P61),
    "mk" to mapOf(ListType.And to P11, ListType.Or to P12, ListType.Unit to P11),
    "ms" to mapOf(ListType.And to P63, ListType.Or to P48, ListType.Unit to P02),
    "nb" to mapOf(ListType.And to P17, ListType.Or to P18, ListType.Unit to P17),
    "nl" to mapOf(ListType.And to P00, ListType.Or to P01, ListType.Unit to P00),
    "nn" to mapOf(ListType.And to P17, ListType.Or to P18, ListType.Unit to P02),
    "no" to mapOf(ListType.And to P17, ListType.Or to P18, ListType.Unit to P17),
    "pl" to mapOf(ListType.And to P13, ListType.Or to P64, ListType.Unit to P13),
    "pt" to mapOf(ListType.And to P36, ListType.Or to P35, ListType.Unit to P36),
    "ro" to mapOf(ListType.And to P65, ListType.Or to P66, ListType.Unit to P67),
    "ru" to mapOf(ListType.And to P11, ListType.Or to P12, ListType.Unit to P10),
    "sk" to mapOf(ListType.And to P68, ListType.Or to P69, ListType.Unit to P02),
    "sl" to mapOf(ListType.And to P70, ListType.Or to P71, ListType.Unit to P70),
    "sq" to mapOf(ListType.And to P72, ListType.Or to P73, ListType.Unit to P36),
    "sr" to mapOf(ListType.And to P11, ListType.Or to P12, ListType.Unit to P11),
    "sv" to mapOf(ListType.And to P74, ListType.Or to P18, ListType.Unit to P02),
    "sw" to mapOf(ListType.And to P75, ListType.Or to P76, ListType.Unit to P75),
    "th" to mapOf(ListType.And to P77, ListType.Or to P78, ListType.Unit to P79),
    "tr" to mapOf(ListType.And to P80, ListType.Or to P81, ListType.Unit to P10),
    "uk" to mapOf(ListType.And to P08, ListType.Or to P82, ListType.Unit to P08),
    "uz" to mapOf(ListType.And to P83, ListType.Or to P84, ListType.Unit to P10),
    "vi" to mapOf(ListType.And to P85, ListType.Or to P86, ListType.Unit to P02),
    "zh" to mapOf(ListType.And to P87, ListType.Or to P88, ListType.Unit to P89),
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
