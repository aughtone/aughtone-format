# Aught One Format

Aught One Format is a suite of Kotlin Multiplatform libraries designed to provide consistent, localized formatting for Dates, Times, Numbers, and other human-readable metrics across all platforms.

## 🚀 Major Updates in 4.2.0

New range formatting, and a set of output changes to know about; the [CHANGELOG](CHANGELOG.md) lists every change.

- **Date and time ranges** (`:datetime`): `formatRange` on `LocalTime`, `LocalDate`, `LocalDateTime`, `Instant` and `DayOfWeek` writes a range the way the locale does, from the Unicode CLDR interval formats — "March 3 – 5, 2024", "9:00 – 11:30 AM", "3.–5. März 2024", "Mon – Fri" — with what both ends share written once.
- **The era is opt-in** (`:datetime`): `Full` dates no longer end with "Common Era"; pass `showEra = true` for "1952 CE". A date before year 1 always shows its era.
- **Names inside a date follow the language** (`:datetime`): lower case where it writes them so ("15 de marzo de 2024"), while `displayName` keeps the stand-alone form ("Marzo").
- **Default digits follow CLDR** (`:datetime`): Latin for Hindi, Thai, Urdu outside India and Arabic without a region; Arabic-Indic for Arabic in Egypt, Saudi Arabia and the other regions that write them. `numberingSystem` still overrides.
- **Short style in every language** (`:readable`): `RelativeStyle.Short` takes CLDR's short phrases outside English ("vor 5 Min."), never longer than the long phrase.
- **More languages, better words** (`:readable`): Bengali, Gujarati, Kannada, Malayalam, Marathi, Punjabi, Tamil, Telugu and Urdu relative times and durations, which were English; Arabic durations name one and two without a digit; Chinese months read "2个月", not "2月" (February); Georgian ordinals take their prefix form; a duration under half a second reads "now".
- **Looser identifier input** (`:identifiers`): `formatMac` reads any of its notations and `formatIban` lower case and the spaced form; the output is always the notation asked for.
- **Deprecated**: the duplicate `Altitude`/`Azimuth.formatReadable` in `io.github.aughtone.readable.geo`; use the ones in `…readable.quantitative`. Removed in 5.0.0.
- **Dependencies**: Kotlin 2.4.21, Compose Multiplatform 1.12.2.

## 🚀 Major Updates in 4.1.1

A correctness release: no new API, many fixes to output. Code that pinned the old output in its own tests will need updating; the [CHANGELOG](CHANGELOG.md) lists every change.

- **Dates no longer throw** (`:datetime`): `Long` and `Full` dates threw for Spanish, Portuguese, Catalan, Galician, Basque, Lithuanian and Latvian (and `Full` for Danish and Finnish). Quoted text in locale patterns now reaches the output intact, two-digit years work in any century, and region variants keep their language's patterns.
- **Numbers and money are written out exactly** (`:readable`): negative numbers, very large and very small values, NaN and infinity, and every digit of a `Long`. Money always shows its minor-unit digits (`$12.00`) and puts the sign before an attached symbol (`-$12.00`). Separators and grouping follow each language.
- **Grammar in every bundled language** (`:readable`): plural forms follow the CLDR rules, durations and day labels are localized, and relative phrases ("5 minutes ago", "in 3 days") put the unit in the right case. Fourteen languages whose grammar needs it take each phrase whole from CLDR.
- **Clocks and zone names** (`:datetime`): JavaScript and Wasm detect the 12/24-hour setting; elsewhere the convention comes from CLDR for the locale. AM/PM sits where the language puts it, and zones that share an abbreviation keep their own names.
- **Rendering** (`:viewable`, `:viewable-compose`): the Compose painter draws where it should, EPS is the right way up with its strokes, SVG keeps alpha, empty geometries give valid WKT, and flat lines and single points render.
- **Smaller fixes** in `:toolbox` (no exponent notation from `Double.format`, Web Mercator finite at the poles) and `:identifiers` (non-ASCII digits rejected as hex, malformed Punycode rejected).
- **Behaviour changes to know about**: English in the UK, Ireland and South Africa defaults to the 24-hour clock and writes the day first; Spanish, Italian, Polish and others leave four-digit numbers ungrouped; halves round away from zero.

## 🚀 Major Updates in 4.1.0

