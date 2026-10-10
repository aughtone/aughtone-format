# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Fixed
- **Dates no longer throw for nine languages** (`:datetime`): `Long` and `Full` dates threw `IllegalArgumentException` for Spanish, Portuguese, Catalan, Galician, Basque, Lithuanian and Latvian, and `Full` for Danish and Finnish, because quoted words in the locale's pattern (`'de'`) were read as pattern letters. Literal text is now copied through, so Russian and Bulgarian dates also lose their stray quote marks, Japanese times keep `時分秒`, and a zone in parentheses no longer leaves a doubled space.
- **Year formatting** (`:datetime`): a two-digit year shows the last two digits in any century (`31.12.99`, was `31.12.+1999`); a bare year is no longer zero-padded (`800`, was `0800`); at `Full`, years before the common era are counted from one (ISO year 0 is "1 Before Common Era").
- **Region variants keep their language's patterns** (`:datetime`): French in Canada and Spanish in the United States were given English date order; Inuktitut in Canada was given English AM/PM.
- **Zone name in the repeated hour** (`:datetime`): `Instant.format` named the zone for the first pass of the hour when clocks go back, so both read "EDT"; it now uses the exact instant. `LocalDate.format` no longer shifts a date in a zone where that day's midnight does not exist. A zone at zero offset (London in winter) resolves "GMT" where it returned "Z".
- **Number formatting** (`:readable`): negative numbers grouped their minus sign (`"-,123"`); values from ten million up, and below 0.001, came out as fragments of exponent notation on JVM, Android and iOS (`"1"`, `"5.0E-4"`); `Double.NaN` threw. Numbers are now written out in full on every platform, `Long` and `ULong` keep every digit, and not-a-number and the infinities render as `"NaN"`, `"∞"` and `"-∞"`.
- **Money** (`:readable`): amounts dropped trailing zeros (`"$12.5"`, `"$12"`), large amounts were truncated (`"$1.0E"`), and negatives read `"$-,123"`. Money now always shows the currency's minor-unit digits, is exact for any amount, and puts the minus sign before an attached symbol (`"-$123.00"`).
- **Unit boundaries** (`:readable`): a value that rounds up to the next unit is shown in that unit — `"1 minute"` not `"60 seconds"`, `"1M"` not `"1,000k"`, `"1 MiB"` not `"1,024 KiB"`, `"1 km"` not `"1,000 m"`.
- **Plural forms** (`:readable`): Czech, Slovak, Croatian, Serbian, Slovenian, Lithuanian and Latvian always used the singular ("5 den"); Icelandic, Macedonian and Romanian mis-handled 21 and 20+. `pluralCategoryFor` now follows the CLDR plural rules for every bundled language. Slovenian and Latvian unit words, which were wrong, are replaced.
- **Language gaps** (`:readable`): "Today"/"Tomorrow"/"Yesterday" were English for about forty languages; Dutch, Afrikaans, Indonesian, Malay and Swahili durations used English unit words; Azerbaijani used Turkish ones. German relative time used the wrong case ("vor 2 Tage"), as did the singular in Russian, Ukrainian, Belarusian and Polish.
- **Separators and currency placement** (`:readable`): decimal and grouping separators were wrong for fifteen languages (Indonesian and Vietnamese had them swapped); the currency symbol was on the wrong side for Afrikaans, Hebrew, Swahili and German- and Italian-speaking Switzerland.
- **Relative-time grammar** (`:readable`): "N units ago" and "in N units" used one unit word for both directions, which put the unit in the wrong case in many languages ("před 5 minuta" for "před 5 minutami"). Arabic, Czech, Finnish, Hungarian, Armenian, Icelandic, Georgian, Kazakh, Lithuanian, Slovak, Slovenian, Albanian, Serbian and Swahili now take each phrase whole from Unicode CLDR, by unit, direction and plural form. Unit words are corrected in Croatian, Latvian, Estonian, Uzbek, Romanian ("21 de minute"), Dutch and Afrikaans ("2 uur"), Hindi, Greek, Persian (which had Arabic spellings) and Macedonian (which had a word with Greek letters in it). This adds about 7.5 KB to the `:readable` artifact on each target.
- **Smaller `:readable` fixes**: `RelativeStyle.Short` durations outside English lost their space (`"5Minuten"`); a list element containing `{1}` was substituted into; a coordinate on a whole minute could read one second short (`1° 8' 59"`); an azimuth that rounds to 360° reads 0°; negative ordinals take the right suffix (`"-1st"`); Georgian ordinal suffixes were written in Armenian script; the South African English "Shortly" label was in Inuktitut.
- **`Double.format` and `String.format`** (`:toolbox`): `Double.format` returned exponent notation for large and small values (which reached SVG, EPS and WKT output), saturated above about 9.2e18, and threw on `NaN`. `String.format` read `%10` as `%1` followed by `0` and substituted into the text of earlier arguments.
- **Web Mercator at the poles** (`:toolbox`): latitude −90 projected to infinity, leaving any polygon that reaches the pole undrawable. Latitudes beyond about ±85.0511° are now held at that limit.
- **Identifier validation** (`:identifiers`): MAC, UUID and IPv6 accepted fullwidth and other-script digits as hexadecimal; `formatHandle` accepted a bare `@`; `formatDomain` accepted an empty `xn--` label and a Punycode label with a leading delimiter.
- **`ViewablePathPainter` drew in the wrong place** (`:viewable-compose`): it scaled about the centre of the draw area, so a path was displaced — usually off-canvas, a blank `Image` — unless the draw size equalled its bounds.
- **EPS, SVG and WKT output** (`:viewable`): EPS was upside down, lost the stroke of any filled layer, and could have a bounding box smaller than the drawing; `ViewableGraphic.toSvgDocument` dropped alpha; empty geometries produced invalid WKT (`POLYGON ()`, now `POLYGON EMPTY`).
- **Raster export** (`:viewable`): Android flattened JPEG transparency to black; `drawTo(Graphics2D)` left its transform on the caller's context; iOS leaked a `CGPath` per layer; Android and iOS could return empty bytes instead of failing.

