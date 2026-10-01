package io.github.aughtone.datetime.format.resources.values

/**
 * Localized names for the two calendar eras, used when a date is rendered with
 * era text (for example a [io.github.aughtone.datetime.format.DateTimeStyle.Full]
 * date). Pass an instance to override the era words the module would otherwise
 * resolve for the locale.
 *
 * @property bce The name of the era before year 1 (BCE / BC in English).
 * @property ce The name of the current era (CE / AD in English).
 */
data class EraNames(
    val bce: String,
    val ce: String,
)
