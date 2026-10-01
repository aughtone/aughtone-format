package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber

/**
 * The localized name of this day of the week, in the given [width].
 *
 * Returns just the name — `"Monday"` / `"Mon"` in English, `"lundi"` / `"lun."`
 * in French — not a formatted date. It reads the same bundled per-locale name
 * tables that [format] uses, so a weekday name here matches the weekday shown by
 * a full date rendered for the same [locale].
 *
 * The [locale] is resolved with the same BCP-47 fallback as the rest of the
 * module: a region variant falls back to its language (`en-CA` → `en`), and a
 * language with no bundled table falls back to English. It never throws for an
 * unsupported locale; it renders the English name instead.
 *
 * ```kotlin
 * DayOfWeek.MONDAY.displayName(locale = localeFor("en")!!)                       // "Monday"
 * DayOfWeek.MONDAY.displayName(TextWidth.Abbreviated, localeFor("en")!!)          // "Mon"
 * DayOfWeek.MONDAY.displayName(TextWidth.Full, localeFor("fr")!!)                 // "lundi"
 * ```
 *
 * @param width whether to return the full or abbreviated name. Defaults to
 *   [TextWidth.Full]. Only these two widths are available — see [TextWidth].
 * @param locale the locale whose names to use. Defaults to [Locale.current].
 * @return the day's localized name in [width], falling back to English when the
 *   locale is not bundled.
 * @see TextWidth
 * @see Month.displayName
 */
fun DayOfWeek.displayName(
    width: TextWidth = TextWidth.Full,
    locale: Locale = Locale.current,
): String {
    val resource = Resources.getDayOfWeekNamesResource(locale)
    val names = when (width) {
        TextWidth.Full -> resource.full
        TextWidth.Abbreviated -> resource.abbreviated
    }
    // DayOfWeekNames.names is ISO order (Monday first); isoDayNumber is 1..7.
    return names.names[isoDayNumber - 1]
}
