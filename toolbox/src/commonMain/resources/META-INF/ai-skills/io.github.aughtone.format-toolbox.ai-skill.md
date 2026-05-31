---
skill-id: io.github.aughtone.format-toolbox
spec-version: "1.0"
type: "Aughtone AI-Skill"
name: "Aughtone Format - Toolbox"
scope: "Foundational utilities, type aliases, and string manipulation for the Aughtone Format library."
compatibility: "Kotlin Multiplatform (KMP)"
author: "Aughtone"
---

# 📖 Aughtone Format - Toolbox AI-Skill

The Toolbox provides the core abstractions and foundational extensions used by other modules in the Aughtone Format ecosystem. It focuses on filling gaps in the Kotlin Multiplatform standard library.

## 🎨 The AI Toolbox

### 1. Functional Formatting (`Formatter`)
A standard type alias for formatting functions used throughout the ecosystem.

- **Primary APIs**:
    - `typealias Formatter<T> = (value: T) -> String`
- **Preference**: Use this alias when defining or passing formatting logic to ensure consistency across the codebase.

### 2. String Interpolation (`String.format`)
A KMP-compatible alternative to `String.format` from Java/Android.

- **Primary APIs**:
    - `String.format(vararg args: Any): String`
- **Preference**: Use this for simple string templates where standard Kotlin string templates (`$var`) are not sufficient (e.g., when the template is externalized or dynamic).
- **Example**: `"Hello %1".format("World")` -> `"Hello World"`
- **Contract**:
    - Placeholders use `%1`, `%2`, etc. (1-based index).
    - It does **not** support advanced printf-style flags (like `%.2f`). Use `toReadable()` from the `readable` module for numeric precision.

### 3. Obfuscation (`obfuscateLast`)
Utilities for hiding sensitive information in strings.

- **Primary APIs**:
    - `String.obfuscateLast(count: Int, obfuscationChar: Char, digitsOnly: Boolean): String`
- **Preference**: Use this for displaying masked phone numbers, credit cards, or IDs in the UI.
- **Contract**:
    - `digitsOnly = true` will filter out non-digits before obfuscating, useful for formatting raw input.
    - If the input is shorter than `count`, the original string is returned (no obfuscation).

## ⚖️ Compliance & Standards

- **ExperimentalMultiplatform**: `String.format` is marked as experimental as it is a partial polyfill for missing platform APIs.

## 🔒 Serialization & Immutability

- **Statelessness**: All extensions are pure functions returning new `String` instances.
- **Immutability**: Does not modify the receiver string.

## 🤖 Agent Onboarding (Usage Rules)

1.  **Placeholder Syntax**: Remember that `String.format` uses 1-based indices (`%1`, `%2`) unlike Java's 0-based or positional syntax.
2.  **Avoid Complex Formatting**: Do not attempt to use `String.format` for complex numeric or date formatting. Delegate to the `readable` or `datetime` modules instead.
3.  **Privacy First**: When displaying identifiers (emails, phone numbers), prefer `obfuscateLast()` to maintain security standards.
