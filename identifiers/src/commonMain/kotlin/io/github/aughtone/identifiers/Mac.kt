package io.github.aughtone.identifiers

/**
 * Display notations for a MAC address.
 *
 * @see formatMac
 */
enum class MacNotation {
    /** Canonical lower-case, colon-separated: `00:00:5e:00:53:01`. */
    Colon,

    /** IEEE style — upper-case, hyphen-separated: `00-00-5E-00-53-01`. */
    Ieee,

    /** Cisco style — three lower-case groups of four, dot-separated: `0000.5e00.5301`. */
    CiscoDotted,

    /** Bare lower-case hex, no separators: `00005e005301`. */
    Bare,

    /** Upper-case, colon-separated: `00:00:5E:00:53:01`. */
    UppercaseColon,
}

/**
 * Formats this canonical MAC address in the requested [notation].
 *
 * The receiver is expected to be an already-canonical MAC address — the
 * lower-case, colon-separated form `00:00:5e:00:53:01` a normalizer produces.
 * This is a pure display transform: it renders an alternative notation, it
 * does not parse, validate, or normalize arbitrary input.
 *
 * @param notation the display form to produce; defaults to [MacNotation.Colon].
 * @throws IllegalArgumentException if the receiver is not six colon-separated
 *   pairs of hexadecimal digits.
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

/** Extracts the 12 lower-case hex digits from a canonical colon-separated MAC, or throws. */
private fun canonicalMacHex(value: String): String {
    val octets = value.split(':')
    require(octets.size == 6 && octets.all { o -> o.length == 2 && o.all { it.digitToIntOrNull(16) != null } }) {
        "Not a canonical MAC address (expected six colon-separated hex pairs): \"$value\""
    }
    return octets.joinToString("") { it.lowercase() }
}
