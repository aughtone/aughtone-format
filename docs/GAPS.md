# Project Gaps: Aughtone Format

This document tracks technical debt, missing features, and pending refactors for the Aughtone Format library.

## 📅 Datetime Module (`:datetime`)

### Relative Time Migration
- [x] **Delete Legacy Formatters.** Removed `RelativeStyle.kt` and `MultiplatformDurationFormatter.kt`.
- [x] **Remove Dependency.** Circular dependencies and bloat avoided by moving logic to `:readable`.

### Numbering Systems
- [ ] **Implement Localized Numbering.** Add support for non-Western digits (e.g., Arabic-Indic) in the formatting pipeline, respecting locale preferences while allowing a forced Western digits override.

## 📖 General
- [ ] **Indicate Supported Languages.** Update the main `README.md` to clearly indicate the **55 supported languages** and regions provided by the library.
- [ ] **gap: Resolve KLIB resolver collisions.** Address `unique_name` collisions between AndroidX and JetBrains Compose libraries (e.g., `lifecycle`, `collection`, `annotation`) by implementing a selective `resolutionStrategy` in the root `build.gradle.kts` that aligns versions across targets without breaking Android-specific artifacts (like `-ktx`).

## 📖 Readable Module (`:readable`)
- [x] **Relative Time Integration.** Update `Duration` formatting to include the localized "ago" and "in" wrapping logic (Short/Long styles) migrated from the `:datetime` module.
