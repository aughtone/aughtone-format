package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.strings.DayOfWeekNamesResource
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.format.DayOfWeekNames

internal data class DayOfWeekNamesData(
    override val full: DayOfWeekNames,
    override val abbreviated: DayOfWeekNames
) : DayOfWeekNamesResource

internal val localeDayOfWeekNamesSource = mapOf(
    "ar" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت")),
        abbreviated = DayOfWeekNames(listOf("الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت"))
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
    "zh" to lazy { DayOfWeekNamesData(
        full = DayOfWeekNames(listOf("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日")),
        abbreviated = DayOfWeekNames(listOf("一", "二", "三", "四", "五", "六", "日"))
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
