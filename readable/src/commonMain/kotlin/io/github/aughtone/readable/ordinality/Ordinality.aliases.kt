package io.github.aughtone.readable.ordinality

import io.github.aughtone.toolbox.Formatter

/**
 * A function that formats a long number into its ordinal string representation.
 */
typealias OrdinalFormatter = Formatter<Long>

/**
 * Factory for an [OrdinalFormatter] that appends a static suffix.
 */
fun suffixFormatter(suffix: String): OrdinalFormatter = { n -> "$n$suffix" }

/**
 * Factory for an [OrdinalFormatter] that prepends a static prefix.
 */
fun prefixFormatter(prefix: String): OrdinalFormatter = { n -> "$prefix$n" }
