package io.github.aughtone.readable.duration

import io.github.aughtone.readable.PluralCategory
import io.github.aughtone.readable.pluralCategoryFor
import io.github.aughtone.toolbox.Formatter
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.readable.Locales
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.Duration
import kotlin.time.DurationUnit

import io.github.aughtone.readable.relative.RelativeStyle

/** Functional formatter for Durations. */
typealias DurationFormatter = (Duration, RelativeStyle) -> String

private class DurationUnitNames(val forms: Map<PluralCategory, String>) {
    fun get(category: PluralCategory): String = forms[category] ?: forms[PluralCategory.Other] ?: forms.values.first()
}

// ── Factories ─────────────────────────────────────────────────────────────────

private fun u2(
    s1: String, m1: String, h1: String, d1: String, w1: String, mo1: String, y1: String,
    s2: String, m2: String, h2: String, d2: String, w2: String, mo2: String, y2: String
) = mapOf(
    "second" to DurationUnitNames(mapOf(PluralCategory.One to s1, PluralCategory.Other to s2)),
    "minute" to DurationUnitNames(mapOf(PluralCategory.One to m1, PluralCategory.Other to m2)),
    "hour"   to DurationUnitNames(mapOf(PluralCategory.One to h1, PluralCategory.Other to h2)),
    "day"    to DurationUnitNames(mapOf(PluralCategory.One to d1, PluralCategory.Other to d2)),
    "week"   to DurationUnitNames(mapOf(PluralCategory.One to w1, PluralCategory.Other to w2)),
    "month"  to DurationUnitNames(mapOf(PluralCategory.One to mo1, PluralCategory.Other to mo2)),
    "year"   to DurationUnitNames(mapOf(PluralCategory.One to y1, PluralCategory.Other to y2)),
)

private fun u3(
    s1: String, m1: String, h1: String, d1: String, w1: String, mo1: String, y1: String,
    sF: String, mF: String, hF: String, dF: String, wF: String, moF: String, yF: String,
    sM: String, mM: String, hM: String, dM: String, wM: String, moM: String, yM: String
) = mapOf(
    "second" to DurationUnitNames(mapOf(PluralCategory.One to s1, PluralCategory.Few to sF, PluralCategory.Many to sM)),
    "minute" to DurationUnitNames(mapOf(PluralCategory.One to m1, PluralCategory.Few to mF, PluralCategory.Many to mM)),
    "hour"   to DurationUnitNames(mapOf(PluralCategory.One to h1, PluralCategory.Few to hF, PluralCategory.Many to hM)),
    "day"    to DurationUnitNames(mapOf(PluralCategory.One to d1, PluralCategory.Few to dF, PluralCategory.Many to dM)),
    "week"   to DurationUnitNames(mapOf(PluralCategory.One to w1, PluralCategory.Few to wF, PluralCategory.Many to wM)),
    "month"  to DurationUnitNames(mapOf(PluralCategory.One to mo1, PluralCategory.Few to moF, PluralCategory.Many to moM)),
    "year"   to DurationUnitNames(mapOf(PluralCategory.One to y1, PluralCategory.Few to yF, PluralCategory.Many to yM)),
)

private fun u4(
    s1: String, s2: String, sM: String, sO: String,
    m1: String, m2: String, mM: String, mO: String,
    h1: String, h2: String, hM: String, hO: String,
    d1: String, d2: String, dM: String, dO: String,
    w1: String, w2: String, wM: String, wO: String,
    mo1: String, mo2: String, moM: String, moO: String,
    y1: String, y2: String, yM: String, yO: String,
) = mapOf(
    "second" to DurationUnitNames(mapOf(PluralCategory.One to s1, PluralCategory.Two to s2, PluralCategory.Many to sM, PluralCategory.Other to sO)),
    "minute" to DurationUnitNames(mapOf(PluralCategory.One to m1, PluralCategory.Two to m2, PluralCategory.Many to mM, PluralCategory.Other to mO)),
    "hour"   to DurationUnitNames(mapOf(PluralCategory.One to h1, PluralCategory.Two to h2, PluralCategory.Many to hM, PluralCategory.Other to hO)),
    "day"    to DurationUnitNames(mapOf(PluralCategory.One to d1, PluralCategory.Two to d2, PluralCategory.Many to dM, PluralCategory.Other to dO)),
    "week"   to DurationUnitNames(mapOf(PluralCategory.One to w1, PluralCategory.Two to w2, PluralCategory.Many to wM, PluralCategory.Other to wO)),
    "month"  to DurationUnitNames(mapOf(PluralCategory.One to mo1, PluralCategory.Two to mo2, PluralCategory.Many to moM, PluralCategory.Other to moO)),
    "year"   to DurationUnitNames(mapOf(PluralCategory.One to y1, PluralCategory.Two to y2, PluralCategory.Many to yM, PluralCategory.Other to yO)),
)

