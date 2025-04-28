package io.github.aughtone.datetime.format

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


/**
 * Converts a `Long` representing the number of milliseconds since the epoch (1970-01-01T00:00:00Z)
 * to an [Instant].
 *
 * @return An [Instant] representing the point in time corresponding to the given milliseconds.
 */
fun Long.toInstant() = Instant.fromEpochMilliseconds(this)
/**
 * Converts a `Long` representing milliseconds since the epoch to a `LocalDateTime` in the specified [timeZone].
 *
 * @param timeZone The time zone to use for the conversion. Defaults to the system's default time zone.
 * @return A `LocalDateTime` representing the time corresponding to the `Long` value in the given [timeZone].
 */
fun Long.toLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()) = toInstant().toLocalDateTime(timeZone)
/**
 * Converts a [Long] representing milliseconds since the epoch to a [kotlinx.datetime.LocalDate] in the specified [timeZone].
 *
 * @param timeZone The [TimeZone] in which to interpret the instant. Defaults to the system's current default time zone.
 * @return The [kotlinx.datetime.LocalDate] representing the date portion of the converted [kotlinx.datetime.LocalDateTime].
 */
fun Long.toLocalDate(timeZone: TimeZone = TimeZone.currentSystemDefault()) = toLocalDateTime(timeZone = timeZone).date
/**
 * Converts a `Long` representing milliseconds since the epoch to a `LocalTime` in the specified [timeZone].
 *
 * @param timeZone The time zone to use for the conversion. Defaults to the system's current time zone.
 * @return The `LocalTime` corresponding to the milliseconds since the epoch in the given [timeZone].
 */
fun Long.toLocalTime(timeZone: TimeZone = TimeZone.currentSystemDefault()) = toLocalDateTime(timeZone = timeZone).time
