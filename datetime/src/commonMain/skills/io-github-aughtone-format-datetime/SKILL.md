---
name: io-github-aughtone-format-datetime
description: >-
  Format a date or time for display in Kotlin Multiplatform common code,
  locale-aware and without java.time: a LocalDate, LocalTime, LocalDateTime, or
  Instant by style (Short / Medium / Long / Full), plus nullable date ranges
  from a Pair. Also gives the locale's weekday and month names on their own
  (DayOfWeek/Month.displayName — "Monday"/"Mon", "March"/"Mar"), and its
  ordering and 12/24-hour convention. For relative phrasing ("3 days ago",
  "Tomorrow") use :readable instead.
license: Apache-2.0
metadata:
  version: "4.1.0-alpha1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-datetime

## What it solves

Formatting a date or time for display, by *style* rather than by pattern string, in Kotlin Multiplatform common code — the locale-aware rendering that `java.time.format`/`DateFormat` give you on the JVM but that common Kotlin lacks. You hand it a value and a `DateTimeStyle`; it produces the localized string, with the locale's own month/day names, ordering, 12/24-hour convention, and (where the locale calls for it) digit system.

It formats `kotlinx.datetime` `LocalDate`/`LocalTime`/`LocalDateTime`, `kotlin.time.Instant`, and nullable `Pair` ranges, and offers `Long`-epoch-millis conversions into those types.

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

// Standalone localized weekday / month names (new in 4.1.0)
DayOfWeek.MONDAY.displayName(locale = localeFor("fr")!!)            // "lundi"
Month.MARCH.displayName(TextWidth.Abbreviated, localeFor("en")!!)   // "Mar"
```

`Locale` is `io.github.aughtone.types.locale.Locale` from aughtone-types; build one with `localeFor("en-CA")` (nullable — needs `!!`) or the `Locale(...)` constructor. All of these come in transitively — see below.

## Invariants and traps

**`Pair.format` on two nulls returns `"? - ?"`, not `""`.** The KDoc claims an empty string for the all-null case; the code always emits `"<first> - <second>"`, so both-null gives `"? - ?"` (or `"<placeholder> - <placeholder>"`). Do not rely on the documented empty-string behaviour.

**`Instant.format()` with no arguments appends a time-zone token.** Its `timeStyle` **defaults to `Long`**, and `Long`/`Full` time styles append a zone abbreviation or full name ("4:08:39 p.m. UTC"). Pass `timeStyle = DateTimeStyle.Short` (or `Medium`) if you do not want it. Likewise `DateTimeStyle.Full` on a date appends an era word ("… 1952 Common Era") — override the wording with `eraNames = EraNames(bce = …, ce = …)`, which is consulted only at `Full`.

**Locales silently switch digit systems.** Even with `numberingSystem = null`, Arabic (`ar`), Persian/Urdu (`fa`/`ur`), Hindi (`hi`), Bengali (`bn`), and Thai (`th`) render localized digits. Force Western digits with `numberingSystem = NumberingSystem.LATN` when you need them.

**`is24HourFormat` detection differs by platform — pass it explicitly for reproducible output.** The default probes the platform: it is a real system setting on Android (needs the app `Context`, wired via `androidx.startup`) and locale-derived on iOS/JS, but on JVM and wasmJs there is no detection at all — it falls back to a locale-based guess (12-hour when the locale is unknown). The same locale can therefore render 12- vs 24-hour differently across targets. Supply `is24HourFormat = true/false` when determinism matters.

**These `Instant`s are `kotlin.time.Instant`, not `kotlinx.datetime.Instant`.** The `Instant`/`Pair<Instant?, Instant?>` APIs use the stdlib `kotlin.time.Instant` (experimental time API); callers opt in to `kotlin.time.ExperimentalTime`. A value of `kotlinx.datetime.Instant` will not fit these signatures.

**Nothing throws for a caller.** A formatter that cannot produce a string falls back to the value's `toString()` (raw ISO), and `None`/`None` yields `""`. There are no `@Throws`, and no deprecated symbols, in this module.

## What moved, and what it used to be called

**New in 4.1.0: standalone names.** `DayOfWeek.displayName(width, locale)` and `Month.displayName(width, locale)` expose the locale's day and month names — `Full`/`Abbreviated` via the `TextWidth` enum — that were previously reachable only inside a formatted date. There is no `Narrow` width and no separate stand-alone-vs-in-context form yet; unsupported locales fall back to English. `TimeZone.displayName(instant, width, locale)` gives the zone's localized *specific* name at that instant ("Eastern Standard Time" / "EST"); it is instant-keyed because standard vs. daylight depends on the moment, and an unbundled zone falls back to its UTC offset string. The instant-independent *generic* name ("Eastern Time") is a separate CLDR metazone dataset and is not bundled.

**The iOS framework was renamed in 4.0.0: `AughtoneFormatDatetimeKit` → `AOFormatDatetimeKit`.** A Swift/CocoaPods consumer importing the old framework name must update the import.

**`Instant` migrated from `kotlinx.datetime.Instant` to `kotlin.time.Instant`.** This landed before 4.0.0 and is the stable state, but an agent that "remembers" the older API will reach for `kotlinx.datetime.Instant`; the `format` extensions and `Long.toInstant()` are on `kotlin.time.Instant` now.

Source files were renamed to a `Type.extensions.kt` convention (e.g. `InstantExt.kt` → `Instant.extensions.kt`), but the extension *function* names and packages are unchanged, so this is not consumer-visible. No public API is deprecated.

This module re-exports (via `api`) `kotlinx-datetime`, `kotlinx-serialization-json`, `aughtone-types 4.0.0` (the source of `Locale`/`localeFor`), and `:toolbox`.

## What it is not for

- **Not relative or humanized time.** "3 days ago", "Tomorrow", "in 5 minutes" belong to `:readable` (which builds on this module). This module renders absolute, styled date/time.
- **Not custom pattern strings.** Formatting is by `DateTimeStyle` (`Short`, `Medium`, `Long`, `Full`, `None`) against the locale's own patterns; there is no public API to pass an arbitrary skeleton or `pattern` of your own.
- **Not a parser.** It formats values and converts epoch-millis `Long`s; parsing date/time *text* is `kotlinx-datetime`'s job (`LocalDate.parse`, etc.).
