# Aughtone Format

Aughtone Format is a suite of Kotlin Multiplatform libraries designed to provide consistent, localized formatting for Dates, Times, Numbers, and other human-readable metrics across all platforms.

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
## 🌍 Universal Localization
The library provides deep grammatical parity for **55 core languages** (Slavic, Arabic, Hebrew, Inuktitut, etc.) and full BCP 47 subtag fallback (e.g., `en-ZA` → `en`).

## 🚀 Quick Usage

### Datetime Formatting (`:datetime`)
```kotlin
val now = Clock.System.now()
// Format with styles (Short, Medium, Long, Full)
println(now.format(FormatStyle.MEDIUM, locale = Locale("en"))) // "Apr 23, 2026, 4:15 PM"
```

### Human-Readable Metrics (`:readable`)
```kotlin
// Ordinals
println(123L.toReadableOrdinal(Locale("en"))) // "123rd"

// Durations
println(1.5.hours.toReadableString(Locale("en"))) // "1.5 hours"

// Relative Time
println(instant.toReadableRelative(Locale.current)) // "3 minutes ago"

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
