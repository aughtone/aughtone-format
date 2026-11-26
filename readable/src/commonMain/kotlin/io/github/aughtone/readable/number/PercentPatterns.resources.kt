package io.github.aughtone.readable.number

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR 48.0 by tools/cldr/number-symbols.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Where each bundled language, and each region that does it differently, puts the percent sign and
 * the minus sign, from Unicode CLDR's percent pattern. Keyed by BCP 47 tag like [cldrNumberStyles]
 * and looked up the same way, ending at [englishPercentPattern]. See [PercentPattern].
 */
internal val cldrPercentPatterns: Map<String, PercentPattern> = mapOf(
    "be" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "ca" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "cs" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "da" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "de" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "de-CH" to PercentPattern("#%", "-#%"),
    "de-LI" to PercentPattern("#%", "-#%"),
    "en-AT" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "en-DE" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "en-DK" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "en-FI" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "en-NO" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "en-SE" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "es" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "es-AR" to PercentPattern("#%", "-#%"),
    "es-BO" to PercentPattern("#%", "-#%"),
    "es-BR" to PercentPattern("#%", "-#%"),
    "es-BZ" to PercentPattern("#%", "-#%"),
    "es-CL" to PercentPattern("#%", "-#%"),
    "es-CO" to PercentPattern("#%", "-#%"),
    "es-CR" to PercentPattern("#%", "-#%"),
    "es-CU" to PercentPattern("#%", "-#%"),
    "es-DO" to PercentPattern("#%", "-#%"),
    "es-EC" to PercentPattern("#%", "-#%"),
    "es-GT" to PercentPattern("#%", "-#%"),
    "es-HN" to PercentPattern("#%", "-#%"),
    "es-MX" to PercentPattern("#%", "-#%"),
    "es-NI" to PercentPattern("#%", "-#%"),
    "es-PA" to PercentPattern("#%", "-#%"),
    "es-PE" to PercentPattern("#%", "-#%"),
    "es-PR" to PercentPattern("#%", "-#%"),
    "es-PY" to PercentPattern("#%", "-#%"),
    "es-SV" to PercentPattern("#%", "-#%"),
    "es-US" to PercentPattern("#%", "-#%"),
    "es-UY" to PercentPattern("#%", "-#%"),
    "es-VE" to PercentPattern("#%", "-#%"),
    "eu" to PercentPattern("%\u00A0#", "-%\u00A0#"),
    "fi" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "fr" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "fr-CH" to PercentPattern("#%", "-#%"),
    "gl" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "hr" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "lt" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "mk" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "nb" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "nn" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "no" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "ro" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "ru" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "sk" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "sl" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "sv" to PercentPattern("#\u00A0%", "-#\u00A0%"),
    "tr" to PercentPattern("%#", "-%#"),
    "ml" to PercentPattern("#%", "-#%", indian = false),
    "mr" to PercentPattern("#%", "-#%", indian = false),
    "te" to PercentPattern("#%", "-#%", indian = false),
)

/** Where English, and any language CLDR has no percent pattern for, puts the percent sign. */
internal val englishPercentPattern: PercentPattern = PercentPattern("#%", "-#%")
