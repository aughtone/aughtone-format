package io.github.aughtone.datetime.format.resources.options.clock


object ClockHoursResourceMap {

    val default: ClockHours by lazy { ClockHours0Default }
    val enCA: ClockHours by lazy { ClockHours0EnCA }
    val frCA: ClockHours by lazy { ClockHours0FrCA }

    val map: Map<String, ClockHours> = mapOf(
        "" to default,
        "en_CA" to enCA,
        "fr_CA" to frCA,
    )
}
