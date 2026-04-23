package io.github.aughtone.datetime.format.resources.formats

data class DatePatterns(
    val short: String,
    val medium: String,
    val long: String,
    val full: String
)

internal val localeDatePatterns = mapOf(
    "CA" to DatePatterns(
        full = "EEEE, MMMM d, y",
        long = "MMMM d, y",
        medium = "MMM d, y",
        short = "y-MM-dd"
    ),
    "af" to DatePatterns(
        full = "EEEE dd MMMM y",
        long = "dd MMMM y",
        medium = "dd MMM y",
        short = "y-MM-dd"
    ),
    "ar" to DatePatterns(
        full = "EEEE، d MMMM y",
        long = "d MMMM y",
        medium = "dd‏/MM‏/y",
        short = "d‏/M‏/y"
    ),
    "az" to DatePatterns(
        full = "d MMMM y, EEEE",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd.MM.yy"
    ),
    "be" to DatePatterns(
        full = "EEEE, d MMMM y 'г'.",
        long = "d MMMM y 'г'.",
        medium = "d MMM y 'г'.",
        short = "d.MM.yy"
    ),
    "bg" to DatePatterns(
        full = "EEEE, d MMMM y 'г'.",
        long = "d MMMM y 'г'.",
        medium = "d.MM.y 'г'.",
        short = "d.MM.yy 'г'."
    ),
    "ca" to DatePatterns(
        full = "EEEE, d MMMM 'del' y",
        long = "d MMMM 'del' y",
        medium = "d MMM y",
        short = "d/M/yy"
    ),
    "cs" to DatePatterns(
        full = "EEEE d. MMMM y",
        long = "d. MMMM y",
        medium = "d. M. y",
        short = "dd.MM.yy"
    ),
    "da" to DatePatterns(
        full = "EEEE 'den' d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "dd.MM.y"
    ),
    "de" to DatePatterns(
        full = "EEEE, d. MMMM y",
        long = "d. MMMM y",
        medium = "dd.MM.y",
        short = "dd.MM.yy"
    ),
    "el" to DatePatterns(
        full = "EEEE d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "d/M/yy"
    ),
    "en" to DatePatterns(
        full = "EEEE, MMMM d, y",
        long = "MMMM d, y",
        medium = "MMM d, y",
        short = "M/d/yy"
    ),
    "US" to DatePatterns( // Maintain legacy key
        short = "M/d/yyyy",
        medium = "MMM d, yyyy",
        long = "MMMM d, yyyy",
        full = "EEEE, MMMM d, yyyy"
    ),
    "es" to DatePatterns(
        full = "EEEE, d 'de' MMMM 'de' y",
        long = "d 'de' MMMM 'de' y",
        medium = "d MMM y",
        short = "d/M/yy"
    ),
    "et" to DatePatterns(
        full = "EEEE, d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "dd.MM.yy"
    ),
    "eu" to DatePatterns(
        full = "y('e')'ko' MMMM'ren' d('a'), EEEE",
        long = "y('e')'ko' MMMM'ren' d('a')",
        medium = "y('e')'ko' MMM d('a')",
        short = "yy/M/d"
    ),
    "fa" to DatePatterns(
        full = "EEEE d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "y/M/d"
    ),
    "fi" to DatePatterns(
        full = "cccc d. MMMM y",
        long = "d. MMMM y",
        medium = "d.M.y",
        short = "d.M.y"
    ),
    "fr" to DatePatterns(
        full = "EEEE d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd/MM/y"
    ),
    "gl" to DatePatterns(
        full = "EEEE, d 'de' MMMM 'de' y",
        long = "d 'de' MMMM 'de' y",
        medium = "d 'de' MMM 'de' y",
        short = "dd/MM/yy"
    ),
    "he" to DatePatterns(
        full = "EEEE, d בMMMM y",
        long = "d בMMMM y",
        medium = "d בMMM y",
        short = "d.M.y"
    ),
    "hi" to DatePatterns(
        full = "EEEE, d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "d/M/yy"
    ),
    "hr" to DatePatterns(
        full = "EEEE, d. MMMM y.",
        long = "d. MMMM y.",
        medium = "d. MMM y.",
        short = "dd. MM. y."
    ),
    "hu" to DatePatterns(
        full = "y. MMMM d., EEEE",
        long = "y. MMMM d.",
        medium = "y. MMM d.",
        short = "y. MM. dd."
    ),
    "hy" to DatePatterns(
        full = "y թ. MMMM d, EEEE",
        long = "dd MMMM, y թ.",
        medium = "dd MMM, y թ.",
        short = "dd.MM.yy"
    ),
    "id" to DatePatterns(
        full = "EEEE, dd MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd/MM/yy"
    ),
    "is" to DatePatterns(
        full = "EEEE, d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "d.M.y"
    ),
    "it" to DatePatterns(
        full = "EEEE d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd/MM/yy"
    ),
    "iu" to DatePatterns(
        full = "EEEE, MMMM d, y",
        long = "MMMM d, y",
        medium = "MMM d, y",
        short = "MM/dd/y"
    ),
    "ja" to DatePatterns(
        full = "y年M月d日EEEE",
        long = "y年M月d日",
        medium = "y/MM/dd",
        short = "y/MM/dd"
    ),
    "ka" to DatePatterns(
        full = "EEEE, dd MMMM, y",
        long = "d MMMM, y",
        medium = "d MMM. y",
        short = "dd.MM.yy"
    ),
    "kk" to DatePatterns(
        full = "y 'ж'. d MMMM, EEEE",
        long = "y 'ж'. d MMMM",
        medium = "y 'ж'. dd MMM",
        short = "dd.MM.yy"
    ),
    "ko" to DatePatterns(
        full = "y년 MMMM d일 EEEE",
        long = "y년 MMMM d일",
        medium = "y. M. d.",
        short = "yy. M. d."
    ),
    "lt" to DatePatterns(
        full = "y 'm'. MMMM d 'd'., EEEE",
        long = "y 'm'. MMMM d 'd'.",
        medium = "y-MM-dd",
        short = "y-MM-dd"
    ),
    "lv" to DatePatterns(
        full = "EEEE, y. 'gada' d. MMMM",
        long = "y. 'gada' d. MMMM",
        medium = "y. 'gada' d. MMM",
        short = "dd.MM.yy"
    ),
    "mk" to DatePatterns(
        full = "EEEE, d MMMM y 'г'.",
        long = "d MMMM y 'г'.",
        medium = "d MMM y 'г'.",
        short = "d.M.y 'г'."
    ),
    "ms" to DatePatterns(
        full = "EEEE, d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "d/MM/yy"
    ),
    "nb" to DatePatterns(
        full = "EEEE d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "dd.MM.y"
    ),
    "nl" to DatePatterns(
        full = "EEEE d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd-MM-y"
    ),
    "nn" to DatePatterns(
        full = "EEEE d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "dd.MM.y"
    ),
    "no" to DatePatterns(
        full = "EEEE d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "dd.MM.y"
    ),
    "pl" to DatePatterns(
        full = "EEEE, d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "d.MM.y"
    ),
    "pt" to DatePatterns(
        full = "EEEE, d 'de' MMMM 'de' y",
        long = "d 'de' MMMM 'de' y",
        medium = "d 'de' MMM 'de' y",
        short = "dd/MM/y"
    ),
    "ro" to DatePatterns(
        full = "EEEE, d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd.MM.y"
    ),
    "ru" to DatePatterns(
        full = "EEEE, d MMMM y 'г'.",
        long = "d MMMM y 'г'.",
        medium = "d MMM y 'г'.",
        short = "dd.MM.y"
    ),
    "sk" to DatePatterns(
        full = "EEEE d. MMMM y",
        long = "d. MMMM y",
        medium = "d. M. y",
        short = "d. M. y"
    ),
    "sl" to DatePatterns(
        full = "EEEE, d. MMMM y",
        long = "d. MMMM y",
        medium = "d. MMM y",
        short = "d. M. y"
    ),
    "sq" to DatePatterns(
        full = "EEEE, d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "d.M.yy"
    ),
    "sr" to DatePatterns(
        full = "EEEE, d. MMMM y.",
        long = "d. MMMM y.",
        medium = "d. M. y.",
        short = "d. M. y."
    ),
    "sv" to DatePatterns(
        full = "EEEE d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "y-MM-dd"
    ),
    "sw" to DatePatterns(
        full = "EEEE, d MMMM y",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "dd/MM/y"
    ),
    "th" to DatePatterns(
        full = "EEEEที่ d MMMM G y",
        long = "d MMMM G y",
        medium = "d MMM y",
        short = "d/M/yy"
    ),
    "tr" to DatePatterns(
        full = "d MMMM y EEEE",
        long = "d MMMM y",
        medium = "d MMM y",
        short = "d.MM.y"
    ),
    "uk" to DatePatterns(
        full = "EEEE, d MMMM y 'р'.",
        long = "d MMMM y 'р'.",
        medium = "d MMM y 'р'.",
        short = "dd.MM.yy"
    ),
    "uz" to DatePatterns(
        full = "EEEE, d-MMMM, y",
        long = "d-MMMM, y",
        medium = "d-MMM, y",
        short = "dd/MM/yy"
    ),
    "vi" to DatePatterns(
        full = "EEEE, d MMMM, y",
        long = "d MMMM, y",
        medium = "d MMM, y",
        short = "d/M/yy"
    ),
    "zh" to DatePatterns(
        full = "y年M月d日EEEE",
        long = "y年M月d日",
        medium = "y年M月d日",
        short = "y/M/d"
    ),
)
