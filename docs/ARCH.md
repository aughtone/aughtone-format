# Architecture Guidelines

## 📦 Distribution & Publishing

Aughtone Format is a suite of Kotlin Multiplatform libraries published to **Maven Central** using the `com.vanniktech.maven-publish` plugin.

### Coordinates
- **Group**: `io.github.aughtone`
- **Artifacts**: 
    - `format-datetime` (Core datetime formatting)
    - `format-readable` (Human-readable metrics & ordinals)
    - `format-viewable` (Abstract visual models)
    - `format-viewable-compose` (Compose rendering)
    - `format-datetime-native` (Native platform formatting - optional)
- **Version**: Managed via `libs.versions.versionName`

### Infrastructure
- **Plugin**: `com.vanniktech.maven-publish`
- **Target**: Maven Central (OSSRH)
- **Signing**: Mandatory GPG signing (can be bypassed with `-Pskip-signing` for local builds).

---

## 📐 Library Design Principles

### 1. Minimal Transitive Dependencies
The core formatting modules (`datetime`, `readable`) are designed to be lightweight. Heavy dependencies are isolated into separate modules (e.g., `:datetime-native`).

### 2. Pure KMP Resolution
Formatting rules are implemented in `commonMain` using a **Functional Formatter Pattern** (`Formatter<T>`).
- **Immutable Lambdas**: Resource maps return "baked" lambdas that encapsulate all locale-specific rules and unit names.
- **Redux Safety**: Formatter instances are stateless and idempotent, making them ideal for UDF architectures.

### 3. Resource Isolation
Resources (unit names, separators, ordinal suffixes) are isolated within the `:readable` module. 
- **Frozen Types**: We strictly avoid modifying the `:types` package (which is published and frozen). All localization data is "side-loaded" via the `:readable` resource maps.

### 4. Locale Centricity
All formatting logic strictly consumes `io.github.aughtone.types.locale.Locale` to ensure consistent BCP 47 compliance and avoid platform-specific locale quirks. Defaulting to `Locale.current` is handled at the extension level.

### 5. BCP 47 Subtag Resolution
All formatters use a **subtag fallback chain** for locale lookup:
1. Try the full tag (`en-ZA`, `zh-TW`, `de-CH`).
2. Strip the last subtag and retry (`en-ZA` → `en`).
3. Fall back to English (`en`) as the final default.

This means regional variants only need to be registered when they genuinely differ from the base language. All other region codes (e.g., `fr-CA`, `pt-BR`) automatically inherit the correct base language rules.

### 6. Lazy Resource Caching
All resource maps use a **thread-safe, on-demand caching** pattern:
- A `buildX(tag: String)` factory function constructs a formatter only when first requested.
- The result is stored in a **`@Volatile` immutable map** which is replaced upon new entries.
- **Boundedness**: Caches are limited to **150 entries** (increased from 100) to prevent memory leaks in long-running processes, comfortably covering the **65+ core locales** supported by the library.
- **Zero allocation** for locales that are never used at runtime.
### 7. Grammatical Parity & Plural Categorization
The library implements a **Unicode CLDR-compliant Plural Category Engine** (`PluralCategory.kt`).
- **Classification**: Logic to determine if a number belongs to `Zero`, `One`, `Two`, `Few`, `Many`, or `Other` is centralized.
- **Factory Pattern**: Resource files use standard factories (`u2`, `u3`, `u4`, `u6`) to map these categories to pluralized strings based on language complexity.
- **Ordinal Categories**: Support for language-specific ordinal classification (e.g., French "er/re" vs English "st/nd/rd").
- **Stateless Grammars**: Plural logic is passed into formatters at construction, keeping the formatting loop extremely fast and zero-allocation.

---

## 🏛️ Architectural Decision Records (ADRs)

### ADR-001: Formatting API Naming Standardization
* **Date**: 2026-05-30
* **Status**: Accepted
* **Context**: Previously, formatting extension functions used a mix of `toReadable*`, `readable*`, and other naming patterns. The `to*` prefix conventionally implies cast-like conversions in Kotlin (e.g., `toInt()`, `toInstant()`), which is semantically different from formatting a data type for visual/human representation.
* **Decision**: 
  - Standardized all formatting APIs in the `:readable` module to use the `formatReadable*` naming convention (e.g., `formatReadable`, `formatReadableOrdinal`, `formatReadableRelative`, `formatReadableDataSize`, `formatReadableDuration`).
  - Deprecated all older `toReadable*` functions.
  - Retained the old functions as stubs annotated with `@Deprecated(message, replaceWith)` for automated IDE-assisted migration, ensuring full backwards compatibility.

### ADR-002: Currency Nullability Enforcement in Money Formatting
* **Date**: 2026-05-30
* **Status**: Accepted
* **Context**: With the upgrade of the core `aughtone-types` library dependency to version `3.0.0`, the `Currency` parameter inside `Money` is strictly non-nullable.
* **Decision**: 
  - Updated the `:readable` module's monetary formatting implementation to assume `currency` is always present and non-null on `Money` instances.
  - Removed redundant null-checks (e.g., `currency?.digits`, `currency != null`) from the formatting engine.
  - Removed the obsolete `testNullCurrencyFallback` test case because it is no longer expressible or compilable under the type system.

### ADR-003: Zero-Allocation Localized TimeZone Formatting
* **Date**: 2026-06-02
* **Status**: Accepted
* **Context**: Implementing localized timezone formatting (names and abbreviations) for all 55+ supported languages requires resolving dozens of timezone keys under each locale. Storing these inside dynamic, lazy-loaded Map structures or utilizing the existing runtime cache system in `Resources.kt` would introduce substantial memory allocation and synchronization overhead. Furthermore, large inline maps of strings in a single class can exceed the JVM 64KB method size limit.
* **Decision**:
  - Implemented `TimeZoneNamesLookup` using a compile-time static nested `when` expression pattern, completely eliminating dynamic Map creation and ensuring zero runtime object allocations.
  - Divided lookups into language-specific private helper methods (e.g., `getFullNameFr`, `getAbbreviationEn`) to keep each individual method under the JVM 64KB bytecode size limit.
  - Removed timezone caching entirely from `Resources.kt` since the static lookup functions execute with zero garbage collection overhead and excellent performance.
  - Integrated lookup with `TimeZoneAbbreviationLookup` and the core post-formatting pipeline, enabling locale-specific short/long timezone rendering in dates.


