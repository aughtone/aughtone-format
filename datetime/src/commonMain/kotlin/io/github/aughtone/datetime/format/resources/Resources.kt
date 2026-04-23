package io.github.aughtone.datetime.format.resources

import io.github.aughtone.datetime.format.DynamicLocalDateFormats
import io.github.aughtone.datetime.format.DynamicLocalTimeFormats
import io.github.aughtone.datetime.format.resources.formats.AmPmStrings
import io.github.aughtone.datetime.format.resources.formats.DatePatterns
import io.github.aughtone.datetime.format.resources.formats.TimePatterns
import io.github.aughtone.datetime.format.resources.values.ClockHoursResource
import io.github.aughtone.datetime.format.resources.formats.localeAmPmStrings
import io.github.aughtone.datetime.format.resources.formats.localeClockHoursSource
import io.github.aughtone.datetime.format.resources.formats.localeDatePatterns
import io.github.aughtone.datetime.format.resources.formats.localeDayOfWeekNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeEraNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeMonthNamesSource
import io.github.aughtone.datetime.format.resources.formats.localeTimePatterns
import io.github.aughtone.datetime.format.resources.values.ClockHours
import io.github.aughtone.datetime.format.resources.values.ClockType
import io.github.aughtone.datetime.format.resources.values.DayOfWeekNamesResource
import io.github.aughtone.datetime.format.resources.values.EraNames
import io.github.aughtone.datetime.format.resources.values.EraNamesResource
import io.github.aughtone.datetime.format.resources.values.MonthNamesResource
import io.github.aughtone.datetime.format.resources.values.text.TextResource
import io.github.aughtone.datetime.format.resources.values.text.TextResourceMap
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.toLanguageTag
import kotlinx.datetime.TimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

import kotlin.concurrent.Volatile
import io.github.aughtone.datetime.format.resources.formats.PatternSanitizer

enum class FormatStyle {
    SHORT, MEDIUM, LONG, FULL
}

object Resources {

    private const val defaultFallbackKey = "US"
    private const val defaultLanguageFallbackKey = "en"
    private const val defaultTextResourceKey = "en"
    private const val defaultEraNamesResourceKey = "en"
    private const val MAX_CACHE_SIZE = 100

    // ── Cache (Thread-safe via @Volatile replacement) ─────────────────────────
    @Volatile private var amPmCache = emptyMap<String, AmPmStrings>()
    @Volatile private var timePatternsCache = emptyMap<String, TimePatterns>()
    @Volatile private var datePatternsCache = emptyMap<String, DatePatterns>()
    @Volatile private var dayOfWeekNamesCache = emptyMap<String, DayOfWeekNamesResource>()
    @Volatile private var monthNamesCache = emptyMap<String, MonthNamesResource>()
    @Volatile private var textResourceCache = emptyMap<String, TextResource>()
    @Volatile private var clockHoursCache = emptyMap<String, ClockHours>()
    @Volatile private var eraNamesCache = emptyMap<String, EraNamesResource>()

    private fun <T> lookup(
        locale: Locale,
        getCurrentCache: () -> Map<String, T>,
        updateCache: (Map<String, T>) -> Unit,
        source: Map<String, T>,
        defaultKey: String,
        sanitizer: ((T) -> T)? = null,
    ): T {
        val tag = locale.toLanguageTag()
        val currentCache = getCurrentCache()
        currentCache[tag]?.let { return it }

        val lang = locale.languageCode
        val region = locale.regionCode
        val langRegion = region?.let { "${lang}-$it" }

        val rawResolved = source[langRegion]
            ?: source[region]
            ?: source[lang]
            ?: source.getValue(defaultKey)

        val resolved = sanitizer?.invoke(rawResolved) ?: rawResolved

        // Atomic-ish update (last writer wins, which is fine for idempotent cache)
        val oldCache = getCurrentCache()
        if (!oldCache.containsKey(tag)) {
            val newCache = if (oldCache.size >= MAX_CACHE_SIZE) {
                mapOf(tag to resolved)
            } else {
                oldCache + (tag to resolved)
            }
            updateCache(newCache)
        }

        return resolved
    }

