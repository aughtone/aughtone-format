# Aughtone Format: Readable

The `:readable` module is the human-centric part of the Aughtone ecosystem. It turns raw data — durations, numbers, sizes, money, and coordinates — into short, localized strings for people to read.

Published as `io.github.aughtone:format-readable`. Its output is lossy, rounded, localized prose; it is never meant to be parsed back to the original value. For exact, round-trippable identifier notation use `:identifiers`; for styled absolute date/time use `:datetime`.

## Conventions

Every entry point is an extension named `formatReadable…`, and **every formatter takes `locale: Locale = Locale.current`** — where `Locale` is `io.github.aughtone.types.locale.Locale` (from `aughtone-types`), *not* `java.util.Locale`. Predefined locales live in the `Locales` object (e.g. `Locales.English`, `Locales.French`). Because the default is the ambient locale, pass an explicit `Locales.*` whenever output must be reproducible.

The older `toReadable*` names are deprecated — use `formatReadable*`.

## 🚀 Key features

### ⏱️ Durations and relative time

#### Relative time ("time ago")

Formats an `Instant`, `LocalDateTime`, `LocalDate`, or `LocalTime` into natural language. Pass the reference time explicitly as `now` — never pass the receiver itself, or the delta is zero and everything renders as the "just now" string.

```kotlin
val now = Clock.System.now()

// Long style (default)
(now - 8.minutes).formatReadableRelative(now = now, locale = Locales.English)  // "8 minutes ago"

// Short style drops the affix, and needs a threshold wide enough to stay relative
(now - 5.days).formatReadableRelative(
    now = now,
    relativeDateStyle = RelativeStyle.Short,
    relativeThreshold = 10.days,
    locale = Locales.English,
)  // "5d"   (not "5d ago")

// Calendar phrasing from the date overload
LocalDate(2023, 10, 28).formatReadableRelative(
    now = LocalDate(2023, 10, 27), locale = Locales.English,
)  // "Tomorrow"
```

Beyond `relativeThreshold` the output falls back to an absolute date/time (rendered via `:datetime`). The bare `LocalTime.formatReadableRelative` handles time units only and defaults `relativeThreshold = 3.hours`; for calendar-aware output use the `Instant`-anchored overload.

#### Durations

Translates `kotlin.time.Duration` into scannable language, scaling and pluralizing as it goes. Note the first positional argument is the style — pass the locale by name.

```kotlin
5.seconds.formatReadable(locale = Locales.English)   // "5 seconds"
7.days.formatReadable(locale = Locales.English)      // "1 week"
1.minutes.formatReadable(locale = Locales.English)   // "1 minute"
2.minutes.formatReadable(locale = Locales.English)   // "2 minutes"
```

Values round to the nearest unit and trailing zeros are trimmed, so results are approximate, not fixed-precision (`30.days` → "1 month"; the week unit only ever renders 1–3 before it jumps to days or months).

### 🔢 Numbers and ordinality

```kotlin
1234.56.formatReadable(Locales.English, precision = 1)   // "1,234.6"
1500.0.formatReadableAbbreviated(Locales.English)        // "1.5k"
2500000000.0.formatReadableAbbreviated(Locales.English)  // "2.5G"

1.formatReadableOrdinal(Locales.English)                 // "1st"
1.formatReadableOrdinal(Locales.French)                  // "1er"
```

`precision` defaults are not uniform: floating-point formatters default `1`, integer receivers default `0`.

### 💾 Data sizes

Data sizes use **IEC binary (base-1024)** units only — `KiB`, `MiB`, … There is no decimal/SI (`kB`) option.

```kotlin
1024L.formatReadableDataSize(locale = Locales.English)     // "1 KiB"
1048576L.formatReadableDataSize(locale = Locales.English)  // "1 MiB"
```

### 📍 Geospatial formatting

Coordinates (decimal degrees or degrees-minutes-seconds), altitudes, azimuths, and localized compass labels.

```kotlin
Coordinates(40.7128, -74.0060).formatReadable(
    CoordinateFormat.DegreesMinutesSeconds, Locales.English,
)  // "40° 42' 46\" N, 74° 0' 21\" W"

Azimuth(225.0).formatReadable(Locales.English)   // "225° (SW)"

cardinalDirectionsFor(Locales.English)           // the 8-point compass labels, localized
```

`Coordinates`, `Altitude`, and `Azimuth` extensions are in the `io.github.aughtone.readable.quantitative` package; the `GeoPoint` extensions are in `io.github.aughtone.readable.geo`. DMS truncates its seconds field, so it can read up to about 1″ low.

## 🧠 Plural handling

Most naive formatting handles only "singular vs. plural", which is wrong for many languages. `:readable` applies **simplified Unicode CLDR plural categories** — `Zero`, `One`, `Two`, `Few`, `Many`, `Other` — covering the core languages, and falls back to English for locales it does not have specific rules for. It is best-effort human text, not a guaranteed-correct localization for every language.

| Category | Typical usage | Example (Russian) |
| :--- | :--- | :--- |
| `Zero` | Special zero case | _(depends on language)_ |
| `One` | Singular | 1 минута |
| `Two` | Dual (e.g. Arabic/Hebrew) | _(special case)_ |
| `Few` | Small plural groups | 2 минуты |
| `Many` | Large plural groups | 5 минут |
| `Other` | Catch-all | _(fallback)_ |

## Stateless and concurrency-safe

Formatting is built on a functional-factory pattern: factory helpers (`numberFormatterFor`, `durationFormatterFor`, `moneyFormatterFor`, `ordinalityFor`, `relativeTimeConfigFor`) pre-build immutable formatting lambdas, backed by thread-safe caches — safe to reuse across concurrent UDF or Redux-style architectures.
