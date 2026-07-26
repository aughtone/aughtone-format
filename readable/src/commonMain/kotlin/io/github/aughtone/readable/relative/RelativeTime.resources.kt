@file:Suppress("DEPRECATION")

package io.github.aughtone.readable.relative

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.PluralCategory
import io.github.aughtone.readable.pluralCategoryFor
import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.concurrent.Volatile
import kotlin.math.roundToLong
import kotlin.time.Duration
import kotlin.time.DurationUnit

/** Functional formatter for [Duration]s relative to a point in time. */
typealias RelativeTimeFormatter = (Duration, Boolean) -> String

private class RelativeTimeUnitNames(val forms: Map<PluralCategory, String>) {
    fun get(category: PluralCategory): String = forms[category] ?: forms[PluralCategory.Other] ?: forms.values.first()
}

/**
 * Configuration for relative time formatting in a specific locale.
 *
 * @property formatter The core logic that scales and pluralizes the time difference.
 * @property nowString The localized string for "just now".
 * @property todayString The localized string for "Today".
 * @property tomorrowString The localized string for "Tomorrow".
 * @property yesterdayString The localized string for "Yesterday".
 * @property recentlyString The localized string for "Recently" (the recent past).
 * @property shortlyString The localized string for "Shortly" (the near future).
 */
class RelativeTimeConfig(
    val formatter: RelativeTimeFormatter,
    val nowString: String,
    val todayString: String,
    val tomorrowString: String,
    val yesterdayString: String,
    val recentlyString: String,
    val shortlyString: String,
)

// ── Factories ─────────────────────────────────────────────────────────────────

/**
 * Creates a unit map for languages with 2 grammatical forms (Singular/Plural).
 */
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

/**
 * Creates a unit map for languages with 3 grammatical forms (e.g., Slavic languages).
 */
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

/**
 * Creates a unit map for languages with 4 grammatical forms (e.g., Hebrew, Inuktitut).
 */
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

/**
 * Creates a unit map for languages with 6 grammatical forms (e.g., Arabic).
 */
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

/**
 * Internal helper to construct a [RelativeTimeConfig].
 */
