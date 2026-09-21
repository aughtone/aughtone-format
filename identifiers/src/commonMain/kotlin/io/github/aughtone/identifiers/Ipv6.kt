package io.github.aughtone.identifiers

/**
 * A display notation for an IPv6 address.
 *
 * The address is the same 128-bit value in every notation; a notation changes
 * only how its eight 16-bit groups are written.
 *
 * @see formatIpv6
 */
enum class Ipv6Notation {
    /**
     * Canonical RFC 5952 form: lower-case, leading zeros dropped, and the longest
     * run of all-zero groups collapsed to `::`.
     *
     * `2001:db8::1`
     */
    Compressed,

    /**
     * Fully expanded form: eight groups of four lower-case hex digits, no `::`.
     *
     * `2001:0db8:0000:0000:0000:0000:0000:0001`
     */
    Expanded,

    /**
     * Bracketed form for use as the host in a URL or authority component.
     *
     * `[2001:db8::1]`
     */
    UrlHost,

    /**
     * Reverse-DNS (`ip6.arpa`) form: all 32 nibbles in reverse order, each its own
     * dot-separated label, followed by `.ip6.arpa` — the name looked up for a PTR
     * (reverse) record.
     *
     * `1.0.0.…0.8.b.d.0.1.0.0.2.ip6.arpa`
     */
    ReverseNibbles,
}

/**
 * Renders this IPv6 address in the given display [notation].
 *
 * The receiver must be a valid IPv6 address in hex-group form — canonical
 * compressed (`2001:db8::1`) or fully expanded — with at most one `::`. Groups
 * are parsed case-insensitively; every notation but [Ipv6Notation.Expanded]
 * emits lower-case. Because the address is parsed into its eight groups first,
 * [Ipv6Notation.Compressed] returns the RFC 5952 canonical form regardless of how
 * the input was written.
 *
 * IPv4-embedded forms (a trailing dotted-quad, e.g. `::ffff:192.0.2.1`) are not
 * accepted by this slice.
 *
 * Example:
 * ```kotlin
 * val ip = "2001:db8::1"
 * ip.formatIpv6()                          // "2001:db8::1"                             (default: Compressed)
 * ip.formatIpv6(Ipv6Notation.Expanded)     // "2001:0db8:0000:0000:0000:0000:0000:0001"
 * ip.formatIpv6(Ipv6Notation.UrlHost)      // "[2001:db8::1]"
 * ip.formatIpv6(Ipv6Notation.ReverseNibbles) // "1.0.0.0.…0.0.2.ip6.arpa"
 * ```
 *
 * @param notation the display form to produce. Defaults to [Ipv6Notation.Compressed].
 * @return the address rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a valid hex-group IPv6
 *   address — for example more than one `::`, the wrong number of groups, a group
 *   longer than four hex digits, or an IPv4-embedded (dotted-quad) form.
 * @see Ipv6Notation
 */
fun String.formatIpv6(notation: Ipv6Notation = Ipv6Notation.Compressed): String {
    val groups = ipv6Groups(this)
    return when (notation) {
        Ipv6Notation.Compressed -> compressIpv6(groups)
        Ipv6Notation.Expanded -> groups.joinToString(":") { it.toString(16).padStart(4, '0') }
        Ipv6Notation.UrlHost -> "[" + compressIpv6(groups) + "]"
        Ipv6Notation.ReverseNibbles ->
            groups.joinToString("") { it.toString(16).padStart(4, '0') }
                .reversed()
                .toCharArray()
                .joinToString(".") + ".ip6.arpa"
    }
}

/** Parses an IPv6 address into its eight 16-bit groups, expanding a single `::`. */
private fun ipv6Groups(value: String): IntArray {
    require(value.isNotEmpty() && '.' !in value) {
        "Not a valid hex-group IPv6 address: \"$value\""
    }
    val groups: List<Int> = if ("::" in value) {
        val halves = value.split("::")
        require(halves.size == 2) { "Not a valid IPv6 address (more than one \"::\"): \"$value\"" }
        val left = if (halves[0].isEmpty()) emptyList() else halves[0].split(":")
        val right = if (halves[1].isEmpty()) emptyList() else halves[1].split(":")
        val missing = 8 - (left.size + right.size)
        require(missing >= 1) { "Not a valid IPv6 address (too many groups for \"::\"): \"$value\"" }
        (left + List(missing) { "0" } + right).map { parseHextet(it, value) }
    } else {
        val parts = value.split(":")
        require(parts.size == 8) { "Not a valid IPv6 address (expected eight groups): \"$value\"" }
        parts.map { parseHextet(it, value) }
    }
    return groups.toIntArray()
}

/** Parses one IPv6 group: 1–4 hex digits into a 16-bit value. */
private fun parseHextet(group: String, whole: String): Int {
    require(group.length in 1..4 && group.all { it.digitToIntOrNull(16) != null }) {
        "Not a valid IPv6 address (bad group \"$group\"): \"$whole\""
    }
    return group.toInt(16)
}

/**
 * Collapses the longest run (of length >= 2) of all-zero groups to `::`, per
 * RFC 5952. Ties resolve to the first run; a lone zero group is not compressed.
 */
private fun compressIpv6(groups: IntArray): String {
    var bestStart = -1
    var bestLen = 0
    var i = 0
    while (i < groups.size) {
        if (groups[i] == 0) {
            var j = i
            while (j < groups.size && groups[j] == 0) j++
            if (j - i > bestLen) {
                bestLen = j - i
                bestStart = i
            }
            i = j
        } else {
            i++
        }
    }
    if (bestLen < 2) return groups.joinToString(":") { it.toString(16) }
    val head = (0 until bestStart).joinToString(":") { groups[it].toString(16) }
    val tail = (bestStart + bestLen until groups.size).joinToString(":") { groups[it].toString(16) }
    return "$head::$tail"
}
