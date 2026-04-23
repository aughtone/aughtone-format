---
skill-id: io.github.aughtone.format-readable
name: "[AughtOne Format: Readable](https://github.com/aughtone/aughtone-format)"
type: "AughtOne AI-Skill"
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

### **Duration Formatting**
Localized, human-friendly duration scaling.
- `kotlin.time.Duration.toReadableString(): String`
    - Smart scaling: 59s -> "59 seconds", 60s -> "1 minute"
    - Rounded weeks: 11d -> "2 weeks"
    - Day/Month threshold: 29d -> "29 days", 30d -> "1 month"

## 📜 Compliance & Standards

- **Locales**: Strictly uses `io.github.aughtone.types.locale.Locale`.
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
