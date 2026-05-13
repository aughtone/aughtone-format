---
skill-id: io.github.aughtone.format-readable
spec-version: 1.0
name: "Aughtone Format - Readable"
type: "Aughtone AI-Skill"
scope: "Human-readable string formatting for numeric, metric, temporal, and geospatial types."
compatibility: "Kotlin Multiplatform (KMP)"
author: "Aughtone"
---

# 📖 Aughtone Format - Readable AI-Skill

This skill provides a standardized API for converting quantitative and numeric data into localized, human-readable strings. It follows a "presentation-first" philosophy, prioritizing UX consistency across platforms.

## 🎨 The AI Toolbox

### 1. Numeric Formatting (`toReadable`)
Comprehensive extensions for all Kotlin numeric types (`Double`, `Long`, `Int`, `Short`, `Byte`, `Float` + Unsigned variants).

- **Primary APIs**:
    - `T.toReadable(locale: Locale, precision: Int): String`
    - `T.toReadableAbbreviated(locale: Locale, precision: Int): String` (e.g., `1.5k`, `2M`)
- **Preference**: Use `toReadable()` instead of `toString()` for any value displayed in a UI. Use `toReadableAbbreviated()` for dashboards or space-constrained labels.
- **Contract**:
    - Default `precision` is `0` for integers and `1` for floating-point types.
    - Abbreviation uses SI metric prefixes (`k`, `M`, `G`, `T`, `P`, `E`, `Z`, `Y`).
    - Values under 1000 in `toReadableAbbreviated` fall back to standard `toReadable`.

### 2. Metric & Data Size (`toReadableMetric`, `toReadableDataSize`)
Handles SI scaling and IEC binary scaling for storage and physical measurements.

- **Primary APIs**:
    - `T.toReadableMetric(unit: UnitOfMeasure, locale: Locale, precision: Int): String`
    - `T.toReadableDataSize(unit: UnitOfMeasure, locale: Locale, precision: Int): String`
- **Preference**: Use `toReadableDataSize()` specifically for byte counts. Use `toReadableMetric()` for distance, mass, or power.
- **Contract**:
    - `toReadableMetric` uses base-1000 scaling (e.g., `1500m` -> `"1.5 km"`).
    - `toReadableDataSize` uses base-1024 scaling and IEC symbols (e.g., `1024` -> `"1.0 KiB"`).

### 3. Geospatial Formatting (`geo`)
Formatting for Coordinates, Altitude, and Azimuth.

- **Primary APIs**:
    - `Coordinates.formatReadable(format: CoordinateFormat, locale: Locale): String`
    - `Azimuth.formatReadable(locale: Locale, precision: Int): String`
- **Preference**: Prefer `CoordinateFormat.DecimalDegrees` for technical UIs and `DegreesMinutesSeconds` for traditional navigation UIs.
- **Contract**:
    - `Azimuth` output includes cardinal direction indicators (e.g., `"90° (E)"`).
    - Coordinates are automatically suffixed with localized hemisphere indicators (N, S, E, W).

### 4. Temporal & Ordinality (`relative`, `ordinality`)
Natural language time and numeric order.

- **Primary APIs**:
    - `Instant.toReadableRelative(locale, now, style): String`
    - `T.toReadableOrdinal(locale): String` (e.g., `"1st"`, `"2nd"`, `"1er"`)
- **Preference**: Use `toReadableRelative()` for social feeds or "Last Updated" timestamps.
- **Contract**:
    - Ordinality rules are locale-aware (handles English suffixes, French gendered suffixes, etc.).

## ⚖️ Compliance & Standards

- **ISO 31-0**: Adheres to international standards for numeric separators (e.g., space grouping for French, comma for US).
- **IEC 80000-13**: Uses binary prefixes (Ki, Mi, Gi) for data size as per International Electrotechnical Commission.
- **BCP 47**: Locale resolution follows standard subtag fallback (e.g., `en-ZA` -> `en` -> `default`).
- **WGS84**: Geospatial calculations and formatting assume the WGS84 ellipsoid.

## 🔒 Serialization & Immutability

- **Immutability**: All formatting outputs are `String`. The library operates on immutable types from `io.github.aughtone.types`.
- **Statelessness**: Formatters are stateless and side-effect free.
- **Cache**: `numberFormatterFor` uses an internal on-demand cache keyed by `(Locale, Precision)`.

## 🤖 Agent Onboarding (Usage Rules)

1.  **Strict Locale Import**: ALWAYS import `io.github.aughtone.types.locale.Locale`. **NEVER** use `java.util.Locale` or `android.icu.util.Locale` with these APIs; they are not compatible.
2.  **Contextual Precision**: For financial or high-precision metrics, always specify `precision = 2` or higher. The default `1` is intended for general UI use.
3.  **UI Consistency**: When formatting a list of numbers, ensure all calls use the same `Locale` instance (ideally passed from the View layer) to avoid mixing separator styles.
4.  **Metric Scaling**: Be aware that `toReadableMetric` only scales units defined in the SI base set (Meter, Gram, Watt, etc.). Custom or non-SI units will be formatted with the unit symbol but without prefix scaling.
5.  **Enum usage**: Use the `Locales` object for common constants (e.g., `Locales.English`, `Locales.German`) in tests or static configurations.
