package io.github.aughtone.readable.relative

/**
 * Defines the style of relative time and duration formatting.
 */
enum class RelativeStyle {
    /** Detailed form, e.g., "5 days ago". */
    Long,
    /**
     * Shorthand form, e.g., "5d" for the past and "in 5d" for the future: the unit is abbreviated
     * and the past form carries no "ago".
     *
     * Only English has abbreviated unit words. Every other language renders the same text as
     * [Long] for relative time, and for durations keeps its full unit word.
     */
    Short,
    /** Disables the component of the relative calculation. */
    None
}
