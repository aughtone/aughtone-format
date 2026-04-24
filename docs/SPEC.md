# Functional Specifications

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
- **API**: `Instant.readableRelative(now, relativeDateStyle, relativeTimeStyle, dateStyle, timeStyle, relativeThreshold, nowThreshold, locale, timeZone)`
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

#### 2. Era Overrides
Allows users to inject custom labels for Eras (BCE/CE), useful for historical research or specific regional naming conventions.
- **API**: `eraNames` parameter in `format()` extensions.

#### 3. Numbering Systems
Supports replacing Latin digits with localized digit sets (e.g., Arabic-Indic, Devanagari, Thai).
- **API**: `numberingSystem` parameter in `format()` extensions.
- **Scope**: Applied to all numeric components of the formatted string.

### Localization
- **Language Support**: Consistent **65+ language** coverage, aligned with the `:readable` module.
- **Resource Management**: Uses a thread-safe, bounded caching system for BCP 47 resource lookups.
- **CLDR Compatibility**: Automatically sanitizes patterns to remove unsupported tokens while maintaining platform consistency.
