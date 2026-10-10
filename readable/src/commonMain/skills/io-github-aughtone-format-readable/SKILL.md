---
name: io-github-aughtone-format-readable
description: >-
  The human-readable-text member of the aughtone-format family, for Kotlin
  Multiplatform. Show how long ago something happened or how soon it will
  ("8 minutes ago", "in 3 days", "yesterday", "tomorrow", time ago); a duration
  in words ("2 hours", "1 week", "5m"); file sizes and byte counts (KB/MB/GB,
  written as binary KiB/MiB/GiB); distances, speeds and other metric units with
  SI prefixes ("1.5 km", "500 W"); numbers with thousands separators, or
  abbreviated as compact numbers ("1.5k", "2.5G"); ordinals ("1st", "2nd"); money
  and currency amounts ("$1,234.56", "1 234,56 €"); latitude/longitude as decimal
  degrees or DMS; a compass bearing ("225° (SW)"); a time zone's name; and join a
  list with commas and "and" or "or" ("Mon, Wed and Fri"). Localized with plural
  rules. Output is rounded prose, never a value to parse back. For styled absolute
  dates ("Jan 12, 1952") use io.github.aughtone:format-datetime; for exact
  identifier notation (masked cards, IBAN, IP) use
  io.github.aughtone:format-identifiers.
license: Apache-2.0
metadata:
  version: "4.1.1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-readable

## What it solves

Turning a raw value into the short, localized phrase a person reads:

- Relative time — "8 minutes ago", "Tomorrow", "in 3 days" — from an `Instant`, `LocalDateTime`, `LocalDate`, `LocalTime`, or `Duration`.
- Scaled units — "1.5 km", "500 m", data sizes "1 KiB"/"1 MiB", SI metric quantities.
- Numbers — grouped ("1,234.6"), abbreviated ("1.5k", "2.5G"), and ordinals ("1st", "1er").
- Money — "$1,234.56", "1 234,56 €", "-$12.00".
- Geo — coordinates as decimal degrees or DMS, azimuths "225° (SW)", altitudes.
- Time zones — "Coordinated Universal Time".
- Lists — locale-correct joining: "Monday, Wednesday, and Friday" / "lundi, mercredi et vendredi".

Reach for it instead of assembling these strings by hand — the plural rules, unit thresholds, and locale grouping are the whole point.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts (common, JVM, Android, and iOS via Kotlin); there is no separate Swift or JavaScript API.

## How it is meant to be used

Almost everything is an extension named `formatReadable…`, and nearly every formatter takes `locale: Locale = Locale.current` (that is `io.github.aughtone.types.locale.Locale` — *not* `java.util.Locale`). Predefined locales live in `Locales` (e.g. `Locales.English`, `Locales.French`).

**Each family lives in its own sub-package, and a star import of `io.github.aughtone.readable.*` reaches none of them** — it brings in only `Locales`, `PluralCategory`, `pluralCategoryFor` and `ordinalCategoryFor`. Import each formatter from its package: `duration`, `financial`, `geo`, `list`, `metrics`, `number`, `ordinality`, `quantitative`, `relative`, `time`. Symptom: `Unresolved reference: formatReadable…` although the dependency is present.

```kotlin
import io.github.aughtone.readable.Locales
import io.github.aughtone.readable.duration.formatReadable
import io.github.aughtone.readable.financial.formatReadable
import io.github.aughtone.readable.list.formatReadableList
import io.github.aughtone.readable.metrics.formatReadable
import io.github.aughtone.readable.metrics.formatReadableDataSize
import io.github.aughtone.readable.number.formatReadable
import io.github.aughtone.readable.number.formatReadableAbbreviated
import io.github.aughtone.readable.ordinality.formatReadableOrdinal
import io.github.aughtone.readable.relative.formatReadableRelative
import io.github.aughtone.types.financial.Money
import io.github.aughtone.types.quantitative.Distance
import io.github.aughtone.types.units.UnitOfMeasure

7.days.formatReadable(locale = Locales.English)                 // "1 week"
1500.0.formatReadableAbbreviated(Locales.English)               // "1.5k"
1234.56.formatReadable(Locales.English, precision = 1)          // "1,234.6"
1048576L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English)  // "1 MiB"
Distance(1500.0).formatReadable(Locales.English)                // "1.5 km"
Money(123456L, usd).formatReadable(Locales.English)             // "$1,234.56"
1.formatReadableOrdinal(Locales.English)                        // "1st"

// Locale-correct list joining
listOf("Monday", "Wednesday", "Friday").formatReadableList(Locales.English)  // "Monday, Wednesday, and Friday"

// Relative time: pin `now` explicitly (see traps)
val now = Clock.System.now()
(now - 8.minutes).formatReadableRelative(now = now, locale = Locales.English)  // "8 minutes ago"
```

