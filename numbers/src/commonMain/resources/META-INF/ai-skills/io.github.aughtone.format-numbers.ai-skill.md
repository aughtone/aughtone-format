---
skill-id: io.github.aughtone.format-numbers
scope: core
compatibility: ">=1.0.0"
---

# AI Skill: Aughtone Format (Numbers)

This library provides localized numeric and currency formatting for Kotlin Multiplatform.

## 🧰 The AI Toolbox (Planned/Core Functions)

### **Numeric Formatting**
- [Intent] This library is intended to handle localized decimal, percent, and scientific notation formatting using `aughtone-types` Locales.

### **Currency Formatting**
- [Intent] Provides advanced currency formatting beyond the basic `toolbox` implementation, incorporating **ISO 4217** rules and locale-specific symbol placement.

## 📜 Compliance & Standards
- **Locales**: Uses `aughtone-types` (BCP 47).
- **Precision**: Adheres to **Banker's Rounding** (half-to-even) standards defined in `aughtone-types`.

## 🤖 Agent Onboarding
1. **Context Registration**: Add this skill file to the `AGENTS.md` of the consuming project.
2. **Note**: This library is currently undergoing expansion. For simple currency formatting, refer to `io.github.aughtone.toolbox`.
