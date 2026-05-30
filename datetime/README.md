# Aughtone Format: Datetime

The `:datetime` module provides advanced, locale-aware formatting for `kotlinx-datetime` types. It achieves full parity with the Unicode CLDR for month names, day names, and structured date/time patterns across **65+ languages**.

## 🚀 Usage

### Formatting Instants
```kotlin
val instant = Clock.System.now()

// Standard styles
instant.format(FormatStyle.SHORT, locale = Locale.current)  // 4/23/26, 4:15 PM
instant.format(FormatStyle.MEDIUM, locale = Locale("en")) // Apr 23, 2026, 4:15 PM
instant.format(FormatStyle.LONG, locale = Locale("en"))   // April 23, 2026 at 4:15:37 PM
```

### Advanced Patterns
This module supports 24-hour master strategies, localized AM/PM markers, and era naming overrides.

> [!NOTE]
> For natural language "time ago" formatting, use the `toReadableRelativeTime()` extensions from the [**:readable**](../readable) module.

## 🌍 Language Support
Supports 65+ core languages with regional variant fallback.
- **Slavic**: Correct grammatical handling for dates.
- **Semitic**: Full support for right-to-left scripts.
- **CJK**: Character-based representation (Chinese, Japanese, Korean).

## 📐 Architecture
This module is **stateless**. Formatters are built as immutable lambdas, ensuring thread safety and performance in UDF/Redux architectures.

---
## 🤖 AI-Assisted Development
This library includes embedded, machine-readable "skills" to help AI assistants understand its APIs and best practices.

- **Discovery**: Look for `META-INF/ai-skills/io.github.aughtone.format-datetime.ai-skill.md`

### 🪄 Magic Prompt for AI Assistants
If you are using an AI assistant (like Claude, Gemini, or ChatGPT) to write code with this library, paste this prompt first:

> "Scan all project dependencies for AI Skill files in `META-INF/ai-skills/` with the prefix `io.github.aughtone`. Use these to understand the API patterns, types, and governance for this library. If they are not found in the local classpath, refer to the [Aughtone Format GitHub repository](https://github.com/aughtone/aughtone-format) for the source definitions."
