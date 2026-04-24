# Acceptance Criteria: Datetime Resource Optimization

This document defines the goals and requirements for optimizing the resource infrastructure of the `:datetime` module.

---

## Story: Resource Efficiency
> **I want my resources to be as efficent as possible so that I dont use a lot of memory or take a lot of time during lookups.**

### Acceptance Criteria
- **AC: Single-Locale Optimization.** Normally, only one resource will be uised at a time since the system is generally goign to be formating date for a specific locale, so we should optimimze for that.
- **AC: Lazy Loading & Caching.** We want tpo be as lazy loading as posssible, but once loaded the result should be cached for quick lookups. so that we can reuse the most likely data we have.
- **AC: Lookup Result Caching.** The result of a multi-step fallback lookup (e.g., `en-CA` -> `CA` -> `en`) must be cached at the requested key level. Subsequent requests for `en-CA` should return the cached resource immediately without re-calculating the fallback chain.
- **AC: Locale Key Normalization.** All locale lookups must normalize the requested tag (e.g., converting `en_US` or `EN-us` to a standard format) before accessing the cache to ensure consistent hits. The system should accept a `Locale` object with can format this properly, so we may not need extra code for this.
- **AC: Zero Reflection.** Resource loading must not use reflection, ensuring high performance and compatibility with code-shrinking tools (R8/ProGuard) and GraalVM.
- **AC: Thread Safety.** The caching mechanism must be thread-safe across all Kotlin Multiplatform targets (Android, iOS, JVM, etc.), preventing race conditions during lazy initialization of resources.
- **AC: Granular Lazy Loading.** Instead of using `lazy` for the entire map of strings, individual locale resources should be loaded only when requested. This ensures that memory usage is strictly limited to the locales actually used by the app.

---

## Story: Global Language Alignment
> **As a developer, i want to support the same 51 languages that the readable module supports.**

### Acceptance Criteria
- **AC: Resource Completeness.** For all 51 supported languages, a "smoke test" must verify that every resource type (AM/PM strings, Date/Time patterns, Day/Month names, and Eras) exists or has a valid fallback. There should be no `NoSuchElementException` during a lookup for any supported language.
- **AC: Fallback Integrity.** Every lookup chain must ultimately terminate at a hardcoded "Root" or "Default" resource (e.g., `en-CA`) to ensure the system never crashes due to a missing locale. WE can improve this by using `Locale` inputs.
- **AC: Localized Numbering Systems.** Support for non-Western digits (e.g., Arabic-Indic) should be considered. The default for a locale should be what that locale expects, while providing an option to force Western digits if needed.

---

## Story: AI & Developer Experience
> **As a developer, I want my AI to understand the how this library works automatically, so it makes fewer mistakes.**

### Acceptance Criteria
- **AC: Machine-Readable Skills.** Update the `META-INF/ai-skills` documentation for the module to reflect the new caching and lookup patterns so that future agent interactions remain consistent with these optimizations.

---

## Story: LocalDate Formatting Styles
> **As a developer, I want to be format an LocalDate as a string in Short, Medium, Long, Full, or None format, so that I can display the date for the user.**

### Acceptance Criteria
- **AC: Formatting Styles.** The format styles are defined in `io.github.aughtone.datetime.format.DateTimeStyle`.
- **AC: Flexible Parameters.** When formatting, I will have the option to specify the style and the `Locale`.
- **AC: Default Locale Logic.** The default locale will be selected from `Locale.getCurrent()` and fall back to `en-CA`.

---

## Story: LocalTime Formatting Styles
> **As a developer, I want to be format an LocalTime as a string in Short, Medium, Long, Full, or None format, so that I can display the time for the user.**

### Acceptance Criteria
- **AC: Formatting Styles.** The format styles are defined in `io.github.aughtone.datetime.format.DateTimeStyle`.
- **AC: Flexible Parameters.** When formatting, I will have the option to specify the style, the `Locale`, and if I want 12 or 24-hour format.
- **AC: Default Locale Logic.** The default locale will be selected from `Locale.getCurrent()` and fall back to `en-CA`.
- **AC: Default 24-Hour Logic.** The default 24-hour flag will be set from `is24HourFormat` with the specified locale.

---

## Story: LocalDateTime Formatting Styles
> **As a developer, I want to be format an LocalDateTime as a string in Short, Medium, Long, Full, or None format, so that I can display the date and time for the user.**

