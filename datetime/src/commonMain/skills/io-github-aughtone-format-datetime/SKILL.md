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
  "mars"); get a time zone's localized name ("Eastern Standard Time", "EST");
  follow the locale's 12-hour or 24-hour clock and its native digits. Works on
  kotlinx-datetime LocalDate, LocalTime, LocalDateTime and kotlin.time.Instant.
  Not for relative time like "3 days ago" or "tomorrow", durations or numbers
  (use io.github.aughtone:format-readable); not for custom pattern strings such as
  "yyyy-MM-dd"; and not a parser.
license: Apache-2.0
metadata:
  version: "4.1.0"
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
import io.github.aughtone.datetime.format.DateTimeStyle
import io.github.aughtone.types.locale.localeFor

val dt = LocalDateTime.parse("2022-01-01T12:00:00")
dt.format(dateStyle = DateTimeStyle.Short, timeStyle = DateTimeStyle.Short,
          locale = localeFor("en-CA")!!, timeZone = TimeZone.UTC)   // "2022-01-01 12:00 p.m."

LocalDate(1952, 1, 12).format(DateTimeStyle.Medium)                 // "Jan 12, 1952"
LocalTime(16, 8, 39).format(DateTimeStyle.Short, is24HourFormat = true)  // "16:08"

// Nullable range with a placeholder for the missing end
Pair<Instant?, Instant?>(start, null).format(placeholder = "#")     // "… - #"

// Epoch millis -> kotlinx-datetime types
val date = 1_700_000_000_000L.toLocalDate()

// Standalone localized weekday / month names
DayOfWeek.MONDAY.displayName(locale = localeFor("fr")!!)            // "lundi"
Month.MARCH.displayName(TextWidth.Abbreviated, localeFor("en")!!)   // "Mar"
```

`Locale` is `io.github.aughtone.types.locale.Locale`; build one with `localeFor("en-CA")` (nullable — needs `!!`) or the `Locale(...)` constructor.

## Invariants and traps

**`Pair.format` on two nulls returns `"? - ?"`, not `""`.** The code always emits `"<first> - <second>"`, so both-null gives `"? - ?"` (or `"<placeholder> - <placeholder>"`) and a one-null range renders the missing side as the placeholder. Symptom: a stray `"?"` (or your placeholder) where you expected an empty string or a bare single value.

**`Instant.format()` with no arguments appends a time-zone token.** Its `timeStyle` **defaults to `Long`**, and `Long`/`Full` time styles append a zone abbreviation or full name ("4:08:39 p.m. UTC"). Symptom: an unexpected zone token on the end — pass `timeStyle = DateTimeStyle.Short` (or `Medium`) to drop it. Likewise `DateTimeStyle.Full` on a date appends an era word ("… 1952 Common Era"); override the wording with `eraNames = EraNames(bce = …, ce = …)`, consulted only at `Full`.

**Locales silently switch digit systems.** Even with `numberingSystem = null`, Arabic (`ar`), Persian/Urdu (`fa`/`ur`), Hindi (`hi`), Bengali (`bn`), and Thai (`th`) render localized digits. Symptom: non-ASCII digits in the output — force Western digits with `numberingSystem = NumberingSystem.LATN`.

**`is24HourFormat` detection differs by platform — pass it explicitly for reproducible output.** The default probes the platform: it is a real system setting on Android (needs the app `Context`, wired via `androidx.startup`) and locale-derived on iOS/JS, but on JVM and wasmJs there is no detection at all — it falls back to a locale-based guess (12-hour when the locale is unknown). Symptom: the same locale renders 12- vs 24-hour differently across targets — supply `is24HourFormat = true/false` when determinism matters.

**These `Instant`s are `kotlin.time.Instant`, not `kotlinx.datetime.Instant`.** The `Instant`/`Pair<Instant?, Instant?>` APIs use the stdlib `kotlin.time.Instant` (experimental time API); callers opt in to `kotlin.time.ExperimentalTime`. Symptom: a type mismatch when you pass a `kotlinx.datetime.Instant`.

**Nothing throws for a caller.** A formatter that cannot produce a string falls back to the value's `toString()` (raw ISO), and `None`/`None` yields `""`. Symptom: a raw ISO string in the output means the styled path could not run. There are no `@Throws`, and no deprecated symbols, in this module.

## What moved, and what it used to be called

**Silent change in 4.1.0: zone names now come from the platform.** `Long`/`Full` time styles (and so the default `Instant.format()`) append a zone name that is now read from the platform's own CLDR instead of a bundled table. Same signature, but the wording can differ from 4.0.0 and between targets — UTC reads "UTC" / "Coordinated Universal Time" on the JVM and in the browser, "GMT" / "Greenwich Mean Time" on Apple. Tests that compared exact zone strings may need loosening. Inuktitut full weekday names also changed, to idiomatic day-words (ᐊᐃᑉᐱᖅ for Tuesday).

**New in 4.1.0: standalone names and time-zone names.** `DayOfWeek.displayName(width, locale)` and `Month.displayName(width, locale)` expose the locale's day and month names — `Full`/`Abbreviated` via the `TextWidth` enum — that were previously reachable only inside a formatted date. There is no `Narrow` width and no separate stand-alone-vs-in-context form yet; unsupported locales fall back to English. `TimeZone.displayName(instant, width, locale)` gives the zone's localized *specific* name at that instant ("Eastern Standard Time" / "EST"); it is instant-keyed because standard vs. daylight depends on the moment. The name comes from the **platform's own CLDR** (`java.time`, Android ICU, Apple `NSTimeZone`, JS/Wasm `Intl`), so its exact wording follows the reader's OS and runtime — the library favours coverage over byte-identical consistency here — with a small bundled supplement for languages no platform ships (notably Inuktitut) and an English-name / UTC-offset fallback. Because the wording is the platform's, it can differ across targets (UTC reads "Coordinated Universal Time" on most, "Greenwich Mean Time" on Apple). The instant-independent *generic* name ("Eastern Time") is not provided.

**`Instant` migrated from `kotlinx.datetime.Instant` to `kotlin.time.Instant`.** This landed in the 4.0.0 cycle and is the stable state, but an agent that "remembers" the older API will reach for `kotlinx.datetime.Instant`; the `format` extensions and `Long.toInstant()` are on `kotlin.time.Instant` now.

Source files were renamed to a `Type.extensions.kt` convention (e.g. `InstantExt.kt` → `Instant.extensions.kt`), but the extension *function* names and packages are unchanged, so this is not consumer-visible. No public API is deprecated.

## What it is not for

- **Not relative or humanized time.** "3 days ago", "Tomorrow", "in 5 minutes" belong to `io.github.aughtone:format-readable` (which builds on this module). This module renders absolute, styled date/time.
- **Not custom pattern strings.** Formatting is by `DateTimeStyle` (`Short`, `Medium`, `Long`, `Full`, `None`) against the locale's own patterns; there is no public API to pass an arbitrary skeleton or `pattern` of your own.
- **Not a parser.** It formats values and converts epoch-millis `Long`s; parsing date/time *text* is `kotlinx-datetime`'s job (`LocalDate.parse`, etc.).
