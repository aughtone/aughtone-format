package io.github.aughtone.identifiers

/**
 * Renders this canonical MAC address in the given display [notation].
 *
 * The receiver must already be a **canonical** MAC address — the lower-case,
 * colon-separated form `00:00:5e:00:53:01` that a normalizer (or any equivalent
 * source) produces. This function is a pure, lossless display transform: it
 * regroups and re-cases the same 48 bits into another notation and returns the
 * result.
 *
 * It deliberately does **not** parse, validate, or normalize arbitrary input.
 * Accepting messy spellings, upper-case input, or alternative separators is a
 * normalization concern and belongs upstream; a formatter's contract is
 * "canonical value in, chosen notation out". Passing anything that is not
 * already canonical is treated as a programming error (see the `@throws`).
 *
 * Example:
 * ```kotlin
 * val mac = "00:00:5e:00:53:01"
 * mac.formatMac()                           // "00:00:5e:00:53:01"  (default: Colon)
 * mac.formatMac(MacNotation.Ieee)           // "00-00-5E-00-53-01"
 * mac.formatMac(MacNotation.CiscoDotted)    // "0000.5e00.5301"
 * mac.formatMac(MacNotation.Bare)           // "00005e005301"
 * mac.formatMac(MacNotation.UppercaseColon) // "00:00:5E:00:53:01"
 * ```
 *
 * @param notation the display form to produce. Defaults to [MacNotation.Colon],
 *   which returns the canonical input unchanged.
 * @return the same address re-rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a canonical MAC
 *   address — that is, not exactly six colon-separated pairs of hexadecimal
 *   digits. For example `"00-00-5e-00-53-01"` (wrong separator),
 *   `"00:00:5e:00:53"` (too few octets), and `"00:00:5e:00:53:0g"`
 *   (`g` is not hex) all fail.
 * @see MacNotation
 */
fun String.formatMac(notation: MacNotation = MacNotation.Colon): String {
    val hex = canonicalMacHex(this)
    return when (notation) {
        MacNotation.Colon -> hex.chunked(2).joinToString(":")
        MacNotation.UppercaseColon -> hex.uppercase().chunked(2).joinToString(":")
        MacNotation.Ieee -> hex.uppercase().chunked(2).joinToString("-")
        MacNotation.CiscoDotted -> hex.chunked(4).joinToString(".")
        MacNotation.Bare -> hex
    }
}

/**
 * Validates that [value] is a canonical colon-separated MAC address and returns
 * its twelve lower-case hexadecimal digits with the colons removed, ready for
 * regrouping by [formatMac].
 *
 * @param value the string to check — expected to be six colon-separated pairs
 *   of hexadecimal digits.
 * @return the twelve lower-case hex digits, separators stripped.
 * @throws IllegalArgumentException if [value] is not six colon-separated pairs
 *   of hexadecimal digits.
 */
private fun canonicalMacHex(value: String): String {
    val octets = value.split(':')
    require(octets.size == 6 && octets.all { o -> o.length == 2 && o.all { it.digitToIntOrNull(16) != null } }) {
        "Not a canonical MAC address (expected six colon-separated hex pairs): \"$value\""
    }
    return octets.joinToString("") { it.lowercase() }
}


/**
 * A display notation for a MAC (media access control) address.
 *
 * A MAC address is a single 48-bit value; a notation changes only how those
 * bits are grouped, separated, and cased for display — never the value itself.
 * Every notation is lossless and round-trips: formatting to any notation and
 * back to [Colon] reproduces the canonical input.
 *
 * [Colon] is the canonical form a normalizer emits and the default
 * [formatMac] produces. The others exist because different tools and vendors
 * expect different spellings.
 *
 * @see formatMac
 */
enum class MacNotation {
    /**
     * Canonical form: lower-case hex, a colon between each octet.
     *
     * `00:00:5e:00:53:01`
     */
    Colon,

    /**
     * IEEE 802 print format: upper-case hex, a hyphen between each octet.
     *
     * `00-00-5E-00-53-01`
     */
    Ieee,

    /**
     * Cisco "triple-quad" format: lower-case hex in three dot-separated groups
     * of four digits.
     *
     * `0000.5e00.5301`
     */
    CiscoDotted,

    /**
     * Bare form: twelve lower-case hex digits with no separators.
     *
     * `00005e005301`
     */
    Bare,

    /**
     * Upper-case variant of [Colon]: upper-case hex, colon-separated.
     *
     * `00:00:5E:00:53:01`
     */
    UppercaseColon,
}
