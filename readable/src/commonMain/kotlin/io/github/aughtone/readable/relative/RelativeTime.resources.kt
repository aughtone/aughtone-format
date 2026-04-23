package io.github.aughtone.readable.relative

import io.github.aughtone.toolbox.Formatter
import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration
import kotlin.time.DurationUnit

/** Functional formatter for relative time. Takes a signed [Duration] (negative = past). */
typealias RelativeTimeFormatter = Formatter<Duration>

private data class RelativeTimeUnitNames(val singular: String, val plural: String)

/** Holds both the formatter lambda and the "just now" string for a locale. */
internal data class RelativeTimeConfig(val formatter: RelativeTimeFormatter, val nowString: String)

// ── Factory ───────────────────────────────────────────────────────────────────

private fun u(
    s: String, m: String, h: String, d: String, w: String, mo: String, y: String,
    sP: String = s, mP: String = m, hP: String = h, dP: String = d,
    wP: String = w, moP: String = mo, yP: String = y,
) = mapOf(
    "second" to RelativeTimeUnitNames(s, sP), "minute" to RelativeTimeUnitNames(m, mP),
    "hour"   to RelativeTimeUnitNames(h, hP), "day"    to RelativeTimeUnitNames(d, dP),
    "week"   to RelativeTimeUnitNames(w, wP), "month"  to RelativeTimeUnitNames(mo, moP),
    "year"   to RelativeTimeUnitNames(y, yP),
)

private fun config(
    past: String,
    future: String,
    nowString: String,
    units: Map<String, RelativeTimeUnitNames>,
    sep: String = " ",
): RelativeTimeConfig {
    val formatter: RelativeTimeFormatter = { delta ->
        val totalSeconds = delta.toDouble(DurationUnit.SECONDS)
        val absSeconds = abs(totalSeconds)
        val isPast = totalSeconds < 0

        val (value, unitKey) = when {
            absSeconds >= 31536000 -> (absSeconds / 31536000.0) to "year"
            absSeconds >= 2592000  -> (absSeconds / 2592000.0)  to "month"
            absSeconds >= 604800 && (absSeconds / 604800.0).roundToLong() <= 3 -> (absSeconds / 604800.0) to "week"
            absSeconds >= 86400   -> (absSeconds / 86400.0)   to "day"
            absSeconds >= 3600    -> (absSeconds / 3600.0)    to "hour"
            absSeconds >= 60      -> (absSeconds / 60.0)      to "minute"
            else                  -> absSeconds               to "second"
        }

        val n = value.roundToLong()
        val names = units[unitKey] ?: RelativeTimeUnitNames(unitKey, "${unitKey}s")
        val label = if (n == 1L) names.singular else names.plural
        val unitStr = "$n$sep$label"
        if (isPast) past.replace("{0}", unitStr) else future.replace("{0}", unitStr)
    }
    return RelativeTimeConfig(formatter, nowString)
}

// ── On-demand locale factory ──────────────────────────────────────────────────
// Unit maps and configs are only allocated when a locale is first requested.

