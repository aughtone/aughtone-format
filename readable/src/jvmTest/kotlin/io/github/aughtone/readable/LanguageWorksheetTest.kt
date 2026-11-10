package io.github.aughtone.readable

import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.datetime.format.TextWidth
import io.github.aughtone.datetime.format.displayName
import io.github.aughtone.datetime.format.format
import io.github.aughtone.datetime.format.resources.NumberingSystem
import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.fields.formatReadableFields
import io.github.aughtone.readable.financial.formatReadable
import io.github.aughtone.readable.list.ListType
import io.github.aughtone.readable.list.formatReadableList
import io.github.aughtone.readable.number.formatReadable
import io.github.aughtone.readable.ordinality.formatReadableOrdinal
import io.github.aughtone.readable.quantitative.cardinalDirectionsFor
import io.github.aughtone.readable.relative.RelativeStyle
import io.github.aughtone.readable.relative.relativeTimeConfigFor
import io.github.aughtone.types.financial.Currency
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.locale.Locale
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Writes a translation worksheet for each Canadian Indigenous language the types library is adding
 * (PRD-0001): every term the library writes, in English and in French (Canada), with a blank column
 * for the language and a column saying whether Unicode CLDR holds that term.
 *
 * Each worksheet is written for the language authority consulted on the language. The English and
 * French columns are the library's own output, so they always show what an app would show. The
 * CLDR column marks the rows that could be submitted to CLDR with the authority's help (PRD-0001
 * requirement 9); the rest stay in this library only.
 *
 * Inuktitut has its own text already and is reviewed through [InuktitutReviewSheetTest] instead.
 *
 * The worksheets are written to [DIRECTORY] on every JVM test run: run `./gradlew :readable:jvmTest`.
 * `docs/outbox/` is not committed, because what goes in it is written for people outside the project.
 */
class LanguageWorksheetTest {

    /**
     * A language to write a worksheet for, from RAD-0004's table. [script] is CLDR's default
     * script for it, or `null` where CLDR has none; [variety] is the note for a macrolanguage.
     */
    private class Language(val tag: String, val name: String, val script: String?, val variety: String? = null)

    private val languages = listOf(
        Language("cr", "Cree", "Cans", "Cree is a family of six varieties (Plains, Woods, Swampy, Moose, Northern East and Southern East Cree). Please say which variety these words are."),
        Language("oj", "Ojibwe (Anishinaabemowin)", "Cans", "Ojibwe has several varieties. Please say which variety these words are."),
        Language("ik", "Inupiaq", "Latn", "Inupiaq has two varieties. Please say which variety these words are."),
        Language("hur", "Halkomelem", "Latn"),
        Language("squ", "Squamish", "Latn"),
        Language("str", "Straits Salish (SENĆOŦEN)", "Latn"),
        Language("kwk", "Kwak'wala", "Latn"),
        Language("nuk", "Nuu-chah-nulth", "Latn"),
        Language("hai", "Haida", "Latn", "Haida has two varieties. Please say which variety these words are."),
        Language("shs", "Secwepemctsín", "Latn"),
        Language("oka", "nsyilxcən (Okanagan)", "Latn"),
        Language("lil", "St'át'imcets", "Latn"),
        Language("bla", "Blackfoot (Siksiká)", "Latn"),
        Language("crg", "Michif", "Latn"),
        Language("chp", "Dene Suline", "Latn"),
        Language("dgr", "Tłı̨chǫ", "Latn"),
        Language("gwi", "Gwich'in", "Latn"),
        Language("moh", "Mohawk (Kanien'kéha)", "Latn"),
        Language("cay", "Cayuga", "Latn"),
        Language("one", "Oneida", "Latn"),
        Language("wya", "Wendat", null),
        Language("moe", "Innu-aimun", "Latn"),
        Language("atj", "Atikamekw", "Latn"),
        Language("mic", "Mi'kmaq", "Latn"),
        Language("pqm", "Wolastoqey", "Latn"),
    )

    private val english = Locale(languageCode = "en", regionCode = "CA", displayName = "English (Canada)")
    private val french = Locale(languageCode = "fr", regionCode = "CA", displayName = "French (Canada)")

