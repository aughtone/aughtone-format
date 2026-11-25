package io.github.aughtone.toolbox

/**
 * Which characters a formatter writes for the spaces, separators and signs inside a value: the
 * typographic ones, which keep the value on one line, or plain ASCII ones, which a line can break
 * at and a byte comparison expects.
 *
 * Unicode CLDR never puts an ordinary space inside a number: where a locale groups digits with a
 * space, or spaces a sign or a word from its number, it is a no-break space (U+00A0) or a narrow
 * no-break space (U+202F), so `"1 234 567"` or `"1,5 Mio."` is never split across two lines. Some
 * locales also write negatives with the true minus sign (U+2212). Dates are different: CLDR writes
 * most of their spaces as ordinary ones ("Jun 1", "1 juin"), which a line can break at.
 *
 * Which is the default depends on the kind of value. Numbers, money and percentages default to
 * [Typographic], CLDR's own characters. Dates, times and ranges default to [Ascii], plain spaces,
 * and take [Typographic] where a date must not wrap — a table cell, a chat bubble, a label.
 */
enum class Separators {
    /**
     * The typographic characters, which never break inside a value: CLDR's no-break spaces and the
     * locale's own minus sign in a number, and a no-break space for every space inside a date or
     * time (U+0020 written as U+00A0, U+2009 as U+202F).
     */
    Typographic,

    /**
     * Plain ASCII separators and signs: every no-break space is written as U+0020, the minus sign
     * U+2212 as `-`, and the Arabic percent sign U+066A as `%`. Digits and every other character —
     * a month name, a range's dash — are unchanged.
     */
    Ascii,
}
