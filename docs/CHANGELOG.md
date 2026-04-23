# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **AughtOne AI-Skill**: Integrated the **AughtOne AI-Skill Publishing Standard** across all modules (`readable`, `toolbox`, `datetime`).
- **Readable Module Expansion**:
    - **Relative Time Formatting**: New `Instant.toReadableRelativeTime(locale, now, nowThreshold)` extension. Supports 51 languages, configurable `now` reference (default `Clock.System.now()`), and a configurable `nowThreshold` (default 5 seconds).
    - **Duration Formatting**: Smart-scaling durations (s, m, h, d, w, mo, y) with human-centric rounding and 51-language support.
    - **Geospatial Formatting**: Localized formatting for `Altitude`, `Azimuth` (cardinal directions), and `Coordinates` (DD/DMS).
    - **Numeric Standardization**: Standardized `toReadable*` and `formatReadable` naming conventions.
    - **Functional Architecture**: Refactored all formatters to use "baked" lambdas (`Formatter<T>`) for high performance and immutability.
- **Ordinality System**: Established the functional resource map infrastructure for locale-aware formatting across 51 languages.
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
