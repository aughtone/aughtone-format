package io.github.aughtone.datetime.format

import io.github.aughtone.types.locale.Locale
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * The patterns chosen for a set of fields, checked against CLDR through the generated
 * [fieldCldrFixture], for every bundled locale.
 *
 * The fixture holds the shape of what ICU writes — the order of the fields, whether each is a name
 * or a number and whether it is padded, and the text between them — which is what the skeleton
 * matching decides. The names are left out of it, because the library keeps some of its own. ICU
 * runs on the JVM's test machine only through Node, so this lives in the JVM tests; the matching is
 * common code and runs the same everywhere.
 */
class FieldCldrTest {

    private class Case(val locale: String, val skeleton: String, val shape: String)

    private val cases = fieldCldrFixture.trim().lines().map { line ->
        val f = line.split('|')
        Case(f[0], f[1], f[2])
    }

    private fun localeOf(key: String): Locale = when {
        key == "CA" -> Locale(languageCode = "en", regionCode = "CA", displayName = key)
        key == "US" -> Locale(languageCode = "en", regionCode = "US", displayName = key)
        '-' in key -> Locale(languageCode = key.substringBefore('-'), regionCode = key.substringAfter('-'), displayName = key)
        else -> Locale(languageCode = key, displayName = key)
    }

    /** The shape of a library pattern, written as the fixture writes ICU's. */
    private fun shapeOf(pattern: String): String = tokenizePattern(pattern).joinToString("") { token ->
        when (token) {
            is PatternToken.Literal -> token.text.replace(Regex("[‎‏؜]"), "").replace(Regex("[   ]"), " ")
            is PatternToken.Field -> {
                val c = when (token.letter) {
                    'L' -> 'M'
                    'c' -> 'E'
                    'H', 'K', 'k' -> 'h'
                    'b', 'B' -> 'a'
                    'v' -> 'z'
                    else -> token.letter
                }
                val form = when {
                    c in "GEaz" -> 't'
                    c == 'M' && token.count >= 3 -> 't'
                    c == 'y' -> if (token.count == 2) 'Y' else 'n'
                    token.count >= 2 -> 'N'
                    else -> 'n'
                }
                "<$c$form>"
            }
        }
    }

    @Test
    fun everyFieldSetIsLaidOutAsCldrLaysItOut() {
        val mismatches = cases.mapNotNull { case ->
            val actual = shapeOf(fieldPattern(case.skeleton, localeOf(case.locale)))
            if (actual == case.shape) null else "${case.locale} ${case.skeleton}: CLDR \"${case.shape}\", library \"$actual\""
        }
        assertTrue(mismatches.isEmpty(), "${mismatches.size} of ${cases.size} differ:\n" + mismatches.take(60).joinToString("\n"))
    }
}
