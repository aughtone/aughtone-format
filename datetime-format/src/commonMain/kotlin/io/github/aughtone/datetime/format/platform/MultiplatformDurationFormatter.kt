package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.resources.TimeUnitResources
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.DurationUnit

object MultiplatformDurationFormatter {
    fun formatRelative(
        duration: Duration,
        style: DateTimeStyle,
        relativeTime: RelativeTime,
        languageTag: String,
        roundToNearestUnit: Boolean,
    ): String = when (style) {
        DateTimeStyle.SHORT, DateTimeStyle.MEDIUM -> duration.toString()
//        DateTimeStyle.MEDIUM -> formatRelativeMedium(duration)
        DateTimeStyle.LONG, DateTimeStyle.FULL -> formatRelativeLong(
            duration = duration,
            relativeTime = relativeTime
        )
//        DateTimeStyle.FULL -> duration.toString()
        DateTimeStyle.NONE -> ""
    }
}

fun formatRelativeMedium(
    duration: Duration,
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
            duration.toString(unit = DurationUnit.SECONDS, 0).replace(
                "s", if (millisecondsAgo >= 500) {
                    " sec"
                } else {
                    " secs"
                }
            )
        }

        secondsAgo < 60 -> {
            // XXX not liking this, I'm questioning if the medium style is even necessary for duration.
            duration.toString(unit = DurationUnit.SECONDS, 0)
                .replace(
                    "s",
                    if (secondsAgo == 1 && (millisecondsAgo == 1000 || millisecondsAgo < 500)) " sec" else " secs"
                )
        }

        secondsAgo < 3600 -> {
            val minutes = duration.inWholeMinutes.toInt()
            duration.toString(unit = DurationUnit.MINUTES, 0)
                .replace("m", if (minutes == 1) " min" else " mins")
        }

        daysAgo < 1 -> {
            duration.toString(unit = DurationUnit.HOURS, 0)
                .replace("h", if (hoursAgo == 1) " hr" else " hrs")
        }

        daysAgo < 7 -> {
            duration.toString(unit = DurationUnit.DAYS, 0)
                .replace("d", if (daysAgo == 1) " day" else " days")
        }

        daysAgo < 30 -> {
            "$weeksAgo ${if (weeksAgo == 1) " wk" else " wks"}"
        }

        monthsAgo < 12 || yearsAgo == 0 -> {
            "$monthsAgo ${if (monthsAgo == 1) " mo" else " mos"}"
        }

        else -> {
            "$yearsAgo ${if (yearsAgo == 1) " yr" else " yrs"}"
        }
    }

}

/**
 * Returns the given [duration] in human-readable format.
 *
 * Ported from source: https://github.com/jacobras/Human-Readable
 */
fun formatRelativeLong(
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
