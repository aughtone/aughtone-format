package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDateFormatter
import io.github.aughtone.datetime.format.resources.is24HourFormat
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days


fun Instant.format(
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    timeStyle: DateTimeStyle = DateTimeStyle.LONG,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    languageTag = locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
) ?: toString()


fun Instant.formatRelative(
    until: Duration = 5.days,
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    timeStyle: DateTimeStyle = DateTimeStyle.SHORT,
    relativeStyle: RelativeStyle = RelativeStyle.LONG,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    relativeTo: Instant = Clock.System.now()
):String {
    val untilFuture = plus(until)
    return if (relativeTo < untilFuture) {
        val ago = relativeTo.minus(this)

        ago.formatRelative(
            style = relativeStyle,
            relativeTime = relativeTime,
        )
    } else {
        format(
            dateStyle = dateStyle,
            timeStyle = timeStyle,
            locale = locale,
            timeZone = timeZone
        )
    }


}