private fun u6(
    sZ: String, s1: String, s2: String, sF: String, sM: String, sO: String,
    mZ: String, m1: String, m2: String, mF: String, mM: String, mO: String,
    hZ: String, h1: String, h2: String, hF: String, hM: String, hO: String,
    dZ: String, d1: String, d2: String, dF: String, dM: String, dO: String,
    wZ: String, w1: String, w2: String, wF: String, wM: String, wO: String,
    moZ: String, mo1: String, mo2: String, moF: String, moM: String, moO: String,
    yZ: String, y1: String, y2: String, yF: String, yM: String, yO: String,
) = mapOf(
    "second" to DurationUnitNames(mapOf(PluralCategory.Zero to sZ, PluralCategory.One to s1, PluralCategory.Two to s2, PluralCategory.Few to sF, PluralCategory.Many to sM, PluralCategory.Other to sO)),
    "minute" to DurationUnitNames(mapOf(PluralCategory.Zero to mZ, PluralCategory.One to m1, PluralCategory.Two to m2, PluralCategory.Few to mF, PluralCategory.Many to mM, PluralCategory.Other to mO)),
    "hour"   to DurationUnitNames(mapOf(PluralCategory.Zero to hZ, PluralCategory.One to h1, PluralCategory.Two to h2, PluralCategory.Few to hF, PluralCategory.Many to hM, PluralCategory.Other to hO)),
    "day"    to DurationUnitNames(mapOf(PluralCategory.Zero to dZ, PluralCategory.One to d1, PluralCategory.Two to d2, PluralCategory.Few to dF, PluralCategory.Many to dM, PluralCategory.Other to dO)),
    "week"   to DurationUnitNames(mapOf(PluralCategory.Zero to wZ, PluralCategory.One to w1, PluralCategory.Two to w2, PluralCategory.Few to wF, PluralCategory.Many to wM, PluralCategory.Other to wO)),
    "month"  to DurationUnitNames(mapOf(PluralCategory.Zero to moZ, PluralCategory.One to mo1, PluralCategory.Two to mo2, PluralCategory.Few to moF, PluralCategory.Many to moM, PluralCategory.Other to moO)),
    "year"   to DurationUnitNames(mapOf(PluralCategory.Zero to yZ, PluralCategory.One to y1, PluralCategory.Two to y2, PluralCategory.Few to yF, PluralCategory.Many to yM, PluralCategory.Other to yO)),
)

private fun buildFormatter(locale: Locale, units: Map<String, DurationUnitNames>, sep: String = " "): DurationFormatter = { duration, style ->
    val totalSeconds = abs(duration.toDouble(DurationUnit.SECONDS))
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
    val category = pluralCategoryFor(locale, n)
    val names = units[unitKey]!!
    val label = names.get(category)
    "$n$sep$label"
}

// ── Constants ─────────────────────────────────────────────────────────────────

private val ENGLISH_DURATION_UNITS = u2(
    "second", "minute", "hour", "day", "week", "month", "year",
    "seconds", "minutes", "hours", "days", "weeks", "months", "years"
)

private val ENGLISH_DURATION_UNITS_SHORT = u2(
    "s", "m", "h", "d", "w", "mo", "y",
    "s", "m", "h", "d", "w", "mo", "y"
)

// ── On-demand locale factory ──────────────────────────────────────────────────

private fun isDurationTagSupported(tag: String): Boolean = when (tag) {
    "en", "af", "nl", "id", "ms", "sw", "de", "da", "nb", "no", "nn", "sv", "is",
    "et", "fi", "vi", "el", "hu", "ro", "tr", "ru", "uk", "be", "pl", "cs", "sk",
    "sl", "hr", "sr", "bg", "mk", "fr", "it", "es", "pt", "ca", "gl", "hi", "bn",
    "gu", "kn", "ml", "mr", "pa", "ta", "te", "ar", "he", "fa", "ur", "id", "ms",
    "th", "ko", "ja", "zh-Hans", "zh", "zh-Hant", "zh-TW", "zh-HK" -> true
    else -> false
}

