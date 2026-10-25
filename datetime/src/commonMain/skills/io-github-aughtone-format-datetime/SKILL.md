---
name: io-github-aughtone-format-datetime
description: >-
  The absolute date-and-time member of the aughtone-format family, formatting a
  date or time for display, locale-aware, in Kotlin Multiplatform common code
  with no java.time. Show a date, a time, or both in short, medium, long or full
  style ("Jan 12, 1952", "4:08 p.m."), as DateFormat or
  DateTimeFormatter.ofLocalizedDate would on the JVM; format a timestamp or epoch
  milliseconds; show a range the way the locale writes one ("March 3 – 5, 2024",
  "9:00 – 11:30 AM", "Mon – Fri"); get weekday and month names in the user's
  language ("Monday"/"Mon", "Mars"); get a time zone's localized name, at an
  instant ("Eastern Standard Time") or all year ("Eastern Time"); map a Windows
  zone name to an IANA id; follow the locale's clock and digits. Works on kotlinx-datetime LocalDate, LocalTime, LocalDateTime
  and kotlin.time.Instant. Not for relative time like "3 days ago" or "tomorrow",
  durations or numbers (use io.github.aughtone:format-readable); not for custom
  pattern strings such as "yyyy-MM-dd"; and not a parser.
license: Apache-2.0
metadata:
  version: "4.2.1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-datetime

## What it solves

Formatting a date or time for display, by *style* rather than by pattern string, in Kotlin Multiplatform common code — the locale-aware rendering that `java.time.format`/`DateFormat` give you on the JVM but that common Kotlin lacks. You hand it a value and a `DateTimeStyle`; it produces the localized string, with the locale's own month/day names, ordering, 12/24-hour convention, and (where the locale calls for it) digit system.

It formats `kotlinx.datetime` `LocalDate`/`LocalTime`/`LocalDateTime`, `kotlin.time.Instant`, and nullable `Pair` ranges, and offers `Long`-epoch-millis conversions into those types.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts (common, JVM, Android, and iOS via Kotlin); there is no separate Swift or JavaScript API.

## How it is meant to be used

Every entry point is a `.format(...)` extension. Styles are required for the calendar types and defaulted for `Instant`.

```kotlin
import io.github.aughtone.datetime.format.*
import io.github.aughtone.types.locale.localeFor
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlin.time.Instant

val dt = LocalDateTime.parse("2022-01-01T12:00:00")
dt.format(dateStyle = DateTimeStyle.Short, timeStyle = DateTimeStyle.Short,
          locale = localeFor("en-CA")!!, timeZone = TimeZone.UTC)   // "2022-01-01 12:00 p.m."

LocalDate(1952, 1, 12).format(DateTimeStyle.Medium, locale = localeFor("en-US")!!)  // "Jan 12, 1952"
LocalTime(16, 8, 39).format(DateTimeStyle.Short, is24HourFormat = true)            // "16:08"

// Ranges, as the locale writes them (CLDR interval formats)
LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 3, 5), DateTimeStyle.Long, localeFor("en-US")!!) // "March 3 – 5, 2024"
LocalTime(9, 0).formatRange(LocalTime(11, 30), locale = localeFor("en-US")!!, is24HourFormat = false) // "9:00 – 11:30 AM"
LocalDateTime(2024, 3, 5, 9, 0).formatRange(LocalDateTime(2024, 3, 5, 17, 0),
    DateTimeStyle.Long, DateTimeStyle.Short, localeFor("en-US")!!, is24HourFormat = false) // "March 5, 2024 9:00 AM – 5:00 PM"
DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, localeFor("en-US")!!)     // "Mon – Fri"

// Nullable range with a placeholder for the missing end
val start: Instant = Instant.parse("2023-03-15T13:20:00Z")
Pair<Instant?, Instant?>(start, null).format(placeholder = "#")     // "… - #"

// Epoch millis -> kotlinx-datetime types
val date = 1_700_000_000_000L.toLocalDate()

// Standalone localized weekday / month names
DayOfWeek.MONDAY.displayName(locale = localeFor("fr")!!)            // "lundi"
Month.MARCH.displayName(TextWidth.Abbreviated, localeFor("en")!!)   // "Mar"
```

`Locale` is `io.github.aughtone.types.locale.Locale`; build one with `localeFor("en-CA")` (nullable — needs `!!`) or the `Locale(...)` constructor. The override types live in sub-packages the star import does not reach: `EraNames` in `io.github.aughtone.datetime.format.resources.values`, `NumberingSystem` in `io.github.aughtone.datetime.format.resources`.

