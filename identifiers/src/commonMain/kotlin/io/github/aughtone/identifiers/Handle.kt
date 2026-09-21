package io.github.aughtone.identifiers

/**
 * Renders this username as an at-mention handle — the username prefixed with `@`.
 *
 * The transform is idempotent: a value that already begins with `@` is returned
 * unchanged, so applying it twice yields the same result.
 *
 * ```kotlin
 * "alice".formatHandle()   // "@alice"
 * "@alice".formatHandle()  // "@alice"  (already a handle)
 * ```
 *
 * @return the username with a single leading `@`.
 * @throws IllegalArgumentException if the receiver is blank — a handle needs a
 *   username to point at.
 */
fun String.formatHandle(): String {
    require(isNotBlank()) { "A handle requires a non-blank username." }
    return if (startsWith('@')) this else "@$this"
}
