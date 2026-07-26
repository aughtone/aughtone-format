package io.github.aughtone.readable.relative

/**
 * Resolves the ambiguity of a date-less [kotlinx.datetime.LocalTime] delta across midnight.
 *
 * A [kotlinx.datetime.LocalTime] carries no date, so the distance between two clock readings can be
 * interpreted several ways once it crosses midnight. This tells the formatter which occurrence of the
 * time to measure against. Modelled on ICU's `RelativeDateTimeFormatter.Direction`
 * (`LAST`/`THIS`/`NEXT`), with [Nearest] added.
 *
 * For `now = 23:00`, `this = 01:00`:
 * - [Past] and [Present] → "22 hours ago"
 * - [Future] and [Nearest] → "in 2 hours"
 */
enum class RelativeDirection {
    /** Latest occurrence at or before `now` (backward wrap); result is in the past. */
    Past,

    /**
     * The occurrence on the same calendar day — plain `this - now`, no wrap. This was the behaviour
     * of relative [kotlinx.datetime.LocalTime] formatting prior to 3.1.0.
     */
    Present,

    /** Next occurrence at or after `now` (forward wrap); result is in the future. */
    Future,

    /** Nearest occurrence — the shortest signed distance on a 24-hour clock (at most ±12 hours). */
    Nearest
}
