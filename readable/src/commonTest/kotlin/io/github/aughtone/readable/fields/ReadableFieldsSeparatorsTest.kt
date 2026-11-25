package io.github.aughtone.readable.fields

import io.github.aughtone.toolbox.Separators
import io.github.aughtone.types.locale.localeFor
import io.github.aughtone.readable.relative.formatReadableRelative
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertTrue

/** `separators` on `formatReadableFields`: plain spaces by default, no-break ones on request. */
class ReadableFieldsSeparatorsTest {

    private val tags = listOf("en-US", "fr", "de", "ru", "ja")
    private val skeletons = listOf("MMMd", "MMMMd", "yMMM", "yMMMM", "yMMMd", "yMMMEd", "MMMMEEEEd", "hm", "Hms", "MMMdhm", "yMMMEdHm")
    private val dateTime = LocalDateTime(2024, 3, 15, 16, 5, 6)

    private fun values(tag: String, separators: Separators): List<String> {
        val l = localeFor(tag)!!
        val instant = dateTime.toInstant(TimeZone.UTC)
        return skeletons.flatMap { skeleton ->
            listOfNotNull(
                dateTime.formatReadableFields(skeleton, l, separators = separators),
                if (skeleton.any { it in "hHms" }) null else dateTime.date.formatReadableFields(skeleton, l, separators = separators),
                instant.formatReadableFields(skeleton + "z", l, TimeZone.UTC, separators = separators),
            )
        }
    }

    @Test
    fun theDefaultWritesOnlyOrdinarySpaces() {
        for (tag in tags) for (value in values(tag, Separators.Ascii)) {
            assertTrue(value.none { it.isWhitespace() && it != ' ' }, "$tag: \"$value\"")
        }
    }

    @Test
    fun typographicWritesNoBreakableSpace() {
        for (tag in tags) for (value in values(tag, Separators.Typographic)) {
            assertTrue(value.none { it == ' ' || it == ' ' }, "$tag: \"$value\"")
        }
    }

    @Test
    fun theRelativeFormattersPassItToTheirFallbackDate() {
        val ru = localeFor("ru")!!
        val long = LocalDate(2024, 3, 15)
        val today = LocalDate(2024, 6, 1)
        assertTrue(long.formatReadableRelative(today, locale = ru).none { it.isWhitespace() && it != ' ' })
        assertTrue(long.formatReadableRelative(today, locale = ru, separators = Separators.Typographic).none { it == ' ' })
    }
}
