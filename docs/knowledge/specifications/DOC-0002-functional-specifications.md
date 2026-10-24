# Functional Specifications

DOC-0002 · 2026-04-19
Keywords: what the library does, which modules format what, supported features, localization
          behaviour, regional variants, eras, numbering systems, default digits

## 🌍 Domain Context

The Aughtone Format library provides a standardized, type-safe foundation for multiplatform applications to format data into human-readable strings.

### Public Identity
The library is distributed globally via Maven Central under the following coordinates:
- **Group ID**: `io.github.aughtone`
- **Artifact ID**: `format-*` (e.g., `format-datetime`, `format-readable`)

---

## 📖 Human Readable Module (`:readable`)

The goal of this module is to provide localized, human-friendly formatting for various data types, replacing external dependencies with a custom, highly-extensible implementation.

### Features

#### 1. Numbers & Metrics
Formatting values into abbreviated forms for scannability.
- **Example**: `1,500` → `1.5K`, `1,200,000` → `1.2M`
- **Supported Units**: K (Thousand), M (Million), B (Billion), T (Trillion).

#### 2. Ordinality
Converting numbers to their ordinal forms based on language rules.
- **Example**: `1` → `1st`, `2` → `2nd`, `3` → `3rd`, `10` → `10th`

#### 3. Data Sizes
Formatting byte counts into human-readable units.
- **Example**: `1024` → `1 KiB`, `1048576` → `1 MiB`
- **Standard**: Follows IEC (binary) or SI (decimal) conventions as requested.

#### 4. Relative Time
Formatting temporal types relative to a reference point (default `Clock.System.now()`) into natural language with style control and automatic fallback.
- **API**: `Instant.formatReadableRelative(now, relativeDateStyle, relativeTimeStyle, dateStyle, timeStyle, relativeThreshold, nowThreshold, locale, timeZone)`
- **Extensions**: Supported for `Instant`, `LocalDateTime`, `LocalDate`, and `LocalTime`.
- **Direction**: Negative delta = past (`"8 minutes ago"`), positive delta = future (`"in 8 minutes"`).
- **Styles**: Supports `Long` ("5 days ago"), `Short` ("5d ago"), and `None` (component suppression).
- **Fallback**: If the absolute difference exceeds `relativeThreshold`, the formatter falls back to a standard date/time format using `dateStyle` and `timeStyle`.
- **Day Strings**: Dates within +/- 1 day are formatted using special-case strings: `"Today"`, `"Tomorrow"`, and `"Yesterday"`.
- **Recently**: `LocalDate` supports a localized "Recently" string for dates within its `nowThreshold` (default `1.day`), fully hardened across all 65+ supported languages.
- **Threshold**: Values within `nowThreshold` (default `1.minute` for time-based types) produce a locale-specific "just now" string.
- **Optimizations**: `LocalDate` and `LocalTime` extensions use type-specific `now` parameters.

#### 5. Durations
Translating `kotlin.time.Duration` into natural language using smart-scaling.
- **Scaling Logic**: Switches to the largest unit where the rounded value is $\ge 1$.
- **Rounding Exceptions**:
    - Weeks: Used for durations $\ge 7$ days but $\le 3$ weeks.
    - Transition: 29 days remains as "29 days" to avoid rounding to "4 weeks" before hitting "1 month" (30 days).
- **Precision**: Defaults to integer rounding for readable strings.

#### 6. Geospatial Data
Human-readable representation of physical coordinates and orientations.
- **Altitude**: Automatic scaling between meters (m) and kilometers (km) with localized separators.
- **Azimuth**: Numeric degrees followed by localized cardinal directions in parentheses, e.g., `90° (E)`, `225° (SW)`. Directions (N, S, E, W, etc.) are localized across 65+ languages.
- **Coordinates**:
    - **Decimal Degrees (DD)**: `40.71° N, 74.01° W`
    - **Degrees-Minutes-Seconds (DMS)**: `40° 42' 46" N, 74° 0' 21" W`

#### 7. Grammatical Parity & Plural Categorization
All human-readable formatting adheres to the **Unicode CLDR-compliant Plural Category System** (Zero, One, Two, Few, Many, Other).
- **Linguistic Coverage**: Supports up to 6 grammatical forms (Arabic) to ensure natural sounding output in all 65+ languages.
- **Ordinal Categories**: Explicit support for language-specific ordinal categorization logic (e.g., English *st/nd/rd*, French *er/e*, Swedish *a/e*).

