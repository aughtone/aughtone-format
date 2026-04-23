package io.github.aughtone.readable.relative

import io.github.aughtone.readable.PluralCategory
import io.github.aughtone.readable.pluralCategoryFor
import io.github.aughtone.toolbox.Formatter
import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration
import kotlin.time.DurationUnit

/** Functional formatter for relative time. Takes a signed [Duration] (negative = past). */
typealias RelativeTimeFormatter = Formatter<Duration>

private class RelativeTimeUnitNames(val forms: Map<PluralCategory, String>) {
    fun get(category: PluralCategory): String = forms[category] ?: forms[PluralCategory.Other] ?: forms.values.first()
}

/** Holds both the formatter lambda and the "just now" string for a locale. */
internal data class RelativeTimeConfig(val formatter: RelativeTimeFormatter, val nowString: String)

// ── Factories ─────────────────────────────────────────────────────────────────

/** Simple 2-form factory for languages with only Singular/Plural. */
private fun u2(
    s1: String, m1: String, h1: String, d1: String, w1: String, mo1: String, y1: String,
    s2: String, m2: String, h2: String, d2: String, w2: String, mo2: String, y2: String
) = mapOf(
    "second" to RelativeTimeUnitNames(mapOf(PluralCategory.One to s1, PluralCategory.Other to s2)),
    "minute" to RelativeTimeUnitNames(mapOf(PluralCategory.One to m1, PluralCategory.Other to m2)),
    "hour"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to h1, PluralCategory.Other to h2)),
    "day"    to RelativeTimeUnitNames(mapOf(PluralCategory.One to d1, PluralCategory.Other to d2)),
    "week"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to w1, PluralCategory.Other to w2)),
    "month"  to RelativeTimeUnitNames(mapOf(PluralCategory.One to mo1, PluralCategory.Other to mo2)),
    "year"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to y1, PluralCategory.Other to y2)),
)

/** 3-form factory for Slavic languages (One, Few, Many). */
private fun u3(
    s1: String, m1: String, h1: String, d1: String, w1: String, mo1: String, y1: String,
    sF: String, mF: String, hF: String, dF: String, wF: String, moF: String, yF: String,
    sM: String, mM: String, hM: String, dM: String, wM: String, moM: String, yM: String
) = mapOf(
    "second" to RelativeTimeUnitNames(mapOf(PluralCategory.One to s1, PluralCategory.Few to sF, PluralCategory.Many to sM)),
    "minute" to RelativeTimeUnitNames(mapOf(PluralCategory.One to m1, PluralCategory.Few to mF, PluralCategory.Many to mM)),
    "hour"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to h1, PluralCategory.Few to hF, PluralCategory.Many to hM)),
    "day"    to RelativeTimeUnitNames(mapOf(PluralCategory.One to d1, PluralCategory.Few to dF, PluralCategory.Many to dM)),
    "week"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to w1, PluralCategory.Few to wF, PluralCategory.Many to wM)),
    "month"  to RelativeTimeUnitNames(mapOf(PluralCategory.One to mo1, PluralCategory.Few to moF, PluralCategory.Many to moM)),
    "year"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to y1, PluralCategory.Few to yF, PluralCategory.Many to yM)),
)

/** 4-form factory for Hebrew / Inuktitut (One, Two, Many, Other). */
private fun u4(
    s1: String, s2: String, sM: String, sO: String,
    m1: String, m2: String, mM: String, mO: String,
    h1: String, h2: String, hM: String, hO: String,
    d1: String, d2: String, dM: String, dO: String,
    w1: String, w2: String, wM: String, wO: String,
    mo1: String, mo2: String, moM: String, moO: String,
    y1: String, y2: String, yM: String, yO: String,
) = mapOf(
    "second" to RelativeTimeUnitNames(mapOf(PluralCategory.One to s1, PluralCategory.Two to s2, PluralCategory.Many to sM, PluralCategory.Other to sO)),
    "minute" to RelativeTimeUnitNames(mapOf(PluralCategory.One to m1, PluralCategory.Two to m2, PluralCategory.Many to mM, PluralCategory.Other to mO)),
    "hour"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to h1, PluralCategory.Two to h2, PluralCategory.Many to hM, PluralCategory.Other to hO)),
    "day"    to RelativeTimeUnitNames(mapOf(PluralCategory.One to d1, PluralCategory.Two to d2, PluralCategory.Many to dM, PluralCategory.Other to dO)),
    "week"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to w1, PluralCategory.Two to w2, PluralCategory.Many to wM, PluralCategory.Other to wO)),
    "month"  to RelativeTimeUnitNames(mapOf(PluralCategory.One to mo1, PluralCategory.Two to mo2, PluralCategory.Many to moM, PluralCategory.Other to moO)),
    "year"   to RelativeTimeUnitNames(mapOf(PluralCategory.One to y1, PluralCategory.Two to y2, PluralCategory.Many to yM, PluralCategory.Other to yO)),
)

