package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

object DayOfWeekNames0iu : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "ᐅᓪᓗᓂ ᐊᑕᐅᓯᕐᒥᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᒪᕐᕉᖕᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᐱᖓᓱᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᓯᑕᒪᓂᐅᑕᐅᕙᑦᑐᓂ",
                "ᐅᓪᓗᓂ ᑕᓪᓕᒪᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᖄᕐᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᐅᓪᓗᖓᑕ ᐱᒋᐊᕐᕕᖓ"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "ᐅᓪᓗ", "ᒪᕐᕉ", "ᐱᖓ", "ᓯᑕ", "ᑕᓪᓕ", "ᖄᕐ", "ᐱᒋ"
            )
        )
    }
}

//object DayOfWeekNames0iu : DayOfWeekNamesResource {
//    override val full by lazy {
//        DayOfWeekNames(
//            listOf(
//                "Aippiqsirvia", "Sivuraqsirvia", "Pingasuksirvia", "Sisamakksirvia", "Tallimaksirvia", "Sitamaksirvia", "Aippiqsirvia Uvannga"
//            )
//        )
//    }
//    override val abbreviated by lazy {
//        DayOfWeekNames(
//            listOf(
//                "Aip", "Siv", "Pin", "Sis", "Tal", "Sit", "Apu"
//            )
//        )
//    }
//}
