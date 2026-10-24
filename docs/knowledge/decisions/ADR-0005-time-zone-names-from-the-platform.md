# Time-zone names from the platform

ADR-0005 · 2026-09-30 · Status: accepted
Keywords: time-zone names differ between platforms, UTC reads Greenwich Mean Time on iOS, why are zone
          names not bundled, 500 KB zone table removed, Inuktitut zone names, coverage versus
          consistency, java.time ICU NSTimeZone Intl

## Context

The bundled all-language zone-name table of [ADR-0003](ADR-0003-static-time-zone-name-lookup.md) cost about 500 KB in every artifact, and every platform already ships CLDR's zone names: `java.time` on the JVM, ICU on Android, `NSTimeZone` on Apple platforms and `Intl` in JavaScript and Wasm.

## Decision

- Zone names come from the platform's own CLDR at runtime. This affects `Long` and `Full` time formatting, `TimeZone.displayName`, and `:readable`'s `TimeZone.formatReadable(instant, …)`.
- A small bundled supplement covers the languages no platform ships, chiefly Inuktitut, and is consulted before the platform. A per-platform coverage test reports which zone and language pairs fall back.
- The library favours coverage over byte-identical output: the wording follows the reader's operating system and runtime.

## Consequences

The artifacts lose about 500 KB and gain every language the platforms know. The same call can read differently on two targets ("Coordinated Universal Time" on most, "Greenwich Mean Time" on Apple), so tests that compare exact zone names must allow for it. The bare-offset `TimeZone.formatReadable(offset, …)` cannot consult the platform and uses the supplement, then English.
