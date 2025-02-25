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
    timeStyle: DateTimeStyle = DateTimeStyle.SHORT,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = MultiplatformDateFormatter.formatDateTime(
    localDateTime = toLocalDateTime(timeZone = timeZone),
    dateStyle = dateStyle,
    timeStyle = timeStyle,
    locale.toLanguageTag(),
    timeZone = timeZone,
    twentyFourHour = is24HourFormat(locale = locale)
) ?: toString()


fun Instant.formatRelative(
    until: Duration = 5.days,
    dateStyle: DateTimeStyle = DateTimeStyle.SHORT,
    timeStyle: DateTimeStyle = DateTimeStyle.LONG,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale = Locale.current,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
) {
    val untilFuture = plus(until)
    if (Clock.System.now() < untilFuture) {
        val ago = Clock.System.now().minus(this)

        ago.formatRelative(
            style = timeStyle,
            relativeTime = relativeTime,
            languageTag = locale.toLanguageTag(),
            roundToNearestUnit = true
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
