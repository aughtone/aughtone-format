# Project Gaps: Aughtone Format

This document tracks technical debt, missing features, and pending refactors for the Aughtone Format library.

## 📅 Datetime Module (`:datetime`)

### Relative Time Migration
- [ ] **Delete Legacy Formatters.** Remove `RelativeStyle.kt` and `MultiplatformDurationFormatter.kt` once relative time formatting is fully moved to the `:readable` module.
- [ ] **Remove Dependency.** Once the migration is complete, remove the `api(project(":readable"))` dependency from the `:datetime` module to prevent potential circular dependencies and bloat.

### Numbering Systems
- [ ] **Implement Localized Numbering.** Add support for non-Western digits (e.g., Arabic-Indic) in the formatting pipeline, respecting locale preferences while allowing a forced Western digits override.

## 📖 General
- [ ] **Indicate Supported Languages.** Update the main `README.md` to clearly indicate the 52 supported languages and regions provided by the library.