private fun buildConfig(code: String): RelativeTimeConfig? = when (code) {
    // ── English / Germanic ────────────────────────────────────────────────────
    // en-ZA: "just now" means later in SA English; "now now" = right this moment
    "en-ZA" -> config("{0} ago", "in {0}", "now now",
        u("second","minute","hour","day","week","month","year",
          "seconds","minutes","hours","days","weeks","months","years"))
    "en" -> config("{0} ago", "in {0}", "just now",
        u("second","minute","hour","day","week","month","year",
          "seconds","minutes","hours","days","weeks","months","years"))

    "af" -> config("{0} gelede", "oor {0}", "nou net",
        u("sekonde","minuut","uur","dag","week","maand","jaar",
          "sekondes","minute","ure","dae","weke","maande","jaar"))

    "nl" -> config("{0} geleden", "over {0}", "zojuist",
        u("seconde","minuut","uur","dag","week","maand","jaar",
          "seconden","minuten","uren","dagen","weken","maanden","jaren"))

    "de" -> config("vor {0}", "in {0}", "gerade eben",
        u("Sekunde","Minute","Stunde","Tag","Woche","Monat","Jahr",
          "Sekunden","Minuten","Stunden","Tage","Wochen","Monate","Jahre"))

    "da" -> config("for {0} siden", "om {0}", "lige nu",
        u("sekund","minut","time","dag","uge","måned","år",
          "sekunder","minutter","timer","dage","uger","måneder","år"))

    "nb", "no" -> config("for {0} siden", "om {0}", "akkurat nå",
        u("sekund","minutt","time","dag","uke","måned","år",
          "sekunder","minutter","timer","dager","uker","måneder","år"))

    "nn" -> config("for {0} sidan", "om {0}", "akkurat no",
        u("sekund","minutt","time","dag","veke","månad","år",
          "sekunder","minutt","timar","dagar","veker","månader","år"))

    "sv" -> config("för {0} sedan", "om {0}", "just nu",
        u("sekund","minut","timme","dag","vecka","månad","år",
          "sekunder","minuter","timmar","dagar","veckor","månader","år"))

    "is" -> config("fyrir {0}", "eftir {0}", "rétt núna",
        u("sekúnda","mínúta","klukkustund","dagur","vika","mánuður","ár",
          "sekúndur","mínútur","klukkustundir","dagar","vikur","mánuðir","ár"))

    // ── Romance ───────────────────────────────────────────────────────────────
    "fr" -> config("il y a {0}", "dans {0}", "à l'instant",
        u("seconde","minute","heure","jour","semaine","mois","an",
          "secondes","minutes","heures","jours","semaines","mois","ans"))

    "es" -> config("hace {0}", "en {0}", "ahora mismo",
        u("segundo","minuto","hora","día","semana","mes","año",
          "segundos","minutos","horas","días","semanas","meses","años"))

    "it" -> config("{0} fa", "tra {0}", "proprio ora",
        u("secondo","minuto","ora","giorno","settimana","mese","anno",
          "secondi","minuti","ore","giorni","settimane","mesi","anni"))

    "pt" -> config("há {0}", "em {0}", "agora mesmo",
        u("segundo","minuto","hora","dia","semana","mês","ano",
          "segundos","minutos","horas","dias","semanas","meses","anos"))

    "ca" -> config("fa {0}", "d'aquí a {0}", "ara mateix",
        u("segon","minut","hora","dia","setmana","mes","any",
          "segons","minuts","hores","dies","setmanes","mesos","anys"))

    "gl" -> config("hai {0}", "en {0}", "agora mesmo",
        u("segundo","minuto","hora","día","semana","mes","ano",
          "segundos","minutos","horas","días","semanas","meses","anos"))

    "ro" -> config("acum {0}", "în {0}", "tocmai acum",
        u("secundă","minut","oră","zi","săptămână","lună","an",
          "secunde","minute","ore","zile","săptămâni","luni","ani"))

    // ── Slavic / Baltic ───────────────────────────────────────────────────────
    "ru" -> config("{0} назад", "через {0}", "только что",
        u("секунда","минута","час","день","неделя","месяц","год",
          "секунды","минут","часов","дней","недель","месяцев","лет"))

    "uk" -> config("{0} тому", "через {0}", "щойно",
        u("секунда","хвилина","година","день","тиждень","місяць","рік",
          "секунди","хвилин","годин","днів","тижнів","місяців","років"))

    "be" -> config("{0} таму", "праз {0}", "толькі што",
        u("секунда","хвіліна","гадзіна","дзень","тыдзень","месяц","год",
          "секунды","хвілін","гадзін","дзён","тыдняў","месяцаў","гадоў"))

    "bg" -> config("преди {0}", "след {0}", "точно сега",
        u("секунда","минута","час","ден","седмица","месец","година",
          "секунди","минути","часа","дни","седмици","месеца","години"))

    "pl" -> config("{0} temu", "za {0}", "właśnie teraz",
        u("sekunda","minuta","godzina","dzień","tydzień","miesiąc","rok",
          "sekundy","minut","godzin","dni","tygodni","miesięcy","lat"))

    "cs" -> config("před {0}", "za {0}", "právě teď",
        u("sekunda","minuta","hodina","den","týden","měsíc","rok",
          "sekundy","minut","hodin","dní","týdnů","měsíců","let"))

    "sk" -> config("pred {0}", "za {0}", "práve teraz",
        u("sekunda","minúta","hodina","deň","týždeň","mesiac","rok",
          "sekundy","minút","hodín","dní","týždňov","mesiacov","rokov"))

    "hr" -> config("prije {0}", "za {0}", "upravo sada",
        u("sekunda","minuta","sat","dan","tjedan","mjesec","godina",
          "sekunde","minuta","sati","dana","tjedana","mjeseci","godina"))

    "sr" -> config("пре {0}", "за {0}", "управо сада",
        u("секунда","минут","сат","дан","недеља","месец","година",
          "секунде","минута","сати","дана","недеља","месеци","година"))

    "mk" -> config("пред {0}", "за {0}", "токму сега",
        u("секунда","минута","час","ден","недела","месец","година",
          "секунди","минути","часови","дена","недели","месеци","години"))

    "sl" -> config("pred {0}", "čez {0}", "ravno zdaj",
        u("sekunda","minuta","ura","dan","teden","mesec","leto",
          "sekunde","minut","ur","dni","tednov","mesecev","let"))

    "lt" -> config("prieš {0}", "po {0}", "ką tik",
        u("sekundė","minutė","valanda","diena","savaitė","mėnuo","metai",
          "sekundžių","minučių","valandų","dienų","savaičių","mėnesių","metų"))

    "lv" -> config("pirms {0}", "pēc {0}", "tikko",
        u("sekunde","minūte","stunda","diena","nedēļa","mēnesis","gads",
          "sekundēm","minūtēm","stundām","dienām","nedēļām","mēnešiem","gadiem"))

    "sq" -> config("{0} më parë", "pas {0}", "tani",
        u("sekondë","minutë","orë","ditë","javë","muaj","vit",
          "sekonda","minuta","orë","ditë","javë","muaj","vite"))

    // ── East Asian / SE Asian (no unit separator) ─────────────────────────────
    "ja" -> config("{0}前", "{0}後", "たった今",
        u("秒","分","時間","日","週間","ヶ月","年"), sep = "")

    "zh" -> config("{0}前", "{0}后", "刚刚",
        u("秒","分","小时","天","周","月","年"), sep = "")

    "ko" -> config("{0} 전", "{0} 후", "방금",
        u("초","분","시간","일","주","달","년"), sep = "")

    "th" -> config("{0}ที่แล้ว", "อีก {0}", "เมื่อกี้",
        u("วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี"), sep = "")

    "vi" -> config("{0} trước", "{0} nữa", "vừa xong",
        u("giây","phút","giờ","ngày","tuần","tháng","năm"))

    // ── South / Central Asian ─────────────────────────────────────────────────
    "hi" -> config("{0} पहले", "{0} बाद", "अभी",
        u("सेकंड","मिनट","घंटा","दिन","सप्ताह","महीना","वर्ष",
          "सेकंड","मिनट","घंटे","दिन","सप्ताह","महीने","वर्ष"))

    "id" -> config("{0} yang lalu", "dalam {0}", "baru saja",
        u("detik","menit","jam","hari","minggu","bulan","tahun"))

    "ms" -> config("{0} yang lalu", "dalam {0}", "baru sahaja",
        u("saat","minit","jam","hari","minggu","bulan","tahun"))

    "sw" -> config("{0} iliyopita", "baada ya {0}", "sasa hivi",
        u("sekunde","dakika","saa","siku","wiki","mwezi","mwaka",
          "sekunde","dakika","saa","siku","wiki","miezi","miaka"))

    // ── Middle Eastern ────────────────────────────────────────────────────────
    "ar" -> config("منذ {0}", "خلال {0}", "الآن",
        u("ثانية","دقيقة","ساعة","يوم","أسبوع","شهر","سنة",
          "ثوانٍ","دقائق","ساعات","أيام","أسابيع","شهور","سنوات"))

    "fa" -> config("{0} پیش", "{0} دیگر", "همین الان",
        u("ثانیه","دقیقه","ساعت","روز","هفته","ماه","سال"))

    // ── Turkic ────────────────────────────────────────────────────────────────
    "tr" -> config("{0} önce", "{0} sonra", "şimdi",
        u("saniye","dakika","saat","gün","hafta","ay","yıl"))

    "az" -> config("{0} əvvəl", "{0} sonra", "indi",
        u("saniyə","dəqiqə","saat","gün","həftə","ay","il"))

    "uz" -> config("{0} oldin", "{0} keyin", "hozir",
        u("soniya","daqiqa","soat","kun","hafta","oy","yil"))

    "kk" -> config("{0} бұрын", "{0} кейін", "қазір",
        u("секунд","минут","сағат","күн","апта","ай","жыл"))

    // ── Finno-Ugric ───────────────────────────────────────────────────────────
    "fi" -> config("{0} sitten", "{0} päästä", "juuri nyt",
        u("sekunti","minuutti","tunti","päivä","viikko","kuukausi","vuosi",
          "sekuntia","minuuttia","tuntia","päivää","viikkoa","kuukautta","vuotta"))

    "et" -> config("{0} tagasi", "{0} pärast", "just nüüd",
        u("sekund","minut","tund","päev","nädal","kuu","aasta",
          "sekundit","minutit","tundi","päeva","nädalat","kuud","aastat"))

    "hu" -> config("{0} ezelőtt", "{0} múlva", "éppen most",
        u("másodperc","perc","óra","nap","hét","hónap","év"))

    // ── Other ─────────────────────────────────────────────────────────────────
    "el" -> config("πριν {0}", "σε {0}", "μόλις τώρα",
        u("δευτερόλεπτο","λεπτό","ώρα","ημέρα","εβδομάδα","μήνας","έτος",
          "δευτερόλεπτα","λεπτά","ώρες","ημέρες","εβδομάδες","μήνες","έτη"))

    "eu" -> config("duela {0}", "{0} barru", "orain bertan",
        u("segundo","minutu","ordu","egun","aste","hilabete","urte"))

    else -> null
}

// ── Lazy cache ────────────────────────────────────────────────────────────────

private val configCache = mutableMapOf<String, RelativeTimeConfig>()

/**
 * Returns the [RelativeTimeConfig] for [locale], building and caching it on first use.
 * Supports full BCP 47 subtag fallback: e.g. "en-ZA" → "en" → "en" default.
 */
internal fun relativeTimeConfigFor(locale: Locale): RelativeTimeConfig {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = fullTag
    while (currentTag.isNotEmpty()) {
        val cached = configCache[currentTag]
        if (cached != null) return cached
        val built = buildConfig(currentTag)
        if (built != null) {
            configCache[currentTag] = built
            return built
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return configCache.getOrPut("en") { buildConfig("en")!! }
}
