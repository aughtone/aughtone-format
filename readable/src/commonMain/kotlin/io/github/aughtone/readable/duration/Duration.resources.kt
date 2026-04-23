package io.github.aughtone.readable.duration

import io.github.aughtone.toolbox.Formatter
import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong

/** Functional formatter for Durations. */
typealias DurationFormatter = Formatter<kotlin.time.Duration>

private data class DurationUnitNames(val singular: String, val plural: String)

private fun u(
    s: String, m: String, h: String, d: String, w: String, mo: String, y: String,
    sP: String = s, mP: String = m, hP: String = h, dP: String = d,
    wP: String = w, moP: String = mo, yP: String = y,
) = mapOf(
    "second" to DurationUnitNames(s, sP), "minute" to DurationUnitNames(m, mP),
    "hour"   to DurationUnitNames(h, hP), "day"    to DurationUnitNames(d, dP),
    "week"   to DurationUnitNames(w, wP), "month"  to DurationUnitNames(mo, moP),
    "year"   to DurationUnitNames(y, yP),
)

private fun buildFormatter(units: Map<String, DurationUnitNames>): DurationFormatter = { duration ->
    val totalSeconds = abs(duration.toDouble(kotlin.time.DurationUnit.SECONDS))
    val (value, unitKey) = when {
        totalSeconds >= 31536000 -> (totalSeconds / 31536000.0) to "year"
        totalSeconds >= 2592000  -> (totalSeconds / 2592000.0)  to "month"
        totalSeconds >= 604800 && (totalSeconds / 604800.0).roundToLong() <= 3 -> (totalSeconds / 604800.0) to "week"
        totalSeconds >= 86400   -> (totalSeconds / 86400.0)   to "day"
        totalSeconds >= 3600    -> (totalSeconds / 3600.0)    to "hour"
        totalSeconds >= 60      -> (totalSeconds / 60.0)      to "minute"
        else                    -> totalSeconds               to "second"
    }
    val n = value.roundToLong()
    val names = units[unitKey] ?: DurationUnitNames(unitKey, "${unitKey}s")
    val label = if (n == 1L) names.singular else names.plural
    "$n $label"
}

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun buildDurationFormatter(code: String): DurationFormatter? = when (code) {
    // Germanic
    "en", "af", "nl", "id", "ms", "sw" ->
        buildFormatter(u("second","minute","hour","day","week","month","year",
                         "seconds","minutes","hours","days","weeks","months","years"))
    "de" ->
        buildFormatter(u("Sekunde","Minute","Stunde","Tag","Woche","Monat","Jahr",
                         "Sekunden","Minuten","Stunden","Tage","Wochen","Monate","Jahre"))
    "da" ->
        buildFormatter(u("sekund","minut","time","dag","uge","måned","år",
                         "sekunder","minutter","timer","dage","uger","måneder","år"))
    "nb", "no" ->
        buildFormatter(u("sekund","minutt","time","dag","uke","måned","år",
                         "sekunder","minutter","timer","dager","uker","måneder","år"))
    "nn" ->
        buildFormatter(u("sekund","minutt","time","dag","veke","månad","år",
                         "sekunder","minutt","timar","dagar","veker","månader","år"))
    "sv" ->
        buildFormatter(u("sekund","minut","timme","dag","vecka","månad","år",
                         "sekunder","minuter","timmar","dagar","veckor","månader","år"))
    "is" ->
        buildFormatter(u("sekúnda","mínúta","klukkustund","dagur","vika","mánuður","ár",
                         "sekúndur","mínútur","klukkustundir","dagar","vikur","mánuðir","ár"))

    // Romance
    "fr" ->
        buildFormatter(u("seconde","minute","heure","jour","semaine","mois","an",
                         "secondes","minutes","heures","jours","semaines","mois","ans"))
    "es", "gl" ->
        buildFormatter(u("segundo","minuto","hora","día","semana","mes","año",
                         "segundos","minutos","horas","días","semanas","meses","años"))
    "it" ->
        buildFormatter(u("secondo","minuto","ora","giorno","settimana","mese","anno",
                         "secondi","minuti","ore","giorni","settimane","mesi","anni"))
    "pt" ->
        buildFormatter(u("segundo","minuto","hora","dia","semana","mês","ano",
                         "segundos","minutos","horas","dias","semanas","meses","anos"))
    "ca" ->
        buildFormatter(u("segon","minut","hora","dia","setmana","mes","any",
                         "segons","minuts","hores","dies","setmanes","mesos","anys"))
    "ro" ->
        buildFormatter(u("secundă","minut","oră","zi","săptămână","lună","an",
                         "secunde","minute","ore","zile","săptămâni","luni","ani"))

    // Slavic / Baltic
    "ru" ->
        buildFormatter(u("секунда","минута","час","день","неделя","месяц","год",
                         "секунды","минут","часов","дней","недель","месяцев","лет"))
    "uk" ->
        buildFormatter(u("секунда","хвилина","година","день","тиждень","місяць","рік",
                         "секунди","хвилин","годин","днів","тижнів","місяців","років"))
    "be" ->
        buildFormatter(u("секунда","хвіліна","гадзіна","дзень","тыдзень","месяц","год",
                         "секунды","хвілін","гадзін","дзён","тыдняў","месяцаў","гадоў"))
    "bg" ->
        buildFormatter(u("секунда","минута","час","ден","седмица","месец","година",
                         "секунди","минути","часа","дни","седмици","месеца","години"))
    "pl" ->
        buildFormatter(u("sekunda","minuta","godzina","dzień","tydzień","miesiąc","rok",
                         "sekundy","minut","godzin","dni","tygodni","miesięcy","lat"))
    "cs" ->
        buildFormatter(u("sekunda","minuta","hodina","den","týden","měsíc","rok",
                         "sekundy","minut","hodin","dní","týdnů","měsíců","let"))
    "sk" ->
        buildFormatter(u("sekunda","minúta","hodina","deň","týždeň","mesiac","rok",
                         "sekundy","minút","hodín","dní","týždňov","mesiacov","rokov"))
    "hr" ->
        buildFormatter(u("sekunda","minuta","sat","dan","tjedan","mjesec","godina",
                         "sekunde","minuta","sati","dana","tjedana","mjeseci","godina"))
    "sr" ->
        buildFormatter(u("секунда","минут","сат","дан","недеља","месец","година",
                         "секунде","минута","сати","дана","недеља","месеци","година"))
    "mk" ->
        buildFormatter(u("секунда","минута","час","ден","недела","месец","година",
                         "секунди","минути","часови","дена","недели","месеци","години"))
    "sl" ->
        buildFormatter(u("sekunda","minuta","ura","dan","teden","mesec","leto",
                         "sekunde","minut","ur","dni","tednov","mesecev","let"))
    "lt" ->
        buildFormatter(u("sekundė","minutė","valanda","diena","savaitė","mėnuo","metai",
                         "sekundžių","minučių","valandų","dienų","savaičių","mėnesių","metų"))
    "lv" ->
        buildFormatter(u("sekunde","minūte","stunda","diena","nedēļa","mēnesis","gads",
                         "sekundēm","minūtēm","stundām","dienām","nedēļām","mēnešiem","gadiem"))
    "sq" ->
        buildFormatter(u("sekondë","minutë","orë","ditë","javë","muaj","vit",
                         "sekonda","minuta","orë","ditë","javë","muaj","vite"))

    // East Asian / SE Asian
    "ja" -> buildFormatter(u("秒","分","時間","日","週間","ヶ月","年"))
    "ko" -> buildFormatter(u("초","분","시간","일","주","달","년"))
    "zh-TW" -> buildFormatter(u("秒","分","小時","天","週","月","年"))  // Traditional Chinese
    "zh" -> buildFormatter(u("秒","分","小时","天","周","月","年"))      // Simplified Chinese
    "th" -> buildFormatter(u("วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี"))
    "vi" -> buildFormatter(u("giây","phút","giờ","ngày","tuần","tháng","năm"))

    // South / Central Asian / Middle Eastern
    "hi" ->
        buildFormatter(u("सेकंड","मिनट","घंटा","दिन","सप्ताह","महीना","वर्ष",
                         "सेकंड","मिनट","घंटे","दिन","सप्ताह","महीने","वर्ष"))
    "ar" ->
        buildFormatter(u("ثانية","دقيقة","ساعة","يوم","أسبوع","شهر","سنة",
                         "ثوانٍ","دقائق","ساعات","أيام","أسابيع","شهور","سنوات"))
    "fa" -> buildFormatter(u("ثانیه","دقیقه","ساعت","روز","هفته","ماه","سال"))

    // Turkic
    "tr", "az" -> buildFormatter(u("saniye","dakika","saat","gün","hafta","ay","yıl"))
    "uz" -> buildFormatter(u("soniya","daqiqa","soat","kun","hafta","oy","yil"))
    "kk" -> buildFormatter(u("секунд","минут","сағат","күн","апта","ай","жыл"))

    // Finno-Ugric / Other
    "fi" ->
        buildFormatter(u("sekunti","minuutti","tunti","päivä","viikko","kuukausi","vuosi",
                         "sekuntia","minuuttia","tuntia","päivää","viikkoa","kuukautta","vuotta"))
    "et" ->
        buildFormatter(u("sekund","minut","tund","päev","nädal","kuu","aasta",
                         "sekundit","minutit","tundi","päeva","nädalat","kuud","aastat"))
    "hu" -> buildFormatter(u("másodperc","perc","óra","nap","hét","hónap","év"))
    "el" ->
        buildFormatter(u("δευτερόλεπτο","λεπτό","ώρα","ημέρα","εβδομάδα","μήνας","έτος",
                         "δευτερόλεπτα","λεπτά","ώρες","ημέρες","εβδομάδες","μήνες","έτη"))
    "eu" -> buildFormatter(u("segundo","minutu","ordu","egun","aste","hilabete","urte"))

    else -> null
}

// ── Lazy cache ────────────────────────────────────────────────────────────────

private val durationCache = mutableMapOf<String, DurationFormatter>()

/**
 * Retrieves the [DurationFormatter] for a given [Locale], building and caching it on first use.
 * Supports full BCP 47 subtag lookup (e.g. "zh-TW") before falling back to the base language.
 */
fun durationFormatterFor(locale: Locale): DurationFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    return durationCache.getOrPut(fullTag) {
        var currentTag = fullTag
        var formatter: DurationFormatter? = null
        while (currentTag.isNotEmpty()) {
            formatter = buildDurationFormatter(currentTag)
            if (formatter != null) break
            currentTag = currentTag.substringBeforeLast('-', "")
        }
        formatter ?: buildDurationFormatter("en")!!
    }
}
