@file:Suppress("DEPRECATION")

package io.github.aughtone.readable.financial

import io.github.aughtone.readable.number.NumberStyle
import io.github.aughtone.readable.number.Separators
import io.github.aughtone.readable.number.numberStyleFor
import io.github.aughtone.readable.number.formatDecimalText
import io.github.aughtone.readable.number.toAscii
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.number.RoundingMode
import kotlin.concurrent.Volatile

/**
 * A formatter specifically for rendering a [Money] value into a human-readable string.
 * It encapsulates both the number formatting (separators) and the currency symbol placement.
 */
typealias MoneyFormatter = (Money) -> String

/**
 * Where a locale puts an amount's currency symbol and minus sign, from Unicode CLDR's standard
 * currency pattern. The values for each locale are generated into [cldrCurrencyPatterns]; see
 * `tools/cldr/number-symbols.mjs`.
 *
 * Each template is the amount's layout, with `¤` for the symbol, `#` for the digits and `-` for
 * the minus sign; any other character is written as it is, which in CLDR's data is only ever a
 * no-break space. English is `"¤#"` and `"-¤#"` (`"$5.00"`, `"-$5.00"`), Dutch `"¤ #"` and
 * `"¤ -#"` (`"€ -5,00"`), French `"# ¤"` and `"-# ¤"`.
 *
 * @property positive The layout of a positive amount or zero.
 * @property negative The layout of a negative amount.
 * @property decimal The decimal separator for an amount, where CLDR gives money one of its own
 *   (Swiss French writes `"1'234,5"` but `"1'234.50 CHF"`); `null` to use the locale's number style.
 * @property group The group separator for an amount, where CLDR gives money one of its own
 *   (Austrian German groups numbers with a space but amounts with a dot); `null` likewise.
 * @property minimumGroupingDigits How many digits must stand before the first group separator in
 *   an amount, where that differs from the locale's numbers; `null` likewise.
 * @property indian Whether an amount is grouped the Indian way, where that differs from the
 *   locale's numbers (Malayalam groups numbers in lakhs and amounts in threes); `null` likewise.
 */
internal class CurrencyPattern(
    val positive: String,
    val negative: String,
    val decimal: String? = null,
    val group: String? = null,
    val minimumGroupingDigits: Int? = null,
    val indian: Boolean? = null,
)

/**
 * Writes an amount in this pattern: [digits] is the number without its sign, [minus] the locale's
 * minus sign, [symbol] the currency symbol.
 *
 * Applies CLDR's currency-spacing rule: where the symbol stands directly against the digits and
 * its character on that side is not itself a symbol or a space — a letter, as in `"CHF"` — a
 * no-break space separates them (`"CHF 5.00"`, but `"$5.00"` and `"CA$5.00"`).
 */
internal fun CurrencyPattern.fill(digits: String, negative: Boolean, minus: String, symbol: String): String {
    val template = if (negative) this.negative else positive
    return buildString {
        template.forEachIndexed { index, c ->
            when (c) {
                '¤' -> {
                    if (template.getOrNull(index - 1) == '#' && needsSpacing(symbol.firstOrNull())) append('\u00A0')
                    append(symbol)
                    if (template.getOrNull(index + 1) == '#' && needsSpacing(symbol.lastOrNull())) append('\u00A0')
                }
                '#' -> append(digits)
                '-' -> append(minus)
                else -> append(c)
            }
        }
    }
}

/** Whether a symbol whose character next to the digits is [edge] is spaced from them. */
private fun needsSpacing(edge: Char?): Boolean = edge != null && !edge.isWhitespace() && edge.category !in symbolCategories

private val symbolCategories = setOf(
    CharCategory.CURRENCY_SYMBOL,
    CharCategory.MATH_SYMBOL,
    CharCategory.MODIFIER_SYMBOL,
    CharCategory.OTHER_SYMBOL,
)

/**
 * Resolves where [locale] puts the symbol and sign of an amount in the currency [code]. Walks the
 * BCP 47 subtag chain ("de-CH" → "de") and ends at English; at each tag, a pattern CLDR gives
 * that currency in particular ([cldrCurrencyPatternOverrides]) comes before the locale's own.
 */
