package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.TimeUnitResources
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.DurationUnit

fun Duration.formatRelative(
    style: DateTimeStyle,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale = Locale.current,
): String = formatRelative(
    style = style,
    relativeTime = relativeTime,
    languageTag = locale.toLanguageTag(),
)

internal fun Duration.formatRelative(
    style: DateTimeStyle,
    relativeTime: RelativeTime = RelativeTime.Present,
    languageTag: String,
): String {
    return when (style) {
        DateTimeStyle.SHORT -> toString()
        DateTimeStyle.MEDIUM -> toString()
        DateTimeStyle.LONG -> toString()
        DateTimeStyle.FULL -> toString()
        DateTimeStyle.NONE -> toIsoString()
    }
//    DurationUnit
//    1.seconds
//toComponents { days, hours, minutes, seconds, nanoseconds ->  }
}

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

/**
 * Returns the given [duration] in human-readable format.
 *
 * Ported from source: https://github.com/jacobras/Human-Readable
 */
fun Duration.formatHumanReadableRelative(
    relativeTime: RelativeTime = RelativeTime.Present,
): String {
    val secondsAgo = inWholeSeconds.toInt()
    val hoursAgo = inWholeHours.toInt()
    val daysAgo = inWholeDays.toInt()
    val weeksAgo = (inWholeDays / 7f).roundToInt()
    val monthsAgo = (inWholeDays / 30.5f).roundToInt()
    val yearsAgo = (inWholeDays / 365).toInt()

    return when {
        secondsAgo < 60 -> {
            "$secondsAgo ${TimeUnitResources.Seconds.format(secondsAgo, relativeTime)}"
        }

        secondsAgo < 3600 -> {
            val minutes = inWholeMinutes.toInt()
            "$minutes ${TimeUnitResources.Minutes.format(minutes, relativeTime)}"
        }

        daysAgo < 1 -> {
            "$hoursAgo ${TimeUnitResources.Hours.format(hoursAgo, relativeTime)}"
        }

        daysAgo < 7 -> {
            "$daysAgo ${TimeUnitResources.Days.format(daysAgo, relativeTime)}"
        }

        daysAgo < 30 -> {
            "$weeksAgo ${TimeUnitResources.Weeks.format(weeksAgo, relativeTime)}"
        }

        monthsAgo < 12 || yearsAgo == 0 -> {
            "$monthsAgo ${TimeUnitResources.Months.format(monthsAgo, relativeTime)}"
        }

        else -> {
            "$yearsAgo ${TimeUnitResources.Years.format(yearsAgo, relativeTime)}"
        }
    }
}
