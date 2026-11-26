@file:OptIn(ExperimentalTime::class)

package io.github.aughtone.datetime.format.platform

import io.github.aughtone.types.locale.Locale
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.TimeZone

// Linux ships no CLDR, so the platform has no localized zone names to give. Null sends the caller to
// the library's own fallback, as the contract of these functions says.

internal actual fun platformZoneName(
    timeZone: TimeZone,
    instant: Instant,
    abbreviated: Boolean,
    locale: Locale,
): String? = null

internal actual fun platformGenericZoneName(
    timeZone: TimeZone,
    abbreviated: Boolean,
    locale: Locale,
): String? = null
