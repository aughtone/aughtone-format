package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Bundled month and weekday names: each word in one script, and each name the right one. */
class NameScriptTest {

    private val languages = "af ar az be bg ca cs da de el en es et eu fa fi fr gl he hi hr hu hy id is it iu ja ka kk ko lt lv mk ms nb nl nn pl pt ro ru sk sl sq sr sv sw th tr uk uz vi zh".split(" ")

    private fun locale(language: String) = Locale(languageCode = language, displayName = language)

    @Test
    fun noBundledNameMixesScriptsWithinAWord() {
        for (language in languages) {
            val names = Month.entries.flatMap { listOf(it.displayName(TextWidth.Full, locale(language)), it.displayName(TextWidth.Abbreviated, locale(language))) } +
                DayOfWeek.entries.flatMap { listOf(it.displayName(TextWidth.Full, locale(language)), it.displayName(TextWidth.Abbreviated, locale(language))) }
            for (name in names) assertTrue(isSingleScript(name), "$language: \"$name\" mixes scripts")
        }
    }

    @Test
    fun frenchOctoberAndNovemberAreTheirOwnNames() {
        assertEquals("Octobre", Month.OCTOBER.displayName(TextWidth.Full, locale("fr")))
        assertEquals("Novembre", Month.NOVEMBER.displayName(TextWidth.Full, locale("fr")))
    }

    @Test
    fun everyLanguageHasTwelveDifferentMonthsAndSevenDifferentDays() {
        for (language in languages) {
            assertEquals(12, Month.entries.map { it.displayName(TextWidth.Full, locale(language)) }.toSet().size, "$language months")
            assertEquals(7, DayOfWeek.entries.map { it.displayName(TextWidth.Full, locale(language)) }.toSet().size, "$language days")
        }
    }

    @Test
    fun hindiThursdayIsWrittenInDevanagari() {
        assertEquals("गुरुवार", DayOfWeek.THURSDAY.displayName(TextWidth.Full, locale("hi")))
    }
}

/** The script block a letter belongs to, coarsely; `null` for anything that is not a letter. */
internal fun scriptOf(char: Char): String? = when (char.code) {
    in 0x0041..0x024F, in 0x1E00..0x1EFF -> "Latin"
    in 0x0370..0x03FF, in 0x1F00..0x1FFF -> "Greek"
    in 0x0400..0x052F -> "Cyrillic"
    in 0x0530..0x058F -> "Armenian"
    in 0x0590..0x05FF -> "Hebrew"
    in 0x0600..0x06FF, in 0x0750..0x077F, in 0xFB50..0xFDFF, in 0xFE70..0xFEFF -> "Arabic"
    in 0x0900..0x097F -> "Devanagari"
    in 0x0E00..0x0E7F -> "Thai"
    in 0x10A0..0x10FF -> "Georgian"
    in 0x1100..0x11FF, in 0xAC00..0xD7AF, in 0x3130..0x318F -> "Hangul"
    in 0x1400..0x167F -> "Canadian syllabics"
    in 0x3040..0x30FF, in 0x4E00..0x9FFF, in 0x3400..0x4DBF -> "CJK"
    else -> null
}.takeIf { char.isLetter() }

/** Whether every word in [text] uses letters from a single script. */
internal fun isSingleScript(text: String): Boolean =
    text.split(' ', '-', '.', ',', ' ').all { word -> word.mapNotNull(::scriptOf).toSet().size <= 1 }
