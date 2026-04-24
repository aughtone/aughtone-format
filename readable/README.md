# Aughtone Format: Readable

The `:readable` module is the human-centric core of the Aughtone ecosystem. It transforms raw data—durations, numbers, sizes, and coordinates—into grammatically correct, localized strings. 

While the API is simple and primarily used in English-like patterns, it is built on a **Unicode CLDR-compliant engine** that automatically handles complex pluralization and regional variants for 55+ languages.

## 🚀 Key Features

### ⏱️ Durations & Relative Time
The duration and relative time formatters intelligently scale units and apply correct linguistic plurality.

#### Relative Time ("Time Ago")
Formats `Instant`, `LocalDateTime`, `LocalDate`, or `LocalTime` into natural language with style control.

```kotlin
val now = Clock.System.now()

// Standard (Long Style)
(now - 8.minutes).toReadableRelative() // "8 minutes ago"

// Short Style
(now - 5.days).toReadableRelative(style = RelativeStyle.Short) // "5d ago"

// Special Day Phrasing
(now + 1.days).toReadableRelative() // "Tomorrow"
(now - 1.days).toReadableRelative() // "Yesterday"

// Optimized Types
val today = LocalDate(2023, 10, 27)
today.toReadableRelative(now = today) // "Today"

// Custom Threshold
(now - 45.seconds).toReadableRelative(nowThreshold = 1.minutes) // "just now"
```

#### Durations
Translates `kotlin.time.Duration` into scannable natural language.

```kotlin
// Intelligent scaling
1.5.hours.toReadableString(Locale.current)   // "1.5 hours"
45.seconds.toReadableString(Locale.current)  // "45 seconds"

// Grammatical correctness
1.minutes.toReadableString(Locale.current)   // "1 minute"
2.minutes.toReadableString(Locale.current)   // "2 minutes"
```

### 🔢 Ordinality
Converts integers into ordinal forms (1st, 2nd, etc.) with support for complex linguistic suffixes.

```kotlin
1L.toReadableOrdinal(Locale("en")) // "1st"
2L.toReadableOrdinal(Locale("en")) // "2nd"
3L.toReadableOrdinal(Locale("en")) // "3rd"
```

### 💾 Data Sizes
Formatted using either **IEC (binary, base-1024)** or **SI (decimal, base-1000)** standards.

```kotlin
1024L.toReadableDataSize() // "1.0 KiB" (Default binary)
1000L.toReadableDataSize(base = 1000) // "1.0 KB" (Decimal)
```

### 📍 Geospatial Formatting
Comprehensive support for mapping and navigation metrics.

- **Coordinates**: Supports Decimal Degrees (DD) and Degrees-Minutes-Seconds (DMS).
- **Cardinal Directions**: Fully localized compass directions.
- **Altitudes & Azimuths**: Formatted with SI units and degree symbols.

```kotlin
val coords = Coordinates(45.523, -122.676)
coords.toReadableString(CoordinatesFormat.DMS) // 45° 31' 22" N, 122° 40' 33" W

Cardinal.North.toReadable(Locale("en")) // "North"
Cardinal.SouthWest.toReadable(Locale("en")) // "South-west"
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
The module maintains 1:1 parity with `:datetime`, supporting **55 core languages** and robust BCP 47 subtag fallback (e.g., `fr-CA` → `fr`).
