# Aughtone Format

Aughtone Format is a suite of Kotlin Multiplatform libraries designed to provide consistent, localized formatting for Dates, Times, Numbers, and other human-readable metrics across all platforms.

## 🚀 Major Updates in 3.0.3

Aughtone Format 3.0.3 introduces new modules, API modernizations, performance optimizations, and timezone support:

- **Thread-Safety & Cache Optimizations**: Migrated all resource caches in `:readable` to thread-safe copy-on-write volatile maps capped at 150 entries, and removed nested formatting allocations.
- **Auto-Numbering Fallback**: Added automatic selection of native numbering systems (Arabic, Devanagari, Bengali, Thai, Arabic-Indic) for non-Western locales when formatting dates.
- **iOS Compatibility Target**: Natively compatible with Compose Multiplatform 1.11.1 / Skiko's UIKit requirements by setting iOS deployment target to 16.0.
- **TimeZone Formatting**: Implemented `TimeZone.formatReadable` extension functions in `:readable` to format timezone names with zero allocations.
- **New Vector Modules**: Introduced `:viewable` for platform-agnostic vector graphic representations, styling, and path conversion (SVG, WKT, GeoJSON), and `:viewable-compose` for Jetpack / Compose Multiplatform integration.
- **API Naming Modernization**: Deprecated old `toReadable*` prefix extension functions in favor of unified `formatReadable*` APIs for better readability and style guide alignment.
- **AI-Skills Integration**: Added machine-readable AI skills documentation for all modules to assist coding agents.


## 📦 Core Modules

- **`:datetime`**: Advanced formatting for `kotlinx-datetime` types with multi-locale support.
- **`:readable`**: Human-friendly formatting for metrics (abbreviations), ordinals, and data sizes.
- **`:viewable`**: Platform-agnostic vector graphics representation, styling, and path conversion (GeoJSON, SVG, WKT).
- **`:viewable-compose`**: Jetpack / Compose Multiplatform integration for rendering viewable vector graphics (Painters, ImageVectors).
- **`:toolbox`**: Shared utilities and common formatting primitives.

This project follows a specialized 5-sector documentation hierarchy.

## 📚 Documentation Sectors
- 📐 [Architecture](docs/ARCH.md): Engineering rules and design patterns.
- 🧠 [Functional Specifications](docs/SPEC.md): Business logic and domain constraints.
- 🎨 [Design & UI](docs/DESIGN.md): Presentation layer and user stories.
- 📋 [Acceptance Criteria](docs/ACs/README.md): Success outcomes and verification.
- 📖 [Developer Guide](docs/DEVELOPER.md): Environment setup and onboarding.
- 📜 [Changelog](CHANGELOG.md): History of changes and release notes.
## ✨ Features
- **55+ Languages and Regions Supported**: Deep grammatical parity for Slavic, Arabic, Hebrew, Inuktitut, and more.
- **Relative Time Formatting**: Convert instants and dates into natural language (e.g., "5 minutes ago", "Yesterday").
- **Automatic Fallback**: Smartly switches from relative to absolute formatting based on configurable thresholds.
- **Ordinal Numbers**: Localized ordinal suffix support (e.g., 1st, 2nd, 3.º, 第1).
- **Duration Scaling**: Human-friendly duration strings with perceptual rounding (e.g., "2 weeks" vs "14 days").
- **Numeric Abbreviations**: Scaling large numbers into abbreviated forms (e.g., 1.5K, 1.2M).
- **Geospatial Formatting**: Localized altitude, azimuth (with cardinal directions), and coordinates.

## 🌍 Universal Localization
The library provides deep grammatical parity and full BCP 47 subtag fallback (e.g., `en-ZA` → `en`) for 59 supported language and region combinations:

| Family | Languages & Regions |
| :--- | :--- |
| **Germanic** | Afrikaans (`af-ZA`), Dutch (`nl-NL`), English (`en-US`), South African English (`en-ZA`), German (`de-DE`), Swiss German (`de-CH`), Danish (`da-DK`), Norwegian Bokmål (`nb-NO`), Norwegian Nynorsk (`nn-NO`), Swedish (`sv-SE`), Icelandic (`is-IS`) |
| **Romance** | French (`fr-FR`), Swiss French (`fr-CH`), Spanish (`es-ES`), Italian (`it-IT`), Swiss Italian (`it-CH`), Portuguese (`pt-PT`), Catalan (`ca-ES`), Galician (`gl-ES`), Romanian (`ro-RO`) |
| **Slavic & Baltic** | Russian (`ru-RU`), Ukrainian (`uk-UA`), Belarusian (`be-BY`), Polish (`pl-PL`), Czech (`cs-CZ`), Slovak (`sk-SK`), Bulgarian (`bg-BG`), Croatian (`hr-HR`), Serbian (`sr-RS`), Macedonian (`mk-MK`), Slovenian (`sl-SI`), Lithuanian (`lt-LT`), Latvian (`lv-LV`) |
| **East & SE Asian** | Japanese (`ja-JP`), Chinese (`zh-CN`), Traditional Chinese (`zh-TW`), Korean (`ko-KR`), Thai (`th-TH`), Vietnamese (`vi-VN`), Indonesian (`id-ID`), Malay (`ms-MY`) |
| **South & Central Asian** | Hindi (`hi-IN`), Armenian (`hy-AM`), Georgian (`ka-GE`), Kazakh (`kk-KZ`), Uzbek (`uz-UZ`) |
| **Middle Eastern & African** | Arabic (`ar-EG`), Hebrew (`he-IL`), Persian (`fa-IR`), Turkish (`tr-TR`), Azerbaijani (`az-AZ`), Swahili (`sw-KE`) |
| **Other & Finno-Ugric** | Finnish (`fi-FI`), Estonian (`et-EE`), Hungarian (`hu-HU`), Greek (`el-GR`), Basque (`eu-ES`), Albanian (`sq-AL`), Inuktitut (`iu-CA`) |

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
println(123L.formatReadableOrdinal(Locale("en"))) // "123rd"

// Durations
println(1.5.hours.formatReadable(Locale("en"))) // "1.5 hours"

// Relative Time
println(instant.formatReadableRelative(locale = Locale.current)) // "3 minutes ago"

// Data Sizes
println(1048576L.formatReadableDataSize()) // "1.0 MiB"
```

---
## 🛠️ Governance Standards
Access the [Governance Skills](docs/standards/) for specialized development rules.

---
## 🤖 AI-Assisted Development
This library includes embedded, machine-readable "skills" to help AI assistants understand its APIs and best practices.

- **Discovery**: Look for `META-INF/ai-skills/*.ai-skill.md`

### 🪄 Magic Prompt for AI Assistants
If you are using an AI assistant (like Claude, Gemini, or ChatGPT) to write code with this library, paste this prompt first:

> "Scan all project dependencies for AI Skill files in `META-INF/ai-skills/` with the prefix `io.github.aughtone`. Use these to understand the API patterns, types, and governance for this library. If they are not found in the local classpath, refer to the [Aughtone Format GitHub repository](https://github.com/aughtone/aughtone-format) for the source definitions."

To learn how to add this capability to your own library, see our [AI Skill Publishing Standard](docs/standards/ai-skill-publishing.md).