    /** One term: what it is, its English and French text, and whether CLDR holds it. */
    private class Row(val term: String, val english: String, val french: String, val cldr: Boolean)

    private class Section(val title: String, val note: String, val rows: List<Row>)

    private fun row(term: String, cldr: Boolean = true, render: (Locale) -> String) =
        Row(term, render(english), render(french), cldr)

    private val utc = TimeZone.UTC
    private val latin = NumberingSystem.LATN

    private fun months() = Section(
        "Month names",
        "Each month, full and abbreviated, as a calendar heading or a date would show it.",
        Month.entries.flatMap { month ->
            val name = month.displayName(TextWidth.Full, english)
            listOf(
                row("$name, full") { month.displayName(TextWidth.Full, it) },
                row("$name, abbreviated") { month.displayName(TextWidth.Abbreviated, it) },
            )
        },
    )

    private fun weekdays() = Section(
        "Weekday names",
        "Each day of the week, full and abbreviated.",
        DayOfWeek.entries.flatMap { day ->
            val name = day.displayName(TextWidth.Full, english)
            listOf(
                row("$name, full") { day.displayName(TextWidth.Full, it) },
                row("$name, abbreviated") { day.displayName(TextWidth.Abbreviated, it) },
            )
        },
    )

    private fun clock() = Section(
        "Morning and afternoon",
        "The words that mark a time before and after noon on a 12-hour clock. If the language does not use a 12-hour clock, please say so.",
        listOf(
            row("Before noon (9:05 in the morning)") { LocalTime(9, 5).format(DateTimeStyle.Short, it, utc, is24HourFormat = false, numberingSystem = latin) },
            row("After noon (5:30 in the afternoon)") { LocalTime(17, 30).format(DateTimeStyle.Short, it, utc, is24HourFormat = false, numberingSystem = latin) },
        ),
    )

    private fun eras() = Section(
        "Eras",
        "The names for the years before and after year 1 of the calendar, full and abbreviated. They are shown only when an app asks for them, or for a date before year 1.",
        listOf(
            row("Common era, full") { LocalDate(2024, 3, 15).formatReadableFields("GGGGy", it) },
            row("Common era, abbreviated") { LocalDate(2024, 3, 15).formatReadableFields("Gy", it) },
            row("Before the common era, full") { LocalDate(-43, 3, 15).formatReadableFields("GGGGy", it) },
            row("Before the common era, abbreviated") { LocalDate(-43, 3, 15).formatReadableFields("Gy", it) },
        ),
    )

    private val units = listOf(
        "second" to { n: Int -> n.seconds }, "minute" to { n: Int -> n.minutes }, "hour" to { n: Int -> n.hours },
        "day" to { n: Int -> n.days }, "week" to { n: Int -> (n * 7).days }, "month" to { n: Int -> (n * 30).days },
        "year" to { n: Int -> (n * 365).days },
    )

    private fun relative(): Section {
        fun phrase(delta: Duration) = { locale: Locale -> relativeTimeConfigFor(locale).formatter(delta, true) }
        val labels = listOf(
            row("Just now") { relativeTimeConfigFor(it).nowString },
            row("Today") { relativeTimeConfigFor(it).todayString },
            row("Tomorrow") { relativeTimeConfigFor(it).tomorrowString },
            row("Yesterday") { relativeTimeConfigFor(it).yesterdayString },
            row("Recently: a day a few days back, when an app shows near dates loosely", cldr = false) { relativeTimeConfigFor(it).recentlyString },
            row("Shortly: a day a few days ahead, when an app shows near dates loosely", cldr = false) { relativeTimeConfigFor(it).shortlyString },
        )
        val counted = units.flatMap { (unit, of) ->
            listOf(
                row("1 $unit ago") { phrase(-of(1))(it) },
                row("3 ${unit}s ago") { phrase(-of(3))(it) },
                row("in 1 $unit") { phrase(of(1))(it) },
                row("in 3 ${unit}s") { phrase(of(3))(it) },
            )
        }
        return Section(
            "Relative time",
            "How long ago something happened, or how long until it will. If the language counts two (or any other number) differently from one and from three, please add the form for that number too.",
            labels + counted,
        )
    }

