package io.github.aughtone.readable

import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.relative.relativeTimeConfigFor
import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/** Bundled unit words and phrases: each word written in one script. */
class ScriptTest {

    private val languages = "af ar az be bg ca cs da de el en es et eu fa fi fr gl he hi hr hu hy id is it iu ja ka kk ko lt lv mk ms nb nl nn pl pt ro ru sk sl sq sr sv sw th tr uk uz vi zh".split(" ")

    @Test
    fun noDurationOrRelativePhraseMixesScriptsWithinAWord() {
        val spans = listOf(1, 2, 5).flatMap { n -> listOf(n.seconds, n.minutes, n.hours, n.days, (n * 7).days, (n * 30).days, (n * 365).days) }
        for (language in languages) {
            val locale = Locale(languageCode = language, displayName = language)
            val relative = relativeTimeConfigFor(locale).formatter
            for (span in spans) {
                for (text in listOf(span.formatReadable(locale = locale), relative(-span, true), relative(span, true))) {
                    assertTrue(isSingleScript(text), "$language: \"$text\" mixes scripts")
                }
            }
        }
    }

    @Test
    fun vietnameseYearIsWrittenInLatin() {
        assertEquals("1 năm trước", relativeTimeConfigFor(Locales.Vietnamese).formatter((-365).days, true))
    }
}

/** The script block a letter belongs to, coarsely; `null` for anything that is not a letter. */
private fun scriptOf(char: Char): String? = when (char.code) {
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
private fun isSingleScript(text: String): Boolean =
    text.split(' ', '-', '.', ',', ' ').all { word -> word.mapNotNull(::scriptOf).toSet().size <= 1 }