- **12/24-hour clock** (`:datetime`): JavaScript and Wasm never detected the setting; they now read the hour cycle from `Intl`. Where the platform cannot say (the JVM), the convention comes from CLDR for the locale passed in, so a bare `en` is 12-hour where it was 24-hour.
- **AM/PM placement** (`:datetime`): the marker comes before the time in Korean, Hungarian, Turkish, Japanese and Chinese; it was always a suffix.
- **Month names inside dates** (`:datetime`): Czech, Finnish, Polish, Russian and Ukrainian use the form the language takes after a day number ("5 марта"), where dates showed the dictionary form ("5 Март").
- **Zone names that share an abbreviation** (`:datetime`): London's "BST" resolved to Bangladesh Standard Time, Shanghai's "CST" to Central Standard Time, Jerusalem's "IST" to India Standard Time, and so on; each zone now keeps its own name.
- **Digit grouping** (`:readable`): Spanish, Italian, Polish, Portuguese (Portugal), Hungarian and others no longer group a four-digit number; Hindi and Indian English group in lakhs; Spanish in the Americas uses its region's separators.
- **Lists** (`:readable`): English outside the United States has no comma before the conjunction; Hebrew joins its conjunction to a non-Hebrew word with a hyphen.
- **More small fixes**: 345–364 days reads "1 year", not "12 months"; `ULong` ordinals above the signed range keep their digits (`:readable`); `obfuscateLast` no longer throws on a negative count or splits an emoji, and `String.format` accepts a `null` argument (`:toolbox`); a combined date-time format with one half empty has no stray space (`:datetime`).
- **Flat lines, single points and hand-built paths** (`:viewable`, `:viewable-compose`): a shape with no width or no height gave an infinite scale and an unusable SVG viewBox; JVM strokes overran the ends of a line; `toImage` with a non-positive size failed differently on each platform and now throws `IllegalArgumentException` on all of them.

