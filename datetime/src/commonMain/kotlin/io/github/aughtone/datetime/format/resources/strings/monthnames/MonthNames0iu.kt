package io.github.aughtone.datetime.format.resources.strings.monthnames

import kotlinx.datetime.format.MonthNames

object MonthNames0iu : MonthNamesResource {
    override val full by lazy {
        MonthNames(
            january = "ᔮᓄᐊᓕ",
            february = "ᕖᕝᕗᐊᓕ",
            march = "ᒫᑦᓯ",
            april = "ᐄᐳᓗ",
            may = "ᒪᐃ",
            june = "ᔫᓂ",
            july = "ᔪᓚᐃ",
            august = "ᐋᒐᓯ",
            september = "ᓯᑎᐱᕆ",
            october = "ᐅᑐᐱᕆ",
            november = "ᓄᕕᐱᕆ",
            december = "ᑎᓯᐱᕆ"
        )
    }
    override val abbreviated by lazy {
        MonthNames(
            january = "ᔮᓄ",
            february = "ᕖᕝ",
            march = "ᒫᑦ",
            april = "ᐄᐳ",
            may = "ᒪᐃ",
            june = "ᔫᓂ",
            july = "ᔪᓚ",
            august = "ᐋᒐ",
            september = "ᓯᑎ",
            october = "ᐅᑐ",
            november = "ᓄᕕ",
            december = "ᑎᓯ"
        )
    }
}