The `geo`/`quantitative` families split by package: `Coordinates` extensions are in `io.github.aughtone.readable.quantitative`, `GeoPoint` extensions in `io.github.aughtone.readable.geo`. **`Altitude.formatReadable` and `Azimuth.formatReadable` are declared identically in both packages**, so import them by name from one of the two and never star-import both. Symptom: `Overload resolution ambiguity` on `altitude.formatReadable()`.

**Four functions have no default locale and must be given one:** `formatDecimalDegrees(locale)` and `formatDegreesMinutesSeconds(locale)` (on both `Coordinates` and `GeoPoint`), and `pluralCategoryFor(locale, n)` / `ordinalCategoryFor(locale, n)`. Use `formatReadable(format = …)` on a coordinate to get the defaulted locale.

## Invariants and traps

**Never pass the receiver as `now` to `formatReadableRelative`.** The delta becomes zero and everything renders as the "just now" string. Symptom: everything reads "just now" / "0 seconds ago" — pass a distinct reference time (and pin it in tests for determinism).

**The default `Locale.current` makes output environment-dependent.** Grouping, symbols, and words all change with the ambient locale. Symptom: output that differs between machines or CI — pass an explicit `Locales.*` whenever the result must be reproducible.

**Trailing zeros are trimmed and rounding is lossy — do not promise a fixed decimal count.** `precision` is a maximum: `1024L.formatReadableDataSize(...)` is `"1 KiB"`, not `"1.0 KiB"`; `Distance(500.0)` is `"500 m"`. Money is the one exception and always shows its currency's full minor-unit digits (`"$12.00"`). Units round to the nearest step (`29.days` → "29 days" but `30.days` → "1 month"; `10.days` → "1 week", `11.days` → "2 weeks"), the week unit only ever renders 1–3 before jumping to days/months, and a value that rounds up to a whole larger unit is shown as that unit (59.6 seconds → "1 minute", 999,999 → "1M", 1,048,575 bytes → "1 MiB"). A duration under half a second is "0 seconds". DMS truncates its seconds field (21.6″ reads 21″). Symptom: fewer decimals than you set, or a unit that "jumps" at a threshold.

**Numbers are always written out in full, and nothing here throws.** There is no exponent notation however large or small the value; `Long` and `ULong` keep every digit. Not-a-number renders as `"NaN"` and the infinities as `"∞"` / `"-∞"` (with the bare unit, and no prefix, in the metric and data-size formatters). A negative number carries a leading `-`; for money it goes before an attached symbol (`"-$123.00"`) and otherwise on the number (`"-123,00 €"`).

**Data sizes are IEC/base-1024 only.** Symbols are always `KiB`, `MiB`, …; there is no `base`/decimal (`kB`) option. Symptom: `formatReadableDataSize(base = 1000)` does not compile.

**`precision` defaults are not uniform, and integer-vs-float is not the axis.** The plain number `formatReadable` defaults `precision = 1` on `Double` but `0` on `Int`/`Long`. The metric formatters default `1` on *every* receiver — including `Int`/`Long` (`formatReadableMetric`) and `Distance`/`Speed`/`Altitude`. `Azimuth` defaults `0`. Symptom: an integer that keeps no decimals, or a metric value that keeps one you did not ask for — set it explicitly.

**Language coverage is uneven — count the output as best-effort human text.** Plural categories follow the CLDR rules for whole numbers in every bundled language, and durations ("5 dní", "2 minuti") and the "Today"/"Tomorrow"/"Yesterday" labels are in the reader's language. Relative phrases ("5 minutes ago", "in 3 days") are grammatical for English, German, the Romance languages, Russian, Ukrainian, Belarusian, Polish and the Nordic languages, but many others — Czech, Slovak, Slovenian, Croatian, Lithuanian, Latvian, Finnish, Estonian, Hungarian, Greek among them — put the unit in the wrong grammatical case after "ago"/"in". The "Recently"/"Shortly" labels are English for most languages. A locale outside the bundled set falls back to English rather than throwing. Symptom: an ungrammatical unit, or English words in an otherwise localized string.

**`RelativeStyle.Short` is English-only, and drops "ago".** In English, five days in the past is `"5d"`, not `"5d ago"`, and in the future `"in 5d"`. Every other language has no abbreviated unit words: `Short` gives the same text as `Long` (`"5 Minuten"`, `"vor 5 Minuten"`). `RelativeStyle.None` suppresses that component entirely. Symptom: a bare `"5d"` with no "ago", or a `Short` request that comes back at full length. The bare `LocalTime.formatReadableRelative` handles time units only and defaults `relativeThreshold = 3.hours` — for calendar-aware output ("Tomorrow") use the `Instant`-anchored overload.

## What moved, and what it used to be called

