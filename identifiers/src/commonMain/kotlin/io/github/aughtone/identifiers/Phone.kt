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
     * RFC 3966 `tel:` URI form.
     *
     * `tel:+1-650-253-0000`
     */
    Rfc3966,
}

/**
 * Renders this canonical E.164 phone number in the given display [notation].
 *
 * The receiver must already be a **canonical** E.164 number — a leading `+`,
 * the country calling code, then the national number, `+16502530000`. Unlike the
 * other formatters in this module, phone display is not a pure transform:
 * national and international grouping depend on per-country metadata, which comes
 * from `aughtone-phonenumber`. (That is the module's one dependency; its metadata
 * is dead-code-eliminated for callers that never format a phone.)
 *
 * A country whose metadata is not bundled falls back to the national significant
 * number without grouping.
 *
 * Example:
 * ```kotlin
 * val e164 = "+16502530000"
 * e164.formatPhone()                          // "+1 650-253-0000"      (default: International)
 * e164.formatPhone(PhoneNotation.National)    // "(650) 253-0000"
 * e164.formatPhone(PhoneNotation.Rfc3966)     // "tel:+1-650-253-0000"
 * ```
 *
 * @param notation the display form to produce. Defaults to [PhoneNotation.International].
 * @return the number rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a parseable E.164
 *   phone number.
 * @see PhoneNotation
 */
fun String.formatPhone(notation: PhoneNotation = PhoneNotation.International): String {
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
    return PhoneNumberUtil.format(number, format)
}
