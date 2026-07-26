# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [3.1.0] - 2026-07-25

### Added
- **New / completed localizations (from the library-wide locale audit)**:
  - Relative time now localized for Danish (`da`), Norwegian Bokmål (`nb`, also `no`), Norwegian Nynorsk (`nn`), Swedish (`sv`), and Icelandic (`is`) — previously allow-listed but unimplemented, so they rendered in English.
  - Compass-point abbreviations added for Inuktitut (`iu`) and Traditional Chinese (`zh-TW`, was inheriting Simplified forms).
  - Traditional Chinese (`zh-TW`) era names (西元前/西元) added (was inheriting Simplified 公元前/公元).
  - Localized AM/PM markers for 11 locales that previously held English `AM`/`PM` placeholders (de, nl, ru, uk, be, sr, he, hy, kk, uz, sw).
  - Inuktitut (`iu`) time-zone full names (`getFullNameIu`, 143 zones) — `iu` previously had abbreviations only and fell back to English for full time-zone names.
- **`LocalDate` "Shortly" (near-future label)**: `RelativeTimeConfig` gains a `shortlyString`, the future mirror of `recentlyString`. `LocalDate.formatReadableRelative` now renders near-future dates within `nowThreshold` (beyond "Tomorrow") as the fuzzy "Shortly" string, symmetric with "Recently" for the recent past.
- **`LocalTime` Relative Direction**: New `RelativeDirection { Past, Present, Future, Nearest }` enum and a `direction` parameter on `LocalTime.formatReadableRelative` to resolve the ambiguity of a date-less clock time across midnight (modelled on ICU's `RelativeDateTimeFormatter.Direction`).
- **Calendar-anchored `LocalTime` overload**: `LocalTime.formatReadableRelative(now: Instant, timeZone, …)` resolves the time to a concrete occurrence relative to a dated anchor and can therefore render day labels such as "Yesterday"/"Today"/"Tomorrow".

### Changed
- **`LocalTime` relative default is now `RelativeDirection.Nearest`**: `LocalTime.formatReadableRelative` previously used a linear same-day subtraction; it now defaults to the nearest occurrence on a 24-hour clock. Output is unchanged for times within 12 hours on the same side of `now`, but differs for pairs more than 12 hours apart or crossing midnight (e.g. `now = 23:00, this = 01:00` is now "in 2 hours" instead of "22 hours ago"). Pass `RelativeDirection.Present` for the previous behaviour.

### Fixed
- **Persian (`fa`) money symbol placement**: `fa` had no placement rule and fell to the prefix-no-space default; the currency symbol is now placed after the amount with a space (`"1٬234 ﷼"`), matching Persian convention.
- **Duration formatting fell back to English for 10 locales**: `az`, `eu`, `hy`, `ka`, `kk`, `lt`, `lv`, `sq`, `uz`, and `iu` had complete localized duration unit data but were missing from the internal `isDurationTagSupported` list, so `durationFormatterFor` never selected them and these locales rendered durations in English. Added to the list; they now use their own translations.
- **Indonesian / Malay / Swahili fell back to English**: `id`, `ms`, and `sw` had full localized relative-time configs but were missing from the internal supported-tag list, so `relativeTimeConfigFor` never selected them and these locales rendered in English. Added to the allow-list; they now use their own translations.
- **`LocalDate` "Recently" / `nowThreshold`**: `LocalDate.formatReadableRelative` evaluated its `nowThreshold` window before the Today/Yesterday/Tomorrow labels, so a custom `nowThreshold >= 2` days replaced "Yesterday"/"Tomorrow" with "Recently", and "Recently" could describe future dates. The specific day labels now always take precedence, and "Recently" applies only to past dates within `nowThreshold` beyond yesterday. Default behaviour is unchanged. Regression tests added.
- **Relative Style Selection**: `Instant.formatReadableRelative` resolved the Today/Tomorrow/Yesterday config from `relativeTimeStyle` inside the date-units branch; it now uses a style-free day-label lookup so the wrong style cannot be selected there. Output is unchanged for current locales (these labels are style-invariant today); the style-invariance of the day labels is now guarded by a dedicated test, and tests cover date-only (`relativeTimeStyle = None`), time-only (`relativeDateStyle = None`), threshold boundaries, and day-boundary/timezone disagreement cases.
- **Documentation**: Documented on `formatReadableRelative` that passing the receiver as `now` collapses the delta to zero and renders everything as "just now".

## [3.0.3] - 2026-06-28

### Added
- **Non-Western Numbering Systems**: Automatically fall back to local native digit numbering systems for non-Western locales (ar, hi, th, bn, fa, ur).

### Changed
- **Migration to Types 3.1.0**: Migrated all extension functions in `:datetime` module and `SystemSettings` to use the new `Locale.current` property, resolving `IrLinkageError` runtime crashes on Kotlin/JS.
- **Thread-Safety Improvements**: Converted all 6 internal resource caches in the `:readable` module to thread-safe copy-on-write volatile maps with a 150-entry max size limit.
- **Formatter Optimization**: Removed nested non-thread-safe cache allocations from money formatter closures, resolving number formatters directly.
- **Yarn Lock Workaround**: Configured Yarn lock mismatch reporting to `WARNING` and `yarnLockAutoReplace = true` to prevent CI failures.
- **Gradle Config Cache**: Disabled Gradle configuration cache to bypass Kotlin JS/WasmJS browser test serialization bugs.

### Fixed
- **Documentation**: Documented all 59 supported locales in a detailed markdown table in the main README.md.
- **iOS Linker Compatibility**: Set `minVersion.ios=16.0` globally in root build.gradle.kts to resolve UIKit layout region linkage errors on Xcode 16+ for iOS Simulator targets.
- **JS Linker Compatibility**: Version 3.0.2 was deployed with a stale link to an old types version, causing a linker error.

## [3.0.1] - 2026-05-31

### Added
- **TimeZone Formatting**: Implemented `TimeZone.formatReadable` extension overloads in `:readable` module supporting reference `Instant` or direct `UtcOffset` parameters.
- **Optimized TimeZone Lookup**: Added `TimeZoneNamesLookup` generated static lookup logic supporting zero object allocations and language-specific helper methods to bypass JVM method limits.
- **Resource Management**: Removed redundant runtime caching from `Resources.kt` in favor of fast, garbage-free direct static lookups.
- **Locale Pass-Through**: Updated `MultiplatformPostFormatter` and date/time formatters to pass user `Locale` down to timezone formatting lookups.
- **Governance & Specs**: Updated `SPEC.md` and Acceptance Criteria to track formatting specs and verify correctness on BCP-47 fallbacks.

## [3.0.0] - 2026-05-30

### Added
- **New Vector Modules**: Introduced `:viewable` for platform-agnostic vector graphic representations, styling, and path conversion (SVG, WKT, GeoJSON), and `:viewable-compose` for Jetpack / Compose Multiplatform integration.
- **AI-Skills Integration**: Added machine-readable AI skills documentation for both `:viewable` and `:viewable-compose` to assist coding agents.
- **API Naming Modernization**: Deprecated `toReadable*` prefix on formatting extensions in favor of unified `formatReadable*` APIs for better readability and alignment with standard style guide.
- **KMP Compilation Fixes**: Resolved UIKit and CoreGraphics experimental API opt-in requirements for iOS targets.
- **SVG Test Re-alignment**: Re-aligned misplaced SVG tests from `:toolbox` to `:viewable` module where they belong.
- **Documentation Refinement**: Updated README code examples with non-deprecated functions.

## [2.2.0] - 2026-05-13

### Added
- **Expanded Numeric Support**: Added `toReadable`, `toReadableAbbreviated`, `toReadableDataSize`, and `toReadableMetric` support for all Kotlin numeric types (Byte, Short, Int, Long, Float, Double, and Unsigned variants).
- **Improved KDocs**: Comprehensive documentation and usage examples added to all core formatting functions in the `:readable` module.
- **Geospatial Localization**: Added support for 65+ languages in cardinal directions and coordinate formatting.
- **AI Skill Enhancements**: Synchronized and updated `.ai-skill.md` files for all modules with latest patterns and standards.
- **Magic Prompt for AI Assistants**: Added a standardized prompt to the README to help AI agents discover and use embedded library skills from dependencies.

## [2.1.2] - 2026-04-24

### Added
- **AI Skill Versioning**: Implemented mandatory `spec-version: 1.0` metadata for all embedded AI skill files and the publishing standard to enhance agent compatibility.
- **Recently Phrasing**: Added localized `"Recently"` strings to `RelativeTimeConfig` for Catalan, Galician, Romanian, Lithuanian, Latvian, Albanian, Inuktitut, and Georgian. Hardened existing translations for Azerbaijani, Uzbek, Kazakh, Basque, and Armenian.
- **Relative Fallback**: Introduced `relativeThreshold` to `readableRelative` functions, allowing automatic fallback to standard formatting (via `dateStyle` and `timeStyle`) when the temporal difference is too large.
- **Language Registry Expansion**: Updated `isRelativeTimeTagSupported` to formally support 65+ locales.

### Changed
- **Relative Time API**: Renamed `toReadableRelative` to `readableRelative` across all temporal types for better readability.
- **Optimized Thresholds**: Standardized default `relativeThreshold` to `3.days` for dates and `3.hours` for times. Default `nowThreshold` for `LocalDate` is now `1.days`.
- **Dependency Synchronization**: Updated `aughtone-types` to `2.0.3` to incorporate the latest ecosystem-wide stabilization and metadata improvements.

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
