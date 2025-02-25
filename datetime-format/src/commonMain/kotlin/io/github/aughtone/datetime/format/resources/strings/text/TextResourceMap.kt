package io.github.aughtone.datetime.format.resources.strings.text


object TextResourceMap {
    val default: TextResource by lazy { TextResource0en() }
    val en : TextResource by lazy { TextResource0en() }
    val enCA: TextResource by lazy { TextResource0enCA() }
    val enUS: TextResource by lazy { TextResource0enUS() }
    val enGB: TextResource by lazy { TextResource0enGB() }
    val cs: TextResource by lazy { TextResource0cs() }

    val map: Map<String, TextResource> = mapOf(
        "" to default,
        "en" to en,
        "en_CA" to enCA,
        "en_US" to enUS,
        "en_GB" to enGB,
        "cs" to cs
    )
}
