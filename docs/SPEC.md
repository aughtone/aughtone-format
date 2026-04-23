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
Formatting an `Instant` relative to a reference point (default `Clock.System.now()`) into natural language.
- **API**: `Instant.toReadableRelativeTime(locale, now, nowThreshold)`
- **Direction**: Negative delta = past (`"8 minutes ago"`), positive delta = future (`"in 8 minutes"`).
- **Threshold**: Instants within `nowThreshold` (default `5.seconds`) produce a locale-specific "just now" string.
- **Configurable Reference**: `now` parameter is overridable for deterministic testing or custom reference times.

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
- **Azimuth**: Numeric degrees followed by localized cardinal directions in parentheses, e.g., `90° (E)`, `225° (SW)`.
- **Coordinates**:
    - **Decimal Degrees (DD)**: `40.71° N, 74.01° W`
    - **Degrees-Minutes-Seconds (DMS)**: `40° 42' 46" N, 74° 0' 21" W`

### Localization
- **Architecture**: Leverages the `io.github.aughtone.types.locale.Locale` system for language-specific rules.
- **Functional Parity**: All core formatters (Ordinal, Number, Duration, Relative Time) support a common set of **51 languages**.
- **BCP 47 Subtag Fallback**: All formatters walk the full subtag chain before falling back to the base language code, then to English. e.g., `fr-CA` → `fr` → `en`.
- **Patterns**:
    - **Separators**: Automatically applied based on locale (e.g., `,` vs `.` vs ` ` for grouping).
    - **Pluralization**: Handled via localized unit resource maps (singular/plural).

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
