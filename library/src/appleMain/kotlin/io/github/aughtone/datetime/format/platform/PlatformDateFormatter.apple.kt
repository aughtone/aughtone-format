package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import kotlinx.datetime.toNSTimeZone
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterFullStyle
import platform.Foundation.NSDateFormatterLongStyle
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSDateFormatterNoStyle
import platform.Foundation.NSDateFormatterShortStyle
import platform.Foundation.NSLocale

actual object PlatformDateFormatter:InternalDateFormatter {

    private fun getNSStyle(style: DateTimeStyle) = when (style) {
        DateTimeStyle.SHORT -> NSDateFormatterShortStyle
        DateTimeStyle.MEDIUM -> NSDateFormatterMediumStyle
        DateTimeStyle.LONG -> NSDateFormatterLongStyle
        DateTimeStyle.FULL -> NSDateFormatterFullStyle
        DateTimeStyle.NONE -> NSDateFormatterNoStyle
    }

    actual override fun formatDateTime(
        localDateTime: LocalDateTime,
        dateStyle: DateTimeStyle,
        timeStyle: DateTimeStyle,
        languageTag: String,
        timeZone: TimeZone,
        twentyFourHour: Boolean,
    ): String? = NSDateFormatter().apply {
        this.dateStyle = getNSStyle(dateStyle)//NSDateFormatterShortStyle
        this.timeStyle = getNSStyle(timeStyle)
        this.timeZone = timeZone.toNSTimeZone()
        this.locale = NSLocale(languageTag)
    }.stringFromDate(localDateTime.toInstant(timeZone).toNSDate())

}
