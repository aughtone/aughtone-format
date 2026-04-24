# Aughtone Format

Aughtone Format is a suite of Kotlin Multiplatform libraries designed to provide consistent, localized formatting for Dates, Times, Numbers, and other human-readable metrics across all platforms.

## ⚠️ Breaking Changes in 2.1.0

Version 2.1.0 introduces significant structural changes and hardening:

- **Library Split**: Metric-specific formatting (Ordinals, Durations, Relative Time, Data Sizes) has been moved from the core library into the new **`:readable`** module.
- **`kotlin.time` Hardening**: All Duration and Relative Time APIs have been refactored to use standard `kotlin.time.Duration` types instead of `Long` milliseconds for improved type safety and consistency.
- **Version Synchronization**: This release aligns the versioning across the Aughtone ecosystem.

## 📦 Core Modules

- **`:datetime`**: Advanced formatting for `kotlinx-datetime` types with multi-locale support.
- **`:readable`**: Human-friendly formatting for metrics (abbreviations), ordinals, and data sizes.
- **`:toolbox`**: Shared utilities and common formatting primitives.

This project follows a specialized 5-sector documentation hierarchy.

## 📚 Documentation Sectors
- 📐 [Architecture](docs/ARCH.md): Engineering rules and design patterns.
- 🧠 [Functional Specifications](docs/SPEC.md): Business logic and domain constraints.
- 🎨 [Design & UI](docs/DESIGN.md): Presentation layer and user stories.
- 📋 [Acceptance Criteria](docs/ACs/README.md): Success outcomes and verification.
- 📖 [Developer Guide](docs/DEVELOPER.md): Environment setup and onboarding.
## ✨ Features
- **65+ Languages Supported**: Deep grammatical parity for Slavic, Arabic, Hebrew, Inuktitut, and more.
- **Relative Time Formatting**: Convert instants and dates into natural language (e.g., "5 minutes ago", "Yesterday").
- **Automatic Fallback**: Smartly switches from relative to absolute formatting based on configurable thresholds.
- **Ordinal Numbers**: Localized ordinal suffix support (e.g., 1st, 2nd, 3.º, 第1).
- **Duration Scaling**: Human-friendly duration strings with perceptual rounding (e.g., "2 weeks" vs "14 days").
- **Numeric Abbreviations**: Scaling large numbers into abbreviated forms (e.g., 1.5K, 1.2M).
- **Geospatial Formatting**: Localized altitude, azimuth (with cardinal directions), and coordinates.

## 🌍 Universal Localization
The library provides deep grammatical parity for **65+ core languages** and full BCP 47 subtag fallback (e.g., `en-ZA` → `en`).

## 🚀 Quick Usage

### Datetime Formatting (`:datetime`)
```kotlin
val now = Clock.System.now()
// Format with styles (Short, Medium, Long, Full)
println(now.format(DateTimeStyle.Medium, locale = Locale("en"))) // "Apr 23, 2026, 4:15 PM"
```

### Human-Readable Metrics (`:readable`)
```kotlin
// Ordinals
println(123L.toReadableOrdinal(Locale("en"))) // "123rd"

// Durations
println(1.5.hours.toReadableString(Locale("en"))) // "1.5 hours"

// Relative Time
println(instant.readableRelative(locale = Locale.current)) // "3 minutes ago"

// Data Sizes
println(1048576L.toReadableDataSize()) // "1.0 MiB"
```

---
## 🛠️ Governance Standards
Access the [Governance Skills](docs/standards/) for specialized development rules.

---
## 🤖 AI-Assisted Development
This library includes embedded, machine-readable "skills" to enhance the experience of developers using AI code assistants. These skills help the AI understand our library's APIs and best practices, leading to more accurate and idiomatic code suggestions.

- **AI Skill Discovery**: Look for `META-INF/ai-skills/*.ai-skill.md`

To learn how to add this capability to your own library, see our [AI Skill Publishing Standard](docs/standards/ai-skill-publishing.md).
