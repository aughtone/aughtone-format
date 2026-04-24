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
