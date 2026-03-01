package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.formats.AmPmStrings
import io.github.aughtone.datetime.format.resources.formats.TimePatterns
import io.github.aughtone.datetime.format.resources.formats.localeAmPmStrings
import io.github.aughtone.datetime.format.resources.formats.localeTimePatterns
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

internal object DynamicLocalTimeFormats {

    private val defaultLocale = Locale(languageCode = "en", regionCode = "US", displayName = "en-US")
    private const val defaultPatternKey = "US"
    private const val defaultAmPmKey = "US"

    private fun getLanguageRegionKey(locale: Locale): String? {
        return locale.regionCode?.let { "${locale.languageCode}-$it" }
    }

    private fun getRegionKey(locale: Locale): String? {
        return locale.regionCode
    }

    private fun getPatterns(locale: Locale): TimePatterns {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)

        return localeTimePatterns[langRegionKey]?.value
            ?: localeTimePatterns[regionKey]?.value
            ?: localeTimePatterns.getValue(defaultPatternKey).value
    }

    private fun getAmPmStrings(locale: Locale): AmPmStrings {
        val langRegionKey = getLanguageRegionKey(locale)
        val regionKey = getRegionKey(locale)

        return localeAmPmStrings[langRegionKey]
            ?: localeAmPmStrings[regionKey]
            ?: localeAmPmStrings.getValue(defaultAmPmKey)
    }

    fun short(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        return LocalTime.Format {
            appendPattern(patterns.short)
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun medium(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        return LocalTime.Format {
            appendPattern(patterns.medium)
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun long(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        return LocalTime.Format {
            appendPattern(patterns.long)
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun full(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        return LocalTime.Format {
            appendPattern(patterns.full)
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }
}
