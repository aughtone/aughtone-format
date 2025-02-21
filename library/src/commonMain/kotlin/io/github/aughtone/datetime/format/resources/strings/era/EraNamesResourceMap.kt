package io.github.aughtone.datetime.format.resources.strings.era


object EraNamesResourceMap {
    val default: EraNamesResource by lazy { EraNames0en }
    val en: EraNamesResource by lazy { EraNames0en }

    val map: Map<String, EraNamesResource> = mapOf(
        "" to default,
        "en" to en,
    )
}
