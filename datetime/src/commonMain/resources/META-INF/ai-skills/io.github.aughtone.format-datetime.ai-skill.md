---
skill-id: io.github.aughtone.format-datetime
spec-version: 1.0
name: "[Aughtone Format: DateTime](https://github.com/aughtone/aughtone-format)"
type: "Aughtone AI-Skill"
scope: core
compatibility: ">=1.0.0"
author: "[Brill Pappin](https://github.com/bpappin)"
---

# AI Skill: Aughtone Format (DateTime)

This library provides multiplatform formatting for date and time types, designed to work seamlessly with `kotlinx-datetime` and `aughtone-types`.

## 🧰 The AI Toolbox (Key Functions)

### **Date & Time Extensions**
These extensions are available on standard `kotlinx-datetime` types:
- `LocalDate.format(dateStyle: DateTimeStyle, locale: Locale?, numberingSystem: NumberingSystem?): String`
- `LocalTime.format(timeStyle: DateTimeStyle, locale: Locale?, numberingSystem: NumberingSystem?): String`
- `LocalDateTime.format(dateStyle: DateTimeStyle, timeStyle: DateTimeStyle, locale: Locale?, eraNames: EraNames?, numberingSystem: NumberingSystem?): String`
- `Instant.format(dateStyle: DateTimeStyle, timeStyle: DateTimeStyle, locale: Locale?, eraNames: EraNames?, numberingSystem: NumberingSystem?): String`

### **Advanced Options**
- **Era Overrides**: Pass `EraNames` (bce/ce strings) to override default era labels.
- **Numbering Systems**: Pass `NumberingSystem` (e.g., `ARAB`, `DEVA`, `THAI`) to replace Latin digits with localized digits.

### **Available Styles (`DateTimeStyle`)**
- `Short`: Numerical (e.g., 12/21/23).
- `Medium`: Abbreviated month (e.g., Dec 21, 2023).
- `Long`: Full month (e.g., December 21, 2023).
- `Full`: Includes day of week and Era (e.g., Friday, December 21, 2023 AD).
- `None`: Excludes the component.

## 📜 Compliance & Standards

- **Locales**: Strictly uses `io.github.aughtone.types.locale.Locale` (BCP 47).
- **Time Types**: Operates on `kotlinx-datetime` (`LocalDate`, `LocalTime`, `LocalDateTime`).
- **Internationalization**: Resources are internally mapped to handle 65+ core locales, pluralization, and grammatical cases.
- **24-Hour Master Strategy**: All internal resource patterns are stored in 24-hour (`HH:mm`) format. The formatting logic dynamically converts these to 12-hour format at runtime based on the locale's default or explicit user preference.
- **Time Handling Standards**:
    - **Kotlin 2.1+ Migration**: Always use **`kotlin.time.Instant`** and **`kotlin.time.Clock`** (from the standard library) instead of the legacy `kotlinx.datetime` versions.
    - **Ambiguity Prevention**: NEVER use wildcard imports like `import kotlinx.datetime.*`. This prevents name collisions between `kotlinx.datetime` and `kotlin.time`.
    - **No Type Mixing**: Do not mix `kotlinx.datetime.Instant` and `kotlin.time.Instant` in the same scope or API signature.

## 🤖 Agent Onboarding
1. **Context Registration**: Add this skill file to the `AGENTS.md` of the consuming project.
2. **README Verification**: Ensure the root `README.md` contains the "AI-Assisted Development" section.
3. **Usage Rules**:
    - **Consistency**: Always prefer the `.format()` extension functions over platform-specific formatters (like `SimpleDateFormat` or `NSDateFormatter`) to ensure consistent UI across Android, iOS, and Web.
    - **Locales**: If a `Locale` is not provided, the library defaults to the system's current native locale via `aughtone-types`.
    - **Future-Proofing**: This is the first of several planned formatting libraries (Numbers, Currency, and Addresses are forthcoming).
