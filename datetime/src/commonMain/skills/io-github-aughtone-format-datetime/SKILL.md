---
name: io-github-aughtone-format-datetime
description: >-
  The absolute date-and-time member of the aughtone-format family, formatting a
  date or time for display, locale-aware, in Kotlin Multiplatform common code
  with no java.time. Show a date, a time, or both in short, medium, long or full
  style ("Jan 12, 1952", "4:08 p.m."), as DateFormat or
  DateTimeFormatter.ofLocalizedDate would on the JVM; format a timestamp or epoch
  milliseconds; show a start-to-end date range with a placeholder for a missing
  end; get weekday and month names in the user's language ("Monday"/"Mon",
  "Mars"); get a time zone's localized name ("Eastern Standard Time", "EST");
  follow the locale's 12-hour or 24-hour clock and its native digits. Works on
  kotlinx-datetime LocalDate, LocalTime, LocalDateTime and kotlin.time.Instant.
  Not for relative time like "3 days ago" or "tomorrow", durations or numbers
  (use io.github.aughtone:format-readable); not for custom pattern strings such as
  "yyyy-MM-dd"; and not a parser.
license: Apache-2.0
metadata:
  version: "4.1.1"
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

**`Instant.format()` with no arguments appends a time-zone token.** Its `timeStyle` **defaults to `Long`**, and `Long`/`Full` time styles append a zone abbreviation or full name ("4:08:39 p.m. UTC"). Symptom: an unexpected zone token on the end — pass `timeStyle = DateTimeStyle.Short` (or `Medium`) to drop it. Likewise `DateTimeStyle.Full` on a date appends an era word ("… 1952 Common Era"). `Instant.format` and `LocalDateTime.format` take `eraNames = EraNames(bce = …, ce = …)` to change that wording; `LocalDate.format` has no such parameter.

**Locales silently switch digit systems.** Even with `numberingSystem = null`, Arabic (`ar`), Persian/Urdu (`fa`/`ur`), Hindi (`hi`), Bengali (`bn`), and Thai (`th`) render localized digits. Symptom: non-ASCII digits in the output — force Western digits with `numberingSystem = NumberingSystem.LATN`.

**`is24HourFormat` detection differs by platform — pass it explicitly for reproducible output.** The default probes the platform: it is a real system setting on Android (needs the app `Context`, wired via `androidx.startup`) and on iOS, and on JS and Wasm it is the hour cycle `Intl` resolves for the browser's own locale. On the JVM there is no detection at all — it uses the CLDR convention for the `locale` you pass: 12-hour for `en`, `en-US`, `es-MX`, `ko`, `hi`; 24-hour for `en-GB`, `de`, `fr`. A language it does not know follows its region (12-hour in the US, Canada, Australia, India, Mexico and a few others) and is otherwise 24-hour. So off the JVM the *device* decides and the `locale` argument does not. Symptom: the same locale renders 12- vs 24-hour differently across targets — supply `is24HourFormat = true/false` when determinism matters.

**The AM/PM marker goes where the language writes it.** After the time for most languages (`4:08 PM`), before it for Korean, Hungarian and Turkish, and before it with no space for Japanese and Chinese.

**Month names inside a date are not always the names `Month.displayName` returns.** Czech, Finnish, Polish, Russian and Ukrainian use a different form after a day number: a date reads `5 марта 2024 г.`, while `Month.MARCH.displayName(…)` is `Март`. Symptom: a hand-built date string that reads wrong to a native speaker — format the whole date instead of concatenating a month name.

**These `Instant`s are `kotlin.time.Instant`, not `kotlinx.datetime.Instant`.** The `Instant` and `Pair<Instant?, Instant?>` APIs use the standard library's `kotlin.time.Instant`. Symptom: a type mismatch when you pass a `kotlinx.datetime.Instant`.

**No bundled pattern throws.** `None`/`None` yields `""`, and there are no `@Throws` and no deprecated symbols in this module. Two things outside the patterns can still throw: the default `locale = Locale.current` throws `IllegalArgumentException` if the platform reports no locale at all, and an `Instant` outside the range `LocalDateTime` can hold throws from kotlinx-datetime when it is converted.

## What moved, and what it used to be called

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

**New in 4.1.0: standalone names and time-zone names.** `DayOfWeek.displayName(width, locale)` and `Month.displayName(width, locale)` expose the locale's day and month names — `Full`/`Abbreviated` via the `TextWidth` enum — that were previously reachable only inside a formatted date. There is no `Narrow` width, and `displayName` always gives the stand-alone form; unsupported locales fall back to English. `TimeZone.displayName(instant, width, locale)` gives the zone's localized *specific* name at that instant ("Eastern Standard Time" / "EST"); it is instant-keyed because standard vs. daylight depends on the moment. The name comes from the **platform's own CLDR** (`java.time`, Android ICU, Apple `NSTimeZone`, JS/Wasm `Intl`), so its exact wording follows the reader's OS and runtime — the library favours coverage over byte-identical consistency here — with a small bundled supplement for languages no platform ships (notably Inuktitut) and an English-name / UTC-offset fallback. Because the wording is the platform's, it can differ across targets (UTC reads "Coordinated Universal Time" on most, "Greenwich Mean Time" on Apple). The instant-independent *generic* name ("Eastern Time") is not provided.

**`Instant` migrated from `kotlinx.datetime.Instant` to `kotlin.time.Instant` in 2.1.0.** An agent that "remembers" the older API will reach for `kotlinx.datetime.Instant`; the `format` extensions and `Long.toInstant()` are on `kotlin.time.Instant` now.

Source files were renamed to a `Type.extensions.kt` convention (e.g. `InstantExt.kt` → `Instant.extensions.kt`), but the extension *function* names and packages are unchanged, so this is not consumer-visible. No public API is deprecated.

## What it is not for

- **Not relative or humanized time.** "3 days ago", "Tomorrow", "in 5 minutes" belong to `io.github.aughtone:format-readable` (which builds on this module). This module renders absolute, styled date/time.
- **Not custom pattern strings.** Formatting is by `DateTimeStyle` (`Short`, `Medium`, `Long`, `Full`, `None`) against the locale's own patterns; there is no public API to pass an arbitrary skeleton or `pattern` of your own.
- **Not a parser.** It formats values and converts epoch-millis `Long`s; parsing date/time *text* is `kotlinx-datetime`'s job (`LocalDate.parse`, etc.).
