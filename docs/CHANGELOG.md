# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **Relative Time Styles**: Introduced `RelativeStyle` (`Long`, `Short`, `None`) to control the verbosity of relative time strings (e.g., "5 days ago" vs "5d ago").
- **Day-Based Phrasing**: Added support for "Today", "Tomorrow", and "Yesterday" special-case strings in 55+ languages.
- **Type-Safe Relative Formatting**: Added optimized `toReadableRelative` extensions for `LocalDate` and `LocalTime` with native type-safe `now` parameters.

### Changed
- **Relative Time API Standardization**: Renamed `toReadableRelativeTime` to `toReadableRelative` across all temporal types for consistency.
- **Threshold Optimization**: Increased the default `nowThreshold` for `Instant` and `LocalDateTime` to **1 minute** and implemented a **1-day** threshold for `LocalDate`.
- **Locale Integration**: Standardized `Locale.current` as the default for all readable formatting functions.
- **Duration Styles**: Updated `kotlin.time.Duration.toReadableString` to support `RelativeStyle`, enabling short-form durations (e.g., "5m").

### Added
- **Pluralization Engine**: Integrated a Unicode CLDR-compliant **Plural Category Engine** (`pluralCategoryFor`, `ordinalCategoryFor`) for accurate grammatical parity across 55+ languages.
- **Grammatical Factories**: Implemented multi-form factories (`u2` through `u6`) to handle varied linguistic complexity (Slavic, Arabic, Hebrew, Inuktitut).
- **GeoResources Expansion**: Full **55-language coverage** for cardinal directions with robust, recursive BCP 47 subtag fallback.

### Changed
- **Duration & Relative Time Refactor**: Migrated all durations and relative time strings to the `PluralCategory` architecture, replacing simple singular/plural logic with grammatical classification.
- **Ordinality System**: Enhanced ordinal suffix formatting to use grammatical categories, supporting complex rules like Swedish/Danish (`:a/:e`) and French (`er/e`).
- **BCP 47 Fallback Fix**: Hardened the resource construction loop to ensure regional variants correctly leverage their current subtag during the fallback search.
- **Number Formatting**: Corrected decimal and grouping separators for Armenian (`hy`) and Georgian (`ka`) to align with regional standards.

### Added (Legacy)
- **AughtOne AI-Skill**: Integrated the **AughtOne AI-Skill Publishing Standard** across all modules (`readable`, `toolbox`, `datetime`).
- **Readable Module Expansion**:
    - **Relative Time Formatting**: New `Instant.toReadableRelativeTime(locale, now, nowThreshold)` extension. Supports **55 languages**, configurable `now` reference (default `Clock.System.now()`), and a configurable `nowThreshold` (default 5 seconds).
    - **Temporal Extensions**: Added `LocalDateTime.toReadableRelativeTime()` and `LocalDate.toReadableRelativeTime()` extension functions for direct relative formatting.
    - **Duration Formatting**: Smart-scaling durations (s, m, h, d, w, mo, y) with human-centric rounding and **55-language** support.
    - **Geospatial Formatting**: Localized formatting for `Altitude`, `Azimuth`, and `Coordinates` (DD/DMS). **Cardinal directions are now localized** across 55 languages.
    - **Numeric Standardization**: Standardized `toReadable*` and `formatReadable` naming conventions.
    - **Functional Architecture**: Refactored all formatters to use "baked" lambdas (`Formatter<T>`) for high performance and immutability.
- **Ordinality System**: Established the functional resource map infrastructure for locale-aware formatting across **55 languages**, including new support for **Hebrew**, **Armenian**, and **Georgian**.
- **Project Structure**: Created the `:readable` and `:toolbox` modules.

### Changed
- **Lazy Resource Caching**: All resource maps (`Ordinality`, `Duration`, `Number`, `RelativeTime`) converted from eager global allocation to on-demand `buildX(tag)` + `MutableMap` cache. Zero allocation for unused locales.
- **BCP 47 Subtag Fallback**: All formatters now walk the full subtag chain (e.g., `en-ZA` → `en` → default). Regional variants only need entries where they genuinely differ from the base language.

### Regional Variants Added
- **`en-ZA`** (Number): Comma decimal, space grouping — `1 234,56` (ISO 31-0).
- **`fr-CH`** (Number): Comma decimal, apostrophe grouping — `1'234,56`.
- **`de-CH`** (Number): Period decimal, apostrophe grouping — `1'234.56`.
- **`it-CH`** (Number): Period decimal, apostrophe grouping — `1'234.56` (follows Swiss convention, not Italian).
- **`zh-TW`** (Duration): Traditional Chinese characters — `小時` (hour), `週` (week).
- **`en-ZA`** (RelativeTime): Uses `"now now"` as the threshold phrase — `"just now"` means "sometime later" in South African English.
- **`he`, `hy`, `ka`, `iu`** (Resources): Added full resource parity for Hebrew, Armenian, Georgian, and Inuktitut across all readable modules.

### Cleaned
- **Datetime Module Optimization**:
    - **Architecture**: Implemented a thread-safe, lock-free `@Volatile` map caching system in `Resources.kt` with a 100-entry bound to prevent memory leaks.
    - **Resource Completeness**: Reconciled language support with the `:readable` module, expanding from 26 to **55 supported languages**.
    - **BCP 47 Standard**: Normalized all internal resource keys to hyphenated BCP 47 tags for consistent cross-module lookups.
    - **CLDR Sanitization**: Automated stripping of unsupported CLDR tokens ('B', 'v', 'V') to ensure formatter stability.
    - **Era Overrides**: Added optional `EraNames` overrides to `format` extensions for localized or context-specific era labeling.
    - **Numbering Systems**: Introduced `NumberingSystem` support (e.g., Arabic-Indic, Devanagari) allowing digit replacement for full localization across numeric parts of dates and times.
    - **Legacy Removal**: Migrated relative time and duration formatting to the `:readable` module and cleaned up `TextResource` implementations.
