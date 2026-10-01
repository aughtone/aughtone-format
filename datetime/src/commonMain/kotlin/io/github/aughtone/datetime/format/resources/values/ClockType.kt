package io.github.aughtone.datetime.format.resources.values

/**
 * The hour cycle a locale's clock uses when rendering a time.
 */
enum class ClockType {
    /** 24-hour clock, hours 0–23. */
    C24Hour,

    /** 12-hour clock, hours 1–12 with an AM/PM marker. */
    C12Hour,

    /** 6-hour clock, as used by some older Thai and Italian conventions. */
    C6Hour,
}
