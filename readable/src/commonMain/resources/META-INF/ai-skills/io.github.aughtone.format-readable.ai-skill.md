---
skill-id: io.github.aughtone.format-readable
name: "[Aughtone Format: Readable](https://github.com/aughtone/aughtone-format)"
type: "Aughtone AI-Skill"
scope: core
compatibility: ">=1.0.0"
author: "[Brill Pappin](https://github.com/bpappin)"
---

# AI Skill: Aughtone Format (Readable)

This library provides localized human-readable formatting for Kotlin Multiplatform.

## 🧰 The AI Toolbox

### **Ordinality Formatting**
Convert numbers to their ordinal forms (e.g., 1 to "1st").
- `Int.toReadableOrdinal(locale: Locale): String`
- `Long.toReadableOrdinal(locale: Locale): String`

### **Numeric Abbreviation**
Format large numbers into abbreviated strings (e.g., 1500 to "1.5k").
- `Double.toReadableAbbreviated(locale: Locale, precision: Int): String`

### **Metric & Data Scaling**
Format quantities and data sizes with automatic unit scaling.
- `Distance.formatReadable(locale: Locale): String` (e.g., "1.5 km")
- `Speed.formatReadable(locale: Locale): String`
*   `Long.toReadableDataSize(unit: UnitOfMeasure): String` (e.g., "1.0 MiB")

### **Geo Formatting**
Localized formatting for altitudes, azimuths, and coordinates.
- `Altitude.formatReadable(): String` (scales to km if needed)
- `Azimuth.formatReadable(): String` (e.g., "90° (E)")
- `Coordinates.formatReadable(format: CoordinateFormat): String`
    - `DecimalDegrees`: "40.7128° N, 74.006° W"
    - `DegreesMinutesSeconds`: "40° 42' 46\" N, 74° 0' 21\" W"

### **Relative Time Formatting**
Format instants and dates relative to a reference point (e.g., "5 minutes ago", "Today").
- `Instant.toReadableRelative(locale, dateStyle, timeStyle, now, nowThreshold, timeZone): String`
- `LocalDateTime.toReadableRelative(timeZone, locale, dateStyle, timeStyle, now, nowThreshold): String`
- `LocalDate.toReadableRelative(locale, style, now): String` (optimized for date-only comparison)
- `LocalTime.toReadableRelative(locale, style, now): String` (optimized for time-only comparison)
    - **RelativeStyle**: `Long` ("5 days ago"), `Short` ("5d ago"), `None` (suppress component).
    - **Day Strings**: Automatically handles "Today", "Tomorrow", and "Yesterday" for dates within +/- 1 day.
    - **Defaults**: `nowThreshold` defaults to 1 minute. `locale` defaults to `Locale.current`.

### **Duration Formatting**
Localized, human-friendly duration scaling.
- `kotlin.time.Duration.toReadableString(locale, style): String`
    - **RelativeStyle**: `Long` ("2 minutes"), `Short` ("2m").
    - Smart scaling: 59s -> "59 seconds", 60s -> "1 minute"
    - Rounded weeks: 11d -> "2 weeks"
    - Day/Month threshold: 29d -> "29 days", 30d -> "1 month"

### **Pluralization & Grammar**
The library uses a robust **Plural Category System** (Zero, One, Two, Few, Many, Other) based on Unicode CLDR rules to ensure grammatical accuracy across all 55 supported languages.
- Slavic (3-form), Arabic (6-form), Hebrew (4-form), and Inuktitut (4-form) are explicitly supported.
- Centralized logic via `pluralCategoryFor(locale, n)` and `ordinalCategoryFor(locale, n)`.

## 📜 Compliance & Standards

- **Locales**: Strictly uses `io.github.aughtone.types.locale.Locale`. Supports 55 core locales.
    - Use **`Locale.current`** as the best way to retrieve the current system locale.
- **Time Handling Standards**:
    - **Kotlin 2.1+ Migration**: Always use **`kotlin.time.Instant`** and **`kotlin.time.Clock`** (from the standard library) instead of the legacy `kotlinx.datetime` versions.
    - **Ambiguity Prevention**: NEVER use wildcard imports like `import kotlinx.datetime.*`. This prevents name collisions between `kotlinx.datetime` and `kotlin.time`.
    - **No Type Mixing**: Do not mix `kotlinx.datetime.Instant` and `kotlin.time.Instant` in the same scope or API signature.
- **Scaling**: 
    - **SI Units**: Powers of 1000 (k, M, G, etc.).
    - **Digital Data**: Powers of 1024 (Ki, Mi, Gi, etc.) using IEC symbols.
- **Packages**:
    - `io.github.aughtone.readable.ordinality`
    - `io.github.aughtone.readable.number`
    - `io.github.aughtone.readable.metrics`

## 🔗 References

- [Wiktionary: Ordinal numbers by language](https://en.wiktionary.org/wiki/Category:Ordinal_numbers_by_language)
- [Wikipedia: Metric prefix](https://en.wikipedia.org/wiki/Metric_prefix)
- [IEC 80000-13: Prefixes for binary multiples](https://en.wikipedia.org/wiki/Binary_prefix)
