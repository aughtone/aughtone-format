---
skill-id: io.github.aughtone.toolbox
scope: core
compatibility: ">=1.0.0"
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

### **Numeric Utilities**
- `Long.toHumanReadableString(): String`
    - **Usage**: `1500L.toHumanReadableString()` -> `"1.5K"`
    - **Note**: Uses metric prefixes (K, M, G, T, P, E).
- `Double.formatCurrency(scale: Int = 2): String`
    - **Usage**: `12.345.formatCurrency(2)` -> `"$12.35"`

## 🤖 Agent Onboarding
1. **Context Registration**: Add this skill file to the `AGENTS.md` of the consuming project.
2. **Usage Rules**:
    - **Minimalism**: Use these extensions for basic UI formatting where a full localized formatter (from `aughtone-format`) is not required.
    - **Security**: Always use `obfuscateLast` for sensitive data like partial card numbers or identifiers in logs/UI.
