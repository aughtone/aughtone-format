package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.platform.MultiplatformDurationFormatter
import kotlin.time.Duration

/**
 * Formats this [Duration] into a human-readable string representing its relative time.
 *
 * @param style The style of the relative time string. Defaults to [RelativeStyle.Long].
 * @param relativeTime The reference point for the relative time. Defaults to [RelativeTime.Present].
 * @return A human-readable string representing the relative duration.
 */
fun Duration.formatRelative(
    style: RelativeStyle = RelativeStyle.Long,
    relativeTime: RelativeTime = RelativeTime.Present,
): String = MultiplatformDurationFormatter.formatRelative(
    duration = this,
    style = style,
    relativeTime = relativeTime,
)
