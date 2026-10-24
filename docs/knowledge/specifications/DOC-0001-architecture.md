# Architecture

DOC-0001 · 2026-04-19
Keywords: module layout, which artifact to depend on, Maven coordinates, how locales are
          resolved, BCP 47 fallback, en-ZA falls back to en, resource caching, memory use,
          plural categories, formatter lambdas, why no java.time

## 📦 Distribution & Publishing

Aughtone Format is a suite of Kotlin Multiplatform libraries published to **Maven Central** using the `com.vanniktech.maven-publish` plugin.

### Coordinates
- **Group**: `io.github.aughtone`
- **Artifacts**:
    - `format-datetime` (dates, times, ranges, and weekday, month and zone names)
    - `format-readable` (human-readable numbers, durations, relative time, ordinals and lists)
    - `format-identifiers` (display notations for identifiers: MAC, UUID, IBAN, IP, domains, phones)
    - `format-astronomy` (sunrise, sunset and twilight, moon phases, solar terms and crescent visibility)
    - `format-calendars` (conversion and arithmetic for the non-Gregorian calendars RFC 7529 names; depends on `format-astronomy`)
    - `format-toolbox` (shared helpers)
    - `format-viewable` (abstract visual models)
    - `format-viewable-compose` (Compose rendering)
- **Version**: Managed via `libs.versions.versionName`

### Infrastructure
- **Plugin**: `com.vanniktech.maven-publish`
- **Target**: Maven Central (OSSRH)
- **Signing**: Mandatory GPG signing (can be bypassed with `-Pskip-signing` for local builds).

---

## 📐 Library Design Principles

### 1. Minimal Transitive Dependencies
The core formatting modules (`datetime`, `readable`) are designed to be lightweight. Heavy dependencies are isolated into separate modules. A module depends only on what its own code uses: Compose, in particular, belongs to `viewable-compose` and to any future module made for it, never to a formatting module, so a server or command-line consumer never receives a UI toolkit.

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

## Decisions

The choices behind this architecture are recorded as decisions: [formatting API naming](../decisions/ADR-0001-formatting-api-naming.md), [non-null currency in money formatting](../decisions/ADR-0002-currency-is-never-null-in-money-formatting.md), [time-zone names](../decisions/ADR-0005-time-zone-names-from-the-platform.md) (superseding [the static lookup](../decisions/ADR-0003-static-time-zone-name-lookup.md)), and [wordings kept where they differ from CLDR](../decisions/ADR-0004-keep-everyday-wordings-that-differ-from-cldr.md).
