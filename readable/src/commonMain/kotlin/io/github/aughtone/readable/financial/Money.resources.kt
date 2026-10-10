@file:Suppress("DEPRECATION")

package io.github.aughtone.readable.financial

import io.github.aughtone.readable.number.formatDecimalText
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
 * A rule that takes a formatted number string and a currency symbol, and combines them.
 */
internal typealias MoneyFormatRule = (String, String) -> String

/**
 * Builds the format rule for a specific BCP 47 language code.
 * Rules dictate whether the symbol is prefixed or suffixed, and whether there's a space.
 */
private fun buildFormatRule(code: String): MoneyFormatRule? = when (code) {
    // German- and Italian-speaking Switzerland put the symbol first, with a space ("CHF 1'234.56")
    "de-CH", "it-CH" ->
        { numStr, sym -> "$sym $numStr" }
    // Suffix with space (e.g. "1 234,56 €")
    "az", "be", "bg", "ca", "cs", "da", "de", "el", "es", "et", "eu", "fi", "fr", "gl", "hr", "hu", "hy", "is", "it", "ka", "kk", "lt", "lv", "mk", "nb", "nn", "no", "pl", "pt", "ro", "ru", "sk", "sl", "sq", "sr", "sv", "uk", "uz", "vi", "ar", "fa", "he" ->
        { numStr, sym -> "$numStr $sym" }
    // Prefix without space (e.g. "$1,234.56")
    "en", "af", "ja", "zh", "ko", "th", "hi", "id", "ms", "tr", "iu" -> ::prefixSymbol
    // Prefix with space (e.g. "€ 1.234,56")
    "nl", "sw" ->
        { numStr, sym -> "$sym $numStr" }
    // Null means continue fallback
    else -> null
}

/**
 * Places the symbol directly before the number. A negative amount keeps its sign in front of the
 * symbol ("-$123.00"), never between the symbol and the digits.
 */
private fun prefixSymbol(numStr: String, sym: String): String =
    if (numStr.startsWith('-')) "-$sym${numStr.substring(1)}" else "$sym$numStr"

@Volatile private var moneyRuleCache = emptyMap<String, MoneyFormatRule>()
@Volatile private var moneyFormatterCache = emptyMap<Pair<String, Boolean>, MoneyFormatter>()

/**
 * Looks up the correct symbol placement rule for a given locale using a BCP 47 subtag fallback chain.
 */
private fun moneyRuleFor(locale: Locale): MoneyFormatRule {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    moneyRuleCache[fullTag]?.let { return it }

    var currentTag = fullTag
    var rule: MoneyFormatRule? = null
    while (currentTag.isNotEmpty()) {
        rule = buildFormatRule(currentTag)
        if (rule != null) break
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    // Default to prefix no-space if completely unknown
    val resolved = rule ?: ::prefixSymbol

    val oldCache = moneyRuleCache
    if (!oldCache.containsKey(fullTag)) {
        val newCache = if (oldCache.size >= 150) {
            mapOf(fullTag to resolved)
        } else {
            oldCache + (fullTag to resolved)
        }
        moneyRuleCache = newCache
    }
    return resolved
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

    moneyFormatterCache[cacheKey]?.let { return it }

    val rule = moneyRuleFor(locale)

    val formatter: MoneyFormatter = { money ->
        val currency = money.currency
        val digits = currency.digits
        // Rounded and rendered from the decimal amount itself, never through a Double: every
        // digit stays exact and the fraction keeps the currency's full minor-unit width.
        val amount = money.value.setScale(digits, RoundingMode.HALF_EVEN)
        val numberStr = formatDecimalText(amount.toString(), locale, digits, fixedFraction = true)

        if (showSymbol) {
            rule(numberStr, currency.symbol)
        } else {
            numberStr
        }
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
