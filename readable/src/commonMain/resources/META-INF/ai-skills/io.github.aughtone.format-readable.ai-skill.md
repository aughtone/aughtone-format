---
skill-id: io.github.aughtone.format-readable
spec-version: "1.0"
type: "Aughtone AI-Skill"
name: "Aughtone Format - Readable"
scope: "Human-readable string formatting for numeric, metric, temporal, and geospatial types."
compatibility: "Kotlin Multiplatform (KMP)"
author: "Aughtone"
---

# 📖 Aughtone Format - Readable AI-Skill

This skill provides a standardized API for converting quantitative and numeric data into localized, human-readable strings. It follows a "presentation-first" philosophy, prioritizing UX consistency across platforms.

## 🎨 The AI Toolbox

### 1. Numeric Formatting (`formatReadable`)
Comprehensive extensions for all Kotlin numeric types (`Double`, `Long`, `Int`, `Short`, `Byte`, `Float` + Unsigned variants).

- **Primary APIs**:
    - `T.formatReadable(locale: Locale, precision: Int): String`
    - `T.formatReadableAbbreviated(locale: Locale, precision: Int): String` (e.g., `1.5k`, `2M`)
- **Preference**: Use `formatReadable()` instead of `toString()` for any value displayed in a UI. Use `formatReadableAbbreviated()` for dashboards or space-constrained labels.
- **Contract**:
    - Default `precision` is `0` for integers and `1` for floating-point types.
    - Abbreviation uses SI metric prefixes (`k`, `M`, `G`, `T`, `P`, `E`, `Z`, `Y`).
    - Values under 1000 in `formatReadableAbbreviated` fall back to standard `formatReadable`.

### 2. Metric & Data Size (`formatReadableMetric`, `formatReadableDataSize`)
Handles SI scaling and IEC binary scaling for storage and physical measurements.

- **Primary APIs**:
    - `T.formatReadableMetric(unit: UnitOfMeasure, locale: Locale, precision: Int): String`
    - `T.formatReadableDataSize(unit: UnitOfMeasure, locale: Locale, precision: Int): String`
- **Preference**: Use `formatReadableDataSize()` specifically for byte counts. Use `formatReadableMetric()` for distance, mass, or power.
- **Contract**:
    - `formatReadableMetric` uses base-1000 scaling (e.g., `1500m` -> `"1.5 km"`).
    - `formatReadableDataSize` uses base-1024 scaling and IEC symbols (e.g., `1024` -> `"1.0 KiB"`).

### 3. Geospatial Formatting (`geo`)
Formatting for Coordinates, Altitude, and Azimuth.

- **Primary APIs**:
    - `Coordinates.formatReadable(format: CoordinateFormat, locale: Locale): String`
    - `Azimuth.formatReadable(locale: Locale, precision: Int): String`
- **Preference**: Prefer `CoordinateFormat.DecimalDegrees` for technical UIs and `DegreesMinutesSeconds` for traditional navigation UIs.
- **Contract**:
    - `Azimuth` output includes cardinal direction indicators (e.g., `"90° (E)"`).
    - Coordinates are automatically suffixed with localized hemisphere indicators (N, S, E, W).

### 4. Temporal, TimeZone & Ordinality (`relative`, `ordinality`, `time`)
Natural language time, timezone naming, and numeric order.

- **Primary APIs**:
    - `Instant.formatReadableRelative(locale, now, style): String`
    - `TimeZone.formatReadable(instant, useFullName, locale): String`
    - `TimeZone.formatReadable(offset, useFullName, locale): String`
    - `T.formatReadableOrdinal(locale): String` (e.g., `"1st"`, `"2nd"`, `"1er"`)
- **Preference**: Use `formatReadableRelative()` for social feeds or "Last Updated" timestamps. Use `TimeZone.formatReadable()` for displaying localized timezone abbreviations (e.g., `"EST"`, `"EDT"`) or full names (e.g., `"Eastern Standard Time"`).
- **Contract**:
    - Ordinality rules are locale-aware (handles English suffixes, French gendered suffixes, etc.).
    - TimeZone formatting requires a reference `Instant` or `UtcOffset` to determine daylight saving time status correctly.

## ⚠️ Deprecated Naming Conventions (Backwards Compatibility)

> [!WARNING]
> The older `toReadable*` formatting functions are deprecated and should not be used in new development. They will be removed in a future major release:
> - `T.toReadable(...)` is deprecated in favor of `T.formatReadable(...)`
> - `T.toReadableAbbreviated(...)` is deprecated in favor of `T.formatReadableAbbreviated(...)`
> - `T.toReadableMetric(...)` is deprecated in favor of `T.formatReadableMetric(...)`
> - `T.toReadableDataSize(...)` is deprecated in favor of `T.formatReadableDataSize(...)`
> - `T.toReadableOrdinal(...)` is deprecated in favor of `T.formatReadableOrdinal(...)`
> - `T.toReadableRelative(...)` is deprecated in favor of `T.formatReadableRelative(...)`
> - `Long.toReadableDuration(...)` is deprecated in favor of `Long.formatReadableDuration(...)`
> - `Duration.toReadableString(...)` is deprecated in favor of `Duration.formatReadable(...)`

## ⚖️ Compliance & Standards

- **ISO 31-0**: Adheres to international standards for numeric separators (e.g., space grouping for French, comma for US).
- **IEC 80000-13**: Uses binary prefixes (Ki, Mi, Gi) for data size as per International Electrotechnical Commission.
- **BCP 47**: Locale resolution follows standard subtag fallback (e.g., `en-ZA` -> `en` -> `default`).
- **WGS84**: Geospatial calculations and formatting assume the WGS84 ellipsoid.

## 🔒 Serialization & Immutability

- **Immutability**: All formatting outputs are `String`. The library operates on immutable types from `io.github.aughtone.types`.
- **Statelessness**: Formatters are stateless and side-effect free.
- **Thread-Safe Caches**: All caches (`numberFormatterCache`, `durationCache`, `configCache`, `ordinalityCache`, `moneyRuleCache`, `moneyFormatterCache`) are thread-safe copy-on-write volatile maps.
- **Cache Size Limit**: All internal caches are capped at a maximum of 150 entries to prevent memory leaks and resource bloat.
- **No-Allocation moneyFormatter**: Money formatter closure uses globally cached number formatters directly with zero local caching allocations.

## 🤖 Agent Onboarding (Usage Rules)

1.  **Strict Locale Import**: ALWAYS import `io.github.aughtone.types.locale.Locale`. **NEVER** use `java.util.Locale` or `android.icu.util.Locale` with these APIs; they are not compatible.
2.  **Contextual Precision**: For financial or high-precision metrics, always specify `precision = 2` or higher. The default `1` is intended for general UI use.
3.  **UI Consistency**: When formatting a list of numbers, ensure all calls use the same `Locale` instance (ideally passed from the View layer) to avoid mixing separator styles.
4.  **Metric Scaling**: Be aware that `formatReadableMetric` only scales units defined in the SI base set (Meter, Gram, Watt, etc.). Custom or non-SI units will be formatted with the unit symbol but without prefix scaling.
5.  **Enum usage**: Use the `Locales` object for common constants (e.g., `Locales.English`, `Locales.German`) in tests or static configurations.
