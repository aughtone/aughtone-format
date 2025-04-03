package io.github.aughtone.datetime.format.resources.strings.era

object EraNames0cs : EraNamesResource {
    override val full: EraNames = EraNames(
        bce = "Před naším letopočtem",
        ce = "Náš letopočet"
    )
    override val abbreviated: EraNames = EraNames(
        bce = "př. n. l.",
        ce = "n. l."
    )
}
