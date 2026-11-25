package io.github.aughtone.readable

import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.locale.localeFor
import kotlin.test.Test
import kotlin.test.assertEquals

/** Every [Locales] constant is the locale aughtone-types ships for its tag. */
class LocalesTest {

    private val all: List<Locale> = listOf(
        Locales.Afrikaans, Locales.Dutch, Locales.English, Locales.SouthAfricanEnglish, Locales.German,
        Locales.SwissGerman, Locales.Danish, Locales.NorwegianBokmal, Locales.NorwegianNynorsk, Locales.Swedish,
        Locales.Icelandic, Locales.French, Locales.SwissFrench, Locales.Spanish, Locales.Italian,
        Locales.SwissItalian, Locales.Portuguese, Locales.Catalan, Locales.Galician, Locales.Romanian,
        Locales.Russian, Locales.Ukrainian, Locales.Belarusian, Locales.Polish, Locales.Czech, Locales.Slovak,
        Locales.Bulgarian, Locales.Croatian, Locales.Serbian, Locales.Macedonian, Locales.Slovenian,
        Locales.Lithuanian, Locales.Latvian, Locales.Japanese, Locales.Chinese, Locales.TraditionalChinese,
        Locales.Korean, Locales.Thai, Locales.Vietnamese, Locales.Indonesian, Locales.Malay, Locales.Hindi,
        Locales.Armenian, Locales.Georgian, Locales.Kazakh, Locales.Uzbek, Locales.Arabic, Locales.Hebrew,
        Locales.Persian, Locales.Turkish, Locales.Azerbaijani, Locales.Swahili, Locales.Finnish, Locales.Estonian,
        Locales.Hungarian, Locales.Greek, Locales.Basque, Locales.Albanian, Locales.Inuktitut,
    )

    @Test
    fun everyConstantEqualsTheShippedLocaleForItsTag() {
        for (locale in all) assertEquals(localeFor(locale.languageTag), locale, locale.languageTag)
    }

    @Test
    fun aSetHoldsAConstantAndItsShippedLocaleOnce() {
        for (locale in all) assertEquals(1, setOf(locale, localeFor(locale.languageTag)).size, locale.languageTag)
    }

    @Test
    fun theScriptedConstantsNameTheirScript() {
        assertEquals("zh-Hans-CN", Locales.Chinese.languageTag)
        assertEquals("zh-Hant-TW", Locales.TraditionalChinese.languageTag)
        assertEquals("uz-Latn-UZ", Locales.Uzbek.languageTag)
    }
}
