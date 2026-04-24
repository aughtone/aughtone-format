# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **Relative Fallback**: Introduced `relativeThreshold` to `readableRelative` functions, allowing automatic fallback to standard formatting (via `dateStyle` and `timeStyle`) when the temporal difference is too large.
- **"Recently" Phrasing**: Added localized `"Recently"` strings to `RelativeTimeConfig` for Catalan, Galician, Romanian, Lithuanian, Latvian, Albanian, Inuktitut, and Georgian. Hardened existing translations for Azerbaijani, Uzbek, Kazakh, Basque, and Armenian to ensure accuracy.
- **Language Registry Expansion**: Updated `isRelativeTimeTagSupported` to formally support 65+ locales, including Azerbaijan, Uzbek, Kazakh, and several Baltic/Balkan languages.

### Changed
- **Relative Time API**: Renamed `toReadableRelative` to `readableRelative` across all temporal types.
- **Optimized Thresholds**: Standardized default `relativeThreshold` to `3.days` for dates and `3.hours` for times. Default `nowThreshold` for `LocalDate` is now `1.days`.

## [2.1.1] - 2026-04-24

### Added
- **Pluralization Engine**: Integrated a Unicode CLDR-compliant **Plural Category Engine** (`pluralCategoryFor`, `ordinalCategoryFor`) for accurate grammatical parity across 55+ languages.
- **Grammatical Factories**: Implemented multi-form factories (`u2` through `u6`) to handle varied linguistic complexity (Slavic, Arabic, Hebrew, Inuktitut).
- **GeoResources Expansion**: Full **55-language coverage** for cardinal directions with robust, recursive BCP 47 subtag fallback.
- **Relative Time Styles**: Introduced `RelativeStyle` (`Long`, `Short`, `None`) to control the verbosity of relative time strings (e.g., "5 days ago" vs "5d ago").
- **Day-Based Phrasing**: Added support for "Today", "Tomorrow", and "Yesterday" special-case strings in 55+ languages.
- **Type-Safe Relative Formatting**: Added optimized `toReadableRelative` extensions for `LocalDate` and `LocalTime` with native type-safe `now` parameters.
- **Time Handling Standards**: Formalized project-wide standards in AI Skills for `kotlin.time` (Kotlin 2.1+) transition and restricted wildcard imports to prevent ambiguity.
- **Branding & Standardization**:
    - Standardized Group ID and namespace to `io.github.aughtone`.
    - Renamed all artifacts to use the `format-` prefix consistently (`format-datetime`, `format-readable`, `format-toolbox`).
    - Unified iOS Kit naming to `AughtoneFormat[Module]Kit`.
    - Ecosystem rebranding from "AughtOne" to "Aughtone".

### Changed
- **Relative Time API Standardization**: Renamed `toReadableRelativeTime` to `toReadableRelative` across all temporal types for consistency.
- **Threshold Optimization**: Increased the default `nowThreshold` for `Instant` and `LocalDateTime` to **1 minute** and implemented a **1-day** threshold for `LocalDate`.
- **Locale Integration**: Standardized `Locale.current` (from `aughtone-types`) as the default for all readable formatting functions, replacing platform-specific logic.
- **Duration Styles**: Updated `kotlin.time.Duration.toReadableString` to support `RelativeStyle`, enabling short-form durations (e.g., "5m").
- **Duration & Relative Time Refactor**: Migrated all durations and relative time strings to the `PluralCategory` architecture, replacing simple singular/plural logic with grammatical classification.
- **Ordinality System**: Enhanced ordinal suffix formatting to use grammatical categories, supporting complex rules like Swedish/Danish (`:a/:e`) and French (`er/e`).

### Fixed
- **BCP 47 Fallback**: Hardened the resource construction loop to ensure regional variants (e.g., `en-ZA`, `zh-TW`) correctly leverage their full subtag chain during fallback search.
- **Number Formatting**: Corrected decimal and grouping separators for Armenian (`hy`) and Georgian (`ka`) to align with regional standards.
- **Traditional Chinese Variants**: Added explicit mapping and logic for `zh-TW`, `zh-HK`, and `zh-Hant` in `RelativeTime` and `Duration` modules.

## [2.0.2] - 2024-04-19

### Added
- **Aughtone AI-Skill**: Integrated the **Aughtone AI-Skill Publishing Standard** across all modules (`readable`, `toolbox`, `datetime`).
- **Readable Module Expansion**:
    - **Relative Time Formatting**: New `Instant.toReadableRelativeTime(locale, now, nowThreshold)` extension. Supports **55 languages**.
    - **Temporal Extensions**: Added `LocalDateTime.toReadableRelativeTime()` and `LocalDate.toReadableRelativeTime()` extension functions.
    - **Duration Formatting**: Smart-scaling durations (s, m, h, d, w, mo, y) with human-centric rounding and **55-language** support.
    - **Geospatial Formatting**: Localized formatting for `Altitude`, `Azimuth`, and `Coordinates` (DD/DMS).
    - **Numeric Standardization**: Standardized `toReadable*` and `formatReadable` naming conventions.
    - **Functional Architecture**: Refactored all formatters to use "baked" lambdas (`Formatter<T>`) for high performance and immutability.
- **Ordinality System**: Established the functional resource map infrastructure for locale-aware formatting across **55 languages**.
- **Project Structure**: Created the `:readable` and `:toolbox` modules.

### Changed
- **Lazy Resource Caching**: All resource maps converted to on-demand `buildX(tag)` + `MutableMap` cache. Zero allocation for unused locales.
- **Datetime Module Optimization**:
    - **Architecture**: Implemented thread-safe, lock-free `@Volatile` map caching system.
    - **Resource Completeness**: Expanded from 26 to **55 supported languages**.
    - **CLDR Sanitization**: Automated stripping of unsupported CLDR tokens to ensure formatter stability.

### Regional Variants Added
- **`en-ZA`**: Comma decimal, space grouping, and `"now now"` threshold phrase.
- **`fr-CH`**, **`de-CH`**, **`it-CH`**: Swiss-specific decimal/grouping conventions.
- **`zh-TW`**: Traditional Chinese characters for duration units.
- **`he`, `hy`, `ka`, `iu`**: Full resource parity for Hebrew, Armenian, Georgian, and Inuktitut.
