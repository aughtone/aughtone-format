package io.github.aughtone.datetime.format

import androidx.compose.ui.text.intl.Locale
import io.github.aughtone.datetime.format.resources.Res
import io.github.aughtone.datetime.format.resources.now
import io.github.aughtone.datetime.format.resources.time_ago
import io.github.aughtone.datetime.format.resources.time_in_future
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.getString

/**
 *
 * This is the format string syntax used by the Java Time's DateTimeFormatter class, Swift's and Objective-C's NSDateFormatter class, and the ICU library.
 * The syntax is specified at: [https://unicode-org.github.io/icu/userguide/format_parse/datetime/#datetime-format-syntax](https://unicode-org.github.io/icu/userguide/format_parse/datetime/#datetime-format-syntax).
 *
 * In addition to the standard syntax, this function also supports optional sections by surrounding them in `[]`. For example, `hh:mm[:ss]`.
 *
 * This uses the KMP [byUnicodePattern] function and the full documentation can be found there.
 */
fun Instant.formatAs(
    pattern: String,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String =
    toLocalDateTime(timeZone).formatAs(pattern = pattern)


fun Instant.formatAs(
    style: FormatStyle = FormatStyle.ISO,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String = toLocalDateTime(timeZone).formatAs(style = style)

/**
 * The difference between this instant and the [relativeTo], which defaults to now.
 * Also supports instants in the future.
 *
 * @param relativeTo The [Instant], to compare this instant to, Defaults to [Clock.System.now()].
 * @return The difference between this instant and the [relativeTo], which defaults to now.
 *
 * Ported from source: https://github.com/jacobras/Human-Readable
 */
fun Instant.formatHumanReadableRelative(
    relativeTo: Instant = Clock.System.now(),
): String = runBlocking(context = Dispatchers.IO) {
    val diff: Duration = relativeTo.minus(this@formatHumanReadableRelative)
    val secondsAgo: Long = diff.inWholeSeconds
    return@runBlocking when {
        secondsAgo < 0 -> getString(
            Res.string.time_in_future,
            diff.formatHumanReadableRelative(relativeTime = RelativeTime.Future)
        )

        secondsAgo <= 1 -> getString(Res.string.now)
        else -> getString(
            Res.string.time_ago,
            diff.formatHumanReadableRelative(relativeTime = RelativeTime.Past)
        )
    }
}

fun Instant.formatRelativeUntilDate(
    until: Duration = 3.days,
    style: FormatStyle = FormatStyle.SHORT,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    locale: Locale = Locale.current,
    relativeTo: Instant = Clock.System.now(),
): String = if (this < Clock.System.now().minus(until)) {
    toLocalDateTime(timeZone).date.formatSimple(style, locale = locale)
} else {
    formatHumanReadableRelative(relativeTo = relativeTo)
}
