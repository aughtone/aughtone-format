package io.github.aughtone.readable.list

import io.github.aughtone.types.locale.Locale

/**
 * Joins these already-formatted strings into a single locale-correct list —
 * "Monday, Wednesday, and Friday" in English, "lundi, mercredi et vendredi" in
 * French — using Unicode CLDR list patterns.
 *
 * This joins; it does not translate or reorder. Each element is emitted exactly
 * as given, in the order given; only the separators and the conjunction come
 * from [locale]. Deciding *which* strings go in the list, and in what order, is
 * the caller's job — as is any localization of the elements themselves (for a
 * weekday list, format each day with `DayOfWeek.displayName` first).
 *
 * The [locale] is resolved with the module's usual BCP-47 fallback, ending at
 * English. Coverage is a seeded set of ~55 locales; each covered locale carries
 * all three types — [ListType.And], [ListType.Or], and [ListType.Unit]. Any
 * locale outside the bundled set falls back to English. Only the wide forms
 * exist, so there is no width parameter yet; short and narrow forms arrive with
 * the CLDR data.
 *
 * Empty returns `""`; a single element returns that element unchanged.
 *
 * ```kotlin
 * listOf("Monday", "Wednesday", "Friday").formatReadableList(localeFor("en")!!)  // "Monday, Wednesday, and Friday"
 * listOf("lundi", "mercredi", "vendredi").formatReadableList(localeFor("fr")!!)  // "lundi, mercredi et vendredi"
 * listOf("red", "green").formatReadableList(localeFor("en")!!, ListType.Or)       // "red or green"
 * ```
 *
 * @param locale the locale whose list patterns to use. Defaults to [Locale.current].
 * @param type the kind of list — conjunctive [ListType.And] (default),
 *   disjunctive [ListType.Or], or [ListType.Unit]. See [ListType].
 * @return the elements joined per [locale] and [type].
 * @see ListType
 */
fun List<String>.formatReadableList(
    locale: Locale = Locale.current,
    type: ListType = ListType.And,
): String {
    if (isEmpty()) return ""
    if (size == 1) return this[0]

    val patterns = listPatternsFor(locale, type)
    if (size == 2) return patterns.two.fill(this[0], this[1])

    var accumulated = patterns.start.fill(this[0], this[1])
    for (index in 2..size - 2) {
        accumulated = patterns.middle.fill(accumulated, this[index])
    }
    return patterns.end.fill(accumulated, this[size - 1])
}

/** Fills a CLDR list template's `{0}` and `{1}` placeholders. */
private fun String.fill(first: String, second: String): String =
    replace("{0}", first).replace("{1}", second)
