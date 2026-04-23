package io.github.aughtone.datetime.format

import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.datetime.format.resources.values.EraNames
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.atDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FeatureTest {

    private val testLocale = Locale(languageCode = "en", regionCode = "US", displayName = "English (US)")
    private val testTimeZone = TimeZone.UTC

    @Test
    fun testEraOverride() {
        val date = LocalDate(-500, 1, 1).atTime(0, 0)
        val customEras = EraNames(bce = "BEFORE", ce = "AFTER")
        
        // Full style includes Era by default in MultiplatformPostFormatter
        val formatted = date.format(
            dateStyle = DateTimeStyle.Full,
            timeStyle = DateTimeStyle.None,
            locale = testLocale,
            timeZone = testTimeZone,
            eraNames = customEras
        )
        
        // Default would be "Friday, January 1, 500 BCE" (or similar)
        // With override: "... BEFORE"
        assertTrue(formatted.contains("BEFORE"))
        assertTrue(!formatted.contains("BCE"))
    }

    @Test
    fun testNumberingSystem() {
        val time = LocalTime(12, 34, 56).atDate(LocalDate(2024, 1, 1))
        
        // Arabic-Indic digits
        val formattedArab = time.format(
            dateStyle = DateTimeStyle.Short,
            timeStyle = DateTimeStyle.Short,
            locale = testLocale,
            timeZone = testTimeZone,
            numberingSystem = NumberingSystem.ARAB
        )
        
        // 2024 -> ٢٠٢٤
        // 12:34 -> ١٢:٣٤
        assertTrue(formattedArab.contains("٢٠٢٤"))
        assertTrue(formattedArab.contains("١٢:٣٤"))
    }
}
