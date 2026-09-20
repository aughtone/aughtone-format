package io.github.aughtone.identifiers

/**
 * A display notation for an IBAN (International Bank Account Number).
 *
 * An IBAN is a single string; a notation changes only whether it is spaced for
 * reading — never the value itself. Both notations are lossless and round-trip.
 *
 * @see formatIban
 */
enum class IbanNotation {
    /**
     * Print form: the IBAN split into groups of four characters separated by
     * single spaces, as recommended by ISO 13616 for display on paper.
     *
     * `GB82 WEST 1234 5698 7654 32`
     */
    Grouped,

    /**
     * Compact/electronic form: the canonical IBAN with no separators — the form
     * used in data and on the wire.
     *
     * `GB82WEST12345698765432`
     */
    Compact,
}

/**
 * Renders this canonical IBAN in the given display [notation].
 *
 * The receiver must already be a **canonical** IBAN — the compact,
 * upper-case ISO 13616 form `GB82WEST12345698765432`: a two-letter country
 * code, two check digits, then the country's alphanumeric BBAN, 15–34
 * characters in all. This is a pure, lossless display transform; it does not
 * parse, validate the check digits, or normalize arbitrary input (spaced,
 * lower-case, or otherwise non-canonical strings are rejected).
 *
 * Example:
 * ```kotlin
 * val iban = "GB82WEST12345698765432"
 * iban.formatIban()                       // "GB82 WEST 1234 5698 7654 32"  (default: Grouped)
 * iban.formatIban(IbanNotation.Compact)   // "GB82WEST12345698765432"
 * ```
 *
 * @param notation the display form to produce. Defaults to [IbanNotation.Grouped].
 * @return the IBAN rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a canonical IBAN —
 *   that is, not two upper-case letters, two digits, then upper-case
 *   alphanumerics, totalling 15–34 characters.
 * @see IbanNotation
 */
fun String.formatIban(notation: IbanNotation = IbanNotation.Grouped): String {
    val iban = canonicalIban(this)
    return when (notation) {
        IbanNotation.Grouped -> iban.chunked(4).joinToString(" ")
        IbanNotation.Compact -> iban
    }
}

/**
 * Validates that [value] is a canonical IBAN and returns it unchanged.
 *
 * @param value the string to check — expected to be a compact, upper-case IBAN.
 * @return [value], unchanged, once validated.
 * @throws IllegalArgumentException if [value] is not a canonical IBAN.
 */
private fun canonicalIban(value: String): String {
    val ok = value.length in 15..34 &&
        value[0] in 'A'..'Z' && value[1] in 'A'..'Z' &&
        value[2] in '0'..'9' && value[3] in '0'..'9' &&
        value.all { it in '0'..'9' || it in 'A'..'Z' }
    require(ok) {
        "Not a canonical IBAN (two letters, two check digits, then upper-case alphanumerics, 15–34 chars): \"$value\""
    }
    return value
}