private fun config(
    locale: Locale,
    past: String,
    future: String,
    nowString: String,
    units: Map<String, RelativeTimeUnitNames>,
    todayString: String = "Today",
    tomorrowString: String = "Tomorrow",
    yesterdayString: String = "Yesterday",
    recentlyString: String = "Recently",
    sep: String = " ",
    shortlyString: String = "Shortly",
): RelativeTimeConfig {
    val formatter: RelativeTimeFormatter = { delta, allowDates ->
        val totalSeconds = delta.toDouble(DurationUnit.SECONDS)
        val absSeconds = abs(totalSeconds)
        val isPast = totalSeconds < 0

        val (value, unitKey) = when {
            allowDates && absSeconds >= 31536000 -> (absSeconds / 31536000.0) to "year"
            allowDates && absSeconds >= 2592000  -> (absSeconds / 2592000.0)  to "month"
            allowDates && absSeconds >= 604800 && (absSeconds / 604800.0).roundToLong() <= 3 -> (absSeconds / 604800.0) to "week"
            allowDates && absSeconds >= 86400   -> (absSeconds / 86400.0)   to "day"
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
    return RelativeTimeConfig(formatter, nowString, todayString, tomorrowString, yesterdayString, recentlyString, shortlyString)
}

// ── Constants ─────────────────────────────────────────────────────────────────

private val ENGLISH_UNITS_LONG = u2(
    "second", "minute", "hour", "day", "week", "month", "year",
    "seconds", "minutes", "hours", "days", "weeks", "months", "years"
)

private val ENGLISH_UNITS_SHORT = u2(
    "s", "m", "h", "d", "w", "mo", "y",
    "s", "m", "h", "d", "w", "mo", "y"
)

/** Default English configuration. */
private fun enConfig(locale: Locale, style: RelativeStyle) = config(
    locale = locale,
    past = if (style == RelativeStyle.Short) "{0}" else "{0} ago",
    future = if (style == RelativeStyle.Short) "in {0}" else "in {0}",
    nowString = "just now",
    units = if (style == RelativeStyle.Short) ENGLISH_UNITS_SHORT else ENGLISH_UNITS_LONG,
    todayString = "Today",
    tomorrowString = "Tomorrow",
    yesterdayString = "Yesterday",
    recentlyString = "Recently",
    sep = if (style == RelativeStyle.Short) "" else " "
)

/** Helper to check if a specific BCP 47 tag is explicitly defined in this file. */
private fun isRelativeTimeTagSupported(tag: String): Boolean = when (tag) {
    "en-ZA", "en", "af", "nl", "de", "da", "nb", "no", "nn", "sv", "is", "et", "fi",
    "vi", "el", "hu", "ro", "tr", "ru", "uk", "be", "pl", "cs", "sk", "sl", "hr",
    "sr", "bg", "mk", "fr", "it", "es", "pt", "ca", "gl", "hi", "bn", "gu", "kn",
    "ml", "mr", "pa", "ta", "te", "ar", "he", "fa", "ur", "th", "ko", "ja",
    "zh-Hans", "zh", "zh-Hant", "zh-TW", "zh-HK", "eu", "hy", "ka",
    "az", "uz", "kk", "lt", "lv", "sq", "iu", "id", "ms", "sw" -> true
    else -> false
}

/**
 * Public factory that guarantees a [RelativeTimeConfig], falling back to English if the tag is unknown.
 *
 * Example:
 * ```kotlin
 * val config = buildRelativeTimeConfig("fr", Locale("fr"), RelativeStyle.Long)
 * println(config.nowString) // "à l'instant"
 * ```
 *
 * @param tag The BCP 47 language tag to build the configuration for.
 * @param locale The [Locale] used for pluralization logic.
 * @param style The [RelativeStyle] for the output strings.
 * @return a [RelativeTimeConfig] for the specified parameters.
 */
fun buildRelativeTimeConfig(tag: String, locale: Locale, style: RelativeStyle = RelativeStyle.Long): RelativeTimeConfig {
    return when (tag) {
        // ── English / Germanic ────────────────────────────────────────────────
        "en-ZA" -> config(locale, if (style == RelativeStyle.Short) "{0}" else "{0} ago", if (style == RelativeStyle.Short) "in {0}" else "in {0}", "now now", if (style == RelativeStyle.Short) ENGLISH_UNITS_SHORT else ENGLISH_UNITS_LONG, "Today", "Tomorrow", "Yesterday", "Recently", if (style == RelativeStyle.Short) "" else " ", shortlyString = "ᒫᓐᓇᕈᓘᓂᐊᖅᑐᖅ")
        "en" -> enConfig(locale, style)

        "af" -> config(locale, "{0} gelede", "oor {0}", "nou net", u2("sekonde","minuut","uur","dag","week","maand","jaar", "sekondes","minute","ure","dae","weke","maande","jaar"), "Vandag", "Môre", "Gister", "onlangs", shortlyString = "binnekort")
        "nl" -> config(locale, "{0} geleden", "over {0}", "zojuist", u2("seconde","minuut","uur","dag","week","maand","jaar", "seconden","minuten","uren","dagen","weken","maanden","jaren"), "Vandaag", "Morgen", "Gisteren", "onlangs", shortlyString = "binnenkort")

        "de" -> config(locale, "vor {0}", "in {0}", "gerade eben",
            u2("Sekunde","Minute","Stunde","Tag","Woche","Monat","Jahr",
               "Sekunden","Minuten","Stunden","Tage","Wochen","Monate","Jahre"),
            todayString = "Heute", tomorrowString = "Morgen", yesterdayString = "Gestern", recentlyString = "vor kurzem", shortlyString = "in Kürze")

        "da" -> config(locale, "for {0} siden", "om {0}", "lige nu",
            u2("sekund","minut","time","dag","uge","måned","år",
               "sekunder","minutter","timer","dage","uger","måneder","år"),
            todayString = "I dag", tomorrowString = "I morgen", yesterdayString = "I går", recentlyString = "For nylig", shortlyString = "Om lidt")

        "nb", "no" -> config(locale, "for {0} siden", "om {0}", "akkurat nå",
            u2("sekund","minutt","time","dag","uke","måned","år",
               "sekunder","minutter","timer","dager","uker","måneder","år"),
            todayString = "I dag", tomorrowString = "I morgen", yesterdayString = "I går", recentlyString = "Nylig", shortlyString = "Snart")

        "nn" -> config(locale, "for {0} sidan", "om {0}", "akkurat no",
            u2("sekund","minutt","time","dag","veke","månad","år",
               "sekund","minutt","timar","dagar","veker","månader","år"),
            todayString = "I dag", tomorrowString = "I morgon", yesterdayString = "I går", recentlyString = "Nyleg", shortlyString = "Snart")

        "sv" -> config(locale, "för {0} sedan", "om {0}", "alldeles nyss",
            u2("sekund","minut","timme","dag","vecka","månad","år",
               "sekunder","minuter","timmar","dagar","veckor","månader","år"),
            todayString = "I dag", tomorrowString = "I morgon", yesterdayString = "I går", recentlyString = "Nyligen", shortlyString = "Inom kort")

        "is" -> config(locale, "fyrir {0} síðan", "eftir {0}", "rétt í þessu",
            u2("sekúndu","mínútu","klukkustund","dag","viku","mánuð","ár",
               "sekúndum","mínútum","klukkustundum","dögum","vikum","mánuðum","árum"),
            todayString = "Í dag", tomorrowString = "Á morgun", yesterdayString = "Í gær", recentlyString = "Nýlega", shortlyString = "Innan skammens")

        "fr" -> config(locale, "il y a {0}", "dans {0}", "à l'instant",
            u2("seconde","minute","heure","jour","semaine","mois","an",
               "secondes","minutes","heures","jours","semaines","mois","ans"),
            todayString = "Aujourd'hui", tomorrowString = "Demain", yesterdayString = "Hier", recentlyString = "récemment", shortlyString = "bientôt")

        "es" -> config(locale, "hace {0}", "en {0}", "ahora mismo",
            u2("segundo","minuto","hora","día","semana","mes","año",
               "segundos","minutos","horas","días","semanas","meses","años"),
            todayString = "Hoy", tomorrowString = "Mañana", yesterdayString = "Ayer", recentlyString = "recientemente", shortlyString = "próximamente")

        "it" -> config(locale, "{0} fa", "tra {0}", "proprio ora",
            u2("secondo","minuto","ora","giorno","settimana","mese","anno",
               "secondi","minuti","ore","giorni","settimane","mesi","anni"),
            todayString = "Oggi", tomorrowString = "Domani", yesterdayString = "Ieri", recentlyString = "recentemente", shortlyString = "a breve")

        "pt" -> config(locale, "há {0}", "em {0}", "agora mesmo",
            u2("segundo","minuto","hora","dia","semana","mês","ano",
               "segundos","minutos","horas","dias","semanas","meses","anos"),
            todayString = "Hoje", tomorrowString = "Amanhã", yesterdayString = "Ontem", recentlyString = "recentemente", shortlyString = "em breve")

        "ca" -> config(locale, "fa {0}", "d'aquí a {0}", "ara mateix",
            u2("segon","minut","hora","dia","setmana","mes","any",
               "segons","minuts","hores","dies","setmanes","mesos","anys"), recentlyString = "recentment", shortlyString = "més endavant")

        "gl" -> config(locale, "hai {0}", "en {0}", "agora mesmo",
            u2("segundo","minuto","hora","día","semana","mes","ano",
               "segundos","minutos","horas","días","semanas","meses","anos"), recentlyString = "recentemente", shortlyString = "en breve")

        "ro" -> config(locale, "acum {0}", "în {0}", "tocmai acum",
            u2("secundă","minut","oră","zi","săptămână","lună","an",
               "secunde","minute","ore","zile","săptămâni","luni","ani"), recentlyString = "recent", shortlyString = "în curând")

        // ── Slavic / Baltic (Complex plurals) ─────────────────────────────────
        "ru" -> config(locale, "{0} назад", "через {0}", "только что",
            u3("секунда","минута","час","день","неделя","месяц","год",
               "секунды","минуты","часа","дня","недели","месяца","года",
               "секунд","минут","часов","дней","недель","месяцев","лет"), recentlyString = "недавно", shortlyString = "скоро")

        "uk" -> config(locale, "{0} тому", "через {0}", "щойно",
            u3("секунда","хвилина","година","день","тиждень","місяць","рік",
               "секунди","хвилини","години","дні","тижні","місяці","роки",
               "секунд","хвилин","годин","днів","тижнів","місяців","років"), recentlyString = "нещодавно", shortlyString = "незабаром")

        "be" -> config(locale, "{0} таму", "праз {0}", "толькі што",
            u3("секунда","хвіліна","гадзіна","дзень","тыдзень","месяц","год",
               "секунды","хвіліны","гадзіны","дні","тижні","месяцы","гады",
               "секунд","хвілін","гадзін","дзён","тыдняў","месяцаў","гадоў"), recentlyString = "нядаўна", shortlyString = "хутка")

        "bg" -> config(locale, "преди {0}", "след {0}", "точно сега",
            u2("секунда","минута","час","ден","седмица","месец","година",
               "секунди","минути","часа","дни","седмици","месеца","години"), recentlyString = "наскоро", shortlyString = "скоро")

        "pl" -> config(locale, "{0} temu", "za {0}", "właśnie teraz",
            u3("sekunda","minuta","godzina","dzień","tydzień","miesiąc","rok",
               "sekundy","minuty","godziny","dni","tygodnie","miesiące","lata",
               "sekund","minut","godzin","dni","tygodni","miesięcy","lat"), recentlyString = "niedawno", shortlyString = "wkrótce")

        "cs" -> config(locale, "před {0}", "za {0}", "právě teď",
            u3("sekunda","minuta","hodina","den","týden","měsíc","rok",
               "sekundy","minuty","hodiny","dny","týdny","měsíce","roky",
               "sekund","minut","hodin","дní","týdnů","měsíců","let"), recentlyString = "nedávno", shortlyString = "brzy")

        "sk" -> config(locale, "pred {0}", "za {0}", "práve teraz",
            u3("sekunda","minúta","hodina","deň","týždeň","mesiac","rok",
               "sekundy","minúty","hodiny","dni","týždne","mesiace","roky",
               "sekúnd","minút","hodín","dní","týždňov","mesiacov","rokov"), recentlyString = "nedávno", shortlyString = "čoskoro")

        "hr" -> config(locale, "prije {0}", "za {0}", "upravo sada",
            u3("sekunda","minuta","sat","dan","tjedan","mjesec","godina",
               "sekunde","minute","sata","dana","tjedna","mjeseca","godine",
               "sekundi","minuta","sati","dana","tjedana","mjeseci","godina"), recentlyString = "nedavno", shortlyString = "uskoro")

        "sr" -> config(locale, "пре {0}", "за {0}", "управо сада",
            u3("секунда","минут","сат","дан","недеља","месец","година",
               "секунде","минута","сата","дана","недеље","месеца","године",
               "секунди","минута","сати","дана","недеља","месеци","година"), recentlyString = "недавно", shortlyString = "ускоро")

        "mk" -> config(locale, "пред {0}", "за {0}", "токму сега",
            u2("секунда","минуτα","час","ден","недела","месец","година",
               "секунди","минути","часови","дена","недели","месеци","години"), recentlyString = "неодамна", shortlyString = "наскоро")

        "sl" -> config(locale, "pred {0}", "čez {0}", "ravno zdaj",
            u3("секунда","minuta","ura","dan","teden","mesec","leto",
               "sekundi","minuti","uri","dneva","tedna","meseca","leti",
               "sekunde","minute","ure","dni","tednov","mesecev","let"), recentlyString = "pred kratkim", shortlyString = "kmalu")

        "lt" -> config(locale, "prieš {0}", "po {0}", "ką tik",
            u3("sekundė","minutė","valanda","diena","savaitė","mėnuo","metai",
               "sekundės","minutės","valandos","dienos","savaitės","mėnesiai","metai",
               "sekundžių","minučių","valandų","dienų","savaičių","mėnesių","metų"), recentlyString = "neseniai", shortlyString = "netrukus")

        "lv" -> config(locale, "pirms {0}", "pēc {0}", "tikko",
            u3("sekunde","minūte","stunda","diena","nedēļa","mēnesis","gads",
               "sekundes","minūtes","stundas","dienas","nedēļas","mēneši","gadi",
               "sekundēm","minūtēm","stundām","dienām","nedēļām","mēnešiem","gadiem"), recentlyString = "nesen", shortlyString = "drīzumā")

        "sq" -> config(locale, "{0} më parë", "pas {0}", "tani",
            u2("sekondë","minutë","orë","ditë","javë","muaj","vit",
               "sekonda","minuta","orë","ditë","javë","muaj","vite"), recentlyString = "kohët e fundit", shortlyString = "së shpejti")

        // ── East Asian / SE Asian (no unit separator, single form) ───────────
        "ja" -> config(locale, "{0}前", "{0}後", "たった今",
            u2("秒","分","時間","日","週間","ヶ月","年",
               "秒","分","時間","日","週間","ヶ月","年"), recentlyString = "最近", sep = "", shortlyString = "まもなく")

        "zh-TW", "zh-HK", "zh-Hant" -> config(locale, "{0}前", "{0}後", "剛剛",
            u2("秒","分","小時","天","週","月","年",
               "秒","分","小時","天","週","月","年"), recentlyString = "最近", sep = "", shortlyString = "即將")

        "zh", "zh-Hans" -> config(locale, "{0}前", "{0}后", "刚刚",
            u2("秒","分","小时","天","周","月","年",
               "秒","分","小时","天","周","月","年"), recentlyString = "最近", sep = "", shortlyString = "即将")

        "ko" -> config(locale, "{0} 전", "{0} 후", "방금",
            u2("초","분","시간","일","주","달","년",
               "초","분","시간","일","주","달","년"), recentlyString = "최근에", sep = "", shortlyString = "곧")

        "th" -> config(locale, "{0}ที่แล้ว", "อีก {0}", "เมื่อกี้",
            u2("วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี",
               "วินาที","นาที","ชั่วโมง","วัน","สัปดาห์","เดือน","ปี"), recentlyString = "เมื่อเร็วๆ นี้", sep = "", shortlyString = "เร็วๆ นี้")

        "vi" -> config(locale, "{0} trước", "{0} nữa", "vừa xong",
            u2("giây","phút","giờ","ngày","tuần","tháng","نăm",
               "giây","phút","giờ","ngày","tuần","tháng","năm"), recentlyString = "gần đây", shortlyString = "sắp tới")

        "iu" -> config(locale, "{0} ᖄᖏᖅᑐᖅ", "{0} ᐊᓂᒍᖅᐸᑦ", "ᒫᓐᓇᑲᐅᑎᒋ",
            u4("ᓯᑲᓐᑎ","ᓯᑲᓐᑎᒃ","ᓯᑲᓐᑎᑦ","ᓯᑲᓐᑎᑦ",
               "ᒥᓂᑎ","ᒥᓂᑎᒃ","ᒥᓂᑎᑦ","ᒥᓂᑎᑦ",
               "ᐃᑲᕐᕋᖅ","ᐃᑲᕐᕋᒃ","ᐃᑲᕐᕋᐃᑦ","ᐃᑲᕐᕋᐃᑦ",
               "ᐅᓪლᓗᖅ","ᐅᓪᓗᒃ","ᐅᓪᓗᐃᑦ","ᐅᓪᓗᐃᑦ",
               "ᐱᓇᓱᐊᕈᓯᖅ","ᐱᓇᓱᐊᕈᓯᒃ","ᐱᓇᓱᐊᕈᓰᑦ","ᐱᓇᓱᐊᕈᓰᑦ",
               "ᑕᖅᑭᖅ","ᑕᖅᑭᒃ","ᑕᖅᑮᑦ","ᑕᖅᑮᑦ",
               "ᐊᕐᕌᒍ","ᐊᕐᕌᒍᒃ","ᐊᕐᕌᒍᐃᑦ","ᐊᕐᕌᒍᐃᑦ"), recentlyString = "ᒫᓐᓇᓵᖅ")

        // ── South / Central Asian ─────────────────────────────────────────────
        "hi" -> config(locale, "{0} पहले", "{0} बाद", "अभी",
            u2("सेकंड","मिनट","घंटा","दिन","सप्ताह","महीना","वर्ष",
               "सेकंड","मिनट","घंटे","दिन","सप्ताह","महीने","वर्ष"), recentlyString = "हाल ही में", shortlyString = "शीघ्र ही")

        "id" -> config(locale, "{0} yang lalu", "dalam {0}", "baru saja",
            u2("detik","menit","jam","hari","minggu","bulan","tahun",
               "detik","menit","jam","hari","minggu","bulan","tahun"), recentlyString = "baru-baru ini", shortlyString = "segera")

        "ms" -> config(locale, "{0} yang lalu", "dalam {0}", "baru sahaja",
            u2("saat","minit","jam","hari","minggu","bulan","tahun",
               "saat","minit","jam","hari","minggu","bulan","tahun"), recentlyString = "baru-baru ini", shortlyString = "tidak lama lagi")

        "sw" -> config(locale, "{0} iliyopita", "baada ya {0}", "sasa hivi",
            u2("sekunde","dakika","saa","siku","wiki","mwezi","mwaka",
               "sekunde","dakika","saa","siku","wiki","miezi","miaka"), recentlyString = "hivi karibuni", shortlyString = "hivi punde")

        // ── Middle Eastern ────────────────────────────────────────────────────
        "ar" -> config(locale, "منذ {0}", "خلال {0}", "الآن",
            u6("ثانية","ثانية","ثانيتان","ثوانٍ","ثانية","ثانية",
               "دقيقة","دقيقة","دقيقتان","دقائق","دقيقة","دقيقة",
               "ساعة","ساعة","ساعتان","ساعات","ساعة","ساعة",
               "يوم","يوم","يومان","أيام","يوم","يوم",
               "أسبوع","أسبوع","أسبوعان","أسابيع","أسبوع","أسبوع",
               "شهر","شهر","شهران","أشهر","شهر","شهر",
               "سنة","سنة","سنتان","سنوات","سنة","سنة"), recentlyString = "مؤخرا", shortlyString = "قريبا")

        "fa" -> config(locale, "{0} پیش", "{0} دیگر", "همین الان",
            u2("ثانية","دقيقة","ساعة","روز","هفته","ماه","سال",
               "ثانية","دقيقة","ساعت","روز","هفته","ماه","سال"), recentlyString = "به تازگی", shortlyString = "به زودی")

        "he" -> config(locale, "לפני {0}", "בעוד {0}", "ממש עכשיו",
            u4("שנייה","שנייה","שניות","שניות",
               "דקה","דקה","דקות","דקות",
               "שעה","שעה","שעות","שעות",
               "יום","יום","ימים","ימים",
               "שבוע","שבוע","שבועות","שבועות",
               "חודש","חודש","חודשים","חודשים",
               "שנה","שנה","שנים","שנים"), recentlyString = "לאחרונה", shortlyString = "בקרוב")

        // ── Turkic ────────────────────────────────────────────────────────────
        "tr" -> config(locale, "{0} önce", "{0} sonra", "şimdi",
            u2("saniye","dakika","saat","gün","hafta","ay","yıl",
               "saniye","dakika","saat","gün","hafta","ay","yıl"), recentlyString = "son zamanlarda", shortlyString = "yakında")

        "az" -> config(locale, "{0} əvvəl", "{0} sonra", "indi",
            u2("saniyə","dəqiqə","saat","gün","həftə","ay","il",
               "saniyə","dəqiqə","saat","gün","həftə","ay","il"), recentlyString = "bu yaxınlarda", shortlyString = "tezliklə")

        "uz" -> config(locale, "{0} oldin", "{0} keyin", "hozir",
            u2("soniya","daqiqa","soat","kun","hafta","oy","yil",
               "soniya","daqiqa","soat","kun","hafta","oy","yil"), recentlyString = "yaqinda", shortlyString = "tez orada")

        "kk" -> config(locale, "{0} бұрын", "{0} кейін", "қазір",
            u2("секунд","минут","сағат","күн","апта","ай","жыл",
               "секунд","минут","сағат","күн","апта","ай","жыл"), recentlyString = "жақында", shortlyString = "жуырда")

        // ── Finno-Ugric ───────────────────────────────────────────────────────
        "fi" -> config(locale, "{0} sitten", "{0} päästä", "juuri nyt",
            u2("sekunti","minuutti","tunti","päivä","viikko","kuukausi","vuosi",
               "sekuntia","minuuttia","tuntia","päivää","viikkoa","kuukautta","vuotta"), recentlyString = "äskettäin", shortlyString = "pian")

        "et" -> config(locale, "{0} tagasi", "{0} pärast", "just nüüd",
            u2("sekund","minut","tund","päev","nädal","kuu","aasta",
               "sekundit","minutit","tundi","päeva","nädalat","kuud","aastat"), recentlyString = "hiljuti", shortlyString = "varsti")

        "hu" -> config(locale, "{0} ezelőtt", "{0} múlva", "éppen most",
            u2("másodperc","perc","óra","nap","hét","hónap","év",
               "másodperc","perc","óra","nap","hét","hónap","év"), recentlyString = "nemrég", shortlyString = "hamarosan")

        // ── Other ─────────────────────────────────────────────────────────────
        "el" -> config(locale, "πριν {0}", "σε {0}", "μόλις τώρα",
            u2("δευτερόλεπτο","λεπτό","ώρα","ημέρα","εβδομάδα","μήνας","έτος",
               "δευτερόλεπτα","λεπτά","ώρες","ημέρες","εβδομάδες","μήνες","έτη"), recentlyString = "πρόσφατα", shortlyString = "σύντομα")

        "eu" -> config(locale, "duela {0}", "{0} barru", "orain bertan",
            u2("segundo","minutu","ordu","egun","aste","hilabete","urte",
               "segundo","minutu","ordu","egun","aste","hilabete","urte"), recentlyString = "berriki", shortlyString = "laster")

        "hy" -> config(locale, "{0} առաջ", "{0} հետո", "հենց հիմա",
            u2("վայրկյան","րոպե","ժամ","օր","շաբաթ","ամիս","տարի",
               "վայրկյան","րոպե","ժամ","օր","շաբաթ","ամիս","տարի"), recentlyString = "վերջերս", shortlyString = "շուտով")

        "ka" -> config(locale, "{0}ის წინ", "{0}ში", "ახლავე",
            u2("წამ","წუთ","საათ","დღ","კვირ","თვ","წლ",
               "წამ","წუთ","საათ","დღ","კვირ","თვ","წლ"), recentlyString = "ახლახან", sep = "", shortlyString = "მალე")

        else -> if (tag == "en") enConfig(locale, style) else buildRelativeTimeConfig("en", locale, style)
    }
}

@Volatile private var configCache = emptyMap<Pair<String, RelativeStyle>, RelativeTimeConfig>()

/**
 * Returns the [RelativeTimeConfig] for [locale], building and caching it on first use.
 * Supports full BCP 47 subtag fallback: e.g. "en-ZA" → "en" → "en" default.
 *
 * Example:
 * ```kotlin
 * val config = relativeTimeConfigFor(Locale("fr"), RelativeStyle.Short)
 * println(config.todayString) // "Aujourd'hui"
 * ```
 *
 * @param locale The locale defining the localization rules.
 * @param relativeStyle The style (Long, Short, None) to use for the configuration.
 * @return a [RelativeTimeConfig] for the specified [locale] and [relativeStyle].
 */
fun relativeTimeConfigFor(locale: Locale, relativeStyle: RelativeStyle = RelativeStyle.Long): RelativeTimeConfig {
    val fullTag = if (locale.regionCode != null) "${locale.languageCode}-${locale.regionCode}" else locale.languageCode
    var currentTag = fullTag
    while (currentTag.isNotEmpty()) {
        val key = currentTag to relativeStyle
        val cached = configCache[key]
        if (cached != null) return cached
        if (isRelativeTimeTagSupported(currentTag)) {
            val built = buildRelativeTimeConfig(currentTag, locale, relativeStyle)
            val oldCache = configCache
            if (!oldCache.containsKey(key)) {
                val newCache = if (oldCache.size >= 150) {
                    mapOf(key to built)
                } else {
                    oldCache + (key to built)
                }
                configCache = newCache
            }
            return built
        }
        currentTag = currentTag.substringBeforeLast('-', "")
    }
    val defaultKey = "en" to relativeStyle
    configCache[defaultKey]?.let { return it }
    val built = buildRelativeTimeConfig("en", Locales.English, relativeStyle)
    val oldCache = configCache
    if (!oldCache.containsKey(defaultKey)) {
        val newCache = if (oldCache.size >= 150) {
            mapOf(defaultKey to built)
        } else {
            oldCache + (defaultKey to built)
        }
        configCache = newCache
    }
    return built
}
