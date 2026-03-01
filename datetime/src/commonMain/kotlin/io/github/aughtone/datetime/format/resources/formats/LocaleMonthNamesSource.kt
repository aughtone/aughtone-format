package io.github.aughtone.datetime.format.resources.formats

import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import kotlinx.datetime.Month
import kotlinx.datetime.number
import kotlinx.datetime.format.MonthNames


internal data class MonthNamesData(
    override val full: MonthNames,
    override val abbreviated: MonthNames
) : MonthNamesResource

internal val localeMonthNamesSource = mapOf(
    "ar" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "يناير",
                february = "فبراير",
                march = "مارس",
                april = "أبريل",
                may = "مايو",
                june = "يونيو",
                july = "يوليو",
                august = "أغسطس",
                september = "سبتمبر",
                october = "أكتوبر",
                november = "نوفمبر",
                december = "ديسمبر"
            ),
            abbreviated = MonthNames(
                january = "يناير",
                february = "فبراير",
                march = "مارس",
                april = "أبريل",
                may = "مايو",
                june = "يونيو",
                july = "يوليو",
                august = "أغسطس",
                september = "سبتمبر",
                october = "أكتوبر",
                november = "نوفمبر",
                december = "ديسمبر"
            )
        )
    },
    "cs" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Leden",
                february = "Únor",
                march = "Březen",
                april = "Duben",
                may = "Květen",
                june = "Červen",
                july = "Červenec",
                august = "Srpen",
                september = "Září",
                october = "Říjen",
                november = "Listopad",
                december = "Prosinec"
            ),
            abbreviated = MonthNames(
                january = "Led",
                february = "Úno",
                march = "Bře",
                april = "Dub",
                may = "Kvě",
                june = "Čvn",
                july = "Čvc",
                august = "Srp",
                september = "Zář",
                october = "Říj",
                november = "Lis",
                december = "Pro"
            )
        )
    },
    "de" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Januar",
                february = "Februar",
                march = "März",
                april = "April",
                may = "Mai",
                june = "Juni",
                july = "Juli",
                august = "August",
                september = "September",
                october = "Oktober",
                november = "November",
                december = "Dezember"
            ),
            abbreviated = MonthNames(
                january = "Jan.",
                february = "Feb.",
                march = "Mär.",
                april = "Apr.",
                may = "Mai",
                june = "Juni",
                july = "Juli",
                august = "Aug.",
                september = "Sept.",
                october = "Okt.",
                november = "Nov.",
                december = "Dez."
            )
        )
    },
    "en" to lazy {
        MonthNamesData(
            full = MonthNames.ENGLISH_FULL,
            abbreviated = MonthNames.ENGLISH_ABBREVIATED
        )
    },
    "es" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Enero",
                february = "Febrero",
                march = "Marzo",
                april = "Abril",
                may = "Mayo",
                june = "Junio",
                july = "Julio",
                august = "Agosto",
                september = "Septiembre",
                october = "Octubre",
                november = "Noviembre",
                december = "Diciembre"
            ),
            abbreviated = MonthNames(
                january = "Ene.",
                february = "Feb.",
                march = "Mar.",
                april = "Abr.",
                may = "May.",
                june = "Jun.",
                july = "Jul.",
                august = "Ago.",
                september = "Sept.",
                october = "Oct.",
                november = "Nov.",
                december = "Dic."
            )
        )
    },
    "fi" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Tammikuu",
                february = "Helmikuu",
                march = "Maaliskuu",
                april = "Huhtikuu",
                may = "Toukokuu",
                june = "Kesäkuu",
                july = "Heinäkuu",
                august = "Elokuu",
                september = "Syyskuu",
                october = "Lokakuu",
                november = "Marraskuu",
                december = "Joulukuu"
            ),
            abbreviated = MonthNames(
                january = "Tammi",
                february = "Helmi",
                march = "Maalis",
                april = "Huhti",
                may = "Touko",
                june = "Kesä",
                july = "Heinä",
                august = "Elo",
                september = "Syys",
                october = "Loka",
                november = "Marras",
                december = "Joulu"
            )
        )
    },
    "fr" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Janvier",
                february = "Février",
                march = "Mars",
                april = "Avril",
                may = "Mai",
                june = "Juin",
                july = "Juillet",
                august = "Août",
                september = "Septembre",
                october = "October",
                november = "Octobre",
                december = "Décembre"
            ),
            abbreviated = MonthNames(
                january = "Jan.",
                february = "Fév.",
                march = "Mars",
                april = "Avril",
                may = "Mai",
                june = "Juin",
                july = "Juil.",
                august = "Août",
                september = "Sept.",
                october = "Oct.",
                november = "Nov.",
                december = "Déc."
            )
        )
    },
    "id" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Januari",
                february = "Februari",
                march = "Maret",
                april = "April",
                may = "Mei",
                june = "Juni",
                july = "Juli",
                august = "Agustus",
                september = "September",
                october = "Oktober",
                november = "November",
                december = "Desember"
            ),
            abbreviated = MonthNames(
                january = "Jan",
                february = "Feb",
                march = "Mar",
                april = "Apr",
                may = "Mei",
                june = "Jun",
                july = "Jul",
                august = "Agu",
                september = "Sep",
                october = "Okt",
                november = "Nov",
                december = "Des"
            )
        )
    },
    "it" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Gennaio",
                february = "Febbraio",
                march = "Marzo",
                april = "Aprile",
                may = "Maggio",
                june = "Giugno",
                july = "Luglio",
                august = "Agosto",
                september = "Settembre",
                october = "Ottobre",
                november = "Novembre",
                december = "Dicembre"
            ),
            abbreviated = MonthNames(
                january = "Gen.",
                february = "Feb.",
                march = "Mar.",
                april = "Apr.",
                may = "Mag.",
                june = "Giu.",
                july = "Lug.",
                august = "Ago.",
                september = "Set.",
                october = "Ott.",
                november = "Nov.",
                december = "Dic."
            )
        )
    },
    "iu" to lazy {
        MonthNamesData(
            full = MonthNames(
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
            ),
            abbreviated = MonthNames(
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
        )
    },
    "ja" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "1月",
                february = "2月",
                march = "3月",
                april = "4月",
                may = "5月",
                june = "6月",
                july = "7月",
                august = "8月",
                september = "9月",
                october = "10月",
                november = "11月",
                december = "12月"
            ),
            abbreviated = MonthNames(
                january = "1月",
                february = "2月",
                march = "3月",
                april = "4月",
                may = "5月",
                june = "6月",
                july = "7月",
                august = "8月",
                september = "9月",
                october = "10月",
                november = "11月",
                december = "12月"
            )
        )
    },
    "kk" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Қаңтар",
                february = "Ақпан",
                march = "Наурыз",
                april = "Сәуір",
                may = "Мамыр",
                june = "Маусым",
                july = "Шілде",
                august = "Тамыз",
                september = "Қыркүйек",
                october = "Қазан",
                november = "Қараша",
                december = "Желтоқсан"
            ),
            abbreviated = MonthNames(
                january = "Қаң",
                february = "Ақп",
                march = "Нау",
                april = "Сәу",
                may = "Мам",
                june = "Мау",
                july = "Шіл",
                august = "Там",
                september = "Қыр",
                october = "Қаз",
                november = "Қар",
                december = "Жел"
            )
        )
    },
    "ko" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "1월",
                february = "2월",
                march = "3월",
                april = "4월",
                may = "5월",
                june = "6월",
                july = "7월",
                august = "8월",
                september = "9월",
                october = "10월",
                november = "11월",
                december = "12월"
            ),
            abbreviated = MonthNames(
                january = "1월",
                february = "2월",
                march = "3월",
                april = "4월",
                may = "5월",
                june = "6월",
                july = "7월",
                august = "8월",
                september = "9월",
                october = "10월",
                november = "11월",
                december = "12월"
            )
        )
    },
    "nl" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januari",
                february = "februari",
                march = "maart",
                april = "april",
                may = "mei",
                june = "juni",
                july = "juli",
                august = "augustus",
                september = "september",
                october = "oktober",
                november = "november",
                december = "december"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mrt.",
                april = "apr.",
                may = "mei",
                june = "jun.",
                july = "jul.",
                august = "aug.",
                september = "sep.",
                october = "okt.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "pl" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "styczeń",
                february = "luty",
                march = "marzec",
                april = "kwiecień",
                may = "maj",
                june = "czerwiec",
                july = "lipiec",
                august = "sierpień",
                september = "wrzesień",
                october = "październik",
                november = "listopad",
                december = "grudzień"
            ),
            abbreviated = MonthNames(
                january = "sty",
                february = "lut",
                march = "mar",
                april = "kwi",
                may = "maj",
                june = "cze",
                july = "lip",
                august = "sie",
                september = "wrz",
                october = "paź",
                november = "lis",
                december = "gru"
            )
        )
    },
    "pt" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Janeiro",
                february = "Fevereiro",
                march = "Março",
                april = "Abril",
                may = "Maio",
                june = "Junho",
                july = "Julho",
                august = "Agosto",
                september = "Setembro",
                october = "Outubro",
                november = "Novembro",
                december = "Dezembro"
            ),
            abbreviated = MonthNames(
                january = "Jan.",
                february = "Fev.",
                march = "Mar.",
                april = "Abr.",
                may = "Mai.",
                june = "Jun.",
                july = "Jul.",
                august = "Ago.",
                september = "Set.",
                october = "Out.",
                november = "Nov.",
                december = "Dez."
            )
        )
    },
    "ru" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Январь",
                february = "Февраль",
                march = "Март",
                april = "Апрель",
                may = "Май",
                june = "Июнь",
                july = "Июль",
                august = "Август",
                september = "Сентябрь",
                october = "Октябрь",
                november = "Ноябрь",
                december = "Декабрь"
            ),
            abbreviated = MonthNames(
                january = "Янв",
                february = "Фев",
                march = "Мар",
                april = "Апр",
                may = "Май",
                june = "Июн",
                july = "Июл",
                august = "Авг",
                september = "Сен",
                october = "Окт",
                november = "Ноя",
                december = "Дек"
            )
        )
    },
    "tr" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Ocak",
                february = "Şubat",
                march = "Mart",
                april = "Nisan",
                may = "Mayıs",
                june = "Haziran",
                july = "Temmuz",
                august = "Ağustos",
                september = "Eylül",
                october = "Ekim",
                november = "Kasım",
                december = "Aralık"
            ),
            abbreviated = MonthNames(
                january = "Oca",
                february = "Şub",
                march = "Mar",
                april = "Nis",
                may = "May",
                june = "Haz",
                july = "Tem",
                august = "Ağu",
                september = "Eyl",
                october = "Eki",
                november = "Kas",
                december = "Ara"
            )
        )
    },
    "uk" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Січень",
                february = "Лютий",
                march = "Березень",
                april = "Квітень",
                may = "Травень",
                june = "Червень",
                july = "Липень",
                august = "Серпень",
                september = "Вересень",
                october = "Жовтень",
                november = "Листопад",
                december = "Грудень"
            ),
            abbreviated = MonthNames(
                january = "Січ",
                february = "Лют",
                march = "Бер",
                april = "Квіт",
                may = "Трав",
                june = "Черв",
                july = "Лип",
                august = "Серп",
                september = "Вер",
                october = "Жовт",
                november = "Лист",
                december = "Груд"
            )
        )
    },
    "uz" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Yanvar",
                february = "Fevral",
                march = "Mart",
                april = "Aprel",
                may = "May",
                june = "Iyun",
                july = "Iyul",
                august = "Avgust",
                september = "Sentyabr",
                october = "Oktyabr",
                november = "Noyabr",
                december = "Dekabr"
            ),
            abbreviated = MonthNames(
                january = "Yan",
                february = "Fev",
                march = "Mar",
                april = "Apr",
                may = "May",
                june = "Iyn",
                july = "Iyl",
                august = "Avg",
                september = "Sen",
                october = "Okt",
                november = "Noy",
                december = "Dek"
            )
        )
    },
    "vi" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Tháng Một",
                february = "Tháng Hai",
                march = "Tháng Ba",
                april = "Tháng Tư",
                may = "Tháng Năm",
                june = "Tháng Sáu",
                july = "Tháng Bảy",
                august = "Tháng Tám",
                september = "Tháng Chín",
                october = "Tháng Mười",
                november = "Tháng Mười Một",
                december = "Tháng Mười Hai"
            ),
            abbreviated = MonthNames(
                january = "Thg 1",
                february = "Thg 2",
                march = "Thg 3",
                april = "Thg 4",
                may = "Thg 5",
                june = "Thg 6",
                july = "Thg 7",
                august = "Thg 8",
                september = "Thg 9",
                october = "Thg 10",
                november = "Thg 11",
                december = "Thg 12"
            )
        )
    },
    "zh" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "一月",
                february = "二月",
                march = "三月",
                april = "四月",
                may = "五月",
                june = "六月",
                july = "七月",
                august = "八月",
                september = "九月",
                october = "十月",
                november = "十一月",
                december = "十二月"
            ),
            abbreviated = MonthNames(
                january = "一月",
                february = "二月",
                march = "三月",
                april = "四月",
                may = "五月",
                june = "六月",
                july = "七月",
                august = "八月",
                september = "九月",
                october = "十月",
                november = "十一月",
                december = "十二月"
            )
        )
    },
    "numbers" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = Month.JANUARY.number.toString(),
                february = Month.FEBRUARY.number.toString(),
                march = Month.MARCH.number.toString(),
                april = Month.APRIL.number.toString(),
                may = Month.MAY.number.toString(),
                june = Month.JUNE.number.toString(),
                july = Month.JULY.number.toString(),
                august = Month.AUGUST.number.toString(),
                september = Month.SEPTEMBER.number.toString(),
                october = Month.OCTOBER.number.toString(),
                november = Month.NOVEMBER.number.toString(),
                december = Month.DECEMBER.number.toString()
            ),
            abbreviated = MonthNames(
                january = Month.JANUARY.number.toString(),
                february = Month.FEBRUARY.number.toString(),
                march = Month.MARCH.number.toString(),
                april = Month.APRIL.number.toString(),
                may = Month.MAY.number.toString(),
                june = Month.JUNE.number.toString(),
                july = Month.JULY.number.toString(),
                august = Month.AUGUST.number.toString(),
                september = Month.SEPTEMBER.number.toString(),
                october = Month.OCTOBER.number.toString(),
                november = Month.NOVEMBER.number.toString(),
                december = Month.DECEMBER.number.toString()
            )
        )
    }
)
