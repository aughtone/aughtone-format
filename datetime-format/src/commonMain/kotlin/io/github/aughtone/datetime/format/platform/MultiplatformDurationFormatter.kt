package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.RelativeStyle
import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.strings.TimeUnitPlurals
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.DurationUnit

object MultiplatformDurationFormatter {
    fun formatRelative(
        duration: Duration,
        style: RelativeStyle,
        relativeTime: RelativeTime,
    ): String = when (style) {
        RelativeStyle.SHORT -> formatRelativeShort(
            duration = duration,
            relativeTime = relativeTime
        )

        RelativeStyle.LONG -> formatRelativeLong(
            duration = duration,
            relativeTime = relativeTime
        )

        RelativeStyle.NONE -> ""
    }
}

private fun formatRelativeShort(
    duration: Duration,
    relativeTime: RelativeTime = RelativeTime.Present,
): String = when (relativeTime) {
    RelativeTime.Past -> Resources.getText().time_in_past.text.format(duration.toString())
    RelativeTime.Future -> Resources.getText().time_in_future.text.format(duration.toString())
    else -> duration.toString()
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

    val formattedTime = when {
        millisecondsAgo < 1000 -> {
            val rounded = duration.absoluteValue.toDouble(DurationUnit.SECONDS).roundToInt()
            "$rounded ${TimeUnitPlurals.Seconds.format(rounded, relativeTime)}"

        }

        secondsAgo < 60 -> {
            val rounded = duration.absoluteValue.toDouble(DurationUnit.SECONDS).roundToInt()
            "$rounded ${
                TimeUnitPlurals.Seconds.format(
                    rounded,
                    relativeTime
                )
            }"
        }

        secondsAgo < 3600 -> {
            val minutes = duration.inWholeMinutes.toInt()
            "$minutes ${TimeUnitPlurals.Minutes.format(minutes, relativeTime)}"
        }

        daysAgo < 1 -> {
            "$hoursAgo ${TimeUnitPlurals.Hours.format(hoursAgo, relativeTime)}"
        }

        daysAgo < 7 -> {
            "$daysAgo ${TimeUnitPlurals.Days.format(daysAgo, relativeTime)}"
        }

        daysAgo < 30 -> {
            "$weeksAgo ${TimeUnitPlurals.Weeks.format(weeksAgo, relativeTime)}"
        }

        monthsAgo < 12 || yearsAgo == 0 -> {
            "$monthsAgo ${TimeUnitPlurals.Months.format(monthsAgo, relativeTime)}"
        }

        else -> {
            "$yearsAgo ${TimeUnitPlurals.Years.format(yearsAgo, relativeTime)}"
        }
    }

    return when (relativeTime) {
        RelativeTime.Past -> Resources.getText().time_in_past.text.format(formattedTime)
        RelativeTime.Future -> Resources.getText().time_in_future.text.format(formattedTime)
        else -> formattedTime
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
            "$secondsAgo ${TimeUnitPlurals.Seconds.format(secondsAgo, relativeTime)}"
        }

        secondsAgo < 3600 -> {
            val minutes = duration.inWholeMinutes.toInt()
            "$minutes ${TimeUnitPlurals.Minutes.format(minutes, relativeTime)}"
        }

        daysAgo < 1 -> {
            "$hoursAgo ${TimeUnitPlurals.Hours.format(hoursAgo, relativeTime)}"
        }

        daysAgo < 7 -> {
            "$daysAgo ${TimeUnitPlurals.Days.format(daysAgo, relativeTime)}"
        }

        daysAgo < 30 -> {
            "$weeksAgo ${TimeUnitPlurals.Weeks.format(weeksAgo, relativeTime)}"
        }

        monthsAgo < 12 || yearsAgo == 0 -> {
            "$monthsAgo ${TimeUnitPlurals.Months.format(monthsAgo, relativeTime)}"
        }

        else -> {
            "$yearsAgo ${TimeUnitPlurals.Years.format(yearsAgo, relativeTime)}"
        }
    }
}
