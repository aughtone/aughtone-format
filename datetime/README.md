# Aughtone Format: Datetime

The `:datetime` module provides advanced, locale-aware formatting for `kotlinx-datetime` types. It achieves full parity with the Unicode CLDR for month names, day names, and structured date/time patterns across **65+ languages**.

## 🚀 Usage

```kotlin
import io.github.aughtone.datetime.format.*
import io.github.aughtone.types.locale.localeFor

val us = localeFor("en-US")!!

// Styles
LocalDate(1952, 1, 12).format(DateTimeStyle.Medium, locale = us)            // "Jan 12, 1952"
LocalDate(1952, 1, 12).format(DateTimeStyle.Full, locale = us)              // "Saturday, January 12, 1952"
LocalDate(1952, 1, 12).format(DateTimeStyle.Full, locale = us, showEra = true) // "Saturday, January 12, 1952 CE"
LocalTime(16, 8).format(DateTimeStyle.Short, locale = us, is24HourFormat = false) // "4:08 PM"
Clock.System.now().format(DateTimeStyle.Short, DateTimeStyle.Short, locale = us)

// Ranges, as the locale writes them: what both ends share is written once
LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 3, 5), DateTimeStyle.Long, us)          // "March 3 – 5, 2024"
LocalDate(2024, 3, 3).formatRange(LocalDate(2024, 3, 5), DateTimeStyle.Long, localeFor("de")!!) // "3.–5. März 2024"
LocalTime(9, 0).formatRange(LocalTime(11, 30), locale = us, is24HourFormat = false)      // "9:00 – 11:30 AM"
DayOfWeek.MONDAY.formatRange(DayOfWeek.FRIDAY, TextWidth.Abbreviated, us)                  // "Mon – Fri"

// Names on their own
Month.MARCH.displayName(TextWidth.Full, localeFor("es")!!)                                // "Marzo"
TimeZone.of("America/Toronto").displayName(Clock.System.now(), TextWidth.Abbreviated, us) // "EST" or "EDT"
TimeZone.of("America/Toronto").genericDisplayName(TextWidth.Full, us)                     // "Eastern Time", all year

// Windows zone names, as Outlook and Exchange write them, to and from IANA identifiers
TimeZone.ianaIdForWindowsName("Eastern Standard Time")        // "America/New_York"
TimeZone.ianaIdForWindowsName("Eastern Standard Time", "CA")  // "America/Toronto"
TimeZone.windowsNameForIanaId("America/Toronto")              // "Eastern Standard Time"
```

The spaces around a range's dash are thin spaces (U+2009), as CLDR writes them.

### What a locale controls
Patterns, month and weekday names (in the form the language uses inside a date — "15 de marzo de 2024"), the 12/24-hour convention, the AM/PM marker and where it goes, and the default digits, which follow CLDR (Latin for Hindi and Thai, Arabic-Indic for Arabic in Egypt). The era appears only when asked for with `showEra`, or always for a date before year 1.

> [!NOTE]
> For natural language "time ago" formatting, use the `formatReadableRelative()` extensions from the [**:readable**](../readable) module.

## 🌍 Language Support
Supports 65+ core languages with regional variant fallback.
- **Slavic**: Correct grammatical handling for dates.
- **Semitic**: Full support for right-to-left scripts.
- **CJK**: Character-based representation (Chinese, Japanese, Korean).

## 📐 Architecture
This module is **stateless**. Formatters are built as immutable lambdas, ensuring thread safety and performance in UDF/Redux architectures.

