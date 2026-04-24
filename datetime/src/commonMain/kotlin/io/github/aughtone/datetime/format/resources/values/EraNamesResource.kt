package io.github.aughtone.datetime.format.resources.values

import io.github.aughtone.datetime.format.resources.NamesResource

interface EraNamesResource: NamesResource<EraNames> {
    override val full: EraNames
    override val abbreviated: EraNames
}
