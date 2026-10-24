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
 * Renders this IBAN in the given display [notation].
 *
 * The receiver may be the compact electronic form (`GB82WEST12345698765432`) or the printed form
 * with spaces (`GB82 WEST 1234 5698 7654 32`), in upper or lower case. Both are the same IBAN,
 * so the result is always [notation]'s own spelling, in upper case as ISO 13616 writes it. This
 * is a display transform: it does not verify the check digits, or that the country's BBAN has
 * the right length.
 *
 * Example:
 * ```kotlin
 * val iban = "GB82WEST12345698765432"
 * iban.formatIban()                                  // "GB82 WEST 1234 5698 7654 32"  (default: Grouped)
 * iban.formatIban(IbanNotation.Compact)              // "GB82WEST12345698765432"
 * "gb82 west 1234 5698 7654 32".formatIban(IbanNotation.Compact) // "GB82WEST12345698765432"
 * ```
 *
 * @param notation the display form to produce. Defaults to [IbanNotation.Grouped].
 * @return the IBAN rendered in [notation].
 * @throws IllegalArgumentException if the receiver, without its spaces, is not an IBAN — that
 *   is, not two letters, two digits, then letters and digits, totalling 15–34 characters. Any
 *   separator other than a space, and any letter outside A–Z, is rejected.
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
 * Reads [value], compact or spaced and in either case, and returns the compact upper-case IBAN.
 *
 * @param value an IBAN, with or without spaces.
 * @return the compact, upper-case IBAN.
 * @throws IllegalArgumentException if [value] is not an IBAN.
 */
private fun canonicalIban(value: String): String {
    val iban = value.filterNot { it == ' ' }.uppercase()
    val ok = iban.length in 15..34 &&
        iban[0] in 'A'..'Z' && iban[1] in 'A'..'Z' &&
        iban[2] in '0'..'9' && iban[3] in '0'..'9' &&
        iban.all { it in '0'..'9' || it in 'A'..'Z' }
    require(ok) {
        "Not an IBAN (two letters, two check digits, then letters and digits, 15–34 characters): \"$value\""
    }
    return iban
}