- **Standalone weekday & month names** (`:datetime`): `DayOfWeek.displayName(width, locale)` and `Month.displayName(width, locale)` (with a `TextWidth { Full, Abbreviated }` enum) expose the locale's day and month names — "Monday"/"Mon", "lundi", "mars" — without formatting a whole date.
- **Localized time-zone names** (`:datetime`): `TimeZone.displayName(instant, width, locale)` gives the zone's daylight-aware *specific* name ("Eastern Standard Time" / "EST").
- **Locale-correct list joining** (`:readable`): `List<String>.formatReadableList(locale, type)` — "Monday, Wednesday, and Friday" / "lundi, mercredi et vendredi" — from CLDR list patterns, ~55 locales.
- **Zone names now delegate to the platform's CLDR** (java.time / Android ICU / Apple / Intl) with a small bundled Inuktitut supplement, replacing a ~500 KB bundled table — see [Coverage over consistency](#coverage-over-consistency). Wording follows the reader's OS and can differ across platforms.
- **Better Inuktitut (`iu`) weekday names**: idiomatic Inuktut day-words from the Inuktut Tusaalanga glossary, replacing the previous machine-generated phrases.
- **`ImageFormat.WEBP` removed (breaking)** (`:viewable`): it produced real WebP only on Android. Raster export is PNG or JPEG; cross-platform WebP is planned to return.
- **JPEG export fixed on the JVM** (`:viewable`): it previously returned empty bytes.
- **Dependencies**: `aughtone-types` 4.1.0, Compose Multiplatform 1.12.1.

## 🚀 Major Updates in 4.0.0

- **New `:identifiers` module**: display formatters for canonical identifiers — MAC, UUID, IBAN, card/PAN, IPv4/IPv6/network, domain (Punycode decode), and phone (E.164 → national/international/RFC 3966). Each renders an already-canonical value in an alternative notation via `String.formatX(notation)`.
- **`aughtone-types` 4.0.0 (breaking)**: types is re-exported by the formatting modules, so its 4.0.0 API is part of this library's surface. Notably `GeoBoundingBox` is no longer a `GeoGeometry`, so rendering a bounding box as a path/SVG/WKT geometry has been removed from `:viewable` and `:toolbox`.
- **Toolchain**: Kotlin 2.4.20, Compose Multiplatform 1.12.0, Android Gradle Plugin 9.4.1, Gradle 9.6.0.

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
- **`:identifiers`**: Display formatters for canonical identifiers — render a normalized MAC, UUID, IBAN, card number, IPv4/IPv6, domain, or phone number in an alternative notation.
- **`:viewable`**: Platform-agnostic vector graphics representation, styling, and path conversion (GeoJSON, SVG, WKT).
- **`:viewable-compose`**: Jetpack / Compose Multiplatform integration for rendering viewable vector graphics (Painters, ImageVectors).
- **`:toolbox`**: Shared utilities and common formatting primitives.

This project follows a specialized 5-sector documentation hierarchy.

## 📚 Documentation Sectors
- 📐 [Architecture](docs/knowledge/specifications/DOC-0001-architecture.md): Engineering rules and design patterns.
- 🧠 [Functional Specifications](docs/knowledge/specifications/DOC-0002-functional-specifications.md): What each module does.
- 🏛️ [Decisions](docs/knowledge/decisions/README.md): The choices behind the design, and why.
- 🔬 [Research](docs/knowledge/research/README.md): Investigations and what they found.
- 📖 [Developer Guide](docs/knowledge/guides/DOC-0005-developer-guide.md): Adding languages, conventions and releases.
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
The library provides full BCP 47 subtag fallback (e.g., `en-ZA` → `en`) for 59 supported language and region combinations. Numbers, dates, durations, plural forms, day labels and relative phrases ("5 minutes ago") are localized for all of them:

| Family | Languages & Regions |
| :--- | :--- |
| **Germanic** | Afrikaans (`af-ZA`), Dutch (`nl-NL`), English (`en-US`), South African English (`en-ZA`), German (`de-DE`), Swiss German (`de-CH`), Danish (`da-DK`), Norwegian Bokmål (`nb-NO`), Norwegian Nynorsk (`nn-NO`), Swedish (`sv-SE`), Icelandic (`is-IS`) |
| **Romance** | French (`fr-FR`), Swiss French (`fr-CH`), Spanish (`es-ES`), Italian (`it-IT`), Swiss Italian (`it-CH`), Portuguese (`pt-PT`), Catalan (`ca-ES`), Galician (`gl-ES`), Romanian (`ro-RO`) |
| **Slavic & Baltic** | Russian (`ru-RU`), Ukrainian (`uk-UA`), Belarusian (`be-BY`), Polish (`pl-PL`), Czech (`cs-CZ`), Slovak (`sk-SK`), Bulgarian (`bg-BG`), Croatian (`hr-HR`), Serbian (`sr-RS`), Macedonian (`mk-MK`), Slovenian (`sl-SI`), Lithuanian (`lt-LT`), Latvian (`lv-LV`) |
| **East & SE Asian** | Japanese (`ja-JP`), Chinese (`zh-CN`), Traditional Chinese (`zh-TW`), Korean (`ko-KR`), Thai (`th-TH`), Vietnamese (`vi-VN`), Indonesian (`id-ID`), Malay (`ms-MY`) |
| **South & Central Asian** | Hindi (`hi-IN`), Armenian (`hy-AM`), Georgian (`ka-GE`), Kazakh (`kk-KZ`), Uzbek (`uz-UZ`) |
| **Middle Eastern & African** | Arabic (`ar-EG`), Hebrew (`he-IL`), Persian (`fa-IR`), Turkish (`tr-TR`), Azerbaijani (`az-AZ`), Swahili (`sw-KE`) |
| **Other & Finno-Ugric** | Finnish (`fi-FI`), Estonian (`et-EE`), Hungarian (`hu-HU`), Greek (`el-GR`), Basque (`eu-ES`), Albanian (`sq-AL`), Inuktitut (`iu-CA`) |

