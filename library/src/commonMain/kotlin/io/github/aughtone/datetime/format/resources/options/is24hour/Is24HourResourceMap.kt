package io.github.aughtone.datetime.format.resources.options.is24hour

import io.github.aughtone.datetime.format.resources.strings.monthnames.MonthNamesResource


object Is24HourResourceMap {

    val default: Is24Hour by lazy { Is24Hour0default }
    val enCA: Is24Hour by lazy { Is24Hour0enCA }
    val frCA: Is24Hour by lazy { Is24Hour0frCA }

    val map: Map<String, Is24Hour> = mapOf(
        "" to default,
        "en_CA" to enCA,
        "fr_CA" to frCA,
    )
}
