package io.github.aughtone.datetime.format.resources.formats

// ─────────────────────────────────────────────────────────────────────────────
// Generated from Unicode CLDR 48.0.0 by tools/cldr/format-names.mjs — do not edit by
// hand; change the generator and run it again.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Month and weekday names in the form a language writes them inside a date, where that differs
 * from the name on its own: lower case in Spanish, French or Italian ("5 de marzo de 2024", where
 * a heading reads "Marzo"), another grammatical form in Russian ("5 марта", not "Март").
 *
 * Each list replaces the bundled set of the same width inside a date; `null` keeps the bundled
 * set. Months run January to December and weekdays Monday to Sunday. The names are CLDR's
 * format-context names.
 */
internal class InDateNames(
    val monthsFull: List<String>? = null,
    val monthsAbbreviated: List<String>? = null,
    val daysFull: List<String>? = null,
    val daysAbbreviated: List<String>? = null,
)

/** In-date names keyed by language code, for the languages whose in-date names differ. */
internal val localeInDateNames: Map<String, InDateNames> = mapOf(
    "cs" to InDateNames(
        monthsFull = listOf("ledna", "února", "března", "dubna", "května", "června", "července", "srpna", "září", "října", "listopadu", "prosince"),
        monthsAbbreviated = listOf("led", "úno", "bře", "dub", "kvě", "čvn", "čvc", "srp", "zář", "říj", "lis", "pro"),
        daysFull = listOf("pondělí", "úterý", "středa", "čtvrtek", "pátek", "sobota", "neděle"),
        daysAbbreviated = listOf("po", "út", "st", "čt", "pá", "so", "ne"),
    ),
    "es" to InDateNames(
        monthsFull = listOf("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"),
        monthsAbbreviated = listOf("ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sept", "oct", "nov", "dic"),
        daysFull = listOf("lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"),
        daysAbbreviated = listOf("lun", "mar", "mié", "jue", "vie", "sáb", "dom"),
    ),
    "fi" to InDateNames(
        monthsFull = listOf("tammikuuta", "helmikuuta", "maaliskuuta", "huhtikuuta", "toukokuuta", "kesäkuuta", "heinäkuuta", "elokuuta", "syyskuuta", "lokakuuta", "marraskuuta", "joulukuuta"),
        monthsAbbreviated = listOf("tammi", "helmi", "maalis", "huhti", "touko", "kesä", "heinä", "elo", "syys", "loka", "marras", "joulu"),
    ),
    "fr" to InDateNames(
        monthsFull = listOf("janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"),
        monthsAbbreviated = listOf("janv.", "févr.", "mars", "avr.", "mai", "juin", "juil.", "août", "sept.", "oct.", "nov.", "déc."),
    ),
    "it" to InDateNames(
        monthsFull = listOf("gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"),
        monthsAbbreviated = listOf("gen", "feb", "mar", "apr", "mag", "giu", "lug", "ago", "set", "ott", "nov", "dic"),
        daysFull = listOf("lunedì", "martedì", "mercoledì", "giovedì", "venerdì", "sabato", "domenica"),
        daysAbbreviated = listOf("lun", "mar", "mer", "gio", "ven", "sab", "dom"),
    ),
    "kk" to InDateNames(
        monthsFull = listOf("қаңтар", "ақпан", "наурыз", "сәуір", "мамыр", "маусым", "шілде", "тамыз", "қыркүйек", "қазан", "қараша", "желтоқсан"),
        monthsAbbreviated = listOf("қаң.", "ақп.", "нау.", "сәу.", "мам.", "мау.", "шіл.", "там.", "қыр.", "қаз.", "қар.", "жел."),
        daysFull = listOf("дүйсенбі", "сейсенбі", "сәрсенбі", "бейсенбі", "жұма", "сенбі", "жексенбі"),
        daysAbbreviated = listOf("дс", "сс", "ср", "бс", "жм", "сб", "жс"),
    ),
    "pl" to InDateNames(
        monthsFull = listOf("stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "października", "listopada", "grudnia"),
    ),
    "pt" to InDateNames(
        monthsFull = listOf("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"),
        monthsAbbreviated = listOf("jan.", "fev.", "mar.", "abr.", "mai.", "jun.", "jul.", "ago.", "set.", "out.", "nov.", "dez."),
    ),
    "ru" to InDateNames(
        monthsFull = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"),
        monthsAbbreviated = listOf("янв.", "февр.", "мар.", "апр.", "мая", "июн.", "июл.", "авг.", "сент.", "окт.", "нояб.", "дек."),
    ),
    "uk" to InDateNames(
        monthsFull = listOf("січня", "лютого", "березня", "квітня", "травня", "червня", "липня", "серпня", "вересня", "жовтня", "листопада", "грудня"),
        monthsAbbreviated = listOf("січ.", "лют.", "бер.", "квіт.", "трав.", "черв.", "лип.", "серп.", "вер.", "жовт.", "лист.", "груд."),
    ),
    "uz" to InDateNames(
        monthsFull = listOf("yanvar", "fevral", "mart", "aprel", "may", "iyun", "iyul", "avgust", "sentabr", "oktabr", "noyabr", "dekabr"),
        monthsAbbreviated = listOf("yan", "fev", "mar", "apr", "may", "iyn", "iyl", "avg", "sen", "okt", "noy", "dek"),
        daysFull = listOf("dushanba", "seshanba", "chorshanba", "payshanba", "juma", "shanba", "yakshanba"),
    ),
    "vi" to InDateNames(
        monthsFull = listOf("tháng 1", "tháng 2", "tháng 3", "tháng 4", "tháng 5", "tháng 6", "tháng 7", "tháng 8", "tháng 9", "tháng 10", "tháng 11", "tháng 12"),
        monthsAbbreviated = listOf("thg 1", "thg 2", "thg 3", "thg 4", "thg 5", "thg 6", "thg 7", "thg 8", "thg 9", "thg 10", "thg 11", "thg 12"),
    ),
)
