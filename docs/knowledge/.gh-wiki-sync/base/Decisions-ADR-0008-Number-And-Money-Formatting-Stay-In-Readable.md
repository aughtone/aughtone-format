# Number and money formatting stay in :readable

ADR-0008 · 2026-11-19 · Status: accepted
Keywords: separate numbers module, :numbers artifact, where does currency formatting live, money formatting without dates, split readable, module per type, smaller artifact for prices only, moving formatReadable to a new package

## Context

[RAD-0005](Research-RAD-0005-Number-And-Currency-Formatting) found that number and money formatting fall short of CLDR, and leans towards generating their symbols and patterns from CLDR data. Before that work starts, one question shapes everything else: does it stay in `:readable`, where `formatReadable` on numbers and `Money.formatReadable` live today, or move to a new `:numbers` module?

The forces:

- **`:readable` is organised by kind of output, not by input type.** It holds the formatters that turn a value into text a person reads: durations, relative times, data sizes, metrics, quantities, ordinals, lists, numbers, money. A module per input type would be a different organising principle, and would invite the same question for every other type the library formats.
- **The number engine is shared inside the module.** Data sizes, metrics, quantities and money all format their digits through it. A `:numbers` module would have to be an `api` dependency of `:readable`, so every `:readable` caller would carry it regardless.
- **The only gain from a split is a smaller artifact** for a caller who wants numbers or money and nothing else from `:readable`.
- **A split moves public API.** The `formatReadable` overloads would change package, needing deprecated forwarders and a migration across releases.
- **The CLDR data already fits here.** `:readable` already ships relative-time data generated from CLDR; the number data follows the same pattern.

## Decision

Number, money and (when added) percent formatting stay in `:readable`, and the CLDR number data that RAD-0005 calls for is generated into `:readable`. There is no `:numbers` module.

A separate `:numbers` module was rejected: it splits the library by input type where `:readable` is a kind of formatting, it would be a mandatory dependency of `:readable` anyway, and it costs a public API move to serve a narrow audience.

## Consequences

- No package moves, no deprecations, no migration for callers: improvements to number and money output land under the existing API.
- A caller who wants only number or money formatting takes all of `:readable`, including its date-time and relative-time data. If that audience turns out to be real, splitting later is still possible, and costs the same API move it would cost now.
- The CLDR number data adds to `:readable`'s size for every caller. How much is not yet measured; RAD-0005's scope question decides it.
