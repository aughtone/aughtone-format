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
 * @return the username with a single leading `@`. Any `@` signs already at the front are
 *   collapsed into that one; an `@` inside the username is left alone.
 * @throws IllegalArgumentException if the receiver is blank, or is nothing but `@` signs — a
 *   handle needs a username to point at.
 */
fun String.formatHandle(): String {
    val username = trimStart('@')
    require(username.isNotBlank()) { "A handle requires a non-blank username." }
    return "@$username"
}
