package io.github.aughtone.identifiers

/**
 * A display notation for an IPv4 network in CIDR form.
 *
 * The network is the same range in every notation; a notation changes only how
 * the prefix length is expressed.
 *
 * @see formatIpNetwork
 */
enum class IpNetworkNotation {
    /**
     * Canonical CIDR form: the network address, a slash, and the prefix length.
     *
     * `192.0.2.0/24`
     */
    Cidr,

    /**
     * Address and dotted-decimal subnet mask, space-separated.
     *
     * `192.0.2.0 255.255.255.0`
     */
    Netmask,

    /**
     * The wildcard (inverse) mask — the bit-complement of the subnet mask, as
     * used in ACLs.
     *
     * `0.0.0.255`
     */
    Wildcard,

    /**
     * The address range the network spans, from its first (network) address to
     * its last (broadcast) address.
     *
     * `192.0.2.0 - 192.0.2.255`
     */
    Range,
}

/**
 * Renders this canonical IPv4 CIDR network in the given display [notation].
 *
 * The receiver must already be a **canonical** CIDR network — a canonical IPv4
 * address, a slash, and a prefix length in `0..32`, `192.0.2.0/24`. This is a
 * pure display transform; it does not parse or normalize arbitrary input.
 *
 * The [IpNetworkNotation.Range] bounds are computed by masking, so they are
 * correct even if the address carries host bits; the other notations echo the
 * address as given.
 *
 * Example:
 * ```kotlin
 * val net = "192.0.2.0/24"
 * net.formatIpNetwork()                          // "192.0.2.0/24"            (default: Cidr)
 * net.formatIpNetwork(IpNetworkNotation.Netmask)  // "192.0.2.0 255.255.255.0"
 * net.formatIpNetwork(IpNetworkNotation.Wildcard) // "0.0.0.255"
 * net.formatIpNetwork(IpNetworkNotation.Range)    // "192.0.2.0 - 192.0.2.255"
 * ```
 *
 * @param notation the display form to produce. Defaults to [IpNetworkNotation.Cidr].
 * @return the network rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a canonical IPv4 CIDR
 *   network — that is, not a canonical IPv4 address, a slash, and a prefix in
 *   `0..32`.
 * @see IpNetworkNotation
 */
fun String.formatIpNetwork(notation: IpNetworkNotation = IpNetworkNotation.Cidr): String {
    val slash = indexOf('/')
    require(slash > 0 && slash < length - 1) {
        "Not a canonical CIDR network (expected address/prefix): \"$this\""
    }
    val address = substring(0, slash)
    val prefixText = substring(slash + 1)
    val octets = ipv4Octets(address)
    val prefix = prefixText.toIntOrNull()
    require(prefix != null && prefix in 0..32 && prefixText == prefix.toString()) {
        "Not a canonical CIDR network (prefix must be 0..32): \"$this\""
    }

    val mask = netmaskOctets(prefix)
    return when (notation) {
        IpNetworkNotation.Cidr -> "${octets.joinToString(".")}/$prefix"
        IpNetworkNotation.Netmask -> "${octets.joinToString(".")} ${mask.joinToString(".")}"
        IpNetworkNotation.Wildcard -> IntArray(4) { 255 - mask[it] }.joinToString(".")
        IpNetworkNotation.Range -> {
            val first = IntArray(4) { octets[it] and mask[it] }
            val last = IntArray(4) { first[it] or (255 - mask[it]) }
            "${first.joinToString(".")} - ${last.joinToString(".")}"
        }
    }
}

/** The four octets of the subnet mask for a prefix length in `0..32`. */
private fun netmaskOctets(prefix: Int): IntArray {
    val mask = if (prefix == 0) 0L else (0xFFFFFFFFL shl (32 - prefix)) and 0xFFFFFFFFL
    return IntArray(4) { ((mask shr (24 - it * 8)) and 0xFF).toInt() }
}
