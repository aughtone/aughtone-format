---
skill-id: io.github.aughtone.format-toolbox
name: "[Aughtone Format: Toolbox](https://github.com/aughtone/aughtone-format)"
type: "Aughtone AI-Skill"
scope: core
compatibility: ">=1.0.0"
author: "[Brill Pappin](https://github.com/bpappin)"
---

# AI Skill: Aughtone Toolbox

This library provides low-level primitive extensions and utility functions used across the Aughtone ecosystem.

## 🧰 The AI Toolbox (Key Functions)

### **String Utilities**
- `String.format(vararg args: Any): String`
    - **Usage**: `"Hello %1".format("World")`
    - **Note**: Replaces `%1`, `%2`, etc. with arguments. Useful for basic template substitution in KMP.
- `String.obfuscateLast(count: Int = 4, ...): String`
    - **Usage**: `"12345678".obfuscateLast(4)` -> `••••5678`
    - **Note**: Supports `digitsOnly = true` for credit cards or phone numbers.

### **Formatting Primitives**
- `typealias Formatter<T> = (T) -> String`
    - **Usage**: Define functional formatters for any type `T`.
    - **Example**: `val hex: Formatter<Int> = { it.toString(16) }`

## 📜 Compliance & Standards

- **Time Handling Standards**:
    - **Kotlin 2.1+ Migration**: Always use **`kotlin.time.Instant`** and **`kotlin.time.Clock`** (from the standard library) instead of the legacy `kotlinx.datetime` versions.
    - **Ambiguity Prevention**: NEVER use wildcard imports like `import kotlinx.datetime.*`. This prevents name collisions between `kotlinx.datetime` and `kotlin.time`.
    - **No Type Mixing**: Do not mix `kotlinx.datetime.Instant` and `kotlin.time.Instant` in the same scope or API signature.

## 🤖 Agent Onboarding
1. **Context Registration**: Add this skill file to the `AGENTS.md` of the consuming project.
2. **Usage Rules**:
    - **Security**: Always use `obfuscateLast` for sensitive data like partial card numbers or identifiers in logs/UI.
