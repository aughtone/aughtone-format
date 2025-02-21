package io.github.aughtone.datetime.format.resources.strings.text


object TextResourceMap {
    val default: TextResource by lazy { TextResource0enUS }
    val enCA: TextResource by lazy { TextResource0enCA }
    val enUS: TextResource by lazy { TextResource0enUS }

    val map: Map<String, TextResource> = mapOf(
        "" to default,
        "en_CA" to enCA,
        "en_US" to enUS,
    )
}
