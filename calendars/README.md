# `:calendars`

Conversion to and from `kotlinx.datetime.LocalDate`, and the shape of years and months, for every calendar iCalendar's `RSCALE` (RFC 7529) names: Buddhist, Japanese, ROC, Hebrew, Islamic (civil, tabular, Umm al-Qura, and observation-based), Persian, Coptic, Ethiopic, Indian, Chinese and Korean (Dangi). Pure Kotlin common code, so every platform gives the same answer.

Published as `io.github.aughtone:format-calendars`. It depends on `:astronomy` for the Chinese and Korean calendars' new moons and solar terms, and for crescent predictions.

```kotlin
val hebrew = CalendarSystem.forCldrName("hebrew")!!.calendar()!!
LocalDate(2024, 4, 23).toCalendarDate(CalendarSystem.Hebrew)   // Resolved: 15 Nisan 5784
hebrew.leapMonth(5784)                                          // 5: Adar I, RFC 7529's 5L

val saudi = ObservedIslamicCalendar(CalendarSystem.IslamicSaudi, observedMonthStarts)
ObservedIslamicCalendar.predictMonthStart(1445, 9, latitude, longitude, timeZone)  // a prediction, never an observation
```

The Chinese calendar follows the Hong Kong Observatory's published tables for 1901–2099 and is computed outside them; it does not use ICU's data, which starts some months a day out ([ADR-0007](../docs/knowledge/decisions/ADR-0007-chinese-calendar-not-from-icu.md)). The other calendars are checked against ICU for 1900 to 2100.
