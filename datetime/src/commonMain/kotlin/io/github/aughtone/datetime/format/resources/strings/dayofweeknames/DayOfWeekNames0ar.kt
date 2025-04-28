package io.github.aughtone.datetime.format.resources.strings.dayofweeknames

import kotlinx.datetime.format.DayOfWeekNames

/**
 * Day of week names for Arabic.
 *
 * Important Considerations
 * - Right-to-Left: Arabic is written from right to left. Make sure your text rendering engine and UI elements are configured properly for right-to-left languages.
 * - Context the abbreviations are using the full name, because it is the common and standard use.
 * - Regional Variations: While these are the standard names for the days of the week in Modern Standard Arabic, some regional dialects might have slight variations. However, these are broadly understood across the Arabic-speaking world.
 */
object DayOfWeekNames0ar : DayOfWeekNamesResource {
    override val full by lazy {
        DayOfWeekNames(
            listOf(
                "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت"
            )
        )
    }
    override val abbreviated by lazy {
        DayOfWeekNames(
            listOf(
                "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت"
            )
        )
    }
}
