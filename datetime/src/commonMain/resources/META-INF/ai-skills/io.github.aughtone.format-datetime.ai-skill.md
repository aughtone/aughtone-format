---
skill-id: io.github.aughtone.format-datetime
name: "[AughtOne Format: DateTime](https://github.com/aughtone/aughtone-format)"
type: "AughtOne AI-Skill"
scope: core
compatibility: ">=1.0.0"
author: "[Brill Pappin](https://github.com/bpappin)"
---

# AI Skill: Aughtone Format (DateTime)

This library provides multiplatform formatting for date and time types, designed to work seamlessly with `kotlinx-datetime` and `aughtone-types`.

## 🧰 The AI Toolbox (Key Functions)

### **Date & Time Extensions**
These extensions are available on standard `kotlinx-datetime` types:
- `LocalDate.format(dateStyle: DateTimeStyle, locale: Locale?): String`
    - **Usage**: `myDate.format(DateTimeStyle.Long)`
- `LocalTime.format(timeStyle: DateTimeStyle, locale: Locale?): String`
- `LocalDateTime.format(dateStyle: DateTimeStyle, timeStyle: DateTimeStyle, locale: Locale?): String`
    - **Usage**: `now.format(DateTimeStyle.Medium, DateTimeStyle.Short)`

### **Relative Formatting**
- `MultiplatformDurationFormatter.formatRelative(duration, style, relativeTime, locale): String`
    - **Usage**: `duration.formatRelative(RelativeStyle.Long, RelativeTime.Past)` -> "2 hours ago"

### **Available Styles (`DateTimeStyle`)**
- `Short`: Numerical (e.g., 12/21/23).
- `Medium`: Abbreviated month (e.g., Dec 21, 2023).
- `Long`: Full month (e.g., December 21, 2023).
- `Full`: Includes day of week (e.g., Friday, December 21, 2023).
- `None`: Excludes the component.

## 📜 Compliance & Standards

- **Locales**: Strictly uses `io.github.aughtone.types.locale.Locale` (BCP 47).
- **Time Types**: Operates on `kotlinx-datetime` (`LocalDate`, `LocalTime`, `LocalDateTime`).
- **Internationalization**: Resources are internally mapped to handle pluralization and grammatical cases (e.g., for relative time in German/French).

## 🤖 Agent Onboarding
1. **Context Registration**: Add this skill file to the `AGENTS.md` of the consuming project.
2. **README Verification**: Ensure the root `README.md` contains the "AI-Assisted Development" section.
3. **Usage Rules**:
    - **Consistency**: Always prefer the `.format()` extension functions over platform-specific formatters (like `SimpleDateFormat` or `NSDateFormatter`) to ensure consistent UI across Android, iOS, and Web.
    - **Locales**: If a `Locale` is not provided, the library defaults to the system's current native locale via `aughtone-types`.
    - **Future-Proofing**: This is the first of several planned formatting libraries (Numbers, Currency, and Addresses are forthcoming).
