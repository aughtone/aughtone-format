package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.Month
import kotlinx.datetime.number

/**
 * The localized name of this month, in the given [width].
 *
 * Returns just the name — `"March"` / `"Mar"` in English — not a formatted
 * date. It reads the same bundled per-locale name tables that [format] uses, so
 * a month name here matches the month shown by a full date rendered for the
 * same [locale].
 *
 * The [locale] is resolved with the same BCP-47 fallback as the rest of the
 * module: a region variant falls back to its language (`en-CA` → `en`), and a
 * language with no bundled table falls back to English. It never throws for an
 * unsupported locale; it renders the English name instead.
 *
 * ```kotlin
 * Month.MARCH.displayName(locale = localeFor("en")!!)                     // "March"
 * Month.MARCH.displayName(TextWidth.Abbreviated, localeFor("en")!!)        // "Mar"
 * ```
 *
 * @param width whether to return the full or abbreviated name. Defaults to
 *   [TextWidth.Full]. Only these two widths are available — see [TextWidth].
 * @param locale the locale whose names to use. Defaults to [Locale.current].
 * @return the month's localized name in [width], falling back to English when
 *   the locale is not bundled.
 * @see TextWidth
 * @see DayOfWeek.displayName
 */
fun Month.displayName(
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String {
    val resource = Resources.getMonthNamesResource(locale)
    val names = when (width) {
        TextWidth.Full -> resource.full
        TextWidth.Abbreviated -> resource.abbreviated
    }
    // MonthNames.names is January-first; Month.number is 1..12.
    return names.names[number - 1]
}
