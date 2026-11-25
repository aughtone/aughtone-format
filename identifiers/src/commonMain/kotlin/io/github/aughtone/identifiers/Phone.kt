package io.github.aughtone.identifiers

import io.github.aughtone.phonenumber.PhoneNumberUtil

/**
 * A display notation for a phone number.
 *
 * @see formatPhone
 */
enum class PhoneNotation {
    /**
     * National form — how the number is written and dialled within its own
     * country, without the country calling code.
     *
     * `(650) 253-0000`
     */
    National,

    /**
     * International form — the country calling code and national number, grouped
     * for reading.
     *
     * `+1 650-253-0000`
     */
    International,

    /**
     * RFC 3966 `tel:` URI form, for a link or a machine. Always written with ASCII hyphens,
     * whatever [PhoneSeparators] asks for, because a `tel:` URI must be.
     *
     * `tel:+1-650-253-0000`
     */
    Rfc3966,
}

/**
 * The characters a phone number is grouped with in [PhoneNotation.National] and
 * [PhoneNotation.International]. [PhoneNotation.Rfc3966] is a URI and is never changed.
 *
 * @see formatPhone
 */
enum class PhoneSeparators {
    /**
     * Characters that keep the number on one line: a no-break space (U+00A0) where the grouping
     * has a space, and a non-breaking hyphen (U+2011) where it has a hyphen. Parentheses, the
     * leading `+` and the digits are unchanged. Use it for text a person reads — a paragraph, a
     * table cell, a chat bubble — where an ordinary space or hyphen lets the number wrap in the
     * middle. The default.
     *
     * `+1 650-253-0000` and `+33 1 23 45 67 89`, each written with U+00A0 and U+2011 and kept on
     * one line
     */
    Typographic,

    /**
     * The ordinary space (U+0020) and hyphen-minus (U+002D) the number's metadata gives, which a
     * line can break at. Use it where text is processed as bytes or compared with a literal: a
     * CSV column, a log line, a test fixture, a search index.
     *
     * `+1 650-253-0000`
     */
    Ascii,
}

/**
 * Renders this canonical E.164 phone number in the given display [notation], grouped with
 * [separators].
 *
 * The receiver is an E.164 number — a leading `+`, the country calling code, then the national
 * number, `+16502530000` — which may also be written with spaces, hyphens, dots or parentheses
 * (`+1 (650) 253-0000`). The result is always [notation]'s own spelling. Unlike the
 * other formatters in this module, phone display is not a pure transform:
 * national and international grouping depend on per-country metadata, which comes
 * from `aughtone-phonenumber`. (That is the module's one dependency; its metadata
 * is dead-code-eliminated for callers that never format a phone.)
 *
 * By default the grouping never lets the number break across a line: its spaces are no-break
 * spaces (U+00A0) and its hyphens non-breaking hyphens (U+2011), which read the same. Pass
 * [PhoneSeparators.Ascii] for the plain characters, where the text is processed or compared as
 * bytes. [PhoneNotation.Rfc3966] is a URI and keeps its ASCII hyphens either way.
 *
 * A number without its leading `+` and calling code cannot be read, because nothing says which
 * country it belongs to, and is rejected, as is a calling code the metadata does not know (see
 * the `@throws`).
 *
 * Example:
 * ```kotlin
 * val e164 = "+16502530000"
 * e164.formatPhone()                          // "+1 650-253-0000"      (with U+00A0 and U+2011)
 * e164.formatPhone(PhoneNotation.National)    // "(650) 253-0000"       (with U+00A0 and U+2011)
 * e164.formatPhone(separators = PhoneSeparators.Ascii)  // "+1 650-253-0000"
 * e164.formatPhone(PhoneNotation.Rfc3966)     // "tel:+1-650-253-0000"
 * ```
 *
 * @param notation the display form to produce. Defaults to [PhoneNotation.International].
 * @param separators the characters [PhoneNotation.National] and [PhoneNotation.International]
 *   group with. Defaults to [PhoneSeparators.Typographic], which never breaks across a line.
 * @return the number rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not an international phone number: no
 *   leading `+` and calling code, an unknown calling code, or text that is not a number.
 * @see PhoneNotation
 */
fun String.formatPhone(
    notation: PhoneNotation = PhoneNotation.International,
    separators: PhoneSeparators = PhoneSeparators.Typographic,
): String {
    val number = try {
        // The "+" carries the country code, so the default region is unused ("ZZ").
        PhoneNumberUtil.parse(this, "ZZ")
    } catch (e: PhoneNumberUtil.NumberParseException) {
        throw IllegalArgumentException("Not a valid E.164 phone number: \"$this\"", e)
    }
    val format = when (notation) {
        PhoneNotation.National -> PhoneNumberUtil.PhoneNumberFormat.NATIONAL
        PhoneNotation.International -> PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL
        PhoneNotation.Rfc3966 -> PhoneNumberUtil.PhoneNumberFormat.RFC3966
    }
    val text = PhoneNumberUtil.format(number, format)
    return if (notation == PhoneNotation.Rfc3966 || separators == PhoneSeparators.Ascii) {
        text
    } else {
        text.replace(' ', '\u00A0').replace('-', '\u2011')
    }
}

@Deprecated("Kept for binary compatibility.", level = DeprecationLevel.HIDDEN)
fun String.formatPhone(notation: PhoneNotation = PhoneNotation.International): String =
    formatPhone(notation, PhoneSeparators.Typographic)
