package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.resources.timezone.TimeZoneAbbreviationLookup
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetAt

/**
 * This object is really a hacky workaround for some oversights in the way the multiplatform
 * datetime library works for formatting. I expect it will eventually go away, but for the moment,
 * it is needed to hande some special cases.
 *
 * The Multiplatform makes some things impossible to do in formatting, because the formatting
 * requires knowledge of the datetime being formatted in order to work.
 *
 * Some good examples of that are the Era, which needs to know if the formatted date is BCE or CE.
 * and the TimeZone in a format which people generally ready, which also needs to know what the date
 * is so that it can switch from standard to daylight savings time at certain times of year.
 */
object MultiplatformPostFormatter {

    fun postFormatTime(
        timeStyle: DateTimeStyle,
        timeZone: TimeZone,
        instant: Instant,
        formatedTime: String?,
    ): String? {
        val offset: UtcOffset = timeZone.offsetAt(instant)
        return when (timeStyle) {
            DateTimeStyle.LONG -> {

                "$formatedTime ${
                    TimeZoneAbbreviationLookup.getTimeZoneAbbreviation(
                        timeZone = timeZone,
                        offset = offset
                    )
                }"
            }

            DateTimeStyle.FULL -> {
                "$formatedTime ${
                    TimeZoneAbbreviationLookup.getTimeZoneFullName(
                        timeZone = timeZone,
                        offset = offset
                    )
                }"
            }

            else -> {
                formatedTime
            }
        }
    }
}
