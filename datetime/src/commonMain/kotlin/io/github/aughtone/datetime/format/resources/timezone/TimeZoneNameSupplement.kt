package io.github.aughtone.datetime.format.resources.timezone

import io.github.aughtone.types.locale.Locale

/**
 * The small bundled supplement of time-zone names for languages that **no
 * platform's CLDR ships** — the coverage holes left by delegating zone names to
 * `java.time` (JVM/Android), Foundation (Apple) and `Intl` (JS/Wasm).
 *
 * The zone-name migration (#16) dropped the ~500 KB all-locale table and now
 * reads the reader's OS/runtime CLDR instead. That data is complete on the major
 * platforms for the common languages, but a few languages are absent everywhere —
 * most importantly **Inuktitut (`iu`)**, whose zone names would otherwise fall
 * back silently to English. Those languages are bundled here, and the resolver
 * consults this supplement **before** the platform so a supplemented language is
 * never at the mercy of the platform's English fallback.
 *
 * The set is deliberately tiny: it holds only the true cross-platform gap, not a
 * copy of what the platforms already localize. Entries are keyed by the same
 * English abbreviation ("EST", "EDT", …) the [TimeZoneAbbreviationLookup]
 * scaffolding derives from a zone and its offset. Add a language here only when
 * a per-platform coverage scan shows it is missing on every target.
 *
 * @see TimeZoneAbbreviationLookup for the resolver that consults this first.
 */
internal object TimeZoneNameSupplement {

    /**
     * The localized full name for [englishAbbr] in [locale], or `null` when this
     * supplement carries no name for that language — in which case the caller
     * delegates to the platform CLDR.
     */
    fun getFullName(englishAbbr: String, locale: Locale): String? = when (locale.languageCode) {
        "iu" -> fullNameIu(englishAbbr)
        else -> null
    }

    /**
     * The localized abbreviation for [englishAbbr] in [locale], or `null` when
     * this supplement carries no abbreviation for that language.
     */
    fun getAbbreviation(englishAbbr: String, locale: Locale): String? = when (locale.languageCode) {
        "iu" -> abbreviationIu(englishAbbr)
        else -> null
    }

