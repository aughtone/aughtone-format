package io.github.aughtone.datetime.format.resources.strings

import io.github.aughtone.datetime.format.resources.NamesResource

interface EraNamesResource: NamesResource<EraNames> {
    override val full: EraNames
    override val abbreviated: EraNames
}

data class EraNames(
    val bce: String,
    val ce: String,
)