    private fun durations() = Section(
        "Durations",
        "A length of time on its own, as in \"it took 5 minutes\". Please add a form for any number the language counts differently.",
        units.flatMap { (unit, of) ->
            listOf(
                row("1 $unit") { of(1).formatReadable(locale = it) },
                row("3 ${unit}s") { of(3).formatReadable(locale = it) },
            )
        } + row("5 minutes, in the short form an app uses where space is tight") { 5.minutes.formatReadable(RelativeStyle.Short, it) },
    )

    private fun ordinals() = Section(
        "Ordinals",
        "Position in a sequence, written with digits: first, second, third.",
        listOf(1, 2, 3, 10).map { n -> row("$n, as in ${n.formatReadableOrdinal(english)}") { n.formatReadableOrdinal(it) } },
    )

    private fun lists() = Section(
        "Lists",
        "Several names joined into one phrase, with \"and\" and with \"or\".",
        listOf(
            row("Two names, with \"and\"") { listOf("Mary", "Peter").formatReadableList(it, ListType.And) },
            row("Three names, with \"and\"") { listOf("Mary", "Peter", "Annie").formatReadableList(it, ListType.And) },
            row("Two names, with \"or\"") { listOf("Mary", "Peter").formatReadableList(it, ListType.Or) },
            row("Three names, with \"or\"") { listOf("Mary", "Peter", "Annie").formatReadableList(it, ListType.Or) },
        ),
    )

    private fun directions(): Section {
        val points = listOf("North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest")
        return Section(
            "Compass directions",
            "The eight compass points, abbreviated as a map or a navigation screen shows them.",
            points.mapIndexed { index, point -> row(point, cldr = false) { cardinalDirectionsFor(it)[index] } },
        )
    }

    private fun zones(): Section {
        val zones = listOf("America/Vancouver", "America/Edmonton", "America/Winnipeg", "America/Toronto", "America/Halifax", "America/St_Johns")
            .mapNotNull { id -> runCatching { TimeZone.of(id) }.getOrNull() }
        val seasons = listOf("winter" to LocalDateTime(2024, 1, 15, 12, 0), "summer" to LocalDateTime(2024, 7, 15, 12, 0))
        return Section(
            "Time-zone names",
            "The time zones of Canada, in winter and in summer, full and abbreviated. Please give the names the language uses, if it has them.",
            zones.flatMap { zone ->
                seasons.flatMap { (season, at) ->
                    val instant = at.toInstant(zone)
                    val name = zone.displayName(instant, TextWidth.Full, english)
                    listOf(
                        row("$name ($season), full") { zone.displayName(instant, TextWidth.Full, it) },
                        row("$name ($season), abbreviated") { zone.displayName(instant, TextWidth.Abbreviated, it) },
                    )
                }
            },
        )
    }

    private fun conventions(): Section {
        val date = LocalDate(2024, 3, 15)
        val dollar = Currency(code = "CAD", number = 124, digits = 2, name = "Canadian Dollar", symbol = "$")
        return Section(
            "How dates, times and numbers are written",
            "Not words to translate, but the order and the marks the language uses. Please write each example the way the language would.",
            listOf(DateTimeStyle.Full, DateTimeStyle.Long, DateTimeStyle.Medium, DateTimeStyle.Short).map { style ->
                row("15 March 2024, $style style") { date.format(style, locale = it, timeZone = utc, numberingSystem = latin) }
            } + listOf(
                row("A date and a time together") { LocalDateTime(2024, 3, 15, 17, 30).format(DateTimeStyle.Long, DateTimeStyle.Short, it, utc, is24HourFormat = true, numberingSystem = latin) },
                row("A time on the 24-hour clock") { LocalTime(17, 30).format(DateTimeStyle.Short, it, utc, is24HourFormat = true, numberingSystem = latin) },
                row("The number 1234.5") { 1234.5.formatReadable(it) },
                row("1234.50 Canadian dollars") { Money(123450L, dollar).formatReadable(it) },
            ),
        )
    }

    private fun sections() = listOf(
        months(), weekdays(), clock(), eras(), relative(), durations(), ordinals(), lists(), directions(), zones(), conventions(),
    )

