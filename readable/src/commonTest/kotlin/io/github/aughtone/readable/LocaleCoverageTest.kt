package io.github.aughtone.readable

import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.relative.relativeTimeConfigFor
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.minutes

/**
 * Guards against "allow-list drift": a locale that has its own translation data but is missing from
 * an internal supported-tag list, so it silently falls back to English. This class of bug has bitten
 * twice — id/ms/sw (relative time) and az/eu/hy/ka/kk/lt/lv/sq/uz/iu (duration). Both public entry
 * points below route through those allow-lists, so any future drift fails here, by locale name.
 */
class LocaleCoverageTest {

    // One Locale per supported language (Locales.kt). English-based locales are omitted because their
    // output legitimately equals the English reference.
    private val supported = listOf(
        Locales.Afrikaans, Locales.Dutch, Locales.German, Locales.Danish, Locales.NorwegianBokmal,
        Locales.NorwegianNynorsk, Locales.Swedish, Locales.Icelandic, Locales.French, Locales.Spanish,
        Locales.Italian, Locales.Portuguese, Locales.Catalan, Locales.Galician, Locales.Romanian,
        Locales.Russian, Locales.Ukrainian, Locales.Belarusian, Locales.Polish, Locales.Czech,
        Locales.Slovak, Locales.Bulgarian, Locales.Croatian, Locales.Serbian, Locales.Macedonian,
        Locales.Slovenian, Locales.Lithuanian, Locales.Latvian, Locales.Japanese, Locales.Chinese,
        Locales.TraditionalChinese, Locales.Korean, Locales.Thai, Locales.Vietnamese, Locales.Indonesian,
        Locales.Malay, Locales.Hindi, Locales.Armenian, Locales.Georgian, Locales.Kazakh, Locales.Uzbek,
        Locales.Arabic, Locales.Hebrew, Locales.Persian, Locales.Turkish, Locales.Azerbaijani,
        Locales.Swahili, Locales.Finnish, Locales.Estonian, Locales.Hungarian, Locales.Greek,
        Locales.Basque, Locales.Albanian, Locales.Inuktitut,
    )

    @Test
    fun everySupportedLocaleReachesItsOwnRelativeTimeConfig() {
        // Fails if a config case in buildRelativeTimeConfig is missing from isRelativeTimeTagSupported:
        // the locale would resolve to the English "just now" fallback.
        for (loc in supported) {
            val nowString = relativeTimeConfigFor(loc).nowString
            assertTrue(nowString != "just now", "relative-time allow-list drift: '${loc.languageCode}' fell back to English")
        }
    }

    @Test
    fun everySupportedLocaleReachesItsOwnDurationUnits() {
        // Fails if a case in buildDurationFormatter is missing from isDurationTagSupported.
        // af/nl/id/ms/sw intentionally reuse the English unit words, so they are exempt.
        val reuseEnglishUnits = setOf("af", "nl", "id", "ms", "sw")
        val englishOut = 60.minutes.formatReadable(locale = Locales.English)
        for (loc in supported) {
            if (loc.languageCode in reuseEnglishUnits) continue
            val out = 60.minutes.formatReadable(locale = loc)
            assertTrue(out != englishOut, "duration allow-list drift: '${loc.languageCode}' fell back to English ($out)")
        }
    }
}
