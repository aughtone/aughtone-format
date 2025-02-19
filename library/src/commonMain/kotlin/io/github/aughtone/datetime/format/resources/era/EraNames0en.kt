package io.github.aughtone.datetime.format.resources.era

object EraNames0en : EraNamesResource {
    override val full: EraNames = EraNames(bce="Before Common Era", ce = "Common Era")
    override val abbreviated: EraNames = EraNames(bce="BCE", ce = "CE")
}
