package io.github.aughtone.datetime.format.resources.strings.monthnames


object MonthNamesResourceMap {

    val numbers: MonthNamesResource by lazy { MonthNames0numbers }
    val default: MonthNamesResource by lazy { MonthNames0en }
    val en: MonthNamesResource by lazy { MonthNames0en }
    val es: MonthNamesResource by lazy { MonthNames0es }
    val fr: MonthNamesResource by lazy { MonthNames0fr }
    val uk: MonthNamesResource by lazy { MonthNames0uk }

    val map: Map<String, MonthNamesResource> = mapOf(
        "" to default,
        "#" to numbers,
        "en" to en,
        "es" to es,
        "fr" to fr,
        "uk" to uk
    )
}
