package io.github.aughtone.readable

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.TextWidth
import io.github.aughtone.datetime.format.displayName
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.formatRange
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.list.ListType
import io.github.aughtone.readable.list.formatReadableList
import io.github.aughtone.readable.number.formatReadable
import io.github.aughtone.readable.financial.formatReadable
import io.github.aughtone.readable.ordinality.formatReadableOrdinal
import io.github.aughtone.readable.quantitative.formatReadable
import io.github.aughtone.readable.relative.RelativeStyle
import io.github.aughtone.readable.relative.relativeTimeConfigFor
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale
import io.github.aughtone.types.quantitative.Azimuth
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Writes the Inuktitut review sheet: every piece of Inuktitut text the library produces, each
 * beside the same call in English, so a reviewer checks what an app would actually show rather
 * than a list of words.
 *
 * The sheet is written to [SHEET] on every JVM test run, so it always shows the current output:
 * run `./gradlew :readable:jvmTest` and send the file. `docs/outbox/` is not committed, because
 * what goes in it is written for people outside the project.
 */
class InuktitutReviewSheetTest {

    private val english = Locale(languageCode = "en", displayName = "English")
    private val inuktitut = Locale(languageCode = "iu", regionCode = "CA", displayName = "Inuktitut")

    /**
     * One row of the sheet: what the case is, and the text in each language. [ownWords] is false
     * for a row whose Latin letters come from its input (the measurements in a unit list), so it
     * is not flagged as English when the two languages agree.
     */
    private class Row(val case: String, val english: String, val inuktitut: String, val ownWords: Boolean = true)

    private class Section(val title: String, val note: String, val rows: List<Row>)

    private fun row(case: String, ownWords: Boolean = true, render: (Locale) -> String) =
        Row(case, render(english), render(inuktitut), ownWords)

    private val date = LocalDate(2024, 3, 15)
    private val utc = TimeZone.UTC

    private fun dates() = Section(
        "Dates",
        "15 March 2024, a Friday, at each style. The era is shown only when an app asks for it.",
        listOf(DateTimeStyle.Short, DateTimeStyle.Medium, DateTimeStyle.Long, DateTimeStyle.Full).map { style ->
            row("$style date") { date.format(style, locale = it, timeZone = utc, numberingSystem = NumberingSystem.LATN) }
        } + listOf(
            row("Long date with the era") { date.format(DateTimeStyle.Long, locale = it, timeZone = utc, numberingSystem = NumberingSystem.LATN, showEra = true) },
            row("A date before the common era") { LocalDate(-43, 3, 15).format(DateTimeStyle.Long, locale = it, timeZone = utc, numberingSystem = NumberingSystem.LATN) },
            row("Full date and short time") {
                LocalDateTime(2024, 3, 15, 17, 30).format(DateTimeStyle.Full, DateTimeStyle.Short, it, utc, is24HourFormat = true, numberingSystem = NumberingSystem.LATN)
            },
        ),
    )

    private fun months() = Section(
        "Month names",
        "Each month on its own, full and abbreviated, as a calendar heading or a menu would show it.",
        Month.entries.flatMap { month ->
            listOf(
                row("${month.displayName(TextWidth.Full, english)} (full)") { month.displayName(TextWidth.Full, it) },
                row("${month.displayName(TextWidth.Abbreviated, english)} (abbreviated)") { month.displayName(TextWidth.Abbreviated, it) },
            )
        },
    )

    private fun weekdays() = Section(
        "Weekday names",
        "Each day on its own, full and abbreviated. Check that each abbreviation belongs to its full name.",
        DayOfWeek.entries.flatMap { day ->
            listOf(
                row("${day.displayName(TextWidth.Full, english)} (full)") { day.displayName(TextWidth.Full, it) },
                row("${day.displayName(TextWidth.Abbreviated, english)} (abbreviated)") { day.displayName(TextWidth.Abbreviated, it) },
            )
        },
    )

    private fun times() = Section(
        "Times",
        "Morning and afternoon times on the 12-hour and 24-hour clocks. The Long style adds the zone, here Iqaluit in winter.",
        listOf(LocalTime(9, 5), LocalTime(17, 30)).flatMap { time ->
            listOf(
                row("$time, 12-hour clock") { time.format(DateTimeStyle.Short, it, utc, is24HourFormat = false, numberingSystem = NumberingSystem.LATN) },
                row("$time, 24-hour clock") { time.format(DateTimeStyle.Short, it, utc, is24HourFormat = true, numberingSystem = NumberingSystem.LATN) },
            )
        } + row("9:05:30, with seconds") { LocalTime(9, 5, 30).format(DateTimeStyle.Medium, it, utc, is24HourFormat = true, numberingSystem = NumberingSystem.LATN) }
            + iqaluit()?.let { zone ->
                listOf(row("9:05 in Iqaluit, with the zone") {
                    LocalDateTime(2024, 1, 15, 9, 5).toInstant(zone)
                        .format(DateTimeStyle.None, DateTimeStyle.Long, it, zone, is24HourFormat = true, numberingSystem = NumberingSystem.LATN)
                })
            }.orEmpty(),
    )

