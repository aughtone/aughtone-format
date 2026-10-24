---
name: io-github-aughtone-format-calendars
description: >-
  The calendar-arithmetic member of the aughtone-format family, in Kotlin
  Multiplatform common code with no platform calendars. Convert a date to and
  from the Hebrew, Islamic (civil, tabular, Umm al-Qura, or from observed month
  starts), Persian, Coptic, Ethiopic, Indian, Japanese era, Buddhist, Minguo,
  Chinese or Korean lunar calendar; find a leap month (Adar I, a Chinese leap
  month) or how many months and days a year has; predict when an Islamic month
  begins from the crescent; evaluate an iCalendar
  RSCALE recurrence rule (RFC 7529) such as "every year on 15 Nisan", including
  its leap months written 5L. The same answer on JVM, Android, iOS, JS and Wasm,
  where java.time, ICU, NSCalendar and Intl would each disagree or do no
  arithmetic. Works on kotlinx-datetime LocalDate. Not for formatting a date in
  these calendars as text, and not for sunrise, sunset or moon phases (use
  io.github.aughtone:format-astronomy).
license: Apache-2.0
metadata:
  version: "4.2.1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-calendars

## What it solves

Dates in calendars other than the Gregorian: converting a `kotlinx.datetime.LocalDate` to "15 Nisan 5787" or "1 Ramadan 1448" and back, and the shape of each calendar's years and months, which a recurrence rule needs. iCalendar's `RSCALE` (RFC 7529) names its calendar by CLDR identifier, and this module uses the same identifiers.

Everything is computed in common code, so every platform gives the same answer. The platforms cannot: Android and the JVM have some of these calendars, Apple most, and JavaScript's `Intl` formats in them but does no arithmetic, and their results differ.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts; there is no separate Swift or JavaScript API.

## How it is meant to be used

```kotlin
import io.github.aughtone.calendars.*
import kotlinx.datetime.LocalDate

// An RSCALE value is a CLDR calendar name
val hebrew = CalendarSystem.forCldrName("HEBREW")!!.calendar()!!

when (val result = LocalDate(2024, 4, 23).toCalendarDate(CalendarSystem.Hebrew)) {
    is CalendarResult.Resolved -> result.date            // CalendarDate(Hebrew, 5784, 7, 15)
    is CalendarResult.Unresolved -> result.reason
}

CalendarDate(CalendarSystem.Hebrew, 5784, 7, 15).toLocalDate()   // 2024-04-23
hebrew.monthsInYear(5784)                                        // 13
hebrew.leapMonth(5784)                                           // 5: Adar I follows Shevat
hebrew.daysInMonth(5784, 5, isLeapMonth = true)                  // 30
```

`CalendarSystem.calendar()` gives a `Calendar`: `fromLocalDate`, `toLocalDate`, `monthsInYear`, `daysInMonth` and `leapMonth`. `LocalDate.toCalendarDate(system)` and `CalendarDate.toLocalDate()` are shorthand for the two conversions.

The Chinese and Korean calendars work the same way; a leap month is the month it follows, flagged:

```kotlin
val chinese = CalendarSystem.Chinese.calendar()!!
chinese.fromLocalDate(LocalDate(2027, 2, 6))   // Resolved: CalendarDate(Chinese, 2027, 1, 1) — New Year
chinese.leapMonth(2023)                        // 2: 2023 has a leap second month
```

The observation-based Islamic calendars (`islamic`, `islamic-rgsa`) are built from the month starts observed, and predicted separately:

```kotlin
val saudi = ObservedIslamicCalendar(CalendarSystem.IslamicSaudi, listOf(
    IslamicMonthStart(1445, 9, LocalDate(2024, 3, 11)),
    IslamicMonthStart(1445, 10, LocalDate(2024, 4, 10)),
))
saudi.fromLocalDate(LocalDate(2024, 3, 20))    // Resolved: 10 Ramadan 1445

ObservedIslamicCalendar.predictMonthStart(1445, 9, 21.4225, 39.8262, TimeZone.of("Asia/Riyadh"))
    // PredictedMonthStart(firstDay = 2024-03-12, sightingEvening = 2024-03-11, …), Odeh criterion
```

## Invariants and traps

**A leap month is the month it follows, with `isLeapMonth` set — RFC 7529's `5L`.** The Hebrew Adar I, inserted after Shevat (5) in a leap year, is `month = 5, isLeapMonth = true`; Adar, or Adar II in a leap year, is month 6, and Nisan is 7. Symptom: Nisan reported as month 8, or a "13th month" in a Hebrew year — that is a different numbering, not this one.

