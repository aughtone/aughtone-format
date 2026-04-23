package io.github.aughtone.readable.duration

import io.github.aughtone.toolbox.Formatter
import io.github.aughtone.types.locale.Locale
import kotlin.math.abs
import kotlin.math.roundToLong

/**
 * Functional formatter for Durations.
 */
typealias DurationFormatter = Formatter<kotlin.time.Duration>

/**
 * Localized unit names for duration.
 */
private data class DurationUnitNames(
    val singular: String,
    val plural: String
)

/**
 * Factory to create a [DurationFormatter] for a set of unit names.
 */
private fun createDurationFormatter(
    units: Map<String, DurationUnitNames>
): DurationFormatter = { duration ->
    val totalSeconds = abs(duration.toDouble(kotlin.time.DurationUnit.SECONDS))
    
    val (value, unitKey) = when {
        totalSeconds >= 31536000 -> (totalSeconds / 31536000.0) to "year"
        totalSeconds >= 2592000 -> (totalSeconds / 2592000.0) to "month"
        totalSeconds >= 604800 && (totalSeconds / 604800.0).roundToLong() <= 3 -> (totalSeconds / 604800.0) to "week"
        totalSeconds >= 86400 -> (totalSeconds / 86400.0) to "day"
        totalSeconds >= 3600 -> (totalSeconds / 3600.0) to "hour"
        totalSeconds >= 60 -> (totalSeconds / 60.0) to "minute"
        else -> totalSeconds to "second"
    }

    val roundedValue = value.roundToLong()
    val names = units[unitKey] ?: DurationUnitNames(unitKey, unitKey + "s")
    val label = if (roundedValue == 1L) names.singular else names.plural

    "$roundedValue $label"
}

// Unit Set Definitions
private val enUnits = mapOf("second" to DurationUnitNames("second", "seconds"), "minute" to DurationUnitNames("minute", "minutes"), "hour" to DurationUnitNames("hour", "hours"), "day" to DurationUnitNames("day", "days"), "week" to DurationUnitNames("week", "weeks"), "month" to DurationUnitNames("month", "months"), "year" to DurationUnitNames("year", "years"))
private val frUnits = mapOf("second" to DurationUnitNames("seconde", "secondes"), "minute" to DurationUnitNames("minute", "minutes"), "hour" to DurationUnitNames("heure", "heures"), "day" to DurationUnitNames("jour", "jours"), "week" to DurationUnitNames("semaine", "semaines"), "month" to DurationUnitNames("mois", "mois"), "year" to DurationUnitNames("an", "ans"))
private val deUnits = mapOf("second" to DurationUnitNames("Sekunde", "Sekunden"), "minute" to DurationUnitNames("Minute", "Minuten"), "hour" to DurationUnitNames("Stunde", "Stunden"), "day" to DurationUnitNames("Tag", "Tage"), "week" to DurationUnitNames("Woche", "Wochen"), "month" to DurationUnitNames("Monat", "Monate"), "year" to DurationUnitNames("Jahr", "Jahre"))
private val esUnits = mapOf("second" to DurationUnitNames("segundo", "segundos"), "minute" to DurationUnitNames("minuto", "minutos"), "hour" to DurationUnitNames("hora", "horas"), "day" to DurationUnitNames("día", "días"), "week" to DurationUnitNames("semana", "semanas"), "month" to DurationUnitNames("mes", "meses"), "year" to DurationUnitNames("año", "años"))
private val ruUnits = mapOf("second" to DurationUnitNames("секунда", "секунды"), "minute" to DurationUnitNames("минута", "минуты"), "hour" to DurationUnitNames("час", "часа"), "day" to DurationUnitNames("день", "дни"), "week" to DurationUnitNames("неделя", "недели"), "month" to DurationUnitNames("месяц", "месяца"), "year" to DurationUnitNames("год", "года"))
private val scUnits = mapOf("second" to DurationUnitNames("sekund", "sekunder"), "minute" to DurationUnitNames("minut", "minuter"), "hour" to DurationUnitNames("time", "timer"), "day" to DurationUnitNames("dag", "dager"), "week" to DurationUnitNames("uke", "uker"), "month" to DurationUnitNames("måned", "måneder"), "year" to DurationUnitNames("år", "år"))
private val jaUnits = mapOf("second" to DurationUnitNames("秒", "秒"), "minute" to DurationUnitNames("分", "分"), "hour" to DurationUnitNames("時間", "時間"), "day" to DurationUnitNames("日", "日"), "week" to DurationUnitNames("週間", "週間"), "month" to DurationUnitNames("ヶ月", "ヶ月"), "year" to DurationUnitNames("年", "年"))

