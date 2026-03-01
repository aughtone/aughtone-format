package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.values.EraNames
import io.github.aughtone.datetime.format.resources.values.EraNamesResource

internal data class EraNamesData(
    override val full: EraNames,
    override val abbreviated: EraNames
) : EraNamesResource

internal val localeEraNamesSource by lazy {
    mapOf(
        "ar" to lazy { EraNamesData(
            full = EraNames(bce = "قبل الحقبة العامة", ce = "الحقبة العامة"),
            abbreviated = EraNames(bce = "ق.ح.ع.", ce = "ح.ع.")
        ) },
        "cs" to lazy { EraNamesData(
            full = EraNames(bce = "Před naším letopočtem", ce = "Náš letopočet"),
            abbreviated = EraNames(bce = "př. n. l.", ce = "n. l.")
        ) },
        "de" to lazy { EraNamesData(
            full = EraNames(bce = "Vor der Zeitrechnung", ce = "Zeitrechnung"),
            abbreviated = EraNames(bce = "v. Z.", ce = "Z.")
        ) },
        "en" to lazy { EraNamesData(
            full = EraNames(bce = "Before Common Era", ce = "Common Era"),
            abbreviated = EraNames(bce = "BCE", ce = "CE")
        ) },
        "es" to lazy { EraNamesData(
            full = EraNames(bce = "Antes de la era común", ce = "Era común"),
            abbreviated = EraNames(bce = "a. e. c.", ce = "e. c.")
        ) },
        "fi" to lazy { EraNamesData(
            full = EraNames(bce = "Ennen ajanlaskun alkua", ce = "Ajanlaskun alun jälkeen"),
            abbreviated = EraNames(bce = "e.a.a.", ce = "j.a.a.")
        ) },
        "fr" to lazy { EraNamesData(
            full = EraNames(bce = "Avant l'ère commune", ce = "Ère commune"),
            abbreviated = EraNames(bce = "A.E.C.", ce = "E.C.")
        ) },
        "id" to lazy { EraNamesData(
            full = EraNames(bce = "Sebelum Masehi", ce = "Masehi"),
            abbreviated = EraNames(bce = "SM", ce = "M")
        ) },
        "it" to lazy { EraNamesData(
            full = EraNames(bce = "Avanti Era Comune", ce = "Era Comune"),
            abbreviated = EraNames(bce = "A.E.C.", ce = "E.C.")
        ) },
        "iu" to lazy { EraNamesData(
            full = EraNames(bce = "ᓯᕗᓂᐊᓂ ᐅᓪᓗᕆᔭᐅᓂᖏᑕ", ce = "ᐅᓪᓗᕆᔭᐅᓂᖏᑕ ᑭᖑᓂᐊᓂ"),
            abbreviated = EraNames(bce = "ᓯᕗ", ce = "ᑭᖑ")
        ) },
        "ja" to lazy { EraNamesData(
            full = EraNames(bce = "紀元前", ce = "西暦"),
            abbreviated = EraNames(bce = "紀元前", ce = "西暦")
        ) },
        "kk" to lazy { EraNamesData(
            full = EraNames(bce = "Біздің заманымызға дейін", ce = "Біздің заманымыз"),
            abbreviated = EraNames(bce = "б.з.д.", ce = "б.з.")
        ) },
        "ko" to lazy { EraNamesData(
            full = EraNames(bce = "기원전", ce = "서기"),
            abbreviated = EraNames(bce = "기원전", ce = "서기")
        ) },
        "nl" to lazy { EraNamesData(
            full = EraNames(bce = "Voor de gewone jaartelling", ce = "Gewone jaartelling"),
            abbreviated = EraNames(bce = "v.g.j.", ce = "g.j.")
        ) },
        "pl" to lazy { EraNamesData(
            full = EraNames(bce = "Przed naszą erą", ce = "Nasza era"),
            abbreviated = EraNames(bce = "p.n.e.", ce = "n.e.")
        ) },
        "pt" to lazy { EraNamesData(
            full = EraNames(bce = "Antes da Era Comum", ce = "Era Comum"),
            abbreviated = EraNames(bce = "A.E.C.", ce = "E.C.")
        ) },
        "ru" to lazy { EraNamesData(
            full = EraNames(bce = "До нашей эры", ce = "Нашей эры"),
            abbreviated = EraNames(bce = "до н. э.", ce = "н. э.")
        ) },
        "tr" to lazy { EraNamesData(
            full = EraNames(bce = "Milattan Önce", ce = "Milattan Sonra"),
            abbreviated = EraNames(bce = "MÖ", ce = "MS")
        ) },
        "uk" to lazy { EraNamesData(
            full = EraNames(bce = "До нашої ери", ce = "Нашої ери"),
            abbreviated = EraNames(bce = "до н. э.", ce = "н. э.")
        ) },
        "uz" to lazy { EraNamesData(
            full = EraNames(bce = "Miloddan avval", ce = "Milodiy"),
            abbreviated = EraNames(bce = "m.a.", ce = "m.")
        ) },
        "vi" to lazy { EraNamesData(
            full = EraNames(bce = "Trước Công Nguyên", ce = "Công Nguyên"),
            abbreviated = EraNames(bce = "TCN", ce = "CN")
        ) },
        "zh" to lazy { EraNamesData(
            full = EraNames(bce = "公元前", ce = "公元"),
            abbreviated = EraNames(bce = "公元前", ce = "公元")
        ) }
    )
}
