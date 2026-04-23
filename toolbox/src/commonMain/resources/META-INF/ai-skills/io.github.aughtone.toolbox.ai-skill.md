---
skill-id: io.github.aughtone.toolbox
name: "[AughtOne Format: Toolbox](https://github.com/aughtone/aughtone-format)"
type: "AughtOne AI-Skill"
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

## 🤖 Agent Onboarding
1. **Context Registration**: Add this skill file to the `AGENTS.md` of the consuming project.
2. **Usage Rules**:
    - **Security**: Always use `obfuscateLast` for sensitive data like partial card numbers or identifiers in logs/UI.
