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
        ) },
        "af" to lazy { EraNamesData(
            full = EraNames(bce = "voor Christus", ce = "ná Christus"),
            abbreviated = EraNames(bce = "v.C.", ce = "n.C.")
        ) },
        "az" to lazy { EraNamesData(
            full = EraNames(bce = "eramızdan əvvəl", ce = "yeni era"),
            abbreviated = EraNames(bce = "e.ə.", ce = "y.e.")
        ) },
        "be" to lazy { EraNamesData(
            full = EraNames(bce = "да нараджэння Хрыстова", ce = "ад нараджэння Хрыстова"),
            abbreviated = EraNames(bce = "да н.э.", ce = "н.э.")
        ) },
        "bg" to lazy { EraNamesData(
            full = EraNames(bce = "преди Христа", ce = "след Христа"),
            abbreviated = EraNames(bce = "пр.Хр.", ce = "сл.Хр.")
        ) },
        "ca" to lazy { EraNamesData(
            full = EraNames(bce = "abans de Crist", ce = "després de Crist"),
            abbreviated = EraNames(bce = "aC", ce = "dC")
        ) },
        "da" to lazy { EraNamesData(
            full = EraNames(bce = "før Kristus", ce = "efter Kristus"),
            abbreviated = EraNames(bce = "f.Kr.", ce = "e.Kr.")
        ) },
        "el" to lazy { EraNamesData(
            full = EraNames(bce = "προ Χριστού", ce = "μετά Χριστόν"),
            abbreviated = EraNames(bce = "π.Χ.", ce = "μ.Χ.")
        ) },
        "et" to lazy { EraNamesData(
            full = EraNames(bce = "enne Kristust", ce = "pärast Kristust"),
            abbreviated = EraNames(bce = "eKr", ce = "pKr")
        ) },
        "eu" to lazy { EraNamesData(
            full = EraNames(bce = "Kristo aurretik", ce = "Kristo ondoren"),
            abbreviated = EraNames(bce = "K.a.", ce = "K.o.")
        ) },
        "fa" to lazy { EraNamesData(
            full = EraNames(bce = "قبل از میلاد", ce = "میلادی"),
            abbreviated = EraNames(bce = "ق.م.", ce = "م.")
        ) },
        "gl" to lazy { EraNamesData(
            full = EraNames(bce = "antes de Cristo", ce = "despois de Cristo"),
            abbreviated = EraNames(bce = "a.C.", ce = "d.C.")
        ) },
        "he" to lazy { EraNamesData(
            full = EraNames(bce = "לפני הספירה", ce = "לספירה"),
            abbreviated = EraNames(bce = "לפנה״ס", ce = "לספירה")
        ) },
        "hi" to lazy { EraNamesData(
            full = EraNames(bce = "ईसा-पूर्व", ce = "ईसवी सन"),
            abbreviated = EraNames(bce = "ईसा-पूर्व", ce = "ईस्वी")
        ) },
        "hr" to lazy { EraNamesData(
            full = EraNames(bce = "prije Krista", ce = "poslije Krista"),
            abbreviated = EraNames(bce = "pr. Kr.", ce = "po. Kr.")
        ) },
        "hu" to lazy { EraNamesData(
            full = EraNames(bce = "Krisztus előtt", ce = "időszámításunk szerint"),
            abbreviated = EraNames(bce = "i. e.", ce = "i. sz.")
        ) },
        "hy" to lazy { EraNamesData(
            full = EraNames(bce = "Քրիստոսից առաջ", ce = "Քրիստոսից հետո"),
            abbreviated = EraNames(bce = "մ.թ.ա.", "մ.թ.")
        ) },
        "is" to lazy { EraNamesData(
            full = EraNames(bce = "fyrir Krist", ce = "eftir Krist"),
            abbreviated = EraNames(bce = "f.Kr.", ce = "e.Kr.")
        ) },
        "ka" to lazy { EraNamesData(
            full = EraNames(bce = "ძველი წელთაღრიცხვით", ce = "ახალი წელთაღრიცხვით"),
            abbreviated = EraNames(bce = "ძვ. წ.", ce = "ახ. წ.")
        ) },
        "lt" to lazy { EraNamesData(
            full = EraNames(bce = "prieš Kristų", ce = "po Kristaus"),
            abbreviated = EraNames(bce = "pr. Kr.", ce = "po Kr.")
        ) },
        "lv" to lazy { EraNamesData(
            full = EraNames(bce = "pirms mūsu ēras", ce = "mūsu ērā"),
            abbreviated = EraNames(bce = "p.m.ē.", ce = "m.ē.")
        ) },
        "mk" to lazy { EraNamesData(
            full = EraNames(bce = "пред нашата ера", ce = "од нашата ера"),
            abbreviated = EraNames(bce = "пр. н. е.", ce = "н. е.")
        ) },
        "ms" to lazy { EraNamesData(
            full = EraNames(bce = "S.M.", ce = "TM"),
            abbreviated = EraNames(bce = "S.M.", ce = "TM")
        ) },
        "nb" to lazy { EraNamesData(
            full = EraNames(bce = "før Kristus", ce = "etter Kristus"),
            abbreviated = EraNames(bce = "f.Kr.", ce = "e.Kr.")
        ) },
        "nn" to lazy { EraNamesData(
            full = EraNames(bce = "før Kristus", ce = "etter Kristus"),
            abbreviated = EraNames(bce = "f.Kr.", ce = "e.Kr.")
        ) },
        "no" to lazy { EraNamesData(
            full = EraNames(bce = "før Kristus", ce = "etter Kristus"),
            abbreviated = EraNames(bce = "f.Kr.", ce = "e.Kr.")
        ) },
        "ro" to lazy { EraNamesData(
            full = EraNames(bce = "înainte de Hristos", ce = "după Hristos"),
            abbreviated = EraNames(bce = "î.Hr.", ce = "d.Hr.")
        ) },
        "sk" to lazy { EraNamesData(
            full = EraNames(bce = "pred Kristom", ce = "po Kristovi"),
            abbreviated = EraNames(bce = "pred Kr.", ce = "po Kr.")
        ) },
        "sl" to lazy { EraNamesData(
            full = EraNames(bce = "pred Kristusom", ce = "po Kristusu"),
            abbreviated = EraNames(bce = "pr. Kr.", ce = "po Kr.")
        ) },
        "sq" to lazy { EraNamesData(
            full = EraNames(bce = "para Krishtit", ce = "mbas Krishtit"),
            abbreviated = EraNames(bce = "p.K.", ce = "mb.K.")
        ) },
        "sr" to lazy { EraNamesData(
            full = EraNames(bce = "пре нове ере", ce = "нове ере"),
            abbreviated = EraNames(bce = "п. н. е.", ce = "н. е.")
        ) },
        "sv" to lazy { EraNamesData(
            full = EraNames(bce = "före Kristus", ce = "efter Kristus"),
            abbreviated = EraNames(bce = "f.Kr.", ce = "e.Kr.")
        ) },
        "sw" to lazy { EraNamesData(
            full = EraNames(bce = "Kabla ya Kristo", ce = "Baada ya Kristo"),
            abbreviated = EraNames(bce = "KK", ce = "BK")
        ) },
        "th" to lazy { EraNamesData(
            full = EraNames(bce = "ปีก่อนคริสตกาล", ce = "คริสต์ศักราช"),
            abbreviated = EraNames(bce = "ก่อน ค.ศ.", ce = "ค.ศ.")
        ) },
    )
}
