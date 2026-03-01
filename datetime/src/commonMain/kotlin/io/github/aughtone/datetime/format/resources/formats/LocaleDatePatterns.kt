package io.github.aughtone.datetime.format.resources.formats

internal val localeDatePatterns = mapOf(
    "US" to DatePatterns(
        short = "M/d/yy",
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