#### 8. Time Zones
Formatting time zones into human-readable abbreviations or full names using `TimeZoneAbbreviationLookup`.
- **API**: `TimeZone.formatReadable(instant, useFullName, locale)` and `TimeZone.formatReadable(offset, useFullName, locale)`
- **DST Resolution**: Time zones require an `Instant` or `UtcOffset` to resolve DST-specific variations (e.g., `EST` vs `EDT` for Eastern Time).

### Localization
- **Architecture**: Leverages the `io.github.aughtone.types.locale.Locale` system for language-specific rules.
- **Functional Parity**: All core formatters (Ordinal, Number, Duration, Relative Time) support a common set of **65+ languages**.
- **BCP 47 Subtag Fallback**: All formatters walk the full subtag chain before falling back to the base language code, then to English. e.g., `fr-CA` → `fr` → `en`.
- **Patterns**:
    - **Separators**: Automatically applied based on locale (e.g., `,` vs `.` vs ` ` for grouping).
    - **Grammatical Numbers**: Handled via localized plural category factories (`u2`, `u3`, `u4`, `u6`) to ensure correct linguistic form for all counts.

### Regional Variants
Some locales require overrides that differ from their base language. Only documented, well-established differences are implemented.

#### Number Formatting (`Number.resources.kt`)

| Tag | Region | Decimal | Grouping | Example |
|---|---|---|---|---|
| `en-ZA` | South Africa | `,` | ` ` (space) | `1 234,56` |
| `fr-CH` | Swiss French | `,` | `'` (apostrophe) | `1'234,56` |
| `de-CH` | Swiss German | `.` | `'` (apostrophe) | `1'234.56` |
| `it-CH` | Swiss Italian | `.` | `'` (apostrophe) | `1'234.56` |

#### Duration Formatting (`Duration.resources.kt`)

| Tag | Region | Difference |
|---|---|---|
| `zh-TW` | Traditional Chinese (Taiwan) | Uses Traditional characters: `小時` (hour), `週` (week) instead of Simplified `小时`, `周` |

#### Relative Time (`RelativeTime.resources.kt`)

| Tag | Region | Difference |
|---|---|---|
| `en-ZA` | South African English | Uses `"now now"` as the threshold phrase instead of `"just now"`, which means "sometime later" in SA English |

---

## 📅 Datetime Module (`:datetime`)

The goal of this module is to provide localized date and time formatting using `kotlinx-datetime` primitives, optimized for Kotlin Multiplatform.

### Features

#### 1. Core Formatting
Provides standard styles for `LocalDate`, `LocalTime`, `LocalDateTime`, and `Instant`.
- **Styles**: `Short`, `Medium`, `Long`, `Full`.
- **24-Hour Master Strategy**: All internal resource patterns are stored in 24-hour (`HH:mm`) format. The formatting logic dynamically converts these to 12-hour format at runtime based on the locale's default or explicit user preference, ensuring consistent localization of AM/PM markers.

#### 2. Eras
No style shows the era unless asked: `showEra = true` follows the date with the locale's abbreviation (BCE/CE), and `eraNames` supplies other words, which also shows it. A date before year 1 always shows its era, with the year counted from one.
- **API**: `showEra` and `eraNames` parameters in the date `format()` extensions.

#### 3. Numbering Systems
Supports replacing Latin digits with localized digit sets (e.g., Arabic-Indic, Devanagari, Thai).
- **API**: `numberingSystem` parameter in `format()` extensions.
- **Scope**: Applied to all numeric components of the formatted string.

#### 4. Time-Zone Names
A zone's name at a moment ("Eastern Daylight Time"), the generic name it keeps all year ("Eastern Time" / "ET"), and translation between Windows zone names and IANA identifiers.
- **API**: `TimeZone.displayName`, `TimeZone.genericDisplayName`, `TimeZone.ianaIdForWindowsName` and `TimeZone.windowsNameForIanaId`.
- **Sources**: Zone names come from the platform's CLDR; the Windows mapping is bundled from CLDR's `windowsZones`, so it is the same on every platform.

### Localization
- **Language Support**: Consistent **65+ language** coverage, aligned with the `:readable` module.
- **Resource Management**: Uses a thread-safe, bounded caching system for BCP 47 resource lookups.
- **CLDR Compatibility**: Automatically sanitizes patterns to remove unsupported tokens while maintaining platform consistency.

