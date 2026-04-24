package io.github.aughtone.readable.relative

/**
 * Defines the style of relative time and duration formatting.
 */
enum class RelativeStyle {
    /** Detailed form, e.g., "5 days ago". */
    Long,
    /** Shorthand form, e.g., "5d ago". */
    Short,
    /** Disables the component of the relative calculation. */
    None
}