internal fun currencyPatternFor(locale: Locale, code: String): CurrencyPattern {
    var currentTag: String =
        if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    while (currentTag.isNotEmpty()) {
        cldrCurrencyPatternOverrides["$currentTag:$code"]?.let { return it }
        cldrCurrencyPatterns[currentTag]?.let { return it }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return englishCurrencyPattern
}

/** How [locale] writes the digits of an amount in [pattern]: its number style, with any separators CLDR gives money. */
private fun moneyStyleFor(numberStyle: NumberStyle, pattern: CurrencyPattern): NumberStyle =
    if (pattern.decimal == null && pattern.group == null && pattern.minimumGroupingDigits == null && pattern.indian == null) {
        numberStyle
    } else NumberStyle(
        decimal = pattern.decimal ?: numberStyle.decimal,
        grouping = pattern.group ?: numberStyle.grouping,
        minus = numberStyle.minus,
        plus = numberStyle.plus,
        percent = numberStyle.percent,
        minimumGroupingDigits = pattern.minimumGroupingDigits ?: numberStyle.minimumGroupingDigits,
        indian = pattern.indian ?: numberStyle.indian,
    )

/**
 * The symbols a locale shows for currencies, from [cldrCurrencySymbols]: each layer is one tag of
 * the locale's BCP 47 subtag chain, most specific first, ending at [englishCurrencySymbols]. Each
 * maps an ISO 4217 code to its standard and narrow symbol, either `null` where the tag shows its
 * parent's.
 */
internal class CurrencySymbols(private val layers: List<Map<String, Array<String?>>>) {

    /**
     * The symbol [currency] is written with in [form]: CLDR's for the locale, or the currency's own
     * [Currency.symbol] where CLDR has none.
     */
    fun of(currency: Currency, form: CurrencySymbol): String {
        val index = when (form) {
            CurrencySymbol.Standard -> 0
            CurrencySymbol.Narrow -> 1
            CurrencySymbol.Code -> return currency.code
        }
        for (layer in layers) layer[currency.code]?.get(index)?.let { return it }
        return currency.symbol
    }
}

/** Reads one tag's packed symbols, `"CODE|standard|narrow;…"`, with an empty form left `null`. */
private fun unpackSymbols(packed: String): Map<String, Array<String?>> =
    packed.split(';').associate { entry ->
        val (code, standard, narrow) = entry.split('|')
        code to arrayOf(standard.ifEmpty { null }, narrow.ifEmpty { null })
    }

private val englishSymbolLayer: Map<String, Array<String?>> by lazy {
    englishCurrencySymbols.mapValues { (_, symbols) ->
        val (standard, narrow) = symbols.split('|')
        arrayOf<String?>(standard, narrow)
    }
}

/** The symbols [locale] shows for currencies: its own, then its language's, then English. */
internal fun currencySymbolsFor(locale: Locale): CurrencySymbols {
    val layers = mutableListOf<Map<String, Array<String?>>>()
    var currentTag: String =
        if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    while (currentTag.isNotEmpty()) {
        cldrCurrencySymbols[currentTag]?.let { layers += unpackSymbols(it) }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    layers += englishSymbolLayer
    return CurrencySymbols(layers)
}

private data class MoneyFormatterKey(
    val tag: String,
    val showSymbol: Boolean,
    val symbol: CurrencySymbol,
    val separators: Separators,
)

@Volatile private var moneyFormatterCache = emptyMap<MoneyFormatterKey, MoneyFormatter>()

/**
 * Retrieves the [MoneyFormatter] for a given [Locale], building and caching it on first use.
 *
 * The separators, the minus sign, the currency symbol, and where the symbol and sign go are the
 * ones Unicode CLDR gives the locale: `"$1,234.56"` and `"-$1,234.56"` in English, `"1 234,56 €"`
 * in French, `"€ -1.234,56"` in Dutch, `"CHF-1'234.56"` in Swiss German. Every space inside an
 * amount is a no-break space, so it never breaks across a line, unless [separators] is
 * [Separators.Ascii].
 *
 * The symbol is CLDR's standard symbol for the currency in the locale unless [symbol] says
 * otherwise, so a dollar that is not the locale's own is marked: `"CA$5.00"` in US English,
 * `"US$5.00"` in Canadian English. A currency CLDR has no symbol for is written with its own
 * [Currency.symbol]; the `symbol` of a currency CLDR does name is not used.
 *
 * @param locale The locale dictating separators, the symbol and its placement.
 * @param showSymbol If true, the currency symbol is included.
 * @param symbol Which symbol to write: CLDR's standard symbol (the default), its narrow symbol, or
 *   the ISO code. See [CurrencySymbol]. Ignored when [showSymbol] is false.
 * @param separators Whether spaces and signs are the typographic ones CLDR uses (the default), or
 *   plain ASCII.
 */
fun moneyFormatterFor(
    locale: Locale,
    showSymbol: Boolean = true,
    symbol: CurrencySymbol = CurrencySymbol.Standard,
    separators: Separators = Separators.Typographic,
): MoneyFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    val cacheKey = MoneyFormatterKey(fullTag, showSymbol, symbol, separators)

    moneyFormatterCache[cacheKey]?.let { return it }

    val numberStyle = numberStyleFor(locale)
    val minus = numberStyle.minus
    val symbols = currencySymbolsFor(locale)

    val formatter: MoneyFormatter = { money ->
        val currency = money.currency
        val digits = currency.digits
        val pattern = currencyPatternFor(locale, currency.code)
        val moneyStyle = moneyStyleFor(numberStyle, pattern)
        // Rounded and rendered from the decimal amount itself, never through a Double: every
        // digit stays exact and the fraction keeps the currency's full minor-unit width.
        val amount = money.value.setScale(digits, RoundingMode.HALF_EVEN)
        val numberStr = formatDecimalText(amount.toString(), digits, moneyStyle, fixedFraction = true)

        val text = if (showSymbol) {
            // The number text carries the locale's minus sign only when the rounded amount is
            // below zero, so an amount that rounds to zero takes the positive layout.
            val negative = numberStr.startsWith(minus)
            pattern.fill(numberStr.removePrefix(minus), negative, minus, symbols.of(currency, symbol))
        } else {
            numberStr
        }
        if (separators == Separators.Ascii) text.toAscii() else text
    }

    val oldCache = moneyFormatterCache
    if (!oldCache.containsKey(cacheKey)) {
        val newCache = if (oldCache.size >= 150) {
            mapOf(cacheKey to formatter)
        } else {
            oldCache + (cacheKey to formatter)
        }
        moneyFormatterCache = newCache
    }
    return formatter
}

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun moneyFormatterFor(locale: Locale, showSymbol: Boolean = true): MoneyFormatter =
    moneyFormatterFor(locale, showSymbol, CurrencySymbol.Standard, Separators.Typographic)
