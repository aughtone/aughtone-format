# Architecture Guidelines

## 📦 Distribution & Publishing

Aughtone Format is a suite of Kotlin Multiplatform libraries published to **Maven Central** using the `com.vanniktech.maven-publish` plugin.

### Coordinates
- **Group**: `io.github.aughtone`
- **Artifacts**: 
    - `format-datetime` (Core datetime formatting)
    - `format-readable` (Human-readable metrics & ordinals)
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
- **Boundedness**: Caches are limited to **100 entries** to prevent memory leaks in long-running processes, easily covering the **55 core locales** supported by the library.
- **Zero allocation** for locales that are never used at runtime.
### 7. Grammatical Parity & Plural Categorization
The library implements a **Unicode CLDR-compliant Plural Category Engine** (`PluralCategory.kt`).
- **Classification**: Logic to determine if a number belongs to `Zero`, `One`, `Two`, `Few`, `Many`, or `Other` is centralized.
- **Factory Pattern**: Resource files use standard factories (`u2`, `u3`, `u4`, `u6`) to map these categories to pluralized strings based on language complexity.
- **Ordinal Categories**: Support for language-specific ordinal classification (e.g., French "er/re" vs English "st/nd/rd").
- **Stateless Grammars**: Plural logic is passed into formatters at construction, keeping the formatting loop extremely fast and zero-allocation.
