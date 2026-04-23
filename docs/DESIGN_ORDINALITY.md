# Design: Ordinality Formatting

This document defines the design and implementation rules for converting numbers into their ordinal representations (e.g., 1 to "1st", 2 to "2nd").

## 🧠 Functional Specification

The Ordinality feature provides a localized way to append the correct suffix or apply language-specific rules to a number to indicate its position in a sequence.

### Requirements
- Support for `Int` and `Long` types.
- Localization based on the `aughtone-types` `Locale` system.
- Fallback mechanism for unsupported locales (defaults to English).

## 📐 Architecture

### Resource Registry
The system uses a lazy-initialized map (`ordinalityResourceMap`) keyed by IETF BCP 47 language tags. This allows for lightweight initialization and easy addition of new languages.

### Interface: `OrdinalityResource`
Each language implementation must fulfill this interface:
```kotlin
interface OrdinalityResource {
    fun format(number: Int): String
    fun format(number: Long): String
}
```

### Resolution Logic
The `ordinalityFor(locale: Locale)` function implements a BCP 47 "lookup" algorithm, progressively stripping subtags (e.g., `en-CA` -> `en`) until a match is found.

## 🌍 Supported Languages

| Language | Tag | Examples |
| :--- | :--- | :--- |
| English | `en` | 1st, 2nd, 3rd, 4th, 11th, 21st |

## 🛠️ Usage

```kotlin
val locale = localeFor("en-US")!!
val result = 1.toOrdinal(locale) // "1st"
```

## 🔗 References

- [Wiktionary: Ordinal numbers by language](https://en.wiktionary.org/wiki/Category:Ordinal_numbers_by_language)
- [Wikipedia: Ordinal numeral](https://en.wikipedia.org/wiki/Ordinal_numeral)
