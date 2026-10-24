# Static time-zone name lookup

ADR-0003 · 2026-06-02 · Status: superseded by ADR-0005
Keywords: time-zone name table, localized zone names, zone abbreviation lookup, JVM 64KB method limit,
          zero-allocation lookup, TimeZoneNamesLookup, bundled zone names

## Context

Localized time-zone names and abbreviations for every supported language need many keys per locale. Lazy maps, or the runtime cache in `Resources.kt`, would allocate and synchronise heavily, and one large inline map of strings in a class can exceed the JVM's 64 KB method limit.

## Decision

- `TimeZoneNamesLookup` resolves names through compile-time nested `when` expressions, with no maps created at runtime.
- Lookups are split into per-language helper methods (`getFullNameFr`, `getAbbreviationEn`, …) so no method passes the 64 KB limit.
- Time-zone caching is removed from `Resources.kt`.

## Consequences

Lookups allocate nothing. The table, about 500 KB, ships in every artifact. That cost led to [ADR-0005](ADR-0005-time-zone-names-from-the-platform.md), which replaced this design in 4.1.0.