## Invariants and traps

**`Pair.format` on two nulls returns `"? - ?"`, not `""`.** The code always emits `"<first> - <second>"`, so both-null gives `"? - ?"` (or `"<placeholder> - <placeholder>"`) and a one-null range renders the missing side as the placeholder. Symptom: a stray `"?"` (or your placeholder) where you expected an empty string or a bare single value.

**`formatRange` and `Pair.format` are different tools.** `formatRange` writes a complete range as the locale does — the shared parts once, the locale's own separator and spacing. `Pair.format` joins two independently formatted values with `" - "` and exists for a range with a missing end. Use `formatRange` whenever both ends are known.

**Range separators are not plain spaces.** CLDR writes a thin space (U+2009) around the en dash ("3\u2009–\u20095"), and some languages use no space or another mark ("3.–5. März 2024", "9:00～11:30", "09:00 ~ 17:00"). Symptom: an `assertEquals` against `"March 3 – 5, 2024"` typed with ordinary spaces fails although the text looks identical — build the expectation with `"\u2009–\u2009"`, or compare after normalising spaces.

**A range is shortened only where CLDR has a pattern for it.** Time ranges are shortened at the `Short` style only; at `Medium`, `Long` and `Full` they are two complete times joined by the separator, each with its own seconds or zone name. Date ranges are shortened at every style. A `LocalDateTime` or `Instant` range on one day writes the date once and then the time range; across days it writes both ends in full. Equal ends are written once, and an end before the start is written in the order given, not swapped.

**`Instant.format()` with no arguments appends a time-zone token.** Its `timeStyle` **defaults to `Long`**, and `Long`/`Full` time styles append a zone abbreviation or full name ("4:08:39 p.m. UTC"). Symptom: an unexpected zone token on the end — pass `timeStyle = DateTimeStyle.Short` (or `Medium`) to drop it.

**No style shows the era unless asked.** `DateTimeStyle.Full` is "Saturday, January 12, 1952"; pass `showEra = true` (on `LocalDate.format`, `LocalDateTime.format` and `Instant.format`) for the locale's abbreviation, "… 1952 CE", or `eraNames = EraNames(bce = …, ce = …)` for words of your own, which also shows it. A date before year 1 always shows its era, at every style, with the year counted from one ("January 1, 501 BCE"): without it the year would read as one of the common era. Symptom: an unexpected "BCE" on an ancient date, or a missing era you expected from `Full`.

**Some locales default to native digits, and Arabic's depend on the region.** With `numberingSystem = null` the digits are CLDR's default for the locale: native for Persian (`fa`), Bengali (`bn`), Marathi (`mr`) and Urdu in India (`ur-IN`); Arabic-Indic for Arabic in Egypt, Saudi Arabia, Iraq, Jordan and the other regions that write them, but Latin for bare `ar`, the UAE and the Maghreb; Latin for Hindi, Thai and Urdu in Pakistan. Symptom: non-ASCII digits for `ar-EG` but not for `ar` — force a system with `numberingSystem = NumberingSystem.LATN` (or `DEVA`, `THAI`, `ARAB`, …).

**`is24HourFormat` detection differs by platform — pass it explicitly for reproducible output.** The default probes the platform: it is a real system setting on Android (needs the app `Context`, wired via `androidx.startup`) and on iOS, and on JS and Wasm it is the hour cycle `Intl` resolves for the browser's own locale. On the JVM there is no detection at all — it uses the CLDR convention for the `locale` you pass: 12-hour for `en`, `en-US`, `es-MX`, `ko`, `hi`; 24-hour for `en-GB`, `de`, `fr`. A language it does not know follows its region (12-hour in the US, Canada, Australia, India, Mexico and a few others) and is otherwise 24-hour. So off the JVM the *device* decides and the `locale` argument does not. Symptom: the same locale renders 12- vs 24-hour differently across targets — supply `is24HourFormat = true/false` when determinism matters.

**The AM/PM marker goes where the language writes it.** After the time for most languages (`4:08 PM`), before it for Korean, Hungarian and Turkish, and before it with no space for Japanese and Chinese.

**Month and weekday names inside a date are not always the names `displayName` returns.** Inside a date they are CLDR's in-date form: lower case in Spanish, French, Italian, Portuguese, Czech, Kazakh, Uzbek and Vietnamese (`15 de marzo de 2024`, `viernes, 15 de marzo de 2024`), and a different grammatical form in Czech, Finnish, Polish, Russian and Ukrainian (`5 марта 2024 г.`). `Month.displayName` and `DayOfWeek.displayName` give the stand-alone name, which these languages capitalise (`Marzo`, `Март`). A date that opens a sentence is not capitalised for you. Symptom: a hand-built date string that reads wrong to a native speaker — format the whole date instead of concatenating a name.

