# Aughtone Format

Aughtone Format is a suite of Kotlin Multiplatform libraries designed to provide consistent, localized formatting for Dates, Times, Numbers, and other human-readable metrics across all platforms.

## 🚀 Latest Release: 3.1.2

3.1.1 and 3.1.2 are maintenance releases — licensing notices and repository housekeeping, with no functional or API changes. The most recent feature release is 3.1.0, highlighted below.

### Highlights from 3.1.0

Aughtone Format 3.1.0 focuses on relative-time expressiveness and completing localization coverage:

- **Direction-Aware `LocalTime` Relative Time**: New `RelativeDirection { Past, Present, Future, Nearest }` enum and a `direction` parameter on `LocalTime.formatReadableRelative` resolve the ambiguity of a date-less clock time across midnight (modelled on ICU's `Direction`). The default is now `Nearest` (shortest distance on a 24-hour clock) — a behaviour change from the previous linear same-day math; pass `RelativeDirection.Present` for the old behaviour.
- **Calendar-Anchored `LocalTime`**: A new `LocalTime.formatReadableRelative(now: Instant, timeZone, …)` overload resolves the time to a concrete occurrence relative to a dated anchor, so it can render day labels like "Yesterday"/"Today"/"Tomorrow".
- **"Shortly" Near-Future Label**: `LocalDate` relative formatting gains a "Shortly" label — the future mirror of "Recently" — for near-future dates within the `nowThreshold` window.
- **Completed Localizations**: Relative time now works for Danish, Norwegian (Bokmål/Nynorsk), Swedish, and Icelandic (previously fell back to English); Inuktitut gains full time-zone names (143 zones); Traditional Chinese gains its own compass points and era names; and 11 locales gained proper AM/PM markers.
- **English-Fallback Fixes**: Indonesian/Malay/Swahili relative time, duration formatting for 10 locales (`az`, `eu`, `hy`, `ka`, `kk`, `lt`, `lv`, `sq`, `uz`, `iu`), and Persian money symbol placement were silently falling back to English or formatting incorrectly — all now fixed, with a coverage-guard test preventing this class of regression.


## 📦 Core Modules

- **`:datetime`**: Advanced formatting for `kotlinx-datetime` types with multi-locale support.
- **`:readable`**: Human-friendly formatting for metrics (abbreviations), ordinals, and data sizes.
- **`:viewable`**: Platform-agnostic vector graphics representation, styling, and path conversion (GeoJSON, SVG, WKT).
- **`:viewable-compose`**: Jetpack / Compose Multiplatform integration for rendering viewable vector graphics (Painters, ImageVectors).
- **`:toolbox`**: Shared utilities and common formatting primitives.

This project follows a specialized 5-sector documentation hierarchy.

## 📚 Documentation Sectors
- 📐 [Architecture](docs/ARCH.md): Engineering rules and design patterns.
- 🧠 [Functional Specifications](docs/SPEC.md): Business logic and domain constraints.
- 🎨 [Design & UI](docs/DESIGN.md): Presentation layer and user stories.
- 📋 [Acceptance Criteria](docs/ACs/README.md): Success outcomes and verification.
- 📖 [Developer Guide](docs/DEVELOPER.md): Environment setup and onboarding.
- 📜 [Changelog](CHANGELOG.md): History of changes and release notes.
## ✨ Features
- **55+ Languages and Regions Supported**: Deep grammatical parity for Slavic, Arabic, Hebrew, Inuktitut, and more.
- **Relative Time Formatting**: Convert instants, dates, and times into natural language (e.g., "5 minutes ago", "Yesterday", "Shortly"), with direction-aware `LocalTime` handling across midnight.
- **Automatic Fallback**: Smartly switches from relative to absolute formatting based on configurable thresholds.
- **Ordinal Numbers**: Localized ordinal suffix support (e.g., 1st, 2nd, 3.º, 第1).
- **Duration Scaling**: Human-friendly duration strings with perceptual rounding (e.g., "2 weeks" vs "14 days").
- **Numeric Abbreviations**: Scaling large numbers into abbreviated forms (e.g., 1.5K, 1.2M).
- **Geospatial Formatting**: Localized altitude, azimuth (with cardinal directions), and coordinates.

## 🌍 Universal Localization
The library provides deep grammatical parity and full BCP 47 subtag fallback (e.g., `en-ZA` → `en`) for 59 supported language and region combinations:

| Family | Languages & Regions |
| :--- | :--- |
| **Germanic** | Afrikaans (`af-ZA`), Dutch (`nl-NL`), English (`en-US`), South African English (`en-ZA`), German (`de-DE`), Swiss German (`de-CH`), Danish (`da-DK`), Norwegian Bokmål (`nb-NO`), Norwegian Nynorsk (`nn-NO`), Swedish (`sv-SE`), Icelandic (`is-IS`) |
| **Romance** | French (`fr-FR`), Swiss French (`fr-CH`), Spanish (`es-ES`), Italian (`it-IT`), Swiss Italian (`it-CH`), Portuguese (`pt-PT`), Catalan (`ca-ES`), Galician (`gl-ES`), Romanian (`ro-RO`) |
| **Slavic & Baltic** | Russian (`ru-RU`), Ukrainian (`uk-UA`), Belarusian (`be-BY`), Polish (`pl-PL`), Czech (`cs-CZ`), Slovak (`sk-SK`), Bulgarian (`bg-BG`), Croatian (`hr-HR`), Serbian (`sr-RS`), Macedonian (`mk-MK`), Slovenian (`sl-SI`), Lithuanian (`lt-LT`), Latvian (`lv-LV`) |
| **East & SE Asian** | Japanese (`ja-JP`), Chinese (`zh-CN`), Traditional Chinese (`zh-TW`), Korean (`ko-KR`), Thai (`th-TH`), Vietnamese (`vi-VN`), Indonesian (`id-ID`), Malay (`ms-MY`) |
| **South & Central Asian** | Hindi (`hi-IN`), Armenian (`hy-AM`), Georgian (`ka-GE`), Kazakh (`kk-KZ`), Uzbek (`uz-UZ`) |
| **Middle Eastern & African** | Arabic (`ar-EG`), Hebrew (`he-IL`), Persian (`fa-IR`), Turkish (`tr-TR`), Azerbaijani (`az-AZ`), Swahili (`sw-KE`) |
| **Other & Finno-Ugric** | Finnish (`fi-FI`), Estonian (`et-EE`), Hungarian (`hu-HU`), Greek (`el-GR`), Basque (`eu-ES`), Albanian (`sq-AL`), Inuktitut (`iu-CA`) |

## 🚀 Quick Usage

### Datetime Formatting (`:datetime`)
```kotlin
val now = Clock.System.now()
// Format with styles (Short, Medium, Long, Full)
println(now.format(DateTimeStyle.Short, locale = Locale("en-US"))) // "4/23/26, 4:15 PM"
println(now.format(DateTimeStyle.Short, locale = Locale("en-CA"))) // "2026-04-23, 4:15 p.m."
```

### Human-Readable Metrics (`:readable`)
```kotlin
// Ordinals
println(123L.formatReadableOrdinal(Locale("en-US"))) // "123rd"
println(123L.formatReadableOrdinal(Locale("fr-FR"))) // "123e"

// Durations
println(1.5.hours.formatReadable(Locale("en-US"))) // "1.5 hours"
println(1.5.hours.formatReadable(Locale("es-ES"))) // "1,5 horas"

// Relative Time
println(instant.formatReadableRelative(locale = Locale("en-US"))) // "3 minutes ago"
println(instant.formatReadableRelative(locale = Locale("de-DE"))) // "vor 3 Minuten"

// Relative Time — direction-aware LocalTime (disambiguates a clock time across midnight)
val alarm = LocalTime(1, 0)
println(alarm.formatReadableRelative(now = LocalTime(23, 0), direction = RelativeDirection.Future)) // "in 2 hours"

// Data Sizes
println(1048576L.formatReadableDataSize()) // "1.0 MiB"
```

---
## 🛠️ Governance Standards
Access the [Governance Skills](docs/standards/) for specialized development rules.

