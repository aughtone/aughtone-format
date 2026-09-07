# Aughtone Format: Datetime

The `:datetime` module provides advanced, locale-aware formatting for `kotlinx-datetime` types. It achieves full parity with the Unicode CLDR for month names, day names, and structured date/time patterns across **65+ languages**.

## 🚀 Usage

### Formatting Instants
```kotlin
val instant = Clock.System.now()

// Standard styles
instant.format(FormatStyle.SHORT, locale = Locale("en-US")) // 4/23/26, 4:15 PM
instant.format(FormatStyle.SHORT, locale = Locale("en-CA")) // 2026-04-23, 4:15 p.m.
instant.format(FormatStyle.MEDIUM, locale = Locale.current) // Apr 23, 2026, 4:15 PM
instant.format(FormatStyle.LONG, locale = Locale.current)   // April 23, 2026 at 4:15:37 PM
```

### Advanced Patterns
This module supports 24-hour master strategies, localized AM/PM markers, and era naming overrides.

> [!NOTE]
> For natural language "time ago" formatting, use the `formatReadableRelative()` extensions from the [**:readable**](../readable) module.

## 🌍 Language Support
Supports 65+ core languages with regional variant fallback.
- **Slavic**: Correct grammatical handling for dates.
- **Semitic**: Full support for right-to-left scripts.
- **CJK**: Character-based representation (Chinese, Japanese, Korean).

## 📐 Architecture
This module is **stateless**. Formatters are built as immutable lambdas, ensuring thread safety and performance in UDF/Redux architectures.

