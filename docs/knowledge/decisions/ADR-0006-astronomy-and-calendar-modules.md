# Astronomy and calendar modules

ADR-0006 · 2026-10-24 · Status: accepted; the Chinese and Korean calendar data superseded by ADR-0007
Keywords: sunrise sunset dawn dusk, opening hours relative to the sun, Hebrew Islamic Chinese
          calendar conversion, RFC 7529 RSCALE, leap month 5L, Umm al-Qura, crescent visibility
          Odeh Yallop, observed versus predicted month start, Chinese calendar table or computed,
          why not platform calendars, why not one module

## Context

Two consumers need the same mathematics. A calendar library evaluating iCalendar `RSCALE` rules (RFC 7529) needs conversion and arithmetic for every calendar the RFC names (#25). An opening-hours library needs sunrise, sunset, dawn and dusk for a position and a date (#29). The Chinese and Korean calendars depend on new moons and solar terms, and the observation-based Islamic calendars need the moon's position to predict a month start, so the astronomy is shared.

The answers must be identical on JVM, Android, Apple, JavaScript and Wasm. The platforms cannot provide that: Android and the JVM have some of these calendars, Apple has most, JavaScript's `Intl` formats in them but does no arithmetic, and their results differ from each other. Sun times are not available from any platform API at all. So the implementation is pure Kotlin in common code, on kotlinx-datetime alone.

## Decision

**Two new modules.** `:astronomy` (`format-astronomy`) holds the sun and moon calculations and no calendar data. `:calendars` (`format-calendars`) holds the calendar arithmetic and depends on `:astronomy`. A consumer that needs only sun times, moon phases or solar terms takes `:astronomy` and never carries the calendar tables. One module was rejected for that reason; folding either into `:datetime` was rejected because every date formatter would then carry both.

**Sun times, in two forms.** The main form takes a latitude, a longitude, a `LocalDate` and a `TimeZone`, and answers for the calendar day the caller means at that place, which is what an opening-hours rule means. A second form answers for a UTC day, for callers who want that simpler reading. Each gives sunrise and sunset and civil, nautical and astronomical dawn and dusk as `Instant`s, with polar day and polar night reported as their own results rather than as a guessed time. The calculation is NOAA's solar algorithm with standard refraction at sea level, accurate to about a minute at ordinary latitudes.

**One date type for every calendar.** A calendar date is a calendar system, a year, a month number with a leap-month flag, and a day. RFC 7529's `5L` (the leap month that follows month 5) is that month number with the flag set. Month numbering follows RFC 7529 and CLDR, so Hebrew Adar I is `5L`. Each calendar provides conversion to and from `LocalDate`, the number of months in a year and the number of days in a month. A type per calendar was rejected: `RSCALE` names its calendar at run time, so a caller would have to dispatch on the name anyway.

**Data is generated, not hand-written.** The Japanese era list and the Umm al-Qura table come from CLDR and ICU data through generators under `tools/cldr/`, which record their source version, as the library's other bundled data does.

**Chinese and Korean calendars: a table, and computation outside it.** For 1900 to 2100 the months come from a bundled table generated from ICU's data, about 600 bytes per calendar (each year's month lengths, its leap month and its new-year date); Korean Dangi has its own table because its months are reckoned at Seoul's meridian. Outside that range they are computed from new moons and solar terms. Computation alone was the first recommendation, since `:astronomy` must exist anyway and computing costs little extra; the table was preferred because a new moon within minutes of midnight can put a computed month on the wrong day, and the table settles those years exactly for about 1.2 KB.

**Observation-based Islamic calendars resolve from what the caller supplies.** `islamic` and `islamic-rgsa` take the observed first day of each month the caller knows; a date outside them is reported as unresolved, never guessed. A predicted month start is offered separately, from a crescent-visibility criterion, a date and a location, and returned as a distinct type so a prediction cannot be mistaken for an observation. Both the Odeh (2006) and Yallop (1997) criteria are provided, with Odeh as the default.

**Size budget.** `:astronomy` within 40 KB and `:calendars` within 80 KB as JVM jars, measured when each lands and recorded on its story.

## Consequences

- Sun times, moon phases and every RFC 7529 calendar give the same answer on every target, testable against ICU and Apple's calendars where those platforms have them.
- Two more artifacts to publish, document and keep skills for.
- The bundled tables have edges: the Chinese and Korean table ends at 2100 and Umm al-Qura at the end of its published range, beyond which results are computed and may differ from an authority's table at the rare boundary.
- Observation-based dates are only as good as the month starts a caller supplies. A prediction can disagree with what is announced, which is why it has its own type.
- 2026-10-24, measured: the JVM jars are 53 KB for `:astronomy` and 92 KB for `:calendars`, over the 40 KB and 80 KB budgets. Most of the excess is coefficient and calendar tables compiled as array literals, which a compact string encoding would shrink.
- The library carries astronomical code it must keep correct. Its accuracy claims (about a minute for sun times) are stated on the API and checked against published tables.

## Corrections

- 2026-10-24: The Chinese and Korean calendars do not use ICU's data, as the Decision above says: ICU's months were found wrong where a new moon falls near midnight. The Chinese table now comes from the Hong Kong Observatory's published tables, and the Korean calendar is computed. See [ADR-0007](ADR-0007-chinese-calendar-not-from-icu.md).