**Output corrected in 4.1.1 — code that worked around the old output will now be wrong.** These all changed in 4.1.1:

- Negative numbers: `-123` used to format as `"-,123"`; it is now `"-123"`.
- Large and small numbers: from 10,000,000 up (and below 0.001) the JVM, Android and iOS builds emitted fragments of exponent notation (`"1"`, `"5.0E-4"`, money `"$1.0E"`). Every digit is now written out.
- Money: `"$12.5"` and `"$12"` are now `"$12.50"` and `"$12.00"`; a negative amount is `"-$123.00"`, not `"$-,123"`.
- `Double.NaN` used to throw `IllegalArgumentException`; it now renders `"NaN"`.
- Unit boundaries: `"60 seconds"`, `"1,000k"` and `"1,024 KiB"` are now `"1 minute"`, `"1M"` and `"1 MiB"`.
- Durations in Czech, Slovak, Croatian, Serbian, Slovenian, Lithuanian and Latvian always used the singular ("5 den"); Dutch, Afrikaans, Indonesian, Malay and Swahili used English words. Both are fixed.
- "Today"/"Tomorrow"/"Yesterday" were English for most languages and are now localized.
- `RelativeStyle.Short` durations outside English lost their space (`"5Minuten"`); they now read `"5 Minuten"`.
- Decimal and grouping separators were wrong for about fifteen languages — Ukrainian, Bulgarian, Hungarian and others now group with a space, and Indonesian and Vietnamese no longer swap the decimal point and the comma. Spanish in the Americas follows its own region (`1,234.56` in Mexico).
- Grouping follows the language: Spanish, Italian, Polish, Portuguese (Portugal), Hungarian and others leave a four-digit number whole (`1234,5`, grouping from `12.345`), and Hindi and Indian English group in lakhs (`12,34,567`).
- Lists in English outside the United States have no comma before "and" (`A, B and C`).
- A list element containing the text `{1}` was substituted into; elements are now emitted unchanged.

**Silent change in 4.1.0: `TimeZone.formatReadable` wording.** The `Instant` overload now takes its name from the platform's own CLDR, so the wording can differ from 4.0.0 and between targets (UTC is "Coordinated Universal Time" on most, "Greenwich Mean Time" on Apple). The bare-`UtcOffset` overload cannot consult the platform, so it now returns the English name (or Inuktitut) — it no longer localizes into other languages. Pass an `Instant` to get the reader's language.

**New in 4.1.0: `List<String>.formatReadableList(locale, type)`** — locale-correct list joining ("Monday, Wednesday, and Friday" / "lundi, mercredi et vendredi"), from CLDR list patterns, types `And`/`Or`/`Unit`. Covers ~55 bundled locales (English fallback beyond); wide forms only for now (no short/narrow).

**`toReadable*` → `formatReadable*` (renamed in 3.0.0).** This is the single most likely stale call. Every human-formatting entry point was `toReadable…` and is now `formatReadable…`. The old names still exist as `@Deprecated` wrappers with `ReplaceWith`, so old code compiles with a warning — but write the new name:

- `toReadableString` / `toReadable` → `formatReadable`
- `toReadableRelative` → `formatReadableRelative`
- `toReadableOrdinal` → `formatReadableOrdinal`
- `toReadableDataSize` → `formatReadableDataSize`
- `toReadableMetric` → `formatReadableMetric`
- `toReadableAbbreviated` → `formatReadableAbbreviated`

The legacy `toReadableRelative` overloads carry a stronger warning: they never fall back to an absolute date/time, whereas `formatReadableRelative` does. Prefer the new one.

**`CoordinateFormat` and `cardinalDirectionsFor` moved package (3.0.0):** from `io.github.aughtone.readable.geo` to `io.github.aughtone.readable.quantitative`. An old import from `…readable.geo` for those symbols no longer resolves.

**`RelativeDirection.Nearest` was added and became the `LocalTime` default in 3.1.0** (it was `Present` before), which can change which side of "now" a bare time resolves to.

**`Money`'s `Currency` is non-null (4.0.0).** A `Money` now always carries a `Currency`; code that passed or expected a null currency no longer compiles. This is what decides the fraction digits in the formatted amount.

## What it is not for

- **Not canonical or machine-parseable output.** Everything here rounds, trims, and localizes; you cannot parse it back to the original value. For exact, round-trippable identifier notation use `io.github.aughtone:format-identifiers`.
- **Not raw date/time pattern formatting.** Styled `LocalDate`/`LocalDateTime`/`Instant` formatting (Short/Medium/Long/Full) lives in `io.github.aughtone:format-datetime`; this module builds *relative* phrasing on top of it.
- **Not a full CLDR/ICU localization engine.** The plural, ordinal, and unit rules are simplified and English-backstopped; do not rely on it for exhaustive per-language correctness.
