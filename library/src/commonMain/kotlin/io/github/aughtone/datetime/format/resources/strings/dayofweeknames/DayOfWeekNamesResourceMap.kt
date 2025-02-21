package io.github.aughtone.datetime.format.resources.strings.dayofweeknames


object DayOfWeekNamesResourceMap {

    val numbers: DayOfWeekNamesResource by lazy { DayOfWeekNames0numbers }
    val default: DayOfWeekNamesResource by lazy { DayOfWeekNames0en }
    val en: DayOfWeekNamesResource by lazy { DayOfWeekNames0en }
    val es: DayOfWeekNamesResource by lazy { DayOfWeekNames0es }
    val fr: DayOfWeekNamesResource by lazy { DayOfWeekNames0fr }

    val map: Map<String, DayOfWeekNamesResource> = mapOf(
        "" to default,
        "#" to numbers,
        "en" to en,
        "es" to es,
        "fr" to fr,
    )
}
