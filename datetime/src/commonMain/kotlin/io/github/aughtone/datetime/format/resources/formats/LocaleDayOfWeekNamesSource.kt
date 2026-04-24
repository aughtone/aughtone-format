package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.format.DayOfWeekNames

internal data class DayOfWeekNamesData(
    override val full: DayOfWeekNames,
    override val abbreviated: DayOfWeekNames
) : DayOfWeekNamesResource

internal val localeDayOfWeekNamesSource = mapOf(
    "ar" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت", "الأحد")),
        abbreviated = DayOfWeekNames(listOf("الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت", "الأحد"))
    ) },
    "cs" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Sobota", "Neděle")),
        abbreviated = DayOfWeekNames(listOf("Po", "Út", "St", "Čt", "Pá", "So", "Ne"))
    ) },
    "de" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag")),
        abbreviated = DayOfWeekNames(listOf("Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"))
    ) },
    "en" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames.ENGLISH_FULL,
        abbreviated = DayOfWeekNames.ENGLISH_ABBREVIATED
    ) },
    "es" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")),
        abbreviated = DayOfWeekNames(listOf("Lun.", "Mar.", "Mié.", "Jue.", "Vie.", "Sáb.", "Dom."))
    ) },
    "fi" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("maanantai", "tiistai", "keskiviikko", "torstai", "perjantai", "lauantai", "sunnuntai")),
        abbreviated = DayOfWeekNames(listOf("ma", "ti", "ke", "to", "pe", "la", "su"))
    ) },
    "fr" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche")),
        abbreviated = DayOfWeekNames(listOf("lun.", "mar.", "mer.", "jeu.", "ven.", "sam.", "dim."))
    ) },
    "id" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")),
        abbreviated = DayOfWeekNames(listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min"))
    ) },
    "it" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica")),
        abbreviated = DayOfWeekNames(listOf("Lun", "Mar", "Mer", "Gio", "Ven", "Sab", "Dom"))
    ) },
    "iu" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("ᐅᓪᓗᓂ ᐊᑕᐅᓯᕐᒥᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᒪᕐᕉᖕᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᐱᖓᓱᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᓯᑕᒪᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᑕᓪᓕᒪᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᖄᕐᓂᐅᑕᐅᕙᑦᑐᓂ", "ᐅᓪᓗᓂ ᐅᓪᓗᖓᑕ ᐱᒋᐊᕐᕕᖓ")),
        abbreviated = DayOfWeekNames(listOf("ᐅᓪᓗ", "ᒪᕐᕉ", "ᐱᖓ", "ᓯᑕ", "ᑕᓪᓕ", "ᖄᕐ", "ᐱᒋ"))
    ) },
    "ja" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日", "日曜日")),
        abbreviated = DayOfWeekNames(listOf("月", "火", "水", "木", "金", "土", "日"))
    ) },
    "kk" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Дүйсенбі", "Сейсенбі", "Сәрсенбі", "Бейсенбі", "Жұма", "Сенбі", "Жексенбі")),
        abbreviated = DayOfWeekNames(listOf("Дс", "Сс", "Ср", "Бс", "Жм", "Сн", "Жк"))
    ) },
    "ko" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일")),
        abbreviated = DayOfWeekNames(listOf("월", "화", "수", "목", "금", "토", "일"))
    ) },
    "nl" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("maandag", "dinsdag", "woensdag", "donderdag", "vrijdag", "zaterdag", "zondag")),
        abbreviated = DayOfWeekNames(listOf("ma", "di", "wo", "do", "vr", "za", "zo"))
    ) },
    "pl" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota","niedziela")),
        abbreviated = DayOfWeekNames(listOf("pon", "wt", "śr", "czw", "pt", "sob", "nd"))
    ) },
    "pt" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sábado", "domingo")),
        abbreviated = DayOfWeekNames(listOf("seg", "ter", "qua", "qui", "sex", "sáb", "dom"))
    ) },
    "ru" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("понедельник", "вторник", "среда", "четверг", "пятница", "суббота", "воскресенье")),
        abbreviated = DayOfWeekNames(listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс"))
    ) },
    "tr" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar")),
        abbreviated = DayOfWeekNames(listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cmt", "Paz"))
    ) },
    "uk" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("понеділок", "вівторок", "середа", "четвер", "п’ятниця", "субота","неділя")),
        abbreviated = DayOfWeekNames(listOf("пн", "вт", "ср", "чт", "пт", "сб", "нд"))
    ) },
    "uz" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Dushanba", "Seshanba", "Chorshanba", "Payshanba", "Juma", "Shanba", "Yakshanba")),
        abbreviated = DayOfWeekNames(listOf("Du", "Se", "Cho", "Pa", "Ju", "Sha", "Ya"))
    ) },
    "vi" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật")),
        abbreviated = DayOfWeekNames(listOf("T2", "T3", "T4", "T5", "T6", "T7", "CN"))
    ) },
    "sr" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("понедељак", "уторак", "среда", "четвρтак", "петак", "субота", "недеља")),
        abbreviated = DayOfWeekNames(listOf("пон", "ут", "ср", "чет", "пет", "суб", "нед"))
    ) },
    "sv" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag")),
        abbreviated = DayOfWeekNames(listOf("mån", "tis", "ons", "tors", "fre", "lör", "sön"))
    ) },
    "zh" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日")),
        abbreviated = DayOfWeekNames(listOf("一", "二", "三", "四", "五", "六", "日"))
    ) },
    "af" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrydag", "Saterdag", "Sondag")),
        abbreviated = DayOfWeekNames(listOf("Ma.", "Di.", "Wo.", "Do.", "Vr.", "Sa.", "So."))
    ) },
    "az" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("bazar ertəsi", "çərşənbə axşamı", "çərşənbə", "cümə axşamı", "cümə", "şənbə", "bazar")),
        abbreviated = DayOfWeekNames(listOf("B.e.", "Ç.a.", "Ç.", "C.a.", "C.", "Ş.", "B."))
    ) },
    "be" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("панядзелак", "аўторак", "серада", "чацвер", "пятніца", "субота", "нядзеля")),
        abbreviated = DayOfWeekNames(listOf("пн", "аў", "ср", "чц", "пт", "сб", "нд"))
    ) },
    "bg" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("понеделник", "вторник", "сряда", "четвъртък", "петък", "събота", "неделя")),
        abbreviated = DayOfWeekNames(listOf("пн", "вт", "ср", "чт", "пт", "сб", "нд"))
    ) },
    "ca" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("dilluns", "dimarts", "dimecres", "dijous", "divendres", "dissabte", "diumenge")),
        abbreviated = DayOfWeekNames(listOf("dl.", "dt.", "dc.", "dj.", "dv.", "ds.", "dg."))
    ) },
    "da" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("mandag", "tirsdag", "onsdag", "torsdag", "fredag", "lørdag", "søndag")),
        abbreviated = DayOfWeekNames(listOf("man.", "tirs.", "ons.", "tors.", "fre.", "lør.", "søn."))
    ) },
    "el" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο", "Κυριακή")),
        abbreviated = DayOfWeekNames(listOf("Δευ", "Τρί", "Τετ", "Πέμ", "Παρ", "Σάβ", "Κυρ"))
    ) },
    "et" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("esmaspäev", "teisipäev", "kolmapäev", "neljapäev", "reede", "laupäev", "pühapäev")),
        abbreviated = DayOfWeekNames(listOf("E", "T", "K", "N", "R", "L", "P"))
    ) },
    "eu" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("astelehena", "asteartea", "asteazkena", "osteguna", "ostirala", "larunbata", "igandea")),
        abbreviated = DayOfWeekNames(listOf("al.", "ar.", "az.", "og.", "or.", "lr.", "ig."))
    ) },
    "fa" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("دوشنبه", "سه‌شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه", "یکشنبه")),
        abbreviated = DayOfWeekNames(listOf("دوشنبه", "سه‌شنبه", "چهارشنبه", "پنجشنبه", "جمعه", "شنبه", "یکشنبه"))
    ) },
    "gl" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("luns", "martes", "mércores", "xoves", "venres", "sábado", "domingo")),
        abbreviated = DayOfWeekNames(listOf("luns", "mar.", "mér.", "xov.", "ven.", "sáb.", "dom."))
    ) },
    "he" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("יום שני", "יום שלישי", "יום רביעי", "יום חמישי", "יום שישי", "יום שבת", "יום ראשון")),
        abbreviated = DayOfWeekNames(listOf("יום ב׳", "יום ג׳", "יום ד׳", "יום ה׳", "יום ו׳", "שבת", "יום א׳"))
    ) },
    "hi" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("सोमवार", "मंगलवार", "बुधवार", "गुरुوار", "शुक्रवार", "शनिवार", "रविवार")),
        abbreviated = DayOfWeekNames(listOf("सोम", "मंगल", "बुध", "गुरु", "शुक्र", "शनि", "रवि"))
    ) },
    "hr" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("ponedjeljak", "utorak", "srijeda", "četvrtak", "petak", "subota", "nedjelja")),
        abbreviated = DayOfWeekNames(listOf("pon", "uto", "sri", "čet", "pet", "sub", "ned"))
    ) },
    "hu" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("hétfő", "kedd", "szerda", "csütörtök", "péntek", "szombat", "vasárnap")),
        abbreviated = DayOfWeekNames(listOf("H", "K", "Sze", "Cs", "P", "Szo", "V"))
    ) },
    "hy" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("երկուշաբթի", "երեքշաբթի", "չորեքշաբթի", "հինգշաբթի", "ուրբաթ", "շաբաթ", "կիրակի")),
        abbreviated = DayOfWeekNames(listOf("երկ", "երք", "չրք", "հնգ", "ուր", "շբթ", "կիր"))
    ) },
    "is" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("mánudagur", "þriðjudagur", "miðvikudagur", "fimmtudagur", "föstudagur", "laugardagur", "sunnudagur")),
        abbreviated = DayOfWeekNames(listOf("mán.", "þri.", "mið.", "fim.", "fös.", "lau.", "sun."))
    ) },
    "ka" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("ორშაბათი", "სამშაბათი", "ოთხშაბათი", "ხუთშაბათი", "პარასკევი", "შაბათი", "კვირა")),
        abbreviated = DayOfWeekNames(listOf("ორშ", "სამ", "ოთხ", "ხუთ", "პარ", "შაბ", "კვი"))
    ) },
    "lt" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("pirmadienis", "antradienis", "trečiadienis", "ketvirtadienis", "penktadienis", "šeštadienis", "sekmadienis")),
        abbreviated = DayOfWeekNames(listOf("pr", "an", "tr", "kt", "pn", "št", "sk"))
    ) },
    "lv" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("pirmdiena", "otrdiena", "trešdiena", "ceturtdiena", "piektdiena", "sestdiena", "svētdiena")),
        abbreviated = DayOfWeekNames(listOf("pirmd.", "otrd.", "trešd.", "ceturtd.", "piektd.", "sestd.", "svētd."))
    ) },
    "mk" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("понеделник", "вторник", "среда", "четврток", "петок", "сабота", "недела")),
        abbreviated = DayOfWeekNames(listOf("пон.", "вто.", "сре.", "чет.", "пет.", "саб.", "нед."))
    ) },
    "ms" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Isnin", "Selasa", "Rabu", "Khamis", "Jumaat", "Sabtu", "Ahad")),
        abbreviated = DayOfWeekNames(listOf("Isn", "Sel", "Rab", "Kha", "Jum", "Sab", "Ahd"))
    ) },
    "nb" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("mandag", "tirsdag", "onsdag", "torsdag", "fredag", "lørdag", "søndag")),
        abbreviated = DayOfWeekNames(listOf("man.", "tir.", "ons.", "tor.", "fre.", "lør.", "søn."))
    ) },
    "nn" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("måndag", "tysdag", "onsdag", "torsdag", "fredag", "laurdag", "søndag")),
        abbreviated = DayOfWeekNames(listOf("må.", "ty.", "on.", "to.", "fr.", "la.", "sø."))
    ) },
    "no" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("mandag", "tirsdag", "onsdag", "torsdag", "fredag", "lørdag", "søndag")),
        abbreviated = DayOfWeekNames(listOf("man.", "tir.", "ons.", "tor.", "fre.", "lør.", "søn."))
    ) },
    "ro" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("luni", "marți", "miercuri", "joi", "vineri", "sâmbătă", "duminică")),
        abbreviated = DayOfWeekNames(listOf("lun.", "mar.", "mie.", "joi", "vin.", "sâm.", "dum."))
    ) },
    "sk" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("pondelok", "utorok", "streda", "štvrtok", "piatok", "sobota", "nedeľa")),
        abbreviated = DayOfWeekNames(listOf("po", "ut", "st", "št", "pi", "so", "ne"))
    ) },
    "sl" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("ponedeljek", "torek", "sreda", "četrtek", "petek", "sobota", "nedelja")),
        abbreviated = DayOfWeekNames(listOf("pon.", "tor.", "sre.", "čet.", "pet.", "sob.", "ned."))
    ) },
    "sq" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("e hënë", "e martë", "e mërkurë", "e enjte", "e premte", "e shtunë", "e diel")),
        abbreviated = DayOfWeekNames(listOf("hën", "mar", "mër", "enj", "pre", "sht", "die"))
    ) },
    "sr" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("понедељак", "уторак", "среда", "четвртак", "петак", "субота", "недеља")),
        abbreviated = DayOfWeekNames(listOf("пон", "уто", "сре", "чет", "пет", "суб", "нед"))
    ) },
    "sv" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag")),
        abbreviated = DayOfWeekNames(listOf("mån", "tis", "ons", "tors", "fre", "lör", "sön"))
    ) },
    "sw" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("Jumatatu", "Jumanne", "Jumatano", "Alhamisi", "Ijumaa", "Jumamosi", "Jumapili")),
        abbreviated = DayOfWeekNames(listOf("Jumatatu", "Jumanne", "Jumatano", "Alhamisi", "Ijumaa", "Jumamosi", "Jumapili"))
    ) },
    "th" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("วันจันทร์", "วันอังคาร", "วันพุธ", "วันพฤหัสบดี", "วันศุกร์", "วันเสาร์", "วันอาทิตย์")),
        abbreviated = DayOfWeekNames(listOf("จันทร์", "อังคาร", "พุธ", "พฤหัส", "ศุกร์", "เสาร์", "อาทิตย์"))
    ) },
    "numbers" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf(
            DayOfWeek.MONDAY.isoDayNumber.toString(),
            DayOfWeek.TUESDAY.isoDayNumber.toString(),
            DayOfWeek.WEDNESDAY.isoDayNumber.toString(),
            DayOfWeek.THURSDAY.isoDayNumber.toString(),
            DayOfWeek.FRIDAY.isoDayNumber.toString(),
            DayOfWeek.SATURDAY.isoDayNumber.toString(),
            DayOfWeek.SUNDAY.isoDayNumber.toString()
        )),
        abbreviated = DayOfWeekNames(listOf(
            DayOfWeek.MONDAY.isoDayNumber.toString(),
            DayOfWeek.TUESDAY.isoDayNumber.toString(),
            DayOfWeek.WEDNESDAY.isoDayNumber.toString(),
            DayOfWeek.THURSDAY.isoDayNumber.toString(),
            DayOfWeek.FRIDAY.isoDayNumber.toString(),
            DayOfWeek.SATURDAY.isoDayNumber.toString(),
            DayOfWeek.SUNDAY.isoDayNumber.toString()
        ))
    ) }
)
