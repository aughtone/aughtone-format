package io.github.aughtone.datetime.format.platform

import io.github.aughtone.datetime.format.RelativeStyle
import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.resources.Resources
import nl.jacobras.humanreadable.HumanReadable
import kotlin.time.Duration

object MultiplatformDurationFormatter {
    fun formatRelative(
        duration: Duration,
        style: RelativeStyle,
        relativeTime: RelativeTime,
    ): String = when (style) {
        RelativeStyle.Short -> formatRelativeShort(
            duration = duration,
            relativeTime = relativeTime
        )

        RelativeStyle.Long -> formatRelativeLong(
            duration = duration,
            relativeTime = relativeTime
        )

        RelativeStyle.None -> ""
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
