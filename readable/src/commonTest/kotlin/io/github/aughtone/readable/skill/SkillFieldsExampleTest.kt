package io.github.aughtone.readable.skill

import io.github.aughtone.datetime.format.TextWidth
import io.github.aughtone.readable.fields.ReadableFields
import io.github.aughtone.readable.fields.formatReadableFields
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The `formatReadableFields` example from this module's dependency skill, kept compiling and kept
 * true, with exactly the imports the skill lists. When this test changes, change the example in
 * `SKILL.md` to match.
 */
class SkillFieldsExampleTest {

    @Test
    fun theFieldsExampleCompilesAndProducesTheOutputItClaims() {
        val june1 = LocalDate(2024, 6, 1)
        assertEquals("Jun 1", june1.formatReadableFields(ReadableFields.MonthDay, localeFor("en-US")!!))
        assertEquals("1. Juni", june1.formatReadableFields(localeFor("de")!!) { month(TextWidth.Abbreviated); day() })
        assertEquals("6月1日", june1.formatReadableFields("MMMd", localeFor("ja")!!))
        assertEquals(
            "Sat 4:05 PM",
            LocalDateTime(2024, 6, 1, 16, 5).formatReadableFields(ReadableFields.WeekdayHourMinute, localeFor("en-US")!!, is24HourFormat = false),
        )
    }
}
