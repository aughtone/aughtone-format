---
name: io-github-aughtone-format-readable
description: >-
  Format "3 days ago", "1.5 km", "$1,234.56", "1st", "1.5k", "8 minutes ago" —
  human-facing, localized formatting of relative times, durations, numbers
  (grouped and abbreviated), ordinals, money, data sizes, coordinates, and time
  zones. Reach for it instead of hand-rolling relative-time phrasing, unit
  scaling, ordinals, or locale number/money formatting. Output is lossy,
  rounded, localized prose — never a value to parse back (that is :identifiers).
license: Apache-2.0
metadata:
  version: "4.1.0-alpha1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-readable

## What it solves

Turning a raw value into the short, localized phrase a person reads:

- Relative time — "8 minutes ago", "Tomorrow", "in 3 days" — from an `Instant`, `LocalDateTime`, `LocalDate`, `LocalTime`, or `Duration`.
- Scaled units — "1.5 km", "500 m", data sizes "1 KiB"/"1 MiB", SI metric quantities.
- Numbers — grouped ("1,234.6"), abbreviated ("1.5k", "2.5G"), and ordinals ("1st", "1er").
- Money — "$1,234.56", "1 234,56 €".
- Geo — coordinates as decimal degrees or DMS, azimuths "225° (SW)", altitudes.
- Time zones — "Coordinated Universal Time".

Reach for it instead of assembling these strings by hand — the plural rules, unit thresholds, and locale grouping are the whole point.

## How it is meant to be used

Almost everything is an extension named `formatReadable…`, and **every formatter takes `locale: Locale = Locale.current`** (that is `io.github.aughtone.types.locale.Locale` — *not* `java.util.Locale`). Predefined locales live in `Locales` (e.g. `Locales.English`, `Locales.French`).

```kotlin
import io.github.aughtone.readable.*
import io.github.aughtone.readable.relative.formatReadableRelative
import io.github.aughtone.readable.number.formatReadableAbbreviated

7.days.formatReadable(locale = Locales.English)                 // "1 week"
1500.0.formatReadableAbbreviated(Locales.English)               // "1.5k"
1234.56.formatReadable(Locales.English, precision = 1)          // "1,234.6"
1048576L.formatReadableDataSize(UnitOfMeasure.Byte, Locales.English)  // "1 MiB"
Distance(1500.0).formatReadable(Locales.English)                // "1.5 km"
Money(123456L, usd).formatReadable(Locales.English)             // "$1,234.56"
1.formatReadableOrdinal(Locales.English)                        // "1st"

// Relative time: pin `now` explicitly (see traps)
val now = Clock.System.now()
(now - 8.minutes).formatReadableRelative(now = now, locale = Locales.English)  // "8 minutes ago"
```

The `geo`/`quantitative` families split by package: `Coordinates`/`Altitude`/`Azimuth` are in `io.github.aughtone.readable.quantitative`, `GeoPoint` extensions in `io.github.aughtone.readable.geo` — import from the right one.

## Invariants and traps

**Never pass the receiver as `now` to `formatReadableRelative`.** The delta becomes zero and everything renders as the "just now" string. Pass a distinct reference time (and pin it in tests for determinism).

**The default `Locale.current` makes output environment-dependent.** Grouping, symbols, and words all change with the ambient locale. Pass an explicit `Locales.*` whenever the result must be reproducible.

**Trailing zeros are trimmed and rounding is lossy — do not promise a fixed decimal count.** `1024L.formatReadableDataSize(...)` is `"1 KiB"`, not `"1.0 KiB"`; `Distance(500.0)` is `"500 m"`. Units round to the nearest step (`29.days` → "29 days" but `30.days` → "1 month"; `10.days` → "1 week", `11.days` → "2 weeks"), and the week unit only ever renders 1–3 before jumping to days/months. DMS truncates its seconds field (can read ~1″ low).

**Data sizes are IEC/base-1024 only.** Symbols are always `KiB`, `MiB`, …; there is no `base`/decimal (`kB`) option. `formatReadableDataSize(base = 1000)` does not compile.

**`precision` defaults are not uniform.** Floating-point number/metric formatters default `precision = 1`; integer receivers default `precision = 0`; `Azimuth` defaults `0` while `Altitude`/`Distance`/`Speed` default `1`. Set it explicitly when it matters.

**Plural and ordinal rules are simplified CLDR, and unknown locales fall back to English.** They cover the core languages; less-common locales silently degrade to English rather than throwing (there are no exceptions in this module at all). Treat the output as best-effort human text, not a guaranteed-correct localization for every language.

**`RelativeStyle.Short` drops the affix.** In English, `5.days` short is `"5d"`, not `"5d ago"`; `RelativeStyle.None` suppresses that component entirely. The bare `LocalTime.formatReadableRelative` handles time units only and defaults `relativeThreshold = 3.hours` — for calendar-aware output ("Tomorrow") use the `Instant`-anchored overload.

## What moved, and what it used to be called

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

**aughtone-types 4.0.0 is re-exported** (via `api`). Its 4.0.0 changes reach consumers of this module — notably `Currency` is non-null in `Money`. This module also re-exports `kotlinx-datetime`, `kotlinx-serialization-json`, `:datetime`, and `:toolbox`.

## What it is not for

- **Not canonical or machine-parseable output.** Everything here rounds, trims, and localizes; you cannot parse it back to the original value. For exact, round-trippable identifier notation use `:identifiers`.
- **Not raw date/time pattern formatting.** Styled `LocalDate`/`LocalDateTime`/`Instant` formatting (Short/Medium/Long/Full) lives in `:datetime`; this module builds *relative* phrasing on top of it.
- **Not a full CLDR/ICU localization engine.** The plural, ordinal, and unit rules are simplified and English-backstopped; do not rely on it for exhaustive per-language correctness.
