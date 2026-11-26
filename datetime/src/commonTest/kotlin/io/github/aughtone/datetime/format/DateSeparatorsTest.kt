package io.github.aughtone.datetime.format

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertTrue

/** `separators` on the date and time styles: plain spaces by default, no-break ones on request. */
class DateSeparatorsTest {

    private val tags = listOf("en-US", "fr", "de", "ru", "ja")
    private val styles = listOf(DateTimeStyle.Short, DateTimeStyle.Medium, DateTimeStyle.Long, DateTimeStyle.Full)
    private val date = LocalDate(2024, 3, 15)
    private val time = LocalTime(16, 5, 6)
    private val dateTime = LocalDateTime(date, time)

    private fun locale(tag: String): Locale = localeFor(tag)!!

    /** Every value [separators] gives in every style, for one locale. */
    private fun values(tag: String, separators: Separators): List<String> {
        val l = locale(tag)
        val utc = TimeZone.UTC
        return buildList {
            for (style in styles) {
                add(date.format(style, l, utc, separators = separators))
                add(time.format(style, l, utc, is24HourFormat = false, separators = separators))
                add(time.format(style, l, utc, is24HourFormat = true, separators = separators))
                add(dateTime.format(style, style, l, utc, is24HourFormat = false, separators = separators))
                add(dateTime.toInstant(utc).format(style, style, l, utc, is24HourFormat = false, separators = separators))
            }
        }
    }

    @Test
    fun theDefaultWritesOnlyOrdinarySpaces() {
        for (tag in tags) for (value in values(tag, Separators.Ascii)) {
            assertTrue(value.none { it.isWhitespace() && it != ' ' }, "$tag: \"$value\"")
        }
        // The default is Ascii.
        assertTrue(date.format(DateTimeStyle.Long, locale("ru"), TimeZone.UTC).endsWith("2024 г."))
    }

    @Test
    fun typographicWritesNoBreakableSpace() {
        for (tag in tags) for (value in values(tag, Separators.Typographic)) {
            assertTrue(value.none { it == ' ' || it == ' ' }, "$tag: \"$value\"")
        }
    }
}
