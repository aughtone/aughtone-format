package io.github.aughtone.readable.geo

/**
 * Supported formats for human-readable coordinates.
 */
enum class CoordinateFormat {
    /**
     * Decimal Degrees (e.g., 40.7128° N, 74.0060° W)
     */
    DecimalDegrees,

    /**
     * Degrees, Minutes, and Seconds (e.g., 40° 42' 46" N, 74° 0' 21" W)
     */
    DegreesMinutesSeconds
}