    private fun <T> lookupOptional(
        locale: Locale,
        getCurrentCache: () -> Map<String, T>,
        updateCache: (Map<String, T>) -> Unit,
        source: Map<String, T?>,
    ): T? {
        val tag = locale.toLanguageTag()
        val currentCache = getCurrentCache()
        if (currentCache.containsKey(tag)) return currentCache[tag]

        val lang = locale.languageCode
        val region = locale.regionCode
        val langRegion = region?.let { "${lang}-$it" }

        val resolved = source[langRegion]
            ?: source[region]
            ?: source[lang]

        if (resolved != null) {
            val oldCache = getCurrentCache()
            if (!oldCache.containsKey(tag)) {
                val newCache = if (oldCache.size >= MAX_CACHE_SIZE) {
                    mapOf(tag to resolved)
                } else {
                    oldCache + (tag to resolved)
                }
                updateCache(newCache)
            }
        }
        return resolved
    }

    fun getDateFormat(locale: Locale, timeZone: TimeZone, style: FormatStyle): DateTimeFormat<LocalDate> {
        return when (style) {
            FormatStyle.SHORT -> DynamicLocalDateFormats.short(locale, timeZone)
            FormatStyle.MEDIUM -> DynamicLocalDateFormats.medium(locale, timeZone)
            FormatStyle.LONG -> DynamicLocalDateFormats.long(locale, timeZone)
            FormatStyle.FULL -> DynamicLocalDateFormats.full(locale, timeZone)
        }
    }

    fun getTimeFormat(
        locale: Locale,
        timeZone: TimeZone,
        style: FormatStyle,
        twentyFourHour: Boolean? = null,
    ): DateTimeFormat<LocalTime> {
        val clockHours = getClockHours(locale)
        val use24Hour = twentyFourHour ?: clockHours.is24hour
        return when (style) {
            FormatStyle.SHORT -> DynamicLocalTimeFormats.short(locale, use24Hour)
            FormatStyle.MEDIUM -> DynamicLocalTimeFormats.medium(locale, use24Hour)
            FormatStyle.LONG -> DynamicLocalTimeFormats.long(locale, use24Hour)
            FormatStyle.FULL -> DynamicLocalTimeFormats.full(locale, use24Hour)
        }
    }

    internal fun getAmPmStrings(locale: Locale): AmPmStrings =
        lookup(locale, { amPmCache }, { amPmCache = it }, localeAmPmStrings, defaultFallbackKey)

    internal fun getTimePatterns(locale: Locale): TimePatterns =
        lookup(
            locale, 
            { timePatternsCache }, 
            { timePatternsCache = it },
            localeTimePatterns.mapValues { it.value.value }, 
            defaultFallbackKey
        ) { patterns ->
            TimePatterns(
                short = PatternSanitizer.sanitize(patterns.short),
                medium = PatternSanitizer.sanitize(patterns.medium),
                long = PatternSanitizer.sanitize(patterns.long),
                full = PatternSanitizer.sanitize(patterns.full)
            )
        }

    internal fun getDatePatterns(locale: Locale): DatePatterns =
        lookup(locale, { datePatternsCache }, { datePatternsCache = it }, localeDatePatterns, defaultFallbackKey) { patterns ->
            DatePatterns(
                short = PatternSanitizer.sanitize(patterns.short),
                medium = PatternSanitizer.sanitize(patterns.medium),
                long = PatternSanitizer.sanitize(patterns.long),
                full = PatternSanitizer.sanitize(patterns.full)
            )
        }

    internal fun getDayOfWeekNamesResource(locale: Locale): DayOfWeekNamesResource =
        lookup(locale, { dayOfWeekNamesCache }, { dayOfWeekNamesCache = it }, localeDayOfWeekNamesSource.mapValues { it.value.value }, defaultLanguageFallbackKey)

    internal fun getMonthNamesResource(locale: Locale): MonthNamesResource =
        lookup(locale, { monthNamesCache }, { monthNamesCache = it }, localeMonthNamesSource.mapValues { it.value.value }, defaultLanguageFallbackKey)

    fun getText(locale: Locale): TextResource =
        lookup(locale, { textResourceCache }, { textResourceCache = it }, TextResourceMap.map, defaultTextResourceKey)

    fun getClockHours(locale: Locale): ClockHours =
        lookupOptional(locale, { clockHoursCache }, { clockHoursCache = it }, localeClockHoursSource.mapValues { it.value.value })
            ?: ClockHoursResource(is24hour = true, hours = ClockType.C24Hour)

    private fun getEraNamesResource(locale: Locale): EraNamesResource =
        lookup(locale, { eraNamesCache }, { eraNamesCache = it }, localeEraNamesSource.mapValues { it.value.value }, defaultEraNamesResourceKey)

    fun getEraNames(locale: Locale, abbreviated: Boolean): EraNames =
        with(getEraNamesResource(locale)) {
            if (abbreviated) this.abbreviated else this.full
        }
}
