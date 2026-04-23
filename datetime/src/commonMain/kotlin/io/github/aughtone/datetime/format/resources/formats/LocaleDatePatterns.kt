package io.github.aughtone.datetime.format.resources.formats

data class DatePatterns(
    val short: String,
    val medium: String,
    val long: String,
    val full: String
)

internal val localeDatePatterns = mapOf(
    "US" to DatePatterns(
        short = "M/d/yyyy",
        medium = "MMM d, yyyy",
        long = "MMMM d, yyyy",
        full = "EEEE, MMMM d, yyyy"
    ),
    "CA" to DatePatterns(
        short = "yyyy-MM-dd",
        medium = "MMM d, yyyy",
        long = "MMMM d, yyyy",
        full = "EEEE, MMMM d, yyyy"
    ),
    "fr-CA" to DatePatterns(
        short = "yyyy-MM-dd",
        medium = "d MMM yyyy",
        long = "d MMMM yyyy",
        full = "EEEE d MMMM yyyy"
    )
)
