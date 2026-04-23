package io.github.aughtone.readable.financial

import io.github.aughtone.readable.number.numberFormatterFor
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale
import kotlin.math.pow

/**
 * A formatter specifically for rendering a [Money] value into a human-readable string.
 * It encapsulates both the number formatting (separators) and the currency symbol placement.
 */
typealias MoneyFormatter = (Money) -> String

/**
 * A rule that takes a formatted number string and a currency symbol, and combines them.
 */
internal typealias MoneyFormatRule = (String, String) -> String

/**
 * Builds the format rule for a specific BCP 47 language code.
 * Rules dictate whether the symbol is prefixed or suffixed, and whether there's a space.
 */
private fun buildFormatRule(code: String): MoneyFormatRule? = when (code) {
    // Suffix with space (e.g. "1 234,56 €")
    "fr", "de", "es", "it", "ru", "sv", "fi", "nl", "pl", "ro", "bg", "hr", "sk", "cs", "hu", "et", "lt", "lv", "af", "da", "is", "nn", "no", "nb" ->
        { numStr, sym -> "$numStr $sym" }
    // Prefix without space (e.g. "$1,234.56")
    "en", "ja", "zh", "ko", "th", "hi", "ar", "he", "id", "ms", "tr", "vi" ->
        { numStr, sym -> "$sym$numStr" }
    // Null means continue fallback
    else -> null
}

// ── Lazy caches ────────────────────────────────────────────────────────────────

private val moneyRuleCache = mutableMapOf<String, MoneyFormatRule>()
private val moneyFormatterCache = mutableMapOf<Pair<String, Boolean>, MoneyFormatter>()

/**
 * Looks up the correct symbol placement rule for a given locale using a BCP 47 subtag fallback chain.
 */
private fun moneyRuleFor(locale: Locale): MoneyFormatRule {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    return moneyRuleCache.getOrPut(fullTag) {
        var currentTag = fullTag
        var rule: MoneyFormatRule? = null
        while (currentTag.isNotEmpty()) {
            rule = buildFormatRule(currentTag)
            if (rule != null) break
            currentTag = currentTag.substringBeforeLast('-', "")
        }
        // Default to prefix no-space if completely unknown
        rule ?: { numStr, sym -> "$sym$numStr" }
    }
}

/**
 * Retrieves the [MoneyFormatter] for a given [Locale], building and caching it on first use.
 *
 * @param locale The locale dictating separators and symbol placement.
 * @param showSymbol If true, the currency symbol is included.
 */
fun moneyFormatterFor(locale: Locale, showSymbol: Boolean = true): MoneyFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    val cacheKey = fullTag to showSymbol

    return moneyFormatterCache.getOrPut(cacheKey) {
        val rule = moneyRuleFor(locale)
        // Internal cache for number formatters by precision, so we don't look them up on every money format call
        val numFormatterCache = mutableMapOf<Int, (Double) -> String>()

        val formatter: MoneyFormatter = { money ->
            val currency = money.currency
            val digits = currency?.digits ?: 2
            val value = money.cents.toDouble() / 10.0.pow(digits)
            val numFormatter = numFormatterCache.getOrPut(digits) { numberFormatterFor(locale, digits) }
            val numberStr = numFormatter(value)

            if (showSymbol && currency != null) {
                rule(numberStr, currency.symbol)
            } else {
                numberStr
            }
        }
        formatter
    }
}
