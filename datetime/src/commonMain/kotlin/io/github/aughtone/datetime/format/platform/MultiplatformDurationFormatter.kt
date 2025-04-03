package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.RelativeStyle
import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.resources.Resources
import nl.jacobras.humanreadable.HumanReadable
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
    RelativeTime.Past ->  Resources.getText().time_in_past.text.format(duration.toString())
    RelativeTime.Future -> Resources.getText().time_in_future.text.format(duration.toString())
    else -> duration.toString()
}
private fun formatRelativeLong(
    duration: Duration,
    relativeTime: RelativeTime = RelativeTime.Present,
):String =when (relativeTime) {
    RelativeTime.Past ->  Resources.getText().time_in_past.text.format(HumanReadable.duration(duration))
    RelativeTime.Future -> Resources.getText().time_in_future.text.format(HumanReadable.duration(duration))
    else -> HumanReadable.duration(duration)
}
