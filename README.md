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

---
## 🛠️ Governance Standards
Access the [Governance Skills](docs/standards/) for specialized development rules.

---
## 🤖 AI-Assisted Development
This library includes embedded, machine-readable "skills" to enhance the experience of developers using AI code assistants. These skills help the AI understand our library's APIs and best practices, leading to more accurate and idiomatic code suggestions.

- **AI Skill Discovery**: Look for `META-INF/ai-skills/*.ai-skill.md`

To learn how to add this capability to your own library, see our [AI Skill Publishing Standard](docs/standards/ai-skill-publishing.md).