/** 6-form factory for Arabic (Zero, One, Two, Few, Many, Other). */
private fun u6(
    sZ: String, s1: String, s2: String, sF: String, sM: String, sO: String,
    mZ: String, m1: String, m2: String, mF: String, mM: String, mO: String,
    hZ: String, h1: String, h2: String, hF: String, hM: String, hO: String,
    dZ: String, d1: String, d2: String, dF: String, dM: String, dO: String,
    wZ: String, w1: String, w2: String, wF: String, wM: String, wO: String,
    moZ: String, mo1: String, mo2: String, moF: String, moM: String, moO: String,
    yZ: String, y1: String, y2: String, yF: String, yM: String, yO: String,
) = mapOf(
    "second" to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to sZ, PluralCategory.One to s1, PluralCategory.Two to s2, PluralCategory.Few to sF, PluralCategory.Many to sM, PluralCategory.Other to sO)),
    "minute" to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to mZ, PluralCategory.One to m1, PluralCategory.Two to m2, PluralCategory.Few to mF, PluralCategory.Many to mM, PluralCategory.Other to mO)),
    "hour"   to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to hZ, PluralCategory.One to h1, PluralCategory.Two to h2, PluralCategory.Few to hF, PluralCategory.Many to hM, PluralCategory.Other to hO)),
    "day"    to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to dZ, PluralCategory.One to d1, PluralCategory.Two to d2, PluralCategory.Few to dF, PluralCategory.Many to dM, PluralCategory.Other to dO)),
    "week"   to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to wZ, PluralCategory.One to w1, PluralCategory.Two to w2, PluralCategory.Few to wF, PluralCategory.Many to wM, PluralCategory.Other to wO)),
    "month"  to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to moZ, PluralCategory.One to mo1, PluralCategory.Two to mo2, PluralCategory.Few to moF, PluralCategory.Many to moM, PluralCategory.Other to moO)),
    "year"   to RelativeTimeUnitNames(mapOf(PluralCategory.Zero to yZ, PluralCategory.One to y1, PluralCategory.Two to y2, PluralCategory.Few to yF, PluralCategory.Many to yM, PluralCategory.Other to yO)),
)

