package io.github.aughtone.readable.skill

import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.financial.formatReadable
import io.github.aughtone.readable.list.formatReadableList
import io.github.aughtone.readable.metrics.formatReadable
import io.github.aughtone.readable.metrics.formatReadableDataSize
import io.github.aughtone.readable.number.formatReadable
import io.github.aughtone.readable.number.formatReadableAbbreviated
import io.github.aughtone.readable.ordinality.formatReadableOrdinal
import io.github.aughtone.readable.relative.formatReadableRelative
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.quantitative.Distance
import io.github.aughtone.types.units.UnitOfMeasure
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant

/**
 * The usage example from this module's dependency skill, kept compiling and kept true.
 *
 * It lives in its own package and imports exactly what the skill tells a reader to import, so an
 * example that needs an import the skill does not list fails here before it reaches anyone. When
 * this test changes, change the example in `SKILL.md` to match.
 */
class SkillExampleTest {

    private val usd = Currency(code = "USD", number = 840, digits = 2, name = "US Dollar", symbol = "$")

    @Test
    fun theSkillExampleCompilesAndProducesTheOutputItClaims() {
        assertEquals("1 week", 7.days.formatReadable(locale = Locales.English))
        assertEquals("1.5k", 1500.0.formatReadableAbbreviated(Locales.English))
        assertEquals("1,234.6", 1234.56.formatReadable(Locales.English, precision = 1))
        assertEquals("1 MiB", 1048576L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English))
        assertEquals("1.5 km", Distance(1500.0).formatReadable(Locales.English))
        assertEquals("$1,234.56", Money(123456L, usd).formatReadable(Locales.English))
        assertEquals("1st", 1.formatReadableOrdinal(Locales.English))

        assertEquals(
            "Monday, Wednesday, and Friday",
            listOf("Monday", "Wednesday", "Friday").formatReadableList(Locales.English)
        )

        val now = Instant.parse("2026-01-15T12:00:00Z")
        assertEquals("8 minutes ago", (now - 8.minutes).formatReadableRelative(now = now, locale = Locales.English))
    }
}
