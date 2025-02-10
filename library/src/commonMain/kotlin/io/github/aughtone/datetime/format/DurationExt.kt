package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.TimeUnitResources
import kotlin.math.roundToInt
import kotlin.time.Duration

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
