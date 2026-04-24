# Acceptance Criteria (ACs)

This document tracks the verification goals for the Aughtone Format library features.

## 📖 Readable Module (`:readable`)

### 1. Ordinality Formatting
- [x] Supports `Int` and `Long` types.
- [x] Correctly formats English ordinals (1st, 2nd, 11th, 21st).
- [x] Correctly formats French ordinals (1er, 2e).
- [x] Correctly formats Spanish/Italian/Portuguese ordinals (1.º, 2.º).
- [x] Correctly formats German/Czech/Scandinavian ordinals (1., 2.).
- [x] Correctly formats Japanese/Chinese ordinals (第1, 第2).
- [x] Fallback mechanism works correctly (e.g., `fr-CA` -> `fr`).
- [x] Default value for `locale` parameter uses the native system locale.

### 2. Relative Time Formatting
- [x] Supports `Instant`, `LocalDateTime`, `LocalDate`, and `LocalTime`.
- [x] `relativeThreshold` parameter provides a fallback to absolute formatting (default 3 days for date types, 3 hours for time).
- [x] Fallback format defined by `dateStyle` and `timeStyle` parameters (`DateTimeStyle`).
- [x] `LocalDate.readableRelative` correctly omits `timeStyle`.
- [x] `LocalTime.readableRelative` correctly omits `dateStyle`.
- [x] `nowThreshold` for `LocalDate` defaults to 1 day.
- [x] `LocalDate` returns localized "Recently" string for dates within `nowThreshold` (fuzzy matching). Hardened across all supported languages.
- [x] Localization supported across **65+ languages** via `RelativeTimeConfig`.

### 3. Metrics & Abbreviations [PLANNED]
- [ ] Supports numbers from Thousands to Trillions.
- [ ] Localization follows regional rules (e.g., K/M/B vs k/M/Md).

### 3. Data Sizes [PLANNED]
- [ ] Supports IEC (binary) units (KiB, MiB).
- [ ] Supports SI (decimal) units (KB, MB).

## 📅 Datetime Module (`:datetime`)

### 1. Core Formatting
- [x] Supports `LocalDate`, `LocalTime`, and `LocalDateTime`.
- [x] Migrated to `kotlin.time.Instant` (Standard Library).
- [x] Relative time formatting migrated to `:readable` module.

### 2. Localization Hardening
- [x] Verified **51-language** resource coverage.
- [x] Implementation of thread-safe, bounded resource caching (`@Volatile`).
- [x] Removal of legacy compiler flags and redundant dependencies.

### 3. Advanced Features
- [x] **Era Overrides**: Custom BCE/CE labels correctly injected into patterns.
- [x] **Numbering Systems**: Correct replacement of Latin digits with localized digit sets (e.g., Arabic-Indic).