internal val durationFormatterMap: Map<String, DurationFormatter> = mutableMapOf<String, DurationFormatter>().apply {
    val en = createDurationFormatter(enUnits)
    val fr = createDurationFormatter(frUnits)
    val de = createDurationFormatter(deUnits)
    val es = createDurationFormatter(esUnits)
    val ru = createDurationFormatter(ruUnits)
    val sc = createDurationFormatter(scUnits)
    val ja = createDurationFormatter(jaUnits)
    
    // Germanic
    listOf("en", "af", "nl", "id", "ms", "sw").forEach { put(it, en) }
    listOf("de").forEach { put(it, de) }
    listOf("da", "nb", "no", "nn", "sv", "is").forEach { put(it, sc) }
    
    // Romance
    listOf("fr").forEach { put(it, fr) }
    listOf("es", "it", "pt", "ca", "gl", "ro").forEach { put(it, es) }
    
    // Slavic / Baltic
    listOf("ru", "uk", "be", "bg", "pl", "cs", "sk", "hr", "sr", "mk", "sl", "lt", "lv", "sq").forEach { put(it, ru) }
    
    // East Asian / SE Asian
    listOf("ja", "ko", "zh", "th", "vi").forEach { put(it, ja) }
    
    // Central / South Asian / Middle Eastern
    listOf("hi").forEach { put(it, createDurationFormatter(mapOf("second" to DurationUnitNames("सेकंड", "सेकंड"), "minute" to DurationUnitNames("मिनट", "मिनट"), "hour" to DurationUnitNames("घंटा", "घंटे"), "day" to DurationUnitNames("दिन", "दिन"), "week" to DurationUnitNames("सप्ताह", "सप्ताह"), "month" to DurationUnitNames("महीना", "महीने"), "year" to DurationUnitNames("वर्ष", "वर्ष")))) }
    listOf("ar", "fa").forEach { put(it, createDurationFormatter(mapOf("second" to DurationUnitNames("ثانية", "ثوانٍ"), "minute" to DurationUnitNames("دقيقة", "دقائق"), "hour" to DurationUnitNames("ساعة", "ساعات"), "day" to DurationUnitNames("يوم", "أيام"), "week" to DurationUnitNames("أسبوع", "أسابيع"), "month" to DurationUnitNames("شهر", "شهور"), "year" to DurationUnitNames("سنة", "سنوات")))) }
    listOf("tr", "az", "uz", "kk").forEach { put(it, createDurationFormatter(mapOf("second" to DurationUnitNames("saniye", "saniye"), "minute" to DurationUnitNames("dakika", "dakika"), "hour" to DurationUnitNames("saat", "saat"), "day" to DurationUnitNames("gün", "gün"), "week" to DurationUnitNames("hafta", "hafta"), "month" to DurationUnitNames("ay", "ay"), "year" to DurationUnitNames("yıl", "yıl")))) }
    
    // Finno-Ugric / Other
    listOf("fi", "et", "hu").forEach { put(it, createDurationFormatter(mapOf("second" to DurationUnitNames("sekunti", "sekuntia"), "minute" to DurationUnitNames("minuutti", "minuuttia"), "hour" to DurationUnitNames("tunti", "tuntia"), "day" to DurationUnitNames("päivä", "päivää"), "week" to DurationUnitNames("viikko", "viikkoa"), "month" to DurationUnitNames("kuukausi", "kuukautta"), "year" to DurationUnitNames("vuosi", "vuotta")))) }
    listOf("el").forEach { put(it, createDurationFormatter(mapOf("second" to DurationUnitNames("δευτερόλεπτο", "δευτερόλεπτα"), "minute" to DurationUnitNames("λεπτό", "λεπτά"), "hour" to DurationUnitNames("ώρα", "ώρες"), "day" to DurationUnitNames("ημέρα", "ημέρες"), "week" to DurationUnitNames("εβδομάδα", "εβδομάδες"), "month" to DurationUnitNames("μήνας", "μήνες"), "year" to DurationUnitNames("έτος", "έτη")))) }
    listOf("eu").forEach { put(it, createDurationFormatter(mapOf("second" to DurationUnitNames("segundo", "segundo"), "minute" to DurationUnitNames("minutu", "minutu"), "hour" to DurationUnitNames("ordu", "ordu"), "day" to DurationUnitNames("egun", "egun"), "week" to DurationUnitNames("aste", "aste"), "month" to DurationUnitNames("hilabete", "hilabete"), "year" to DurationUnitNames("urte", "urte")))) }
}

/**
 * Retrieves the [DurationFormatter] for a given [Locale].
 */
fun durationFormatterFor(locale: Locale): DurationFormatter {
    return durationFormatterMap[locale.languageCode] ?: durationFormatterMap["en"]!!
}
