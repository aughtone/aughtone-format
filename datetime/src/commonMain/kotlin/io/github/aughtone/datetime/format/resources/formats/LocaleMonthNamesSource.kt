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
    "af" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Januarie",
                february = "Februarie",
                march = "Maart",
                april = "April",
                may = "Mei",
                june = "Junie",
                july = "Julie",
                august = "Augustus",
                september = "September",
                october = "Oktober",
                november = "November",
                december = "Desember"
            ),
            abbreviated = MonthNames(
                january = "Jan.",
                february = "Feb.",
                march = "Mrt.",
                april = "Apr.",
                may = "Mei",
                june = "Jun.",
                july = "Jul.",
                august = "Aug.",
                september = "Sep.",
                october = "Okt.",
                november = "Nov.",
                december = "Des."
            )
        )
    },
    "az" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "yanvar",
                february = "fevral",
                march = "mart",
                april = "aprel",
                may = "may",
                june = "iyun",
                july = "iyul",
                august = "avqust",
                september = "sentyabr",
                october = "oktyabr",
                november = "noyabr",
                december = "dekabr"
            ),
            abbreviated = MonthNames(
                january = "yan",
                february = "fev",
                march = "mar",
                april = "apr",
                may = "may",
                june = "iyn",
                july = "iyl",
                august = "avq",
                september = "sen",
                october = "okt",
                november = "noy",
                december = "dek"
            )
        )
    },
    "be" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "студзеня",
                february = "лютага",
                march = "сакавіка",
                april = "красавіка",
                may = "мая",
                june = "чэрвеня",
                july = "ліпеня",
                august = "жніўня",
                september = "верасня",
                october = "кастрычніка",
                november = "лістапада",
                december = "снежня"
            ),
            abbreviated = MonthNames(
                january = "сту",
                february = "лют",
                march = "сак",
                april = "кра",
                may = "мая",
                june = "чэр",
                july = "ліп",
                august = "жні",
                september = "вер",
                october = "кас",
                november = "ліс",
                december = "сне"
            )
        )
    },
    "bg" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "януари",
                february = "февруари",
                march = "март",
                april = "април",
                may = "май",
                june = "юни",
                july = "юли",
                august = "август",
                september = "септември",
                october = "октомври",
                november = "ноември",
                december = "декември"
            ),
            abbreviated = MonthNames(
                january = "яну",
                february = "фев",
                march = "март",
                april = "апр",
                may = "май",
                june = "юни",
                july = "юли",
                august = "авг",
                september = "сеп",
                october = "окт",
                november = "ное",
                december = "дек"
            )
        )
    },
    "ca" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "de gener",
                february = "de febrer",
                march = "de març",
                april = "d’abril",
                may = "de maig",
                june = "de juny",
                july = "de juliol",
                august = "d’agost",
                september = "de setembre",
                october = "d’octubre",
                november = "de novembre",
                december = "de desembre"
            ),
            abbreviated = MonthNames(
                january = "de gen.",
                february = "de febr.",
                march = "de març",
                april = "d’abr.",
                may = "de maig",
                june = "de juny",
                july = "de jul.",
                august = "d’ag.",
                september = "de set.",
                october = "d’oct.",
                november = "de nov.",
                december = "de des."
            )
        )
    },
    "da" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januar",
                february = "februar",
                march = "marts",
                april = "april",
                may = "maj",
                june = "juni",
                july = "juli",
                august = "august",
                september = "september",
                october = "oktober",
                november = "november",
                december = "december"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mar.",
                april = "apr.",
                may = "maj",
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
    "el" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Ιανουαρίου",
                february = "Φεβρουαρίου",
                march = "Μαρτίου",
                april = "Απριλίου",
                may = "Μαΐου",
                june = "Ιουνίου",
                july = "Ιουλίου",
                august = "Αυγούστου",
                september = "Σεπτεμβρίου",
                october = "Οκτωβρίου",
                november = "Νοεμβρίου",
                december = "Δεκεμβρίου"
            ),
            abbreviated = MonthNames(
                january = "Ιαν",
                february = "Φεβ",
                march = "Μαρ",
                april = "Απρ",
                may = "Μαΐ",
                june = "Ιουν",
                july = "Ιουλ",
                august = "Αυγ",
                september = "Σεπ",
                october = "Οκτ",
                november = "Νοε",
                december = "Δεκ"
            )
        )
    },
    "et" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "jaanuar",
                february = "veebruar",
                march = "märts",
                april = "aprill",
                may = "mai",
                june = "juuni",
                july = "juuli",
                august = "august",
                september = "september",
                october = "oktoober",
                november = "november",
                december = "detsember"
            ),
            abbreviated = MonthNames(
                january = "jaan",
                february = "veebr",
                march = "märts",
                april = "apr",
                may = "mai",
                june = "juuni",
                july = "juuli",
                august = "aug",
                september = "sept",
                october = "okt",
                november = "nov",
                december = "dets"
            )
        )
    },
    "eu" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "urtarrila",
                february = "otsaila",
                march = "martxoa",
                april = "apirila",
                may = "maiatza",
                june = "ekaina",
                july = "uztaila",
                august = "abuztua",
                september = "iraila",
                october = "urria",
                november = "azaroa",
                december = "abendua"
            ),
            abbreviated = MonthNames(
                january = "urt.",
                february = "ots.",
                march = "mar.",
                april = "api.",
                may = "mai.",
                june = "eka.",
                july = "uzt.",
                august = "abu.",
                september = "ira.",
                october = "urr.",
                november = "aza.",
                december = "abe."
            )
        )
    },
    "fa" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "ژانویهٔ",
                february = "فوریهٔ",
                march = "مارس",
                april = "آوریل",
                may = "مهٔ",
                june = "ژوئن",
                july = "ژوئیهٔ",
                august = "اوت",
                september = "سپتامبر",
                october = "اکتبر",
                november = "نوامبر",
                december = "دسامبر"
            ),
            abbreviated = MonthNames(
                january = "ژانویه",
                february = "فوریه",
                march = "مارس",
                april = "آوریل",
                may = "مه",
                june = "ژوئن",
                july = "ژوئیه",
                august = "اوت",
                september = "سپتامبر",
                october = "اکتبر",
                november = "نوامبر",
                december = "دسامبر"
            )
        )
    },
    "gl" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "xaneiro",
                february = "febreiro",
                march = "marzo",
                april = "abril",
                may = "maio",
                june = "xuño",
                july = "xullo",
                august = "agosto",
                september = "setembro",
                october = "outubro",
                november = "novembro",
                december = "decembro"
            ),
            abbreviated = MonthNames(
                january = "xan.",
                february = "feb.",
                march = "mar.",
                april = "abr.",
                may = "maio",
                june = "xuño",
                july = "xul.",
                august = "ago.",
                september = "set.",
                october = "out.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "he" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "ינואר",
                february = "פברואר",
                march = "מרץ",
                april = "אפריל",
                may = "מאי",
                june = "יוני",
                july = "יולי",
                august = "אוגוסט",
                september = "ספטמבר",
                october = "אוקטובר",
                november = "נובמבר",
                december = "דצמבר"
            ),
            abbreviated = MonthNames(
                january = "ינו׳",
                february = "פבר׳",
                march = "מרץ",
                april = "אפר׳",
                may = "מאי",
                june = "יוני",
                july = "יולי",
                august = "אוג׳",
                september = "ספט׳",
                october = "אוק׳",
                november = "נוב׳",
                december = "דצמ׳"
            )
        )
    },
    "hi" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "जनवरी",
                february = "फ़रवरी",
                march = "मार्च",
                april = "अप्रैल",
                may = "मई",
                june = "जून",
                july = "जुलाई",
                august = "अगस्त",
                september = "सितंबर",
                october = "अक्टूबर",
                november = "नवंबर",
                december = "दिसंबर"
            ),
            abbreviated = MonthNames(
                january = "जन॰",
                february = "फ़र॰",
                march = "मार्च",
                april = "अप्रैल",
                may = "मई",
                june = "जून",
                july = "जुल॰",
                august = "अग॰",
                september = "सित॰",
                october = "अक्टू॰",
                november = "नव॰",
                december = "दिस॰"
            )
        )
    },
    "hr" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "siječnja",
                february = "veljače",
                march = "ožujka",
                april = "travnja",
                may = "svibnja",
                june = "lipnja",
                july = "srpnja",
                august = "kolovoza",
                september = "rujna",
                october = "listopada",
                november = "studenoga",
                december = "prosinca"
            ),
            abbreviated = MonthNames(
                january = "sij",
                february = "velj",
                march = "ožu",
                april = "tra",
                may = "svi",
                june = "lip",
                july = "srp",
                august = "kol",
                september = "ruj",
                october = "lis",
                november = "stu",
                december = "pro"
            )
        )
    },
    "hu" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "január",
                february = "február",
                march = "március",
                april = "április",
                may = "május",
                june = "június",
                july = "július",
                august = "augusztus",
                september = "szeptember",
                october = "október",
                november = "november",
                december = "december"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "febr.",
                march = "márc.",
                april = "ápr.",
                may = "máj.",
                june = "jún.",
                july = "júl.",
                august = "aug.",
                september = "szept.",
                october = "okt.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "hy" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "հունվարի",
                february = "փետրվարի",
                march = "մարտի",
                april = "ապրիլի",
                may = "մայիսի",
                june = "հունիսի",
                july = "հուլիսի",
                august = "օգոստոսի",
                september = "սեպտեմբերի",
                october = "հոկտեմբերի",
                november = "նոյեմբերի",
                december = "դեկտեմբերի"
            ),
            abbreviated = MonthNames(
                january = "հնվ",
                february = "փտվ",
                march = "մրտ",
                april = "ապր",
                may = "մյս",
                june = "հնս",
                july = "հլս",
                august = "օգս",
                september = "սեպ",
                october = "հոկ",
                november = "նոյ",
                december = "դեկ"
            )
        )
    },
    "is" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "janúar",
                february = "febrúar",
                march = "mars",
                april = "apríl",
                may = "maí",
                june = "júní",
                july = "júlí",
                august = "ágúst",
                september = "september",
                october = "október",
                november = "nóvember",
                december = "desember"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mar.",
                april = "apr.",
                may = "maí",
                june = "jún.",
                july = "júl.",
                august = "ágú.",
                september = "sep.",
                october = "okt.",
                november = "nóv.",
                december = "des."
            )
        )
    },
    "ka" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "იანვარი",
                february = "თებერვალი",
                march = "მარტი",
                april = "აპრილი",
                may = "მაისი",
                june = "ივნისი",
                july = "ივლისი",
                august = "აგვისტო",
                september = "სექტემბერი",
                october = "ოქტომბერი",
                november = "ნოემბერი",
                december = "დეკემბერი"
            ),
            abbreviated = MonthNames(
                january = "იან",
                february = "თებ",
                march = "მარ",
                april = "აპრ",
                may = "მაი",
                june = "ივნ",
                july = "ივლ",
                august = "აგვ",
                september = "სექ",
                october = "ოქტ",
                november = "ნოე",
                december = "დეკ"
            )
        )
    },
    "lt" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "sausio",
                february = "vasario",
                march = "kovo",
                april = "balandžio",
                may = "gegužės",
                june = "birželio",
                july = "liepos",
                august = "rugpjūčio",
                september = "rugsėjo",
                october = "spalio",
                november = "lapkričio",
                december = "gruodžio"
            ),
            abbreviated = MonthNames(
                january = "saus.",
                february = "vas.",
                march = "kov.",
                april = "bal.",
                may = "geg.",
                june = "birž.",
                july = "liep.",
                august = "rugp.",
                september = "rugs.",
                october = "spal.",
                november = "lapkr.",
                december = "gruod."
            )
        )
    },
    "lv" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "janvāris",
                february = "februāris",
                march = "marts",
                april = "aprīlis",
                may = "maijs",
                june = "jūnijs",
                july = "jūlijs",
                august = "augusts",
                september = "septembris",
                october = "oktobris",
                november = "novembris",
                december = "decembris"
            ),
            abbreviated = MonthNames(
                january = "janv.",
                february = "febr.",
                march = "marts",
                april = "apr.",
                may = "maijs",
                june = "jūn.",
                july = "jūl.",
                august = "aug.",
                september = "sept.",
                october = "okt.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "mk" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "јануари",
                february = "февруари",
                march = "март",
                april = "април",
                may = "мај",
                june = "јуни",
                july = "јули",
                august = "август",
                september = "септември",
                october = "октомври",
                november = "ноември",
                december = "декември"
            ),
            abbreviated = MonthNames(
                january = "јан.",
                february = "фев.",
                march = "мар.",
                april = "апр.",
                may = "мај",
                june = "јун.",
                july = "јул.",
                august = "авг.",
                september = "сеп.",
                october = "окт.",
                november = "ное.",
                december = "дек."
            )
        )
    },
    "ms" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Januari",
                february = "Februari",
                march = "Mac",
                april = "April",
                may = "Mei",
                june = "Jun",
                july = "Julai",
                august = "Ogos",
                september = "September",
                october = "Oktober",
                november = "November",
                december = "Disember"
            ),
            abbreviated = MonthNames(
                january = "Jan",
                february = "Feb",
                march = "Mac",
                april = "Apr",
                may = "Mei",
                june = "Jun",
                july = "Jul",
                august = "Ogo",
                september = "Sep",
                october = "Okt",
                november = "Nov",
                december = "Dis"
            )
        )
    },
    "nb" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januar",
                february = "februar",
                march = "mars",
                april = "april",
                may = "mai",
                june = "juni",
                july = "juli",
                august = "august",
                september = "september",
                october = "oktober",
                november = "november",
                december = "desember"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mars",
                april = "apr.",
                may = "mai",
                june = "juni",
                july = "juli",
                august = "aug.",
                september = "sep.",
                october = "okt.",
                november = "nov.",
                december = "des."
            )
        )
    },
    "nn" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januar",
                february = "februar",
                march = "mars",
                april = "april",
                may = "mai",
                june = "juni",
                july = "juli",
                august = "august",
                september = "september",
                october = "oktober",
                november = "november",
                december = "desember"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mars",
                april = "apr.",
                may = "mai",
                june = "juni",
                july = "juli",
                august = "aug.",
                september = "sep.",
                october = "okt.",
                november = "nov.",
                december = "des."
            )
        )
    },
    "no" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januar",
                february = "februar",
                march = "mars",
                april = "april",
                may = "mai",
                june = "juni",
                july = "juli",
                august = "august",
                september = "september",
                october = "oktober",
                november = "november",
                december = "desember"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mars",
                april = "apr.",
                may = "mai",
                june = "juni",
                july = "juli",
                august = "aug.",
                september = "sep.",
                october = "okt.",
                november = "nov.",
                december = "des."
            )
        )
    },
    "ro" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "ianuarie",
                february = "februarie",
                march = "martie",
                april = "aprilie",
                may = "mai",
                june = "iunie",
                july = "iulie",
                august = "august",
                september = "septembrie",
                october = "octombrie",
                november = "noiembrie",
                december = "decembrie"
            ),
            abbreviated = MonthNames(
                january = "ian.",
                february = "febr.",
                march = "mar.",
                april = "apr.",
                may = "mai",
                june = "iun.",
                july = "iul.",
                august = "aug.",
                september = "sept.",
                october = "oct.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "sk" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januára",
                february = "februára",
                march = "marca",
                april = "apríla",
                may = "mája",
                june = "júna",
                july = "júla",
                august = "augusta",
                september = "septembra",
                october = "októbra",
                november = "novembra",
                december = "decembra"
            ),
            abbreviated = MonthNames(
                january = "jan",
                february = "feb",
                march = "mar",
                april = "apr",
                may = "máj",
                june = "jún",
                july = "júl",
                august = "aug",
                september = "sep",
                october = "okt",
                november = "nov",
                december = "dec"
            )
        )
    },
    "sl" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januar",
                february = "februar",
                march = "marec",
                april = "april",
                may = "maj",
                june = "junij",
                july = "julij",
                august = "avgust",
                september = "september",
                october = "oktober",
                november = "november",
                december = "december"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mar.",
                april = "apr.",
                may = "maj",
                june = "jun.",
                july = "jul.",
                august = "avg.",
                september = "sep.",
                october = "okt.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "sq" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "janar",
                february = "shkurt",
                march = "mars",
                april = "prill",
                may = "maj",
                june = "qershor",
                july = "korrik",
                august = "gusht",
                september = "shtator",
                october = "tetor",
                november = "nëntor",
                december = "dhjetor"
            ),
            abbreviated = MonthNames(
                january = "jan",
                february = "shk",
                march = "mar",
                april = "pri",
                may = "maj",
                june = "qer",
                july = "kor",
                august = "gush",
                september = "sht",
                october = "tet",
                november = "nën",
                december = "dhj"
            )
        )
    },
    "sr" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "јануар",
                february = "фебруар",
                march = "март",
                april = "април",
                may = "мај",
                june = "јун",
                july = "јул",
                august = "август",
                september = "септембар",
                october = "октобар",
                november = "новембар",
                december = "децембар"
            ),
            abbreviated = MonthNames(
                january = "јан",
                february = "феб",
                march = "мар",
                april = "апр",
                may = "мај",
                june = "јун",
                july = "јул",
                august = "авг",
                september = "сеп",
                october = "окт",
                november = "нов",
                december = "дец"
            )
        )
    },
    "sv" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "januari",
                february = "februari",
                march = "mars",
                april = "april",
                may = "maj",
                june = "juni",
                july = "juli",
                august = "augusti",
                september = "september",
                october = "oktober",
                november = "november",
                december = "december"
            ),
            abbreviated = MonthNames(
                january = "jan.",
                february = "feb.",
                march = "mars",
                april = "apr.",
                may = "maj",
                june = "juni",
                july = "juli",
                august = "aug.",
                september = "sep.",
                october = "okt.",
                november = "nov.",
                december = "dec."
            )
        )
    },
    "sw" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "Januari",
                february = "Februari",
                march = "Machi",
                april = "Aprili",
                may = "Mei",
                june = "Juni",
                july = "Julai",
                august = "Agosti",
                september = "Septemba",
                october = "Oktoba",
                november = "Novemba",
                december = "Desemba"
            ),
            abbreviated = MonthNames(
                january = "Jan",
                february = "Feb",
                march = "Mac",
                april = "Apr",
                may = "Mei",
                june = "Jun",
                july = "Jul",
                august = "Ago",
                september = "Sep",
                october = "Okt",
                november = "Nov",
                december = "Des"
            )
        )
    },
    "th" to lazy {
        MonthNamesData(
            full = MonthNames(
                january = "มกราคม",
                february = "กุมภาพันธ์",
                march = "มีนาคม",
                april = "เมษายน",
                may = "พฤษภาคม",
                june = "มิถุนายน",
                july = "กรกฎาคม",
                august = "สิงหาคม",
                september = "กันยายน",
                october = "ตุลาคม",
                november = "พฤศจิกายน",
                december = "ธันวาคม"
            ),
            abbreviated = MonthNames(
                january = "ม.ค.",
                february = "ก.พ.",
                march = "มี.ค.",
                april = "เม.ย.",
                may = "พ.ค.",
                june = "มิ.ย.",
                july = "ก.ค.",
                august = "ส.ค.",
                september = "ก.ย.",
                october = "ต.ค.",
                november = "พ.ย.",
                december = "ธ.ค."
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
