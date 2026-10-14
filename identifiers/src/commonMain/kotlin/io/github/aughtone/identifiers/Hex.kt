package io.github.aughtone.identifiers

/**
 * Whether this character is one of the ASCII hexadecimal digits `0-9`, `a-f`, `A-F`.
 *
 * `Char.digitToIntOrNull(16)` is not this test: it also accepts the digits of every other script
 * and the fullwidth Latin letters, none of which belong in a MAC address, a UUID or an IPv6 group.
 */
internal fun Char.isAsciiHexDigit(): Boolean = this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'
