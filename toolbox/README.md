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
This library includes embedded, machine-readable "skills" to help AI assistants understand its APIs and best practices.

- **Discovery**: Look for `META-INF/ai-skills/io.github.aughtone.format-toolbox.ai-skill.md`

### 🪄 Magic Prompt for AI Assistants
If you are using an AI assistant (like Claude, Gemini, or ChatGPT) to write code with this library, paste this prompt first:

> "Scan all project dependencies for AI Skill files in `META-INF/ai-skills/` with the prefix `io.github.aughtone`. Use these to understand the API patterns, types, and governance for this library. If they are not found in the local classpath, refer to the [Aughtone Format GitHub repository](https://github.com/aughtone/aughtone-format) for the source definitions."
