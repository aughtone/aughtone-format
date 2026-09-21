package io.github.aughtone.identifiers

/**
 * A display notation for a payment card number — the PAN (primary account
 * number).
 *
 * "PAN" is the payments/PCI term for the same value; the public entry point uses
 * the everyday name [String.formatCardNumber] so it is easy to discover.
 *
 * @see formatCardNumber
 */
enum class CardNotation {
    /**
     * Full number, digits split into the issuer's display groups separated by
     * single spaces. Most networks group in fours (`4111 1111 1111 1111`);
     * American Express (15 digits, IIN `34`/`37`) groups `4-6-5`
     * (`3782 822463 10005`). A length that is not a whole number of four-digit
     * groups keeps a shorter final group.
     *
     * This notation is lossless — every digit is shown.
     */
    Grouped,

    /**
     * Masked form: the last four digits only, behind a fixed bullet group.
     *
     * `•••• 1111`
     *
     * **Lossy by design.** All but the last four digits are discarded and the
     * original length is not preserved; use it only for display where the full
     * number must not appear, never as a value to store or parse back.
     */
    Masked,
}

/**
 * Renders this canonical card number (PAN) in the given display [notation].
 *
 * The receiver must already be a **canonical** PAN — the bare digits with no
 * separators, `4111111111111111`, 12–19 digits long. This is a pure display
 * transform; it does not parse, validate (no Luhn check), or normalize
 * arbitrary input (spaced or non-digit strings are rejected).
 *
 * Note that [CardNotation.Masked] is **lossy** — see its documentation.
 *
 * Example:
 * ```kotlin
 * "4111111111111111".formatCardNumber()                    // "4111 1111 1111 1111"  (default: Grouped)
 * "378282246310005".formatCardNumber()                     // "3782 822463 10005"    (Amex 4-6-5)
 * "4111111111111111".formatCardNumber(CardNotation.Masked) // "•••• 1111"
 * ```
 *
 * @param notation the display form to produce. Defaults to [CardNotation.Grouped].
 * @return the card number rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a canonical PAN —
 *   that is, not 12–19 decimal digits with no separators.
 * @see CardNotation
 */
fun String.formatCardNumber(notation: CardNotation = CardNotation.Grouped): String {
    val pan = canonicalPan(this)
    return when (notation) {
        CardNotation.Grouped -> groupPan(pan)
        CardNotation.Masked -> "•••• " + pan.takeLast(4)
    }
}

/** Splits a PAN into its issuer display groups (Amex `4-6-5`, otherwise fours). */
private fun groupPan(pan: String): String {
    val isAmex = pan.length == 15 && (pan.startsWith("34") || pan.startsWith("37"))
    return if (isAmex) {
        "${pan.substring(0, 4)} ${pan.substring(4, 10)} ${pan.substring(10, 15)}"
    } else {
        pan.chunked(4).joinToString(" ")
    }
}

/**
 * Validates that [value] is a canonical PAN and returns it unchanged.
 *
 * @param value the string to check — expected to be 12–19 decimal digits.
 * @return [value], unchanged, once validated.
 * @throws IllegalArgumentException if [value] is not 12–19 decimal digits.
 */
private fun canonicalPan(value: String): String {
    require(value.length in 12..19 && value.all { it in '0'..'9' }) {
        "Not a canonical card number (12–19 decimal digits): \"$value\""
    }
    return value
}