private fun config(
    locale: Locale,
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
        val category = pluralCategoryFor(locale, n)
        val names = units[unitKey]!!
        val label = names.get(category)
        val unitStr = "$n$sep$label"
        if (isPast) past.replace("{0}", unitStr) else future.replace("{0}", unitStr)
    }
    return RelativeTimeConfig(formatter, nowString)
}

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun buildConfig(tag: String, locale: Locale): RelativeTimeConfig? {
    return when (tag) {
        // ── English / Germanic ────────────────────────────────────────────────
        "en-ZA" -> config(locale, "{0} ago", "in {0}", "now now",
            u2("second","minute","hour","day","week","month","year",
               "seconds","minutes","hours","days","weeks","months","years"))
        "en" -> config(locale, "{0} ago", "in {0}", "just now",
            u2("second","minute","hour","day","week","month","year",
               "seconds","minutes","hours","days","weeks","months","years"))

        "af" -> config(locale, "{0} gelede", "oor {0}", "nou net",
            u2("sekonde","minuut","uur","dag","week","maand","jaar",
               "sekondes","minute","ure","dae","weke","maande","jaar"))

        "nl" -> config(locale, "{0} geleden", "over {0}", "zojuist",
            u2("seconde","minuut","uur","dag","week","maand","jaar",
               "seconden","minuten","uren","dagen","weken","maanden","jaren"))

        "de" -> config(locale, "vor {0}", "in {0}", "gerade eben",
            u2("Sekunde","Minute","Stunde","Tag","Woche","Monat","Jahr",
               "Sekunden","Minuten","Stunden","Tage","Wochen","Monate","Jahre"))

        "da" -> config(locale, "for {0} siden", "om {0}", "lige nu",
            u2("sekund","minut","time","dag","uge","måned","år",
               "sekunder","minutter","timer","dage","uger","måneder","år"))

        "nb", "no" -> config(locale, "for {0} siden", "om {0}", "akkurat nå",
            u2("sekund","minutt","time","dag","uke","måned","år",
               "sekunder","minutter","timer","dager","uker","måneder","år"))

        "nn" -> config(locale, "for {0} sidan", "om {0}", "akkurat no",
            u2("sekund","minutt","time","dag","veke","månad","år",
               "sekunder","minutt","timar","dagar","veker","månader","år"))

        "sv" -> config(locale, "för {0} sedan", "om {0}", "just nu",
            u2("sekund","minut","timme","dag","vecka","månad","år",
               "sekunder","minuter","timmar","dagar","veckor","månader","år"))

        "is" -> config(locale, "fyrir {0}", "eftir {0}", "rétt núna",
            u2("sekúnda","mínúta","klukkustund","dagur","vika","mánuður","ár",
               "sekúndur","mínútur","klukkustundir","dagar","vikur","mánuðir","ár"))

        // ── Romance ───────────────────────────────────────────────────────────
        "fr" -> config(locale, "il y a {0}", "dans {0}", "à l'instant",
            u2("seconde","minute","heure","jour","semaine","mois","an",
               "secondes","minutes","heures","jours","semaines","mois","ans"))

        "es" -> config(locale, "hace {0}", "en {0}", "ahora mismo",
            u2("segundo","minuto","hora","día","semana","mes","año",
               "segundos","minutos","horas","días","semanas","meses","años"))

        "it" -> config(locale, "{0} fa", "tra {0}", "proprio ora",
            u2("secondo","minuto","ora","giorno","settimana","mese","anno",
               "secondi","minuti","ore","giorni","settimane","mesi","anni"))

        "pt" -> config(locale, "há {0}", "em {0}", "agora mesmo",
            u2("segundo","minuto","hora","dia","semana","mês","ano",
               "segundos","minutos","horas","dias","semanas","meses","anos"))

        "ca" -> config(locale, "fa {0}", "d'aquí a {0}", "ara mateix",
            u2("segon","minut","hora","dia","setmana","mes","any",
               "segons","minuts","hores","dies","setmanes","mesos","anys"))

        "gl" -> config(locale, "hai {0}", "en {0}", "agora mesmo",
            u2("segundo","minuto","hora","día","semana","mes","ano",
               "segundos","minutos","horas","días","semanas","meses","anos"))

        "ro" -> config(locale, "acum {0}", "în {0}", "tocmai acum",
            u2("secundă","minut","oră","zi","săptămână","lună","an",
               "secunde","minute","ore","zile","săptămâni","luni","ani"))

        // ── Slavic / Baltic (Complex plurals) ─────────────────────────────────
        "ru" -> config(locale, "{0} назад", "через {0}", "только что",
            u3("секунда","минута","час","день","неделя","месяц","год",
               "секунды","минуты","часа","дня","недели","месяца","года",
               "секунд","минут","часов","дней","недель","месяцев","лет"))

        "uk" -> config(locale, "{0} тому", "через {0}", "щойно",
            u3("секунда","хвилина","година","день","тиждень","місяць","рік",
               "секунди","хвилини","години","дні","тижні","місяці","роки",
               "секунд","хвилин","годин","днів","тижнів","місяців","років"))

        "be" -> config(locale, "{0} таму", "праз {0}", "толькі што",
            u3("секунда","хвіліна","гадзіна","дзень","тыдзень","месяц","год",
               "секунды","хвіліны","гадзіны","дні","тижні","месяцы","гады",
               "секунд","хвілін","гадзін","дзён","тыдняў","месяцаў","гадоў"))

        "bg" -> config(locale, "преди {0}", "след {0}", "точно сега",
            u2("секунда","минута","час","ден","седмица","месец","година",
               "секунди","минути","часа","дни","седмици","месеца","години"))

        "pl" -> config(locale, "{0} temu", "za {0}", "właśnie teraz",
            u3("sekunda","minuta","godzina","dzień","tydzień","miesiąc","rok",
               "sekundy","minuty","godziny","dni","tygodnie","miesiące","lata",
               "sekund","minut","godzin","dni","tygodni","miesięcy","lat"))

        "cs" -> config(locale, "před {0}", "za {0}", "právě teď",
            u3("sekunda","minuta","hodina","den","týden","měsíc","rok",
               "sekundy","minuty","hodiny","dny","týdny","měsíce","roky",
               "sekund","minut","hodin","дní","týdnů","měsíců","let"))

        "sk" -> config(locale, "pred {0}", "za {0}", "práve teraz",
            u3("sekunda","minúta","hodina","deň","týždeň","mesiac","rok",
               "sekundy","minúty","hodiny","dni","týždne","mesiace","roky",
               "sekúnd","minút","hodín","dní","týždňov","mesiacov","rokov"))

        "hr" -> config(locale, "prije {0}", "za {0}", "upravo sada",
            u3("sekunda","minuta","sat","dan","tjedan","mjesec","godina",
               "sekunde","minute","sata","dana","tjedna","mjeseca","godine",
               "sekundi","minuta","sati","dana","tjedana","mjeseci","godina"))

        "sr" -> config(locale, "пре {0}", "за {0}", "управо сада",
            u3("секунда","минут","сат","дан","недеља","месец","година",
               "секунде","минута","сата","дана","недеље","месеца","године",
               "секунди","минута","сати","дана","недеља","месеци","година"))

        "mk" -> config(locale, "пред {0}", "за {0}", "токму сега",
            u2("секунда","минута","час","ден","недела","месец","година",
               "секунди","минути","часови","дена","недели","месеци","години"))

        "sl" -> config(locale, "pred {0}", "čez {0}", "ravno zdaj",
            u3("секунда","minuta","ura","dan","teden","mesec","leto",
               "sekundi","minuti","uri","dneva","tedna","meseca","leti",
               "sekunde","minute","ure","dni","tednov","mesecev","let"))

        "lt" -> config(locale, "prieš {0}", "po {0}", "ką tik",
            u3("sekundė","minutė","valanda","diena","savaitė","mėnuo","metai",
               "sekundės","minutės","valandos","dienos","savaitės","mėnesiai","metai",
               "sekundžių","minučių","valandų","dienų","savaičių","mėnesių","metų"))

        "lv" -> config(locale, "pirms {0}", "pēc {0}", "tikko",
            u3("sekunde","minūte","stunda","diena","nedēļa","mēnesis","gads",
               "sekundes","minūtes","stundas","dienas","nedēļas","mēneši","gadi",
               "sekundēm","minūtēm","stundām","dienām","nedēļām","mēnešiem","gadiem"))

        "sq" -> config(locale, "{0} më parë", "pas {0}", "tani",
            u2("sekondë","minutë","orë","ditë","javë","muaj","vit",
               "sekonda","minuta","orë","ditë","javë","muaj","vite"))

        // ── East Asian / SE Asian (no unit separator, single form) ───────────
        "ja" -> config(locale, "{0}前", "{0}後", "たった今",
            u2("秒","分","時間","日","週間","ヶ月","年",
               "秒","分","時間","日","週間","ヶ月","年"), sep = "")

        "zh" -> config(locale, "{0}前", "{0}后", "刚刚",
            u2("秒","分","小时","天","周","月","年",
               "秒","分","小时","天","周","月","年"), sep = "")

        "ko" -> config(locale, "{0} 전", "{0} 후", "방금",
            u2("초","분","시간","일","주","달","년",
               "초","분","시간","일","주","달","년"), sep = "")

        "th" -> config(locale, "{0}ที่แล้ว", "อีก {0}", "เมื่อกี้",
            u2("วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี",
               "วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี"), sep = "")

        "vi" -> config(locale, "{0} trước", "{0} nữa", "vừa xong",
            u2("giây","phút","giờ","ngày","tuần","tháng","năm",
               "giây","phút","giờ","ngày","tuần","tháng","năm"))

        "iu" -> config(locale, "{0} ᖄᖏᖅᑐᖅ", "{0} ᐊᓂᒍᖅᐸᑦ", "ᒫᓐᓇᑲᐅᑎᒋ",
            u4("ᓯᑲᓐᑎ","ᓯᑲᓐᑎᒃ","ᓯᑲᓐᑎᑦ","ᓯᑲᓐᑎᑦ",
               "ᒥᓂᑎ","ᒥᓂᑎᒃ","ᒥᓂᑎᑦ","ᒥᓂᑎᑦ",
               "ᐃᑲᕐᕋᖅ","ᐃᑲᕐᕋᒃ","ᐃᑲᕐᕋᐃᑦ","ᐃᑲᕐᕋᐃᑦ",
               "ᐅᓪლᓗᖅ","ᐅᓪᓗᒃ","ᐅᓪᓗᐃᑦ","ᐅᓪᓗᐃᑦ",
               "ᐱᓇᓱᐊᕈᓯᖅ","ᐱᓇᓱᐊᕈᓯᒃ","ᐱᓇᓱᐊᕈᓰᑦ","ᐱᓇᓱᐊᕈᓰᑦ",
               "ᑕᖅᑭᖅ","ᑕᖅᑭᒃ","ᑕᖅᑮᑦ","ᑕᖅᑮᑦ",
               "ᐊᕐᕌᒍ","ᐊᕐᕌᒍᒃ","ᐊᕐᕌᒍᐃᑦ","ᐊᕐᕌᒍᐃᑦ"))

        // ── South / Central Asian ─────────────────────────────────────────────
        "hi" -> config(locale, "{0} पहले", "{0} बाद", "अभी",
            u2("सेकंड","मिनट","घंटा","दिन","सप्ताह","महीना","वर्ष",
               "सेकंड","मिनट","घंटे","दिन","सप्ताह","महीने","वर्ष"))

        "id" -> config(locale, "{0} yang lalu", "dalam {0}", "baru saja",
            u2("detik","menit","jam","hari","minggu","bulan","tahun",
               "detik","menit","jam","hari","minggu","bulan","tahun"))

        "ms" -> config(locale, "{0} yang lalu", "dalam {0}", "baru sahaja",
            u2("saat","minit","jam","hari","minggu","bulan","tahun",
               "saat","minit","jam","hari","minggu","bulan","tahun"))

        "sw" -> config(locale, "{0} iliyopita", "baada ya {0}", "sasa hivi",
            u2("sekunde","dakika","saa","siku","wiki","mwezi","mwaka",
               "sekunde","dakika","saa","siku","wiki","miezi","miaka"))

        // ── Middle Eastern ────────────────────────────────────────────────────
        "ar" -> config(locale, "منذ {0}", "خلال {0}", "الآن",
            u6("ثانية","ثانية","ثانيتان","ثوانٍ","ثانية","ثانية",
               "دقيقة","دقيقة","دقيقتان","دقائق","دقيقة","دقيقة",
               "ساعة","ساعة","ساعتان","ساعات","ساعة","ساعة",
               "يوم","يوم","يومان","أيام","يوم","يوم",
               "أسبوع","أسبوع","أسبوعان","أسابيع","أسبوع","أسبوع",
               "شهر","شهر","شهران","أشهر","شهر","شهر",
               "سنة","سنة","سنتان","سنوات","سنة","سنة"))

        "fa" -> config(locale, "{0} پیش", "{0} دیگر", "همین الان",
            u2("ثانیه","دقیقه","ساعت","روز","هفته","ماه","سال",
               "ثانیه","دقیقه","ساعت","روز","هفته","ماه","سال"))

        "he" -> config(locale, "לפני {0}", "בעוד {0}", "ממש עכשיו",
            u4("שנייה","שנייה","שניות","שניות",
               "דקה","דקה","דקות","דקות",
               "שעה","שעה","שעות","שעות",
               "יום","יום","ימים","ימים",
               "שבוע","שבוע","שבועות","שבועות",
               "חودש","חודש","חודשים","חודשים",
               "שנה","שנה","שנים","שנים"))

        // ── Turkic ────────────────────────────────────────────────────────────
        "tr" -> config(locale, "{0} önce", "{0} sonra", "şimdi",
            u2("saniye","dakika","saat","gün","hafta","ay","yıl",
               "saniye","dakika","saat","gün","hafta","ay","yıl"))

        "az" -> config(locale, "{0} əvvəl", "{0} sonra", "indi",
            u2("saniyə","dəqiqə","saat","gün","həftə","ay","il",
               "saniyə","dəqiqə","saat","gün","həftə","ay","il"))

        "uz" -> config(locale, "{0} oldin", "{0} keyin", "hozir",
            u2("soniya","daqiqa","soat","kun","hafta","oy","yil",
               "soniya","daqiqa","soat","kun","hafta","oy","yil"))

        "kk" -> config(locale, "{0} бұрын", "{0} кейін", "қазір",
            u2("секунд","минут","сағат","күн","апта","ай","жыл",
               "секунд","минут","сағат","күн","апта","ай","жыл"))

        // ── Finno-Ugric ───────────────────────────────────────────────────────
        "fi" -> config(locale, "{0} sitten", "{0} päästä", "juuri nyt",
            u2("sekunti","minuutti","tunti","päivä","viikko","kuukausi","vuosi",
               "sekuntia","minuuttia","tuntia","päivää","viikkoa","kuukautta","vuotta"))

        "et" -> config(locale, "{0} tagasi", "{0} pärast", "just nüüd",
            u2("sekund","minut","tund","päev","nädal","kuu","aasta",
               "sekundit","minutit","tundi","päeva","nädalat","kuud","aastat"))

        "hu" -> config(locale, "{0} ezelőtt", "{0} múlva", "éppen most",
            u2("másodperc","perc","óra","nap","hét","hónap","év",
               "másodperc","perc","óra","nap","hét","hónap","év"))

        // ── Other ─────────────────────────────────────────────────────────────
        "el" -> config(locale, "πριν {0}", "σε {0}", "μόλις τώρα",
            u2("δευτερόλεπτο","λεπτό","ώρα","ημέρα","εβδομάδα","μήνας","έτος",
               "δευτερόλεπτα","λεπτά","ώρες","ημέρες","εβδομάδες","μήνες","έτη"))

        "eu" -> config(locale, "duela {0}", "{0} barru", "orain bertan",
            u2("segundo","minutu","ordu","egun","aste","hilabete","urte",
               "segundo","minutu","ordu","egun","aste","hilabete","urte"))

        "hy" -> config(locale, "{0} առաջ", "{0} հետո", "հենց հիմա",
            u2("վայրկյան","րոպե","ժամ","օր","շաբաթ","ամիս","տարի",
               "վայրկյան","րոպե","ժամ","օր","շաբաթ","ամիս","տարի"))

        "ka" -> config(locale, "{0}ის წინ", "{0}ში", "ახლავე",
            u2("წამ","წუთ","საათ","დღ","კვირ","თვ","წლ",
               "წამ","წუთ","საათ","დღ","კვირ","თვ","წල"), sep = "")

        else -> null
    }
}

private val configCache = mutableMapOf<String, RelativeTimeConfig>()

/**
 * Returns the [RelativeTimeConfig] for [locale], building and caching it on first use.
 * Supports full BCP 47 subtag fallback: e.g. "en-ZA" → "en" → "en" default.
 */
fun relativeTimeConfigFor(locale: Locale): RelativeTimeConfig {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = fullTag
    while (currentTag.isNotEmpty()) {
        val cached = configCache[currentTag]
        if (cached != null) return cached
        val built = buildConfig(currentTag, locale)
        if (built != null) {
            configCache[currentTag] = built
            return built
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return configCache.getOrPut("en") { buildConfig("en", Locale("en"))!! }
}
