package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.platform.MultiplatformDurationFormatter
import kotlin.time.Duration

fun Duration.formatRelative(
    style: RelativeStyle = RelativeStyle.Long,
    relativeTime: RelativeTime = RelativeTime.Present,
): String = MultiplatformDurationFormatter.formatRelative(
    duration = this,
    style = style,
    relativeTime = relativeTime,
)
