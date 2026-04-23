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

### 2. Metrics & Abbreviations [PLANNED]
- [ ] Supports numbers from Thousands to Trillions.
- [ ] Localization follows regional rules (e.g., K/M/B vs k/M/Md).

### 3. Data Sizes [PLANNED]
- [ ] Supports IEC (binary) units (KiB, MiB).
- [ ] Supports SI (decimal) units (KB, MB).

## 📅 Datetime Module (`:datetime`)

### 1. Core Formatting
- [x] Supports `LocalDate`, `LocalTime`, and `LocalDateTime`.
- [x] Migrated to `kotlin.time.Instant` (Standard Library).
- [x] Integrated with `:readable` for relative time formatting.
