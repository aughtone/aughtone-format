package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import io.github.aughtone.types.locale.Locale

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.applyNumberingSystem
import io.github.aughtone.datetime.format.resources.values.EraNames

/**
 * This object is really a hacky workaround for some oversights in the way the multiplatform
 * datetime library works for formatting. I expect it will eventually go away, but for the moment,
 * it is needed to handle some special cases.
 *
 * The Multiplatform library makes some things impossible to do in formatting, because the formatting
 * requires knowledge of the datetime being formatted in order to work.
 *
 * Some good examples of that are the Era, which needs to know if the formatted date is BCE or CE,
 * and the TimeZone in a format which people generally read, which also needs to know what the date
 * is so that it can switch from standard to daylight savings time at certain times of year.
 */
object MultiplatformPostFormatter {

    /**
     * Finishes a formatted date: adds the era where one is shown, and writes the digits in the
     * locale's numbering system (or [numberingSystem]).
     *
     * The era follows the date when [eraNames] is given, and always for a date before year 1,
     * whose year would otherwise read as one of the common era.
     */
    fun postFormatDate(
        dateStyle: DateTimeStyle,
        locale: Locale,
        date: kotlinx.datetime.LocalDate,
        formattedDate: String?,
        eraNames: EraNames? = null,
        numberingSystem: NumberingSystem? = null,
    ): String? = postFormatDate(dateStyle, locale, date, formattedDate, eraNames, numberingSystem, showEra = false)

    /**
     * As [postFormatDate], with [showEra] asking for the era on a date of the common era too. The
     * era is the locale's abbreviation ("BCE", "CE") unless [eraNames] gives other words.
     */
    internal fun postFormatDate(
        dateStyle: DateTimeStyle,
        locale: Locale,
        date: kotlinx.datetime.LocalDate,
        formattedDate: String?,
        eraNames: EraNames?,
        numberingSystem: NumberingSystem?,
        showEra: Boolean,
    ): String? {
        if (formattedDate == null) return null

        val beforeCommonEra = date.year <= 0
        var result = if (dateStyle != DateTimeStyle.None && (showEra || eraNames != null || beforeCommonEra)) {
            val eras = eraNames ?: io.github.aughtone.datetime.format.resources.Resources.getEraNames(locale, abbreviated = true)
            // ISO year 0 is 1 BCE and year -500 is 501 BCE: beside an era name the year is
            // counted from one and carries no sign.
            if (beforeCommonEra) "${formattedDate.withYearOfEra(date.year)} ${eras.bce}" else "$formattedDate ${eras.ce}"
        } else {
            formattedDate
        }

        val resolvedNumberingSystem = resolveNumberingSystem(numberingSystem, locale)
        if (resolvedNumberingSystem != null) {
            result = result.applyNumberingSystem(resolvedNumberingSystem)
        }

        return result
    }

    fun postFormatTime(
        timeStyle: DateTimeStyle,
        timeZone: TimeZone,
        instant: Instant,
        formattedTime: String?,
        locale: Locale = Locale(languageCode = "en", displayName = "English"),
        numberingSystem: NumberingSystem? = null,
    ): String? {
        if (formattedTime == null) return null

        var result = when (timeStyle) {
            DateTimeStyle.Long -> {
                "$formattedTime ${
                    TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(
                        timeZone = timeZone,
                        instant = instant,
                        locale = locale
                    )
                }"
            }

            DateTimeStyle.Full -> {
                "$formattedTime ${
                    TimeZoneAbbreviationLookup.getTimeZoneFullName(
                        timeZone = timeZone,
                        instant = instant,
                        locale = locale
                    )
                }"
            }

            else -> {
                formattedTime
            }
        }

        val resolvedNumberingSystem = resolveNumberingSystem(numberingSystem, locale)
        if (resolvedNumberingSystem != null) {
            result = result.applyNumberingSystem(resolvedNumberingSystem)
        }

        return result
    }

    /**
     * Rewrites the ISO [year] (zero or negative) inside this formatted date as its year of the
     * BCE era. The formatter printed it as `-500` (or zero-padded, `-0500`), or as a run of zeros
     * for year 0 — a form no day or month number ever takes — so the first such run is the year.
     */
    private fun String.withYearOfEra(year: Int): String {
        val yearOfEra = (1L - year).toString()
        var index = 0
        while (index < length) {
            if (!this[index].isAsciiDigit()) { index++; continue }
            var end = index
            while (end < length && this[end].isAsciiDigit()) end++
            val digits = substring(index, end)
            val signed = index > 0 && this[index - 1] == '-'
            val isYear = if (year == 0) digits.all { it == '0' } else signed && digits.trimStart('0') == (-year.toLong()).toString()
            if (isYear) return substring(0, if (signed) index - 1 else index) + yearOfEra + substring(end)
            index = end
        }
        return this
    }

    private fun Char.isAsciiDigit(): Boolean = this in '0'..'9'

    /** Writes [text]'s digits in [numberingSystem], or in [locale]'s own when it is `null`. */
    internal fun applyNumberingSystem(text: String, locale: Locale, numberingSystem: NumberingSystem?): String =
        resolveNumberingSystem(numberingSystem, locale)?.let { text.applyNumberingSystem(it) } ?: text

    /**
     * [numberingSystem], or the locale's default digits when it is `null`: Unicode CLDR's default
     * numbering system for the locale (CLDR 48). That is Latin digits for most locales, including
     * Hindi, Thai, Urdu in Pakistan and Arabic without a region; native digits for Persian,
     * Bengali, Marathi and Nepali, Urdu in India, and Arabic in the regions that write them.
     * `null` means Latin digits, written as the formatter produced them.
     */
    private fun resolveNumberingSystem(numberingSystem: NumberingSystem?, locale: Locale): NumberingSystem? {
        if (numberingSystem != null) return numberingSystem
        val region = locale.regionCode?.uppercase()
        return when (locale.languageCode) {
            "ar" -> if (region in ARABIC_INDIC_REGIONS) NumberingSystem.ARAB else null
            "fa", "ps", "ks" -> NumberingSystem.ARABEXT
            "ur" -> if (region == "IN") NumberingSystem.ARABEXT else null
            "pa" -> if (region == "PK") NumberingSystem.ARABEXT else null
            "sd" -> NumberingSystem.ARAB
            "bn" -> NumberingSystem.BENG
            "mr", "ne" -> NumberingSystem.DEVA
            else -> null
        }
    }

    /** The regions whose Arabic CLDR writes with Arabic-Indic digits; the rest use Latin digits. */
    private val ARABIC_INDIC_REGIONS = setOf(
        "BH", "DJ", "EG", "ER", "IL", "IQ", "JO", "KM", "KW", "LB", "MR", "OM", "PS", "QA", "SA", "SD", "SO", "SS", "SY", "TD", "YE",
    )
}

