package io.github.aughtone.readable.number

/**
 * Which characters number formatting writes for its separators and signs.
 *
 * Unicode CLDR never puts an ordinary space inside a number: where a locale groups digits with a
 * space, or spaces a sign from its number, it is a no-break space (U+00A0) or a narrow no-break
 * space (U+202F), so a value such as `"1 234 567"` is never split across two lines. Some locales
 * also write negatives with the true minus sign (U+2212) rather than the hyphen-minus.
 *
 * Those characters are right for text a person reads, and are the default. They are wrong where
 * the text is compared or processed as bytes — a CSV column, a log line, a test fixture, a
 * terminal that renders U+202F badly — and there [Ascii] gives the same text with plain ASCII
 * separators and signs.
 */
enum class Separators {
    /** The characters Unicode CLDR uses: no-break spaces, the locale's own minus sign. The default. */
    Typographic,

    /**
     * Plain ASCII separators and signs: every no-break space is written as U+0020, the minus sign
     * U+2212 as `-`, and the Arabic percent sign U+066A as `%`. Digits and every other character
     * are unchanged.
     */
    Ascii,
}
