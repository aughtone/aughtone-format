package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.AmPmStrings
import io.github.aughtone.datetime.format.resources.formats.TimePatterns
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.char

internal object DynamicLocalTimeFormats {

    private fun getPatterns(locale: Locale): TimePatterns = Resources.getTimePatterns(locale)

    private fun getAmPmStrings(locale: Locale): AmPmStrings = Resources.getAmPmStrings(locale)

    private fun String.to12HourPattern(): String = this.replace("HH", "h").replace("H", "h")

    /**
     * Appends the time and, on a 12-hour clock, the AM/PM marker on the side the language writes
     * it: after the time for most, before it for Korean, Hungarian and Turkish ("오후 4:08"), and
     * before it with no space for Japanese and Chinese ("午後4:08").
     */
    private fun DateTimeFormatBuilder.WithTime.appendTime(
        pattern: String,
        use24Hour: Boolean,
        amPmStrings: AmPmStrings,
        locale: Locale,
    ) {
        if (use24Hour) {
            appendPattern(pattern)
            return
        }
        when (locale.languageCode) {
            "ja", "zh" -> {
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
                appendPattern(pattern)
            }
            "ko", "hu", "tr" -> {
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
                char(' ')
                appendPattern(pattern)
            }
            else -> {
                appendPattern(pattern)
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun short(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.short else patterns.short.to12HourPattern()
        return LocalTime.Format { appendTime(pattern.trim(), use24Hour, amPmStrings, locale) }
    }

    fun medium(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.medium else patterns.medium.to12HourPattern()
        return LocalTime.Format { appendTime(pattern.trim(), use24Hour, amPmStrings, locale) }
    }

    fun long(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.long else patterns.long.to12HourPattern()
        return LocalTime.Format { appendTime(pattern.trim(), use24Hour, amPmStrings, locale) }
    }

    fun full(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.full else patterns.full.to12HourPattern()
        return LocalTime.Format { appendTime(pattern.trim(), use24Hour, amPmStrings, locale) }
    }
}