    // Inuktitut (iu) — Canadian Inuit syllabics. No platform CLDR ships these, so
    // they are bundled directly to keep full Inuktitut zone coverage.
    private fun fullNameIu(englishAbbr: String): String? = when (englishAbbr) {
        "+00:00" -> "ᑲᓇᑕᒥ ᐊᑕᐅᓯᐅᖃᑎᒌᒃᑐᑦ ᓯᕿᓐᖑᔭᖓ"
        "-04:00" -> "ᑲᓇᓐᓇᐅᑉ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "-05:00" -> "ᕿᑎᕐᒥᐅᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "-06:00" -> "ᖃᑭᖅᑮᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "-07:00" -> "ᖃᑭᖅᑮᑦ ᓯᕿᓐᖑᔭᖓ"
        "-08:00" -> "ᐊᓛᓵᑲ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "-09:00" -> "ᐊᓛᓵᑲ ᓯᕿᓐᖑᔭᖓ"
        "-10:00" -> "ᕼᐊᐛᔨ-ᐊᓕᐅᑎᐊᓐ ᓯᕿᓐᖑᔭᖓ"
        "ACDT" -> "ᐋᓘᓯᑐᕋᓕᐊ ᕿᑎᕐᒥᐅᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "ACST" -> "ᐋᓘᓯᑐᕋᓕᐊ ᕿᑎᕐᒥᐅᑦ ᓯᕿᓐᖑᔭᖓ"
        "ADT" -> "ᐊᑦᓛᓐᑎᒃ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "AEDT" -> "ᐋᓘᓯᑐᕋᓕᐊ ᑲᓇᓐᓇᖓᑕ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "AEST" -> "ᐋᓘᓯᑐᕋᓕᐊ ᑲᓇᓐᓇᖓᑕ ᓯᕿᓐᖑᔭᖓ"
        "AFT" -> "ᐊᑉᑲᓂᓯᑕᓐ ᓯᕿᓐᖑᔭᖓ"
        "AKDT" -> "ᐊᓛᓵᑲ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "AKST" -> "ᐊᓛᓵᑲ ᓯᕿᓐᖑᔭᖓ"
        "AMST" -> "ᐋᒫᓲᓐ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "AMT" -> "ᐋᒫᓲᓐ ᓯᕿᓐᖑᔭᖓ"
        "ART" -> "ᐋᔾᔭᓐᑎᓇ ᓯᕿᓐᖑᔭᖓ"
        "AST" -> "ᐊᕋᐱᐊᓐ ᓯᕿᓐᖑᔭᖓ"
        "AWST" -> "ᐋᓘᓯᑐᕋᓕᐊ ᐱᖓᓐᓇᖓᑕ ᓯᕿᓐᖑᔭᖓ"
        "AZOST" -> "ᐊᓲᕋᔅ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "AZOT" -> "ᐊᓲᕋᔅ ᓯᕿᓐᖑᔭᖓ"
        "AZT" -> "ᐊᔪᕐᐸᐃᔭᓐ ᓯᕿᓐᖑᔭᖓ"
        "BOT" -> "ᐳᓕᕕᐊ ᓯᕿᓐᖑᔭᖓ"
        "BRST" -> "ᐳᕋᓯᓕᐊ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "BRT" -> "ᐳᕋᓯᓕᐊ ᓯᕿᓐᖑᔭᖓ"
        "BST" -> "ᐸᖕᓛᑎᔅ ᓯᕿᓐᖑᔭᖓ"
        "BTT" -> "ᐳᑖᓐ ᓯᕿᓐᖑᔭᖓ"
        "CAT" -> "ᕿᑎᕐᒥᐅᑦ ᐊᕗᕆᑲ ᓯᕿᓐᖑᔭᖓ"
        "CCT" -> "ᑯᑯᔅ ᕿᑭᖅᑕᑦ ᓯᕿᓐᖑᔭᖓ"
        "CDT" -> "ᕿᑎᕐᒥᐅᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "CEST" -> "ᕿᑎᕐᒥᐅᑦ ᔪᕈᑉ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "CET" -> "ᕿᑎᕐᒥᐅᑦ ᔪᕈᑉ ᓯᕿᓐᖑᔭᖓ"
        "CHADT" -> "ᒐᑕᒻ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "CHAST" -> "ᒐᑕᒻ ᓯᕿᓐᖑᔭᖓ"
        "CHUT" -> "ᒎᒃ ᓯᕿᓐᖑᔭᖓ"
        "CIT" -> "ᕿᑎᕐᒥᐅᑦ ᐃᓐᑐᓃᓯᐊ ᓯᕿᓐᖑᔭᖓ"
        "CLST" -> "ᒋᓕ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "CLT" -> "ᒋᓕ ᓯᕿᓐᖑᔭᖓ"
        "COST" -> "ᑯᓘᒻᐱᐊ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "COT" -> "ᑯᓘᒻᐱᐊ ᓯᕿᓐᖑᔭᖓ"
        "CST" -> "ᕿᑎᕐᒥᐅᑦ ᓯᕿᓐᖑᔭᖓ"
        "CT" -> "ᕿᑎᕐᒥᐅᑦ ᓯᕿᓐᖑᔭᖓ"
        "CVT" -> "ᑲᑉ ᕗᐃᑦ ᓯᕿᓐᖑᔭᖓ"
        "CXT" -> "ᑯᕆᔅᒪᔅ ᕿᑭᖅᑕᖅ ᓯᕿᓐᖑᔭᖓ"
        "DAVT" -> "ᑎᐃᕕᔅ ᓯᕿᓐᖑᔭᖓ"
        "EASST" -> "ᐃᔅᑐᕐ ᕿᑭᖅᑕᖅ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "EAST" -> "ᐃᔅᑐᕐ ᕿᑭᖅᑕᖅ ᓯᕿᓐᖑᔭᖓ"
        "EAT" -> "ᑲᓇᓐᓇᖓ ᐊᕗᕆᑲ ᓯᕿᓐᖑᔭᖓ"
        "ECT" -> "ᐃᑯᐊᑐᕐ ᓯᕿᓐᖑᔭᖓ"
        "EDT" -> "ᑲᓇᓐᓇᐅᑉ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "EEST" -> "ᑲᓇᓐᓇᖓ ᔪᕈᑉ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "EET" -> "ᑲᓇᓐᓇᖓ ᔪᕈᑉ ᓯᕿᓐᖑᔭᖓ"
        "EST" -> "ᑲᓇᓐᓇᐅᑉ ᓯᕿᓐᖑᔭᖓ"
        "FET" -> "ᐅᖓᓯᒃᑐᖅ ᑲᓇᓐᓇᖓ ᔪᕈᑉ ᓯᕿᓐᖑᔭᖓ"
        "FJST" -> "ᐱᔨ ᓯᕿᓐᖑᔭᖓ"
        "FJT" -> "ᐱᔨ ᓯᕿᓐᖑᔭᖓ"
        "FKST" -> "ᐸᐅᒃᓚᓐᑦ ᕿᑭᖅᑕᑦ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "FNT" -> "ᐱᕐᓇᓐᑐ ᑎ ᓄᕈᓐᔭ ᓯᕿᓐᖑᔭᖓ"
        "GALT" -> "ᒐᓛᐸᒍᔅ ᓯᕿᓐᖑᔭᖓ"
        "GAMT" -> "ᒐᒻᐱᐊᕐ ᓯᕿᓐᖑᔭᖓ"
        "GET" -> "ᔪᕐᔨᐊ ᓯᕿᓐᖑᔭᖓ"
        "GFT" -> "ᐅᐃᕖᑦ ᒋᐊᓇ ᓯᕿᓐᖑᔭᖓ"
        "GST" -> "ᑲᖏᖅᓱᐊᓗᒃ ᓯᕿᓐᖑᔭᖓ"
        "GYT" -> "ᒐᐃᐋᓇ ᓯᕿᓐᖑᔭᖓ"
        "HDT" -> "ᕼᐊᐛᔨ-ᐊᓕᐅᑎᐊᓐ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "HKT" -> "ᕼᐊᖕ ᑲᖕ ᓯᕿᓐᖑᔭᖓ"
        "HST" -> "ᕼᐊᐛᔨ-ᐊᓕᐅᑎᐊᓐ ᓯᕿᓐᖑᔭᖓ"
        "ICT" -> "ᐃᓐᑐᒐᐃᓇ ᓯᕿᓐᖑᔭᖓ"
        "IOT" -> "ᐃᓐᑎᐊᓐ ᐃᒪᕕᖓ ᓯᕿᓐᖑᔭᖓ"
        "IRDT" -> "ᐃᕋᓐ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "IRKT" -> "ᐃᕐᑯᑦᔅᒃ ᓯᕿᓐᖑᔭᖓ"
        "IRST" -> "ᐃᕋᓐ ᓯᕿᓐᖑᔭᖓ"
        "IST" -> "ᐃᓐᑎᐊ ᓯᕿᓐᖑᔭᖓ"
        "JST" -> "ᔭᐸᓐ ᓯᕿᓐᖑᔭᖓ"
        "KGT" -> "ᑭᕐᒋᔅᑖᓐ ᓯᕿᓐᖑᔭᖓ"
        "KOST" -> "ᑯᓱᕋᐃ ᓯᕿᓐᖑᔭᖓ"
        "KRAT" -> "ᑯᕋᔅᓄᔭᕐᔅᒃ ᓯᕿᓐᖑᔭᖓ"
        "LHST" -> "ᓘᕐᑦ ᕼᐅᕅ ᓯᕿᓐᖑᔭᖓ"
        "LINT" -> "ᓚᐃᓐ ᕿᑭᖅᑕᑦ ᓯᕿᓐᖑᔭᖓ"
        "MAGT" -> "ᒪᒐᑕᓐ ᓯᕿᓐᖑᔭᖓ"
        "MART" -> "ᒫᕐᑲᓴᔅ ᓯᕿᓐᖑᔭᖓ"
        "MAWT" -> "ᒫᓱᓐ ᓯᕿᓐᖑᔭᖓ"
        "MDT" -> "ᖃᑭᖅᑮᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "MHT" -> "ᒫᓴᓪ ᕿᑭᖅᑕᑦ ᓯᕿᓐᖑᔭᖓ"
        "MSK" -> "ᒫᔅᑯ ᓯᕿᓐᖑᔭᖓ"
        "MST" -> "ᖃᑭᖅᑮᑦ ᓯᕿᓐᖑᔭᖓ"
        "MUT" -> "ᒧᕆᓴᔅ ᓯᕿᓐᖑᔭᖓ"
        "MVT" -> "ᒫᓪᑎᕝᔅ ᓯᕿᓐᖑᔭᖓ"
        "MYT" -> "ᒪᓚᐃᓯᐊ ᓯᕿᓐᖑᔭᖓ"
        "NCT" -> "ᓄᑖᖅ ᑲᓕᑐᓂᐊ ᓯᕿᓐᖑᔭᖓ"
        "NOVST" -> "ᓄᕗᓯᐱᕐᔅᒃ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "NOVT" -> "ᓄᕗᓯᐱᕐᔅᒃ ᓯᕿᓐᖑᔭᖓ"
        "NPT" -> "ᓂᐸᓪ ᓯᕿᓐᖑᔭᖓ"
        "NUT" -> "ᓂᐅᐃ ᓯᕿᓐᖑᔭᖓ"
        "NZDT" -> "ᓄᑖᖅ ᓯᓚᓐᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "NZST" -> "ᓄᑖᖅ ᓯᓚᓐᑦ ᓯᕿᓐᖑᔭᖓ"
        "OMST" -> "ᐅᒻᔅᒃ ᓯᕿᓐᖑᔭᖓ"
        "PDT" -> "ᐸᓯᐱᒃ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "PET" -> "ᐱᕈ ᓯᕿᓐᖑᔭᖓ"
        "PETT" -> "ᑲᒻᒐᑦᑲ ᓯᕿᓐᖑᔭᖓ"
        "PGT" -> "ᐸᐳᐊ ᓄᑖᖅ ᒋᓂ ᓯᕿᓐᖑᔭᖓ"
        "PHT" -> "ᐱᓕᐲᓐᔅ ᓯᕿᓐᖑᔭᖓ"
        "PKT" -> "ᐸᑭᔅᑖᓐ ᓯᕿᓐᖑᔭᖓ"
        "PST" -> "ᐸᓯᐱᒃ ᓯᕿᓐᖑᔭᖓ"
        "PYST" -> "ᐸᕋᒍᐊᐃ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "PYT" -> "ᐸᕋᒍᐊᐃ ᓯᕿᓐᖑᔭᖓ"
        "ROTT" -> "ᕈᑐᕋ ᓯᕿᓐᖑᔭᖓ"
        "SAMT" -> "ᓴᒪᕋ ᓯᕿᓐᖑᔭᖓ"
        "SAST" -> "ᓂᒋᖓ ᐊᕗᕆᑲ ᓯᕿᓐᖑᔭᖓ"
        "SBT" -> "ᓱᓘᒪᓐ ᕿᑭᖅᑕᑦ ᓯᕿᓐᖑᔭᖓ"
        "SCT" -> "ᓯᓱᓪᔅ ᓯᕿᓐᖑᔭᖓ"
        "SRT" -> "ᓱᕆᓇᒻ ᓯᕿᓐᖑᔭᖓ"
        "SST" -> "ᓴᒧᐊ ᓯᕿᓐᖑᔭᖓ"
        "SYOT" -> "ᓯᔪᐛ ᓯᕿᓐᖑᔭᖓ"
        "TAHT" -> "ᑕᕼᐃᑎ ᓯᕿᓐᖑᔭᖓ"
        "TJT" -> "ᑕᔨᑭᔅᑖᓐ ᓯᕿᓐᖑᔭᖓ"
        "TKT" -> "ᑐᑲᓚᐅ ᓯᕿᓐᖑᔭᖓ"
        "TMT" -> "ᑐᕐᒃᒥᓂᔅᑖᓐ ᓯᕿᓐᖑᔭᖓ"
        "TOT" -> "ᑐᖕᒐ ᓯᕿᓐᖑᔭᖓ"
        "TVT" -> "ᑐᕙᓗ ᓯᕿᓐᖑᔭᖓ"
        "ULAT" -> "ᐅᓛᓐᐸᑐᕐ ᓯᕿᓐᖑᔭᖓ"
        "UTC" -> "ᑲᓇᑕᒥ ᐊᑕᐅᓯᐅᖃᑎᒌᒃᑐᑦ ᓯᕿᓐᖑᔭᖓ"
        "UYST" -> "ᐅᕈᒍᐊᐃ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "UYT" -> "ᐅᕈᒍᐊᐃ ᓯᕿᓐᖑᔭᖓ"
        "UZT" -> "ᐅᔭᐸᑭᔅᑖᓐ ᓯᕿᓐᖑᔭᖓ"
        "VET" -> "ᕗᓃᓱᐃᓛ ᓯᕿᓐᖑᔭᖓ"
        "VLAT" -> "ᕗᓛᑎᕗᔅᑐᒃ ᓯᕿᓐᖑᔭᖓ"
        "VOLT" -> "ᕗᓪᒍᒍᕋᑦ ᓯᕿᓐᖑᔭᖓ"
        "VOST" -> "ᕗᔅᑐᒃ ᓯᕿᓐᖑᔭᖓ"
        "WAKT" -> "ᐛᐃᒃ ᕿᑭᖅᑕᖅ ᓯᕿᓐᖑᔭᖓ"
        "WAT" -> "ᐱᖓᓐᓇᖓ ᐊᕗᕆᑲ ᓯᕿᓐᖑᔭᖓ"
        "WEST" -> "ᐱᖓᓐᓇᖓ ᔪᕈᑉ ᐊᐅᔭᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "WET" -> "ᐱᖓᓐᓇᖓ ᔪᕈᑉ ᓯᕿᓐᖑᔭᖓ"
        "WIT" -> "ᐱᖓᓐᓇᖓ ᐃᓐᑐᓃᓯᐊ ᓯᕿᓐᖑᔭᖓ"
        "YAKT" -> "ᔭᑯᑦᔅᒃ ᓯᕿᓐᖑᔭᖓ"
        "YEKT" -> "ᔨᑲᑎᕆᓐᐳᕐᒃ ᓯᕿᓐᖑᔭᖓ"
        "Z" -> "ᑲᓇᑕᒥ ᐊᑕᐅᓯᐅᖃᑎᒌᒃᑐᑦ ᓯᕿᓐᖑᔭᖓ"
        "NDT" -> "ᓂᐅᕗᓪᓚᓐᑦ ᐅᓪᓗᒃᑯᑦ ᓯᕿᓐᖑᔭᖓ"
        "NST" -> "ᓂᐅᕗᓪᓚᓐᑦ ᓯᕿᓐᖑᔭᖓ"
        "NT" -> "ᓂᐅᕗᓪᓚᓐᑦ ᓯᕿᓐᖑᔭᖓ"
        "VUT" -> "ᕗᓄᐊᑐ ᓯᕿᓐᖑᔭᖓ"
        else -> null
    }

    private fun abbreviationIu(englishAbbr: String): String? = when (englishAbbr) {
        "+00:00" -> "UTC"
        "UTC" -> "UTC"
        "Z" -> "UTC"
        else -> null
    }
}