### Acceptance Criteria
- **AC: Formatting Styles.** The format styles are defined in `io.github.aughtone.datetime.format.DateTimeStyle`.
- **AC: Flexible Parameters.** When formatting, I will have the option to specify the date style, the time style, the `Locale`, and if I want 12 or 24-hour format.
- **AC: Default Locale Logic.** The default locale will be selected from `Locale.getCurrent()` and fall back to `en-CA`.
- **AC: Default 24-Hour Logic.** The default 24-hour flag will be set from `is24HourFormat` with the specified locale.
- **AC: Delegation.** The formatting can be delegated to the `LocalDate` format and the `LocalTime` format.

---

## Story: Resource Lookup Specificity
> **As a developer, when i look up a resource, I want to do it in the most efficient way possible, so that I am not spending extra cycles finding resources.**

### Acceptance Criteria
- **AC: Specific-to-Least-Specific.** Resource lookup should proceed from most specific to least specific.
- **AC: Adaptive Optimization.** The order of language or region may be optimized depending on the resource but should favor more specific matches first to catch outliers.

---

## Story: Default TimeZone Assumptions
> **As a developer, I want LocalDate and LocalTime to be assumed to be in the user's current TimeZone, so that display makes sense to the user looking at the screen.**

### Acceptance Criteria
- **AC: System Default TimeZone.** The user's time zone can be found with `kotlinx.datetime.TimeZone.currentSystemDefault()`.
- **AC: Current LocalDateTime.** The user's `LocalDateTime` can be found with `Clock.System.now().toLocalDateTime(currentTimeZone)`.
- **AC: Derived LocalDate.** The `LocalDate` can be found as a property of the `LocalDateTime`. (e.g., `Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date`).

---

## Story: Instant Formatting Styles
> **As a developer, I want to be able format an Instant as a string in Short, Medium, Long, Full, or None format, so that I can display the date and time for the user.**

### Acceptance Criteria
- **AC: Formatting Styles.** The format styles are defined in `io.github.aughtone.datetime.format.DateTimeStyle`.
- **AC: Flexible Parameters.** When formatting, I will have the option to specify the date style, the time style, the `Locale`, the `TimeZone`, and if I want 12 or 24-hour format.
- **AC: Default Locale Logic.** The default locale will be selected from `Locale.getCurrent()` and fall back to `en-CA`.
- **AC: Default 24-Hour Logic.** The default 24-hour flag will be set from `is24HourFormat` with the specified locale.
- **AC: Default TimeZone Logic.** The default `TimeZone` will be populated from `kotlinx.datetime.TimeZone.currentSystemDefault()`.

---

## Story: Era Formatting & Overrides
> **As a developer, I want the date formatting to be assumed to be in the common era, but I would like an overloaded format function that allows me to override it.**

### Acceptance Criteria
- **AC: Default Era.** Assume CE (Common Era) for formatting by default.
- **AC: Era Overrides.** Provide an override for `LocalDateTime` and `Instant` that includes the era in the output.
- **AC: Global Nomenclature.** The era should be translated into the common nomenclature for all 51 supported languages.
- **AC: Optimized Era Resources.** The era resource should be optimized so that outliers are explicitly specified while defaulting to the most common nomenclature where possible.

---

## Story: Relative Time Migration
> **All relative time formatting in the datetime module should be removed, or moved to the readable module, if it doesnt already exist there.**

### Acceptance Criteria
- **AC: External Scope.** We will define the stories and AC for relative datetime in the `readable` module.

---

## Story: Technical Refinements & Integrity
> **As a developer, I want the resource system to be robust, sanitized, and decoupled to ensure long-term maintainability.**

### Acceptance Criteria
- **AC: Cache Boundedness.** Ensure the resolved resource cache does not grow indefinitely; while 51 languages are manageable, the system should be resilient to excessive locale variant lookups.
- **AC: Pattern Sanitization.** CLDR patterns must be sanitized during the lookup/caching phase to strip or handle unsupported tokens (e.g., flexible day periods) before they reach the formatter.
- **AC: Normalization Excellence.** Key normalization must handle case sensitivity and separator differences (e.g., `en_US` vs `en-US`) to ensure cache consistency across all platforms.
- **AC: Target-Aware Thread Safety.** Use thread-safe map implementations or atomic references for caching that are compatible with the Kotlin/Native (iOS) memory model.
- **AC: Dependency Minimization.** Once relative time formatting is moved to `:readable`, the `:datetime` module should no longer require a dependency on `:readable`, eliminating potential circular dependencies.
- **AC: Hardcoded ISO-8601 Root.** Every lookup chain must ultimately fall back to hardcoded ISO-8601 patterns to ensure the system remains functional even if all localized resources are missing.
