package io.github.aughtone.datetime.format.resources.strings.monthnames


object MonthNamesResourceMap {

    val numbers: MonthNamesResource by lazy { MonthNames0numbers }
    val default: MonthNamesResource by lazy { MonthNames0en }
    val ar: MonthNamesResource by lazy { MonthNames0ar }
    val cs: MonthNamesResource by lazy { MonthNames0cs }
    val de: MonthNamesResource by lazy { MonthNames0de }
    val en: MonthNamesResource by lazy { MonthNames0en }
    val es: MonthNamesResource by lazy { MonthNames0es }
    val fi: MonthNamesResource by lazy { MonthNames0fi }
    val fr: MonthNamesResource by lazy { MonthNames0fr }
    val id: MonthNamesResource by lazy { MonthNames0id }
    val it: MonthNamesResource by lazy { MonthNames0it }
    val iu: MonthNamesResource by lazy { MonthNames0iu }
    val ja: MonthNamesResource by lazy { MonthNames0ja }
    val kk: MonthNamesResource by lazy { MonthNames0kk }
    val ko: MonthNamesResource by lazy { MonthNames0ko }
    val nl: MonthNamesResource by lazy { MonthNames0nl }
    val pl: MonthNamesResource by lazy { MonthNames0pl }
    val pt: MonthNamesResource by lazy { MonthNames0pt }
    val ru: MonthNamesResource by lazy { MonthNames0ru }
    val tr: MonthNamesResource by lazy { MonthNames0tr }
    val uk: MonthNamesResource by lazy { MonthNames0uk }
    val uz: MonthNamesResource by lazy { MonthNames0uz }
    val vi: MonthNamesResource by lazy { MonthNames0vi }
    val zh: MonthNamesResource by lazy { MonthNames0zh }

    val map: Map<String, MonthNamesResource> = mapOf(
        "" to default,
        "#" to numbers,
        "ar" to ar,
        "cs" to cs,
        "de" to de,
        "en" to en,
        "es" to es,
        "fi" to fi,
        "fr" to fr,
        "id" to id,
        "it" to it,
        "iu" to iu,
        "ja" to ja,
        "kk" to kk,
        "ko" to ko,
        "nl" to nl,
        "pl" to pl,
        "pt" to pt,
        "ru" to ru,
        "tr" to tr,
        "uk" to uk,
        "uz" to uz,
        "vi" to vi,
        "zh" to zh,
    )
}
