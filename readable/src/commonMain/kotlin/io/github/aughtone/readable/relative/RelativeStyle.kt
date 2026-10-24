package io.github.aughtone.readable.relative

/**
 * Defines the style of relative time and duration formatting.
 */
enum class RelativeStyle {
    /** Detailed form, e.g., "5 days ago". */
    Long,
    /**
     * Shorthand form: the unit is abbreviated.
     *
     * English writes "5d" for the past and "in 5d" for the future, with no "ago", and "5d" for a
     * duration. Every other language takes Unicode CLDR's short form, which keeps the words that
     * say past or future: "vor 5 Min." and "in 5 Min." in German, "5 Min." for a duration.
     * Inuktitut, which CLDR has no data for, writes the same text as [Long].
     */
    Short,
    /** Disables the component of the relative calculation. */
    None
}
