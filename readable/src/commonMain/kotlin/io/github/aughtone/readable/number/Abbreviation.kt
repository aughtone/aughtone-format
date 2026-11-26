package io.github.aughtone.readable.number

/**
 * How `formatReadableAbbreviated` shortens a large number.
 */
enum class Abbreviation {
    /**
     * The locale's own short form for a count, from Unicode CLDR: `"1.5K"` and `"2.5M"` in English,
     * `"1,5 Mio."` in German, which writes thousands whole (`"1500"`), `"150万"` in Japanese and
     * Chinese, which count in ten-thousands, `"15 लाख"` in Hindi. The space between the number and
     * its word is a no-break space (U+00A0), so the two never wrap apart. The default; use it for
     * counts a person reads: followers, views, downloads, items.
     */
    Compact,

    /**
     * The SI prefixes, the same in every language: `"1.5k"`, `"2.5M"`, `"1.2G"`, up to `"Q"`
     * (10^30), with the digits written the locale's way (`"1,5k"` in German). Use it for a
     * quantity measured in a unit rather than counted, where an engineer reads `G` as giga.
     */
    Si,
}