**Converting from an ISO date returns a `CalendarResult`, not a date.** `Resolved` carries the `CalendarDate`; `Unresolved` carries a reason, for a date the calendar cannot place (a Japanese date before the first era). Symptom: a `when` that does not compile without both branches — handle `Unresolved`.

**A date that does not exist converts to `null`; nothing throws.** `CalendarDate` can describe the 30th day of a 29-day month, a leap month in a year without one, or Heisei 1 on 7 January (Heisei began on the 8th): `toLocalDate()` is `null` for each, and `daysInMonth` is `null` for a month that does not exist.

**Japanese dates need their era.** `year` counts from 1 within the era, and `era` is CLDR's code ("reiwa", "heisei", "showa", "taisho", "meiji"; earlier eras, which CLDR does not name, are their CLDR number as a string). A Japanese `CalendarDate` without `era` converts to `null`. Every other calendar ignores `era`.

**Years run on one continuous axis.** The year before year 1 is 0, then -1: in the Republic of China calendar 1911 is year 0, not "1 before Minguo". The Ethiopic Amete Alem calendar (`ethioaa`) counts the Amete Mihret years plus 5500.

**Umm al-Qura is a table, with the civil calendar outside it.** `islamic-umalqura` follows the published table from AH 1300 to 1600 (1882 to 2174) and the tabular civil calendar outside it, as ICU does. The two meet exactly at both ends.

**Chinese and Korean years are the ISO year they begin in.** `CalendarDate(Chinese, 2027, 1, 1)` is New Year 2027, 6 February 2027; month 12 of that year falls in early 2028. There is no 60-year cycle number.

**The Chinese and Korean calendars can disagree with the platform's, and the platform is the one that is wrong.** ICU, behind the JVM's ICU4J, Android's calendars and JavaScript's `Intl`, starts some months a day out when the new moon is within minutes of midnight — Chinese New Year 2027 on 7 February where the official calendar has the 6th, and 2030 on 2 February where it has the 3rd. This module's Chinese months follow the Hong Kong Observatory's published tables (1901–2099) and are computed from the new moon outside them; the Korean months are computed at Seoul. Symptom: a test comparing against `Intl` or Android fails for a handful of months — do not "fix" it toward the platform.

**Chinese and Korean dates outside 1645 to 2999 are unresolved.** The present rules date from 1645; outside that range `fromLocalDate` is `Unresolved` and `toLocalDate` is `null`.

**Observation-based Islamic dates resolve only within the months supplied.** `ObservedIslamicCalendar` returns `Unresolved` before the first month start and after the last month, and for the 30th day of a month whose next start was not supplied (a month has 29 or 30 days). `CalendarSystem.Islamic.calendar()` and `CalendarSystem.IslamicSaudi.calendar()` are `null`: there is nothing to compute without observations.

**A prediction is a different type from an observation.** `predictMonthStart` returns a `PredictedMonthStart`, never an `IslamicMonthStart`, so a prediction cannot be passed where an observed month is expected. It uses the Odeh crescent criterion unless Yallop is asked for, and the naked eye unless `acceptOpticalAid` is set. The month an authority announces is the month that begins.

**Thirteen months in Coptic and Ethiopic years.** Twelve months of 30 days and a 13th of 5, or 6 in a leap year.

**Before 1873, Japanese era boundaries are a convention.** CLDR writes earlier era starts with the lunisolar month and day of the time; a date is placed by comparing year, month and day as written against the proleptic Gregorian date, as ICU does with its own calendar.

## What moved, and what it used to be called

**New in 4.2.1.** This module did not exist before 4.2.1; there is no earlier API to migrate from, and no calendar arithmetic elsewhere in `aughtone-format`. It depends on `io.github.aughtone:format-astronomy` for the new moons and solar terms of the Chinese and Korean calendars and for crescent predictions.

## What it is not for

- **Not formatting.** It converts and counts; it does not write "15 Nisan 5787" as text. Gregorian date formatting is `io.github.aughtone:format-datetime`.
- **Not astronomy.** Sunrise, sunset, twilight, moon phases and solar terms are `io.github.aughtone:format-astronomy`.
- **Not a recurrence engine.** It gives the calendar arithmetic an `RSCALE` rule needs, not the rule evaluation itself.
