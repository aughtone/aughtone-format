# Aughtone Format: Toolbox

The `:toolbox` module provides low-level formatting primitives and shared utilities used by the `:datetime` and `:readable` modules.

## 🚀 Shared Utilities

### Number Formatting
```kotlin
// Locale-aware number separators
val separators = numberFormatterFor(Locale("ka")) // Georgian
println(separators.decimal)  // ","
println(separators.grouping) // " "
```

### Resource Management
The `Resources.kt` file defines the standard **BCP 47 Fallback Chain**:
1. Full tag (e.g., `en-ZA`)
2. Language code (e.g., `en`)
3. Default (usually `en`)

## 🛠️ Primitives
- **`BankersRounding`**: Utilities for unbiased rounding.
- **`MultiplatformFormat`**: Shared interfaces for cross-platform string construction.

---
## 🤖 AI-Assisted Development
This module includes embedded, machine-readable "skills" to enhance the experience of developers using AI code assistants.

- **AI Skill Discovery**: Look for `META-INF/ai-skills/io.github.aughtone.format-toolbox.ai-skill.md`

### 🪄 Magic Prompt for AI Assistants
Use this prompt to inform your AI assistant about the standards and patterns embedded in this library:

> "Scan all project dependencies for AI Skill files located in `META-INF/ai-skills/` with the prefix `io.github.aughtone`. Use these files to understand the coding standards, API patterns, and governance rules for the Aughtone Format library."