    private fun cell(text: String) = text.replace("|", "\\|").replace("\u202F", " ").replace("\u2009", " ").replace("\u00A0", " ")

    private fun worksheet(language: Language, sections: List<Section>): String = buildString {
        val terms = sections.sumOf { it.rows.size }
        val inCldr = sections.sumOf { section -> section.rows.count { it.cldr } }
        appendLine("# ${language.name} worksheet")
        appendLine()
        appendLine("Language tag: `${language.tag}` (and `${language.tag}-CA`). Terms: $terms, of which $inCldr are data Unicode CLDR holds.")
        appendLine()
        appendLine("These are the words and conventions an open-source formatting library uses to write dates, times, durations and similar text in an app. The library has none of them in ${language.name} yet, and today writes English in their place. We would like to write them in ${language.name}, as the language's own speakers would, and only with their guidance.")
        appendLine()
        appendLine("Please write the ${language.name} for each row in the ${language.name} column. The English and French columns are what the library writes today in those languages; use whichever is easier to work from. Leave a row blank if the language has no such word, or if it is better left in English or French, and say so in the notes column if you can.")
        appendLine()
        language.variety?.let { appendLine(it); appendLine() }
        appendLine(
            when (language.script) {
                "Cans" -> "Unicode CLDR expects ${language.name} in syllabics. If the community writes these words in the Latin alphabet, or both, please tell us."
                null -> "Unicode CLDR does not record how ${language.name} is written. Please write it as the community writes it, and tell us which alphabet that is."
                else -> "Unicode CLDR expects ${language.name} in the Latin alphabet. If the community writes it otherwise, please tell us."
            },
        )
        appendLine()
        appendLine("**Unicode CLDR** is the international standard for this kind of data, used by phones, computers and web browsers worldwide; it has no ${language.name} data yet. Rows marked *Yes* in the CLDR column are data CLDR holds. With your agreement, we would like to ask your help to submit them, so ${language.name} reaches every device and not only this library. Rows marked *No* would stay in this library only.")
        appendLine()
        appendLine("**Credit.** The library's documentation will name the authority consulted for ${language.name}, in the way you ask.")
        appendLine()
        appendLine("- Language authority consulted:")
        appendLine("- How the authority would like to be credited:")
        appendLine("- Variety and writing system of these words:")
        appendLine("- Agreement to help submit the CLDR rows to Unicode CLDR:")
        for (section in sections) {
            appendLine()
            appendLine("## ${section.title}")
            appendLine()
            appendLine(section.note)
            appendLine()
            appendLine("| Term | English | French | ${language.name} | Notes | CLDR |")
            appendLine("|---|---|---|---|---|---|")
            for (row in section.rows) {
                appendLine("| ${cell(row.term)} | ${cell(row.english)} | ${cell(row.french)} | | | ${if (row.cldr) "Yes" else "No"} |")
            }
        }
    }

    @Test
    fun writesAWorksheetForEachLanguage() {
        val root = generateSequence(File("").absoluteFile) { it.parentFile }.first { File(it, "settings.gradle.kts").exists() }
        val directory = File(root, DIRECTORY).apply { mkdirs() }
        val sections = sections()
        // Every term renders in both reference languages; an empty cell would be a term the library dropped.
        val empty = sections.flatMap { it.rows }.filter { it.english.isBlank() || it.french.isBlank() }.map { it.term }
        assertTrue(empty.isEmpty(), "terms with no English or French text: $empty")
        assertEquals(languages.size, languages.map { it.tag }.toSet().size, "a language is listed twice")
        val index = StringBuilder()
        index.appendLine("# Language worksheets")
        index.appendLine()
        index.appendLine("One worksheet for each Canadian Indigenous language in PRD-0001, each with ${sections.sumOf { it.rows.size }} terms. Inuktitut is reviewed through its own review sheet.")
        index.appendLine()
        for (language in languages) {
            val name = "${language.tag}-worksheet.md"
            File(directory, name).writeText(worksheet(language, sections))
            index.appendLine("- [${language.name}](${name}) — `${language.tag}`")
        }
        File(directory, "README.md").writeText(index.toString())
    }

    private companion object {
        const val DIRECTORY = "docs/outbox/worksheets"
    }
}
