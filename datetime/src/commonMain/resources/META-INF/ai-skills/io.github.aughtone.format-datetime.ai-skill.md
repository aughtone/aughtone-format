---
skill-id: io.github.aughtone.format-datetime
spec-version: 1.0
name: "Aughtone Format - DateTime"
type: "Aughtone AI-Skill"
scope: "Localization-aware date and time formatting for Kotlin Multiplatform."
compatibility: "Kotlin Multiplatform (KMP)"
author: "Aughtone"
---

# 📖 Aughtone Format - DateTime AI-Skill

This module provides localized formatting for `kotlinx.datetime` types. It abstracts away platform-specific differences (JVM vs. iOS vs. JS) to provide a consistent `format()` API.

## 🎨 The AI Toolbox

### 1. DateTime Styles (`DateTimeStyle`)
Standardized verbosity levels for formatting.

- **Styles**:
    - `Short`: Numeric-heavy (e.g., `12.13.52`, `3:30pm`).
    - `Medium`: Abbreviated names (e.g., `Jan 12, 1952`).
    - `Long`: Full names (e.g., `January 12, 1952`).
    - `Full`: Maximum detail including day of week and era (e.g., `Tuesday, April 12, 1952 AD`).
    - `None`: Excludes the date or time component from the output.

### 2. Extension Formatting (`format`)
Primary entry point for all temporal types.

- **Supported Types**: `Instant`, `LocalDateTime`, `LocalDate`, `LocalTime`.
- **Primary APIs**:
    - `T.format(dateStyle, timeStyle, locale, timeZone, is24HourFormat): String`
- **Preference**: Use `format()` instead of `toString()` for any temporal value displayed to a user.
- **Contract**:
    - Uses `TimeZone.currentSystemDefault()` if not specified.
    - `is24HourFormat` is automatically determined by locale but can be overridden.

### 3. Dynamic Formatting
Builders for custom patterns that still respect locale.

- **Primary APIs**:
    - `DateFormatBuilder.extensions`
    - `TimeFormatBuilder.extensions`
- **Preference**: Use these when predefined `DateTimeStyle` options are insufficient but you still want to maintain localization.

## ⚖️ Compliance & Standards

- **ISO 8601**: Underlying storage and default `toString()` follow ISO 8601.
- **CLDR / BCP 47**: Formatting patterns and localized names are derived from the Unicode Common Locale Data Repository via platform-specific providers.
- **24-Hour Rule**: Respects regional preferences for 12/24 hour clocks.

## 🔒 Serialization & Immutability

- **Thread Safety**: Formatting is stateless and safe to call from any thread/coroutine.
- **Immutability**: Operates on immutable `kotlinx.datetime` types and returns immutable `String` results.

## 🤖 Agent Onboarding (Usage Rules)

1.  **TimeZone Awareness**: When formatting `Instant`, always be conscious of the `timeZone`. Defaulting to `systemDefault()` is common for UI, but UTC may be required for logs or internal values.
2.  **Style Combinations**: You can mix styles (e.g., `dateStyle = Medium, timeStyle = Short`). Use `DateTimeStyle.None` to hide a component (e.g., to format only the date).
3.  **Strict Locale Import**: Ensure you use `io.github.aughtone.types.locale.Locale`.
4.  **24-Hour Override**: Do not hardcode 12 or 24 hour logic unless explicitly requested. Let the `is24HourFormat(locale)` helper handle it to respect user expectations.
