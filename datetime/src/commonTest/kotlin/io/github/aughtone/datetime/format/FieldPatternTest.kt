package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * The skeleton matching on every target. [FieldCldrTest] checks it against CLDR for every locale
 * on the JVM; these are a few of the same cases, so that a platform difference would show here.
 */
class FieldPatternTest {

    private fun locale(language: String, region: String? = null) = Locale(languageCode = language, regionCode = region, displayName = language)

    @Test
    fun theNearestPatternIsChosenAndAdjusted() {
        assertEquals("MMM d", fieldPattern("MMMd", locale("en", "US")))
        assertEquals("d. MMMM", fieldPattern("MMMMd", locale("de")))
        // No published "yMMMMd" in English: "yMMMd" is taken and its month widened.
        assertEquals("MMMM d, y", fieldPattern("yMMMMd", locale("en", "US")))
        // CLDR puts a narrow no-break space before the marker; the renderer writes a plain one.
        assertEquals("h:mm\u202Fa", fieldPattern("hm", locale("en", "US")))
        // A generic zone pattern serves a specific zone.
        assertEquals("HH:mm z", fieldPattern("Hmz", locale("de")))
    }

    @Test
    fun aRequestNoPatternCoversIsSplitAndJoined() {
        assertEquals("MMMM d, y 'at' h:mm\u202Fa", fieldPattern("yMMMMdhm", locale("en", "US")))
        // No pattern has a year and a day without a month: the day is added by its name.
        assertEquals("y ('day': d)", fieldPattern("yd", locale("en", "US")))
    }

    @Test
    fun aSkeletonOfOtherLettersIsRejected() {
        assertFailsWith<IllegalArgumentException> { fieldPattern("", locale("en")) }
        assertFailsWith<IllegalArgumentException> { fieldPattern("yQQQ", locale("en")) }
        assertFailsWith<IllegalArgumentException> { fieldPattern("HHmmZ", locale("en")) }
        assertFailsWith<IllegalArgumentException> { fieldPattern("MdM", locale("en")) }
    }
}
