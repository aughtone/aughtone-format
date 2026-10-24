# `:astronomy`

Sunrise, sunset and twilight for a place and a day, new and full moons, the moon's phase, the 24 solar terms, and predictions of the young crescent moon's visibility, in pure Kotlin common code so every platform gives the same answer.

Published as `io.github.aughtone:format-astronomy`. It depends on nothing but kotlinx-datetime.

```kotlin
val times = SunTimes.of(43.6532, -79.3832, LocalDate(2024, 6, 21), TimeZone.of("America/Toronto"))
times.sunrise      // SunEvent.At(instant), or AlwaysAbove / AlwaysBelow in polar day and night
times.civilDusk    // OpenStreetMap's "dusk"

Moon.newMoonsBetween(start, end)
Sun.solarTerms(2025)
Crescent.visibility(date, latitude, longitude, timeZone)   // Odeh by default, or Yallop
```

The calculations follow NOAA's solar algorithm and Meeus's *Astronomical Algorithms*, and are checked against NOAA's sunrise tables, NASA's moon phases and the Hong Kong Observatory's solar terms. See [ADR-0006](../docs/knowledge/decisions/ADR-0006-astronomy-and-calendar-modules.md).