---

## 🌅 Astronomy Module (`:astronomy`)

The goal of this module is to answer when the sun and the moon do things at a place on a day, in common code, so every platform gives the same answer. See ADR-0006.

### Features
- **Sun times**: sunrise, sunset and civil, nautical and astronomical dawn and dusk for a latitude, longitude and date, in the place's own day (`SunTimes.of`) or a UTC day (`SunTimes.ofUtcDay`). Polar day and polar night are reported as such, never as a guessed time.
- **Moon**: new and full moons in a range, and the moon's phase at an instant.
- **Solar terms**: the 24 terms of a year, which the Chinese and Korean calendars reckon by.
- **Crescent visibility**: whether the young crescent can be seen on an evening, by the Odeh criterion (default) or Yallop's.
- **Accuracy**: sun times within a minute of NOAA's tables up to 60° of latitude; moon phases and solar terms within two minutes of NASA's and the Hong Kong Observatory's tables.

---

## 🗓️ Calendars Module (`:calendars`)

The goal of this module is conversion and arithmetic for every calendar iCalendar's `RSCALE` (RFC 7529) names, identical on every platform. It depends on `:astronomy`. See ADR-0006 and ADR-0007.

### Features
- **Calendars**: Buddhist, Japanese, ROC, Hebrew, Islamic civil, tabular and Umm al-Qura, Persian, Coptic, Ethiopic (both eras), Indian, Chinese and Korean (Dangi).
- **API**: `CalendarSystem.calendar()` gives `fromLocalDate`, `toLocalDate`, `monthsInYear`, `daysInMonth` and `leapMonth`. A leap month is the month it follows with `isLeapMonth` set (RFC 7529's `5L`).
- **Chinese and Korean**: Chinese months follow the Hong Kong Observatory's published tables for 1901–2099 and are computed outside them; Korean months are computed at Seoul. ICU's data is not used, because it starts nine modern Chinese months on the wrong day (ADR-0007).
- **Observation-based Islamic**: `ObservedIslamicCalendar` resolves dates only from month starts the caller supplies; `predictMonthStart` returns a separate prediction type from a crescent criterion.

---

## 🛠️ Toolbox Module (`:toolbox`)

The goal of this module is to provide core utilities and mathematical primitives that support high-level formatting and transformation.

### Features

#### 1. Geospatial Projections
Converting geographic coordinates (WGS84) to 2D Cartesian planes.
- **Projections**:
    - `WebMercatorProjection`: Standard for web maps (EPSG:3857).
    - `EquirectangularProjection`: Simple linear mapping.
- **Math**: Bounding box calculation and merging for all GeoJSON types.

#### 2. Vector Generation
Generating standard path data from complex types.
- **SVG Path Data**: Extension functions to generate the `d` attribute for `GeoJson` objects.
- **Precision**: Configurable decimal precision to manage string size vs. fidelity.

---

## 🎨 Viewable Modules (`:viewable` & `:viewable-compose`)

The goal of these modules is to bridge the gap between abstract data types and visual rendering engines.

### Features

#### 1. Viewable Model (`:viewable`)
A platform-agnostic representation of visual intent.
- **`ViewablePath`**: A collection of `PathCommand` (MoveTo, LineTo, Close) and a `WindingRule` (EvenOdd, NonZero).
- **Coordinate System**: Uses projected Cartesian coordinates.
- **Geo Integration**: Direct conversion from `GeoJson` to `ViewablePath` using a specified `GeoProjection`.
- **Headless Exports**: 
    - `toImage()`: Generates platform-native bitmaps (Android `Bitmap`, JVM `BufferedImage`, iOS `UIImage`).
    - `toSvgDocument()`: Generates a complete XML SVG document string with correct `viewBox` and `fill-rule`.

#### 2. Compose Rendering (`:viewable-compose`)
Integration with the Jetpack/Compose Multiplatform ecosystem.
- **`ViewablePathPainter`**: A `Painter` that renders `ViewablePath` objects.
- **Auto-Scaling**: Automatically calculates scaling and translation to fit the path into the target viewport while maintaining aspect ratio.
- **`toComposePath()`**: Extension to convert abstract commands directly into `androidx.compose.ui.graphics.Path`.
- **`toImageVector()`**: Extension to convert `ViewablePath` into a Compose `ImageVector`, supporting viewport-aware normalization.
