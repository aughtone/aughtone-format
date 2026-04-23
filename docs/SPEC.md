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

#### 4. Relative Time & Durations
Translating durations and time differences into natural language using smart-scaling.
- **Scaling Logic**: Switches to the largest unit where the rounded value is $\ge 1$.
- **Rounding Exceptions**:
    - Weeks: Used for durations $\ge 7$ days but $\le 3$ weeks. 
    - Transition: 29 days remains as "29 days" to avoid rounding to "4 weeks" before hitting "1 month" (30 days).
- **Precision**: Defaults to integer rounding for readable strings.

#### 5. Geospatial Data
Human-readable representation of physical coordinates and orientations.
- **Altitude**: Automatic scaling between meters (m) and kilometers (km) with localized separators.
- **Azimuth**: Numeric degrees followed by localized cardinal directions in parentheses, e.g., `90° (E)`, `225° (SW)`.
- **Coordinates**:
    - **Decimal Degrees (DD)**: `40.71° N, 74.01° W`
    - **Degrees-Minutes-Seconds (DMS)**: `40° 42' 46" N, 74° 0' 21" W`

### Localization
- **Architecture**: Leverages the `io.github.aughtone.types.locale.Locale` system for language-specific rules.
- **Functional Parity**: All core formatters (Ordinal, Number, Duration) support a common set of **51 languages**.
- **Patterns**:
    - **Separators**: Automatically applied based on locale (e.g., `,` vs `.` vs ` ` for grouping).
    - **Pluralization**: Handled via localized unit resource maps (singular/plural).
