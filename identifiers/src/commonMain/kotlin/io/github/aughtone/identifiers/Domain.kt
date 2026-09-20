package io.github.aughtone.identifiers

/**
 * A display notation for a domain name.
 *
 * @see formatDomain
 */
enum class DomainNotation {
    /**
     * Unicode (U-label) form: each `xn--` A-label is Punycode-decoded to its
     * Unicode form; other labels pass through unchanged.
     *
     * `xn--caf-dma.example` → `café.example`
     */
    Unicode,

    /**
     * ASCII (A-label) form: the domain unchanged, as given.
     *
     * `xn--caf-dma.example`
     */
    Ascii,
}

/**
 * Renders this canonical domain name in the given display [notation].
 *
 * The receiver must already be a **canonical**, ASCII-compatible domain — the
 * A-label form `xn--caf-dma.example` that a normalizer produces. Rendering the
 * Unicode form is a pure, table-free transform: each `xn--` label is
 * Punycode-decoded (RFC 3492) to its U-label and every other label is copied
 * through, so `xn--caf-dma.example` becomes `café.example`.
 *
 * Being table-free is deliberate. Full IDNA / UTS-46 validation and mapping
 * (which needs the Unicode tables) is a normalization concern and stays
 * upstream — as does any confusable or mixed-script check. This function only
 * decodes an already-validated domain for display; it does not validate the
 * result's script, bidi, or confusability.
 *
 * Example:
 * ```kotlin
 * val domain = "xn--caf-dma.example"
 * domain.formatDomain()                       // "café.example"          (default: Unicode)
 * domain.formatDomain(DomainNotation.Ascii)   // "xn--caf-dma.example"
 * "www.example.com".formatDomain()            // "www.example.com"       (no xn-- labels: unchanged)
 * ```
 *
 * @param notation the display form to produce. Defaults to [DomainNotation.Unicode].
 * @return the domain rendered in [notation].
 * @throws IllegalArgumentException if an `xn--` label does not contain valid
 *   Punycode.
 * @see DomainNotation
 */
fun String.formatDomain(notation: DomainNotation = DomainNotation.Unicode): String = when (notation) {
    DomainNotation.Ascii -> this
    DomainNotation.Unicode -> split('.').joinToString(".") { label ->
        if (label.startsWith("xn--", ignoreCase = true)) punycodeDecode(label.substring(4), label)
        else label
    }
}

// --- Punycode (RFC 3492) decode --------------------------------------------

private const val PUNY_BASE = 36
private const val PUNY_TMIN = 1
private const val PUNY_TMAX = 26
private const val PUNY_SKEW = 38
private const val PUNY_DAMP = 700
private const val PUNY_INITIAL_BIAS = 72
private const val PUNY_INITIAL_N = 0x80

/**
 * Decodes the Punycode body of an `xn--` label (RFC 3492) to its Unicode string.
 *
 * @param input the label content after the `xn--` prefix.
 * @param label the full label, for error messages.
 * @return the decoded Unicode label.
 * @throws IllegalArgumentException if [input] is not valid Punycode.
 */
private fun punycodeDecode(input: String, label: String): String {
    val output = mutableListOf<Int>()
    var n = PUNY_INITIAL_N
    var i = 0
    var bias = PUNY_INITIAL_BIAS

    val lastDelim = input.lastIndexOf('-')
    for (idx in 0 until maxOf(lastDelim, 0)) {
        val c = input[idx]
        require(c.code < 0x80) { "Invalid Punycode (non-ASCII before delimiter) in label \"$label\"" }
        output.add(c.code)
    }
    var pos = if (lastDelim >= 0) lastDelim + 1 else 0

    while (pos < input.length) {
        val oldI = i
        var w = 1
        var k = PUNY_BASE
        while (true) {
            require(pos < input.length) { "Invalid Punycode (truncated) in label \"$label\"" }
            val digit = punyDigit(input[pos])
            pos++
            require(digit < PUNY_BASE) { "Invalid Punycode (bad digit) in label \"$label\"" }
            require(digit <= (Int.MAX_VALUE - i) / w) { "Invalid Punycode (overflow) in label \"$label\"" }
            i += digit * w
            val t = when {
                k <= bias -> PUNY_TMIN
                k >= bias + PUNY_TMAX -> PUNY_TMAX
                else -> k - bias
            }
            if (digit < t) break
            require(w <= Int.MAX_VALUE / (PUNY_BASE - t)) { "Invalid Punycode (overflow) in label \"$label\"" }
            w *= (PUNY_BASE - t)
            k += PUNY_BASE
        }
        val out = output.size + 1
        bias = punyAdapt(i - oldI, out, oldI == 0)
        require(i / out <= Int.MAX_VALUE - n) { "Invalid Punycode (overflow) in label \"$label\"" }
        n += i / out
        i %= out
        require(n in 0..0x10FFFF && n !in 0xD800..0xDFFF) {
            "Invalid Punycode (decoded an invalid code point) in label \"$label\""
        }
        output.add(i, n)
        i++
    }
    return codePointsToString(output)
}

/** RFC 3492 bias adaptation. */
private fun punyAdapt(deltaIn: Int, numPoints: Int, firstTime: Boolean): Int {
    var delta = if (firstTime) deltaIn / PUNY_DAMP else deltaIn / 2
    delta += delta / numPoints
    var k = 0
    while (delta > ((PUNY_BASE - PUNY_TMIN) * PUNY_TMAX) / 2) {
        delta /= (PUNY_BASE - PUNY_TMIN)
        k += PUNY_BASE
    }
    return k + (((PUNY_BASE - PUNY_TMIN + 1) * delta) / (delta + PUNY_SKEW))
}

/** Value of a Punycode digit, or [PUNY_BASE] (invalid) for a non-digit. */
private fun punyDigit(c: Char): Int = when (c) {
    in 'a'..'z' -> c - 'a'
    in 'A'..'Z' -> c - 'A'
    in '0'..'9' -> c - '0' + 26
    else -> PUNY_BASE
}

/** Builds a string from Unicode code points, encoding supplementary ones as surrogate pairs. */
private fun codePointsToString(codePoints: List<Int>): String {
    val sb = StringBuilder(codePoints.size)
    for (cp in codePoints) {
        if (cp <= 0xFFFF) {
            sb.append(cp.toChar())
        } else {
            val c = cp - 0x10000
            sb.append((0xD800 + (c shr 10)).toChar())
            sb.append((0xDC00 + (c and 0x3FF)).toChar())
        }
    }
    return sb.toString()
}
