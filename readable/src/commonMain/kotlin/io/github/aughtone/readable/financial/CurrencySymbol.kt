package io.github.aughtone.readable.financial

/**
 * Which symbol an amount is written with, from Unicode CLDR's symbols for the locale.
 *
 * Several currencies share a sign: Canadian, US and Australian dollars are all `$`. CLDR's
 * standard symbol tells them apart from the reader's point of view, so it depends on the locale as
 * much as the currency — a Canadian dollar is `"CA$"` in US English but `"$"` in Canadian English,
 * where the US dollar is `"US$"` instead. The narrow symbol is the bare sign, for where the context
 * already says which currency it is.
 *
 * A currency CLDR has no symbol for — a fund code such as `"BOV"`, or a code made up for a test —
 * is written with its own [io.github.aughtone.types.financial.Currency.symbol] in [Standard] and
 * [Narrow].
 */
enum class CurrencySymbol {
    /**
     * CLDR's standard symbol in the locale, which tells a currency from others that share its
     * sign: `"CA$5.00"` and `"$5.00"` in US English, `"$5.00"` and `"US$5.00"` in Canadian English,
     * `"5,00 $ US"` in Canadian French. Where CLDR has no symbol for the currency in the locale,
     * this is its ISO code (`"AED 5.00"` in English). The default.
     */
    Standard,

    /**
     * CLDR's narrow symbol: the shortest sign the locale writes the currency with, `"$"` for every
     * dollar. Use it where the context already says which currency an amount is in.
     */
    Narrow,

    /** The ISO 4217 code, placed and spaced as a symbol: `"CAD 5.00"` in English, `"5,00 CAD"` in French. */
    Code,
}
