---
name: io-github-aughtone-format-astronomy
description: >-
  The astronomy member of the aughtone-format family, in Kotlin Multiplatform
  common code with no platform calls. Get sunrise, sunset, dawn and dusk (civil,
  nautical and astronomical twilight) for a latitude, longitude and date, for
  opening hours such as OpenStreetMap's sunrise-sunset, dawn or dusk, with polar
  day and polar night reported instead of a guessed time; find new moons, full
  moons and the moon's phase; the 24 solar terms the Chinese and Korean calendars
  reckon by; and predict whether the young crescent moon will be seen on an
  evening, by the Odeh or Yallop criterion, as an Islamic month's start depends
  on. The same answer on JVM, Android, iOS, JS and Wasm. Works on
  kotlinx-datetime LocalDate, TimeZone and kotlin.time.Instant. Not for
  converting dates between calendars (use io.github.aughtone:format-calendars)
  and not for formatting times as text (use io.github.aughtone:format-datetime).
license: Apache-2.0
metadata:
  version: "4.2.1"
  repository: https://github.com/aughtone/aughtone-format
---

# io.github.aughtone:format-astronomy

## What it solves

When the sun and the moon do things, at a place on a day: sunrise, sunset and twilight for opening hours that follow the sun, new and full moons and the moon's phase, the 24 solar terms, and whether the new crescent can be seen. Pure Kotlin in common code, so every platform gives the same answer; no platform offers any of it.

**Called from Kotlin only.** The library publishes Kotlin Multiplatform artifacts; there is no separate Swift or JavaScript API.

## How it is meant to be used

```kotlin
import io.github.aughtone.astronomy.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val toronto = TimeZone.of("America/Toronto")
val times = SunTimes.of(43.6532, -79.3832, LocalDate(2024, 6, 21), toronto)
when (val sunrise = times.sunrise) {
    is SunEvent.At -> sunrise.instant.toLocalDateTime(toronto).time   // 05:36
    SunEvent.AlwaysAbove -> "midnight sun"                             // polar day
    SunEvent.AlwaysBelow -> "no sunrise"                               // polar night
}
times.civilDawn; times.civilDusk           // OpenStreetMap's dawn and dusk

Moon.newMoonsBetween(start, end)           // List<Instant>
Moon.phaseAt(instant).illuminatedFraction  // 0.0 to 1.0
Sun.solarTerms(2025)                        // 24 SolarTerm(longitude, instant)

Crescent.visibility(LocalDate(2024, 3, 10), 21.4225, 39.8262, TimeZone.of("Asia/Riyadh"))
    // CrescentPrediction(visibility = …, criterion = Odeh, value = …, bestTime = …)
```

`SunTimes.ofUtcDay(latitude, longitude, date)` answers for a UTC day instead of a place's own day.

## Invariants and traps

**Every sun event can be "no such moment".** `SunEvent` is `At(instant)`, `AlwaysAbove` or `AlwaysBelow`. For sunrise and sunset those are polar day and polar night; for dawn and dusk they mean the sun never got that far below the horizon (a white night) or never came that close. Symptom: a `ClassCastException` from `as SunEvent.At` above about 60° of latitude — handle all three.

**A day's events are the ones around its solar noon.** Where the sun sets after midnight (Reykjavik in June), the day's `sunset` is in the early hours of the next date, which is what "open until sunset" means. Symptom: a sunset whose local date is the next day — that is correct.

**Pass the place's own zone to `SunTimes.of`.** The zone decides which calendar day is meant. A fixed offset works, and needs no time-zone database (`FixedOffsetTimeZone(UtcOffset(hours = -5))`), which matters on JavaScript and Wasm, where `TimeZone.of` needs one.

**Accuracy is stated, not exact.** Sun times agree with NOAA's tables within a minute up to 60° of latitude, and within a few minutes beyond, where the first or last sunrise at the edge of the polar day can be a day apart. Elevation is not taken into account. New and full moons and solar terms agree with NASA's and the Hong Kong Observatory's tables within two minutes. All instants are Universal Time; future ones carry ΔT, which is predicted.

**A crescent prediction is not a sighting.** `Crescent.visibility` gives the Odeh criterion unless `CrescentCriterion.Yallop` is passed. Use `visibility.isNakedEyeVisible` or `isVisibleWithOpticalAid` to decide, not the enum's order. Where a month begins when the crescent is sighted, the announcement decides, whatever this predicts.

**Latitude and longitude are checked.** Outside −90 to 90 or −180 to 180, or not a number, `SunTimes.of` and `Crescent.visibility` throw `IllegalArgumentException`.

## What moved, and what it used to be called

**New in 4.2.1.** This module did not exist before 4.2.1, and nothing in `aughtone-format` calculated sun or moon positions. There is no earlier API to migrate from.

## What it is not for

- **Not calendar conversion.** Chinese, Korean and observation-based Islamic calendars, which use these calculations, are `io.github.aughtone:format-calendars`.
- **Not formatting.** It gives instants; showing a time as text is `io.github.aughtone:format-datetime`.
- **Not planets or stars.** It covers the sun and the moon only.
