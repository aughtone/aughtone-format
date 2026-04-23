package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetAt

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

    fun postFormatDate(
        dateStyle: DateTimeStyle,
        locale: io.github.aughtone.types.locale.Locale,
        date: kotlinx.datetime.LocalDate,
        formattedDate: String?,
        eraNames: EraNames? = null,
        numberingSystem: NumberingSystem? = null,
    ): String? {
        if (formattedDate == null) return null
        
        var result = when (dateStyle) {
            DateTimeStyle.Full -> {
                val eras = eraNames ?: io.github.aughtone.datetime.format.resources.Resources.getEraNames(locale, abbreviated = false)
                val era = if (date.year < 0) eras.bce else eras.ce
                "$formattedDate $era"
            }
            else -> formattedDate
        }

        if (numberingSystem != null) {
            result = result.applyNumberingSystem(numberingSystem)
        }

        return result
    }

    fun postFormatTime(
        timeStyle: DateTimeStyle,
        timeZone: TimeZone,
        instant: Instant,
        formattedTime: String?,
        numberingSystem: NumberingSystem? = null,
    ): String? {
        if (formattedTime == null) return null
        val offset: UtcOffset = timeZone.offsetAt(instant)
        
        var result = when (timeStyle) {
            DateTimeStyle.Long -> {
                "$formattedTime ${
                    TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(
                        timeZone = timeZone,
                        offset = offset
                    )
                }"
            }

            DateTimeStyle.Full -> {
                "$formattedTime ${
                    TimeZoneAbbreviationLookup.getTimeZoneFullName(
                        timeZone = timeZone,
                        offset = offset
                    )
                }"
            }

            else -> {
                formattedTime
            }
        }

        if (numberingSystem != null) {
            result = result.applyNumberingSystem(numberingSystem)
        }

        return result
    }
}
