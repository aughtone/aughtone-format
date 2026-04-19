@file:OptIn(ExperimentalMultiplatform::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.datetime.format.RelativeStyle
import io.github.aughtone.datetime.format.RelativeTime
import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.toolbox.format
import nl.jacobras.humanreadable.HumanReadable
import kotlin.time.Duration

object MultiplatformDurationFormatter {
    fun formatRelative(
        duration: Duration,
        style: RelativeStyle,
        relativeTime: RelativeTime,
        locale: Locale,
    ): String = when (style) {
        RelativeStyle.Short -> formatRelativeShort(
            duration = duration,
            relativeTime = relativeTime,
            locale = locale
        )

        RelativeStyle.Long -> formatRelativeLong(
            duration = duration,
            relativeTime = relativeTime,
            locale = locale
        )

        RelativeStyle.None -> ""
    }
}

private fun formatRelativeShort(
    duration: Duration,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale,
): String = when (relativeTime) {
    RelativeTime.Past ->  Resources.getText(locale).time_in_past.text.format(duration.toString())
    RelativeTime.Future -> Resources.getText(locale).time_in_future.text.format(duration.toString())
    else -> duration.toString()
}
private fun formatRelativeLong(
    duration: Duration,
    relativeTime: RelativeTime = RelativeTime.Present,
    locale: Locale,
):String =when (relativeTime) {
    RelativeTime.Past ->  Resources.getText(locale).time_in_past.text.format(HumanReadable.duration(duration))
    RelativeTime.Future -> Resources.getText(locale).time_in_future.text.format(HumanReadable.duration(duration))
    else -> HumanReadable.duration(duration)
}
