package io.github.aughtone.datetime.format

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun Long.toInstant() = Instant.fromEpochMilliseconds(this)
fun Long.toLocalDateTime(timeZone: TimeZone) = toInstant().toLocalDateTime(timeZone)
fun Long.toLocalDate(timeZone: TimeZone) = toLocalDateTime(timeZone = timeZone).date
fun Long.toLocalTime(timeZone: TimeZone) = toLocalDateTime(timeZone = timeZone).time