**These `Instant`s are `kotlin.time.Instant`, not `kotlinx.datetime.Instant`.** The `Instant` and `Pair<Instant?, Instant?>` APIs use the standard library's `kotlin.time.Instant`. Symptom: a type mismatch when you pass a `kotlinx.datetime.Instant`.

**No bundled pattern throws.** `None`/`None` yields `""`, and there are no `@Throws` and no deprecated symbols in this module. Two things outside the patterns can still throw: the default `locale = Locale.current` throws `IllegalArgumentException` if the platform reports no locale at all, and an `Instant` outside the range `LocalDateTime` can hold throws from kotlinx-datetime when it is converted.

## What moved, and what it used to be called

**New in 4.2.1: generic zone names and Windows zones.** `TimeZone.genericDisplayName(width, locale)` gives the name a zone keeps all year — "Eastern Time" / "ET" — for a weekly schedule or opening hours that span a daylight-saving change, where `displayName`'s "Eastern Standard Time" is wrong half the year; it comes from the platform's CLDR, like `displayName`. `TimeZone.ianaIdForWindowsName(name, territory)` turns a Windows zone name, as Outlook and Exchange write it in an iCalendar `TZID` ("Eastern Standard Time"), into an IANA identifier for `TimeZone.of` (`"America/New_York"`, or `"America/Toronto"` for territory `"CA"`), and `TimeZone.windowsNameForIanaId(id)` goes the other way. Both are from bundled CLDR data, the same on every platform, return `null` for a name CLDR does not list, and use current IANA names (`"Asia/Kolkata"`) while accepting older ones (`"Asia/Calcutta"`, `"US/Eastern"`).

**New in 4.2.0: ranges.** `LocalTime.formatRange`, `LocalDate.formatRange`, `LocalDateTime.formatRange`, `Instant.formatRange` and `DayOfWeek.formatRange`, from bundled CLDR interval formats. An `Instant` range names each end's zone at its own moment, so a range across a daylight-saving change reads "EST" at one end and "EDT" at the other. Before 4.2.0 the only range API was `Pair.format`, which joins two full values with `" - "`.

**Silent changes in 4.2.0 — same calls, different output:**

- `Full` dates no longer end with the era: "Saturday, January 12, 1952", where 4.1 wrote "… 1952 Common Era". `showEra = true` brings it back, abbreviated ("CE"). A date before year 1 now shows "BCE" at every style, not only at `Full`. Passing `eraNames` still shows the era.
- Month and weekday names inside a date are lower case where the language writes them so: Spanish `15 de marzo de 2024` (was `15 de Marzo de 2024`), French `15 mars 2024`, Italian, Portuguese, Czech, Kazakh, Uzbek, Vietnamese; abbreviations too, in those languages and in Russian, Ukrainian and Finnish (`15 мар. 2024 г.`, was `15 Мар 2024 г.`). `displayName` is unchanged.
- Default digits follow CLDR: Hindi, Thai, Urdu (outside India) and Arabic without a region write Latin digits, where 4.1 wrote native ones; Arabic in Egypt, Saudi Arabia and the other Arabic-Indic regions keeps them, and Marathi now gets Devanagari. Pass `numberingSystem` for the old output.
- Abbreviated Chinese month names are CLDR's `3月`; earlier versions returned the full name `三月` for `Month.displayName(TextWidth.Abbreviated, zh)`.

**Silent changes in 4.1.1 — same calls, different output.** Code or tests written against 4.1.0 output will see these:

- `Long` and `Full` dates no longer throw for Spanish, Portuguese, Catalan, Galician, Basque, Lithuanian and Latvian (nor `Full` for Danish and Finnish). In 4.1.0 and earlier they threw `IllegalArgumentException("Unrecognized pattern character …")`, because the quoted words in those patterns were read as pattern letters; on those versions only `Short` and `Medium` are safe for these languages.
- Literal text in a pattern reaches the output intact: Russian and Bulgarian dates lost their stray quote marks (`2024 'г'.`), Japanese times keep `時分秒`, and a zone in parentheses no longer leaves a doubled space.
- A two-digit year shows the last two digits in any century (`31.12.99`, was `31.12.+1999`); a bare year is not zero-padded (`800`, was `0800`); and at `Full` a year before the common era is counted from one (ISO year 0 is "1 Before Common Era").
- A region variant keeps its language's patterns: French in Canada is French, not English order. English in the UK, Ireland, Australia, New Zealand, India and South Africa writes the day first (`05/03/2024`).
- Month names in Czech, Finnish, Polish, Russian and Ukrainian dates take their in-date form (`5 марта`, was `5 Март`), and French October and November are their own names (4.1.0 had `October` and `Octobre`).
- The AM/PM marker moved before the time for Korean, Hungarian, Turkish, Japanese and Chinese; it was always a suffix.
- 12/24-hour: JS and Wasm now detect the setting (they never did); a locale with no region uses its language's convention, so a bare `en` is 12-hour (was 24-hour); `en-GB`, `en-IE` and `en-ZA` are 24-hour (were 12-hour).
- Zone names: `Instant.format` names the zone for the exact instant, so 01:30 after clocks go back reads "EST" (it read "EDT" for both passes). Zones that share an abbreviation keep their own names — London's "BST" read as Bangladesh Standard Time, Shanghai's "CST" as Central Standard Time, Jerusalem's "IST" as India Standard Time. London in winter gives "GMT" where it gave "Z".
- `LocalDate.format` no longer shifts to the next day in a zone where that day's midnight does not exist.

**Silent change in 4.1.0: zone names now come from the platform.** `Long`/`Full` time styles (and so the default `Instant.format()`) append a zone name that is now read from the platform's own CLDR instead of a bundled table. Same signature, but the wording can differ from 4.0.0 and between targets — UTC reads "UTC" / "Coordinated Universal Time" on the JVM and in the browser, "GMT" / "Greenwich Mean Time" on Apple. Tests that compared exact zone strings may need loosening. Inuktitut full weekday names also changed, to idiomatic day-words (ᐊᐃᑉᐱᖅ for Tuesday).

**New in 4.1.0: standalone names and time-zone names.** `DayOfWeek.displayName(width, locale)` and `Month.displayName(width, locale)` expose the locale's day and month names — `Full`/`Abbreviated` via the `TextWidth` enum — that were previously reachable only inside a formatted date. There is no `Narrow` width, and `displayName` always gives the stand-alone form; unsupported locales fall back to English. `TimeZone.displayName(instant, width, locale)` gives the zone's localized *specific* name at that instant ("Eastern Standard Time" / "EST"); it is instant-keyed because standard vs. daylight depends on the moment. The name comes from the **platform's own CLDR** (`java.time`, Android ICU, Apple `NSTimeZone`, JS/Wasm `Intl`), so its exact wording follows the reader's OS and runtime — the library favours coverage over byte-identical consistency here — with a small bundled supplement for languages no platform ships (notably Inuktitut) and an English-name / UTC-offset fallback. Because the wording is the platform's, it can differ across targets (UTC reads "Coordinated Universal Time" on most, "Greenwich Mean Time" on Apple). The instant-independent *generic* name ("Eastern Time") was added in 4.2.1 as `genericDisplayName`.

**`Instant` migrated from `kotlinx.datetime.Instant` to `kotlin.time.Instant` in 2.1.0.** An agent that "remembers" the older API will reach for `kotlinx.datetime.Instant`; the `format` extensions and `Long.toInstant()` are on `kotlin.time.Instant` now.

Source files were renamed to a `Type.extensions.kt` convention (e.g. `InstantExt.kt` → `Instant.extensions.kt`), but the extension *function* names and packages are unchanged, so this is not consumer-visible. No public API is deprecated.

## What it is not for

- **Not relative or humanized time.** "3 days ago", "Tomorrow", "in 5 minutes" belong to `io.github.aughtone:format-readable` (which builds on this module). This module renders absolute, styled date/time.
- **Not a chosen set of fields.** Formatting here is by `DateTimeStyle` (`Short`, `Medium`, `Long`, `Full`, `None`) against the locale's own patterns. A month and a day ("Jun 1"), a weekday and an hour, or a month and a year, laid out as the locale does, is `formatReadableFields` in `io.github.aughtone:format-readable`, package `io.github.aughtone.readable.fields`; it also takes a CLDR skeleton. Do not assemble one from `displayName` and a phrase of your own — that is right in one language. There is no API here for a `pattern` of your own; a layout fixed in every language is kotlinx-datetime's `Format { }`.
- **Not a parser.** It formats values and converts epoch-millis `Long`s; parsing date/time *text* is `kotlinx-datetime`'s job (`LocalDate.parse`, etc.).