fun buildDurationFormatter(tag: String, locale: Locale, style: RelativeStyle = RelativeStyle.Long): DurationFormatter {
    val sep = if (style == RelativeStyle.Short) "" else " "
    return when (tag) {
        "en" -> if (style == RelativeStyle.Short) buildFormatter(locale, ENGLISH_DURATION_UNITS_SHORT, sep) else buildFormatter(locale, ENGLISH_DURATION_UNITS, sep)
        "af", "nl", "id", "ms", "sw" ->
            buildFormatter(locale, ENGLISH_DURATION_UNITS, sep)
        "de" ->
            buildFormatter(locale, u2("Sekunde","Minute","Stunde","Tag","Woche","Monat","Jahr",
                             "Sekunden","Minuten","Stunden","Tage","Wochen","Monate","Jahre"), sep)
        "da" ->
            buildFormatter(locale, u2("sekund","minut","time","dag","uge","måned","år",
                             "sekunder","minutter","timer","dage","uger","måneder","år"), sep)
        "nb", "no" ->
            buildFormatter(locale, u2("sekund","minutt","time","dag","uke","måned","år",
                             "sekunder","minutter","timer","dager","uker","måneder","år"), sep)
        "nn" ->
            buildFormatter(locale, u2("sekund","minutt","time","dag","veke","månad","år",
                             "sekunder","minutt","timar","dagar","veker","månader","år"), sep)
        "sv" ->
            buildFormatter(locale, u2("sekund","minut","timme","dag","vecka","månad","år",
                             "sekunder","minuter","timmar","dagar","veckor","månader","år"), sep)
        "is" ->
            buildFormatter(locale, u2("sekúnda","mínúta","klukkustund","dagur","vika","mánuður","ár",
                             "sekúndur","mínútur","klukkustundir","dagar","vikur","mánuðir","ár"), sep)

        "fr" ->
            buildFormatter(locale, u2("seconde","minute","heure","jour","semaine","mois","an",
                             "secondes","minutes","heures","jours","semaines","mois","ans"), sep)
        "es", "gl" ->
            buildFormatter(locale, u2("segundo","minuto","hora","día","semana","mes","año",
                             "segundos","minutos","horas","días","semanas","meses","años"), sep)
        "it" ->
            buildFormatter(locale, u2("secondo","minuto","ora","giorno","settimana","mese","anno",
                             "secondi","minuti","ore","giorni","settimane","mesi","anni"), sep)
        "pt" ->
            buildFormatter(locale, u2("segundo","minuto","hora","dia","semana","mês","ano",
                             "segundos","minutos","horas","dias","semanas","meses","anos"), sep)
        "ca" ->
            buildFormatter(locale, u2("segon","minut","hora","dia","setmana","mes","any",
                             "segons","minuts","hores","dies","setmanes","mesos","anys"), sep)
        "ro" ->
            buildFormatter(locale, u2("secundă","minut","oră","zi","săptămână","lună","an",
                             "secunde","minute","ore","zile","săptămâni","luni","ani"), sep)

        "ru" -> buildFormatter(locale, u3("секунда","минута","час","день","неделя","месяц","год",
                                   "секунды","минуты","часа","дня","недели","месяца","года",
                                   "секунд","минут","часов","дней","недель","месяцев","лет"), sep)
        "uk" -> buildFormatter(locale, u3("секунда","хвилина","година","день","тиждень","місяць","рік",
                                   "секунди","хвилини","години","дні","тижні","місяці","роки",
                                   "секунд","хвилин","годин","днів","тижнів","місяців","років"), sep)
        "be" -> buildFormatter(locale, u3("секунда","хвіліна","гадзіна","дзень","тыдзень","месяц","год",
                                   "секунды","хвіліны","гадзіны","дні","тижні","месяцы","гады",
                                   "секунд","хвілін","гадзін","дзён","тыдняў","месяцаў","гадоў"), sep)
        "bg" -> buildFormatter(locale, u2("секунда","минута","час","ден","седмица","месец","година",
                                   "секунди","минути","часа","дни","седмици","месеца","години"), sep)
        "pl" -> buildFormatter(locale, u3("sekunda","minuta","godzina","dzień","tydzień","miesiąc","rok",
                                   "sekundy","minuty","godziny","dni","tygodnie","miesiące","lata",
                                   "sekund","minut","godzin","dni","tygodni","miesięcy","lat"), sep)
        "cs" -> buildFormatter(locale, u3("sekunda","minuta","hodina","den","týden","měsíc","rok",
                                   "sekundy","minuty","hodiny","dny","týdnů","měsíce","roky",
                                   "sekund","minut","hodin","dní","týdnů","měsíců","let"), sep)
        "sk" -> buildFormatter(locale, u3("sekunda","minúta","hodina","deň","týždeň","mesiac","rok",
                                   "sekundy","minúty","hodiny","dni","týždne","mesiace","roky",
                                   "sekúnd","minút","hodín","dní","týždňov","mesiacov","rokov"), sep)
        "hr" -> buildFormatter(locale, u3("sekunda","minuta","sat","dan","tjedan","mjesec","godina",
                                   "sekunde","minute","sata","dana","tjedna","mjeseca","godine",
                                   "sekundi","minuta","sati","dana","tjedana","mjeseci","godina"), sep)
        "sr" -> buildFormatter(locale, u3("секунда","минут","сат","дан","недеља","месец","година",
                                   "секунде","минута","сата","дана","недеље","месеца","године",
                                   "секунди","минута","сати","дана","недеља","месеци","година"), sep)
        "mk" -> buildFormatter(locale, u2("секунда","минута","час","ден","недела","месец","година",
                                   "секунди","минути","часови","дена","недели","месеци","години"), sep)
        "sl" -> buildFormatter(locale, u3("sekunda","minuta","ura","dan","teden","mesec","leto",
                                   "sekundi","minuti","uri","dneva","tedna","meseca","leti",
                                   "секунде","minute","ure","dni","tednov","mesecev","let"), sep)
        "lt" -> buildFormatter(locale, u3("sekundė","minutė","valanda","diena","savaitė","mėnuo","metai",
                                   "sekundės","minutės","valandos","dienos","savaitės","mėnesiai","metai",
                                   "sekundžių","minučių","valandų","dienų","savaičių","mėnesių","metų"), sep)
        "lv" -> buildFormatter(locale, u3("sekunde","minūte","stunda","diena","nedēļa","mēnesis","gads",
                                   "sekundes","minūtes","stundas","dienas","nedēļas","mēneši","gadi",
                                   "sekundēm","minūtēm","stundām","dienām","nedēļām","mēnešiem","gadiem"), sep)
        "sq" -> buildFormatter(locale, u2("sekondë","minutë","orë","ditë","javë","muaj","vit",
                                   "sekonda","minuta","orë","ditë","javë","muaj","vite"), sep)

        "ja" -> buildFormatter(locale, u2("秒","分","時間","日","週間","ヶ月","年",
                                   "秒","分","時間","日","週間","ヶ月","年"), "")
        "ko" -> buildFormatter(locale, u2("초","분","시간","일","주","달","년",
                                   "초","분","시간","일","주","달","년"), "")
        "zh-TW", "zh-HK" -> buildFormatter(locale, u2("秒","分","小時","天","週","月","年",
                                               "秒","分","小時","天","週","月","年"), "")
        "zh" -> buildFormatter(locale, u2("秒","分","小时","天","周","月","年",
                                   "秒","分","小时","天","周","月","年"), "")
        "th" -> buildFormatter(locale, u2("วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี",
                                   "วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี"), "")
        "vi" -> buildFormatter(locale, u2("giây","phút","giờ","ngày","tuần","tháng","năm",
                                   "giây","phút","giờ","ngày","tuần","tháng","năm"), " ")
        "iu" -> buildFormatter(locale, u4("ᓯᑲᓐᑎ","ᓯᑲᓐᑎᒃ","ᓯᑲᓐᑎᑦ","ᓯᑲᓐᑎᑦ",
                                   "ᒥᓂᑎ","ᒥᓂᑎᒃ","ᒥᓂᑎᑦ","ᒥᓂᑎᑦ",
                                   "ᐃᑲᕐᕋᖅ","ᐃᑲᕐᕋᒃ","ᐃᑲᕐᕋᐃᑦ","ᐃᑲᕐᕋᐃᑦ",
                                   "ᐅᓪᓗᖅ","ᐅᓪᓗᒃ","ᐅᓪᓗᐃᑦ","ᐅᓪᓗᐃᑦ",
                                   "ᐱᓇᓱᐊᕈᓯᖅ","ᐱᓇᓱᐊᕈᓯᒃ","ᐱᓇᓱᐊᕈᓰᑦ","ᐱᓇᓱᐊᕈᓰᑦ",
                                   "ᑕᖅᑭᖅ","ᑕᖅᑭᒃ","ᑕᖅᑮᑦ","ᑕᖅᑮᑦ",
                                   "ᐊᕐᕌᒍ","ᐊᕐᕌᒍᒃ","ᐊᕐᕌᒍᐃᑦ","ᐊᕐᕌᒍᐃᑦ"), " ")

        "hi" -> buildFormatter(locale, u2("सेकंड","मिनट","घंटा","दिन","सप्ताह","महीना","वर्ष",
                                   "सेकंड","मिनट","घंटे","दिन","सप्ताह","महीने","वर्ष"), " ")
        "ar" -> buildFormatter(locale, u6("ثانية","ثانية","ثانيتان","ثوانٍ","ثانية","ثانية",
                                   "دقيقة","دقيقة","دقيقتان","دقائق","دقيقة","دقيقة",
                                   "ساعة","ساعة","ساعتان","ساعات","ساعة","ساعة",
                                   "يوم","يوم","يومان","أيام","يوم","يوم",
                                   "أسبوع","أسبوع","أسبوعان","أسابيع","أسبوع","أسبوع",
                                   "شهر","شهر","شهران","أشهر","شهر","شهر",
                                   "سنة","سنة","سنتان","سنوات","سنة","سنة"), " ")
        "fa" -> buildFormatter(locale, u2("ثانیه","دقیقه","ساعت","روز","هفته","ماه","سال",
                                   "ثانیه","دقیقه","ساعت","روز","هفته","ماه","سال"), " ")
        "he" -> buildFormatter(locale, u4("שנייה","שנייה","שניות","שניות",
                                   "דקה","דקה","דקות","דקות",
                                   "שעה","שעה","שעות","שעות",
                                   "יום","יום","ימים","ימים",
                                   "שבוע","שבוע","שבועות","שבועות",
                                   "חודש","חודש","חודשים","חודשים",
                                   "שנה","שנה","שנים","שנים"), " ")

        "tr", "az" -> buildFormatter(locale, u2("saniye","dakika","saat","gün","hafta","ay","yıl",
                                         "saniye","dakika","saat","gün","hafta","ay","yıl"), " ")
        "uz" -> buildFormatter(locale, u2("soniya","daqiqa","soat","kun","hafta","oy","yil",
                                   "soniya","daqiqa","soat","kun","hafta","oy","yil"), " ")
        "kk" -> buildFormatter(locale, u2("секунд","минут","сағат","күн","апта","ай","жыл",
                                   "секунд","минут","сағат","күн","апта","ай","жыл"))

        "fi" -> buildFormatter(locale, u2("sekunti","minuutti","tunti","päivä","viikko","kuukausi","vuosi",
                                   "sekuntia","minuuttia","tuntia","päivää","viikkoa","kuukautta","vuotta"))
        "et" -> buildFormatter(locale, u2("sekund","minut","tund","päev","nädal","kuu","aasta",
                                   "sekundit","minutit","tundi","päeva","nädalat","kuud","aastat"))
        "hu" -> buildFormatter(locale, u2("másodperc","perc","óra","nap","hét","hónap","év",
                                   "másodperc","perc","óra","nap","hét","hónap","év"))
        "el" -> buildFormatter(locale, u2("δευτερόλεπτο","λεπτό","ώρα","ημέρα","εβδομάδα","μήνας","έτος",
                                   "δευτερόλεπτα","λεπτά","ώρες","ημέρες","εβδομάδες","μήνες","έτη"))
        "eu" -> buildFormatter(locale, u2("segundo","minutu","ordu","egun","aste","hilabete","urte",
                                   "segundo","minutu","ordu","egun","aste","hilabete","urte"))
        "hy" -> buildFormatter(locale, u2("վայրկյան","րոպե","ժամ","օր","շաբաթ","ամիս","տարի",
                                   "վայրկյան","րոպե","ժամ","օր","շաբաթ","ամիս","տարի"))
        "ka" -> buildFormatter(locale, u2("წამი","წუთი","საათი","დღე","კვირა","თვე","წელი",
                                   "წამი","წუთი","საათი","დღე","კვირა","თვე","წელი"))

        else -> if (tag == "en") buildFormatter(locale, ENGLISH_DURATION_UNITS) else buildDurationFormatter("en", locale)
    }
}

// ── Lazy cache ────────────────────────────────────────────────────────────────

private val durationCache = mutableMapOf<Pair<String, RelativeStyle>, DurationFormatter>()

/**
 * Retrieves the [DurationFormatter] for a given [Locale], building and caching it on first use.
 * Supports full BCP 47 subtag lookup (e.g. "zh-TW") before falling back to the base language.
 */
fun durationFormatterFor(locale: Locale, style: RelativeStyle = RelativeStyle.Long): DurationFormatter {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = fullTag
    while (currentTag.isNotEmpty()) {
        val key = currentTag to style
        val cached = durationCache[key]
        if (cached != null) return cached
        if (isDurationTagSupported(currentTag)) {
            val built = buildDurationFormatter(currentTag, locale, style)
            durationCache[key] = built
            return built
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    return durationCache.getOrPut("en" to style) { buildDurationFormatter("en", Locales.English, style) }
}
