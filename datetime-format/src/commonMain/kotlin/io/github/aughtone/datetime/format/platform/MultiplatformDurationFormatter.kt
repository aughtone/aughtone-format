package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.RelativeStyle
import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.resources.TimeUnitResources
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.DurationUnit

object MultiplatformDurationFormatter {
    fun formatRelative(
        duration: Duration,
        style: RelativeStyle,
        relativeTime: RelativeTime,
    ): String = when (style) {
        RelativeStyle.SHORT -> duration.toString()
        RelativeStyle.LONG -> formatRelativeLong(
            duration = duration,
            relativeTime = relativeTime
        )
        RelativeStyle.NONE -> ""
    }
}

/**
 * Returns the given [duration] in human-readable format.
 *
 * Ported from source: https://github.com/jacobras/Human-Readable
 */
private fun formatRelativeLong(
    duration: Duration,
    relativeTime: RelativeTime = RelativeTime.Present,
): String {
    val millisecondsAgo = duration.inWholeMilliseconds.toInt()
    val secondsAgo = duration.inWholeSeconds.toInt()
    val hoursAgo = duration.inWholeHours.toInt()
    val daysAgo = duration.inWholeDays.toInt()
    val weeksAgo = (duration.inWholeDays / 7f).roundToInt()
    val monthsAgo = (duration.inWholeDays / 30.5f).roundToInt()
    val yearsAgo = (duration.inWholeDays / 365).toInt()

    return when {

        millisecondsAgo < 1000 -> {
            val rounded = duration.absoluteValue.toDouble(DurationUnit.SECONDS).roundToInt()
            "$rounded ${TimeUnitResources.Seconds.format(rounded, relativeTime)}"

        }

        secondsAgo < 60 -> {
            val rounded = duration.absoluteValue.toDouble(DurationUnit.SECONDS).roundToInt()
            "$rounded ${
                TimeUnitResources.Seconds.format(
                    rounded,
                    relativeTime
                )
            }"
        }

        secondsAgo < 3600 -> {
            val minutes = duration.inWholeMinutes.toInt()
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

/**
 * Returns the given [duration] in human-readable format.
 *
 * Ported from source: https://github.com/jacobras/Human-Readable
 */
private fun formatHumanReadableRelative(
    duration: Duration,
    relativeTime: RelativeTime = RelativeTime.Present,
): String {
    val secondsAgo = duration.inWholeSeconds.toInt()
    val hoursAgo = duration.inWholeHours.toInt()
    val daysAgo = duration.inWholeDays.toInt()
    val weeksAgo = (duration.inWholeDays / 7f).roundToInt()
    val monthsAgo = (duration.inWholeDays / 30.5f).roundToInt()
    val yearsAgo = (duration.inWholeDays / 365).toInt()

    return when {
        secondsAgo < 60 -> {
            "$secondsAgo ${TimeUnitResources.Seconds.format(secondsAgo, relativeTime)}"
        }

        secondsAgo < 3600 -> {
            val minutes = duration.inWholeMinutes.toInt()
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