### Changed
- **Icelandic relative past has no trailing "síðan"** (`:readable`): "fyrir 8 mínútum", following CLDR, where it read "fyrir 8 mínútum síðan". Other languages that now take whole CLDR phrases changed wording the same way (Slovak "o 2 hodiny" for "za 2 hodina", Armenian and Kazakh future forms).
- **English in the United Kingdom, Ireland and South Africa defaults to the 24-hour clock** (`:datetime`), following CLDR, where it defaulted to 12-hour. This applies where the platform reports no setting of its own.
- **A `ViewablePath` built without bounds works them out from its commands** (`:viewable`), so it is scaled to fit when rendered, where it was drawn unscaled.
- **`Money.formatReadable` always shows minor-unit digits** (`:readable`): twelve dollars is `"$12.00"`, where it was `"$12"`.
- **Rounding of negative halves** (`:readable`, `:toolbox`): halves round away from zero, so −2.5 rounds to −3, where it rounded to −2.
- **English date patterns outside North America** (`:datetime`): `en-GB`, `en-AU`, `en-NZ`, `en-IE`, `en-IN` and `en-ZA` write the day first, where they used the United States order.
- **Portuguese (Portugal) groups thousands with a space** (`:readable`); Brazilian Portuguese keeps the dot.

### Known issues
- Relative phrases in the languages that keep the compact form may be worded differently from CLDR where both wordings are valid (Spanish "en 2 horas" for "dentro de 2 horas"); these are left as they were. Hebrew relative phrases always show the digit.
- The "Recently" and "Shortly" labels are English for most languages, and `RelativeStyle.Short` abbreviates only in English. These and the other open items are collected in [#19](https://github.com/aughtone/aughtone-format/issues/19).
- `Altitude.formatReadable` and `Azimuth.formatReadable` are declared in both `…readable.geo` and `…readable.quantitative`; import them by name from one package.

## [4.1.0] - 2026-09-30

### Added
- **Standalone localized weekday and month names** (`:datetime`): `DayOfWeek.displayName(width, locale)` and `Month.displayName(width, locale)`, with a `TextWidth { Full, Abbreviated }` enum — the locale's day/month names ("Monday"/"Mon", "lundi", "mars") without formatting a whole date. Unsupported locales fall back to English; there is no narrow width or separate stand-alone form yet.
- **Localized time-zone display names** (`:datetime`): `TimeZone.displayName(instant, width, locale)` returns the zone's *specific* (daylight-aware) name at an instant ("Eastern Standard Time" / "EST").
- **Locale-correct list joining** (`:readable`): `List<String>.formatReadableList(locale, type = And | Or | Unit)` — "Monday, Wednesday, and Friday" / "lundi, mercredi et vendredi" — from CLDR list patterns, covering ~55 bundled locales (English fallback beyond), wide forms only.
- **Improved Inuktitut (`iu`) weekday names**: the full weekday names now use the idiomatic Inuktut day-words (ᓇᒡᒐᔾᔭᐅ, ᐊᐃᑉᐱᖅ, ᐱᖓᑦᓯᖅ, …) from the Inuktut Tusaalanga glossary, replacing the previous machine-generated descriptive "day-being-the-Nth" phrases. (CLDR carries no Inuktitut weekday names, only placeholders.)

### Changed
- **BREAKING (behaviour) — time-zone display names now delegate to each platform's own CLDR** (`java.time` on JVM, ICU on Android, `NSTimeZone` on Apple, `Intl` on JS/Wasm) with a small bundled Inuktitut supplement, replacing the previously bundled all-locale table. The library now favours **coverage over byte-identical consistency** for zone names: exact wording follows the reader's OS and runtime and can differ across targets (UTC reads "Coordinated Universal Time" on most platforms, "Greenwich Mean Time" on Apple). This affects `:datetime` Long/Full time formatting and `TimeZone.displayName`, and `:readable`'s `TimeZone.formatReadable(instant, …)`. The bare-offset `TimeZone.formatReadable(offset, …)` overload cannot consult the platform (no instant) and resolves the bundled supplement then an English name.
- Documentation/KDoc corrected across all modules in a pre-release audit (behaviour descriptions, return/throws tags, and out-of-date examples).
- Upgraded to `aughtone-types` 4.1.0 (re-exported by `:datetime`, `:readable` and `:toolbox`).
- Compose Multiplatform 1.12.1.

### Fixed
- **JPEG export on the JVM returned empty bytes** (`:viewable`): images are drawn with an alpha channel, which `ImageIO`'s JPEG writer rejects, so `ViewableImage.toByteArray(JPEG)` and `toDataUri(JPEG)` silently produced nothing. JPEG output is now flattened onto an opaque white background before encoding.

### Removed
- **BREAKING — `ImageFormat.WEBP` removed** (`:viewable`). It produced real WebP only on Android: on the JVM `ViewableImage.toByteArray(WEBP)` silently returned an empty byte array (the JDK has no WebP `ImageIO` writer) and on iOS it returned PNG bytes that `toDataUri` labelled `image/webp`. Use `ImageFormat.PNG` or `ImageFormat.JPEG`; cross-platform WebP will return once an encoder is chosen. The JVM encoder now also fails loudly if any format's writer produces no output, instead of returning empty bytes.
- The bundled all-locale `TimeZoneNamesLookup` zone-name table (~500 KB) in `:datetime`, superseded by platform-CLDR delegation plus the bundled Inuktitut supplement.

## [4.0.0] - 2026-09-21

### Added
- **New `:identifiers` module** (`io.github.aughtone:format-identifiers`) — display formatters that render a canonical identifier in an alternative notation: `String.formatMac`, `formatUuid` (and `kotlin.uuid.Uuid.format`), `formatHandle`, `formatIban`, `formatCardNumber`, `formatIpv4`, `formatIpv6`, `formatIpNetwork`, `formatDomain` (table-free Punycode / RFC 3492 decode), and `formatPhone` (E.164 → national / international / RFC 3966). Each takes an already-canonical value plus a defaulted notation enum. Every formatter is dependency-free except `formatPhone`, which uses `aughtone-phonenumber` for per-country grouping metadata (dead-code-eliminated for consumers that never format a phone).

### Changed
- **BREAKING — upgraded to `aughtone-types` 4.0.0.** Types is re-exported (`api`) by `:datetime`, `:readable`, and `:toolbox`, so its 4.0.0 API changes are part of this library's public surface.
- **BREAKING — `GeoBoundingBox` is no longer a `GeoGeometry`** (a types 4.0.0 change). Rendering a bounding box as a polygon geometry was therefore removed from `:viewable` (`toViewablePath`, `toSvgPathData`, `toWkt`) and `:toolbox` (`GeoGeometry.calculateBoundingBox` no longer has a `GeoBoundingBox` case). A `GeoBoundingBox` is a bounds value, not a geometry, and is no longer accepted by those functions.
- Toolchain bumps: Kotlin 2.4.20, Android Gradle Plugin 9.4.1, Compose Multiplatform 1.12.0, Gradle 9.6.0.

## [3.1.2] - 2026-09-07

### Changed
- Maintenance release: internal repository and documentation housekeeping only. No functional or API changes from 3.1.1; the published artifact is behaviourally identical.

## [3.1.1] - 2026-09-07

### Added
- **`NOTICE.md` and `THIRD-PARTY-NOTICES.md`**: Attribute the Unicode CLDR data compiled into the published artifact — the localized time-zone names in `TimeZoneNamesLookup.kt` (`:datetime`) — under the Unicode License, distinct from the Apache-2.0 licence that covers the library's own code. First-party curated data (for example the relative-time and duration phrasings) is recorded as carrying no third-party terms.

### Fixed
- **`LICENSE` copyright notice**: The Apache-2.0 appendix carried a stray `Copyright 2023 JetBrains s.r.o.` line copied from a template; it is reset to the standard `Copyright [yyyy] [name of copyright owner]` placeholder, with the project copyright asserted in `NOTICE.md`.

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