Relative times and durations in `:readable` are also localized for Bengali (`bn`), Gujarati (`gu`), Kannada (`kn`), Malayalam (`ml`), Marathi (`mr`), Punjabi (`pa`), Tamil (`ta`), Telugu (`te`) and Urdu (`ur`), from CLDR.

### Coverage over consistency
For localized *display names* (time-zone names, and similar large translation sets) the library favours **coverage over consistency**. Byte-identical output on every platform was the original goal — the property bundled tables give — but it was judged not worth its cost. The new priority is that every reader sees text in **their own language**, even where the exact wording differs slightly across platforms and OS versions.

**Why not simply bundle every translation?** A complete localized dataset — every name in every supported language — is a large matrix (hundreds of KB even gzipped) and, unlike the library's own types, it **cannot be dead-code-eliminated**: a table reached by the reader's display language keeps every entry live. Bundling it in common code puts that weight on *every* target, including the browser and mobile, where the platform's runtime already holds the same CLDR data. So instead:

- **Delegate to the platform's CLDR** where it exists (JVM, Android, Apple, JS, Wasm) — the browser especially, where `Intl` is complete and bundling would only duplicate it.
- **Bundle the complete tables only where the platform has none** — desktop targets, where the size is affordable.
- **Carry a small supplement in common code** for the few languages CLDR itself omits (e.g. Inuktitut), so no reader silently falls back to English.

The trade is explicit: this delivers coverage, not consistency — four platform CLDR versions will word things a little differently and drift over time. Where consistency matters more than size (a desktop-only consumer), the bundled tables remain the option.

## 🚀 Quick Usage

### Datetime Formatting (`:datetime`)
```kotlin
val now = Clock.System.now()
// Format with styles (Short, Medium, Long, Full)
println(now.format(dateStyle = DateTimeStyle.Short, timeStyle = DateTimeStyle.Short, locale = localeFor("en-US")!!)) // "4/23/2026 4:15 PM"
println(now.format(dateStyle = DateTimeStyle.Short, timeStyle = DateTimeStyle.Short, locale = localeFor("en-CA")!!)) // "2026-04-23 4:15 p.m."
```

### Human-Readable Metrics (`:readable`)
Each family of formatters lives in its own package (`…readable.ordinality`, `.duration`, `.relative`, `.metrics`, `.number`, `.financial`, `.list`, …); predefined locales are in `Locales`.
```kotlin
// Ordinals
println(123L.formatReadableOrdinal(Locales.English)) // "123rd"
println(123L.formatReadableOrdinal(Locales.French))  // "123e"

// Durations — scaled to the most significant unit and rounded
println(90.minutes.formatReadable(locale = Locales.English)) // "2 hours"
println(90.minutes.formatReadable(locale = Locales.Spanish)) // "2 horas"

// Numbers and money
println(1234567.891.formatReadable(Locales.German, precision = 2)) // "1.234.567,89"
println(Money(123456L, usd).formatReadable(Locales.English))       // "$1,234.56"

// Relative Time
println(instant.formatReadableRelative(locale = Locales.English)) // "3 minutes ago"
println(instant.formatReadableRelative(locale = Locales.German))  // "vor 3 Minuten"

// Relative Time — direction-aware LocalTime (disambiguates a clock time across midnight)
val alarm = LocalTime(1, 0)
println(alarm.formatReadableRelative(now = LocalTime(23, 0), direction = RelativeDirection.Future)) // "in 2 hours"

// Data Sizes
println(1048576L.formatReadableDataSize()) // "1 MiB"
```

---
## 🛠️ Governance Standards
See [How This Documentation Works](docs/README.md) for where documents live, and [the standards](docs/standards/) for development rules.
