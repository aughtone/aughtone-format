package io.github.aughtone.identifiers

/**
 * Renders this MAC address in the given display [notation].
 *
 * The receiver may be written in any of the notations this function produces — colon
 * (`00:00:5e:00:53:01`), IEEE hyphenated (`00-00-5E-00-53-01`), Cisco dotted
 * (`0000.5e00.5301`) or bare (`00005e005301`) — in upper or lower case. Every one of
 * those is the same 48-bit value, so the result is always [notation]'s own spelling, with its
 * own letter case, whatever the input looked like. This is a lossless display transform.
 *
 * Anything that cannot be read as one of those notations is treated as a programming error (see
 * the `@throws`): this does not search messy text for an address, or accept separators other
 * than the notation's own.
 *
 * Example:
 * ```kotlin
 * val mac = "00:00:5e:00:53:01"
 * mac.formatMac()                           // "00:00:5e:00:53:01"  (default: Colon)
 * mac.formatMac(MacNotation.Ieee)           // "00-00-5E-00-53-01"
 * mac.formatMac(MacNotation.CiscoDotted)    // "0000.5e00.5301"
 * mac.formatMac(MacNotation.Bare)           // "00005e005301"
 * mac.formatMac(MacNotation.UppercaseColon) // "00:00:5E:00:53:01"
 * "00-00-5E-00-53-01".formatMac()           // "00:00:5e:00:53:01"
 * ```
 *
 * @param notation the display form to produce. Defaults to [MacNotation.Colon], the
 *   canonical form.
 * @return the same address re-rendered in [notation].
 * @throws IllegalArgumentException if the receiver is not a MAC address in one of the
 *   notations above — for example `"00:00:5e:00:53"` (five octets), `"00:00:5e:00:53:0g"`
 *   (`g` is not hex), or `"00-00:5e-00:53-01"` (mixed separators). Only ASCII hex digits count:
 *   fullwidth and other-script digits are rejected.
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
 * Reads [value] in any of the [MacNotation] spellings and returns its twelve lower-case
 * hexadecimal digits, ready for regrouping by [formatMac].
 *
 * @param value six colon- or hyphen-separated hex pairs, three dot-separated groups of four, or
 *   twelve hex digits with no separator, in either case.
 * @return the twelve lower-case hex digits, separators stripped.
 * @throws IllegalArgumentException if [value] is none of those.
 */
private fun canonicalMacHex(value: String): String {
    val groups = when {
        ':' in value -> value.split(':').takeIf { it.size == 6 && it.all { g -> g.length == 2 } }
        '-' in value -> value.split('-').takeIf { it.size == 6 && it.all { g -> g.length == 2 } }
        '.' in value -> value.split('.').takeIf { it.size == 3 && it.all { g -> g.length == 4 } }
        else -> listOf(value).takeIf { value.length == 12 }
    }
    require(groups != null && groups.all { group -> group.all { it.isAsciiHexDigit() } }) {
        "Not a MAC address (expected colon, hyphen, dotted or bare hex notation): \"$value\""
    }
    return groups.joinToString("") { it.lowercase() }
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
