# Aughtone Format: Readable

The `:readable` module is the human-centric core of the Aughtone ecosystem. It transforms raw data—durations, numbers, sizes, and coordinates—into grammatically correct, localized strings. 

While the API is simple and primarily used in English-like patterns, it is built on a **Unicode CLDR-compliant engine** that automatically handles complex pluralization and regional variants for 65+ languages.

## 🚀 Key Features

### ⏱️ Durations & Relative Time
The duration and relative time formatters intelligently scale units and apply correct linguistic plurality.

#### Relative Time ("Time Ago")
Formats `Instant`, `LocalDateTime`, `LocalDate`, or `LocalTime` into natural language with style control.

```kotlin
val now = Clock.System.now()

// Standard (Long Style)
(now - 8.minutes).formatReadableRelative() // "8 minutes ago"

// Short Style
(now - 5.days).formatReadableRelative(style = RelativeStyle.Short) // "5d ago"

// Special Day Phrasing
(now + 1.days).formatReadableRelative() // "Tomorrow"
(now - 1.days).formatReadableRelative() // "Yesterday"

// Optimized Types
val today = LocalDate(2023, 10, 27)
today.formatReadableRelative(now = today) // "Today"

// Custom Threshold
(now - 45.seconds).formatReadableRelative(nowThreshold = 1.minutes) // "just now"
```

#### Durations
Translates `kotlin.time.Duration` into scannable natural language.

```kotlin
// Intelligent scaling
1.5.hours.formatReadable(Locale.current)   // "1.5 hours"
45.seconds.formatReadable(Locale.current)  // "45 seconds"

// Grammatical correctness
1.minutes.formatReadable(Locale.current)   // "1 minute"
2.minutes.formatReadable(Locale.current)   // "2 minutes"
```

### 🔢 Ordinality
Converts integers into ordinal forms (1st, 2nd, etc.) with support for complex linguistic suffixes.

```kotlin
1L.formatReadableOrdinal(Locale("en-US")) // "1st"
2L.formatReadableOrdinal(Locale("en-US")) // "2nd"
3L.formatReadableOrdinal(Locale("en-US")) // "3rd"
```

### 💾 Data Sizes
Formatted using either **IEC (binary, base-1024)** or **SI (decimal, base-1000)** standards.

```kotlin
1024L.formatReadableDataSize() // "1.0 KiB" (Default binary)
1000L.formatReadableDataSize(base = 1000) // "1.0 KB" (Decimal)
```

### 📍 Geospatial Formatting
Comprehensive support for mapping and navigation metrics.

- **Coordinates**: Supports Decimal Degrees (DD) and Degrees-Minutes-Seconds (DMS).
- **Cardinal Directions**: Fully localized compass directions.
- **Altitudes & Azimuths**: Formatted with SI units and degree symbols.

```kotlin
val coords = Coordinates(45.523, -122.676)
coords.formatReadable(CoordinateFormat.DegreesMinutesSeconds) // 45° 31' 22" N, 122° 40' 33" W

Cardinal.North.formatReadable(Locale("en-US")) // "North"
Cardinal.SouthWest.formatReadable(Locale("en-US")) // "South-west"
```

---

## 🧠 Linguistic Intelligence
The "hidden" strength of this module is its **Plural Category Engine**. Most libraries only handle "singular vs. plural," which fails for many world languages. Aughtone `:readable` implements the full Unicode CLDR spectrum:

| Category | Typical Usage | Example (Russian) |
| :--- | :--- | :--- |
| `Zero` | Special zero case | _(Depends on language)_ |
| `One` | Singular | 1 минута |
| `Two` | Dual (e.g., Arabic/Hebrew) | _(Special case)_ |
| `Few` | Small plural groups | 2 минуты |
| `Many` | Large plural groups | 5 минут |
| `Other` | Catch-all | _(Fallback)_ |

### **Stateless & Performant**
Every formatter in this module is built using a **Functional Factory** pattern. We pre-build immutable formatting lambdas that are safe for use in highly concurrent UDF (Unidirectional Data Flow) or Redux-based architectures.

## 🌍 Language Parity
The module maintains 1:1 parity with `:datetime`, supporting **65+ core languages** and robust BCP 47 subtag fallback (e.g., `fr-CA` → `fr`).

---
## 🤖 AI-Assisted Development
This library includes embedded, machine-readable "skills" to help AI assistants understand its APIs and best practices.

- **Discovery**: Look for `META-INF/ai-skills/io.github.aughtone.format-readable.ai-skill.md`

### 🪄 Magic Prompt for AI Assistants
If you are using an AI assistant (like Claude, Gemini, or ChatGPT) to write code with this library, paste this prompt first:

> "Scan all project dependencies for AI Skill files in `META-INF/ai-skills/` with the prefix `io.github.aughtone`. Use these to understand the API patterns, types, and governance for this library. If they are not found in the local classpath, refer to the [Aughtone Format GitHub repository](https://github.com/aughtone/aughtone-format) for the source definitions."
