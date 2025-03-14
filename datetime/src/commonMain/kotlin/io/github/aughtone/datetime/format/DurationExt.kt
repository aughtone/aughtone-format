package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.platform.MultiplatformDurationFormatter
import kotlin.time.Duration
import kotlin.time.DurationUnit

fun Duration.formatRelative(
    style: RelativeStyle = RelativeStyle.LONG,
    relativeTime: RelativeTime = RelativeTime.Present,
): String = MultiplatformDurationFormatter.formatRelative(
    duration = this,
    style = style,
    relativeTime = relativeTime,
)
