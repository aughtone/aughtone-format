package io.github.aughtone.datetime.format.resources.strings.era

import io.github.aughtone.datetime.format.resources.NamesResource

interface EraNamesResource:NamesResource<EraNames> {
    override val full: EraNames
    override val abbreviated: EraNames
}
