package io.github.aughtone.identifiers

/**
 * A display notation for an IPv4 address.
 *
 * The address is the same 32-bit value in every notation; a notation changes
 * only how it is written.
 *
 * @see formatIpv4
 */
enum class Ipv4Notation {
    /**
     * Canonical dotted-decimal form: four decimal octets separated by dots.
     *
     * `192.0.2.1`
     */
    Dotted,

    /**
     * Reverse-DNS (`in-addr.arpa`) form: the octets in reverse order followed by
     * the `.in-addr.arpa` suffix — the name looked up for a PTR (reverse) record.
     *
     * `1.2.0.192.in-addr.arpa`
     */
    ReverseDns,

    /**
     * The address as a single unsigned 32-bit decimal integer (most significant
     * octet first).
     *
     * `3221225985`
     */
    Integer,
}

/**
 * Renders this canonical IPv4 address in the given display [notation].
 *
 * The receiver must already be a **canonical** IPv4 address — dotted decimal
 * with four octets in `0..255` and no leading zeros, `192.0.2.1`. This is a pure
 * display transform; it does not parse or normalize arbitrary input (octets out
 * of range, leading zeros, or the wrong number of parts are rejected).
 *
 * Example:
 * ```kotlin
 * val ip = "192.0.2.1"
 * ip.formatIpv4()                        // "192.0.2.1"              (default: Dotted)
 * ip.formatIpv4(Ipv4Notation.ReverseDns) // "1.2.0.192.in-addr.arpa"
 * ip.formatIpv4(Ipv4Notation.Integer)    // "3221225985"
 * ```
 *
 * @param notation the display form to produce. Defaults to [Ipv4Notation.Dotted],
 *   which returns the canonical input unchanged.
 * @return the address rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a canonical IPv4
 *   address — that is, not four dot-separated decimal octets in `0..255`
 *   without leading zeros.
 * @see Ipv4Notation
 */
fun String.formatIpv4(notation: Ipv4Notation = Ipv4Notation.Dotted): String {
    val octets = ipv4Octets(this)
    return when (notation) {
        Ipv4Notation.Dotted -> octets.joinToString(".")
        Ipv4Notation.ReverseDns -> octets.reversed().joinToString(".") + ".in-addr.arpa"
        Ipv4Notation.Integer -> octets.fold(0L) { acc, o -> (acc shl 8) or o.toLong() }.toString()
    }
}

/**
 * Parses a canonical dotted-decimal IPv4 address into its four octets.
 *
 * Shared with the network formatter ([formatIpNetwork]); not part of the public
 * API.
 *
 * @param value the string to parse — expected to be four dot-separated decimal
 *   octets in `0..255` without leading zeros.
 * @return the four octets, each in `0..255`, most significant first.
 * @throws IllegalArgumentException if [value] is not a canonical IPv4 address.
 */
internal fun ipv4Octets(value: String): IntArray {
    val parts = value.split('.')
    require(parts.size == 4) {
        "Not a canonical IPv4 address (expected four dot-separated octets): \"$value\""
    }
    return IntArray(4) { i ->
        val n = parts[i].toIntOrNull()
        require(n != null && n in 0..255 && parts[i] == n.toString()) {
            "Not a canonical IPv4 address (octet out of range, non-numeric, or zero-padded): \"$value\""
        }
        n
    }
}