    private fun ranges() = Section(
        "Ranges",
        "A start and an end written together, with what they share written once.",
        listOf(
            row("9:00 to 11:30, 12-hour clock") { LocalTime(9, 0).formatRange(LocalTime(11, 30), locale = it, timeZone = utc, is24HourFormat = false, numberingSystem = NumberingSystem.LATN) },
            row("9:00 to 17:00, 24-hour clock") { LocalTime(9, 0).formatRange(LocalTime(17, 0), locale = it, timeZone = utc, is24HourFormat = true, numberingSystem = NumberingSystem.LATN) },
            row("3 to 5 March 2024") { LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 3, 5), DateTimeStyle.Long, it, NumberingSystem.LATN) },
            row("3 March to 2 April 2024") { LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 4, 2), DateTimeStyle.Long, it, NumberingSystem.LATN) },
            row("Monday to Friday") { DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Full, it) },
            row("Mon to Fri (abbreviated)") { DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, it) },
        ),
    )

    private fun iqaluit(): TimeZone? = runCatching { TimeZone.of("America/Iqaluit") }.getOrNull()

    private fun zones(): Section {
        val zones = listOf(
            "America/Iqaluit", "America/Rankin_Inlet", "America/Cambridge_Bay", "America/Toronto",
            "America/Winnipeg", "America/Edmonton", "America/Vancouver", "America/Halifax", "America/St_Johns", "UTC",
        ).mapNotNull { id -> runCatching { TimeZone.of(id) }.getOrNull()?.let { id to it } }
        val winter = LocalDateTime(2024, 1, 15, 12, 0)
        val summer = LocalDateTime(2024, 7, 15, 12, 0)
        return Section(
            "Time-zone names",
            "The zones of Nunavut and the rest of Canada, in winter and in summer. The full table of names is in the checklist on the issue.",
            zones.flatMap { (id, zone) ->
                listOf("winter" to winter, "summer" to summer).flatMap { (season, at) ->
                    val instant = at.toInstant(zone)
                    listOf(
                        row("$id, $season, full name") { zone.displayName(instant, TextWidth.Full, it) },
                        row("$id, $season, abbreviation") { zone.displayName(instant, TextWidth.Abbreviated, it) },
                    )
                }
            }.distinctBy { it.case.substringAfter(", ") + it.english + it.inuktitut },
        )
    }

    private val units = listOf(
        "second" to { n: Int -> n.seconds }, "minute" to { n: Int -> n.minutes }, "hour" to { n: Int -> n.hours },
        "day" to { n: Int -> n.days }, "week" to { n: Int -> (n * 7).days }, "month" to { n: Int -> (n * 30).days },
        "year" to { n: Int -> (n * 365).days },
    )
    private val counts = listOf(1, 2, 3, 5)

    private fun relative(): Section {
        fun phrase(delta: Duration) = { locale: Locale -> relativeTimeConfigFor(locale).formatter(delta, true) }
        val counted = units.flatMap { (unit, of) ->
            counts.flatMap { n ->
                listOf(
                    row("$n $unit${if (n > 1) "s" else ""} ago") { phrase(-of(n))(it) },
                    row("in $n $unit${if (n > 1) "s" else ""}") { phrase(of(n))(it) },
                )
            }
        }
        val labels = listOf(
            row("Just now") { relativeTimeConfigFor(it).nowString },
            row("Today") { relativeTimeConfigFor(it).todayString },
            row("Tomorrow") { relativeTimeConfigFor(it).tomorrowString },
            row("Yesterday") { relativeTimeConfigFor(it).yesterdayString },
            row("Recently: a date a few days back, when an app shows near dates loosely") { relativeTimeConfigFor(it).recentlyString },
            row("Shortly: a date a few days ahead, when an app shows near dates loosely") { relativeTimeConfigFor(it).shortlyString },
        )
        return Section(
            "Relative time",
            "How long ago, or how long until. Inuktitut is treated as having three counted forms: one, two, and three or more.",
            labels + counted,
        )
    }

    private fun durations() = Section(
        "Durations",
        "A length of time on its own, as in \"took 5 minutes\". A duration under half a second is written as the word for now.",
        listOf(row("Under half a second (0.2 s)") { 200.milliseconds.formatReadable(locale = it) }) +
            units.flatMap { (unit, of) ->
                counts.map { n -> row("$n $unit${if (n > 1) "s" else ""}") { of(n).formatReadable(locale = it) } }
            } +
            row("5 minutes, short style") { 5.minutes.formatReadable(RelativeStyle.Short, it) },
    )

    private fun ordinals() = Section(
        "Ordinals",
        "Position in a sequence: first, second, and so on, written with digits.",
        listOf(1, 2, 3, 4, 10, 21, 100).map { n -> row("$n (as in ${n.formatReadableOrdinal(english)})") { n.formatReadableOrdinal(it) } },
    )

    private fun lists() = Section(
        "Lists",
        "Several names joined into one phrase.",
        listOf(
            row("Two names, joined with \"and\"") { listOf("Mary", "Peter").formatReadableList(it, ListType.And) },
            row("Three names, joined with \"and\"") { listOf("Mary", "Peter", "Annie").formatReadableList(it, ListType.And) },
            row("Two names, joined with \"or\"") { listOf("Mary", "Peter").formatReadableList(it, ListType.Or) },
            row("Three names, joined with \"or\"") { listOf("Mary", "Peter", "Annie").formatReadableList(it, ListType.Or) },
            row("Three measurements, as a unit list", ownWords = false) { listOf("3 ft", "7 in", "2 lb").formatReadableList(it, ListType.Unit) },
        ),
    )

    private fun numbers(): Section {
        val dollar = Currency(code = "CAD", number = 124, digits = 2, name = "Canadian Dollar", symbol = "$")
        return Section(
            "Numbers and money",
            "The decimal mark, the thousands separator, and where the currency symbol goes.",
            listOf(
                row("1234.5") { 1234.5.formatReadable(it) },
                row("1000000") { 1_000_000.0.formatReadable(it, precision = 0) },
                row("-12.5") { (-12.5).formatReadable(it) },
                row("1234.50 dollars") { Money(123450L, dollar).formatReadable(it) },
            ),
        )
    }

    private fun directions() = Section(
        "Compass directions",
        "A bearing in degrees with its compass direction, as a map or a navigation screen shows it.",
        listOf(0.0, 45.0, 90.0, 135.0, 180.0, 225.0, 270.0, 315.0).map { degrees ->
            row("${degrees.toInt()} degrees") { Azimuth(degrees).formatReadable(it) }
        },
    )

    private fun sheet(): String {
        val sections = listOf(dates(), months(), weekdays(), times(), ranges(), zones(), relative(), durations(), ordinals(), lists(), numbers(), directions())
        // A row whose Inuktitut is the English text, with Latin letters in it, is one the library
        // has no Inuktitut for yet.
        fun isEnglish(row: Row) = row.ownWords && row.inuktitut == row.english && row.english.any { it in 'a'..'z' || it in 'A'..'Z' }
        val stillEnglish = sections.sumOf { section -> section.rows.count(::isEnglish) }
        fun cell(text: String) = text.replace("|", "\\|").replace("\u202F", " ").replace("\u2009", " ").replace("\u00A0", " ")
        return buildString {
            appendLine("# Inuktitut review sheet")
            appendLine()
            appendLine("Every piece of Inuktitut text the library writes, each beside the same thing in English. Please check each Inuktitut entry. Where it is wrong, or reads oddly, write what it should be in the last column. Where the Inuktitut column shows English, the library has no Inuktitut for it yet: please give the word.")
            appendLine()
            appendLine("Rows still in English: $stillEnglish.")
            appendLine()
            appendLine("This sheet is written by the library itself, so it shows what an app would show. Write corrections in the last column; do not edit the other columns.")
            for (section in sections) {
                appendLine()
                appendLine("## ${section.title}")
                appendLine()
                appendLine(section.note)
                appendLine()
                appendLine("| Case | English | Inuktitut | Correction |")
                appendLine("|---|---|---|---|")
                for (row in section.rows) {
                    val inuktitut = if (isEnglish(row)) "${cell(row.inuktitut)} *(English)*" else cell(row.inuktitut)
                    appendLine("| ${cell(row.case)} | ${cell(row.english)} | $inuktitut | |")
                }
            }
        }
    }

    @Test
    fun writesTheReviewSheet() {
        val root = generateSequence(File("").absoluteFile) { it.parentFile }.first { File(it, "settings.gradle.kts").exists() }
        val text = sheet()
        // Every case renders in both languages; an empty cell would be a case the library dropped.
        val emptyCells = text.lines().filter { it.startsWith("| ") && it.contains("|  |") && !it.endsWith("|  | |") }
        assertTrue(emptyCells.isEmpty(), "empty cells:\n" + emptyCells.joinToString("\n"))
        File(root, SHEET).apply { parentFile.mkdirs(); writeText(text) }
    }

    private companion object {
        const val SHEET = "docs/outbox/inuktitut-review-sheet.md"
    }
}
