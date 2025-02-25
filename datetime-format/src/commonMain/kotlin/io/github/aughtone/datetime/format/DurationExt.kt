package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDurationFormatter
import kotlin.time.Duration
import kotlin.time.DurationUnit

fun Duration.formatRelative(
    style: DateTimeStyle,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale = Locale.current,
    roundToNearestUnit: Boolean = true,
): String = formatRelative(
    style = style,
    relativeTime = relativeTime,
    languageTag = locale.toLanguageTag(),
    roundToNearestUnit = roundToNearestUnit,
)

internal fun Duration.formatRelative(
    style: DateTimeStyle,
    relativeTime: RelativeTime = RelativeTime.Present,
    languageTag: String,
    roundToNearestUnit: Boolean = true,
): String = MultiplatformDurationFormatter.formatRelative(
    duration = this,
    style = style,
    relativeTime = relativeTime,
    languageTag = languageTag,
    roundToNearestUnit = roundToNearestUnit
)

/**
 * If the style is NONE, the relativeTime, durationUnit, and locale will be ignored
 */
fun Duration.formatRelative(
    style: DateTimeStyle,
    relativeTime: RelativeTime = RelativeTime.Present,
    durationUnit: DurationUnit = DurationUnit.MILLISECONDS,
    locale: Locale = Locale.current,
): String {
    return when (style) {
        DateTimeStyle.SHORT -> toString(durationUnit)
        DateTimeStyle.MEDIUM -> toString(durationUnit)
        DateTimeStyle.LONG -> toString(durationUnit)
        DateTimeStyle.FULL -> toString(durationUnit)
        DateTimeStyle.NONE -> toIsoString()
    }
//    DurationUnit
//    1.seconds
//toComponents { days, hours, minutes, seconds, nanoseconds ->  }
}
