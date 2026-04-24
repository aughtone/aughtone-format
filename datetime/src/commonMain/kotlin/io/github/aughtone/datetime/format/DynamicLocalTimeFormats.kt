package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.Resources
import io.github.aughtone.datetime.format.resources.formats.AmPmStrings
import io.github.aughtone.datetime.format.resources.formats.TimePatterns
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

internal object DynamicLocalTimeFormats {

    private fun getPatterns(locale: Locale): TimePatterns = Resources.getTimePatterns(locale)

    private fun getAmPmStrings(locale: Locale): AmPmStrings = Resources.getAmPmStrings(locale)

    private fun String.to12HourPattern(): String = this.replace("HH", "h").replace("H", "h")

    fun short(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.short else patterns.short.to12HourPattern()
        return LocalTime.Format {
            appendPattern(pattern.trim())
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun medium(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.medium else patterns.medium.to12HourPattern()
        return LocalTime.Format {
            appendPattern(pattern.trim())
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun long(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.long else patterns.long.to12HourPattern()
        return LocalTime.Format {
            appendPattern(pattern.trim())
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }

    fun full(locale: Locale, use24Hour: Boolean): DateTimeFormat<LocalTime> {
        val patterns = getPatterns(locale)
        val amPmStrings = getAmPmStrings(locale)
        val pattern = if (use24Hour) patterns.full else patterns.full.to12HourPattern()
        return LocalTime.Format {
            appendPattern(pattern.trim())
            if (!use24Hour) {
                char(' ')
                amPmMarker(am = amPmStrings.am, pm = amPmStrings.pm)
            }
        }
    }
}
